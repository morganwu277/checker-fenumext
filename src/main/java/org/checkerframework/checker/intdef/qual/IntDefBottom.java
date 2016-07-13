package org.checkerframework.checker.intdef.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.checkerframework.framework.qual.DefaultFor;
import org.checkerframework.framework.qual.ImplicitFor;
import org.checkerframework.framework.qual.LiteralKind;
import org.checkerframework.framework.qual.SubtypeOf;
import org.checkerframework.framework.qual.TargetLocations;
import org.checkerframework.framework.qual.TypeUseLocation;

/**
 * The bottom qualifier for IntDef, its relationships are setup via the
 * IntDefAnnotatedTypeFactory.
 * 
 */
@Documented
@TargetLocations({ TypeUseLocation.EXPLICIT_LOWER_BOUND, TypeUseLocation.EXPLICIT_UPPER_BOUND })
@Target({})
@SubtypeOf({}) // subtype relationships are set up by passing this class as a
               // bottom
               // to the multigraph hierarchy constructor
@Retention(RetentionPolicy.RUNTIME)
@ImplicitFor(literals = { LiteralKind.NULL }, typeNames = { java.lang.Void.class })
@DefaultFor(TypeUseLocation.LOWER_BOUND)
public @interface IntDefBottom {
}
