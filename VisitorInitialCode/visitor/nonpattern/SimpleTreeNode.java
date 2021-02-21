package visitor.nonpattern;

/*
 * Define tree element with no children
 */
public class SimpleTreeNode extends TreeNode  {

  public SimpleTreeNode(String label) {
    super(label);
  }

  public void inFixPrint()  {
    System.out.print(label);
  }
  
  public void postFixPrint() {
	  System.out.print(" " + label + " ");
  }
  
  public int compute() {
	  return Integer.parseInt(label);
  }
}