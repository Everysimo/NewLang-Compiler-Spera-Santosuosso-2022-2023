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

letter = [a-zA-Z]

letters = letter+

number = [0-9] 

numbers = number+

#### Regex ID
ID = letter (letters | numbers)*

## UNSIGNED NUMBER

digit = [0-9]

digits = 0 | ( [1-9] ([0-9])* )

#### Regex UNSIGNED NUMBER

UnsignedNumber = digits (.digits)? (E(+ | -)?digits)?

## WHITESPACE

delim = blank | newline | tab


#### Regex WHITESPACE

ws = delim ( delim )*


## COMMENTI

I COMMENTI non sono stati gestiti nel Lexer.

## ALTRE FUNZIONI

#### eof :
Questa funzione si occupa di verificare se si è arrivati all'ultimo carattere del file.

#### closeFile :
Questa funzione si occupa chiudere lo stream di lettura del file.

#### installID :
Questa funzione si occupa di verificare se il lessema è già presente nella StringTable, se non lo è crea un nuovo toke e lo inserisce nella tabella.









