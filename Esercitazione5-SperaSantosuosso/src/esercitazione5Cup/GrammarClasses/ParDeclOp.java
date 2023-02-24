package esercitazione5Cup.GrammarClasses;

import esercitazione5Cup.GrammarClasses.Leaf.Identifier;
import esercitazione5Cup.GrammarClasses.Leaf.IdentifierOrInit;
import esercitazione5Cup.Visitor.Visitable;
import esercitazione5Cup.Visitor.Visitor;

import java.util.ArrayList;

public class ParDeclOp implements Visitable {
    String type;
    String out;
    ArrayList<IdentifierOrInit> idList;

    public ParDeclOp(String out,String type, ArrayList<IdentifierOrInit> idList) {
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

    public ArrayList<IdentifierOrInit> getIdList() {
        return idList;
    }

    public void setIdList(ArrayList<IdentifierOrInit> idList) {
        this.idList = idList;
    }

    @Override
    public Object accept(Visitor v) throws Exception {
        return v.visit(this);
    }
}
