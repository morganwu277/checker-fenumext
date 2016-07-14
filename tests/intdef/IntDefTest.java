package intdef;

class IntDefUser {
  @GradeNumber int mygrade = Constants.GRADE_1;


  //:: error: (assignment.type.incompatible)
  @ClassNumber int myclass = Constants.GRADE_1;


  void foo() {
    //:: error: (assignment.type.incompatible)
    mygrade = 1;

    mygrade = Constants.GRADE_2;
    mygrade = Constants.GRADE_5;

    //:: error: (assignment.type.incompatible)
    mygrade = Constants.CLASS_2;

    //:: warning: (cast.unsafe)
    mygrade = ((@GradeNumber int) 12);

    //:: error: (method.invocation.invalid)
    ((Integer) mygrade).hashCode();

    // sanity check: unqualified instantiation and call work.
    Object o = new Object();
    o.hashCode();

    if (mygrade == Constants.GRADE_3) {
    }

    //:: error: (binary.type.incompatible)
    if (mygrade == myclass) {
    }

  }
}
