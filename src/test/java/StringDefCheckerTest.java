import org.checkerframework.checker.stringdef.StringDefChecker;
import org.checkerframework.framework.test.CheckerFrameworkTest;
import org.junit.runners.Parameterized.Parameters;

import java.io.File;


public class StringDefCheckerTest extends CheckerFrameworkTest {

  public StringDefCheckerTest(File testFile) {
    super(testFile,
        StringDefChecker.class,
        "",
        "-XprintProcessorInfo",
        "-verbose",
        "-Anomsgtext",
        "-Aquals=stringdef.SchoolName,stringdef.DepartName"

    );
  }

  @Parameters
  public static String[] getTestDirs() {
    return new String[]{"stringdef"};
  }

}
