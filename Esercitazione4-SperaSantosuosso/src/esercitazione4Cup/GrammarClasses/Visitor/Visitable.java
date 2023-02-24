package esercitazione4Cup.GrammarClasses.Visitor;

import esercitazione4Cup.GrammarClasses.Leaf.IntegerConst;
import esercitazione4Cup.GrammarClasses.Leaf.RealConst;

public interface Visitable {

    public Object accept(Visitor v);

}
