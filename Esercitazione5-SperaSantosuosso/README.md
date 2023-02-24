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
            
            Expr ::= Const
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

| Configuration     |      Esito       |
|:------------------|:----------------:|
| ProgrammaNewLang  |   Input valido   |
| ProgrammaNewLang2 |   Input valido   |
| ProgrammaNewLang3 | Input valido |
| TestCupTestProf   |   Input non valido   |

# SYMBOL TABLE
La symbol table è stata realizzata tramite una struttra ad albero, dove ogni nodo rappresena una tabella, il type Environment
corrisponde ad un visita Bottom-Up a partire dal nodo corrente, passando per i vari padri fino alla radice.


# 4 Visitros
## XML
L'albero di parsing in output dell'ultimo codice analizzato in formato XML è generato nella cartella "output"

## SEMANTIC
Verifica se la semantica della grammatica è corretta e riempe la symbol table, che non viene cancellata

## TYPE CHECKING
Utilizzando la tabella dei simboli precedentemente riempita, verifica che tutti i tipi siano ben rispettati, basandosi sulle
regole di inferenza elencate nella traccia,e in più sono state aggiunte le regole mancanti nella cartella docs

## TRADUZIONE
Permette di tradurre il codice NewLang in un codice in linguaggio C eseguibile

# SCELTE IMPLENTATIVE
1) La concatenazione fra stringhe può avvenire solo se il primo parametro è un id, poichè la funzione strcat() in C
si aspetta un identificatore per concatenare la seconda stringa nella prima
2) Le funzioni e le variabili possono essere utilizzate prima di essere dichiarate 
Per la traduzione delle variabli ad esempio nel caseo int x = y, per far si che in C venga dichiata y prima di x, verifichiamo se è già
x non stata già dichiarata la dichiariamo, e ne teniamo traccia in una struttura a parte in modo da non dichiararla
un'altra volta successivamente.
Per la traduzione delle funzioni dopo il main, utilizziamo la dichiarazione di funzioni offerta in C e la mettiamo all'inizio del codice

## ESECUZIONE CODICE C
Una volta realizzato il codice C, questo verrà automaticamente seguito nella console se il pc che esegue il Tester ha il compilatore gcc installato.
Il programma è compatibile sia per il S.O Windows che quello MACos