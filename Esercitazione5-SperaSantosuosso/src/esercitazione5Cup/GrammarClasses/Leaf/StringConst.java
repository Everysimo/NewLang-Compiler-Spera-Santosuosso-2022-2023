package esercitazione5Cup.GrammarClasses.Leaf;

import esercitazione5Cup.GrammarClasses.Const;
import esercitazione5Cup.GrammarClasses.Stats.TypeSetter;
import esercitazione5Cup.Visitor.Visitor;

public class StringConst extends Const {

    public StringConst(String attrib) {
        this.attrib = attrib;
    }

    public String getAttrib() {
        return attrib;
    }

    public void setAttrib(String attrib) {
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
    TypeSetter type = new TypeSetter("string");
    private int line;
    private String attrib;
}
