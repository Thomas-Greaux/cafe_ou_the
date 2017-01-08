package CoT.bfck;

import CoT.bfck.Command.Command;
import CoT.bfck.Exception.FileDoesntExists;
import CoT.bfck.Exception.ImpossibleIndexException;
import CoT.bfck.Exception.NotACommandException;
import CoT.bfck.Exception.OutOfCapacityException;
import CoT.bfck.Memory.ExecPointer;
import CoT.bfck.Memory.Memory;
import CoT.bfck.Memory.Metrics;
import CoT.bfck.Options.CreateImage;
import CoT.bfck.Options.OpOption;
import CoT.bfck.Reader.ReadFile;
import CoT.bfck.Reader.ReadImage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The controleur read the file given in the command line.
 * Send the differents lines of this file to the Reader.
 * @author cafe_ou_the
 */
public class Controleur {

    private Memory mem = new Memory();
    private ArrayList<Command> commands = new ArrayList<Command>();
    private String file;
    private String file_ext;
    private String in;
    private String out;
    private FileWriter fw;
    private int c = 0;
    private JumpTable jumpTable;
    private String fileWithoutExtension;

    // OPTIONS
    private int option_p;
    private int option_rewrite = -1;
    private int option_translate = -1;
    private int option_check = -1;
    private int option_in = -1;
    private int option_out = -1;
    private int option_trace = -1;
    private int option_metrics = -1;
    private int option_generation = -1;
    private int option_interpret = 0;

    /**
     * Normal constructor for the Controleur
     * @param args commandes written by the user
     */
    public Controleur(String[] args) {
        for (int i = 0; i < args.length; i++) { //Boucle d'initialisation des options
            if (args[i].equals("-p")) option_p = i;
            else if (args[i].equals("--rewrite")) {
                option_rewrite = i;
                option_interpret = 1;
            } else if (args[i].equals("--translate")) {
                option_translate = i;
                option_interpret = 1;
            } else if (args[i].equals("--check")) {
                option_check = i;
                option_interpret = 1;
            } else if (args[i].equals("-i")) {
                option_in = i;
                in = args[option_in + 1];
            } else if (args[i].equals("-o")) {
                option_out = i;
                out = args[option_out + 1];
            } else if (args[i].equals("--gen")){
                option_generation = i;
                option_interpret = 1;
            } else if (args[i].equals("--trace")) option_trace = i;
            else if (args[i].equals("--moff")) option_metrics = i;
        }
        file = args[option_p + 1];
        file_ext = OpOption.getFileExt(file);
    }


    /**
     * Run the program then display the memory
     * @throws NotACommandException
     * @throws IOException
     * @throws OutOfCapacityException
     * @throws ImpossibleIndexException
     */
    public void run() throws NotACommandException, IOException, OutOfCapacityException, ImpossibleIndexException, FileDoesntExists {

        //On remplie notre liste de commandes
        if (file_ext.equals(".bf")) {                    //En fonction du type de fichier
            commands = new ReadFile().readFile(file);    //
        }                                                //
        else if (file_ext.equals(".bmp")) {              //
            commands = new ReadImage().readImage(file);  //
        }                                                //

        if (option_translate != -1) {
            String rewrite = OpOption.rewrite(commands);
            new CreateImage().create_Image(rewrite);
        }
        if (option_rewrite != -1) OpOption.print(commands);
        if (option_check != -1) OpOption.check(commands);
        if (option_generation != -1){
            Translator translator = new Translator(Translator.default_path);
            translator.translate(commands);
        }

        if (option_trace != -1) {
            String[] f = file.split("/");
            String myFile = f[f.length - 1];
            fileWithoutExtension = myFile.split("\\.")[0];
            fw = new FileWriter(new File("files/Output/" + fileWithoutExtension + ".log"));//On reset le fichier
            fw.close();
        }

        if (option_in != -1) mem.setIn(in);

        if (option_out != -1) mem.setOut(out);

        if (option_interpret == 0) {
            OpOption.check(commands); //On check toujours le programme

            Metrics.setProgSize(commands.size());

            jumpTable = new JumpTable(commands); //Table contenant les liaison entre jump et back

            commands.add(null); //ajout d'un element null pour gerer le cas ou le program fini par un ]

            long start = System.currentTimeMillis();
            execute(commands);
            long finish = System.currentTimeMillis();

            Metrics.setExecTime(finish - start);

            mem.display();
            if (option_metrics == -1) Metrics.display(); //On n'affiche pas les metrics seulement si l'option est entree
        }
    }

    /**
     * Execute the commands
     * @param commands ArrayList of the commands we want to execute
     * @throws IOException
     * @throws ImpossibleIndexException
     * @throws OutOfCapacityException
     */
    public void execute(ArrayList<Command> commands) throws IOException, ImpossibleIndexException, OutOfCapacityException {
        int n = commands.size();
        ExecPointer execPointer = new ExecPointer();
        for (; execPointer.getValue() < n; execPointer.incr()) //Boucle d'execution des commandes, on joue sur j pour gerer les boucles
        {
            if (commands.get(execPointer.getValue()) != null) {
                if (commands.get(execPointer.getValue()).getNameShort().equals("[") && mem.getValue() == 0) {
                    execPointer.setValue(jumpTable.getComp(execPointer.getValue()));
                }
                else if (commands.get(execPointer.getValue()).getNameShort().equals("]") && mem.getValue() != 0) {
                    execPointer.setValue(jumpTable.getComp(execPointer.getValue()));
                } else {
                    if (option_trace != -1) {
                        fw = new FileWriter(new File("files/Output/" + fileWithoutExtension + ".log"), true);
                        c++;
                        fw.write("Step n°" + c + "\n");
                        fw.write("Next command : " + commands.get(execPointer.getValue()).getName() + "\n");
                        fw.write("Data pointer value : " + mem.getIndex() + "\n");;
                        fw.write("Memory SNAPSHOT :\n" + mem.display_String());
                        fw.write("\n---------------------------\n");
                        closeWritter();
                    }
                }
                commands.get(execPointer.getValue()).execute(mem);
            }
        }
        mem.close_stream();
    }


    public void closeWritter() throws IOException {
        fw.close();
    }
}
