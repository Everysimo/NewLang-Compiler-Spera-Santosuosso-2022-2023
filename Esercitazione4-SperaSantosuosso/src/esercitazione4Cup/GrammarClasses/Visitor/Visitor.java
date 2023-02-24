package esercitazione4Cup.GrammarClasses.Visitor;

import esercitazione4Cup.GrammarClasses.*;
import esercitazione4Cup.GrammarClasses.Leaf.*;
import esercitazione4Cup.GrammarClasses.Operations.BinariePlus.*;
import esercitazione4Cup.GrammarClasses.Operations.Unarie.NotOp;
import esercitazione4Cup.GrammarClasses.Operations.Unarie.UMinusOp;
import esercitazione4Cup.GrammarClasses.Stats.*;

public interface Visitor {

    public Object visit(IntegerConst ic);
    public Object visit(RealConst rc);
    public Object visit(BooleanConst bc);
    public Object visit(Identifier id);
    public Object visit(StringConst sc);
    public Object visit(CharConst cc);

    public Object visit(AndOp v);

    public Object visit(DivOp v);

    public Object visit(EqOp v);

    public Object visit(GeOp v);

    public Object visit(GtOp v);

    public Object visit(LeOp v);

    public Object visit(LtOp v);

    public Object visit(MinusOp v);

    public Object visit(NeOp v);

    public Object visit(OrOp v);

    public Object visit(PlusOp v);

    public Object visit(PowOp v);

    public Object visit(StrCatOp v);

    public Object visit(TimesOp v);

    public Object visit(NotOp v);

    public Object visit(UMinusOp v);

    public Object visit(AssignStatOp v);
    public Object visit(ForStatOp v);
    public Object visit(FunCallOp v);
    public Object visit(IfStatOp v);
    public Object visit(ReadStatOp v);
    public Object visit(WhileStatOp v);
    public Object visit(WriteStatOp v);


    public Object visit(BodyOp v);

    public Object visit(FunDeclOp v);

    public Object visit(IdInitOp v);

    public Object visit(MainFunDeclOp v);

    public Object visit(ParDeclOp v);

    public Object visit(ProgramOp v);

    public Object visit(VarDeclOp v);

}
