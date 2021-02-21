package visitor.nonpattern;

public class ParserTest{
  public static void main(String[] args)  {
    Parser parser = new Parser("1 - 2 - 3 * 4 / 5"); // spaces are vital!!
    TreeNode node = parser.parse();
    node.inFixPrint();
    System.out.println();
    node.postFixPrint();
    System.out.println();
    System.out.println(node.compute());
  }
}