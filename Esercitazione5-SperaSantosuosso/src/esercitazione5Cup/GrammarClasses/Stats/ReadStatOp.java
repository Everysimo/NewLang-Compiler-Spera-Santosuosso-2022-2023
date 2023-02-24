package esercitazione5Cup.GrammarClasses.Stats;

import esercitazione5Cup.GrammarClasses.Leaf.Identifier;
import esercitazione5Cup.GrammarClasses.Leaf.IdentifierOrInit;
import esercitazione5Cup.GrammarClasses.Leaf.StringConst;
import esercitazione5Cup.Visitor.Visitor;

import java.util.ArrayList;

public class ReadStatOp extends Stat{
    ArrayList<IdentifierOrInit> idList;
    StringConst string_const;

    int line;

    public ReadStatOp(ArrayList<IdentifierOrInit> idList, StringConst string_const) {
        this.idList = idList;
        this.string_const = string_const;
    }

    public ArrayList<IdentifierOrInit> getIdList() {
        return idList;
    }

    public void setIdList(ArrayList<IdentifierOrInit> idList) {
        this.idList = idList;
    }

    public StringConst getString_const() {
        return string_const;
    }

    public void setString_const(StringConst string_const) {
        this.string_const = string_const;
    }
    public Object accept(Visitor v) throws Exception {
        return v.visit(this);
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public void setLine(int line) {
        this.line = line;
    }



}
