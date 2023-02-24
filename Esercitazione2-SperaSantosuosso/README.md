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



