import com.datarobot.prediction.IClassificationPredictor;
import com.datarobot.prediction.Predictors;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.io.File;
// import com.datarobot.mlops.MLOps;
// import com.datarobot.mlops.enums.OutputType;
// import com.datarobot.mlops.common.exceptions.DRCommonException;


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

  @Test
  public void test2() {
    File f = new File("/tmp/ta");
    assertTrue(f.exists());
// mlops = MLOps.getInstance().setModelId("5e2124e8cb3745043b8687df").setOutputType(OutputType.OUTPUT_DIR).setDeploymentId("5e2124e557f20e00471f20fc").init()
// .init();
// mlops = MLOps.getInstance().setModelId("5e2124e8cb3745043b8687df").setOutputType(OutputType.OUTPUT_DIR).setDeploymentId("5e2124e557f20e00471f20fc").init()
// mlops.setSpoolDir("/tmp/ta/makebelieve").setSpoolFileMaxSize(104857600).setSpoolMaxFiles(5);
  }
}
