package org.checkerframework.checker.stringdef.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.checkerframework.framework.qual.DefaultFor;
import org.checkerframework.framework.qual.DefaultQualifierInHierarchy;
import org.checkerframework.framework.qual.SubtypeOf;
import org.checkerframework.framework.qual.TypeUseLocation;

/**
 * An unqualified type. Such a type is incomparable to (that is, neither a
 * subtype nor a supertype of) any StringDef type.
 * <p>
 *
 * This annotation may not be written in source code; it is an implementation
 * detail of the checker.
 */
@Documented
@SubtypeOf({ StringDefTop.class })
@DefaultQualifierInHierarchy
@DefaultFor({ TypeUseLocation.IMPLICIT_UPPER_BOUND, TypeUseLocation.IMPLICIT_LOWER_BOUND, TypeUseLocation.EXCEPTION_PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Target({}) // empty target prevents programmers from writing this in a program
public @interface StringDefUnqualified {
}
