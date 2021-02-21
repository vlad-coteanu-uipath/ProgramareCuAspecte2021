package visitor.nonpattern;

/*
 * Tree node that contains two children
 */
public class BinaryTreeNode extends TreeNode {

	public BinaryTreeNode(String label) {
		super(label);
		left = right = null;
	}

	public BinaryTreeNode(String label, TreeNode left, TreeNode right) {
		super(label);
		this.left = left;
		this.right = right;
	}

	// print the bracketed infix version of the expression
	public void inFixPrint() {
		System.out.print("(");
		if (this.left != null)
			this.left.inFixPrint();
		System.out.print(" " + label + " ");
		if (this.right != null)
			this.right.inFixPrint();
		System.out.print(")");
	}

	public void postFixPrint() {
		if (this.left != null)
			this.left.postFixPrint();
		if (this.right != null)
			this.right.postFixPrint();
		System.out.print(" " + label + " ");
	}
	
	public int compute() {
		int leftValue = 0;
		int rightValue = 0;
		if(this.left != null) {
			leftValue = this.left.compute();
		}
		if(this.right != null) {
			rightValue = this.right.compute();
		}
		switch(label) {
		case "+":
			return leftValue + rightValue;
		case "-":
			return leftValue - rightValue;
		case "*":
			return leftValue * rightValue;
		case "/":
			return leftValue / rightValue;
		default:
			throw new RuntimeException("Not suported operation");
		}
	}

	private TreeNode left, right;

}