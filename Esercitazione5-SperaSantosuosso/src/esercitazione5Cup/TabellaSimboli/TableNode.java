package esercitazione5Cup.TabellaSimboli;

import esercitazione5Cup.GrammarClasses.ParDeclOp;
import esercitazione5Cup.TabellaSimboli.Symbols.SymbolFunc;
import esercitazione5Cup.TabellaSimboli.Symbols.SymbolVar;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeIterator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class TableNode {
    public HashMap<Integer, SymbolVar> varScopesTable = new HashMap<>();
    public HashMap<Integer, SymbolFunc> funcScopesTable = new HashMap<>();

    public ArrayList<Integer> variabiliDichiarate = new ArrayList<>();

    TableNode padre;
    ArrayList<TableNode> figli = new ArrayList<>();

    int scopeLevel = 0;

    int numFiglio = 0;

    int figliVisitati = 0;


    public TableNode(int scopeLevel,int numFiglio) {
        this.scopeLevel = scopeLevel;
    }
    public TableNode(TableNode padre,int scopeLevel,int numFiglio) {
        this.padre = padre;
        this.scopeLevel = scopeLevel;
        this.numFiglio = numFiglio;
    }

    public void addDeclaredVar(Integer s){
        variabiliDichiarate.add(s);
    }

    public boolean isVariabileDichiarataNelTypeEnvironment(Integer s){
        TableNode node = this;
        if(variabiliDichiarate.contains(s)){
            return true;
        }
        while(node.getPadre()!=null){
            node = node.getPadre();
            if(variabiliDichiarate.contains(s)){
                return true;
            }
        }
        return false;
    }

    public boolean isVariabileDichiarataNelloScope(Integer s){
        TableNode node = this;
        if(variabiliDichiarate.contains(s)){
            return true;
        }
        return false;
    }

    public void addFiglio(TableNode figlio){
        figli.add(figlio);

    }

    public TableNode getFiglio(int index){
        return figli.get(index);
    }

    public TableNode getPadre(){
        return padre;
    }

    public SymbolVar cercaVarFinoAllaRadice(int idLessema) {
        TableNode node = this;
        if (node.varScopesTable.containsKey(idLessema)) {
            return node.varScopesTable.get(idLessema);
        }
        while (node.getPadre() != null) {
            node = node.getPadre();
            if (node.varScopesTable.containsKey(idLessema)) {
                return node.varScopesTable.get(idLessema);
            }
        }
        return null;
    }

    public SymbolFunc cercaFuncFinoAllaRadice(int idLessema){
        TableNode node = this;
        if(funcScopesTable.containsKey(idLessema)){
            return funcScopesTable.get(idLessema);
        }
        while(node.getPadre()!=null){
            node = node.getPadre();
            if(node.funcScopesTable.containsKey(idLessema)){
                return node.funcScopesTable.get(idLessema);
            }
        }
        return null;
    }

    public SymbolVar cercaVarNelloScopeCorrente(int idLessema){
        TableNode node = this;
        if(node.varScopesTable.containsKey(idLessema)){
            return node.varScopesTable.get(idLessema);
        }
        return null;
    }

    public SymbolFunc cercaFuncNelloScopeCorrente(int idLessema){
        TableNode node = this;
        if(funcScopesTable.containsKey(idLessema)){
            return funcScopesTable.get(idLessema);
        }

        return null;
    }

    public SymbolVar addVar(Integer idVar, String tipo, int linea) throws SymbolTable.VarGiaDefinita {
        SymbolVar var = new SymbolVar(idVar,tipo);
        if(cercaVarNelloScopeCorrente(var.getLessema())!=null){
            SymbolVar varGiaEsistente = cercaVarFinoAllaRadice(var.getLessema());
            throw new SymbolTable.VarGiaDefinita(linea,idVar,varGiaEsistente.getLine());
        }
        else {
            var.setLine(linea);
            varScopesTable.put(idVar, var);
            return var;
        }

    }

    public SymbolVar addVar(Integer idVar, String tipo, int linea, boolean isOut) throws SymbolTable.VarGiaDefinita {
        SymbolVar var = new SymbolVar(idVar,tipo);
        var.setOut(isOut);
        if(cercaVarNelloScopeCorrente(var.getLessema())!=null){
            SymbolVar varGiaEsistente = varScopesTable.get(var.getLessema());
            throw new SymbolTable.VarGiaDefinita(linea,idVar,varGiaEsistente.getLine());
        }
        else {
            var.setLine(linea);
            varScopesTable.put(idVar, var);
            return var;
        }

    }

    public SymbolFunc addFunc(Integer idFunc, ArrayList<ParDeclOp> params, String returnType, int linea) throws SymbolTable.FunzioneGiaDefinita {
        SymbolFunc func = new SymbolFunc(idFunc,params,returnType);
        if(cercaFuncNelloScopeCorrente(func.getLessema())!=null){
            SymbolFunc funcGiaEsistente = funcScopesTable.get(func.getLessema());
            throw new SymbolTable.FunzioneGiaDefinita(linea,idFunc,funcGiaEsistente.getLine());
        }
        else{
            func.setLine(linea);
            funcScopesTable.put(idFunc, func);
            return func;
        }

    }

    public void stampaStack(){
        if(padre != null){
            padre.stampaStack();
        }
        else {

            System.out.println("\n------------------------------------------- NUOVA TABELLA ------------------------------------------- \n");
        }
        System.out.println("\n------------------------------------------- Scope Funzioni -------------------------------------------");
        System.out.println("---------------------------------------------- Scope "+this.scopeLevel+" -------------------------------------------");

        for (Map.Entry<Integer, SymbolFunc> m : funcScopesTable.entrySet()) {
            System.out.println("|          "+m.getValue().toString() +"          |");
        }

        System.out.println("----------------------------------------------Scope Variavbili-------------------------------------------");
        System.out.println("------------------------------------------------ Scope "+this.scopeLevel+" -------------------------------------------");
            for (Map.Entry<Integer, SymbolVar> m : varScopesTable.entrySet()) {
                System.out.println("|          "+m.getValue().toString() +"          |");
            }
    }
}
