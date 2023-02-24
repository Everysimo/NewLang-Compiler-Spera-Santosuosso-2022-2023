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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ComportamentoTypeCheckingVisitor implements Visitor {

    String tipoFunzione;
    Boolean risultatoTrovato = false;

    boolean isStatPrintf, isOpCalled = false;


    int tab = 0;

    boolean isFunCallAssigned = false;
    boolean isVarDeclString = false;



    public ComportamentoTypeCheckingVisitor() {

    }


    @Override
    public Object visit(ReturnOp v) throws Exception {
        String returnType = "void";
        if (v.getExpr() != null) {
            Expr e = v.getExpr();
            returnType = (String) v.getExpr().accept(this);

        }
        if (returnType != tipoFunzione) {
            throw new SymbolTable.ReturnNonCorrispondente(v.getLine(), tipoFunzione, returnType);
        }
        risultatoTrovato = true;
        return null;
    }

    @Override
    public Object visit(IntegerConst ic) throws Exception {
        if(!ic.getType().getType().equals("integer")){
            throw new SymbolTable.TipoNonCorrispondente(ic.getLine(), ic.getType().getType(),"integer",String.valueOf(ic.getAttrib()));
        }
        return ic.getType().getType();
    }

    @Override
    public Object visit(RealConst rc) throws Exception {
        if(!rc.getType().getType().equals("float")){
            throw new SymbolTable.TipoNonCorrispondente(rc.getLine(), rc.getType().getType(),"float", String.valueOf(rc.getAttrib()));
        }
        return rc.getType().getType();
    }

    @Override
    public Object visit(BooleanConst bc) throws Exception {
        if(!bc.getType().getType().equals("bool")){
            throw new SymbolTable.TipoNonCorrispondente(bc.getLine(), bc.getType().getType(),"bool", String.valueOf(bc.getAttrib()));
        }
        return bc.getType().getType();
    }

    @Override
    public Object visit(Identifier id) throws Exception {
        String lessama = Lexer.stringTable.get(id.getAttrib());
        SymbolVar var = SymbolTable.cercaVar(id.getAttrib());

        if (SymbolTable.cercaVar(id.getAttrib()) != null) {
            return SymbolTable.cercaVar(id.getAttrib()).getTipo();
        } else if (SymbolTable.cercaFunc(id.getAttrib()) != null) {
            return SymbolTable.cercaFunc(id.getAttrib()).getReturnType();
        } else {
            throw new SymbolTable.VarNonDefinita(id.getLine(), lessama);
        }
    }

    @Override
    public Object visit(StringConst sc) throws Exception {
        if(!sc.getType().getType().equals("string")){
            throw new SymbolTable.TipoNonCorrispondente(sc.getLine(), "string", sc.getType().getType(),sc.getAttrib());
        }

        return sc.getType().getType();
    }

    @Override
    public Object visit(CharConst cc) throws Exception {
        if(!cc.getType().getType().equals("char")){
            throw new SymbolTable.TipoNonCorrispondente(cc.getLine(), "char", cc.getType().getType(),cc.getAttrib());
        }

        return cc.getType().getType();
    }

    @Override
    public Object visit(AndOp v) throws Exception {

        String tipo1 = (String) v.getRef1().accept(this);
        String tipo2 = (String) v.getRef2().accept(this);

        if (tipo1.equals("bool") && tipo2.equals("bool")) {
            v.setType(new TypeSetter("bool"));
        } else {
            throw new SymbolTable.OperazioneNonDefinita(v.getLine(), "AndOp", tipo1, tipo2);
        }
        return v.getType().getType();
    }

    @Override
    public Object visit(DivOp v) throws Exception {

        String tipo1 = (String) v.getRef1().accept(this);
        String tipo2 = (String) v.getRef2().accept(this);
        if (tipo1.equals("integer") && tipo2.equals("integer")) {
            v.setType(new TypeSetter("integer"));
        } else if (tipo1.equals("integer") && tipo2.equals("float")) {
            v.getRef1().setType(new TypeSetter("float"));
            v.setType(new TypeSetter("float"));
        } else if (tipo1.equals("float") && tipo2.equals("integer")) {
            v.getRef2().setType(new TypeSetter("float"));
            v.setType(new TypeSetter("float"));
        } else if (tipo1.equals("float") && tipo2.equals("float")) {
            v.setType(new TypeSetter("float"));
        } else {
            throw new SymbolTable.OperazioneNonDefinita(v.getLine(), "DivOp", tipo1, tipo2);
        }
        return v.getType().getType();
    }

    @Override
    public Object visit(EqOp v) throws Exception {

        String tipo1 = (String) v.getRef1().accept(this);
        String tipo2 = (String) v.getRef2().accept(this);

        if (tipo1.equals("integer") && tipo2.equals("integer")) {
            v.setType(new TypeSetter("bool"));
        } else if (tipo1.equals("integer") && tipo2.equals("float")) {
            v.setType(new TypeSetter("bool"));
        } else if (tipo1.equals("float") && tipo2.equals("integer")) {
            v.setType(new TypeSetter("bool"));
        } else if (tipo1.equals("float") && tipo2.equals("float")) {
            v.setType(new TypeSetter("bool"));
        } else if (tipo1.equals("string") && tipo2.equals("string")) {
            v.setType(new TypeSetter("bool"));
        } else {
            throw new SymbolTable.OperazioneNonDefinita(v.getLine(), "EqOp", tipo1, tipo2);
        }
        return v.getType().getType();

    }

    @Override
    public Object visit(GeOp v) throws Exception {

        String tipo1 = (String) v.getRef1().accept(this);
        String tipo2 = (String) v.getRef2().accept(this);

        if (tipo1.equals("integer") && tipo2.equals("integer")) {
            v.setType(new TypeSetter("bool"));
        } else if (tipo1.equals("integer") && tipo2.equals("float")) {
            v.setType(new TypeSetter("bool"));
        } else if (tipo1.equals("float") && tipo2.equals("integer")) {
            v.setType(new TypeSetter("bool"));
        } else if (tipo1.equals("float") && tipo2.equals("float")) {
            v.setType(new TypeSetter("bool"));
        } else {
            throw new SymbolTable.OperazioneNonDefinita(v.getLine(), "GeOp", tipo1, tipo2);
        }
        return v.getType().getType();
    }

    @Override
    public Object visit(GtOp v) throws Exception {

        String tipo1 = (String) v.getRef1().accept(this);
        String tipo2 = (String) v.getRef2().accept(this);

        if (tipo1.equals("integer") && tipo2.equals("integer")) {
            v.setType(new TypeSetter("bool"));
        } else if (tipo1.equals("integer") && tipo2.equals("float")) {
            v.setType(new TypeSetter("bool"));
        } else if (tipo1.equals("float") && tipo2.equals("integer")) {
            v.setType(new TypeSetter("bool"));
        } else if (tipo1.equals("float") && tipo2.equals("float")) {
            v.setType(new TypeSetter("bool"));
        } else {
            throw new SymbolTable.OperazioneNonDefinita(v.getLine(), "GtOp", tipo1, tipo2);
        }
        return v.getType().getType();
    }

    @Override
    public Object visit(LeOp v) throws Exception {

        String tipo1 = (String) v.getRef1().accept(this);
        String tipo2 = (String) v.getRef2().accept(this);

        if (tipo1.equals("integer") && tipo2.equals("integer")) {
            v.setType(new TypeSetter("bool"));
        } else if (tipo1.equals("integer") && tipo2.equals("float")) {
            v.setType(new TypeSetter("bool"));
        } else if (tipo1.equals("float") && tipo2.equals("integer")) {
            v.setType(new TypeSetter("bool"));
        } else if (tipo1.equals("float") && tipo2.equals("float")) {
            v.setType(new TypeSetter("bool"));
        } else {
            throw new SymbolTable.OperazioneNonDefinita(v.getLine(), "LeOp", tipo1, tipo2);
        }
        return v.getType().getType();
    }

    @Override
    public Object visit(LtOp v) throws Exception {

        String tipo1 = (String) v.getRef1().accept(this);
        String tipo2 = (String) v.getRef2().accept(this);

        if (tipo1.equals("integer") && tipo2.equals("integer")) {
            v.setType(new TypeSetter("bool"));
        } else if (tipo1.equals("integer") && tipo2.equals("float")) {
            v.setType(new TypeSetter("bool"));
        } else if (tipo1.equals("float") && tipo2.equals("integer")) {
            v.setType(new TypeSetter("bool"));
        } else if (tipo1.equals("float") && tipo2.equals("float")) {
            v.setType(new TypeSetter("bool"));
        } else {
            throw new SymbolTable.OperazioneNonDefinita(v.getLine(), "LtOp", tipo1, tipo2);
        }
        return v.getType().getType();
    }

    @Override
    public Object visit(MinusOp v) throws Exception {

        String tipo1 = (String) v.getRef1().accept(this);
        String tipo2 = (String) v.getRef2().accept(this);
        if (tipo1.equals("integer") && tipo2.equals("integer")) {
            v.setType(new TypeSetter("integer"));
        } else if (tipo1.equals("integer") && tipo2.equals("float")) {
            v.getRef1().setType(new TypeSetter("float"));
            v.setType(new TypeSetter("float"));
        } else if (tipo1.equals("float") && tipo2.equals("integer")) {
            v.getRef2().setType(new TypeSetter("float"));
            v.setType(new TypeSetter("float"));
        } else if (tipo1.equals("float") && tipo2.equals("float")) {
            v.setType(new TypeSetter("float"));
        } else {
            throw new SymbolTable.OperazioneNonDefinita(v.getLine(), "MinusOp", tipo1, tipo2);
        }
        return v.getType().getType();
    }

    @Override
    public Object visit(NeOp v) throws Exception {

        String tipo1 = (String) v.getRef1().accept(this);
        String tipo2 = (String) v.getRef2().accept(this);

        if (tipo1.equals("integer") && tipo2.equals("integer")) {
            v.setType(new TypeSetter("bool"));
        } else if (tipo1.equals("integer") && tipo2.equals("float")) {
            v.setType(new TypeSetter("bool"));
        } else if (tipo1.equals("float") && tipo2.equals("integer")) {
            v.setType(new TypeSetter("bool"));
        } else if (tipo1.equals("float") && tipo2.equals("float")) {
            v.setType(new TypeSetter("bool"));
        } else if (tipo1.equals("string") && tipo2.equals("string")) {
            v.setType(new TypeSetter("bool"));
        } else {
            throw new SymbolTable.OperazioneNonDefinita(v.getLine(), "NeOp", tipo1, tipo2);
        }
        return v.getType().getType();
    }

    @Override
    public Object visit(OrOp v) throws Exception {

        String tipo1 = (String) v.getRef1().accept(this);
        String tipo2 = (String) v.getRef2().accept(this);

        if (tipo1.equals("bool") && tipo2.equals("bool")) {
            v.setType(new TypeSetter("bool"));
        } else {
            throw new SymbolTable.OperazioneNonDefinita(v.getLine(), "OrOp", tipo1, tipo2);
        }
        return v.getType().getType();
    }

    @Override
    public Object visit(PlusOp v) throws Exception {

        String tipo1 = (String) v.getRef1().accept(this);
        String tipo2 = (String) v.getRef2().accept(this);
        if (tipo1.equals("integer") && tipo2.equals("integer")) {
            v.setType(new TypeSetter("integer"));
        } else if (tipo1.equals("integer") && tipo2.equals("float")) {
            v.getRef1().setType(new TypeSetter("float"));
            v.setType(new TypeSetter("float"));
        } else if (tipo1.equals("float") && tipo2.equals("integer")) {
            v.getRef2().setType(new TypeSetter("float"));
            v.setType(new TypeSetter("float"));
        } else if (tipo1.equals("float") && tipo2.equals("float")) {
            v.setType(new TypeSetter("float"));
        } else {
            throw new SymbolTable.OperazioneNonDefinita(v.getLine(), "PlusOp", tipo1, tipo2);
        }
        return v.getType().getType();
    }

    @Override
    public Object visit(PowOp v) throws Exception {
        String tipo1 = (String) v.getRef1().accept(this);
        String tipo2 = (String) v.getRef2().accept(this);
        if (tipo1.equals("integer") && tipo2.equals("integer")) {
            v.setType(new TypeSetter("integer"));
        } else if (tipo1.equals("integer") && tipo2.equals("float")) {
            v.getRef1().setType(new TypeSetter("float"));
            v.setType(new TypeSetter("float"));
        } else if (tipo1.equals("float") && tipo2.equals("integer")) {
            v.getRef2().setType(new TypeSetter("float"));
            v.setType(new TypeSetter("float"));
        } else if (tipo1.equals("float") && tipo2.equals("float")) {
            v.setType(new TypeSetter("float"));
        } else {
            throw new SymbolTable.OperazioneNonDefinita(v.getLine(), "PowOp", tipo1, tipo2);
        }
        return v.getType().getType();
    }

    @Override
    public Object visit(StrCatOp v) throws Exception {
        isStatPrintf = false;
        if (v.getRef1() instanceof Identifier) {
            Identifier id = (Identifier) v.getRef1();
            if (SymbolTable.cercaVar(id.getAttrib()).getTipo().equals("string")) {
                v.getRef1().accept(this);
                v.getRef2().accept(this);
                SymbolVar sym = SymbolTable.cercaVar(id.getAttrib());
                if (!(sym.getTipo().equals("string"))) {
                    throw new SymbolTable.StrCatSiAspettaParametriStringa(v.getRef1().getLine(),1,"string",sym.getTipo());
                }
                if (!(v.getRef2().getType().getType().equals("string"))) {
                    throw new SymbolTable.StrCatSiAspettaParametriStringa(v.getRef2().getLine(),2,"string",v.getRef2().getType().getType());
                }
                v.setType(new TypeSetter("string"));
            }
        } else throw new SymbolTable.StrCatInCSiAspettaUnaVariabileComePrimoArgomento(v.getLine());

        return null;
    }


    @Override
    public Object visit(TimesOp v) throws Exception {

        String tipo1 = (String) v.getRef1().accept(this);
        String tipo2 = (String) v.getRef2().accept(this);
        if (tipo1.equals("integer") && tipo2.equals("integer")) {
            v.setType(new TypeSetter("integer"));
        } else if (tipo1.equals("integer") && tipo2.equals("float")) {
            v.getRef1().setType(new TypeSetter("float"));
            v.setType(new TypeSetter("float"));
        } else if (tipo1.equals("float") && tipo2.equals("integer")) {
            v.getRef2().setType(new TypeSetter("float"));
            v.setType(new TypeSetter("float"));
        } else if (tipo1.equals("float") && tipo2.equals("float")) {
            v.setType(new TypeSetter("float"));
        } else {
            throw new SymbolTable.OperazioneNonDefinita(v.getLine(), "TimesOp", tipo1, tipo2);
        }
        return v.getType().getType();
    }

    @Override
    public Object visit(NotOp v) throws Exception {
        String tipo = (String) v.getRef().accept(this);
        if (tipo.equals("bool")) {
            v.setType(new TypeSetter(tipo));
            return tipo;
        } else {
            throw new SymbolTable.OperazioneNonDefinita(v.getLine(), "NotOp", tipo);
        }
    }

    @Override
    public Object visit(UMinusOp v) throws Exception {
        String tipo = (String) v.getRef().accept(this);
        if (tipo.equals("integer") || tipo.equals("float")) {
            v.setType(new TypeSetter(tipo));
            return tipo;
        } else {
            throw new SymbolTable.OperazioneNonDefinita(v.getLine(), "UMinusOp", tipo);
        }
    }

    //RIVEDERE SE IL TIPO DI ASSEGNAMENTO VA BENE
    @Override
    public Object visit(AssignStatOp v) throws Exception {
        if (v.getIdList().size() != v.getExprList().size()) {
            throw new SymbolTable.ErroreGenerico(v.getLine(), "Il numero di identificatori non è uguale al numero di espressioni");
        } else {
            for (int i = 0; i < v.getIdList().size(); i++) {
                String tipoIdentifier = (String) v.getIdList().get(i).accept(this);
                if (v.getExprList().get(i) instanceof FunCallOp) {
                    isFunCallAssigned = true;
                } else {
                    isFunCallAssigned = false;
                }
                String tipoExpr = (String) v.getExprList().get(i).accept(this);
                if (!tipoIdentifier.equals(tipoExpr))
                    throw new SymbolTable.TipoNonCorrispondente(v.getExprList().get(i).getLine(), tipoIdentifier, tipoExpr, v.getIdList().get(i).getAttrib());
            }
            return null;
        }
    }

    @Override
    public Object visit(ForStatOp v) throws Exception {

        SymbolTable.moveToNuovoScope();
        SymbolVar item1 = SymbolTable.cercaVar(v.getIdInit().getAttrib());
        if (item1 == null) {
            throw new SymbolTable.VarNonDefinita(v.getLine(), Lexer.stringTable.get(v.getIdInit().getAttrib()));
        }
        String tipo1 = item1.getTipo();
        String tipo2 = v.getIntegerConst().getType().getType();
        if (tipo1.equals("integer") && tipo2.equals("integer")) {
            v.getIdInit().accept(this);
            v.getIntegerConst().accept(this);

            v.getBody().accept(this);

            SymbolTable.moveToScopeSuperiore();
            return null;
        } else {
            throw new SymbolTable.OperazioneNonDefinita(v.getLine(), "ForStatOp", tipo1, tipo2);
        }

    }

    @Override
    public Object visit(FunCallOp v) throws Exception {

        SymbolFunc item1 = SymbolTable.cercaFunc(v.getId().getAttrib());
        if (item1 == null) {
            throw new SymbolTable.FunzioneNonDefinita(v.getLine(), Lexer.stringTable.get(v.getId().getAttrib()));
        }
        String tipo1 = item1.getReturnType();

        /*if(!tipo1.equals(v.getType().getType())){
            throw new SymbolTable.TipoRitornoFunCallNonCorrispondente(v.getLine(),tipo1,v.getType().getType(),SymbolTable.cercaVar(v.getId().getAttrib()).getLessema());
        }*/

        int numPar = 0;
        int k;
        for (ParDeclOp p : item1.getParams()) {

            numPar += p.getIdList().size();
        }

        int size=0;
        if(v.getExprList() != null)
            size=v.getExprList().size();
        if (size != numPar)
        {
            throw new SymbolTable.NumeroParametriFunzioneNonCorrsiponenti(v.getLine(), Lexer.stringTable.get(v.getId().getAttrib()));
        }

        ArrayList<ParDeclOp> params = item1.getParams();
        boolean isOut;

        int j = 0;

        for (k = 0; k < params.size(); k++) {
            ParDeclOp p = params.get(k);
            for (int i = 0; i < p.getIdList().size(); i++) {
                String tipoExpr;
                String tipoParam = p.getType();
                isOut = p.getOut() != null;
                if (isOut) {
                    Identifier id = null;
                    if (v.getExprList().get(j) instanceof Identifier) {
                        id = (Identifier) v.getExprList().get(j);

                    }
                    if (id != null)
                    {
                        if (SymbolTable.cercaVar(id.getAttrib()) != null)
                        {
                            tipoExpr = (String) id.accept(this);
                        }
                        else
                        {
                            throw new SymbolTable.VarNonDefinita(v.getExprList().get(j).getLine(), Lexer.stringTable.get(id.getAttrib()));
                        }
                    }
                    else
                    {
                        throw new SymbolTable.ParametroOutFunzioneSiAspettaVar(v.getExprList().get(j).getLine(), (j + 1), Lexer.stringTable.get(v.getId().getAttrib()));
                    }
                } else {
                    tipoExpr = (String) v.getExprList().get(j).accept(this);
                }
                if (!tipoExpr.equals(tipoParam))
                    throw new SymbolTable.ParametriFunzioneNonCorrsiponenti(v.getExprList().get(j).getLine(), (j + 1), Lexer.stringTable.get(v.getId().getAttrib()));

                j = j + 1;
            }

        }
        return tipo1;
    }

    @Override
    public Object visit(IfStatOp v) throws Exception {
        ;
        String type = (String) v.getExpr().accept(this);


        if (type.equals("bool")) {
            SymbolTable.moveToNuovoScope();

            v.getBodyOp1().accept(this);

            SymbolTable.moveToScopeSuperiore();

            if (v.getBodyOp2().getVerDecls() != null || v.getBodyOp2().getStats() != null) {


                SymbolTable.moveToNuovoScope();

                v.getBodyOp2().accept(this);

                SymbolTable.moveToScopeSuperiore();

            }
            return null;
        } else {
            throw new SymbolTable.OperazioneNonDefinita(v.getLine(), "IfStatOp", type);
        }
    }

    @Override
    public Object visit(ReadStatOp v) throws Exception {
        if (v.getString_const() != null) {
            isStatPrintf = false;
            String tipo = (String) v.getString_const().accept(this);
            if (!tipo.equals("string")) throw new SymbolTable.OperazioneNonDefinita(v.getLine(), "ReadOp", tipo);
        }

        for (int i = 0; i < v.getIdList().size(); i++) {
            IdentifierOrInit id = v.getIdList().get(i);
            SymbolVar symbol = SymbolTable.cercaVar(id.getAttrib());
            if (symbol == null) {
                throw new SymbolTable.VarNonDefinita(id.getLine(), Lexer.stringTable.get(id.getAttrib()));
            }
        }


        return null;
    }

    @Override
    public Object visit(WhileStatOp v) throws Exception {
        String type = (String) v.getExpr().accept(this);

        if (type.equals("bool")) {
            SymbolTable. moveToNuovoScope();

            v.getBody().accept(this);

            SymbolTable.moveToScopeSuperiore();

            return null;
        } else {
            throw new SymbolTable.OperazioneNonDefinita(v.getLine(), "WhileOp", type);
        }
    }

    @Override
    public Object visit(WriteStatOp v) throws Exception {
        for (Expr e : v.getExprList())
        {
            e.accept(this);
        }

        return null;
    }

    @Override
    public Object visit(BodyOp v) throws Exception {

        if (v.getVerDecls() != null) {
            for (VarDeclOp vdo : v.getVerDecls()) {
                if (vdo != null) vdo.accept(this);
            }
        }

        if (v.getStats() != null) {
            for (Stat s : v.getStats()) {
                if (s != null) s.accept(this);
            }
        }
        return null;
    }

    @Override
    public Object visit(VarDeclOp v) throws Exception {
        for (int i = 0; i < v.getIdList().size(); i++) {

            IdInitOp io = (IdInitOp) v.getIdList().get(i);
            if (io.getExpr() != null) {
                if (v.getType().getType().equals("var"))
                {
                    Expr e = io.getExpr();
                    if (e instanceof StringConst) {
                        v.setType(new TypeSetter("string"));
                        isVarDeclString = true;
                    }
                    if (e instanceof IntegerConst) {
                        v.setType(new TypeSetter("integer"));
                    }
                    if (e instanceof RealConst) {
                        v.setType(new TypeSetter("float"));
                    }
                    if (e instanceof CharConst) {
                        v.setType(new TypeSetter("char"));
                    }

                    if (e instanceof BooleanConst) {
                        v.setType(new TypeSetter("bool"));
                    }
                    io.accept(this);

                    v.setType(new TypeSetter("var"));
                } else {
                    IdentifierOrInit id = v.getIdList().get(i);

                    if (v.getType().getType().equals("string")) {
                        isVarDeclString = true;
                    }

                    if (id instanceof IdInitOp) {
                        IdInitOp idInit = (IdInitOp) id;
                        String tipo = (String) idInit.accept(this);
                        if (!v.getType().getType().equals(tipo))
                            throw new SymbolTable.TipoNonCorrispondente(idInit.getLine(), v.getType().getType(), tipo, idInit.getId().getAttrib());

                    } else {
                        id.accept(this);
                    }

                }
            } else {
                if (v.getType().getType().equals("string")) {
                    isVarDeclString = true;
                }
                IdentifierOrInit id = v.getIdList().get(i);
                id.accept(this);
            }

            if (i != (v.getIdList().size() - 1)) {
                IdentifierOrInit id = v.getIdList().get(i);

            }
        }
        return null;
    }

    @Override
    public Object visit(TypeSetter v) throws Exception {
        return null;
    }

    @Override
    public Object visit(FunDeclOp v) throws Exception {

        SymbolTable.moveToNuovoScope();

        tipoFunzione = v.getTypeOrVoid();

        if(tipoFunzione.equals("void"))
        {
            risultatoTrovato=true;
        }
        for (int i = 0; i < v.getParDeclList().size(); i++) {
            v.getParDeclList().get(i).accept(this);
        }
        v.getBody().accept(this);


        if (risultatoTrovato)
        {
            risultatoTrovato = false;
        } else {
            throw new SymbolTable.ReturnMancante(v.getLine(), Lexer.stringTable.get(v.getId().getAttrib()));
        }

        SymbolTable.moveToScopeSuperiore();

        return null;
    }

    @Override
    public Object visit(IdInitOp v) throws Exception {

        v.getId().accept(this);
        SymbolVar sym = SymbolTable.cercaVar(v.getId().getAttrib());
        String tipo = null;
        if (v.getExpr() != null) {
            tipo = (String) v.getExpr().accept(this);
            if(!sym.getTipo().equals(tipo)){
                throw new SymbolTable.TipoNonCorrispondente(v.getLine(),sym.getTipo(),tipo,v.getId().getAttrib());
            }
        }

        return tipo;
    }
    @Override
    public Object visit(MainFunDeclOp v) throws Exception {

        v.getFunDeclOp().accept(this);

        return null;
    }

    @Override
    public Object visit(ParDeclOp v) throws Exception {

        for (int i = 0; i < v.getIdList().size(); i++) {
            IdentifierOrInit id = v.getIdList().get(i);
            id.accept(this);
        }

        return null;
    }

    @Override
    public Object visit(ProgramOp v) throws Exception {
        SymbolTable. moveToNuovoScope();


        for (DeclOp dop : v.getDecList1()) {
            dop.accept(this);
        }

        v.getMain().accept(this);

        for (DeclOp dop : v.getDecList2()) {
            dop.accept(this);
        }

        SymbolTable.moveToScopeSuperiore();

        return null;
    }

}
