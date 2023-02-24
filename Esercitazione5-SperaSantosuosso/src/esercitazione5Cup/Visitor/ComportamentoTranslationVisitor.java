package esercitazione5Cup.Visitor;

import esercitazione5Cup.GrammarClasses.*;
import esercitazione5Cup.GrammarClasses.Leaf.*;
import esercitazione5Cup.GrammarClasses.Operations.BinariePlus.*;
import esercitazione5Cup.GrammarClasses.Operations.Unarie.NotOp;
import esercitazione5Cup.GrammarClasses.Operations.Unarie.UMinusOp;
import esercitazione5Cup.GrammarClasses.Stats.*;
import esercitazione5Cup.Lexer;
import esercitazione5Cup.TabellaSimboli.SymbolTable;
import esercitazione5Cup.TabellaSimboli.Symbols.SymbolFunc;
import esercitazione5Cup.TabellaSimboli.Symbols.SymbolVar;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ComportamentoTranslationVisitor implements Visitor {

    String nomeFile;

    String tipoFunzione;
    Boolean risultatoTrovato = false;

    boolean isStatPrintf, isOpCalled,isOutParam = false;


    FileWriter fw;

    int tab = 0;

    boolean isFunCallAssigned = false;
    boolean isVarDeclString = false;

    private void inzializeWritingFile() throws Exception {
        try {
            File treeFile = new File("test_files/c_out/"+nomeFile+".c");
            fw = new FileWriter("test_files/c_out/"+nomeFile+".c");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public ComportamentoTranslationVisitor(String nomeFile) {
        this.nomeFile = nomeFile;
    }


    @Override
    public Object visit(ReturnOp v) throws Exception {
        stampaTab();
        fw.append("return ");
        if (v.getExpr() != null) {
            Expr e = v.getExpr();
            isFunCallAssigned = e instanceof FunCallOp;
            isOpCalled = e instanceof PowOp;
            v.getExpr().accept(this);

        }
        risultatoTrovato = true;
        fw.append(";\n");
        return null;
    }

    @Override
    public Object visit(IntegerConst ic) throws Exception {
        Integer lessame = ic.getAttrib();
        fw.append(lessame + "");
        return null;
    }

    @Override
    public Object visit(RealConst rc) throws Exception {
        Float lessame = rc.getAttrib();
        fw.append(lessame + "");
        return null;
    }

    @Override
    public Object visit(BooleanConst bc) throws Exception {
        Boolean lessame = bc.getAttrib();
        if (lessame) {
            fw.append("1");
        } else {
            fw.append("0");
        }
        return null;
    }

    @Override
    public Object visit(Identifier id) throws Exception {
        String lessame = Lexer.stringTable.get(id.getAttrib());
        SymbolVar var = SymbolTable.cercaVar(id.getAttrib());
        if (var != null && var.isOut()) {
            fw.append("*");
        }
        fw.append(lessame);
        return null;
    }

    @Override
    public Object visit(StringConst sc) throws Exception
    {
        String lessame = sc.getAttrib();

        if (isStatPrintf)
        {
            fw.append("").append(lessame);
            isStatPrintf = false;
        }
        else
        {
            fw.append("\"").append(lessame).append("\"");
        }
        return null;
    }

    @Override
    public Object visit(CharConst cc) throws Exception {
        String lessame = cc.getAttrib();
        if (isStatPrintf) {
            fw.append("").append(lessame);
            isStatPrintf = false;
        } else {
            fw.append("'").append(lessame).append("'");
        }
        return null;
    }

    @Override
    public Object visit(AndOp v) throws Exception {
        fw.append("(");
        isFunCallAssigned = v.getRef1() instanceof FunCallOp;
        v.getRef1().accept(this);
        fw.append(" && ");
        isFunCallAssigned = v.getRef2() instanceof FunCallOp;
        v.getRef2().accept(this);
        fw.append(")");
        return null;
    }

    @Override
    public Object visit(DivOp v) throws Exception {
        fw.append("(");
        isFunCallAssigned = v.getRef1() instanceof FunCallOp;
        v.getRef1().accept(this);
        fw.append("/");
        isFunCallAssigned = v.getRef2() instanceof FunCallOp;
        v.getRef2().accept(this);
        fw.append(")");
        return null;
    }

    @Override
    public Object visit(EqOp v) throws Exception
    {

        fw.append("(");
        isFunCallAssigned = v.getRef1() instanceof FunCallOp;
        v.getRef1().accept(this);
        fw.append(" == ");
        isFunCallAssigned = v.getRef2() instanceof FunCallOp;
        v.getRef2().accept(this);
        fw.append(")");
        return null;
    }

    @Override
    public Object visit(GeOp v) throws Exception {
        fw.append("(");
        isFunCallAssigned = v.getRef1() instanceof FunCallOp;
        v.getRef1().accept(this);
        fw.append(" >= ");
        isFunCallAssigned = v.getRef2() instanceof FunCallOp;
        v.getRef2().accept(this);
        fw.append(")");
        return null;
    }

    @Override
    public Object visit(GtOp v) throws Exception {
        fw.append("(");
        isFunCallAssigned = v.getRef1() instanceof FunCallOp;
        v.getRef1().accept(this);
        fw.append(" > ");
        isFunCallAssigned = v.getRef2() instanceof FunCallOp;
        v.getRef2().accept(this);
        fw.append(")");
        return null;
    }

    @Override
    public Object visit(LeOp v) throws Exception {
        fw.append("(");
        isFunCallAssigned = v.getRef1() instanceof FunCallOp;
        v.getRef1().accept(this);
        fw.append(" <= ");
        isFunCallAssigned = v.getRef2() instanceof FunCallOp;
        v.getRef2().accept(this);
        fw.append(")");
        return null;
    }

    @Override
    public Object visit(LtOp v) throws Exception {
        fw.append("(");
        isFunCallAssigned = v.getRef1() instanceof FunCallOp;
        v.getRef1().accept(this);
        fw.append(" < ");
        isFunCallAssigned = v.getRef2() instanceof FunCallOp;
        v.getRef2().accept(this);
        fw.append(")");
        return null;
    }

    @Override
    public Object visit(MinusOp v) throws Exception {
        fw.append("(");
        isFunCallAssigned = v.getRef1() instanceof FunCallOp;
        v.getRef1().accept(this);
        fw.append(" - ");
        isFunCallAssigned = v.getRef2() instanceof FunCallOp;
        v.getRef2().accept(this);
        fw.append(")");
        return null;
    }

    @Override
    public Object visit(NeOp v) throws Exception
    {
        fw.append("(");
        isFunCallAssigned = v.getRef1() instanceof FunCallOp;
        v.getRef1().accept(this);
        fw.append(" != ");
        isFunCallAssigned = v.getRef2() instanceof FunCallOp;
        v.getRef2().accept(this);
        fw.append(")");
        return null;
    }

    @Override
    public Object visit(OrOp v) throws Exception
    {
        fw.append("(");
        isFunCallAssigned = v.getRef1() instanceof FunCallOp;
        v.getRef1().accept(this);
        fw.append(" || ");
        isFunCallAssigned = v.getRef2() instanceof FunCallOp;
        v.getRef2().accept(this);
        fw.append(")");

        return null;
    }

    @Override
    public Object visit(PlusOp v) throws Exception {
        fw.append("(");
        isFunCallAssigned = v.getRef1() instanceof FunCallOp;
        v.getRef1().accept(this);
        fw.append(" + ");
        isFunCallAssigned = v.getRef2() instanceof FunCallOp;
        v.getRef2().accept(this);
        fw.append(")");
        return null;
    }

    @Override
    public Object visit(PowOp v) throws Exception {
        fw.append("pow(");
        isFunCallAssigned = v.getRef1() instanceof FunCallOp;
        v.getRef1().accept(this);
        fw.append(",");
        isFunCallAssigned = v.getRef2() instanceof FunCallOp;
        v.getRef2().accept(this);
        fw.append(")");
        if (!isOpCalled) {
            fw.append(";\n");
        }
        return null;
    }

    @Override
    public Object visit(StrCatOp v) throws Exception {
        isStatPrintf = false;
        fw.append("strcat(");
        if (v.getRef1() instanceof Identifier) {
            Identifier id = (Identifier) v.getRef1();
            if (SymbolTable.cercaVar(id.getAttrib()).getTipo().equals("string")) {
                isFunCallAssigned = v.getRef1() instanceof FunCallOp;
                v.getRef1().accept(this);
                fw.append(",");
                isFunCallAssigned = v.getRef2() instanceof FunCallOp ;
                v.getRef2().accept(this);
                fw.append(")");
                if (!isOpCalled) {
                    fw.append(";\n");
                }
            }
        }

        return null;
    }


    @Override
    public Object visit(TimesOp v) throws Exception {
        fw.append("(");
        isFunCallAssigned = v.getRef1() instanceof FunCallOp;
        v.getRef1().accept(this);
        fw.append(" * ");
        isFunCallAssigned = v.getRef2() instanceof FunCallOp ;
        v.getRef2().accept(this);
        fw.append(")");
        return null;
    }

    @Override
    public Object visit(NotOp v) throws Exception {
        fw.append("(");
        fw.append(" !");
        isFunCallAssigned = v.getRef() instanceof FunCallOp;
        v.getRef().accept(this);
        fw.append(")");
        return null;
    }

    @Override
    public Object visit(UMinusOp v) throws Exception {
        fw.append("(");
        fw.append("-");
        isFunCallAssigned = v.getRef() instanceof FunCallOp;
        v.getRef().accept(this);
        fw.append(")");
        return null;
    }

    @Override
    public Object visit(AssignStatOp v) throws Exception {
        for (int i = 0; i < v.getIdList().size(); i++) {
            isStatPrintf = false;
            stampaTab();
            SymbolTable.cercaVar(v.getIdList().get(i).getAttrib());
            v.getIdList().get(i).accept(this);
            fw.append(" = ");

            isFunCallAssigned = v.getExprList().get(i) instanceof FunCallOp;
            isOpCalled = v.getExprList().get(i) instanceof PowOp;
            v.getExprList().get(i).accept(this);
            if (!isFunCallAssigned) {
                fw.append(";\n");
            }
        }
        return null;
    }

    @Override
    public Object visit(ForStatOp v) throws Exception {
        SymbolTable.moveToNuovoScope();
        stampaTab();
        fw.append("for(");
        fw.append("int ");
        SymbolVar item1 = SymbolTable.cercaVar(v.getIdInit().getAttrib());
        IntegerConst item2 = v.getIntegerConst();
        v.getIdInit().accept(this);
        if(item1.getLessema() <= item2.getAttrib()){
            fw.append("; " + Lexer.stringTable.get(v.getIdInit().getAttrib()) + " <= ");
            v.getIntegerConst().accept(this);
            fw.append("; " + Lexer.stringTable.get(v.getIdInit().getAttrib()) + "++ )\n");
        }else{
            fw.append("; " + Lexer.stringTable.get(v.getIdInit().getAttrib()) + " >= ");
            v.getIntegerConst().accept(this);
            fw.append("; " + Lexer.stringTable.get(v.getIdInit().getAttrib()) + "-- )\n");
        }

        v.getBody().accept(this);

        SymbolTable.moveToScopeSuperiore();
        return null;


    }

    @Override
    public Object visit(FunCallOp v) throws Exception {
        boolean assignedCall = isFunCallAssigned;
        if (!assignedCall) {
            stampaTab();
        }

        fw.append(Lexer.stringTable.get(v.getId().getAttrib()));
        fw.append("(");

        SymbolFunc item1 = SymbolTable.cercaFunc(v.getId().getAttrib());

        String tipo1 = item1.getReturnType();

        int k = 0;


        ArrayList<ParDeclOp> params = item1.getParams();
        boolean isOut;

        int j = 0;

        for (k = 0; k < params.size(); k++) {

            ParDeclOp p = params.get(k);
            for (int i = 0; i < p.getIdList().size(); i++) {
                isOut = p.getOut() != null;
                if (isOut) {
                    Identifier id = null;
                    if (v.getExprList().get(j) instanceof Identifier) {
                        id = (Identifier) v.getExprList().get(j);

                    }
                    if (id != null) {
                        if (SymbolTable.cercaVar(id.getAttrib()) != null) {
                            fw.append("&");
                            id.accept(this);
                        }
                    }
                } else {
                    v.getExprList().get(j).accept(this);
                }

                if ((k == (params.size() - 1)) && (i != (p.getIdList().size() - 1))) {
                    fw.append(",");
                }
                if ((k != (params.size() - 1))) {
                    fw.append(",");
                }

                j = j + 1;
            }

        }
        fw.append(")");
        if (!assignedCall) {
            fw.append(";\n");
        }
        isFunCallAssigned = false;
        return tipo1;
    }

    @Override
    public Object visit(IfStatOp v) throws Exception {
        stampaTab();
        fw.append("if(");
        v.getExpr().accept(this);
        fw.append(")\n");

        SymbolTable.moveToNuovoScope();

        v.getBodyOp1().accept(this);

        SymbolTable.moveToScopeSuperiore();

        if (v.getBodyOp2().getVerDecls() != null || v.getBodyOp2().getStats() != null) {
            stampaTab();
            fw.append("else \n");

            SymbolTable.moveToNuovoScope();

            v.getBodyOp2().accept(this);

            SymbolTable.moveToScopeSuperiore();

        }
        return null;

    }

    @Override
    public Object visit(ReadStatOp v) throws Exception {
        if (v.getString_const() != null) {
            isStatPrintf = false;
            stampaTab();
            fw.append("printf(");
            v.getString_const().accept(this);
            fw.append(");\n");
        }


        StringBuilder s1 = new StringBuilder();
        String s2 = "";
        stampaTab();
        fw.append("scanf(");

        for (int i = 0; i < v.getIdList().size(); i++) {
            IdentifierOrInit id = v.getIdList().get(i);
            SymbolVar symbol = SymbolTable.cercaVar(id.getAttrib());

            if (symbol.getTipo().equals("integer")) {
                s1.append("%d");
            } else if (symbol.getTipo().equals("float")) {
                s1.append("%f");
            } else if (symbol.getTipo().equals("char")) {
                s1.append("%c");
            } else if (symbol.getTipo().equals("string")) {
                s1.append("%s");
            } else if (symbol.getTipo().equals("bool")) {
                s1.append("%d");
            }
            if (!symbol.getTipo().equals("string")) {
                s2 += " &";
            }
            s2 += Lexer.stringTable.get(v.getIdList().get(i).getAttrib()) + "";
            if (i != (v.getIdList().size() - 1)) {
                s2 += (",");
            }

        }

        fw.append("\"").append(s1.toString()).append("\",").append(s2).append(");\n");

        isStatPrintf=false;
        return null;
    }

    @Override
    public Object visit(WhileStatOp v) throws Exception {

        stampaTab();
        fw.append("while(");
        v.getExpr().accept(this);

        fw.append(")\n");

        SymbolTable.moveToNuovoScope();

        v.getBody().accept(this);

        SymbolTable.moveToScopeSuperiore();

        return null;

    }

    @Override
    public Object visit(WriteStatOp v) throws Exception {
        stampaTab();
        fw.append("printf(\"");
        isOpCalled = true;
        String s2 = "";
        for (Expr e : v.getExprList()) {
            if (e instanceof UMinusOp) {
                fw.append("-");
                e = ((UMinusOp) e).getRef();
            }
            isStatPrintf = true;
            if (e instanceof Identifier) {
                Identifier id = (Identifier) e;
                SymbolVar symbol = SymbolTable.cercaVar(id.getAttrib());
                if (symbol.getTipo().equals("integer")) {
                    fw.append("%d");
                } else if (symbol.getTipo().equals("char")) {
                    fw.append("%c");
                } else if (symbol.getTipo().equals("float")) {
                    fw.append("%f");
                } else if (symbol.getTipo().equals("string")) {
                    fw.append("%s");
                } else if (symbol.getTipo().equals("bool")) {
                    fw.append("%d");
                }
            } else if (e instanceof StrCatOp) {
                StrCatOp stc = (StrCatOp) e;
                fw.append("%s");
            } else if (e instanceof PowOp) {
                fw.append("%f");
            } else if (e instanceof TimesOp) {
                TimesOp to = (TimesOp) e;
                fw.append("%f");
            } else if (e.getType().getType().equals("string")) {
                e.accept(this);
                fw.append("");
            } else if (e.getType().getType().equals("char")) {
                e.accept(this);
                fw.append("");
            } else if (e.getType().getType().equals("integer")) {
                e.accept(this);
                fw.append("");
            } else if (e.getType().getType().equals("float")) {
                e.accept(this);
                fw.append("");
            }
        }

        fw.append("\"");
        for (Expr e : v.getExprList()) {
            //System.out.println(e.toString());
            isStatPrintf = true;
            if (e instanceof UMinusOp) {
                e = ((UMinusOp) e).getRef();
            }
            if (e instanceof Identifier) {
                Identifier id = (Identifier) e;
                SymbolVar symbol = SymbolTable.cercaVar(id.getAttrib());
                if (symbol.getTipo().equals("integer")) {
                    s2 += Lexer.stringTable.get(id.getAttrib()) + ",";
                } else if (symbol.getTipo().equals("char")) {
                    s2 += Lexer.stringTable.get(id.getAttrib()) + ",";
                } else if (symbol.getTipo().equals("float")) {
                    s2 += Lexer.stringTable.get(id.getAttrib()) + ",";
                } else if (symbol.getTipo().equals("string")) {
                    s2 += Lexer.stringTable.get(id.getAttrib()) + ",";
                } else if (symbol.getTipo().equals("bool")) {
                    s2 += Lexer.stringTable.get(id.getAttrib()) + ",";
                }
            } else if (e instanceof StrCatOp) {
                StrCatOp stc = (StrCatOp) e;
                fw.append(",");
                stc.accept(this);
            } else if (e instanceof PowOp || e instanceof TimesOp) {
                fw.append(",");
                e.accept(this);
            }
        }
        isOpCalled = false;
        //v.getTypeWrite().accept(this);
        if (s2.length() > 0) fw.append("," + s2.substring(0, s2.length() - 1));
        //System.out.println(s2.length());
        fw.append(");\n");
        if (((StringConst) v.getTypeWrite()).getAttrib().equals("writeln")) {
            stampaTab();
            fw.append("printf(\"\\n\");\n");

        }
        isStatPrintf=false;
        return null;
    }

    @Override
    public Object visit(BodyOp v) throws Exception {
        isStatPrintf = false;
        stampaTab();
        tab++;
        fw.append("{\n");

        if (v.getVerDecls() != null) {
            for (VarDeclOp vdo : v.getVerDecls()) {
                stampaTab();
                if (vdo != null) {
                    for (IdentifierOrInit i : vdo.getIdList()) {
                        if (i instanceof IdInitOp iop) {
                            if (iop.getExpr() instanceof Identifier id) {
                                SymbolVar sym = SymbolTable.cercaVar(id.getAttrib());
                                if (!SymbolTable.isVariabileDichiarataNelloScope(sym.getLessema())) {
                                    String tipo = sym.getTipo();

                                    switch (tipo) {
                                        case "string" -> {
                                            fw.append("char");
                                            isVarDeclString = true;
                                        }
                                        case "integer", "bool" -> fw.append("int");
                                        case "float" -> fw.append("float");
                                        case "char" -> fw.append("char");
                                    }
                                    fw.append(" ");
                                    id.accept(this);
                                    fw.append(";\n");
                                    stampaTab();

                                    SymbolTable.addDeclaredVar(sym.getLessema());
                                }
                            }
                        }
                    }
                    isVarDeclString = false;
                    vdo.accept(this);
                };
                fw.write("\n");
            }
        }

        if (v.getStats() != null)
        {
            for (Stat s : v.getStats())
            {

                if (s != null)
                {
                    s.accept(this);
                }
            }
        }

        tab--;
        stampaTab();
        fw.append("}\n");
        return null;
    }

    @Override
    public Object visit(VarDeclOp v) throws Exception
    {
        String lastTipo="";
        if(v.getIdList().size() > 0)
        {
            SymbolVar sym = SymbolTable.cercaVar(v.getIdList().get(0).getAttrib());

            if (!SymbolTable.isVariabileDichiarataNelloScope(sym.getLessema()))
            {
                lastTipo=sym.getTipo();
                switch (sym.getTipo())
                {
                    case "string" ->
                    {
                        fw.append("char");
                        isVarDeclString = true;
                    }
                    case "integer" -> fw.append("int");
                    case "float" -> fw.append("float");
                    case "char" -> fw.append("char");
                    case "bool" -> fw.append("int");
                }
                fw.append(" ");
            }
        }


        for (int i = 0; i < v.getIdList().size(); i++)
        {
            IdentifierOrInit id = v.getIdList().get(i);

            if (id instanceof IdInitOp iop)
            {
                if (iop.getExpr() == null)
                {
                    if (!SymbolTable.isVariabileDichiarataNelloScope((iop.getId().getAttrib())))
                    {
                        iop.accept(this);
                        if (i != (v.getIdList().size() - 1))
                        {
                            SymbolVar sym = SymbolTable.cercaVar(v.getIdList().get(i+1).getAttrib());
                            if(sym!=null)
                            {
                                if(lastTipo.equals(sym.getTipo()))
                                {
                                    fw.append(", ");
                                }
                                else
                                {
                                    isVarDeclString = false;
                                    fw.append(";\n");
                                    switch (sym.getTipo())
                                    {
                                        case "string" ->
                                        {
                                            fw.append("char");
                                            isVarDeclString = true;
                                        }
                                        case "integer" -> fw.append("int");
                                        case "float" -> fw.append("float");
                                        case "char" -> fw.append("char");
                                        case "bool" -> fw.append("int");
                                    }
                                    fw.append(" ");
                                    lastTipo=sym.getTipo();
                                }
                            }
                            else
                            {
                                fw.append(";\n");
                            }
                        }
                        else
                        {
                            fw.append(";\n ");
                        }
                    }
                }
                else
                {
                    isFunCallAssigned = iop.getExpr() instanceof FunCallOp;
                    iop.accept(this);
                    if (i != (v.getIdList().size() - 1))
                    {
                        SymbolVar sym = SymbolTable.cercaVar(v.getIdList().get(i+1).getAttrib());
                        if(sym!=null)
                        {
                            if(lastTipo.equals(sym.getTipo()))
                            {
                                fw.append(", ");
                            }
                            else
                            {
                                isVarDeclString = false;
                                fw.append(";\n");
                                stampaTab();
                                switch (sym.getTipo())
                                {
                                    case "string" ->
                                    {
                                        fw.append("char");
                                        isVarDeclString = true;
                                    }
                                    case "integer" -> fw.append("int");
                                    case "float" -> fw.append("float");
                                    case "char" -> fw.append("char");
                                    case "bool" -> fw.append("int");
                                }
                                fw.append(" ");
                                lastTipo=sym.getTipo();
                            }
                        }
                        else
                        {
                            fw.append(";\n");
                        }
                    }
                    else
                    {
                        fw.append(";\n ");
                    }
                }
                SymbolTable.addDeclaredVar(iop.getId().getAttrib());
            }
        }
        isVarDeclString = false;
        return null;
    }

    @Override
    public Object visit(TypeSetter v) throws Exception {
        return null;
    }

    @Override
    public Object visit(FunDeclOp v) throws Exception {

        SymbolTable.moveToNuovoScope();

        tipoFunzione = SymbolTable.cercaFunc(v.getId().getAttrib()).getReturnType();

        stampaTab();
        switch (tipoFunzione) {
            case "integer" -> fw.append("int");
            case "bool" -> fw.append("int");
            case "string" -> fw.append("char*");
            case "char" -> fw.append("char");
            case "float" -> fw.append("float");
            case "void" -> fw.append("void");
        }

        fw.append(" ");

        v.getId().accept(this);

        fw.append("(");
        for (int i = 0; i < v.getParDeclList().size(); i++) {
            v.getParDeclList().get(i).accept(this);
            if (i != (v.getParDeclList().size() - 1)) {
                fw.write(",");
            }
        }
        fw.append(")\n");
        v.getBody().accept(this);


        SymbolTable.moveToScopeSuperiore();

        return null;
    }

    @Override
    public Object visit(IdInitOp v) throws Exception
    {

        v.getId().accept(this);
        if (isVarDeclString)
        {
            fw.append("[1024]");
        }

        if (v.getExpr() != null)
        {
            fw.append(" = ");
            v.getExpr().accept(this);
        }

        return null;
    }

    //entrata nella funzione main ( da gestire per la generazione del codice c)
    @Override
    public Object visit(MainFunDeclOp v) throws Exception {

        v.getFunDeclOp().accept(this);

        fw.append("\n");
        fw.append("int main()\n{\n");
        tab++;
        stampaTab();
        fw.append(Lexer.stringTable.get(v.getFunDeclOp().getId().getAttrib()));
        fw.append("();\n");
        tab--;
        stampaTab();
        fw.append("}\n");

        return null;
    }

    @Override
    public Object visit(ParDeclOp v) throws Exception {

        for (int i = 0; i < v.getIdList().size(); i++) {

            if (v.getType().equals("string")) {
                fw.append("char *");
            } else {
                switch (v.getType()) {
                    case "integer" -> fw.append("int");
                    case "char" -> fw.append("char");
                    case "float" -> fw.append("float");
                    case "void" -> fw.append("void");
                }
                fw.append(" ");
            }
            if(isOutParam){
                fw.append("*");
            }
            IdentifierOrInit id = v.getIdList().get(i);

            id.accept(this);
            if (i != (v.getIdList().size() - 1)) {
                fw.append(", ");
            }

        }
        isOutParam = false;
        return null;
    }

    @Override
    public Object visit(ProgramOp v) throws Exception {


        inzializeWritingFile();
        fw.write("#include <stdio.h>\n");
        fw.write("#include <stdlib.h>\n");
        fw.write("#include <string.h>\n");
        fw.write("#include <math.h>\n");
        fw.write("\n");


        for (DeclOp dop : v.getDecList1()) {

            if (dop instanceof VarDeclOp vdop) {
                vdop.accept(this);
            }

            fw.write("\n");
        }
        for (DeclOp dop : v.getDecList2()) {

            if (dop instanceof VarDeclOp vdop) {
                vdop.accept(this);
            }

            fw.write("\n");
        }

        for (DeclOp dop : v.getDecList2()) {

            if (dop instanceof FunDeclOp) {
                FunDeclOp fd = (FunDeclOp) dop;
                tipoFunzione = fd.getTypeOrVoid();

                stampaTab();
                switch (tipoFunzione) {
                    case "integer" -> fw.append("int");
                    case "bool" -> fw.append("int");
                    case "string" -> fw.append("char*");
                    case "char" -> fw.append("char");
                    case "float" -> fw.append("float");
                    case "void" -> fw.append("void");
                }

                fw.append(" ");

                fd.getId().accept(this);

                fw.append("(");
                for (int i = 0; i < fd.getParDeclList().size(); i++) {
                    if(fd.getParDeclList().get(i).getOut()!=null){
                        isOutParam = true;
                    }
                    else {
                        isOutParam = false;
                    }
                    fd.getParDeclList().get(i).accept(this);
                    if (i != (fd.getParDeclList().size() - 1)) {
                        fw.write(",");
                    }
                }
                fw.append(");\n");
            }

            fw.write("\n");
        }


        for (DeclOp dop : v.getDecList1()) {
            if (dop instanceof FunDeclOp) {
                dop.accept(this);
                fw.append("\n");
            }
        }


        v.getMain().accept(this);


        for (DeclOp dop : v.getDecList2()) {
            if (dop instanceof FunDeclOp) {
                dop.accept(this);
                fw.append("\n");
            }
        }


        SymbolTable.moveToScopeSuperiore();
        try {
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void stampaTab() {
        for (int i = 0; i < tab; i++) {
            try {
                fw.append("\t");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
