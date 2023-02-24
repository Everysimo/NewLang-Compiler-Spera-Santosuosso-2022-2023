package esercitazione5Cup.GrammarClasses.Stats;

import esercitazione5Cup.GrammarClasses.BodyOp;
import esercitazione5Cup.GrammarClasses.IdInitOp;
import esercitazione5Cup.GrammarClasses.Leaf.IntegerConst;
import esercitazione5Cup.TabellaSimboli.ErrorLine;
import esercitazione5Cup.Visitor.Visitor;

public class ForStatOp extends Stat implements ErrorLine {
    IdInitOp idInit;
    IntegerConst integerConst;
    BodyOp body;
    private int line;

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
