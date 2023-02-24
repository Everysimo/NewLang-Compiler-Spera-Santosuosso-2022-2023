package esercitazione4Cup.GrammarClasses.Stats;

import esercitazione4Cup.GrammarClasses.Leaf.Expr;
import esercitazione4Cup.GrammarClasses.Leaf.Identifier;
import esercitazione4Cup.GrammarClasses.Visitor.Visitor;

import java.util.ArrayList;

public class AssignStatOp extends Stat{
    ArrayList<Identifier> idList;
    ArrayList<Expr> exprList;

    public AssignStatOp(ArrayList<Identifier> idList, ArrayList<Expr> exprList) {
        this.idList = idList;
        this.exprList = exprList;
    }

    public ArrayList<Identifier> getIdList() {
        return idList;
    }

    public void setIdList(ArrayList<Identifier> idList) {
        this.idList = idList;
    }

    public ArrayList<Expr> getExprList() {
        return exprList;
    }

    public void setExprList(ArrayList<Expr> exprList) {
        this.exprList = exprList;
    }

    public Object accept(Visitor v) {
        return v.visit(this);
    }

}
