package esercitazione4Cup.GrammarClasses;

import esercitazione4Cup.GrammarClasses.Leaf.Identifier;
import esercitazione4Cup.GrammarClasses.Visitor.Visitor;

import java.util.ArrayList;

public class FunDeclOp extends DeclOp{
    Identifier id;
    ArrayList<ParDeclOp> parDeclList;
    String typeOrVoid;
    BodyOp body;

    public FunDeclOp(Identifier id, ArrayList<ParDeclOp> parDeclList, String typeOrVoid, BodyOp body) {
        this.id = id;
        this.parDeclList = parDeclList;
        this.typeOrVoid = typeOrVoid;
        this.body = body;
    }

    public Identifier getId() {
        return id;
    }

    public void setId(Identifier id) {
        this.id = id;
    }

    public ArrayList<ParDeclOp> getParDeclList() {
        return parDeclList;
    }

    public void setParDeclList(ArrayList<ParDeclOp> parDeclList) {
        this.parDeclList = parDeclList;
    }

    public String getTypeOrVoid() {
        return typeOrVoid;
    }

    public void setTypeOrVoid(String typeOrVoid) {
        this.typeOrVoid = typeOrVoid;
    }

    public BodyOp getBody() {
        return body;
    }

    public void setBody(BodyOp body) {
        this.body = body;
    }

    public Object accept(Visitor v) {
        return v.visit(this);
    }
}
