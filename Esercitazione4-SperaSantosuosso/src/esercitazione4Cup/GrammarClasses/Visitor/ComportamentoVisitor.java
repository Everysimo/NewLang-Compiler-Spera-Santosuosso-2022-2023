package esercitazione4Cup.GrammarClasses.Visitor;

import esercitazione4Cup.GrammarClasses.*;
import esercitazione4Cup.GrammarClasses.Leaf.*;
import esercitazione4Cup.GrammarClasses.Operations.BinariePlus.*;
import esercitazione4Cup.GrammarClasses.Operations.Unarie.NotOp;
import esercitazione4Cup.GrammarClasses.Operations.Unarie.UMinusOp;
import esercitazione4Cup.GrammarClasses.Stats.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ComportamentoVisitor implements Visitor {

    FileWriter fw;
    int tab = 0;
    public ComportamentoVisitor(){

    }

    private void inzializeWritingFile(){
        try {
            File treeFile = new File("output/ParsingTreeXML.txt");
            if (treeFile.createNewFile()) {
                System.out.println("File Creato: " + treeFile.getName());
            }
            fw = new FileWriter("output/ParsingTreeXML.txt");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public Object visit(IntegerConst ic){
        tab++;
        System.out.println("<Integer val= \""+ic.getAttrib()+"\"/> \n");
        try{
            stampaTab();
            fw.append("<Integer val= \"").append(String.valueOf(ic.getAttrib())).append("\"/> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return ic.getAttrib();
    }

    @Override
    public Object visit(RealConst rc) {
        tab++;
        System.out.println("<RealConst val= \""+rc.getAttrib()+"\"/> \n");
        try{
            stampaTab();
            fw.append("<RealConst val= \"").append(String.valueOf(rc.getAttrib())).append("\"/> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return rc.getAttrib();
    }

    @Override
    public Object visit(BooleanConst bc) {
        tab++;
        System.out.println("<BooleanConst val= \""+bc.getAttrib()+"\"/> \n");
        try{
            stampaTab();
            fw.append("<BooleanConst val= \"").append(String.valueOf(bc.getAttrib())).append("\"/> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return bc.getAttrib();
    }

    @Override
    public Object visit(Identifier id) {
        tab++;
        System.out.println("<Identifier val= \""+id.getAttrib()+"\"/> \n");
        try{
            stampaTab();
            fw.append("<Identifier val= \""+id.getAttrib()+"\"/> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return id.getAttrib();
    }

    @Override
    public Object visit(StringConst sc) {
        tab++;
        System.out.println("<StringConst val= \""+sc.getAttrib()+"\"/> \n");
        try{
            stampaTab();
            fw.append("<StringConst val= \""+sc.getAttrib()+"\"/> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return sc.getAttrib();
    }

    @Override
    public Object visit(CharConst cc) {
        tab++;
        System.out.println("<CharConst val= \""+cc.getAttrib()+"\"/> \n");
        try{
            stampaTab();
            fw.append("<CharConst val= \""+cc.getAttrib()+"\"/> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return cc.getAttrib();
    }

    @Override
    public Object visit(AndOp v) {
        tab++;
        System.out.println("<And> \n");
        try{
            stampaTab();
            fw.append("<And> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getRef1().accept(this);
        v.getRef2().accept(this);
        System.out.println("</And>\n");
        try{
            stampaTab();
            fw.append("</And>\n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return null;
    }

    @Override
    public Object visit(DivOp v) {
        tab++;
        System.out.println("<Div> \n");
        try{
            stampaTab();
            fw.append("<Div> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getRef1().accept(this);
        v.getRef2().accept(this);
        System.out.println("</Div> \n");
        try{
            stampaTab();
            fw.append("</Div> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return null;
    }

    @Override
    public Object visit(EqOp v) {
        tab++;
        System.out.println("<Eq>\n");
        try{
            stampaTab();
            fw.append("<Eq>\n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getRef1().accept(this);
        v.getRef2().accept(this);
        System.out.println("</Eq>\n");
        try{
            stampaTab();
            fw.append("</Eq>\n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return null;
    }

    @Override
    public Object visit(GeOp v) {
        tab++;
        System.out.println("<GeOp>\n");
        try{
            stampaTab();
            fw.append("<GeOp>\n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getRef1().accept(this);
        v.getRef2().accept(this);
        System.out.println("</GeOp>\n");
        try{
            stampaTab();
            fw.append("</GeOp>\n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return null;
    }

    @Override
    public Object visit(GtOp v) {
        tab++;
        System.out.println("<GtOp>\n");
        try{
            stampaTab();
            fw.append("<GtOp>\n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getRef1().accept(this);
        v.getRef2().accept(this);
        System.out.println("</GtOp>\n");
        try{
            stampaTab();
            fw.append("</GtOp>\n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return null;
    }

    @Override
    public Object visit(LeOp v) {
        tab++;
        System.out.println("<LeOp>\n");
        try{
            stampaTab();
            fw.append("<LeOp>\n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getRef1().accept(this);
        v.getRef2().accept(this);
        System.out.println("</LeOp>\n");
        try{
            stampaTab();
            fw.append("</LeOp>\n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return null;
    }

    @Override
    public Object visit(LtOp v) {
        tab++;
        System.out.println("<LtOp>\n");
        try{
            stampaTab();
            fw.append("<LtOp>\n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getRef1().accept(this);
        v.getRef2().accept(this);
        System.out.println("</LtOp>\n");
        try{
            stampaTab();
            fw.append("</LtOp>\n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return null;
    }

    @Override
    public Object visit(MinusOp v) {
        tab++;
        System.out.println("<Minus> \n");
        try{
            stampaTab();
            fw.append("<Minus> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getRef1().accept(this);
        v.getRef2().accept(this);
        System.out.println("</Minus> \n");
        try{
            stampaTab();
            fw.append("</Minus> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return null;
    }

    @Override
    public Object visit(NeOp v) {
        tab++;
        System.out.println("<NeOp>\n");
        try{
            stampaTab();
            fw.append("<NeOp>\n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getRef1().accept(this);
        v.getRef2().accept(this);
        System.out.println("</NeOp>\n");
        try{
            stampaTab();
            fw.append("</NeOp>\n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return null;
    }

    @Override
    public Object visit(OrOp v) {
        tab++;
        System.out.println("<OrOp>\n");
        try{
            stampaTab();
            fw.append("<OrOp>\n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getRef1().accept(this);
        v.getRef2().accept(this);
        System.out.println("</OrOp>\n");
        try{
            stampaTab();
            fw.append("</OrOp>\n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return null;
    }

    @Override
    public Object visit(PlusOp v) {
        tab++;
        System.out.println("<Plus> \n");
        try{
            stampaTab();
            fw.append("<Plus> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getRef1().accept(this);
        v.getRef2().accept(this);
        System.out.println("</Plus> \n");
        try{
            stampaTab();
            fw.append("</Plus> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return null;
    }

    @Override
    public Object visit(PowOp v) {
        tab++;
        System.out.println("<Pow> \n");
        try{
            stampaTab();
            fw.append("<Pow> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getRef1().accept(this);
        v.getRef2().accept(this);
        System.out.println("</Pow> \n");
        try{
            stampaTab();
            fw.append("</Pow> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return null;
    }

    @Override
    public Object visit(StrCatOp v) {
        tab++;
        System.out.println("<StrCat> \n");
        try{
            stampaTab();
            fw.append("<StrCat> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getRef1().accept(this);
        v.getRef2().accept(this);
        System.out.println("</StrCat> \n");
        try{
            stampaTab();
            fw.append("</StrCat> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return null;
    }

    //DA QUA in SU

    @Override
    public Object visit(TimesOp v) {
        tab++;
        System.out.println("<Times> \n");
        try{
            stampaTab();
            fw.append("<Times> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getRef1().accept(this);
        v.getRef2().accept(this);
        System.out.println("</Times> \n");
        try{
            stampaTab();
            fw.append("</Times> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return null;
    }

    @Override
    public Object visit(NotOp v) {
        tab++;
        System.out.println("<Not> \n");
        try{
            stampaTab();
            fw.append("<Not> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getRef().accept(this);
        System.out.println("</Not> \n");
        try{
            stampaTab();
            fw.append("</Not> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return null;
    }

    @Override
    public Object visit(UMinusOp v) {
        tab++;
        System.out.println("<UMinus> \n");
        try{
            stampaTab();
            fw.append("<UMinus> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getRef().accept(this);
        System.out.println("</UMinus> \n");
        try{
            stampaTab();
            fw.append("</UMinus> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return null;
    }

    @Override
    public Object visit(AssignStatOp v) {
        tab++;
        System.out.println("<AssignStat> \n");
        try{
            stampaTab();
            fw.append("<AssignStat> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        for (Identifier i: v.getIdList()) {
            i.accept(this);
        }
        for (Expr e: v.getExprList()) {
            e.accept(this);
        }
        System.out.println("</AssignStat> \n");
        try{
            stampaTab();
            fw.append("</AssignStat> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return null;
    }

    @Override
    public Object visit(ForStatOp v) {
        tab++;
        System.out.println("<ForStatOp> \n");
        try{
            stampaTab();
            fw.append("<ForStatOp> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getIdInit().accept(this);
        v.getIntegerConst().accept(this);
        v.getBody().accept(this);
        System.out.println("</ForStatOp> \n");
        try{
            stampaTab();
            fw.append("</ForStatOp> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return null;
    }

    @Override
    public Object visit(FunCallOp v) {
        tab++;
        System.out.println("<FunCallOp> \n");
        try{
            stampaTab();
            fw.append("<FunCallOp> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getId().accept(this);
        for (Expr e: v.getExprList()) {
            e.accept(this);
        }
        System.out.println("</FunCallOp> \n");
        try{
            stampaTab();
            fw.append("</FunCallOp> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return null;
    }

    @Override
    public Object visit(IfStatOp v) {
        tab++;
        System.out.println("<IfStatOp> \n");
        try{
            stampaTab();
            fw.append("<IfStatOp> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getExpr().accept(this);
        v.getBodyOp1().accept(this);
        v.getBodyOp2().accept(this);
        System.out.println("</IfStatOp> \n");
        try{
            stampaTab();
            fw.append("</IfStatOp> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return null;
    }

    @Override
    public Object visit(ReadStatOp v) {
        tab++;
        System.out.println("<ReadStatOp> \n");
        try{
            stampaTab();
            fw.append("<ReadStatOp> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        for (Identifier i: v.getIdList()) {
            i.accept(this);
        }
        if(v.getString_const()!=null)
            v.getString_const().accept(this);
        System.out.println("</ReadStatOp> \n");
        try{
            stampaTab();
            fw.append("</ReadStatOp> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return null;
    }

    @Override
    public Object visit(WhileStatOp v) {
        tab++;
        System.out.println("<WhileStatOp> \n");
        try{
            stampaTab();
            fw.append("<WhileStatOp> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getExpr().accept(this);
        v.getBody().accept(this);
        System.out.println("</WhileStatOp> \n");
        try{
            stampaTab();
            fw.append("</WhileStatOp> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return null;
    }

    @Override
    public Object visit(WriteStatOp v) {
        tab++;
        System.out.println("<WriteStatOp> \n");
        try{
            stampaTab();
            fw.append("<WriteStatOp> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        for (Expr e: v.getExprList()) {
            e.accept(this);
        }
        v.getTypeWrite().accept(this);
        System.out.println("</WriteStatOp> \n");
        try{
            stampaTab();
            fw.append("</WriteStatOp> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return null;
    }

    @Override
    public Object visit(BodyOp v) {
        tab++;
        System.out.println("<BodyOp> \n");
        try{
            stampaTab();
            fw.append("<BodyOp> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        if(v.getVerDecls()!=null){
            for (VarDeclOp vdo: v.getVerDecls()) {
                if(vdo != null)
                    vdo.accept(this);
            }
        }

        if(v.getStats()!=null){
            for (Stat s: v.getStats()) {
                if(s!=null)
                    s.accept(this);
            }
        }

        System.out.println("</BodyOp> \n");
        try{
            stampaTab();
            fw.append("</BodyOp> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return null;
    }

    @Override
    public Object visit(VarDeclOp v) {
        tab++;
        System.out.println("<VarDeclOp type = \""+v.getType()+"\"> \n");
        try{
            stampaTab();
            fw.append("<VarDeclOp type = \""+v.getType()+"\"> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        for (Identifier i: v.getIdList()) {
            i.accept(this);
        }
        System.out.println("</VarDeclOp> \n");
        try{
            stampaTab();
            fw.append("</VarDeclOp> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return null;
    }

    @Override
    public Object visit(FunDeclOp v) {
        tab++;
        System.out.println("<FunDeclOp type = \""+v.getTypeOrVoid()+"\"> \n");
        try{
            stampaTab();
            fw.append("<FunDeclOp type = \""+v.getTypeOrVoid()+"\"> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }

        v.getId().accept(this);
        for (ParDeclOp pdo: v.getParDeclList()) {
            pdo.accept(this);
        }

        v.getBody().accept(this);
        System.out.println("</FunDeclOp> \n");
        try{
            stampaTab();
            fw.append("</FunDeclOp> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return null;
    }

    @Override
    public Object visit(IdInitOp v) {
        tab++;
        System.out.println("<IdInitOp> \n");
        try{
            stampaTab();
            fw.append("<IdInitOp> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getId().accept(this);
        v.getExpr().accept(this);
        System.out.println("</IdInitOp> \n");
        try{
            stampaTab();
            fw.append("</IdInitOp> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return null;
    }

    @Override
    public Object visit(MainFunDeclOp v) {
        tab++;
        System.out.println("<MainFunDeclOp> \n");
        try{
            stampaTab();
            fw.append("<MainFunDeclOp> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }

        v.getFunDeclOp().accept(this);
        System.out.println("</MainFunDeclOp> \n");
        try{
            stampaTab();
            fw.append("</MainFunDeclOp> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return null;
    }

    @Override
    public Object visit(ParDeclOp v) {
        tab++;
        boolean isOut = false;
        if(v.getOut()!=null){
            isOut = true;
        }
        System.out.println("<ParDeclOp type = \""+v.getType()+"\" isOut = \""+isOut+"\"> \n");


        try{
            stampaTab();
            fw.append("<ParDeclOp type = \""+v.getType()+"\" isOut = \""+isOut+"\"> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }

        for (Identifier i: v.getIdList()) {
            i.accept(this);
        }
        System.out.println("</ParDeclOp> \n");
        try{
            stampaTab();
            fw.append("</ParDeclOp> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        tab--;
        return null;
    }

    @Override
    public Object visit(ProgramOp v) {
        inzializeWritingFile();
        System.out.println("<ProgramOp> \n");
        try{
            stampaTab();
            fw.append("<ProgramOp> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }

        for (DeclOp dop: v.getDecList1()) {
            dop.accept(this);
        }
        v.getMain().accept(this);

        for (DeclOp dop: v.getDecList2()) {
            dop.accept(this);
        }

        System.out.println("</ProgramOp> \n");
        try{
            stampaTab();
            fw.append("</ProgramOp> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        try {
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void stampaTab(){
        for(int i = 0; i < tab;i++){
            try {
                fw.append("\t");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
