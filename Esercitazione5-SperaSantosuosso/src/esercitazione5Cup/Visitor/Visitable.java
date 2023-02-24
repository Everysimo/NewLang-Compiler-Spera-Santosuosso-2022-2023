package esercitazione5Cup.Visitor;

import esercitazione5Cup.GrammarClasses.Leaf.IntegerConst;
import esercitazione5Cup.GrammarClasses.Leaf.RealConst;

public interface Visitable {

    public Object accept(Visitor v) throws Exception;

}
