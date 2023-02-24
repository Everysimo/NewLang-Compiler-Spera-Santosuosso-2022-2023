package esercitazione5Cup.GrammarClasses;

import esercitazione5Cup.GrammarClasses.Leaf.Expr;
import esercitazione5Cup.GrammarClasses.Stats.Stat;
import esercitazione5Cup.TabellaSimboli.ErrorLine;
import esercitazione5Cup.Visitor.Visitable;
import esercitazione5Cup.Visitor.Visitor;

public class ReturnOp extends Stat implements Visitable,ErrorLine {
    private Expr expr;
    private int line;

    public ReturnOp(Expr expr){
        this.expr = expr;
    }

    public ReturnOp(){
        this.expr = null;
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }

    @Override
    public int getLine() {
        return this.line;
    }

    @Override
    public void setLine(int line) {
        this.line = line;
    }

    public Object accept(Visitor v) throws Exception {
        return v.visit(this);
    }
}
