## CheckerFramework @IntDef/@StringDef annotations.
Use CheckerFramwork implement the @IntDef/@StringDef annotations.

### 1. Build

You will get your scripts after executing `./build.sh`, move the scripts to any $PATH directory, or you can set the `dist` directory as one of the $PATH directories. 

### 2. Use
`./fenumext-intdef-check.sh ./tests/intdef/TestInstance.java ` OR `./fenumext-stringdef-check.sh ./tests/stringdef/TestInstance.java `

#### Here is the result of `@IntDef` check on the test files.
```bash
[05:34 PM morganwu@morgan-yinnut checker-fenumext]$ ./fenumext-intdef-check.sh ./tests/intdef/TestInstance.java 
Processor org.checkerframework.checker.intdef.IntDefChecker matches [java.lang.SuppressWarnings] and returns false.
./tests/intdef/TestInstance.java:22: error: [assignment.type.incompatible] incompatible types in assignment.
  Object state2 = new TestInstance().ACONST1;
                                    ^
  found   : @IntDef(1) Object
  required: @IntDef(2) Object
./tests/intdef/TestInstance.java:29: error: [assignment.type.incompatible] incompatible types in assignment.
    state1 = new Object();
             ^
  found   : @IntDefUnqualified Object
  required: @IntDef(1) Object
./tests/intdef/TestInstance.java:35: error: [assignment.type.incompatible] incompatible types in assignment.
    state1 = t.BCONST1;
              ^
  found   : @IntDef(2) Object
  required: @IntDef(1) Object
./tests/intdef/TestInstance.java:43: error: [assignment.type.incompatible] incompatible types in assignment.
    state1 = t.ABCONST1;
              ^
  found   : @IntDef({1, 2}) Object
  required: @IntDef(1) Object
./tests/intdef/TestInstance.java:46: error: [method.invocation.invalid] call to hashCode() not allowed on the given receiver.
    state1.hashCode();
                   ^
  found   : @IntDef(1) Object
  required: @IntDefUnqualified Object
./tests/intdef/TestInstance.java:48: error: [method.invocation.invalid] call to hashCode() not allowed on the given receiver.
    t.ACONST1.hashCode();
                      ^
  found   : @IntDef(1) Object
  required: @IntDefUnqualified Object
./tests/intdef/TestInstance.java:58: error: [binary.type.incompatible] incompatible types.
    if (t.ACONST1 == t.BCONST2) {
                  ^
  found   : @IntDef(1) Object
  required: @IntDef(2) Object
./tests/intdef/TestInstance.java:61: error: [binary.type.incompatible] incompatible types.
    if (t.ACONST1 == t.BCONST1) {
                  ^
  found   : @IntDef(1) Object
  required: @IntDef(2) Object
8 errors

```

#### Here is the result of `@StringDef` check on the test files.
```bash
[05:34 PM morganwu@morgan-yinnut checker-fenumext]$ ./fenumext-stringdef-check.sh ./tests/stringdef/TestInstance.java
Processor org.checkerframework.checker.stringdef.StringDefChecker matches [java.lang.SuppressWarnings] and returns false.
./tests/stringdef/TestInstance.java:23: error: [assignment.type.incompatible] incompatible types in assignment.
  Object state2 = new TestInstance().ACONST1;
                                    ^
  found   : @StringDef("A") Object
  required: @StringDef("B") Object
./tests/stringdef/TestInstance.java:30: error: [assignment.type.incompatible] incompatible types in assignment.
    state1 = new Object();
             ^
  found   : @StringDefUnqualified Object
  required: @StringDef("A") Object
./tests/stringdef/TestInstance.java:36: error: [assignment.type.incompatible] incompatible types in assignment.
    state1 = t.BCONST1;
              ^
  found   : @StringDef("B") Object
  required: @StringDef("A") Object
./tests/stringdef/TestInstance.java:45: error: [assignment.type.incompatible] incompatible types in assignment.
    state1 = t.ABCONST1;
              ^
  found   : @StringDef({"A", "B"}) Object
  required: @StringDef("A") Object
./tests/stringdef/TestInstance.java:48: error: [method.invocation.invalid] call to hashCode() not allowed on the given receiver.
    state1.hashCode();
                   ^
  found   : @StringDef("A") Object
  required: @StringDefUnqualified Object
./tests/stringdef/TestInstance.java:50: error: [method.invocation.invalid] call to hashCode() not allowed on the given receiver.
    t.ACONST1.hashCode();
                      ^
  found   : @StringDef("A") Object
  required: @StringDefUnqualified Object
./tests/stringdef/TestInstance.java:60: error: [binary.type.incompatible] incompatible types.
    if ( t.ACONST1 == t.BCONST2  ) {
                   ^
  found   : @StringDef("A") Object
  required: @StringDef("B") Object
./tests/stringdef/TestInstance.java:63: error: [binary.type.incompatible] incompatible types.
    if ( t.ACONST1 == t.BCONST1  ) {
                   ^
  found   : @StringDef("A") Object
  required: @StringDef("B") Object
8 errors

```