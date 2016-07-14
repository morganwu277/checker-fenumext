import org.checkerframework.checker.intdef.IntDefChecker;
import org.checkerframework.framework.test.CheckerFrameworkTest;
import org.junit.runners.Parameterized.Parameters;

import java.io.File;


public class IntDefCheckerTest extends CheckerFrameworkTest {

  public IntDefCheckerTest(File testFile) {
    super(testFile,
        IntDefChecker.class,
        "",
        "-XprintProcessorInfo",
        "-verbose",
        "-Anomsgtext",
        "-Aquals=intdef.GradeNumber"
    );
  }

  @Parameters
  public static String[] getTestDirs() {
    return new String[]{"intdef"};
  }

}
