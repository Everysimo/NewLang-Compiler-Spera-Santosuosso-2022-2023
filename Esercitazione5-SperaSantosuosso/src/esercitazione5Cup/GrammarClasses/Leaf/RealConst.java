package esercitazione5Cup.GrammarClasses.Leaf;

import esercitazione5Cup.GrammarClasses.Const;
import esercitazione5Cup.GrammarClasses.Stats.TypeSetter;
import esercitazione5Cup.Visitor.Visitor;

public class RealConst extends Const {

    public RealConst(Float attrib) {
        this.attrib = attrib;
    }

    public RealConst() {
        this.attrib = 0.0f;
    }

    public Float getAttrib() {
        return attrib;
    }

    public void setAttrib(Float attrib) {
        this.attrib = attrib;
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

    TypeSetter type = new TypeSetter("float");
    private int line;
    private Float attrib;


}
