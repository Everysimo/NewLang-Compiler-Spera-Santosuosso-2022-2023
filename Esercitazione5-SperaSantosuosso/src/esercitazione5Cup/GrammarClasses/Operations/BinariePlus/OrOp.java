package esercitazione5Cup.GrammarClasses.Operations.BinariePlus;

import esercitazione5Cup.GrammarClasses.Leaf.Expr;
import esercitazione5Cup.GrammarClasses.Stats.TypeSetter;
import esercitazione5Cup.Visitor.Visitor;

public class OrOp extends BinaryOp{
    public OrOp(Expr ref1, Expr ref2) {
        super(ref1, ref2);
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
