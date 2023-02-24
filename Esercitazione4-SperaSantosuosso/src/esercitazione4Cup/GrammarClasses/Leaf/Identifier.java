package esercitazione4Cup.GrammarClasses.Leaf;


import esercitazione4Cup.GrammarClasses.Const;
import esercitazione4Cup.GrammarClasses.IdInitOp;
import esercitazione4Cup.GrammarClasses.Visitor.Visitor;

public class Identifier extends Const {

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
    public Object accept(Visitor v){
        return v.visit(this);
    }
    private int attrib;
}
