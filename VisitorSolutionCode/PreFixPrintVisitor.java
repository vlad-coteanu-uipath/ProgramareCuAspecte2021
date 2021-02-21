package adapter.pattern;

//Visitor to print the expression in prefix notation

public class PreFixPrintVisitor extends Visitor  {

  public void visitBinaryOperatorNode(BinaryOperatorNode node)  {
    //print out this node
    System.out.print("(");
    System.out.print(node.getLabel() + " ");
    //print out left subtree
    if (node.getLeft() != null)
      node.getLeft().accept(this);
    System.out.print(" ");
    //print out right subtree
    if (node.getRight() != null)
      node.getRight().accept(this);
    System.out.print(")");
  }

  public void visitNumericNode(NumericNode node)  {
    System.out.print(node.getLabel());
  }
}
