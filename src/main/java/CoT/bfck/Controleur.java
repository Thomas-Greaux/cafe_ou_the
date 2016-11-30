package CoT.bfck;

import CoT.bfck.Command.Command;
import CoT.bfck.Command.Jump;
import CoT.bfck.Reader.CreateImage;
import CoT.bfck.Reader.ReadFile;
import CoT.bfck.Reader.ReadImage;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

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
    private String rewrite;
    private String in;
    private String out;
    private FileWriter fw;
    private int c = 0;
    private JumpTable jumpTable;

    // OPTIONS
    private int option_p;
    private int option_rewrite = -1;
    private int option_translate = -1;
    private int option_check = -1;
    private int option_in = -1;
    private int option_out = -1;
    private int option_trace = -1;

    /**
     * Normal constructor for the Controleur
     *
     * @param args commandes written by the user
     */

    private int option_metrics = -1;
    private int option_interpret = 0;

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
            } else if (args[i].equals("--trace")) option_trace = i;
            else if (args[i].equals("--moff")) option_metrics = i;
        }
        file = args[option_p + 1];
        file_ext = OpOption.getFileExt(file);
    }


    /**
     * Run the program then display the memory
     *
     * @throws Exception
     */
    public void run() throws Exception {

        //On remplie notre liste de commandes
        if (file_ext.equals(".bf")) {                     //En fonction du type de fichier
            commands = new ReadFile().readFile(file);    //
        }                                                //
        else if (file_ext.equals(".bmp")) {               //
            commands = new ReadImage().readImage(file);  //
        }                                                //

        if (option_translate != -1) {
            rewrite = OpOption.rewrite(commands);
            new CreateImage().create_Image(rewrite);
        }
        if (option_rewrite != -1) OpOption.print(commands);

        if (option_check != -1) OpOption.check(commands);

        if (option_trace != -1) {
            String[] f = file.split("/");
            String myFile = f[f.length - 1];
            myFile = myFile.split("\\.")[0];
            fw = new FileWriter(new File("files/Output/" + myFile + ".log"));
        }

        if (option_in != -1) mem.setIn(in);

        if (option_out != -1) mem.setOut(out);

        if (option_interpret == 0) {
            OpOption.check(commands); //On check toujours le programme

            Metrics.PROG_SIZE = commands.size();

            jumpTable = new JumpTable(commands); //Table contenant les liaison entre jump et back

            commands.add(null); //ajout d'un element null pour gerer le cas ou le program fini par un ]

            long start = System.currentTimeMillis();
            execute(commands);
            long finish = System.currentTimeMillis();

            Metrics.EXEC_TIME = finish - start;

            mem.display();
            if (option_metrics == -1) Metrics.display(); //On n'affiche pas les metrics seulement si l'option est entree
        }
    }

    /**
     * Execute the commands
     *
     * @param commands ArrayList of the commands we want to execute
     * @throws Exception
     */
    public void execute(ArrayList<Command> commands) throws Exception {
        int compteur = 0;
        int k;
        int n = commands.size();
        for (int j = 0; j < n; j++, Metrics.EXEC_MOVE++) //Boucle d'execution des commandes, on joue sur j pour gerer les boucles
        {
            if (commands.get(j) != null) {
                if (commands.get(j).getNameShort().equals("[") && mem.getValue() == 0) {
                    j = jumpTable.getComp(j);
                }

                if (commands.get(j).getNameShort().equals("]") && mem.getValue() != 0) {
                    compteur++;
                    for (j--; compteur > 0; j--, Metrics.EXEC_MOVE++) {
                        if (commands.get(j).getNameShort().equals("[")) compteur--;
                        if (commands.get(j).getNameShort().equals("]")) compteur++;
                    }
                    j++;
                } else {
                    if (option_trace != -1) {
                        j = jumpTable.getComp(j);
                    } else {
                        if (option_trace != -1) {
                            c++;
                            fw.write("Step n°" + c + "\n");
                            fw.write("Next command : " + commands.get(j).getName() + "\n");
                            fw.write("Data pointer value : " + mem.getValue() + "\n");
                            fw.write("Memory SNAPSHOT :\n" + mem.display_String());
                            fw.write("\n---------------------------\n");
                        }
                        commands.get(j).execute(mem);
                    }
                }
            }
            mem.close_stream();
            if (option_trace != -1)
                fw.close();
        }

    }
}