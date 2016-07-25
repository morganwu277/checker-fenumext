[![travis-build](https://travis-ci.org/morganwu277/checker-fenumext.svg?branch=master)](https://travis-ci.org/morganwu277/checker-fenumext)
## CheckerFramework @IntDef/@StringDef annotations.
Use CheckerFramwork implement the @IntDef/@StringDef annotations.

### 1. Build
Please use Java 1.8 version and Maven.   
You will get your scripts after executing `./build.sh`, move the scripts to any $PATH directory, or you can set the `dist` directory as one of the $PATH directories. 

#### 1.1. Test
Please go to `IntDefCheckerTest.java` and `StringDefCheckerTest.java` for executing unit test.

### 2. Use
`./fenumext-intdef-check.sh ./tests/intdef/TestInstance.java `    
OR     
`./fenumext-stringdef-check.sh ./tests/stringdef/TestInstance.java `

#### Here is the result of `@IntDef` check on the test files.
```bash
[06:58 PM morganwu@morgan-yinnut dist]$ ./fenumext-intdef-check.sh ../tests/intdef/IntDefTest.java 
Processor org.checkerframework.checker.intdef.IntDefChecker matches [] and returns false.
../tests/intdef/IntDefTest.java:8: error: [assignment.type.incompatible] incompatible types in assignment.
  @ClassNumber int myclass = Constants.GRADE_1;
                                      ^
  found   : @GradeNumber int
  required: @ClassNumber int
../tests/intdef/IntDefTest.java:13: error: [assignment.type.incompatible] incompatible types in assignment.
    mygrade = 1;
              ^
  found   : @IntDefUnqualified int
  required: @GradeNumber int
../tests/intdef/IntDefTest.java:19: error: [assignment.type.incompatible] incompatible types in assignment.
    mygrade = Constants.CLASS_2;
                       ^
  found   : @ClassNumber int
  required: @GradeNumber int
../tests/intdef/IntDefTest.java:22: warning: [cast.unsafe] "@IntDefUnqualified int" may not be casted to the type "@GradeNumber int"
    mygrade = ((@GradeNumber int) 12);
               ^
../tests/intdef/IntDefTest.java:25: error: [method.invocation.invalid] call to hashCode() not allowed on the given receiver.
    ((Integer) mygrade).hashCode();
                                ^
  found   : @GradeNumber Integer
  required: @IntDefUnqualified Integer
../tests/intdef/IntDefTest.java:35: error: [binary.type.incompatible] incompatible types.
    if (mygrade == myclass) {
                ^
  found   : @GradeNumber int
  required: @ClassNumber int
5 errors
1 warning

```

#### Here is the result of `@StringDef` check on the test files.
```bash
[06:59 PM morganwu@morgan-yinnut dist]$ ./fenumext-stringdef-check.sh ../tests/stringdef/StringDefTest.java 
Processor org.checkerframework.checker.stringdef.StringDefChecker matches [] and returns false.
../tests/stringdef/StringDefTest.java:7: error: [assignment.type.incompatible] incompatible types in assignment.
  @DepartName String department_ece = "ECE";
                                      ^
  found   : @StringDefUnqualified String
  required: @DepartName String
../tests/stringdef/StringDefTest.java:12: error: [assignment.type.incompatible] incompatible types in assignment.
    school = "";
             ^
  found   : @StringDefUnqualified String
  required: @SchoolName String
../tests/stringdef/StringDefTest.java:18: error: [assignment.type.incompatible] incompatible types in assignment.
    school = Constants.DEPART_CS;
                      ^
  found   : @DepartName String
  required: @SchoolName String
../tests/stringdef/StringDefTest.java:21: warning: [cast.unsafe] "@StringDefUnqualified String" may not be casted to the type "@SchoolName String"
    school = ((@SchoolName String) "UW");
              ^
../tests/stringdef/StringDefTest.java:24: error: [method.invocation.invalid] call to hashCode() not allowed on the given receiver.
    school.hashCode();
                   ^
  found   : @SchoolName String
  required: @StringDefUnqualified String
../tests/stringdef/StringDefTest.java:26: error: [method.invocation.invalid] call to hashCode() not allowed on the given receiver.
    Constants.DEPART_ECE.hashCode();
                                 ^
  found   : @DepartName String
  required: @StringDefUnqualified String
../tests/stringdef/StringDefTest.java:36: error: [binary.type.incompatible] incompatible types.
    if (school == Constants.DEPART_CS) {
               ^
  found   : @SchoolName String
  required: @DepartName String
6 errors
1 warning

```


