package org.checkerframework.checker.stringdef;

import org.checkerframework.checker.stringdef.qual.StringDef;
import org.checkerframework.checker.stringdef.qual.StringDefBottom;
import org.checkerframework.checker.stringdef.qual.StringDefTop;
import org.checkerframework.checker.stringdef.qual.StringDefUnqualified;
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


public class StringDefAnnotatedTypeFactory extends BaseAnnotatedTypeFactory {

  protected AnnotationMirror STRINGDEF_UNQUALIFIED, STRINGDEF, STRINGDEF_BOTTOM;

  public StringDefAnnotatedTypeFactory(BaseTypeChecker checker) {
    super(checker);

    STRINGDEF_BOTTOM = AnnotationUtils.fromClass(elements, StringDefBottom.class);
    STRINGDEF = AnnotationUtils.fromClass(elements, StringDef.class);
    STRINGDEF_UNQUALIFIED = AnnotationUtils.fromClass(elements, StringDefUnqualified.class);

    this.postInit();
  }

  /**
   * Copied from SubtypingChecker. Instead of returning an empty set if no
   * "quals" option is given, we return StringDef as the only qualifier.
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

    // Load top, bottom, unqualified, and StringDef
    qualSet.add(StringDefTop.class);
    qualSet.add(StringDef.class);
    qualSet.add(StringDefUnqualified.class);
    qualSet.add(StringDefBottom.class);

    // Also call super to load everything in qual directory
    qualSet.addAll(super.createSupportedTypeQualifiers());

    // TODO: warn if no qualifiers given?
    // Just StringDef("..") is still valid, though...
    return Collections.unmodifiableSet(qualSet);
  }

  @Override
  public QualifierHierarchy createQualifierHierarchy(MultiGraphFactory factory) {
    return new StringDefQualifierHierarchy(factory);
  }


  protected class StringDefQualifierHierarchy extends GraphQualifierHierarchy {

    /*
     * The user is expected to introduce additional StringDef annotations. These
     * annotations are declared to be subtypes of StringDefTop, using the
     * 
     * @SubtypeOf annotation. However, there is no way to declare that it is a
     * supertype of Bottom. Therefore, we use the constructor of
     * GraphQualifierHierarchy that allows us to set a dedicated bottom
     * qualifier.
     */
    public StringDefQualifierHierarchy(MultiGraphFactory factory) {
      super(factory, STRINGDEF_BOTTOM);
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
      if (AnnotationUtils.areSameIgnoringValues(lhs, STRINGDEF) && AnnotationUtils.areSameIgnoringValues(rhs, STRINGDEF)) {
        if (AnnotationUtils.areSame(lhs, rhs)) {
          return true;
        } else {
          List<String> lhs_str = FenumExtUtils.getStringValueList(lhs.toString());
          List<String> rhs_str = FenumExtUtils.getStringValueList(rhs.toString());
          return lhs_str.containsAll(rhs_str) && rhs_str.containsAll(lhs_str);
        }
      }
      // Ignore annotation values to ensure that annotation is in supertype map.
      if (AnnotationUtils.areSameIgnoringValues(lhs, STRINGDEF)) {
        lhs = STRINGDEF;
      }
      if (AnnotationUtils.areSameIgnoringValues(rhs, STRINGDEF)) {
        rhs = STRINGDEF;
      }

      return super.isSubtype(rhs, lhs);
    }
  }

}
