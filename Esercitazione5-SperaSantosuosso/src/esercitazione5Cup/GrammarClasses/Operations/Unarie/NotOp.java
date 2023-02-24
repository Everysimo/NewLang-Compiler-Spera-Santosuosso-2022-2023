package esercitazione5Cup.GrammarClasses.Operations.Unarie;

import esercitazione5Cup.GrammarClasses.Leaf.Expr;
import esercitazione5Cup.GrammarClasses.Stats.TypeSetter;
import esercitazione5Cup.Visitor.Visitor;

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

    @Override
    public TypeSetter getType() {
        return type;
    }

    @Override
    public void setType(TypeSetter type) {
        this.type = type;
    }

    private TypeSetter type;

    private int line;
}
