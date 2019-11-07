import com.datarobot.prediction.IClassificationPredictor;
import com.datarobot.prediction.Predictors;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ModelLoadTest {

  static IClassificationPredictor predictor = Predictors.getPredictor("5d5da72a3fa59e2850f824fc");

  @Test
  public void test1() {
    // System.out.println("in java.scoring method");
    // URL[] model = new URL[]{new URL("file:///Users/timothy.whittaker/Desktop/sbt-projects/scoring/models/5d5da72a3fa59e2850f824fc.jar")};
    // URLClassLoader classLoader = URLClassLoader.newInstance(model, Thread.currentThread().getContextClassLoader());
    // IClassificationPredictor predictor = Predictors.getPredictor(classLoader);
    assertEquals(predictor.getModelId(), "5d5da72a3fa59e2850f824fc");
  }
}
