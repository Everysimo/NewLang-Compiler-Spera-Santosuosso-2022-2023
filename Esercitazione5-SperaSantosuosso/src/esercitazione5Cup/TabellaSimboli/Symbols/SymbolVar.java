package esercitazione5Cup.TabellaSimboli.Symbols;

import esercitazione5Cup.Lexer;

public class SymbolVar extends Symbol {
    private String tipo;
    private boolean isOut;
    

    public SymbolVar(Integer nome, String tipo){
        super(nome);
        this.tipo = tipo;
        isOut = false;
    }

    public boolean isOut() {
        return isOut;
    }

    public void setOut(boolean out) {
        isOut = out;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return super.toString() +" SymbolVar{" +
                "NomeVar=" + Lexer.stringTable.get(super.getLessema()) +
                ", Type='" + tipo + '\'' +
                '}';
    }
}
