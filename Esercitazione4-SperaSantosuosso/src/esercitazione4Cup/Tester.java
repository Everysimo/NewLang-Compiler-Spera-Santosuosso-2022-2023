package esercitazione4Cup;
import esercitazione4Cup.GrammarClasses.ProgramOp;
import esercitazione4Cup.GrammarClasses.Visitor.ComportamentoVisitor;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;

public class Tester {
    public static void main(String[] args) throws Exception {
        System.out.println("Type in circuit, hit Return, then Cmd-D (in MacOs) o Ctrl-D (in Windows)");
        InputStreamReader inp = new InputStreamReader(System.in);
        Reader keyboard = new BufferedReader(inp);
        FileReader reader;
        reader = new FileReader(args[0]);
        Parser p = new Parser(new Lexer(reader));
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) p.parse().value;
        ((ProgramOp) root).accept(new ComportamentoVisitor());

    }
}
