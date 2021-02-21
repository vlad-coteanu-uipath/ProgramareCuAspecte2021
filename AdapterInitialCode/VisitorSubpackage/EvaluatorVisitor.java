package VisitorSubpackage;

import java.util.*;

//Visitor to print the value of the expression

public class EvaluatorVisitor extends Visitor  {

  public EvaluatorVisitor() {
    value = 0;
    this.stack = new Stack();
  }

  public void visitBinaryOperatorNode(BinaryOperatorNode node)  {
    node.getLeft().accept(this);
    node.getRight().accept(this);

    //reverse the order
    int right = Integer.parseInt((String) stack.pop());
    int left = Integer.parseInt((String) stack.pop());
    // apply appropriate operator
    stack.push("" + node.compute(left,right));
  }

  public void visitNumericNode(NumericNode node)  {
    stack.push(node.getLabel());
  }

  public int getValue() {
    return (new Integer((String) this.stack.pop())).intValue();
  }

  private int value;
  private Stack stack; //hold values/operators (all Strings)
}