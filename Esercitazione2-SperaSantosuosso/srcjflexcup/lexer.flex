/* JFlex example: part of Java language lexer specification */
package jflex;
import java_cup.runtime.*;import java.util.HashMap;

/**
* This class is a simple example lexer.
*/
%%

%public
%class Lexer
%cupsym Token
%cup
%unicode
%line
%column
%cupdebug

%{
    public StringBuffer string = new StringBuffer();
        public static HashMap<Integer, String> stringTable = new HashMap<>();
        private static HashMap<String, Integer> symbolTable = new HashMap<>();
        private int idCounter = 0;
        public int errorLine ,errorColumn;
        private Symbol symbol(int type) {
            return new Symbol(type, yyline, yycolumn);
        }
        private Symbol symbol(int type, Object value) {
            return new Symbol(type, yyline, yycolumn, value);
        }
%}

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace = {LineTerminator} | [ \t\f]
/* comments */
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}
TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
// Comment can be the last line of the file, without line terminator.
EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent = ( [^*] | \*+ [^/*] )*
Id = [:jletter:] [:jletterdigit:]*
Digit = [0-9]
Digits = [0] | ([1-9]{Digit}*)
UnsignedNumber = {Digits}(\.{Digits})?(E(\+|\-)?{Digits})?


%state STRING
%state COMMENT
%%
/* keywords */
<YYINITIAL> "if" { return symbol(Token.IF); }
<YYINITIAL> "then" { return symbol(Token.THEN); }
<YYINITIAL> "else" { return symbol(Token.ELSE); }
<YYINITIAL> "while" { return symbol(Token.WHILE); }
<YYINITIAL> "do" { return symbol(Token.DO); }
<YYINITIAL> "for" { return symbol(Token.FOR); }
<YYINITIAL> "int" { return symbol(Token.INT); }
<YYINITIAL> "float" { return symbol(Token.FLOAT); }
<YYINITIAL> "double" { return symbol(Token.DOUBLE); }
<YYINITIAL> "boolean" { return symbol(Token.BOOLEAN); }
<YYINITIAL> "void" { return symbol(Token.VOID); }
<YYINITIAL> "switch" { return symbol(Token.SWITCH); }
<YYINITIAL> "case" { return symbol(Token.CASE); }
<YYINITIAL> "default" { return symbol(Token.DEFAULT); }
<YYINITIAL> "return" { return symbol(Token.RETURN); }
<YYINITIAL> "import" { return symbol(Token.IMPORT); }

// RELOP
<YYINITIAL> "<" { return symbol(Token.LT); }
<YYINITIAL> "=" { return symbol(Token.EQ); }
<YYINITIAL> ">" { return symbol(Token.GT); }
<YYINITIAL> "<=" { return symbol(Token.LE); }
<YYINITIAL> "<>" { return symbol(Token.NE); }
<YYINITIAL> "<--" { return symbol(Token.ASSIGN); }
<YYINITIAL> ">=" { return symbol(Token.GE); }
<YYINITIAL> "-" { return symbol(Token.SUBTRACT); }
<YYINITIAL> "+" { return symbol(Token.ADD); }


//SEPARETOR
<YYINITIAL> "(" { return symbol(Token.LPAR); }
<YYINITIAL> \) { return symbol(Token.RPAR); }
<YYINITIAL> "{" { return symbol(Token.LBRACK); }
<YYINITIAL> "}" { return symbol(Token.RBRACK); }
<YYINITIAL> "," { return symbol(Token.COMMA); }
<YYINITIAL> ";" { return symbol(Token.SEMI); }

<YYINITIAL> {

[\"] {errorLine=yyline+1;errorColumn=yycolumn+1; string.setLength(0); yybegin(STRING);  }
/* operators */
"=" { return symbol(Token.EQ); }
"+" { return symbol(Token.PLUS); }

"/*" {yybegin(COMMENT);errorLine=yyline+1;errorColumn=yycolumn+1; }
/* Comment */
{Comment} { }
/* whitespace */
{WhiteSpace} { /* ignore */ }
/*ID*/
{Id} {
          if(symbolTable.containsKey(yytext())){
                return stringTable.containsKey(symbolTable.get(yytext())) ? symbol(Token.ID, symbolTable.get(yytext())) : null;
              }
              else{
                  idCounter++;
                  stringTable.put(idCounter,yytext());
                  symbolTable.put(yytext(),idCounter);
                  return symbol(Token.ID,Integer.valueOf(String.valueOf(idCounter)));
          }
      }
/*UNSIGNED NUMBER*/
{UnsignedNumber} { return symbol(Token.UNSIGNED_NUMBER, yytext()); }
}

<STRING> {
\" {yybegin(YYINITIAL);}
 //
[^\"$]+ { string.append( yytext() ); return symbol(Token.STRING, yytext());}
 <<EOF>> {throw new Error("Terminatore stringa mancante a riga "+errorLine+" e colonna "+errorColumn);}
      }


<COMMENT> {
      [^*] {}
      <<EOF>> {throw new Error("Terminatore commento mancante a riga "+errorLine+" e colonna "+errorColumn);}
      "*/"   {yybegin(YYINITIAL);}
}

/* error fallback */
//In questo caso viene lanciata un'eccezione
//[^] { throw new Error("Illegal character <"+ yytext()+">"); }


//In questo caso viene stampato l'errore
[^] { return symbol(Token.ERROR, yytext());}