package esercitazione4;

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
		tokenMap.put(1,"ERROR");
		tokenMap.put(2,"MAIN");
		tokenMap.put(3,"SEMI");
		tokenMap.put(4,"COMMA");
		tokenMap.put(5,"PIPE");
		tokenMap.put(6,"VAR");
		tokenMap.put(7,"INT");
		tokenMap.put(8,"FLOAT");
		tokenMap.put(9,"STRING");
		tokenMap.put(10,"BOOL");
		tokenMap.put(11,"CHAR");
		tokenMap.put(12,"VOID");
		tokenMap.put(13,"DEF");
		tokenMap.put(14,"OUT");
		tokenMap.put(15,"FOR");
		tokenMap.put(16,"IF");
		tokenMap.put(17,"ELSE");
		tokenMap.put(18,"WHILE");
		tokenMap.put(19,"TO");
		tokenMap.put(20,"LOOP");
		tokenMap.put(21,"READ");
		tokenMap.put(22,"WRITE");
		tokenMap.put(23,"WRITELN");
		tokenMap.put(24,"LPAR");
		tokenMap.put(25,"RPAR");
		tokenMap.put(26,"LBRAC");
		tokenMap.put(27,"RBRAC");
		tokenMap.put(28,"COLON");
		tokenMap.put(29,"ASSIGN");
		tokenMap.put(30,"RETURN");
		tokenMap.put(31,"TRUE");
		tokenMap.put(32,"FALSE");
		tokenMap.put(33,"PLUS");
		tokenMap.put(34,"MINUS");
		tokenMap.put(35,"TIMES");

		tokenMap.put(36,"DIV");
		tokenMap.put(37,"POW");
		tokenMap.put(38,"STR_CONCAT");
		tokenMap.put(39,"EQ");
		tokenMap.put(40,"NE");
		tokenMap.put(41,"LT");
		tokenMap.put(42,"LE");
		tokenMap.put(43,"GT");
		tokenMap.put(44,"GE");
		tokenMap.put(45,"AND");
		tokenMap.put(46,"OR");
		tokenMap.put(47,"NOT");
		tokenMap.put(48,"INTEGER_CONST");
		tokenMap.put(49,"ID");

		tokenMap.put(51,"REAL_CONST");
		tokenMap.put(52,"STRING_CONST");
		tokenMap.put(53,"CHAR_CONST");
		tokenMap.put(54,"REAL");
		tokenMap.put(55,"REF_ID");
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
				}else if(tokenMap.get(token.sym).equals("REAL_CONST")) {
					tokens += "<" + tokenMap.get(token.sym) + "," + token.value + ">\n";
				}else if(tokenMap.get(token.sym).equals("INTEGER_CONST")) {
					tokens += "<" + tokenMap.get(token.sym) + "," + token.value + ">\n";
				}else if(tokenMap.get(token.sym).equals("STRING_CONST")){
					tokens += "<" + tokenMap.get(token.sym) + "," + token.value + ">\n";
				}
				else if(tokenMap.get(token.sym).equals("CHAR_CONST")){
					tokens += "<" + tokenMap.get(token.sym) + "," + token.value + ">\n";
				}
				else{
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
