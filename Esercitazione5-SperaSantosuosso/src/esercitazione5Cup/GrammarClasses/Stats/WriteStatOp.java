package esercitazione5Cup.GrammarClasses.Stats;

import esercitazione5Cup.GrammarClasses.Leaf.Expr;
import esercitazione5Cup.GrammarClasses.Leaf.StringConst;
import esercitazione5Cup.Visitor.Visitor;

import java.util.ArrayList;

public class WriteStatOp extends Stat{
    ArrayList<Expr> exprList;
    StringConst typeWrite;

    int line;

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
    public Object accept(Visitor v) throws Exception {
        return v.visit(this);
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public void setLine(int line) {
        this.line = line;
    }



}
