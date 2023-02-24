package esercitazione4Cup.GrammarClasses.Stats;

import esercitazione4Cup.GrammarClasses.Leaf.Expr;
import esercitazione4Cup.GrammarClasses.Leaf.Identifier;
import esercitazione4Cup.GrammarClasses.Visitor.Visitor;

import java.util.ArrayList;

public class FunCallOp extends Expr{
    Identifier id;
    ArrayList<Expr> exprList;

    public FunCallOp(Identifier id, ArrayList<Expr> exprList) {
        this.id = id;
        this.exprList = exprList;
    }

    public Identifier getId() {
        return id;
    }

    public void setId(Identifier id) {
        this.id = id;
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
