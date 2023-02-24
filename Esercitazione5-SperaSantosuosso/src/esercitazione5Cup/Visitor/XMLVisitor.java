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

public class XMLVisitor implements Visitor {

    FileWriter fw;
    int tab = 0;
    public XMLVisitor(){

    }

    private void inzializeWritingFile(){
        try {
            File treeFile = new File("output/ParsingTreeXML.txt");
            if (treeFile.createNewFile()) {
            }
            fw = new FileWriter("output/ParsingTreeXML.txt");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public Object visit(ReturnOp v) throws Exception {
        if (v.getExpr() != null) {
            tab++;
            try{
                stampaTab();
                fw.append("</Return> \n");
            }
            catch (IOException e){
                System.out.println(e);
            }
            tab--;
            v.getExpr().accept(this);
        }
        return null;
    }

    @Override
    public Object visit(IntegerConst ic){
        tab++;
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
    public Object visit(AndOp v) throws Exception {
        tab++;
        try{
            stampaTab();
            fw.append("<And> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getRef1().accept(this);
        v.getRef2().accept(this);
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
    public Object visit(DivOp v) throws Exception {
        tab++;
        try{
            stampaTab();
            fw.append("<Div> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getRef1().accept(this);
        v.getRef2().accept(this);
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
    public Object visit(EqOp v) throws Exception {
        tab++;
        try{
            stampaTab();
            fw.append("<Eq>\n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getRef1().accept(this);
        v.getRef2().accept(this);
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
    public Object visit(GeOp v) throws Exception {
        tab++;
        try{
            stampaTab();
            fw.append("<GeOp>\n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getRef1().accept(this);
        v.getRef2().accept(this);
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
    public Object visit(GtOp v) throws Exception {
        tab++;
        try{
            stampaTab();
            fw.append("<GtOp>\n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getRef1().accept(this);
        v.getRef2().accept(this);
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
    public Object visit(LeOp v) throws Exception{
        tab++;
        try{
            stampaTab();
            fw.append("<LeOp>\n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getRef1().accept(this);
        v.getRef2().accept(this);
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
    public Object visit(LtOp v)throws Exception {
        tab++;
        try{
            stampaTab();
            fw.append("<LtOp>\n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getRef1().accept(this);
        v.getRef2().accept(this);
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
    public Object visit(MinusOp v)throws Exception {
        tab++;
        try{
            stampaTab();
            fw.append("<Minus> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getRef1().accept(this);
        v.getRef2().accept(this);
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
    public Object visit(NeOp v) throws Exception{
        tab++;
        try{
            stampaTab();
            fw.append("<NeOp>\n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getRef1().accept(this);
        v.getRef2().accept(this);
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
    public Object visit(OrOp v)throws Exception {
        tab++;
        try{
            stampaTab();
            fw.append("<OrOp>\n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getRef1().accept(this);
        v.getRef2().accept(this);
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
    public Object visit(PlusOp v)throws Exception {
        tab++;
        try{
            stampaTab();
            fw.append("<Plus> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getRef1().accept(this);
        v.getRef2().accept(this);
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
    public Object visit(PowOp v)throws Exception {
        tab++;
        try{
            stampaTab();
            fw.append("<Pow> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getRef1().accept(this);
        v.getRef2().accept(this);
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
    public Object visit(StrCatOp v)throws Exception {
        tab++;
        try{
            stampaTab();
            fw.append("<StrCat> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getRef1().accept(this);
        v.getRef2().accept(this);
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
    public Object visit(TimesOp v) throws Exception{
        tab++;
        try{
            stampaTab();
            fw.append("<Times> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getRef1().accept(this);
        v.getRef2().accept(this);
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
    public Object visit(NotOp v)throws Exception {
        tab++;
        try{
            stampaTab();
            fw.append("<Not> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getRef().accept(this);
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
    public Object visit(UMinusOp v)throws Exception {
        tab++;
        try{
            stampaTab();
            fw.append("<UMinus> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getRef().accept(this);
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
    public Object visit(AssignStatOp v) throws Exception{
        tab++;
        try{
            stampaTab();
            fw.append("<AssignStat> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }

        for (int i = 0; i < v.getIdList().size(); i++) {
            v.getIdList().get(i).accept(this);
            v.getExprList().get(i).accept(this);
        }

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
    public Object visit(ForStatOp v) throws Exception{
        tab++;
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
    public Object visit(FunCallOp v) throws Exception{
        tab++;
        try{
            stampaTab();
            fw.append("<FunCallOp> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getId().accept(this);
        if(v.getExprList()!= null){
            for (Expr e: v.getExprList()) {
                e.accept(this);
            }
            try{
                stampaTab();
                fw.append("</FunCallOp> \n");
            }
            catch (IOException e){
                System.out.println(e);
            }
        }

        tab--;
        return null;
    }

    @Override
    public Object visit(IfStatOp v) throws Exception{
        tab++;
        try{
            stampaTab();
            fw.append("<IfStatOp> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getExpr().accept(this);
        v.getBodyOp1().accept(this);
        if (v.getBodyOp2().getVerDecls() != null || v.getBodyOp2().getStats() != null) {

            v.getBodyOp2().accept(this);

        }
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
    public Object visit(ReadStatOp v) throws Exception{
        tab++;
        try{
            stampaTab();
            fw.append("<ReadStatOp> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        for (IdentifierOrInit i: v.getIdList()) {
            i.accept(this);
        }
        if(v.getString_const()!=null)
            v.getString_const().accept(this);
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
    public Object visit(WhileStatOp v) throws Exception{
        tab++;
        try{
            stampaTab();
            fw.append("<WhileStatOp> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getExpr().accept(this);
        v.getBody().accept(this);
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
    public Object visit(WriteStatOp v) throws Exception{
        tab++;
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
    public Object visit(BodyOp v) throws Exception{
        tab++;
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
    public Object visit(VarDeclOp v) throws Exception{

        tab++;
        try{
            stampaTab();
            fw.append("<VarDeclOp type = \""+v.getType()+"\"> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }

        for (int i = 0; i < v.getIdList().size(); i++) {
            IdInitOp io = (IdInitOp) v.getIdList().get(i);
             io.accept(this);

        }

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
    public Object visit(TypeSetter v) throws Exception {
        return null;
    }

    @Override
    public Object visit(FunDeclOp v) throws Exception{
        tab++;
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
    public Object visit(IdInitOp v) throws Exception{
        tab++;
        try{
            stampaTab();
            fw.append("<IdInitOp> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }
        v.getId().accept(this);
        if (v.getExpr() != null) {
            v.getExpr().accept(this);
        }
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
    public Object visit(MainFunDeclOp v) throws Exception{
        tab++;
        try{
            stampaTab();
            fw.append("<MainFunDeclOp> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }

        v.getFunDeclOp().accept(this);
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
    public Object visit(ParDeclOp v) throws Exception{
        tab++;
        boolean isOut = false;
        if(v.getOut()!=null){
            isOut = true;
        }


        try{
            stampaTab();
            fw.append("<ParDeclOp type = \""+v.getType()+"\" isOut = \""+isOut+"\"> \n");
        }
        catch (IOException e){
            System.out.println(e);
        }

        for (IdentifierOrInit i: v.getIdList()) {
            i.accept(this);
        }
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
    public Object visit(ProgramOp v) throws Exception{
        inzializeWritingFile();
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
