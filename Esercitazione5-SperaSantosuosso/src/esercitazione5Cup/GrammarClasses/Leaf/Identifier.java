package esercitazione5Cup.GrammarClasses.Leaf;


import esercitazione5Cup.GrammarClasses.Stats.TypeSetter;
import esercitazione5Cup.Visitor.Visitor;

public class Identifier extends IdentifierOrInit {

    public Identifier(){}

    public Identifier(int attrib) {
        this.attrib = attrib;
    }

    public int getAttrib() {
        return attrib;
    }

    public void setAttrib(int attrib) {
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

    private TypeSetter type;
    private int line;
    private int attrib;



}
