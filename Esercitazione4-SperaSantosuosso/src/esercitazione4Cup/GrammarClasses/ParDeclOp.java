package esercitazione4Cup.GrammarClasses;

import esercitazione4Cup.GrammarClasses.Leaf.Identifier;
import esercitazione4Cup.GrammarClasses.Visitor.Visitable;
import esercitazione4Cup.GrammarClasses.Visitor.Visitor;

import java.util.ArrayList;

public class ParDeclOp implements Visitable {
    String type;
    String out;
    ArrayList<Identifier> idList;

    public ParDeclOp(String out,String type, ArrayList<Identifier> idList) {
        this.type = type;
        this.out = out;
        this.idList = idList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }

    public ArrayList<Identifier> getIdList() {
        return idList;
    }

    public void setIdList(ArrayList<Identifier> idList) {
        this.idList = idList;
    }

    @Override
    public Object accept(Visitor v) {
        return v.visit(this);
    }
}
