// Circuit1.flex
//         ../jflex-1.8.2/bin/jflex -d src srcjflexcup/circuit.flex
// CS2A Language Processing
//
// Description of lexer for circuit description language.
//
// Ian Stark
package esercitazione5Cup;
import java_cup.runtime.Symbol; //This is how we pass tokens to the parser
import java.util.HashMap;
%%

%public
%class Lexer
%cupsym Token
%cup
%unicode
%line
%column

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
Comment = {TraditionalComment} | {EndOfLineComment}
TraditionalComment = "|*" [^*] ~"|"
// Comment can be the last line of the file, without line terminator.
EndOfLineComment = "||" {InputCharacter}* {LineTerminator}?
Id = [:jletter:] [:jletterdigit:]*
Digit = [0-9]
UnsignedIntegerConst = [0] | ([1-9]{Digit}*)
IntegerConst = [0] | ([1-9]{Digit}*)
RealConst = {IntegerConst}(\.{UnsignedIntegerConst})?
Apice = \'
NotApice = [^\']
Char =  {Apice}{NotApice}{Apice}
EmptyChar = {Apice}{Apice}


%state STRING
%state COMMENT
%%
/* keywords */
<YYINITIAL> "start:" { return symbol(Token.MAIN); }
<YYINITIAL> ";" { return symbol(Token.SEMI); }
<YYINITIAL> "," { return symbol(Token.COMMA); }
<YYINITIAL> "|" { return symbol(Token.PIPE); }
<YYINITIAL> "var" { return symbol(Token.VAR); }
<YYINITIAL> "integer" { return symbol(Token.INTEGER); }
<YYINITIAL> "string" { return symbol(Token.STRING); }
<YYINITIAL> "boolean" { return symbol(Token.BOOL); }
<YYINITIAL> "char" { return symbol(Token.CHAR); }
<YYINITIAL> "void" { return symbol(Token.VOID); }
<YYINITIAL> "float" { return symbol(Token.FLOAT); }
<YYINITIAL> "def" { return symbol(Token.DEF); }
<YYINITIAL> "out" { return symbol(Token.OUT); }
<YYINITIAL> "for" { return symbol(Token.FOR); }
<YYINITIAL> "if" { return symbol(Token.IF); }
<YYINITIAL> "else" { return symbol(Token.ELSE); }
<YYINITIAL> "while" { return symbol(Token.WHILE); }
<YYINITIAL> "to" { return symbol(Token.TO); }
<YYINITIAL> "loop" { return symbol(Token.LOOP); }
<YYINITIAL> "then" { return symbol(Token.THEN); }
<YYINITIAL> "<--" { return symbol(Token.READ); }
<YYINITIAL> "-->" { return symbol(Token.WRITE); }
<YYINITIAL> "-->!" { return symbol(Token.WRITELN); }


<YYINITIAL> "(" { return symbol(Token.LPAR); }
<YYINITIAL> ")" { return symbol(Token.RPAR); }
<YYINITIAL> "{" { return symbol(Token.LBRACK); }
<YYINITIAL> "}" { return symbol(Token.RBRACK); }
<YYINITIAL> ":" { return symbol(Token.COLON); }
<YYINITIAL> "<<" { return symbol(Token.ASSIGN); }
<YYINITIAL> "return" { return symbol(Token.RETURN); }
<YYINITIAL> "true" { return symbol(Token.TRUE); }
<YYINITIAL> "false" { return symbol(Token.FALSE); }
<YYINITIAL> "+" { return symbol(Token.PLUS); }
<YYINITIAL> "-" { return symbol(Token.MINUS); }
<YYINITIAL> "*" { return symbol(Token.TIMES); }
<YYINITIAL> "/" { return symbol(Token.DIV); }
<YYINITIAL> "^" { return symbol(Token.POW); }
<YYINITIAL> "&" { return symbol(Token.STR_CONCAT); }
<YYINITIAL> "=" { return symbol(Token.EQ); }
<YYINITIAL> "<>" { return symbol(Token.NE); }
<YYINITIAL> "!=" { return symbol(Token.NE); }
<YYINITIAL> "<" { return symbol(Token.LT); }
<YYINITIAL> "<=" { return symbol(Token.LE); }
<YYINITIAL> ">" { return symbol(Token.GT); }
<YYINITIAL> ">=" { return symbol(Token.GE); }
<YYINITIAL> "and" { return symbol(Token.AND); }
<YYINITIAL> "or" { return symbol(Token.OR); }
<YYINITIAL> "not" { return symbol(Token.NOT); }
<YYINITIAL> {

[\"] {errorLine=yyline+1;errorColumn=yycolumn+1; string.setLength(0); yybegin(STRING);  }

/* operators */

"|*" {yybegin(COMMENT);errorLine=yyline+1;errorColumn=yycolumn+1; }
/* Comment */
{Comment} {}
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
{IntegerConst} { return symbol(Token.INTEGER_CONST, yytext()); }
{RealConst} { return symbol(Token.REAL_CONST, yytext()); }

/*CHAR*/
{Char} {return symbol(Token.CHAR_CONST, yytext().substring(1,2)); }
{EmptyChar} {return symbol(Token.CHAR_CONST,yytext().substring(1,1)); }
}

<STRING> {
\" {yybegin(YYINITIAL); return symbol(Token.STRING_CONST, string.toString());}
 //
[^\"$]* { string.append( yytext() );}
 <<EOF>> {throw new Error("Terminatore stringa mancante a riga "+errorLine+" e colonna "+errorColumn);}
  }



<COMMENT> {
      [^*] {}
      <<EOF>> {throw new Error("Terminatore commento mancante a riga "+errorLine+" e colonna "+errorColumn);}
      "|"   {yybegin(YYINITIAL);}
}

/* error fallback */
//In questo caso viene lanciata un'eccezione
//[^] { throw new Error("Illegal character <"+ yytext()+">"); }


//In questo caso viene stampato l'errore
[^] { throw new Error("Carattere non riconosciuto \""+yytext()+"\" a riga "+(yyline+1)+" e colonna "+(yycolumn+1)+"");}
