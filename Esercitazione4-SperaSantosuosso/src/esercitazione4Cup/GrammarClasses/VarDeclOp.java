package esercitazione4Cup.GrammarClasses;

import esercitazione4Cup.GrammarClasses.Leaf.Expr;
import esercitazione4Cup.GrammarClasses.Leaf.Identifier;
import esercitazione4Cup.GrammarClasses.Visitor.Visitor;

import java.util.ArrayList;

public class VarDeclOp extends DeclOp{
    String type;
    ArrayList<Identifier> idList;

    public VarDeclOp(String type, ArrayList<Identifier> idList) {
        this.type = type;
        this.idList = idList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
