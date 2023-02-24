package esercitazione5Cup;

import esercitazione5Cup.GrammarClasses.ProgramOp;
import esercitazione5Cup.Visitor.*;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;

public class Tester {

    static String fileName;
    public static void main(String[] args) throws Exception {
        InputStreamReader inp = new InputStreamReader(System.in);
        Reader keyboard = new BufferedReader(inp);
        String path=args[0];
        String[] nomeFile=path.split("/");
        fileName=nomeFile[nomeFile.length-1].split("\\.")[0];

        FileReader reader;
        if(args.length>0){
            reader = new FileReader(args[0]);
            Parser passer = new Parser(new Lexer(reader));
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) passer.parse().value;
            ((ProgramOp) root).accept(new XMLVisitor());
            ((ProgramOp) root).accept(new ComportamentoSemanticVisitor());
            ((ProgramOp) root).accept(new ComportamentoTypeCheckingVisitor());
            ((ProgramOp) root).accept(new ComportamentoTranslationVisitor(fileName));

            Tester.runProgramInC();
        }

    }

    public static void runProgramInC(){
        String os = System.getProperty("os.name").toLowerCase();

        try {
            Process p = Runtime.getRuntime().exec("gcc -v");
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        } catch (Exception e) {
            System.out.println("GCC is not installed on this system.");
            e.printStackTrace();
        }

        if (os.contains("win")) {

            try {
                Runtime rt = Runtime.getRuntime();
                Process processGCC = rt.exec("gcc test_files\\c_out\\"+fileName+".c"+" -o test_files\\c_out\\"+fileName+".exe");
                processGCC.waitFor();
                Process processEXE = Runtime.getRuntime().exec("cmd /k start cmd.exe @cmd /k test_files\\c_out\\"+fileName+".exe");
                processEXE.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (os.contains("mac")) {
            try {
                String gccCommand = "gcc -o "+fileName+" test_files/c_out/"+fileName+".c";
                Process p = Runtime.getRuntime().exec(gccCommand);
                p.waitFor();

                String terminalCommand = "open -a Terminal ";
                Runtime.getRuntime().exec(terminalCommand + "./"+fileName);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
