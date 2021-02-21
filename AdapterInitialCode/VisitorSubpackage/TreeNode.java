//abstract superclass of both tree elements (simple, binary)

package VisitorSubpackage;
public interface TreeNode {

  public abstract String getLabel() ;

  public abstract void accept(Visitor v);
}