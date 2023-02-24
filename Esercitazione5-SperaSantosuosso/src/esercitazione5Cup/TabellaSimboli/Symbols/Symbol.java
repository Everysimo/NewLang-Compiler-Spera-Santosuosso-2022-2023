package esercitazione5Cup.TabellaSimboli.Symbols;

import esercitazione5Cup.Lexer;
import esercitazione5Cup.TabellaSimboli.ErrorLine;

public class Symbol implements ErrorLine {

    private Integer lessema;
    private int line;

    public Symbol(Integer lessema){
        this.lessema = lessema;
    }

    public Integer getLessema() {
        return lessema;
    }

    public void setLessema(Integer lessema) {
        this.lessema = lessema;
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
    public String toString() {
        return "Symbol{" +
                "idlessema='(" + lessema + ")"+ Lexer.stringTable.get(lessema)+" \'" +
                ", line=" + (line+1) +
                '}';
    }
}
