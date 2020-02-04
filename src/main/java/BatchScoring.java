package com.datarobot.java;

import com.datarobot.prediction.IClassificationPredictor;
import com.datarobot.prediction.Predictors;
import com.datarobot.mlops.MLOps;
import com.datarobot.mlops.common.exceptions.DRCommonException;
import com.datarobot.mlops.enums.OutputType;

import org.postgresql.pljava.annotation.Function;
import org.postgresql.pljava.ResultSetHandle;
import org.postgresql.pljava.ResultSetProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class BatchScoring implements ResultSetProvider
{

  private String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
  private String username = "timothy.whittaker";
  private String password = "postgres";
  private String query;
  MLOps mlops = null;
  private long predTimeAccum;
  List<List<Double>> predictions = new ArrayList<>();  // adding in for MLOps
  // List<int> associationIds = new ArrayList<>();  // adding in for MLOps
  // private ArrayList<Map<String, Object>> records; // adding in for MLOps
  HashMap<String, List<Object>> featureData = new HashMap<>();

  private Statement stmt = DriverManager.getConnection(jdbcUrl, username, password).createStatement();
  private ResultSet rs;
  private ResultSetMetaData md;
  private int columnCount;

  static IClassificationPredictor predictor = Predictors.getPredictor("5d5da72a3fa59e2850f824fc");

  public BatchScoring(String table) throws SQLException
  {
    query = String.format("SELECT * from %s", table);
    rs = stmt.executeQuery(query);
    md = rs.getMetaData();
    columnCount = md.getColumnCount();
    for (int i = 1; i <= columnCount; ++i) {
        featureData.put(md.getColumnName(i), new ArrayList<Object>());
    }
  }

 public boolean assignRowValues(ResultSet receiver, int currentRow) throws SQLException {
   if(!rs.next())
    return false;

    Map<String, Object> row = new HashMap<>();
    for (int i = 1; i <= columnCount; ++i) {
         String colName = md.getColumnName(i);
         Object value = rs.getObject(i);
         // row.put(md.getColumnName(i), rs.getObject(i));
         row.put(colName, value);
         featureData.get(colName).add(value);
    }

    long startTime = System.currentTimeMillis();  // for MLOps
    Map<String, Double> class_probabilities = predictor.score(row);
    long endTime = System.currentTimeMillis();  // for MLOps

    predictions.add(new ArrayList(class_probabilities.values()));  // for MLOps
    // associationIds.add(rs.getInt(1));  // for MLOps
    predTimeAccum += (endTime - startTime);  // for MLOps

   receiver.updateInt(1, rs.getInt(1));
   receiver.updateDouble(2, class_probabilities.get("1"));
   receiver.updateLong(3, endTime - startTime);
   return true;
 }

  public void close() throws SQLException
  {
   rs.close();
   try {

       // MLOPS: initialize mlops instance, initialize it with the model ID from the CodeGen model
       // mlops = MLOps.getInstance().init();

       mlops = MLOps.getInstance().setModelId("5e2124e8cb3745043b8687df").setOutputType(OutputType.OUTPUT_DIR).setDeploymentId("5e2124e557f20e00471f20fc").setSpoolDir("/tmp/ta").setSpoolFileMaxSize(104857600).setSpoolMaxFiles(5).init();
       // Call the model to generate predictions.
       // For this example, we simply fake the predictions.

       // MLOPS: report the predictions to MLOps service.
       List<String> classes = Arrays.asList(predictor.getClassLabels());
       mlops.reportClassificationPredictions(predictions, classes);//, associationIds);

       // MLOPS: report number of samples and time to produce predictions.
       mlops.reportDeploymentStats(predictions.size(), predTimeAccum);

       // MLOPS: report feature data; this is used for feature drift.
       mlops.reportFeatureData(featureData);

   } catch (DRCommonException e) {
       // File f = new File("/Users/timothy.whittaker/Desktop/exception.txt");
       try {
         PrintWriter pw = new PrintWriter("/Users/timothy.whittaker/Desktop/exception.txt");
         pw.write(e.getMessage());
         // pw.write(mlops.spoolDir);
         // pw.write(System.getenv("MLOPS_SPOOLER_DIR_PATH") );
         // pw.write(System.getenv("MLOPS_DEPLOYMENT_ID") );
         // pw.write(System.getenv("MLOPS_MODEL_ID") );
         // pw.write(System.getenv("MLOPS_SPOOLER_MAX_FILES") );
         pw.write("Shutting down MLOps Agent");
         mlops.shutdown();
         pw.close();
       } catch (FileNotFoundException ee) {
         int v = 1;
       }

   } finally {
       // MLOPS: shutdown
       if (mlops != null) {
           mlops.shutdown();
       }
   }
  }
  @Function(type="scoredRecord")
  public static ResultSetProvider batchScore(String table)
  throws SQLException
  {
    return new BatchScoring(table);
  }

}
