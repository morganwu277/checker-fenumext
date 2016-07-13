import java.io.File;

import org.checkerframework.checker.stringdef.StringDefChecker;
import org.checkerframework.framework.test.CheckerFrameworkTest;
import org.junit.runners.Parameterized.Parameters;


public class StringDefCheckerTest extends CheckerFrameworkTest{

    public StringDefCheckerTest(File testFile) {
        super(testFile,
              StringDefChecker.class, 
              "",
              "-XprintProcessorInfo",
              "-verbose",
              "-Anomsgtext"
                );
    }   
    
  @Parameters
  public static String[] getTestDirs() {
    return new String[] { "stringdef" };
  }
    
}
