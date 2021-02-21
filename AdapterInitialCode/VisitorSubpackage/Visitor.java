//Abstract superclass of all visitors
package VisitorSubpackage;

public abstract class Visitor {
  public abstract void visitBinaryOperatorNode(BinaryOperatorNode node);
  public abstract void visitNumericNode(NumericNode node);
}