package VisitorSubpackage;

import java.util.*;

//This class parses the given expression into a parse tree

public class Parser {

  public Parser(String expression) {
    lexAnalyser = new StringTokenizer(expression);
  }

  public TreeNode parse() {
    getNextToken();
    return this.parseExpression(0);
  }

  private TreeNode parseExpression(int priority)  {
    TreeNode lhs = parseInteger();
    if (lhs == null) return null;

    while (isOperator(lexItem) && priority <= leftPrecedence(lexItem))  {
      String op = lexItem;
      getNextToken();
      TreeNode rhs = parseExpression(rightPrecedence(op));
      if (rhs == null) {
        System.out.println("Error in expression");
        System.exit(1);
      }
      else  {
        TreeNode temp = lhs;
        //lhs = new BinaryTreeNode(op, temp, rhs);
        if(op.equals("+"))
          lhs = new AdditionNode(temp, rhs);
        else
        if(op.equals("-"))
          lhs = new SubtractionNode(temp, rhs);
        else
        if(op.equals("*"))
          lhs = new MultiplicationNode(temp, rhs);
        else
        if(op.equals("/"))
          lhs = new DivisionNode(temp, rhs);
        else
          System.out.println("ERROR in INPUT!");
      }
    }
    return lhs;
  }

  private TreeNode parseInteger() {
    TreeNode nodep = null;

    if (this.isInteger(lexItem))  {
      nodep = new NumericNode(new Integer(lexItem).intValue());
      if (this.lexAnalyser.hasMoreTokens())
        getNextToken();
      else
        this.lexItem = null;
    }
    else  {
      System.out.println("Error in expression " + lexItem);
      System.exit(1);
    }
    return nodep;
  }

  private int leftPrecedence(String op) {
    if (op.equals("*"))
      return 3;
    else if (op.equals("/"))
      return 3;
    else if (op.equals("+"))
      return 1;
    else if (op.equals("-"))
      return 1;
    else
      return - 1;
  }

  private int rightPrecedence(String op)  {
    if (op.equals("*"))
      return 4;
    else if (op.equals("/"))
      return 4;
    else if (op.equals("+"))
      return 2;
    else if (op.equals("-"))
      return 2;
    else
      return - 1;
  }

  private boolean isInteger(String integer) {
    try {
      Integer.parseInt(integer);
      return true;
    }
    catch (NumberFormatException nfe) {
      return false;
    }
  }

  private boolean isOperator(String op) {
    boolean isOp = false;
    for (int i = 0; i < operators.length; i++)
      if (operators[i].equals(op))
        isOp = true;
    return isOp;
  }

  private void getNextToken() {
    lexItem = lexAnalyser.nextToken();
  }

  private StringTokenizer lexAnalyser;
  private String[] operators = new String[]{"+", "-", "*", "/"};
  private String lexItem;


}
