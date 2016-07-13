package org.checkerframework.checker.stringdef;

import java.util.Collections;
import java.util.Set;

import javax.lang.model.element.AnnotationMirror;

import org.checkerframework.common.basetype.BaseTypeChecker;
import org.checkerframework.common.basetype.BaseTypeVisitor;
import org.checkerframework.framework.source.Result;
import org.checkerframework.framework.type.AnnotatedTypeMirror;
import org.checkerframework.framework.type.AnnotatedTypeMirror.AnnotatedDeclaredType;
import org.checkerframework.framework.type.AnnotatedTypeMirror.AnnotatedExecutableType;
import org.checkerframework.framework.type.QualifierHierarchy;
import org.checkerframework.javacutil.TreeUtils;

import com.sun.source.tree.BinaryTree;
import com.sun.source.tree.CaseTree;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.NewClassTree;
import com.sun.source.tree.SwitchTree;
import com.sun.source.tree.Tree;

public class StringDefVisitor extends BaseTypeVisitor<StringDefAnnotatedTypeFactory> {
  public StringDefVisitor(BaseTypeChecker checker) {
    super(checker);
  }

  @Override
  public Void visitBinary(BinaryTree node, Void p) {
    if (!TreeUtils.isStringConcatenation(node)) {
      // TODO: ignore string concatenations

      // The StringDef Checker is only concerned with primitive types, so just
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
    return Collections.singleton(atypeFactory.STRINGDEF_UNQUALIFIED);
  }

  // TODO: should we require a match between switch expression and cases?

  /**
   * Tests that the qualifiers present on the useType are valid qualifiers,
   * given the qualifiers on the declaration of the type, declarationType.
   * 可能不需要重写
   */
  @Override
  public boolean isValidUse(AnnotatedDeclaredType declarationType, AnnotatedDeclaredType useType, Tree tree) {
    // The checker calls this method to compare the annotation used in a
    // type to the modifier it adds to the class declaration. As our default
    // modifier is Unqualified, this results in an error when a non-subtype
    // is used. Can we use StringDefTop as default instead?
    return true;
  }

}
