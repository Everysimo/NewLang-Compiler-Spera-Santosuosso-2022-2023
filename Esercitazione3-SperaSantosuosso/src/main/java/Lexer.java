package main.java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class Lexer {

	BufferedReader bfReader;
	FileReader reader;
	//coppia (id,valore) e KEYWORDS
	public static HashMap<String,String> stringTable;
	//coppia (valore,id)
	private static HashMap<String,String> symbolTable;
	//stato del compilatore
	private int state;
	//movimento del puntatore dall'inizio della parola
	private int offset;
	//counter id analizzati
	private int idCounter = 0;

	public Lexer(){
		// la symbol table in questo caso la chiamiamo stringTable
		stringTable = new HashMap<>();
		symbolTable = new HashMap<>();
		state = 0;
		stringTable.put("if", "IF");
		stringTable.put("then", "THEN");
		stringTable.put("else", "ELSE");
		stringTable.put("while", "WHILE");
		stringTable.put("end", "END");
		stringTable.put("loop", "LOOP");
		stringTable.put("do", "DO");
		stringTable.put("for", "FOR");
		stringTable.put("int", "INT");
		stringTable.put("float", "FLOAT");
		stringTable.put("double", "DOUBLE");
		stringTable.put("boolean", "BOOLEAN");
		stringTable.put("void", "VOID");
		stringTable.put("switch", "SWITCH");
		stringTable.put("case", "CASE");
		stringTable.put("default", "DEFAULT");
		stringTable.put("return", "RETURN");
		stringTable.put("import", "IMPORT");
	}
	
	public Boolean initialize(String filePath){
		try {
			reader = new FileReader(filePath);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		bfReader=new BufferedReader(reader);
	    return true;
	} 
	
	public Token next_Token()throws Exception{
		//Ad ogni chiamata del lexer (nextToken())
		//si resettano tutte le variabili utilizzate
		state = 0;
		String lessema = ""; //� il lessema riconosciuto
		offset = 0;
		char c;

		bfReader.mark(1000);

		while(true) {

			// legge un carattere da input e lancia eccezione quando incontra EOF per restituire null
			//  per indicare che non ci sono pi� token
			if (!eof()) {
				c = (char) bfReader.read();
//				System.out.println(c);
				offset++;
//				System.out.println("Stampo offset : "+offset);
			} else {
				//Se il carattere letto è eof ritorna il token EOF
				return new Token("EOF");
			}

			//relop
			label:
			switch (state) {
				case 0:
					switch (String.valueOf(c)) {
						case "<":
							state = 1;
							lessema = lessema + c;
//							System.out.println(lessema);
							if (eof()) {
								return new Token( "LT");
							}
							break label;
						case "=":
							state = 5;
							lessema = lessema + c;
//							System.out.println(lessema);
							if (eof()) {
								return new Token( "EQ");
							}
							break label;
						case ">":
							state = 6;
							lessema = lessema + c;
//							System.out.println(lessema);
							if (eof()) {
								return new Token( "GT");
							}
							break label;
						case "-":
							return new Token( "SUBTRACT");
						case "+":
							return new Token( "ADD");
						default:
							state = 9;
							break;
					}
					break;
					//case <
				case 1:
					switch (String.valueOf(c)) {
						case "=":
							state = 2;
							lessema = lessema + c;
//						System.out.println(lessema);
							if (eof()) {
								return new Token( "LE");
							}
							break label;
						case ">":
							lessema = lessema + c;
//						System.out.println(lessema);
							return new Token("NE");
						case "-":
							state = 3;
							if (eof()) {
								return new Token( "LT");
							}
							lessema = lessema + c;
//						System.out.println(lessema);
							break;
						default:
							retract();
							return new Token( "LT");
					}
					break;
				//case <=
				case 2:
					retract();
					return new Token( "LE");

				//case <- ASSIGN else LT
				case 3:
					if (String.valueOf(c).equals("-")) {
						return new Token("ASSIGN");
					} else {
						retract();
						retract();
						return new Token( "LT");
					}

					//case < LT
				case 4:
					return new Token("LT");

				//case =
				case 5:
					retract();
					return new Token( "EQ");

				//case >
				case 6:
					if (String.valueOf(c).equals("=")) {
						state = 7;
						lessema = lessema + c;
//						System.out.println(lessema);
						if (eof()) {
							return new Token( "GE");
						}
						break;
					} else {
						state = 8;
						if (eof()) {
							return new Token( "LT");
						}
					}
					break;
				//case >=
				case 7:
					retract();
					return new Token( "GE");
				//case >
				case 8:
					retract();
					return new Token( "GT");
			}//end switch

			//id
			switch(state){
				case 9:
					if(Character.isLetter(c)){
						state = 10;
						lessema = lessema + c;
						// Nel caso in cui il file � terminato ma ho letto qualcosa di valido
						// devo lanciare il token (altrimenti perderei l'ultimo token, troncato per l'EOF)
//						System.out.println(lessema);
						if(eof())
						{
							return installID(lessema);
						}
						break;
					}
					else {
						state = 12;
					}

					break;
				//case letter
				case 10:
					if(Character.isLetterOrDigit(c)){
						lessema += c;
//						System.out.println(lessema);
						if(eof())
							return installID(lessema);
						state = 10;
					}else{
						state = 11;
						retract();
//						System.out.println(c);
						return installID(lessema);
					}
					break;
			}//end switch
			//unsigned numbers
			switch(state){
				case 11:
					retract();
					break;
				case 12:
					if(Character.isDigit(c)){
						state = 13;
						lessema += c;
//						System.out.println(lessema);
						if(eof()){
							return new Token("NUMBER", lessema);
						}
					}
					else {
						state = 22;
					}
					break;
				//case digit
				case 13:
					if(Character.isDigit(c)){
						lessema += c;
//						System.out.println(lessema);
						if(eof()){
							return new Token("NUMBER", lessema);
						}
					}
					else if(String.valueOf(c).equals(".")){
						if(eof()){
							retract();
							return new Token("NUMBER", lessema);
						}
						state = 14;
						lessema += c;
//						System.out.println(lessema);
					}
					else if(String.valueOf(c).equals("E")){
						if(eof()){
							retract();
							return new Token("NUMBER", lessema);
						}
						state = 16;
						lessema += c;
//						System.out.println(lessema);
					}
					else {
						retract();
						return new Token("NUMBER", lessema);
					}
					break;
				//case digits.
				case 14:
					if(Character.isDigit(c)){
						state = 15;
						lessema += c;
//						System.out.println(lessema);
						if(eof()){
							return new Token("NUMBER", lessema);
						}
					}
					else {
						retract();
						retract();
						return new Token("NUMBER", lessema.substring(0,lessema.length()-1));
					}
					break;
				//case digits.digits
				case 15:
					if(Character.isDigit(c)){
						state = 15;
						lessema += c;
//						System.out.println(lessema);
						if(eof()){
							return new Token("NUMBER", lessema);
						}
					}
					else if(String.valueOf(c).equals("E")){
						state = 16;
						lessema += c;
//						System.out.println(lessema);
						if(eof()){
							return new Token("NUMBER", lessema);
						}
					}
					else {
						retract();
						return new Token("NUMBER", lessema);
					}
					break;
				//case digitsE
				case 16:
					if(Character.isDigit(c)){
						state = 18;
						lessema += c;
//						System.out.println(lessema);
						if(eof()){
							return new Token("NUMBER", lessema);
						}
					}
					else if(String.valueOf(c).equals("+")||String.valueOf(c).equals("-")){
						state = 17;
						lessema += c;
//						System.out.println(lessema);
						if(eof()){
							return new Token("NUMBER", lessema);
						}
					}
					else {
						retract();
						retract();
						return new Token("NUMBER", lessema.substring(0,lessema.length()-1));
					}
					break;
				//case digit.digitsE(+|-) | digitE(+|-)
				case 17:
					if(Character.isDigit(c)){
						state = 18;
						lessema += c;
//						System.out.println(lessema);
						if(eof()){
							return new Token("NUMBER", lessema);
						}
					}
					else {
						retract();
						retract();
						retract();
						return new Token("NUMBER", lessema.substring(0,lessema.length()-2));
					}
					break;
				//case digit.digitsE(+|-)digits | digitE(+|-)digits
				case 18:
					if(Character.isDigit(c)){
						lessema += c;
//						System.out.println(lessema);
						if(eof()){
							retract();
							return new Token("NUMBER", lessema);
						}
					}
					else {
						retract();
						return new Token("NUMBER", lessema);
					}
					break;
			}
			//Separetor
			switch (state){
				case 22:
					switch (c) {
						case '(' -> {
							return new Token( "LPAR");
						}
						case ')' -> {
							return new Token( "RPAR");
						}
						case '{' -> {
							return new Token( "LBRACK");
						}
						case '}' -> {
							return new Token( "RBRACK");
						}
						case ',' -> {
							return new Token( "COMMA");
						}
						case ';' -> {
							return new Token("SEMI");
						}
						default -> state = 23;
					}
			}
			//Witespaces
			switch (state){
				case 23:
					if(Character.isWhitespace(c)||c==' ') {
						state = 0;
						lessema = "";
					}
					else{
						state = 0;
//						System.out.println("Carattere non riconosciuto "+c);
						return new Token("ERROR", String.valueOf(c));
					}
					break;
			}
		}
	}//end method


	private void retract() throws IOException {
		// fa il retract nel file di un carattere

		if(offset>0){
			offset--;
			bfReader.reset();
			bfReader.skip(offset);
		}

	}

	public boolean eof() throws IOException {
		int i = bfReader.read();
		bfReader.reset();
		if(offset>0){
			bfReader.skip(offset);
		}
		return i < 0;
	}

	private Token installID(String lessema){
		Token token;

		//utilizzo come chiave della hashmap il lessema
		if(stringTable.containsKey(lessema)){
			return new Token(stringTable.get(lessema));
		}
		else if(symbolTable.containsKey(lessema)){
			if(stringTable.containsKey(symbolTable.get(lessema))){
				return new Token("ID",symbolTable.get(lessema));
			}
		}
		else{
			idCounter+=1;
			token =  new Token("ID", String.valueOf(idCounter));
			stringTable.put(String.valueOf(idCounter), lessema);
			symbolTable.put(lessema,String.valueOf(idCounter));
			return token;
		}
		return null;
	}

	public void closeFile() throws IOException {
		bfReader.close();
		reader.close();
		System.out.println("File chiuso correttamente");
	}
}
