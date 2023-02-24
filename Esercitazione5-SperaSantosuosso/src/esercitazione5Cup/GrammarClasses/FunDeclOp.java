package esercitazione5Cup.GrammarClasses;

import esercitazione5Cup.GrammarClasses.Leaf.Identifier;
import esercitazione5Cup.GrammarClasses.Stats.TypeSetter;
import esercitazione5Cup.TabellaSimboli.ErrorLine;
import esercitazione5Cup.TabellaSimboli.SymbolTable;
import esercitazione5Cup.Visitor.Visitor;

import java.util.ArrayList;

public class FunDeclOp extends DeclOp{
    Identifier id;
    ArrayList<ParDeclOp> parDeclList;
    String typeOrVoid;
    BodyOp body;

    int line;


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


    public void setType(TypeSetter type) {
        super.setType(type);
    }

}
