package esercitazione4Cup.GrammarClasses.Stats;

import esercitazione4Cup.GrammarClasses.BodyOp;
import esercitazione4Cup.GrammarClasses.Leaf.Expr;
import esercitazione4Cup.GrammarClasses.Visitor.Visitor;

public class IfStatOp extends Stat {
    Expr expr;
    BodyOp bodyOp1;
    BodyOp bodyOp2 = null;

    public IfStatOp(Expr expr, BodyOp bodyOp1, BodyOp bodyOp2) {
        this.expr = expr;
        this.bodyOp1 = bodyOp1;
        this.bodyOp2 = bodyOp2;
    }


    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }

    public BodyOp getBodyOp1() {
        return bodyOp1;
    }

    public void setBodyOp1(BodyOp bodyOp1) {
        this.bodyOp1 = bodyOp1;
    }

    public BodyOp getBodyOp2() {
        return bodyOp2;
    }

    public void setBodyOp2(BodyOp bodyOp2) {
        this.bodyOp2 = bodyOp2;
    }
    public Object accept(Visitor v) {
        return v.visit(this);
    }

}
