package esercitazione4Cup.GrammarClasses.Stats;

import esercitazione4Cup.GrammarClasses.Leaf.Expr;
import esercitazione4Cup.GrammarClasses.Leaf.StringConst;
import esercitazione4Cup.GrammarClasses.Visitor.Visitor;

import java.util.ArrayList;

public class WriteStatOp extends Stat{
    ArrayList<Expr> exprList;
    StringConst typeWrite;

    public WriteStatOp(ArrayList<Expr> exprList, StringConst typeWrite) {
        this.exprList = exprList;
        this.typeWrite = typeWrite;
    }

    public ArrayList<Expr> getExprList() {
        return exprList;
    }

    public void setExprList(ArrayList<Expr> exprList) {
        this.exprList = exprList;
    }

    public StringConst getTypeWrite() {
        return typeWrite;
    }

    public void setTypeWrite(StringConst typeWrite) {
        this.typeWrite = typeWrite;
    }
    public Object accept(Visitor v) {
        return v.visit(this);
    }

}
