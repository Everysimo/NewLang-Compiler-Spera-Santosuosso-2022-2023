package esercitazione4Cup.GrammarClasses.Leaf;

import esercitazione4Cup.GrammarClasses.Stats.Stat;
import esercitazione4Cup.GrammarClasses.Visitor.Visitable;
import esercitazione4Cup.GrammarClasses.Visitor.Visitor;

public abstract class Expr extends Stat implements Visitable {

    public Object accept(Visitor v){return null;}

}
