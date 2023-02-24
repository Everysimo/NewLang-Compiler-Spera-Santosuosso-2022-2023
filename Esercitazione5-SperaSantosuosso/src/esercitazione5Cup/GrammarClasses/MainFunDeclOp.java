package esercitazione5Cup.GrammarClasses;

import JFlex.Main;
import esercitazione5Cup.TabellaSimboli.ErrorLine;
import esercitazione5Cup.Visitor.Visitable;
import esercitazione5Cup.Visitor.Visitor;

import java.util.ArrayList;

public class MainFunDeclOp implements Visitable, ErrorLine {
    String main;
    FunDeclOp funDeclOp;

    public MainFunDeclOp(String main, FunDeclOp funDeclOp) {
        this.main = main;
        this.funDeclOp = funDeclOp;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public FunDeclOp getFunDeclOp() {
        return funDeclOp;
    }

    public void setFunDeclOp(FunDeclOp funDeclOp) {
        this.funDeclOp = funDeclOp;
    }

    @Override
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

    private int line;
}
