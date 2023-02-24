package esercitazione5Cup.TabellaSimboli;

import esercitazione5Cup.GrammarClasses.ParDeclOp;
import esercitazione5Cup.Lexer;
import esercitazione5Cup.TabellaSimboli.Symbols.SymbolFunc;
import esercitazione5Cup.TabellaSimboli.Symbols.SymbolVar;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class SymbolTable {

    static TableNode nodo;
    static TableNode radice;


    public static void pushNuovoScope(){
        if(nodo==null){
            nodo = new TableNode(null,0,0);
            nodo.figliVisitati=0;
            radice = nodo;
        }
        else{
            int numFiglio = (nodo.figli.size());
            TableNode figlio = new TableNode(nodo,((nodo.scopeLevel)+1),numFiglio);
            figlio.figliVisitati=0;
            nodo.addFiglio(figlio);
            nodo = nodo.getFiglio(numFiglio);
        }

    }

    public static void moveToNuovoScope(){
        if(nodo == null){
            nodo = radice;
        }else {
            int numFiglio = (nodo.figliVisitati);
            if(nodo.figli.size()!=0){
                nodo.figliVisitati += 1;
                nodo = nodo.getFiglio(numFiglio);

            }
        }
    }


    public static void moveToScopeSuperiore(){
        if(nodo.figliVisitati == nodo.figli.size()){
            nodo.figliVisitati = 0;
        }
        if (nodo.getPadre() == null){
            nodo = radice;
            return;
        }
        nodo = nodo.getPadre();
    }

    public static void popScopeOnExit(){
            nodo = nodo.getPadre();
    }

    public static SymbolVar cercaVar(Integer idLessema){
        return nodo.cercaVarFinoAllaRadice(idLessema);
    }

    public static SymbolFunc cercaFunc(Integer idFunc){
        return nodo.cercaFuncFinoAllaRadice(idFunc);
    }

    public static SymbolFunc addFunc(Integer idFunc, ArrayList<ParDeclOp> params, String returnType, int linea) throws FunzioneGiaDefinita {
        return nodo.addFunc(idFunc, params, returnType, linea);
    }

    public static SymbolVar addVar(Integer idVar, String tipo, int linea) throws VarGiaDefinita {
        return nodo.addVar(idVar, tipo, linea);
    }

    public static SymbolVar addVar(Integer idVar, String tipo, int linea, boolean isOut) throws VarGiaDefinita {
        return nodo.addVar(idVar, tipo, linea,isOut);
    }

    public static void addDeclaredVar(Integer idLessema){
        nodo.addDeclaredVar(idLessema);
    }

    public static boolean isVariabileDichiarataNelTypeEnvironment(Integer idLessema){
        return nodo.isVariabileDichiarataNelTypeEnvironment(idLessema);
    }

    public static boolean isVariabileDichiarataNelloScope(Integer idLessema){
        return nodo.isVariabileDichiarataNelloScope(idLessema);
    }

    public static void stampaStack(){
        nodo.stampaStack();
    }

    public static class TipoNonCorrispondente extends Exception{
        public TipoNonCorrispondente(int lineaErrore, String tipo, String tipoAssegnato, Integer lessema){
            super("Errore alla linea: "+lineaErrore+".\n Tipo non corrispondente per il lessema: "+ Lexer.stringTable.get(lessema)+" tipo previsto: "+tipo+" invece il tipo assegnato è: "+tipoAssegnato);
        }

        public TipoNonCorrispondente(int lineaErrore, String tipo, String tipoAssegnato, String lessema){
            super("Errore alla linea: "+lineaErrore+".\n Tipo non corrispondente per il lessema: "+ Lexer.stringTable.get(lessema)+" tipo previsto: "+tipo+" invece il tipo assegnato è: "+tipoAssegnato);
        }
    }
    public static class TipoRitornoFunCallNonCorrispondente extends Exception{
        public TipoRitornoFunCallNonCorrispondente(int lineaErrore, String tipo, String tipoAssegnato, Integer lessema){
            super("Errore alla linea: "+lineaErrore+".\n Tipo di ritorno non corrsipondente per la funzione: "+ Lexer.stringTable.get(lessema)+" invece il tipo assegnato è: "+tipo+" tipo assegnato: "+tipoAssegnato);
        }
        public TipoRitornoFunCallNonCorrispondente(int lineaErrore, String tipo, String tipoAssegnato, String lessema){
            super("Errore alla linea: "+lineaErrore+".\n Tipo di ritorno non corrsipondente per la funzione: "+ Lexer.stringTable.get(lessema)+" invece il tipo assegnato è: "+tipo+" tipo assegnato: "+tipoAssegnato);
        }
    }

    public static class ReturnNonCorrispondente extends Exception{
        public ReturnNonCorrispondente(int lineaErrore, String tipo, String tipoAssegnato){
            super("Errore alla linea: "+lineaErrore+".\n Tipo di ritorno non corrispondente, tipo previsto: "+tipo+" invece il tipo assegnato èo: "+tipoAssegnato);
        }
    }

    public static class StrCatSiAspettaParametriStringa extends Exception{
        public StrCatSiAspettaParametriStringa(int lineaErrore,int numParam, String tipo, String tipoAssegnato){
            super("Errore alla linea: "+lineaErrore+".\n Str Cat per il parametro "+numParam+"si aspetta il tipo "+tipo+" invece il tipo assegnato è: "+tipoAssegnato);
        }
    }

    public static class FunzioneGiaDefinita extends Exception{
        public FunzioneGiaDefinita(int lineaErrore, Integer lessema, int lineaDichiarazioneDuplicata){
            super("Errore alla linea: "+lineaErrore+".\n La funzione: "+Lexer.stringTable.get(lessema)+" è stata già definita alla linea: "+lineaDichiarazioneDuplicata );
        }
    }
    public static class VarGiaDefinita extends Exception{
        public VarGiaDefinita(int lineaErrore, Integer lessema, int lineaDichiarazioneDuplicata){
            super("Errore alla linea: "+lineaErrore+".\n La variabile: "+Lexer.stringTable.get(lessema)+" è stato già definito alla linea: "+lineaDichiarazioneDuplicata );
        }
    }

    public static class OperazioneNonDefinita extends Exception{
        public OperazioneNonDefinita(int lineaErrore, String operazione, String tipo1, String tipo2){
            super("Errore alla linea: "+lineaErrore+".\n L'operazione: \""+getOpName(operazione)+"\" non è compatibile con questi tipi: "+tipo1+" , "+tipo2 );
        }

        public OperazioneNonDefinita(int lineaErrore, String operazione, String tipo){
            super("Errore alla linea: "+lineaErrore+".\n L'operazione: \""+getOpName(operazione)+"\" non è compatibile con questo tipo: "+tipo);
        }

        private static String getOpName(String operazione){
            switch (operazione){
                case "AndOp" : return "and";
                case "DivOp" : return "/";
                case "EqOp" : return "=";
                case "GeOp" : return ">=";
                case "GtOp" : return ">";
                case "LeOp" : return "<=";
                case "LtOp" : return "<";
                case "MinusOp" : return "-";
                case "NeOp" : return "<> or !=";
                case "OrOp" : return "or";
                case "PlusOp" : return "+";
                case "PowOp" : return "^";
                case "StrCatOp" : return "&";
                case "TimesOp" : return "*";
                case "WriteStatOp" : return "--> or -->!";
                case "ReadStatOp" : return "<--";
                case "AssignStatOp" : return "<<";
                default: return operazione;
            }
        }

    }
public static class VarNonDefinita extends Exception {
    public VarNonDefinita(int lineaErrore, String lessema) {
        super("Errore alla linea: " + lineaErrore + ".\n La variabile: \"" + lessema + "\" non è stata definita.");
    }
}

public static class FunzioneNonDefinita extends Exception{
        public FunzioneNonDefinita(int lineaErrore, String lessema){
            super("Errore alla linea: "+lineaErrore+".\n La funzione chiamata : \""+lessema+"\" non è stata definita.");
        }
    }

    public static class ReturnMancante extends Exception{
        public ReturnMancante(int lineaErrore, String lessema){
            super("Errore alla linea: "+lineaErrore+".\n \"return\" mancante per La funzione : \""+lessema+"\"");
        }
    }

    public static class NumeroParametriFunzioneNonCorrsiponenti extends Exception{
        public NumeroParametriFunzioneNonCorrsiponenti(int lineaErrore, String lessema){
            super("Errore alla linea: "+lineaErrore+".\n Il numero di parametri della funzione " +
                    "chiamata : \""+lessema+"\" non coincide con quelli attesi.");
        }
    }

    public static class ParametriFunzioneNonCorrsiponenti extends Exception{
        public ParametriFunzioneNonCorrsiponenti(int lineaErrore,int numeroParametro, String lessema){
            super("Errore alla linea: "+lineaErrore+".\n Il tipo del parametro "+ numeroParametro +" della funzione " +
                    "chiamata : \""+lessema+"\" non coincide con quello atteso.");
        }
    }

    public static class ParametroOutFunzioneSiAspettaVar extends Exception{
        public ParametroOutFunzioneSiAspettaVar(int lineaErrore,int numeroParametro, String lessema){
            super("Errore alla linea: "+lineaErrore+".\n Il parametro "+ numeroParametro +" della funzione " +
                    "chiamata : \""+lessema+"\" non si aspetta una variabile per l'output non una costante.");
        }
    }

    public static class StrCatInCSiAspettaUnaVariabileComePrimoArgomento extends Exception{
        public StrCatInCSiAspettaUnaVariabileComePrimoArgomento(int lineaErrore){
            super("Errore alla linea: "+lineaErrore+" \n Il primo parametro della funzione strcat() \'&\' si aspetta" +
                    " una variabile tipo stringa");
        }
    }


    public static class ErroreGenerico extends Exception{
        public ErroreGenerico(int lineaErrore, String error){
            super("Errore alla linea: "+lineaErrore+".\n"+ error);
        }
    }

    public static class MainNonDeveAvereParametri extends Exception{
        public MainNonDeveAvereParametri(int lineaErrore,int lessema){
            super("Errore alla linea: "+lineaErrore+" la funzione start:"+Lexer.stringTable.get(lessema)+"() non deve avere parametri");
        }
    }
}
