package esercitazione5Cup.GrammarClasses.Leaf;

import esercitazione5Cup.GrammarClasses.Stats.Stat;
import esercitazione5Cup.GrammarClasses.Stats.TypeSetter;
import esercitazione5Cup.TabellaSimboli.ErrorLine;
import esercitazione5Cup.Visitor.Visitable;
import esercitazione5Cup.Visitor.Visitor;

public abstract class Expr extends Stat implements Visitable, ErrorLine {

    private TypeSetter type;

    public TypeSetter getType() {
        return type;
    }

    public void setType(TypeSetter type) {
        this.type = type;
    }

    public Object accept(Visitor v) throws Exception {return null;}

}
