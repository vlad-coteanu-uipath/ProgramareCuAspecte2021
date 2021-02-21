package visitor.nonpattern;

/*
 * Abstract superclass of both tree elements (simple, binary)
 */
public abstract class TreeNode  {

  protected TreeNode(String label)  {
    this.label = label;
  }

  public abstract void inFixPrint();
  
  public abstract void postFixPrint();
  
  public abstract int compute();

  protected String label;
}