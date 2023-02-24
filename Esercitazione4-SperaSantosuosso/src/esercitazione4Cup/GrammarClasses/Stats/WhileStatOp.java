package esercitazione4Cup.GrammarClasses.Stats;

import esercitazione4Cup.GrammarClasses.BodyOp;
import esercitazione4Cup.GrammarClasses.Leaf.Expr;
import esercitazione4Cup.GrammarClasses.Visitor.Visitor;

public class WhileStatOp extends Stat{
    Expr expr;
    BodyOp body;

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
    public Object accept(Visitor v) {
        return v.visit(this);
    }

}
