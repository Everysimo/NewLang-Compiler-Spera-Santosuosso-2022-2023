package esercitazione4Cup.GrammarClasses.Stats;

import esercitazione4Cup.GrammarClasses.Leaf.Identifier;
import esercitazione4Cup.GrammarClasses.Leaf.StringConst;
import esercitazione4Cup.GrammarClasses.Visitor.Visitor;

import java.util.ArrayList;

public class ReadStatOp extends Stat{
    ArrayList<Identifier> idList;
    StringConst string_const;

    public ReadStatOp(ArrayList<Identifier> idList, StringConst string_const) {
        this.idList = idList;
        this.string_const = string_const;
    }

    public ArrayList<Identifier> getIdList() {
        return idList;
    }

    public void setIdList(ArrayList<Identifier> idList) {
        this.idList = idList;
    }

    public StringConst getString_const() {
        return string_const;
    }

    public void setString_const(StringConst string_const) {
        this.string_const = string_const;
    }
    public Object accept(Visitor v) {
        return v.visit(this);
    }

}
