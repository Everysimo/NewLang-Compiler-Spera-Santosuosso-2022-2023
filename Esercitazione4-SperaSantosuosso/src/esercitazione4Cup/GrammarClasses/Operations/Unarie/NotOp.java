package esercitazione4Cup.GrammarClasses.Operations.Unarie;

import esercitazione4Cup.GrammarClasses.Leaf.Expr;
import esercitazione4Cup.GrammarClasses.Visitor.Visitor;

public class NotOp extends Expr{
    Expr ref;

    public NotOp(Expr ref) {
        this.ref = ref;
    }

    public Expr getRef() {
        return ref;
    }

    public void setRef(Expr ref) {
        this.ref = ref;
    }

    @Override
    public Object accept(Visitor v) {
        return v.visit(this);
    }
}
