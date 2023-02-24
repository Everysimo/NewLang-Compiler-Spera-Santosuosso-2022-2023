package esercitazione4Cup.GrammarClasses.Leaf;

import esercitazione4Cup.GrammarClasses.Const;
import esercitazione4Cup.GrammarClasses.Visitor.Visitor;

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
    public Object accept(Visitor v){
        return v.visit(this);
    }
    private String attrib;
}
