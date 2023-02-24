package esercitazione4Cup.GrammarClasses;

import esercitazione4Cup.GrammarClasses.Stats.Stat;
import esercitazione4Cup.GrammarClasses.Visitor.Visitor;

import java.util.ArrayList;

public class BodyOp {
    ArrayList<VarDeclOp> verDecls;
    ArrayList<Stat> stats;

    public BodyOp(ArrayList<VarDeclOp> verDecls, ArrayList<Stat> stats) {
        this.verDecls = verDecls;
        this.stats = stats;
    }

    public ArrayList<VarDeclOp> getVerDecls() {
        return verDecls;
    }

    public void setVerDecls(ArrayList<VarDeclOp> verDecls) {
        this.verDecls = verDecls;
    }

    public ArrayList<Stat> getStats() {
        return stats;
    }

    public void setStats(ArrayList<Stat> stats) {
        this.stats = stats;
    }

    public Object accept(Visitor v) {
        return v.visit(this);
    }

}
