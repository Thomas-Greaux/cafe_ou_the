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

    /**
     * Translate brainfuck code to Java code
     * @param commands to translate
     */

    public void translate(ArrayList<Command> commands){
        pw.println("public class Main{");
        pw.println("\tpublic static void main(String[] args){");
        pw.println("\t\tint[] memory = new int[30000];");
        pw.println("\t\tfor(int i = 0; i<30000; i++){");
        pw.println("\t\t\tmemory[i] = 0;");
        pw.println("\t\t}");
        for(Command cmd : commands){
            for(int i = 0; i < 20; i++){

            }
        }
        pw.println("\t}");
        pw.println("}");
    }

    /**
     * Translate a single instruction
     * @param cmd the brainfuck instruction
     * @return the translated instruction in Java
     */
    public String translate_instruction(Command cmd){
        String instruct = cmd.getNameShort();
        if(instruct.equals("+")) return "memory[i]++;";
        if(instruct.equals("-")) return "memory[i]--;";
        if(instruct.equals(">")) return "i++;";
        if(instruct.equals("-")) return "i--;";
        else{
            System.exit(7);
            return "";
        }
    }
}
