package esercitazione5Cup.GrammarClasses.Stats;

import esercitazione5Cup.GrammarClasses.Leaf.Expr;
import esercitazione5Cup.TabellaSimboli.ErrorLine;
import esercitazione5Cup.Visitor.Visitable;
import esercitazione5Cup.Visitor.Visitor;

public abstract class Stat implements Visitable, ErrorLine {

    public Object accept(Visitor v) throws Exception {return null;}


}
