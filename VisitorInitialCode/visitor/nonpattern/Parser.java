package visitor.nonpattern;

import java.util.StringTokenizer;

/*
 * Creates a parse tree from the given expression
 */
public class Parser {

  public Parser(String expression) {
    lexAnalyser = new StringTokenizer(expression);
  }

  public TreeNode parse() {
    lexAnalyse();
    return this.parseExpression(0);
  }

  private TreeNode parseExpression(int priority)  {
    TreeNode lhs = parsePrimary();
    if (lhs == null) return null;

    while (isOperator(lexItem) && priority <= leftPrecedence(lexItem))  {
      String op = lexItem;
      lexAnalyse();
      TreeNode rhs = parseExpression(rightPrecedence(op));
      if (rhs == null) {
        System.out.println("Error in expression");
        System.exit(1);
      }
      else  {
        TreeNode temp = lhs;
        lhs = new BinaryTreeNode(op, temp, rhs);
      }
    }
    return lhs;
  }

  private TreeNode parsePrimary() {
    TreeNode nodep = null;

    if (this.isInteger(lexItem))  {
      nodep = new SimpleTreeNode(lexItem);
      if (this.lexAnalyser.hasMoreTokens())
        lexAnalyse();
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

  private void lexAnalyse() {
    lexItem = lexAnalyser.nextToken();
  }

  private StringTokenizer lexAnalyser;
  private String[] operators = new String[]{"+", "-", "*", "/"};
  private String lexItem;

}