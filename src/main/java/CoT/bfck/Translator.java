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
        write_initialization();
        write_constructor();
        write_display();
        write_main(commands);
        pw.println("}");
        pw.flush();
    }

    public void write_initialization(){
        pw.println();
        pw.println("public int i;");
        pw.println("public int[] memory;");
        pw.println();
    }

    /**
     * Write the main method
     * @param commands to execute
     */
    public void write_main(ArrayList<Command> commands){
        pw.println();
        pw.println("\tpublic static void main(String[] args){");
        pw.println("\tMain m = new Main();");
        for(Command command : commands){
            pw.println("\t\t" + translate_instruction(command));
        }
        pw.println("\tm.display();");
        pw.println("\t}");
        pw.println();
    }

    /**
     * Write the constructor
     */
    public void write_constructor(){
        pw.println();
        pw.println("\tpublic Main(){");
        pw.println("\t\ti = 0;");
        pw.println("\t\tmemory = new int[30000];");
        pw.println("\t\tfor(int k = 0; k<30000; k++){");
        pw.println("\t\t\tmemory[k] = 0;");
        pw.println("\t\t}");
        pw.println("\t}");
        pw.println();
    }


    /**
     * Translate a single instruction
     * @param cmd the brainfuck instruction
     * @return the translated instruction in Java
     */
    public String translate_instruction(Command cmd){
        String instruct = cmd.getNameShort();
        if(instruct.equals("+")) return "m.memory[m.i]++;";
        if(instruct.equals("-")) return "m.memory[m.i]--;";
        if(instruct.equals(">")) return "m.i++;";
        if(instruct.equals("<")) return "m.i--;";
        if(instruct.equals("[")) return "while(m.memory[m.i])\t{";
        if(instruct.equals("]")) return "\t}";
        else{
            System.out.println("Pas une instruction: " + instruct);
            System.exit(7);
            return "";
        }
    }

    /**
     * Write the display method
     */
    public void write_display(){
        pw.println();
        pw.println("\tpublic void display(){");
        pw.println("\tfor(int k = 0; k<30000; k++){");
        pw.println("\t\tif(memory[k] != 0) System.out.println(\"C\" + k + \": \" + memory[k]);");
        pw.println("\t\t}");
        pw.println("\t}");
        pw.println();
    }

    /**
     * Classe d'essai
     * @param args les arguments du programme
     */
    public static void main (String[] args){
        ArrayList<Command> commands = new ArrayList<Command>();
        commands.add(new Increment());
        commands.add(new Increment());
        commands.add(new Increment());
        commands.add(new Increment());
        commands.add(new Right());
        commands.add(new Right());
        commands.add(new Increment());
        Translator t = new Translator(null);
        t.translate(commands);
    }

}
