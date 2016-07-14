package intdef;

import org.checkerframework.checker.intdef.qual.IntDef;
import org.checkerframework.checker.intdef.qual.IntDefTop;
import org.checkerframework.framework.qual.SubtypeOf;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
@SubtypeOf(IntDefTop.class)
@IntDef({Constants.GRADE_1,
    Constants.GRADE_2,
    Constants.GRADE_3,
    Constants.GRADE_4,
    Constants.GRADE_5,
    Constants.GRADE_6,
    Constants.GRADE_7,
    Constants.GRADE_8,
    Constants.GRADE_9,
    Constants.GRADE_10,
    Constants.GRADE_11,
    Constants.GRADE_12})
@SuppressWarnings("intdef:annotation.type.incompatible")
public @interface GradeNumber {
}
