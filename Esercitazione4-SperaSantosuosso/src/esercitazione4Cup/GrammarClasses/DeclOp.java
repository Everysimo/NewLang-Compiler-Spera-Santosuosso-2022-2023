package esercitazione4Cup.GrammarClasses;

import esercitazione4Cup.GrammarClasses.Visitor.Visitable;
import esercitazione4Cup.GrammarClasses.Visitor.Visitor;

public abstract class DeclOp implements Visitable {
    @Override
    public Object accept(Visitor v) {
        return null;
    }
}
