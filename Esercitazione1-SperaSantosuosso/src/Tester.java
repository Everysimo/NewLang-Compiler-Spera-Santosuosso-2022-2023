public class Tester {

	public static void main(String[] args) {

		Lexer lexer = new Lexer();
		String filePath = args[0];
		String tokens = "";
			if (lexer.initialize(filePath)) {
				Token token;
				try {
					while ((token = lexer.nextToken()) != null) {
						tokens += token+"\n";
					}
					lexer.closeFile();
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("\n==========Results==========\n");
				System.out.println(tokens);
				System.out.println("\n==========String Table==========\n");
				System.out.println(Lexer.stringTable.toString());
			} else
				System.out.println("File not found!!");
	}

}
