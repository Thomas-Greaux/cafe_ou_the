package CoT.bfck;

import CoT.bfck.Command.*;
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

    private static final String default_path = "D:/\"Polytech Nice\"/Projet/bfck_renew/bfck/files/Output/traduction.txt";
    private PrintWriter pw;

    public Translator(String path){
        if(path == null) pw = new PrintWriter(System.out);
        else{
            File file = new File(path);
            try{
                pw = new PrintWriter(file);
            } catch(FileNotFoundException fnfe) {
                System.out.println("Output file for translation not found" + fnfe.toString());
                System.exit(7);
            }
        }
    }

    /**
     * Translate brainfuck code to Java code
     * @param commands to translate
     */

    public void translate(ArrayList<Command> commands){
        pw.println("public class Main{");
        //Declaration of variables
        pw.println("\tprivate int[] memory;");
        pw.println("\tprivate int i;");
        pw.println();
        //Constructor
        pw.println("\tpublic Main(){");
        pw.println("\t\ti = 0;");
        pw.println("\t\tmemory = new int[30000];");
        pw.println("\t\tfor(int i = 0; i<30000; i++){");
        pw.println("\t\t\tmemory[i] = 0;");
        pw.println("\t\t}");
        pw.println("\t}");
        pw.println();
        //Main program
        pw.println("\tpublic static void main(String[] args){");
        for(Command command : commands){
            pw.println("\t\t" + translate_instruction(command));
        }
        pw.println("\t}");
        pw.println("}");
        pw.flush();
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
        if(instruct.equals("<")) return "i--;";
        if(instruct.equals("[")) return "while(memory[i])\t{";
        if(instruct.equals("]")) return "\t}";
        else{
            System.out.println("Pas une instruction: " + instruct);
            System.exit(7);
            return "";
        }
    }

    public static void main (String[] args){
        ArrayList<Command> commands = new ArrayList<Command>();
        commands.add(new Increment());
        commands.add(new Increment());
        commands.add(new Increment());
        commands.add(new Increment());
        commands.add(new Decrement());
        commands.add(new Decrement());
        commands.add(new Right());
        commands.add(new Right());
        commands.add(new Increment());
        commands.add(new Left());
        commands.add(new Increment());
        Translator t = new Translator(null);
        t.translate(commands);
    }

}
