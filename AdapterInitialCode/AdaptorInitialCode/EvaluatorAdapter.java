package AdaptorInitialCode;

import VisitorSubpackage.EvaluatorVisitor;
import VisitorSubpackage.Parser;
import VisitorSubpackage.TreeNode;

class EvaluatorAdapter implements Evaluator{

  public int evaluate(String expression){
	  Parser parser = new Parser(expression); // spaces are vital!!
	  TreeNode node = parser.parse();
	  EvaluatorVisitor ev = new EvaluatorVisitor();
	  node.accept(ev);
	  return ev.getValue();
  }
}