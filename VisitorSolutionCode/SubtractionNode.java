package adapter.pattern;

class SubtractionNode extends BinaryOperatorNode{

  public SubtractionNode(TreeNode left, TreeNode right) {
    super(left,right);
  }

  public String getLabel(){
   return "-";
 }
  
  public int compute(int a, int b){
	  return a-b;
  }

}