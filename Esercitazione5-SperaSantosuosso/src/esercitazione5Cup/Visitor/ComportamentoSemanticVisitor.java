package esercitazione5Cup.Visitor;

import esercitazione5Cup.GrammarClasses.*;
import esercitazione5Cup.GrammarClasses.Leaf.*;
import esercitazione5Cup.GrammarClasses.Operations.BinariePlus.*;
import esercitazione5Cup.GrammarClasses.Operations.Unarie.NotOp;
import esercitazione5Cup.GrammarClasses.Operations.Unarie.UMinusOp;
import esercitazione5Cup.GrammarClasses.Stats.*;
import esercitazione5Cup.Lexer;
import esercitazione5Cup.TabellaSimboli.SymbolTable;

public class ComportamentoSemanticVisitor implements Visitor
{

    String tipoFunzione;
    Boolean risultatoTrovato = false;

    @Override
    public Object visit(ReturnOp v) throws Exception
    {
        if (v.getExpr() != null)
        {
            v.getExpr().accept(this);
        }
        risultatoTrovato = true;
        return null;
    }

    @Override
    public Object visit(IntegerConst ic) throws Exception
    {
        return null;
    }

    @Override
    public Object visit(RealConst rc) throws Exception
    {
        return null;
    }

    @Override
    public Object visit(BooleanConst bc) throws Exception
    {
        return null;
    }

    @Override
    public Object visit(Identifier id) throws Exception
    {
        return null;
    }

    @Override
    public Object visit(StringConst sc) throws Exception
    {
        return "string";
    }

    @Override
    public Object visit(CharConst cc) throws Exception
    {
        return cc.getType();
    }

    @Override
    public Object visit(AndOp v) throws Exception
    {
        v.getRef1().accept(this);
        v.getRef2().accept(this);
        return null;
    }

    @Override
    public Object visit(DivOp v) throws Exception
    {
        v.getRef1().accept(this);
        v.getRef2().accept(this);
        return null;
    }

    @Override
    public Object visit(EqOp v) throws Exception
    {
        v.getRef1().accept(this);
        v.getRef2().accept(this);
        return null;
    }

    @Override
    public Object visit(GeOp v) throws Exception
    {
        v.getRef1().accept(this);
        v.getRef2().accept(this);
        return null;
    }

    @Override
    public Object visit(GtOp v) throws Exception
    {
        v.getRef1().accept(this);
        v.getRef2().accept(this);
        return null;
    }

    @Override
    public Object visit(LeOp v) throws Exception
    {
        v.getRef1().accept(this);
        v.getRef2().accept(this);
        return null;
    }

    @Override
    public Object visit(LtOp v) throws Exception
    {
        v.getRef1().accept(this);
        v.getRef2().accept(this);
        return null;
    }

    @Override
    public Object visit(MinusOp v) throws Exception
    {
        v.getRef1().accept(this);
        v.getRef2().accept(this);
        return null;
    }

    @Override
    public Object visit(NeOp v) throws Exception
    {
        v.getRef1().accept(this);
        v.getRef2().accept(this);
        return null;
    }

    @Override
    public Object visit(OrOp v) throws Exception
    {
        v.getRef1().accept(this);
        v.getRef2().accept(this);
        return null;
    }

    @Override
    public Object visit(PlusOp v) throws Exception
    {
        v.getRef1().accept(this);
        v.getRef2().accept(this);
        return null;
    }

    @Override
    public Object visit(PowOp v) throws Exception
    {
        v.getRef1().accept(this);
        v.getRef2().accept(this);
        return null;
    }

    @Override
    public Object visit(StrCatOp v) throws Exception
    {
        v.getRef1().accept(this);
        v.getRef2().accept(this);
        return null;
    }


    @Override
    public Object visit(TimesOp v) throws Exception
    {
        v.getRef1().accept(this);
        v.getRef2().accept(this);
        return null;
    }

    @Override
    public Object visit(NotOp v) throws Exception
    {
        v.getRef().accept(this);
        return null;
    }

    @Override
    public Object visit(UMinusOp v) throws Exception
    {
        v.getRef().accept(this);
        return null;
    }

    @Override
    public Object visit(AssignStatOp v) throws Exception
    {
        if (v.getIdList().size() != v.getExprList().size())
        {
            throw new SymbolTable.ErroreGenerico(v.getLine(), "Il numero di identificatori non Ã¨ uguale al numero di espressioni");
        }
        else
        {
            for (int i = 0; i < v.getIdList().size(); i++)
            {
                v.getIdList().get(i).accept(this);
                v.getExprList().get(i).accept(this);
            }
            return null;
        }
    }

    @Override
    public Object visit(ForStatOp v) throws Exception
    {
        SymbolTable.pushNuovoScope();
        v.getIdInit().accept(this);
        SymbolTable.addVar(v.getIdInit().getId().getAttrib(), "integer",v.getLine());
        v.getIntegerConst().accept(this);

        v.getBody().accept(this);

        SymbolTable.popScopeOnExit();
        return null;
    }

    @Override
    public Object visit(FunCallOp v) throws Exception
    {
        v.getId().accept(this);
        if(v.getExprList() != null)
        {
            for(Expr e : v.getExprList())
            {
                e.accept(this);
            }
        }

        return null;
    }

    @Override
    public Object visit(IfStatOp v) throws Exception
    {
        v.getExpr().accept(this);
        SymbolTable.pushNuovoScope();

        v.getBodyOp1().accept(this);

        SymbolTable.popScopeOnExit();

        if (v.getBodyOp2().getVerDecls() != null || v.getBodyOp2().getStats() != null)
        {
            SymbolTable.pushNuovoScope();

            v.getBodyOp2().accept(this);

            SymbolTable.popScopeOnExit();
        }
        return null;
    }

    @Override
    public Object visit(ReadStatOp v) throws Exception
    {

        for (IdentifierOrInit i : v.getIdList())
        {
            i.accept(this);
        }
        if (v.getString_const() != null)
        {
            v.getString_const().accept(this);
        }


        return null;
    }

    @Override
    public Object visit(WhileStatOp v) throws Exception
    {
        v.getExpr().accept(this);
        SymbolTable.pushNuovoScope();

        v.getBody().accept(this);

        SymbolTable.popScopeOnExit();
        return null;
    }

    @Override
    public Object visit(WriteStatOp v) throws Exception
    {
        for (Expr e : v.getExprList())
        {
            e.accept(this);
        }
        v.getTypeWrite().accept(this);

        return null;
    }

    @Override
    public Object visit(BodyOp v) throws Exception
    {
        if (v.getVerDecls() != null)
        {
            for (VarDeclOp vdo : v.getVerDecls())
            {
                if (vdo != null)
                    vdo.accept(this);
            }
        }

        if (v.getStats() != null)
        {
            for (Stat s : v.getStats())
            {
                if (s != null)
                    s.accept(this);
            }
        }

        return null;
    }

    @Override
    public Object visit(VarDeclOp v) throws Exception
    {
        for (int i = 0; i < v.getIdList().size(); i++)
        {
            IdInitOp io = (IdInitOp) v.getIdList().get(i);
            if (io.getExpr() != null)
            {
                if (v.getType().getType().equals("var"))
                {
                    Expr e = io.getExpr();
                    if (e instanceof StringConst)
                    {
                        v.setType(new TypeSetter("string"));
                    }
                    if (e instanceof IntegerConst)
                    {
                        v.setType(new TypeSetter("integer"));
                    }
                    if (e instanceof RealConst)
                    {
                        v.setType(new TypeSetter("float"));
                    }
                    if (e instanceof CharConst)
                    {
                        v.setType(new TypeSetter("char"));
                    }

                    if (e instanceof BooleanConst)
                    {
                        v.setType(new TypeSetter("bool"));
                    }
                    SymbolTable.addVar(io.getId().getAttrib(), v.getType().getType(), io.getId().getLine());
                    io.accept(this);
                    v.setType(new TypeSetter("var"));
                }
                else
                {
                    IdentifierOrInit id = v.getIdList().get(i);
                    if (id instanceof IdInitOp idInit)
                    {
                        SymbolTable.addVar(idInit.getId().getAttrib(), v.getType().getType(), v.getLine());
                        idInit.accept(this);
                    }
                    else
                    {
                        SymbolTable.addVar(id.getAttrib(), v.getType().getType(), id.getLine());
                        id.accept(this);
                    }
                }
            }
            else
            {
                IdentifierOrInit id = v.getIdList().get(i);
                SymbolTable.addVar(id.getAttrib(), v.getType().getType(), id.getLine());
                id.accept(this);
            }
        }
        return null;
    }

    @Override
    public Object visit(TypeSetter v) throws Exception
    {
        return null;
    }

    @Override
    public Object visit(FunDeclOp v) throws Exception
    {
        SymbolTable.addFunc(v.getId().getAttrib(), v.getParDeclList(), v.getTypeOrVoid(), v.getId().getLine());
        SymbolTable.pushNuovoScope();
        tipoFunzione = v.getTypeOrVoid();
        v.getId().accept(this);

        if(v.getTypeOrVoid().equals("void"))
        {
            risultatoTrovato=true;
        }

        for (ParDeclOp pdo : v.getParDeclList())
        {
            pdo.accept(this);
        }

        v.getBody().accept(this);

        if (risultatoTrovato)
        {
            risultatoTrovato = false;
        }
        else
        {
            throw new SymbolTable.ReturnMancante(v.getLine(), Lexer.stringTable.get(v.getId().getAttrib()));
        }

        SymbolTable.popScopeOnExit();
        return null;
    }

    @Override
    public Object visit(IdInitOp v) throws Exception
    {

        v.getId().accept(this);


        if (v.getExpr() != null)
        {
            v.getExpr().accept(this);
        }

        return null;
    }

    //entrata nella funzione main ( da gestire per la generazione del codice c)
    @Override
    public Object visit(MainFunDeclOp v) throws Exception
    {

        if(v.getFunDeclOp().getParDeclList().size() > 0)
        {
            throw new SymbolTable.MainNonDeveAvereParametri(v.getFunDeclOp()
                    .getLine()
                    ,v.getFunDeclOp().getId().getAttrib());
        }

        v.getFunDeclOp().accept(this);


        return null;
    }

    @Override
    public Object visit(ParDeclOp v) throws Exception
    {
        boolean isOut = v.getOut() != null;
        for (IdentifierOrInit i : v.getIdList())
        {
            SymbolTable.addVar(i.getAttrib(), v.getType(), i.getLine(),isOut);
            i.accept(this);
        }

        return null;
    }

    @Override
    public Object visit(ProgramOp v) throws Exception
    {
        SymbolTable.pushNuovoScope();

        for (DeclOp dop : v.getDecList1())
        {
            dop.accept(this);
        }

        v.getMain().accept(this);

        for (DeclOp dop : v.getDecList2())
        {
            dop.accept(this);
        }

        SymbolTable.popScopeOnExit();
        return null;
    }

}
