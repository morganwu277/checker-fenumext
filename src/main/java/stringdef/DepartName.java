package stringdef;

import org.checkerframework.checker.stringdef.qual.StringDef;
import org.checkerframework.checker.stringdef.qual.StringDefTop;
import org.checkerframework.framework.qual.SubtypeOf;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
@SubtypeOf(StringDefTop.class)
@StringDef({Constants.DEPART_CS,
    Constants.DEPART_ECE,
    Constants.DEPART_MATH
})
@SuppressWarnings("stringdef:annotation.type.incompatible")
public @interface DepartName {
}
