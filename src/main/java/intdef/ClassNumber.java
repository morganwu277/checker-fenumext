package intdef;


import org.checkerframework.checker.intdef.qual.IntDef;
import org.checkerframework.checker.intdef.qual.IntDefTop;
import org.checkerframework.framework.qual.SubtypeOf;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
@SubtypeOf(IntDefTop.class)
@IntDef({Constants.CLASS_1,
    Constants.CLASS_2,
    Constants.CLASS_3,
    Constants.CLASS_4,
    Constants.CLASS_5})
@SuppressWarnings("intdef:annotation.type.incompatible")
public @interface ClassNumber {
}
