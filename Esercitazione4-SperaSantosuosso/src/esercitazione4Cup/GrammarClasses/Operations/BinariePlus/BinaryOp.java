package esercitazione4Cup.GrammarClasses.Operations.BinariePlus;

import esercitazione4Cup.GrammarClasses.Leaf.Expr;
import esercitazione4Cup.GrammarClasses.Visitor.Visitable;

public abstract class BinaryOp extends Expr {
    Expr ref1,ref2;

    public BinaryOp(Expr ref1, Expr ref2) {
        this.ref1 = ref1;
        this.ref2 = ref2;
    }

    public Expr getRef1() {
        return ref1;
    }

    public void setRef1(Expr ref1) {
        this.ref1 = ref1;
    }

    public Expr getRef2() {
        return ref2;
    }

    public void setRef2(Expr ref2) {
        this.ref2 = ref2;
    }
}
