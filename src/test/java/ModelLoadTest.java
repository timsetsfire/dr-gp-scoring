import com.datarobot.prediction.IPredictorInfo;
import com.datarobot.prediction.IRegressionPredictor;
import com.datarobot.prediction.IClassificationPredictor;
import com.datarobot.prediction.Predictors;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.postgresql.pljava.annotation.Function;
import java.lang.Thread;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class ModelLoadTest {

  static IClassificationPredictor predictor = Predictors.getPredictor("5d5da72a3fa59e2850f824fc");

  @Test
  public void test1() throws MalformedURLException {
    // System.out.println("in java.scoring method");
    // URL[] model = new URL[]{new URL("file:///Users/timothy.whittaker/Desktop/sbt-projects/scoring/models/5d5da72a3fa59e2850f824fc.jar")};
    // URLClassLoader classLoader = URLClassLoader.newInstance(model, Thread.currentThread().getContextClassLoader());
    // IClassificationPredictor predictor = Predictors.getPredictor(classLoader);
    assertEquals(predictor.getModelId(), "5d5da72a3fa59e2850f824fc");
  }
}
