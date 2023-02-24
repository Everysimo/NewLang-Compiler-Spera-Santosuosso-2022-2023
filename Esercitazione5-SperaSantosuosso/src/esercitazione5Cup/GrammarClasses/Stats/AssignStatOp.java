package esercitazione5Cup.GrammarClasses.Stats;

import esercitazione5Cup.GrammarClasses.Leaf.Expr;
import esercitazione5Cup.GrammarClasses.Leaf.Identifier;
import esercitazione5Cup.GrammarClasses.Leaf.IdentifierOrInit;
import esercitazione5Cup.Visitor.Visitor;

import java.util.ArrayList;

public class AssignStatOp extends Stat{
    ArrayList<IdentifierOrInit> idList;
    ArrayList<Expr> exprList;

    int line;

    public AssignStatOp(ArrayList<IdentifierOrInit> idList, ArrayList<Expr> exprList) {
        this.idList = idList;
        this.exprList = exprList;
    }

    public ArrayList<IdentifierOrInit> getIdList() {
        return idList;
    }

    public void setIdList(ArrayList<IdentifierOrInit> idList) {
        this.idList = idList;
    }

    public ArrayList<Expr> getExprList() {
        return exprList;
    }

    public void setExprList(ArrayList<Expr> exprList) {
        this.exprList = exprList;
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
