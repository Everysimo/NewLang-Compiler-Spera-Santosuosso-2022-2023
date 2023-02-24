package esercitazione4Cup.GrammarClasses.Leaf;

import esercitazione4Cup.GrammarClasses.Const;
import esercitazione4Cup.GrammarClasses.Visitor.Visitor;

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
    public Object accept(Visitor v){
        return v.visit(this);
    }

    private Float attrib;


}
