package esercitazione4Cup.GrammarClasses;

import esercitazione4Cup.GrammarClasses.Visitor.Visitable;
import esercitazione4Cup.GrammarClasses.Visitor.Visitor;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;

public class ProgramOp extends DefaultMutableTreeNode implements Visitable {
    ArrayList<DeclOp> decList1;
    MainFunDeclOp main;
    ArrayList<DeclOp> decList2;

    public ProgramOp(ArrayList<DeclOp> decList1, MainFunDeclOp main, ArrayList<DeclOp> decList2) {
        this.decList1 = decList1;
        this.main = main;
        this.decList2 = decList2;
    }

    public ArrayList<DeclOp> getDecList1() {
        return decList1;
    }

    public void setDecList1(ArrayList<DeclOp> decList1) {
        this.decList1 = decList1;
    }

    public MainFunDeclOp getMain() {
        return main;
    }

    public void setMain(MainFunDeclOp main) {
        this.main = main;
    }

    public ArrayList<DeclOp> getDecList2() {
        return decList2;
    }

    public void setDecList2(ArrayList<DeclOp> decList2) {
        this.decList2 = decList2;
    }

    @Override
    public Object accept(Visitor v) {
        return v.visit(this);
    }


}
