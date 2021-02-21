package visitor.pattern;

class ParserTest{
	public static void main(String[] args)  {
	    Parser parser = new Parser("1 + 2 - 3 * 4 / 5"); // spaces are vital!!
	    TreeNode node = parser.parse();
//	    System.out.print("INFIX: ");
//	    Visitor v = new InFixPrintVisitor();
//	    node.accept(v);
//	    System.out.println();
//	    
//	    System.out.print("POSTFIX: ");
//	    Visitor v2 = new PostFixPrintVisitor();
//	    node.accept(v2);
//	    System.out.println();
//	    
//	    System.out.print("COMPUTE: ");
//	    Visitor v3 = new ComputeVisitor();
//	    node.accept(v3);
//	    System.out.println(v3.getData());
	    
	    System.out.print("COUNT: ");
	    Visitor v4 = new CountVisitor();
	    node.accept(v4);
	    System.out.println(v4.getData());
	    
	  }
}