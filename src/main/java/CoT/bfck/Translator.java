package CoT.bfck;

import CoT.bfck.Command.Command;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This class handle the code generation
 * Created by Thoma on 12/19/2016.
 */
public class Translator {

    private File file = new File("../../../../files/Output/traduction.txt");
    private PrintWriter pw;

    public Translator(){
        try{
            pw = new PrintWriter(file);
        } catch(FileNotFoundException fnfe) {
            System.out.println("Output file for translation not found" + fnfe.toString());
            System.exit(7);
        }
    }

    public void translate(ArrayList<Command> commands){
        pw.println("public class Main{");
        pw.println("\tpublic static void main(String[] args){");
        pw.println("\t\tint[] memory = new int[30000];");
        pw.println("\t\tfor(int i = 0; i<30000; i++){");
        pw.println("\t\t\tmemory[i] = 0;");
        pw.println("\t\t}");
        pw.println("\t}");
        pw.println("}");
    }
}
