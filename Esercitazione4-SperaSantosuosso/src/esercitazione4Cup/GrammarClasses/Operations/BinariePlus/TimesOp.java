package esercitazione4Cup.GrammarClasses.Operations.BinariePlus;

import esercitazione4Cup.GrammarClasses.Leaf.Expr;
import esercitazione4Cup.GrammarClasses.Visitor.Visitor;

public class TimesOp extends BinaryOp{

    public TimesOp(Expr ref1, Expr ref2) {
        super(ref1, ref2);
    }

    @Override
    public Object accept(Visitor v) {
        return v.visit(this);
    }
}