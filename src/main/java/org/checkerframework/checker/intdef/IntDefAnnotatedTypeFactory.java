package org.checkerframework.checker.intdef;

import org.checkerframework.checker.intdef.qual.IntDef;
import org.checkerframework.checker.intdef.qual.IntDefBottom;
import org.checkerframework.checker.intdef.qual.IntDefTop;
import org.checkerframework.checker.intdef.qual.IntDefUnqualified;
import org.checkerframework.checker.utils.FenumExtUtils;
import org.checkerframework.common.basetype.BaseAnnotatedTypeFactory;
import org.checkerframework.common.basetype.BaseTypeChecker;
import org.checkerframework.framework.type.AnnotationClassLoader;
import org.checkerframework.framework.type.QualifierHierarchy;
import org.checkerframework.framework.util.GraphQualifierHierarchy;
import org.checkerframework.framework.util.MultiGraphQualifierHierarchy.MultiGraphFactory;
import org.checkerframework.javacutil.AnnotationUtils;

import javax.lang.model.element.AnnotationMirror;
import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public class IntDefAnnotatedTypeFactory extends BaseAnnotatedTypeFactory {

  protected AnnotationMirror INTDEF_UNQUALIFIED;
  protected AnnotationMirror INTDEF, INTDEF_BOTTOM;

  public IntDefAnnotatedTypeFactory(BaseTypeChecker checker) {
    super(checker);

    INTDEF_BOTTOM = AnnotationUtils.fromClass(elements, IntDefBottom.class);
    INTDEF = AnnotationUtils.fromClass(elements, IntDef.class);
    INTDEF_UNQUALIFIED = AnnotationUtils.fromClass(elements, IntDefUnqualified.class);

    this.postInit();
  }

  /**
   * Copied from SubtypingChecker. Instead of returning an empty set if no
   * "quals" option is given, we return IntDef as the only qualifier.
   */
  @Override
  protected Set<Class<? extends Annotation>> createSupportedTypeQualifiers() {
    AnnotationClassLoader loader = new AnnotationClassLoader(checker);

    Set<Class<? extends Annotation>> qualSet = new LinkedHashSet<Class<? extends Annotation>>();

    // Load externally defined quals given in the -Aquals and/or -AqualDirs
    // options
    String qualNames = checker.getOption("quals");
    String qualDirectories = checker.getOption("qualDirs");

    // load individually named qualifiers
    if (qualNames != null) {
      for (String qualName : qualNames.split(",")) {
        qualSet.add(loader.loadExternalAnnotationClass(qualName));
      }
    }

    // load directories of qualifiers
    if (qualDirectories != null) {
      for (String dirName : qualDirectories.split(":")) {
        qualSet.addAll(loader.loadExternalAnnotationClassesFromDirectory(dirName));
      }
    }

    // Load top, bottom, unqualified, and IntDef
    qualSet.add(IntDefTop.class);
    qualSet.add(IntDef.class);
    qualSet.add(IntDefUnqualified.class);
    qualSet.add(IntDefBottom.class);

    // Also call super to load everything in qual directory
    qualSet.addAll(super.createSupportedTypeQualifiers());

    // TODO: warn if no qualifiers given?
    // Just IntDef("..") is still valid, though...
    return Collections.unmodifiableSet(qualSet);
  }

  @Override
  public QualifierHierarchy createQualifierHierarchy(MultiGraphFactory factory) {
    return new IntDefQualifierHierarchy(factory);
  }


  protected class IntDefQualifierHierarchy extends GraphQualifierHierarchy {

    /*
     * The user is expected to introduce additional IntDef annotations. These
     * annotations are declared to be subtypes of IntDefTop, using the
     * 
     * @SubtypeOf annotation. However, there is no way to declare that it is a
     * supertype of Bottom. Therefore, we use the constructor of
     * GraphQualifierHierarchy that allows us to set a dedicated bottom
     * qualifier.
     */
    public IntDefQualifierHierarchy(MultiGraphFactory factory) {
      super(factory, INTDEF_BOTTOM);
    }

    /**
     * Tests whether rhs is a sub-qualifier of lhs
     * 
     * @param rhs
     *          the right-hand side, i.e. the sub qualifier
     * @param lhs
     *          the left-hand side, i.e. the super qualifier
     */
    @Override
    public boolean isSubtype(AnnotationMirror rhs, AnnotationMirror lhs) {
      if (AnnotationUtils.areSameIgnoringValues(lhs, INTDEF) && AnnotationUtils.areSameIgnoringValues(rhs, INTDEF)) {
        if (AnnotationUtils.areSame(lhs, rhs)) {
          return true;
        } else {
          List<Integer> lhs_int = FenumExtUtils.getIntValueList(lhs.toString());
          List<Integer> rhs_int = FenumExtUtils.getIntValueList(rhs.toString());
          return lhs_int.containsAll(rhs_int) && rhs_int.containsAll(lhs_int);
        }
      }
      // Ignore annotation values to ensure that annotation is in supertype map.
      if (AnnotationUtils.areSameIgnoringValues(lhs, INTDEF)) {
        lhs = INTDEF;
      }
      if (AnnotationUtils.areSameIgnoringValues(rhs, INTDEF)) {
        rhs = INTDEF;
      }

      return super.isSubtype(rhs, lhs);
    }
  }

}
