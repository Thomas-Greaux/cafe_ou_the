package CoT.bfck;

import CoT.bfck.Command.Command;
import CoT.bfck.Reader.CreateImage;
import CoT.bfck.Reader.ReadFile;
import CoT.bfck.Reader.ReadImage;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;
import java.util.List;

public class Controleur {

	private Memory mem = new Memory();
	private ArrayList<Command> commands = new ArrayList<Command>();
	private String file;
    private String file_ext;
    private String rewrite;

    // OPTIONS
    private int option_p;
    private int option_rewrite = -1;
    private int option_translate = -1;
    private int option_check = -1;
	
	public Controleur(String[] args){
		for(int i = 0; i < args.length; i++) { //Boucle d'initialisation des options
            if(args[i].equals("-p")) option_p = i;
            else if (args[i].equals("--rewrite")) option_rewrite = i;
            else if (args[i].equals("--translate")) option_translate = i;
            else if (args[i].equals("--check")) option_check = i;
        }
		file = args[option_p + 1];
        file_ext = getFileExt(file);
	}

	/**
	 * Run the program then display the memory
	 * @throws Exception
	 */
	public void run() throws Exception {

        if(file_ext.equals(".bf")) {

            commands = new ReadFile().readFile(file);

            if(option_translate != -1) {
                rewrite = rewrite(commands);
                new CreateImage().create_Image(rewrite);
            }
            else if (option_rewrite != -1) {
                print(commands);
            }

            else if (option_check != -1) {
                check(commands);
            }
        }
        else if(file_ext.equals(".bmp")) {
            commands = new ReadImage().readImage(file);
        }

        if(option_check == -1 && option_translate == -1 && option_rewrite == -1){
            commands.add(null); //ajout d'un element null pour gerer le cas ou le program fini par un ]
            execute(commands);
            mem.display();
        }
	}

    /**
     * Execute les commandes, gere les [ ]
     * @param commands
     * @throws Exception
     */
	public void execute(ArrayList<Command> commands) throws Exception {
		int compteur = 0;
		int k;
        int n = commands.size();
		for(int j = 0 ; j < n ; j++) //Boucle d'execution des commandes, on joue sur j pour gerer les boucles
		{

            if (commands.get(j) != null) {


                if (mem.out() == 0 && commands.get(j).getNameShort().equals("[")) {
                    compteur++;
                    for (k = j + 1; compteur > 0; k++) {
                        if (commands.get(k).getNameShort().equals("[")) compteur++;
                        if (commands.get(k).getNameShort().equals("]")) compteur--;
                    }
                    j = k + 1;
                }

                if (mem.out() != 0 && commands.get(j).getNameShort().equals("]")) {
                    compteur++;
                    for (k = j - 1; compteur > 0; k--) {
                        if (commands.get(k).getNameShort().equals("[")) compteur--;
                        if (commands.get(k).getNameShort().equals("]")) compteur++;
                    }
                    j = k + 1;
                }

                else {
                    System.out.println(commands.get(j).getNameShort());
                    commands.get(j).execute(mem);
                }
            }
		}
	}

    public String rewrite(ArrayList<Command> commands) {
        StringBuilder res = new StringBuilder();
        int n = commands.size();
        for (int i = 0; i < n; i++) {
            if(commands.get(i) != null)  res.append(commands.get(i).getNameShort());
        }
        return res.toString();
    }

	public void print(ArrayList<Command> commands) {
        int n = commands.size();
        for (int i = 0; i < n; i++) {
            if(commands.get(i) != null) System.out.print(commands.get(i).getNameShort());
        }
        System.out.print("\n");
    }

    public void check(ArrayList<Command> commands) {
        int n = commands.size();
        int compteur = 0;
        for(int i = 0; i < n; i++) {
            if (commands.get(i).getNameShort().equals("[")) compteur++;
            else if (commands.get(i).getNameShort().equals("]")) compteur--;

            if(compteur < 0) System.exit(4);
        }

        if(compteur > 0) System.exit(4);
    }

	/**
	 *
	 * @param filename
	 * @return the extension of the file to read
	 */
	public static String getFileExt(String filename) {
		int pos = filename.lastIndexOf(".");
		if (pos > -1) {
			return filename.substring(pos);
		} else {
			return filename;
		}
	}
}