package CoT.bfck;

import CoT.bfck.Command.*;

import java.io.*;
import java.util.ArrayList;

/**
 * This class handle the code generation
 * Created by Thoma on 12/19/2016.
 */
public class Translator {

    public static final String default_path = "files/Output/Java/";
    public static final String name_main = "Main.java";
    public static final String name_methods = "Methods.java";
    public static final String name_memory = "Memory.java";

    private PrintWriter pw_main;
    private PrintWriter pw_methods;
    private PrintWriter pw_memory;
    private int ind = 0; //keep track of the indentation

    private ArrayList<String> methods = new ArrayList<String>(); // list of methods already written

    public Translator(String path){
        if(path == null) pw_main = new PrintWriter(System.out);
        else{
            File file_main = new File(path + name_main);
            File file_functions = new File(path + name_methods);
            File file_memory = new File(path + name_memory);
            try{
                pw_main = new PrintWriter(file_main);
                pw_methods = new PrintWriter(file_functions);
                pw_memory = new PrintWriter(file_memory);
            } catch(FileNotFoundException fnfe) {
                System.out.println("Output file for translation not found" + fnfe.toString());
                System.exit(7);
            }
        }
    }

    /**
     * Translate brainfuck code to Java code
     * @param commands to translate_main
     */

    public void translate(ArrayList<Command> commands){
        write_memory();

        write_method_initialization();
        write_Main(commands);

        write_method_end();
    }

    //////////////////////////////////////
    // Method for all files
    //////////////////////////////////////

    /**
     * write commands, used both in main and functions
     * @param commands to write
     */
    private void write(ArrayList<Command> commands, boolean method){
        Command command;
        PrintWriter pw;
        if(method) pw = pw_methods;
        else pw = pw_main;

        for(int i = 0; i<commands.size() ;i++){
            command = commands.get(i);
            indentation();

            if (command instanceof Method){
                write_method_main((Method) command);
                if(!methods.contains(command.getName())) {
                    methods.add(command.getName());
                    write_method_method((Method) command);
                }
            }

            else if(stackable(command)) {
                int j;
                for (j = i + 1; j < commands.size() && commands.get(j) == command; j++) {

                }
                pw.println("\t" + write_instructions(command, j-i));
                i = j-1;
            }

            else{
                pw.println("\t" + write_instruction(command));
            }
        }
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
        if(instruct.equals("[")) {
            ind++; return "\twhile(m.memory[m.i] != 0){";}
        if(instruct.equals("]")) {
            ind--; return "}";}
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
     * this method handles the indentation
     */
    private void indentation(){for(int k = 0; k< ind; k++) pw_main.print("\t");}

    //////////////////////////////////////
    // Methods used for Main.java
    //////////////////////////////////////

    /**
     * Write the Main.java
     * @param commands commands of the brainfuck program
     */
    private void write_Main(ArrayList<Command> commands){
        pw_main.println("import java.util.Scanner;\n");
        pw_main.println("public class Main{");
        write_initialization();
        write_main(commands);
        pw_main.println("}");
        pw_main.flush();
    }

    private void write_initialization(){
        pw_main.println();
        pw_main.println("\tprivate Memory m = new Memory();");
        pw_main.println("\tprivate Methods methods = new Methods();");
        pw_main.println("\tprivate Scanner sc = new Scanner(System.in);");
        pw_main.println();
    }

    private void write_method_main(Method m){
        if(m instanceof Procedure){
            pw_main.println("\t\t" + m.getName() + "();");
        }
        else if(m instanceof Function){
            pw_main.println("\t\tm.memory[m.i] = methods." + m.getName() + "();");
        }
        else{
            System.out.println(m.getName() + " is not a method");
            System.exit(7);
        }
    }


    /**
     * Write the main method
     * @param commands to execute
     */
    private void write_main(ArrayList<Command> commands){
        pw_main.println();
        pw_main.println("\tpublic void main(){");
        write(commands, false);
        pw_main.println("\n\t\tm.display();");
        pw_main.println("\t}\n");
        pw_main.println("\tpublic static void main(String[] args){");
        pw_main.println("\t\tMain main = new Main();");
        pw_main.println("\t\tmain.main();");
        pw_main.println("\t}");
        pw_main.println();
    }

    //////////////////////////////////////////////
    // Methods used for Functions.java
    //////////////////////////////////////////////

    private void write_method_initialization(){
        pw_methods.println("import java.util.Scanner;\n\n");
        pw_methods.println("public class Methods{");
        pw_methods.println("\tprivate Memory m = new Memory();");
        pw_methods.println("\tprivate Scanner sc = new Scanner(System.in);\n");
    }

    private void write_method_end(){
        pw_methods.println("}");
        pw_methods.flush();
    }

    private void write_method_method(Method method){
        if(method instanceof Procedure) write_procedure(method);
        else if(method instanceof Function) write_function(method);
        else{
            System.out.println("Not a method: " + method.getName());
            System.exit(7);
        }
    }

    private void write_function(Method method){
        pw_methods.println("\tpublic int " + method.getName() + "(){");
        pw_methods.println("\t\tm.reset();\n");
        write(method.getCommand(), true);
        pw_methods.println("\t\treturn m.memory[m.i];");
        pw_methods.println("\t}");
    }

    private void write_procedure(Method method){
        pw_methods.println("\tpublic void " + method.getName() + "(){");
        pw_methods.println("\n\tm.reset();\n");
        write(method.getCommand(), true);
        pw_methods.println("\t}");
    }


    //////////////////////////////////////
    // Method used for Memory.java
    //////////////////////////////////////

    private void write_memory(){
        pw_memory.println("public class Memory {");
        pw_memory.println("\tpublic int[] memory = new int[30000];");
        pw_memory.println("\tpublic int i = 0;");
        write_display();
        write_reset();
        pw_memory.println("}");
        pw_memory.flush();
    }

    /**
     * Write the display method
     */
    private void write_display(){
        pw_memory.println();
        pw_memory.println("\tpublic void display(){");
        pw_memory.println("\t\tSystem.out.println();");
        pw_memory.println("\t\tfor(int k = 0; k<30000; k++){");
        pw_memory.println("\t\t\tif(memory[k] != 0) System.out.println(\"C\" + k + \": \" + memory[k]);");
        pw_memory.println("\t\t}");
        pw_memory.println("\t}");
        pw_memory.println();
    }

    private void write_reset(){
        pw_memory.println("\tpublic void reset(){");
        pw_memory.println("\t\ti = 10000;");
        pw_memory.println("\t\tfor(int k = 0; k<30000; k++){");
        pw_memory.println("\t\t\tmemory[k] = 0;");
        pw_memory.println("\t\t}");
        pw_memory.println("\t}");
    }

}
