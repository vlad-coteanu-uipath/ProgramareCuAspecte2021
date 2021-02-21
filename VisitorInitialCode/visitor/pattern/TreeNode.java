package visitor.pattern;

/*
 * Abstract superclass of both tree elements (simple, binary)
 */
public interface TreeNode {

  public abstract String getLabel() ;

  // enable visitation - makes pattern work
  public abstract void accept(Visitor v);
}