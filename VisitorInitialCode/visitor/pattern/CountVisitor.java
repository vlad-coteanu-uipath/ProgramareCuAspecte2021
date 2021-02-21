package visitor.pattern;

//Visitor to compute the expression value notation

public class CountVisitor extends Visitor {

	private int value = 0;

	public void visitBinaryOperatorNode(BinaryOperatorNode node) {
		value += 1;
	}

	public void visitNumericNode(NumericNode node) {
		value += 1;
	}

	public Object getData() {
		return value;
	}
}