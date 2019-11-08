package com.datarobot.java;
// import com.datarobot.prediction.IPredictorInfo;
// import com.datarobot.prediction.IRegressionPredictor;
import com.datarobot.prediction.IClassificationPredictor;
import com.datarobot.prediction.Predictors;
import java.util.HashMap;
import java.util.Map;
import org.postgresql.pljava.annotation.Function;

public class Scoring {

  static IClassificationPredictor predictor = Predictors.getPredictor("5d5da72a3fa59e2850f824fc");

  @Function
  public static String hello(String toWhom) {
    return "Hello, " + toWhom + "!";
  }

 @Function
 public static double score(
  int application_id,
  int loan_amnt,
  int funded_amnt,
  String term,
  String int_rate,
  String int_rate_1,
  double installment,
  String grade,
  String sub_grade,
  String emp_title,
  String emp_length,
  String home_ownership,
  String annual_inc,
  String verification_status,
  String pymnt_plan,
  String url,
  String description,
  String purpose,
  String title,
  String zip_code,
  String addr_state,
  double dti,
  String delinq_2yrs,
  String earliest_cr_line ,
  String inq_last_6mths ,
  String mths_since_last_delinq ,
  String mths_since_last_record ,
  String open_acc ,
  String pub_rec ,
  int revol_bal ,
  String revol_util,
  String total_acc ,
  String initial_list_status ,
  String mths_since_last_major_derog ,
  int policy_code,
  int is_bad
) {

   Map<String, Object> row = new HashMap<>();
    row.put("application_id",application_id);
    row.put("loan_amnt",loan_amnt);
    row.put("funded_amnt",funded_amnt);
    row.put("term",term);
    row.put("int_rate",int_rate);
    row.put("int_rate_1",int_rate_1);
    row.put("installment",installment);
    row.put("grade",grade);
    row.put("sub_grade",sub_grade);
    row.put("emp_title",emp_title);
    row.put("emp_length",emp_length);
    row.put("home_ownership",home_ownership);
    row.put("annual_inc",annual_inc);
    row.put("verification_status",verification_status);
    row.put("pymnt_plan",pymnt_plan);
    row.put("url",url);
    row.put("desc",description);
    row.put("purpose",purpose);
    row.put("title",title);
    row.put("zip_code",zip_code);
    row.put("addr_state",addr_state);
    row.put("dti",dti);
    row.put("delinq_2yrs",delinq_2yrs);
    row.put("earliest_cr_line",earliest_cr_line);
    row.put("inq_last_6mths",inq_last_6mths);
    row.put("mths_since_last_delinq",mths_since_last_delinq);
    row.put("mths_since_last_record",mths_since_last_record);
    row.put("open_acc",open_acc);
    row.put("pub_rec",pub_rec);
    row.put("revol_bal",revol_bal);
    row.put("revol_util",revol_util);
    row.put("total_acc",total_acc);
    row.put("initial_list_status",initial_list_status);
    row.put("mths_since_last_major_derog",mths_since_last_major_derog);
    row.put("policy_code", Integer.toString(policy_code));

    // String pathToModels = "/Users/timothy.whittaker/Desktop/sbt-projects/scoring/models";
    // URL[] model = new URL[]{new URL("file:///Users/timothy.whittaker/Desktop/sbt-projects/scoring/models/5d5da72a3fa59e2850f824fc.jar")};
    // URLClassLoader classLoader = URLClassLoader.newInstance(model, Thread.currentThread().getContextClassLoader());
    // // get a classification predictor object given model
    // IClassificationPredictor predictor = Predictors.getPredictor(classLoader);
    // Map<String, Double> class_probabilities = predictor.score(row);
    //  get a classification predictor object given model

   Map<String, Double> class_probabilities = predictor.score(row);
   return class_probabilities.get("1");

  }

}
