package esercitazione5Cup.GrammarClasses;

import esercitazione5Cup.GrammarClasses.Leaf.Expr;
import esercitazione5Cup.GrammarClasses.Leaf.Identifier;
import esercitazione5Cup.GrammarClasses.Leaf.IdentifierOrInit;
import esercitazione5Cup.GrammarClasses.Stats.TypeSetter;
import esercitazione5Cup.Visitor.Visitor;

import java.util.ArrayList;

public class VarDeclOp extends DeclOp{
    TypeSetter type;
    ArrayList<IdentifierOrInit> idList;

    int line;

    public VarDeclOp(String type, ArrayList<IdentifierOrInit> idList) {
        this.type = new TypeSetter(type);
        this.idList = idList;
    }

    public TypeSetter getType() {
        return type;
    }

    public void setType(TypeSetter type) {
        this.type = type;
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

    @Override
    public int getLine() {
        return this.line;
    }

    @Override
    public void setLine(int line) {
        this.line = line;
    }
}
