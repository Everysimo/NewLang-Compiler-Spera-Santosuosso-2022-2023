package esercitazione5Cup.GrammarClasses.Stats;

import esercitazione5Cup.GrammarClasses.Leaf.Expr;
import esercitazione5Cup.GrammarClasses.Leaf.Identifier;
import esercitazione5Cup.Visitor.Visitor;

import java.util.ArrayList;

public class FunCallOp extends Expr{
    Identifier id;
    ArrayList<Expr> exprList;

    TypeSetter type;

    public FunCallOp(Identifier id, ArrayList<Expr> exprList,TypeSetter type) {
        this.id = id;
        this.exprList = exprList;
        this.type = type;
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
    public Object accept(Visitor v) throws Exception {
        return v.visit(this);
    }

    public TypeSetter getType(){
        return type;
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public void setLine(int line) {
        this.line = line;
    }

    private int line;
}
