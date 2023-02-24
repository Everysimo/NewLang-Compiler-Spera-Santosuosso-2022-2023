package esercitazione4Cup.GrammarClasses.Stats;

import esercitazione4Cup.GrammarClasses.Leaf.Expr;
import esercitazione4Cup.GrammarClasses.Visitor.Visitable;
import esercitazione4Cup.GrammarClasses.Visitor.Visitor;

public abstract class Stat implements Visitable {

    public Object accept(Visitor v){return null;}


}
