package esercitazione4Cup.GrammarClasses.Stats;

import esercitazione4Cup.GrammarClasses.BodyOp;
import esercitazione4Cup.GrammarClasses.IdInitOp;
import esercitazione4Cup.GrammarClasses.Leaf.IntegerConst;
import esercitazione4Cup.GrammarClasses.Visitor.Visitor;

public class ForStatOp extends Stat{
    IdInitOp idInit;
    IntegerConst integerConst;
    BodyOp body;

    public ForStatOp(IdInitOp idInit, IntegerConst integerConst, BodyOp body) {
        this.idInit = idInit;
        this.integerConst = integerConst;
        this.body = body;
    }

    public BodyOp getBody() {
        return body;
    }

    public void setBody(BodyOp body) {
        this.body = body;
    }

    public IdInitOp getIdInit() {
        return idInit;
    }

    public void setIdInit(IdInitOp idInit) {
        this.idInit = idInit;
    }

    public IntegerConst getIntegerConst() {
        return integerConst;
    }

    public void setIntegerConst(IntegerConst integerConst) {
        this.integerConst = integerConst;
    }
    public Object accept(Visitor v) {
        return v.visit(this);
    }

}
