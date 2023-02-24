package esercitazione5Cup.GrammarClasses.Stats;

import esercitazione5Cup.GrammarClasses.BodyOp;
import esercitazione5Cup.GrammarClasses.Leaf.Expr;
import esercitazione5Cup.Visitor.Visitor;

public class WhileStatOp extends Stat{
    Expr expr;
    BodyOp body;
    int line;

    public WhileStatOp(Expr expr, BodyOp body) {
        this.expr = expr;
        this.body = body;
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }

    public BodyOp getBody() {
        return body;
    }

    public void setBody(BodyOp body) {
        this.body = body;
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
