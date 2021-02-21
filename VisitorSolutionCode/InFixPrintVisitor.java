package adapter.pattern;

//Visitor to print the expression in infix notation

public class InFixPrintVisitor extends Visitor  {

  public void visitBinaryOperatorNode(BinaryOperatorNode node)  {
    //print out left subtree
    System.out.print("(");
    if (node.getLeft() != null)
      node.getLeft().accept(this);
      //print out this node
    System.out.print(" " + node.getLabel() + " ");
    //print out right subtree
    if (node.getRight() != null)
      node.getRight().accept(this);
    System.out.print(")");
  }

  public void visitNumericNode(NumericNode node)  {
    System.out.print(node.getLabel());
  }
}