package visitor.pattern;

//Visitor to print the expression in postfix notation

public class PostFixPrintVisitor extends Visitor {

	public void visitBinaryOperatorNode(BinaryOperatorNode node) {
		if (node.getLeft() != null)
			node.getLeft().accept(this);
		if (node.getRight() != null)
			node.getRight().accept(this);
		System.out.print(" " + node.getLabel() + " ");
	}

	public void visitNumericNode(NumericNode node) {
		System.out.print(" " + node.getLabel() + " ");
	}
}