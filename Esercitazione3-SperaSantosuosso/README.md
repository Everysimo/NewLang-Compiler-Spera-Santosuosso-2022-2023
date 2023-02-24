# Lexer

![alt text](https://www.aaronraff.dev/static/566ed7517c4c29af13b2e0a06d782be7/bc69a/how-to-write-a-lexer-in-go-featured.jpg)


## Parole chiavi

| Nome    | Attributo |
|:--------|:---------:|
| if      |    IF     |
| then    |   THEN    | 
| else    |   ELSE    | 
| while   |   WHILE   | 
| do      |    DO     | 
| for     |    FOR    | 
| int     |    INT    | 
| float   |   FLOAT   | 
| double  |  DOUBLE   | 
| boolean |  BOOLEAN  | 
| void    |   VOID    | 
| switch  |  SWITCH   | 
| case    |   CASE    | 
| default |  DEFAULT  | 
| return  |  RETURN   | 
| import  |  IMPORT   | 
| end     |    END    | 
| loop    |   LOOP    | 

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
| <--     |  ASSIGN  | 
| \>=     |    GE    |
| -       | SUBTRACT |
| +       |   ADD    |

## SEPARATORI (SEPARETOR)

| Lessema |  Token   |
|:--------|:--------:|
| (       |   LPAR   |
| )       |   RPAR   | 
| {       |  LBRACK  | 
| }       |  RBRACK  | 
| ,       |  COMMA   | 
| ;       |   SEMI   |


## ID

#### Regex ID
ID = [:jletter:] [:jletterdigit:]*

## UNSIGNED NUMBER

digit = [0-9]

digits = 0 | ( [1-9] ([0-9])* )

#### Regex UNSIGNED NUMBER

UnsignedNumber = digits (.digits)? (E(+ | -)?digits)?


## COMMENTI

I COMMENTI sono stati gestiti nel Lexer.

# Parser

## GRAMMATICA

            S -> Program  EOF
            Program -> Program SEMI Stmt
                           |  Stmt
            Stmt -> IF Expr THEN Stmt END IF

                     | IF Expr THEN Stmt ELSE Stmt END IF

                     | ID ASSIGN Expr

                     | WHILE Expr LOOP Stmt END LOOP

            Expr ->  Expr  Relop Expr

            Expr ->   ID
                     |  NUMBER

            Relop -> LE|LT|GE|GT|EQ|NE

## GRAMMATICA SENZA RICORSIONE E CON FATTORIZZAZIONE SINISTRA

            S -> Program  EOF

            Program -> Stmt Program1
            Program1 -> SEMI Stmt Program1
                        | ""

            Stmt -> IF Expr THEN Stmt Stmt1

                     | IF Expr THEN Stmt Stmt1

                     | ID ASSIGN Expr

                     | WHILE Expr LOOP Stmt END LOOP

            Stmt1 -> END IF 
                    | ELSE stmt END IF

            Expr ->  ID Expr1
                    | Number Expr1
            Expr1 ->  Relop Expr Expr1
                    | ""

            Relop -> LE|LT|GE|GT|EQ|NE

## TEST

| File_source   |      Esito       |
|:--------------|:----------------:|
| file_source1  |   Input valido   |
| file_source2  |   Input valido   |
| file_source3  | Input NON valido |
| file_source4  |   Input valido   |
| file_source5  | Input NON valido |
| file_source6  | Input NON valido |
| file_source7  | Input NON valido |
| file_source8  |   Input valido   |
| file_source9  | Input NON valido |
| file_source10 |   Input valido   |
| file_source11 |   Input valido   |
| file_source12 | Input NON valido |

