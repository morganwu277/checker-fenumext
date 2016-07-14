package stringdef;

import org.checkerframework.checker.stringdef.qual.StringDef;

@SuppressWarnings("stringdef:assignment.type.incompatible")
public class StringDefTest {
  public static final @StringDef("A") String ACONST1 = "ACONST2";
  public static final @StringDef("A") String ACONST2 = "ACONST2";
  public static final @StringDef("A") String ACONST3 = "ACONST3";

  public static final @StringDef("B") String BCONST1 = "BCONST1";
  public static final @StringDef("B") String BCONST2 = "BCONST2";
  public static final @StringDef({"A", "B"}) Object ABCONST1 = "ABCONST1";
}

class StringdefUser {
  @StringDef("A")
  Object state1 = StringDefTest.ACONST1;

  //:: error: (assignment.type.incompatible)
  @StringDef("B") Object state2 = StringDefTest.ACONST1;

  @StringDef({"A", "B"}) Object state3 = StringDefTest.ABCONST1;

  void foo(StringDefTest t) {
    //:: error: (assignment.type.incompatible)
    state1 = new String();

    state1 = t.ACONST2;
    state1 = t.ACONST3;

    //:: error: (assignment.type.incompatible)
    state1 = t.BCONST1;

    //:: error: (assignment.type.incompatible)
    state3 = t.ACONST1;
    //:: error: (assignment.type.incompatible)
    state3 = t.BCONST1;

    //:: warning: (cast.unsafe)
    state3 = (@StringDef({"A","B"}) String)(t.ACONST1);

    //:: error: (assignment.type.incompatible)
    state1 = t.ABCONST1;

    //:: error: (method.invocation.invalid)
    state1.hashCode();
    //:: error: (method.invocation.invalid)
    t.ACONST1.hashCode();

    // sanity check: unqualified instantiation and call work.
    Object o = new String();
    o.hashCode();

    if ( t.ACONST1 == t.ACONST2  ) {
    }

    //:: error: (binary.type.incompatible)
    if ( t.ACONST1 == t.BCONST2  ) {
    }
    //:: error: (binary.type.incompatible)
    if ( t.ACONST1 == t.BCONST1  ) {
    }

  }
}
