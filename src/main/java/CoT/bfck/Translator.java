package CoT.bfck;

import CoT.bfck.Command.*;
import CoT.bfck.Exception.NotACommandException;
import CoT.bfck.Factory.CommandFactory;

import java.io.*;
import java.util.ArrayList;

/**
 * This class handle the code generation
 * Created by Thoma on 12/19/2016.
 */
public class Translator {

    private static final String default_path = "files/Output/Main.java";
    private PrintWriter pw;
    private int ind = 0; //keep track of the indentation

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
        pw.println("import java.util.Scanner;\n");
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
        pw.println("private int i;");
        pw.println("private int[] memory;");
        pw.println();
    }

    /**
     * Write the main method
     * @param commands to execute
     */
    public void write_main(ArrayList<Command> commands){
        Command command;

        pw.println();
        pw.println("\tpublic static void main(String[] args){");
        pw.println("\t\tMain m = new Main();");
        pw.println("\t\tScanner sc = new Scanner(System.in);");
        for(int i = 0; i<commands.size() ;i++){
            command = commands.get(i);
            indentation();

            if(stackable(command)) {
                int j;
                for (j = i + 1; commands.get(j) == command; j++) {

                }
                pw.println("\t" + write_instructions(command, j-i));
                i = j-1;
            }

            else{
                pw.println("\t" + write_instruction(command));
            }
        }
        pw.println("\t\tm.display();");
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
    private String write_instruction(Command cmd){
        String instruct = cmd.getNameShort();
        if(instruct.equals("+")) return "\tm.memory[m.i]++;";
        if(instruct.equals("-")) return "\tm.memory[m.i]--;";
        if(instruct.equals(">")) return "\tm.i++;";
        if(instruct.equals("<")) return "\tm.i--;";
        if(instruct.equals("[")) {ind++; return "\twhile(m.memory[m.i] != 0){";}
        if(instruct.equals("]")) {ind--; return "}";}
        if(instruct.equals(".")) return "\tSystem.out.print((char) m.memory[m.i]);";
        if(instruct.equals(",")) return "\tm.memory[m.i] = sc.nextInt();";
        else{
            System.out.println("Pas une instruction: " + instruct);
            System.exit(7);
            return "";
        }
    }

    /**
     * Non-naive way of printing some instructions
     * @param command to print
     * @param n number of command
     * @return the string to print
     */
    private String write_instructions(Command command, int n){
        String instruct = command.getNameShort();
        if(instruct.equals("+")) return "\tm.memory[m.i] += " + n + ";";
        if(instruct.equals("-")) return "\tm.memory[m.i] -= " + n + ";";
        if(instruct.equals(">")) return "\tm.i += " + n + ";";
        if(instruct.equals("<")) return "\tm.i -= " + n + ";";
        else{
            System.out.println("Non stackable instruction: " + instruct);
            System.exit(7);
            return "";
        }
    }

    /**
     * @param command to evaluate
     * @return whether the command can be translated in a non-naive way
     */
    private boolean stackable(Command command){
        String instruct = command.getNameShort();
        return instruct.equals("+") || instruct.equals("-") || instruct.equals("<") || instruct.equals(">");
    }

    /**
     * Write the display method
     */
    public void write_display(){
        pw.println();
        pw.println("\tpublic void display(){");
        pw.println("\tSystem.out.println();");
        pw.println("\tfor(int k = 0; k<30000; k++){");
        pw.println("\t\tif(memory[k] != 0) System.out.println(\"C\" + k + \": \" + memory[k]);");
        pw.println("\t\t}");
        pw.println("\t}");
        pw.println();
    }

    /**
     * this method handles the indentation
     */
    public void indentation(){for(int k = 0; k<ind; k++) pw.print("\t");}

    //TODO: supprimer, ce n'est la qu'a but de faire des essais -----------------------
    /**
     * Temporaire, pour des essais
     * @param prog
     * @return
     * @throws NotACommandException
     * @throws IOException
     */
    public ArrayList<Command> readFile(String prog) throws NotACommandException, IOException {
        ArrayList<Command> commands = new ArrayList<Command>();
        commands.addAll(read(prog));
        return commands;
    }

    public ArrayList<Command> read(String line) throws NotACommandException{
        ArrayList<Command> list = new ArrayList<Command>();
        CommandFactory cf = new CommandFactory();
        String tmp;
        for(int i=0;i<line.length();i++){
            list.add(cf.getCommand(line.substring(i, i+1)));
        }
        return list;
    }

    //TODO -----------------------------------------------------------------------------

    /**
     * Classe d'essai
     * @param args les arguments du programme
     */
    public static void main (String[] args){
        Translator t = new Translator(default_path);
        ArrayList<Command> commands = new ArrayList<Command>();
        try {
            commands = t.readFile("++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.");
        } catch (Exception e){}

        t.translate(commands); //Essai de Hello World!
    }

}
