package esercitazione4Cup.GrammarClasses;

import JFlex.Main;
import esercitazione4Cup.GrammarClasses.Visitor.Visitable;
import esercitazione4Cup.GrammarClasses.Visitor.Visitor;

import java.util.ArrayList;

public class MainFunDeclOp implements Visitable {
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
    public Object accept(Visitor v) {
        return v.visit(this);
    }
}
