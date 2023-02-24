package esercitazione5Cup.GrammarClasses.Leaf;

import esercitazione5Cup.GrammarClasses.Const;
import esercitazione5Cup.GrammarClasses.Stats.TypeSetter;
import esercitazione5Cup.TabellaSimboli.ErrorLine;

public abstract class IdentifierOrInit extends Const implements ErrorLine {
    private TypeSetter type;
    int attrib;
    int line;
    public int getAttrib(){
        return attrib;
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public void setLine(int line) {
        this.line = line;
    }

    @Override
    public TypeSetter getType() {
        return type;
    }

    @Override
    public void setType(TypeSetter type) {
        this.type = type;
    }



}
