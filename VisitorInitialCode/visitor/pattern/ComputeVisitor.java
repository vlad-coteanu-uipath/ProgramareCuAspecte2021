package visitor.pattern;

//Visitor to compute the expression value notation

public class ComputeVisitor extends Visitor {

	private int value;

	public void visitBinaryOperatorNode(BinaryOperatorNode node) {

		int leftValue = 0;
		int rightValue = 0;
		if (node.getLeft() != null) {
			node.getLeft().accept(this);
			leftValue = value;
		}
		if (node.getRight() != null) {
			node.getRight().accept(this);
			rightValue = value;
		}
		value = node.compute(leftValue, rightValue);
	}

	public void visitNumericNode(NumericNode node) {
		value = Integer.parseInt(node.getLabel());
	}

	public Object getData() {
		return value;
	}
}