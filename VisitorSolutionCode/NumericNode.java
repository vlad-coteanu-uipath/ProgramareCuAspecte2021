package adapter.pattern;
//Define numeric node with no children

public class NumericNode implements TreeNode {

  public NumericNode(int val) {
    value = val;
  }

  public int getValue(int val){
    return value;
  }

  public String getLabel(){
     return (new Integer(value)).toString();
  }

  /*************************/
  //accept a visitor
  public void accept(Visitor v) {
    /***************************/
    //double dispatch - call appropriate visitor function
    v.visitNumericNode(this);
  }
  private int value;
}