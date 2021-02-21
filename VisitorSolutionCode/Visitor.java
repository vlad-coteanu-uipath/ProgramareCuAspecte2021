//Abstract superclass of all visitors
package adapter.pattern;

public abstract class Visitor {
  public abstract void visitBinaryOperatorNode(BinaryOperatorNode node);
  public abstract void visitNumericNode(NumericNode node);
}