package esercitazione5Cup.GrammarClasses.Stats;

import esercitazione5Cup.GrammarClasses.BodyOp;
import esercitazione5Cup.GrammarClasses.Leaf.Expr;
import esercitazione5Cup.Visitor.Visitor;

public class IfStatOp extends Stat {
    Expr expr;
    BodyOp bodyOp1;
    BodyOp bodyOp2 = null;

    int line;

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
