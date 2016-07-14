package org.checkerframework.checker.intdef;

import com.sun.source.tree.*;
import org.checkerframework.common.basetype.BaseTypeChecker;
import org.checkerframework.common.basetype.BaseTypeVisitor;
import org.checkerframework.framework.source.Result;
import org.checkerframework.framework.type.AnnotatedTypeMirror;
import org.checkerframework.framework.type.AnnotatedTypeMirror.AnnotatedDeclaredType;
import org.checkerframework.framework.type.AnnotatedTypeMirror.AnnotatedExecutableType;
import org.checkerframework.framework.type.QualifierHierarchy;
import org.checkerframework.javacutil.TreeUtils;

import javax.lang.model.element.AnnotationMirror;
import java.util.Collections;
import java.util.Set;

public class IntDefVisitor extends BaseTypeVisitor<IntDefAnnotatedTypeFactory> {
  public IntDefVisitor(BaseTypeChecker checker) {
    super(checker);
  }

  @Override
  public Void visitBinary(BinaryTree node, Void p) {
    if (!TreeUtils.isStringConcatenation(node)) {
      // TODO: ignore string concatenations

      // The IntDef Checker is only concerned with primitive types, so just
      // check that
      // the primary annotations are equivalent.
      AnnotatedTypeMirror lhsAtm = atypeFactory.getAnnotatedType(node.getLeftOperand());
      AnnotatedTypeMirror rhsAtm = atypeFactory.getAnnotatedType(node.getRightOperand());

      Set<AnnotationMirror> lhs = lhsAtm.getEffectiveAnnotations();
      Set<AnnotationMirror> rhs = rhsAtm.getEffectiveAnnotations();
      QualifierHierarchy qualHierarchy = atypeFactory.getQualifierHierarchy();
      if (!(qualHierarchy.isSubtype(lhs, rhs) || qualHierarchy.isSubtype(rhs, lhs))) {
        checker.report(Result.failure("binary.type.incompatible", lhsAtm, rhsAtm), node);
      }
    }
    return super.visitBinary(node, p);
  }

  @Override
  public Void visitTypeCast(TypeCastTree node, Void p) {
    return super.visitTypeCast(node, p);
  }

  @Override
  public Void visitSwitch(SwitchTree node, Void p) {
    ExpressionTree expr = node.getExpression();
    AnnotatedTypeMirror exprType = atypeFactory.getAnnotatedType(expr);

    for (CaseTree caseExpr : node.getCases()) {
      ExpressionTree realCaseExpr = caseExpr.getExpression();
      if (realCaseExpr != null) {
        AnnotatedTypeMirror caseType = atypeFactory.getAnnotatedType(realCaseExpr);

        this.commonAssignmentCheck(exprType, caseType, caseExpr, "switch.type.incompatible");
      }
    }
    return super.visitSwitch(node, p);
  }

  @Override
  protected boolean checkConstructorInvocation(AnnotatedDeclaredType dt, AnnotatedExecutableType constructor, NewClassTree src) {
    // Ignore the default annotation on the constructor
    return true;
  }

  @Override
  protected Set<? extends AnnotationMirror> getExceptionParameterLowerBoundAnnotations() {
    return Collections.singleton(atypeFactory.INTDEF_UNQUALIFIED);
  }

  // TODO: should we require a match between switch expression and cases?

  /**
   * Tests that the qualifiers present on the useType are valid qualifiers,
   * given the qualifiers on the declaration of the type, declarationType.
   */
  @Override
  public boolean isValidUse(AnnotatedDeclaredType declarationType, AnnotatedDeclaredType useType, Tree tree) {
    // The checker calls this method to compare the annotation used in a
    // type to the modifier it adds to the class declaration. As our default
    // modifier is Unqualified, this results in an error when a non-subtype
    // is used. Can we use IntDefTop as default instead?
    return true;
  }

}
