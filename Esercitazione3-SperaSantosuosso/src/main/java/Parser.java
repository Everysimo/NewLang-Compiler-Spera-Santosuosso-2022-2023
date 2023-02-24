
package main.java;

import java.util.Arrays;
import java.util.Stack;

public class Parser {
    static Lexer lexer = new Lexer();
    static int ptr = 0;
    static int fallback = 0;
    static String token = null;
    static Stack<String> parsingStack = new Stack<>();
    public static void main(String args[]) throws Exception {
        if(args.length >0){
            String filePath = args[0];

            lexer.initialize(filePath);
            if(s()){
                System.out.println("Valid test");
            }
            else {
                System.out.println("Syntax Error");
            }
        }
    }

    public static void getNextToken(){
        Token t;
        try {
            t = lexer.next_Token();
        } catch (Exception e) {
            throw new RuntimeException(" Errore nella richiesta del token" + Arrays.toString(e.getStackTrace()));
        }
        ptr=0;
        fallback=0;
        token = t.getName();
    }

    static boolean s(){
        parsingStack.push("EOF");
        parsingStack.push("prog");

        if(!program()){
            return false;
        }
        return token.equals("EOF");
    }

    static boolean program(){
        //System.out.println(parsingStack);
        parsingStack.pop();
        parsingStack.push("prog1");
        parsingStack.push("stmt");

        if(!stmt()) {
            return false;
        }
        return program1();
    }

    static boolean program1(){
        //System.out.println(parsingStack);
        parsingStack.pop();
        if(token.equals("SEMI")){
            parsingStack.push("prog1");
            parsingStack.push("stmt");
            parsingStack.push("SEMI");
            //System.out.println(parsingStack);
            parsingStack.pop();
                //Match SEMI e richiesta on demand del token successivo
                if(!stmt()) {
                    return false;
                }
            return program1();
        }
        //getNextToken();
        return true;
    }
    static boolean stmt(){
        //System.out.println(parsingStack);
        parsingStack.pop();
        getNextToken();
        if(token.equals("IF")) {
            parsingStack.push("stmt1");
            parsingStack.push("stmt");
            parsingStack.push("THEN");
            parsingStack.push("expr");
            parsingStack.push("IF");
            //System.out.println(parsingStack);
            parsingStack.pop();
            if (!expr()) {
                return false;
            }
            if (!token.equals("THEN")) {
                return false;
            }
            //System.out.println(parsingStack);
            parsingStack.pop();
            if(!stmt()){
                return false;
            }
            return stmt1();
        }
        else if(token.equals("ID")) {
            parsingStack.push("expr");
            parsingStack.push("ASSIGN");
            parsingStack.push("ID");
            //System.out.println(parsingStack);
            parsingStack.pop();
            getNextToken();
            if (!token.equals("ASSIGN")) {
                return false;
            }
            //System.out.println(parsingStack);
            parsingStack.pop();
            return expr();
        }
        else if(token.equals("WHILE")) {
            parsingStack.push("LOOP");
            parsingStack.push("END");
            parsingStack.push("stmt");
            parsingStack.push("LOOP");
            parsingStack.push("expr");
            parsingStack.push("WHILE");
            //System.out.println(parsingStack);
            parsingStack.pop();
            if(!expr()){
                return false;
            }
            if(!token.equals("LOOP")){
                return false;
            }
            //System.out.println(parsingStack);
            parsingStack.pop();
            if(!stmt()){
                return false;
            }
            if(!token.equals("END")){
                return false;
            }
            //System.out.println(parsingStack);
            parsingStack.pop();
            getNextToken();
            if(!token.equals("LOOP")){
                return false;
            }
            //System.out.println(parsingStack);
            parsingStack.pop();
            getNextToken();
            return true;
        }
        return false;
    }

    static boolean stmt1(){
        //System.out.println(parsingStack);
        parsingStack.pop();
        if(token.equals("END")){
            parsingStack.push("IF");
            parsingStack.push("END");
            //System.out.println(parsingStack);
            parsingStack.pop();
            getNextToken();
            if(!token.equals("IF")){
                return false;
            }
            //System.out.println(parsingStack);
            parsingStack.pop();
            getNextToken();
            return true;
        }
        else if(token.equals("ELSE")){
            parsingStack.push("IF");
            parsingStack.push("END");
            parsingStack.push("stmt");
            parsingStack.push("ELSE");
            //System.out.println(parsingStack);
            parsingStack.pop();
            if(!stmt()){
                return false;
            }
            if(!token.equals("END")){
                return false;
            }
            //System.out.println(parsingStack);
            parsingStack.pop();
            getNextToken();
            if(!token.equals("IF")){
                return false;
            }
            //System.out.println(parsingStack);
            parsingStack.pop();
            getNextToken();
            return true;
        }
        return false;
    }

    static boolean expr(){
        //System.out.println(parsingStack);
        parsingStack.pop();
        getNextToken();
        if (token.equals("ID")) {
            parsingStack.push("expr1");
            parsingStack.push("ID");
            //System.out.println(parsingStack);
            parsingStack.pop();
            getNextToken();
            return expr1();
        }
        else if (token.equals("NUMBER")) {
            parsingStack.push("expr1");
            parsingStack.push("NUMBER");
            //System.out.println(parsingStack);
            parsingStack.pop();
            getNextToken();
            return expr1();
        }
        return false;
    }

    static boolean expr1(){
        //System.out.println(parsingStack);
        parsingStack.pop();
        parsingStack.push("expr1");
        parsingStack.push("expr");
        parsingStack.push("relop");
        if(relop()){
            //System.out.println(parsingStack);
            parsingStack.pop();
            if (!expr()){
                return false;
            }
            return expr1();
        }
        else {
            parsingStack.pop();
            parsingStack.pop();
            parsingStack.pop();
            parsingStack.push("£");
            //System.out.println(parsingStack);
            parsingStack.pop();
            return true;
        }
    }

    static boolean relop() {
        switch (token) {
            case "LT" -> {
                //System.out.println(parsingStack);
                parsingStack.pop();
                parsingStack.push("LT");
                return true;
            }
            case "LE" -> {
                //System.out.println(parsingStack);
                parsingStack.pop();
                parsingStack.push("LE");
                return true;
            }
            case "GT" -> {
                //System.out.println(parsingStack);
                parsingStack.pop();
                parsingStack.push("GT");
                return true;
            }
            case "GE" -> {
                //System.out.println(parsingStack);
                parsingStack.pop();
                parsingStack.push("GE");
                return true;
            }
            case "EQ" -> {
                //System.out.println(parsingStack);
                parsingStack.pop();
                parsingStack.push("EQ");
                return true;
            }
            case "NE" -> {
                //System.out.println(parsingStack);
                parsingStack.pop();
                parsingStack.push("NE");
                return true;
            }
        }
        return false;
    }
}
