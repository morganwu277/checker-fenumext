package org.checkerframework.checker.stringdef;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.checkerframework.common.basetype.BaseTypeChecker;
import org.checkerframework.common.basetype.BaseTypeVisitor;
import org.checkerframework.common.subtyping.SubtypingChecker;
import org.checkerframework.framework.source.SupportedOptions;

/**
 * The main checker class for the StringDef Checker.
 */
@SupportedOptions({ "quals", "qualDirs" })
public class StringDefChecker extends BaseTypeChecker {

  // @Override
  // public void initChecker() {
  // super.initChecker();
  // }

  /**
   * we suppress warnings with these keys: [all, StringDeftop, StringDef, polyall,
   * StringDefbottom, StringDefunqualified] Copied from SubtypingChecker; cannot reuse
   * it, because SubtypingChecker is final.
   * 
   * @see SubtypingChecker#getSuppressWarningsKeys()
   */
  @Override
  public Collection<String> getSuppressWarningsKeys() {
    Set<Class<? extends Annotation>> annos = ((BaseTypeVisitor<?>) visitor).getTypeFactory().getSupportedTypeQualifiers();
    if (annos.isEmpty()) {
      return super.getSuppressWarningsKeys();
    }

    Set<String> swKeys = new HashSet<>();
    swKeys.add(SUPPRESS_ALL_KEY);
    for (Class<? extends Annotation> anno : annos) {
      swKeys.add(anno.getSimpleName().toLowerCase());
    }

    return swKeys;
  }
}
