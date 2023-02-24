package esercitazione4Cup.GrammarClasses.Leaf;

import esercitazione4Cup.GrammarClasses.Const;
import esercitazione4Cup.GrammarClasses.Visitor.Visitor;

public class BooleanConst extends Const{
    Boolean attrib;

    public BooleanConst(Boolean attrib) {
        this.attrib = attrib;
    }

    public Boolean getAttrib() {
        return attrib;
    }

    public void setAttrib(Boolean attrib) {
        this.attrib = attrib;
    }

    @Override
    public Object accept(Visitor v){
        return v.visit(this);
    }
}
