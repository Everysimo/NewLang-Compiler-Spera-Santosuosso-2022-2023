package esercitazione4Cup.GrammarClasses;

import esercitazione4Cup.GrammarClasses.Leaf.Expr;
import esercitazione4Cup.GrammarClasses.Leaf.Identifier;
import esercitazione4Cup.GrammarClasses.Visitor.Visitor;

public class IdInitOp extends Identifier{
    Identifier id;
    Expr expr;

    public IdInitOp(Identifier id, Expr expr) {
        super();
        this.id = id;
        this.expr = expr;
    }

    public Identifier getId() {
        return id;
    }

    public void setId(Identifier id) {
        this.id = id;
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }

    public Object accept(Visitor v) {
        return v.visit(this);
    }
}
