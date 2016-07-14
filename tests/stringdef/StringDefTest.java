package stringdef;

class StringdefUser {
  @SchoolName String school = Constants.SCHOOL_UW;

  //:: error: (assignment.type.incompatible)
  @DepartName String department_ece = "ECE";
  @DepartName String department = Constants.DEPART_ECE;

  void foo() {
    //:: error: (assignment.type.incompatible)
    school = "";

    school = Constants.SCHOOL_WLU;
    school = Constants.SCHOOL_CC;

    //:: error: (assignment.type.incompatible)
    school = Constants.DEPART_CS;

    //:: warning: (cast.unsafe)
    school = ((@SchoolName String) "UW");

    //:: error: (method.invocation.invalid)
    school.hashCode();
    //:: error: (method.invocation.invalid)
    Constants.DEPART_ECE.hashCode();

    // sanity check: unqualified instantiation and call work.
    Object o = new String();
    o.hashCode();

    if (school == Constants.SCHOOL_UW) {
    }

    //:: error: (binary.type.incompatible)
    if (school == Constants.DEPART_CS) {
    }

  }
}
