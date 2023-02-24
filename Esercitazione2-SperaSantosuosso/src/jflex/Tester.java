package jflex;

import java_cup.runtime.Symbol;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;

public class Tester {
	public static HashMap<Integer,String> tokenMap = new HashMap();
	static {
		tokenMap.put(-1,"EOF");
		tokenMap.put(0,"IF");
		tokenMap.put(1,"THAN");
		tokenMap.put(2,"ELSE");
		tokenMap.put(3,"WHILE");
		tokenMap.put(4,"DO");
		tokenMap.put(5,"FOR");
		tokenMap.put(6,"INT");
		tokenMap.put(7,"FLOAT");
		tokenMap.put(8,"DOUBLE");
		tokenMap.put(9,"BOOLEAN");
		tokenMap.put(10,"VOID");
		tokenMap.put(11,"SWITCH");
		tokenMap.put(12,"CASE");
		tokenMap.put(13,"DEFAULT");
		tokenMap.put(14,"RETURN");
		tokenMap.put(15,"IMPORT");
		tokenMap.put(16,"LT");
		tokenMap.put(17,"EQ");
		tokenMap.put(18,"GT");
		tokenMap.put(19,"LE");
		tokenMap.put(20,"NE");
		tokenMap.put(21,"ASSIGN");
		tokenMap.put(22,"GE");
		tokenMap.put(23,"SUBTRACT");
		tokenMap.put(24,"ADD");
		tokenMap.put(25,"LPAR");
		tokenMap.put(26,"RPAR");
		tokenMap.put(27,"LBRACK");
		tokenMap.put(28,"RBRACK");
		tokenMap.put(29,"COMMA");
		tokenMap.put(30,"SEMI");
		tokenMap.put(31,"ID");
		tokenMap.put(32,"UNSIGNED_NUMBER");
		tokenMap.put(34,"ERROR");
		tokenMap.put(35,"STRING");
	}
	public static void main(String[] args) {


		FileReader reader;
		Symbol token;
		String tokens = "";
		try {
			reader = new FileReader(args[0]);
			Lexer lexer = new Lexer(reader);
			while((token = lexer.next_token()).sym != -1){
				if(tokenMap.get(token.sym).equals("ID")){
					tokens += "<"+tokenMap.get(token.sym)+","+Lexer.stringTable.get(token.value)+">\n";
				}else if(tokenMap.get(token.sym).equals("UNSIGNED_NUMBER")) {
					tokens += "<" + tokenMap.get(token.sym) + "," + token.value + ">\n";
				}else if(tokenMap.get(token.sym).equals("STRING")){
					tokens += "<" + tokenMap.get(token.sym) + "," + token.value + ">\n";
				}else{
					tokens += tokenMap.get(token.sym) + "\n";
				}
			}
			System.out.println(Lexer.stringTable.toString());
			System.out.println(tokens);

		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
}
