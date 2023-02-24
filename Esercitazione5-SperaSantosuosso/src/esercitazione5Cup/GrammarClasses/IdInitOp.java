package esercitazione5Cup.GrammarClasses;

import esercitazione5Cup.GrammarClasses.Leaf.Expr;
import esercitazione5Cup.GrammarClasses.Leaf.Identifier;
import esercitazione5Cup.GrammarClasses.Leaf.IdentifierOrInit;
import esercitazione5Cup.TabellaSimboli.ErrorLine;
import esercitazione5Cup.Visitor.Visitor;

public class IdInitOp extends IdentifierOrInit implements ErrorLine {
    Identifier id;
    Expr expr;

    private int line;

    public IdInitOp(Identifier id, Expr expr) {
        super();
        this.id = id;
        this.expr = expr;
    }

    @Override
    public int getAttrib() {
        return this.id.getAttrib();
    }

    public Identifier getId() {
        return id;
    }

    public void setId(Identifier id) {
        this.id = id;
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }

    public Object accept(Visitor v) throws Exception {
        return v.visit(this);
    }

    @Override
    public int getLine() {
        return id.getLine();
    }

    @Override
    public void setLine(int line) {
        id.setLine(line);
    }

}
