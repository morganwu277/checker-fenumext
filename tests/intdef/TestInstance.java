package intdef;

import org.checkerframework.checker.intdef.qual.IntDef;

@SuppressWarnings("intdef:assignment.type.incompatible")
public class TestInstance {
  public final @IntDef(1) Object ACONST1 = new Object();
  public final @IntDef(1) Object ACONST2 = new Object();
  public final @IntDef(1) Object ACONST3 = new Object();

  public final @IntDef(2) Object BCONST1 = new Object();
  public final @IntDef(2) Object BCONST2 = new Object();
  public final @IntDef({ 1, 2 }) Object ABCONST1 = new Object(); // parent-type
}

class IntDefUser {
  @IntDef(1)
  Object state1 = new TestInstance().ACONST1;

  @IntDef(2)
  //:: error: (assignment.type.incompatible)
  Object state2 = new TestInstance().ACONST1;

  @IntDef({ 1, 2 })
  Object state3 = new TestInstance().ABCONST1;

  void foo(TestInstance t) {
    //:: error: (assignment.type.incompatible)
    state1 = new Object();

    state1 = t.ACONST2;
    state1 = t.ACONST3;

    //:: error: (assignment.type.incompatible)
    state1 = t.BCONST1;

    state3 = t.ACONST1;
    state3 = t.BCONST1;

    state3 = (@IntDef({ 1, 2 }) Object) (t.ACONST1);

    //:: error: (assignment.type.incompatible)
    state1 = t.ABCONST1;

    //:: error: (method.invocation.invalid)
    state1.hashCode();
    //:: error: (method.invocation.invalid)
    t.ACONST1.hashCode();

    // sanity check: unqualified instantiation and call work.
    Object o = new Object();
    o.hashCode();

    if (t.ACONST1 == t.ACONST2) {
    }

    //:: error: (binary.type.incompatible)
    if (t.ACONST1 == t.BCONST2) {
    }
    //:: error: (binary.type.incompatible)
    if (t.ACONST1 == t.BCONST1) {
    }

  }
}
