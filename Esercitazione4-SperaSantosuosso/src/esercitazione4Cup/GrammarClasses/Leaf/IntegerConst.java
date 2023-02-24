package esercitazione4Cup.GrammarClasses.Leaf;

import esercitazione4Cup.GrammarClasses.Const;
import esercitazione4Cup.GrammarClasses.Visitor.Visitable;
import esercitazione4Cup.GrammarClasses.Visitor.Visitor;

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

    private int attrib;

    @Override
    public Object accept(Visitor v){
        return v.visit(this);
    }

}
