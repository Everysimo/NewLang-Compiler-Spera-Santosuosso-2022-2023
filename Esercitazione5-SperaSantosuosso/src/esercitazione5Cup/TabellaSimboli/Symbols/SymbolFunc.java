package esercitazione5Cup.TabellaSimboli.Symbols;

import esercitazione5Cup.GrammarClasses.Leaf.Identifier;
import esercitazione5Cup.GrammarClasses.Leaf.IdentifierOrInit;
import esercitazione5Cup.GrammarClasses.ParDeclOp;
import esercitazione5Cup.Lexer;

import java.util.ArrayList;

public class SymbolFunc extends Symbol {

    //lessema(Integer a,b| out String c) : Integer{}
    private ArrayList<ParDeclOp> params;
    private String returnType;

    public SymbolFunc(Integer nomeFunc, ArrayList<ParDeclOp> params, String returnType){
        super(nomeFunc);
        this.params = params;
        this.returnType = returnType;
    }

    public String generateFuncInC(){
        String nomeFunc = Lexer.stringTable.get(super.getLessema());
        StringBuilder outputInC;

        outputInC = new StringBuilder(this.returnType+" "+nomeFunc + "(");
        if(this.params.size() > 0){
            for (ParDeclOp p : this.params) {
                for (IdentifierOrInit i : p.getIdList()) {
                    outputInC.append(p.getType() + " ");
                    if(!p.getOut().isEmpty()){
                        outputInC.append("*");
                    }
                    outputInC.append(i.getAttrib()).append(",");
                }
            }
            outputInC.substring(0,outputInC.length()-1);
        }
        outputInC.append(")");

        return outputInC.toString();
    }

    public ArrayList<ParDeclOp> getParams() {
        return params;
    }

    public void setParams(ArrayList<ParDeclOp> params) {
        this.params = params;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    @Override
    public String toString() {
        return super.toString() +" SymbolFunc{" +
                "NomeFunc=" + Lexer.stringTable.get(super.getLessema()) +
                ", returnType='" + returnType + '\'' +
                '}';
    }
}
