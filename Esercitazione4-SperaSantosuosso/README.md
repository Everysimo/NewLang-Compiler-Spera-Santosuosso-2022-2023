# Lexer

![alt text](https://www.aaronraff.dev/static/566ed7517c4c29af13b2e0a06d782be7/bc69a/how-to-write-a-lexer-in-go-featured.jpg)


## Parole chiavi

| Nome    | Attributo |
|:--------|:---------:|
| start:      |    MAIN     |
| if    |   IF   | 
| then    |   THEN    | 
| else    |   ELSE    | 
| while   |   WHILE   | 
| to   |   TO   |
| do      |    DO     | 
| for     |    FOR    | 
| integer     |    INTEGER    | 
| float   |   FLOAT   | 
| var   |   VAR   | 
| double  |  DOUBLE   | 
| boolean |  BOOL  | 
| true |  TRUE  | 
| false |  FALSE  | 
| char |  CHAR  | 
| out |  OUT  | 
| def |  DEF  | 
| void    |   VOID    | 
| switch  |  SWITCH   | 
| case    |   CASE    | 
| default |  DEFAULT  | 
| return  |  RETURN   | 
| import  |  IMPORT   | 
| end     |    END    | 
| loop    |   LOOP    | 
| <--    |   READ    | 
| -->    |   WRITE    | 
| -->!    |   WRITELN    | 

## Definizioni strutture dati

### stringTable
Struttura globale (HashMap) che rappresenta la coppia id (Chiave) - lessame (Valore) e le KEYWORDS.

### symbolTable
Struttura privata (HashMap) del LEXER che rappresenta la coppia lessame (Chiave) - id (Valore).

## OPERATORI RELAZIONALI (RELOP)

| Lessema |  Token   |
|:--------|:--------:|
| <       |    LT    |
| =       |    EQ    | 
| \>      |    GT    | 
| <=      |    LE    | 
| <>      |    NE    | 
| !=      |    NE    | 
| <<     |  ASSIGN  | 
| \>=     |    GE    |
| -       | SUBTRACT |
| +       |   ADD    |
| ^       |   POW    |
| /       |   DIV    |
| *       |   TIMES    |
| and       |   AND   |
| or       |   OR   |
| not       |   NOT   |
| &       |   STR_CONCAT |

## SEPARATORI (SEPARETOR)

| Lessema |  Token   |
|:--------|:--------:|
| (       |   LPAR   |
| )       |   RPAR   | 
| {       |  LBRACK  | 
| }       |  RBRACK  | 
| ,       |  COMMA   | 
| ;       |   SEMI   |
| :      |    COLON     |
| &#124;  |   PIPE   |


## ID

#### Regex ID
ID = [:jletter:] [:jletterdigit:]*

## NUMBER

digit = [0-9]

UnsignedIntegerConst = [0] | ([1-9]{Digit}*)

IntegerConst = [0] | [\+\-]?([1-9]{Digit}*)

RealConst = {IntegerConst}(\.{UnsignedIntegerConst})?

## COMMENTS

Comment = {TraditionalComment} | {EndOfLineComment}

TraditionalComment = "|*" [^*] ~"|"

EndOfLineComment = "||" {InputCharacter}* {LineTerminator}?

## CHAR

Apice = \'

NotApice = [^\']

Char =  {Apice}{NotApice}{Apice}

EmptyChar = {Apice}{Apice}

## SPECIAL CHARATERS

LineTerminator = \r|\n|\r\n

InputCharacter = [^\r\n]

WhiteSpace = {LineTerminator} | [ \t\f]

# Parser

## GRAMMATICA

            Program -> DeclList MainFunDecl DeclList
            DeclList -> VarDecl DeclList | FunDecl DeclList | /* empty */
            MainFunDecl -> MAIN FunDecl
            
            VarDecl ::= Type IdInitList SEMI
            | VAR IdInitObblList SEMI
            
            Type ::= INTEGER | BOOL | REAL | STRING | CHAR
            
            IdInitList ::= ID
            | IdInitList COMMA ID
            | ID ASSIGN Expr
            | IdInitList COMMA ID ASSIGN Expr
            
            IdInitObblList ::= ID ASSIGN Const
            | IdInitObblList COMMA ID ASSIGN Const
            
            Const ::= INTEGER_CONST | REAL_CONST | TRUE | FALSE | STRING_CONST | CHAR_CONST
            
            FunDecl -> DEF ID LPAR ParamDeclList RPAR COLON TypeOrVoid Body
            
            Body -> LBRACK VarDeclList StatList RBRACK
            
            ParamDeclList ::= /*empty */
            | NonEmptyParamDeclList
            
            NonEmptyParamDeclList ::= ParDecl
            | NonEmptyParamDeclList PIPE ParDecl
            
            ParDecl ::= Type IdList
            | OUT Type IdList
            
            TypeOrVoid -> Type | VOID
            
            VarDeclList -> /* empty */
            | VardDecl VarDeclList
            
            StatList ::= Stat
            | Stat StatList
            
            
            Stat ::= IfStat
            | ForStat
            | ReadStat SEMI
            | WriteStat SEMI
            | AssignStat SEMI
            | WhileStat
            | FunCall SEMI
            | RETURN Expr SEMI
            | RETURN SEMI
            | /* empty */
            
            IfStat ::= IF Expr THEN Body Else
            
            Else ::= /* empty */
            | ELSE Body
            
            WhileStat ::= WHILE Expr LOOP Body
            
            ForStat ::= FOR ID ASSIGN INTEGER_CONST TO INTEGER_CONST LOOP Body
            
            ReadStat ::= IdList READ STRING_CONST
            |  IdList READ
            
            IdList ::= ID
            | IdList COMMA ID
            
            WriteStat ::= LPAR ExprList RPAR WRITE         /* nella versione corretta sono */
            | LPAR ExprList RPAR WRITELN        /* stati aggiunti LPAR ed RPAR */
            
            
            AssignStat ::=  IdList ASSIGN ExprList
            
            FunCall ::= ID LPAR ExprList RPAR   
            | ID LPAR RPAR
            
            ExprList ::= Expr
            | Expr COMMA ExprList
            
            Expr ::= TRUE                            
            | FALSE                           
            | INTEGER_CONST                    
            | REAL_CONST
            | STRING_CONST
            | CHAR_CONST
            | ID
            | FunCall
            | Expr  PLUS Expr
            | Expr  MINUS Expr
            | Expr  TIMES Expr
            | Expr  DIV Expr
            | Expr  AND Expr
            | Expr POW Expr
            | Expr STR_CONCAT Expr
            | Expr  OR Expr
            | Expr  GT Expr
            | Expr  GE Expr
            | Expr  LT Expr
            | Expr  LE Expr
            | Expr  EQ Expr
            | Expr  NE Expr
            | MINUS Expr
            | NOT Expr
            | LPAR Expr RPAR


## TEST

File di testing nella cartella TestFiles

| Configuration   |      Esito       |
|:--------------|:----------------:|
| TestCup  |   Input valido   |
| TestCup2  |   Input valido   |
| TestCup3  | Input valido |
| TestCupTestProf  |   Input non valido   |

## XML

L'albero di parsing in output dell'ultimo codice analizzato in formato XML è generato nella cartella "output"

