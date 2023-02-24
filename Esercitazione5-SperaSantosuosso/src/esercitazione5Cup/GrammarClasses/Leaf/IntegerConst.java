package esercitazione5Cup.GrammarClasses.Leaf;

import esercitazione5Cup.GrammarClasses.Const;
import esercitazione5Cup.GrammarClasses.Stats.TypeSetter;
import esercitazione5Cup.Visitor.Visitable;
import esercitazione5Cup.Visitor.Visitor;

public class IntegerConst extends Const{

    public IntegerConst(int value) {
        this.attrib = value;
    }

    public IntegerConst() {
        this.attrib = 0;
    }

    public int getAttrib() {
        return attrib;
    }

    public void setAttrib(int attrib) {
        this.attrib = attrib;
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

    @Override
    public Object accept(Visitor v) throws Exception {
        return v.visit(this);
    }


    TypeSetter type = new TypeSetter("integer");

    private int line;
    private int attrib;



}
