//abstract superclass of both tree elements (simple, binary)

package adapter.pattern;
public interface TreeNode {

  public abstract String getLabel() ;

  public abstract void accept(Visitor v);
}