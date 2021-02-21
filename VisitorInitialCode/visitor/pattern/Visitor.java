package visitor.pattern;

/*
 * Abstract superclass of all visitors
 */
public abstract class Visitor {
  public abstract void visitBinaryOperatorNode(BinaryOperatorNode node);
  public abstract void visitNumericNode(NumericNode node);
  public Object getData() { return null; }
}