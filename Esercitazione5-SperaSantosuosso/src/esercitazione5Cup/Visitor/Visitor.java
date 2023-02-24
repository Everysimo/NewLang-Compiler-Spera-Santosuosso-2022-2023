package esercitazione5Cup.Visitor;

import esercitazione5Cup.GrammarClasses.*;
import esercitazione5Cup.GrammarClasses.Leaf.*;
import esercitazione5Cup.GrammarClasses.Operations.BinariePlus.*;
import esercitazione5Cup.GrammarClasses.Operations.Unarie.NotOp;
import esercitazione5Cup.GrammarClasses.Operations.Unarie.UMinusOp;
import esercitazione5Cup.GrammarClasses.Stats.*;
import esercitazione5Cup.TabellaSimboli.SymbolTable;

public interface Visitor {

    public Object visit(ReturnOp v) throws Exception;
    public Object visit(IntegerConst ic) throws Exception;
    public Object visit(RealConst rc) throws Exception;
    public Object visit(BooleanConst bc) throws Exception;
    public Object visit(Identifier id) throws Exception;
    public Object visit(StringConst sc) throws Exception;
    public Object visit(CharConst cc) throws Exception;

    public Object visit(AndOp v) throws Exception;

    public Object visit(DivOp v) throws Exception;

    public Object visit(EqOp v) throws Exception;

    public Object visit(GeOp v) throws Exception;

    public Object visit(GtOp v) throws Exception;

    public Object visit(LeOp v) throws Exception;

    public Object visit(LtOp v) throws Exception;

    public Object visit(MinusOp v) throws Exception;

    public Object visit(NeOp v) throws Exception;

    public Object visit(OrOp v) throws Exception;

    public Object visit(PlusOp v) throws Exception;

    public Object visit(PowOp v) throws Exception;

    public Object visit(StrCatOp v) throws Exception;

    public Object visit(TimesOp v) throws Exception;

    public Object visit(NotOp v) throws Exception;

    public Object visit(UMinusOp v) throws Exception;

    public Object visit(AssignStatOp v) throws Exception;
    public Object visit(ForStatOp v) throws Exception;
    public Object visit(FunCallOp v) throws Exception;
    public Object visit(IfStatOp v) throws Exception;
    public Object visit(ReadStatOp v) throws Exception;
    public Object visit(WhileStatOp v) throws Exception;
    public Object visit(WriteStatOp v) throws Exception;


    public Object visit(BodyOp v) throws Exception;

    public Object visit(FunDeclOp v) throws Exception;

    public Object visit(IdInitOp v) throws Exception;

    public Object visit(MainFunDeclOp v) throws Exception;

    public Object visit(ParDeclOp v) throws Exception;

    public Object visit(ProgramOp v) throws Exception;

    public Object visit(VarDeclOp v) throws Exception;

    Object visit(TypeSetter v) throws Exception;
}
