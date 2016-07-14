package stringdef;


import org.checkerframework.checker.stringdef.qual.StringDef;
import org.checkerframework.checker.stringdef.qual.StringDefTop;
import org.checkerframework.framework.qual.SubtypeOf;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
@SubtypeOf(StringDefTop.class)
@StringDef({Constants.SCHOOL_UW,
    Constants.SCHOOL_CC,
    Constants.SCHOOL_WLU})
@SuppressWarnings("stringdef:annotation.type.incompatible")
public @interface SchoolName {
}
