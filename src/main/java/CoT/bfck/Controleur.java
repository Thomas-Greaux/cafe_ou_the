package CoT.bfck;

import CoT.bfck.Command.Command;
import CoT.bfck.Reader.ReadFile;

import java.util.ArrayList;
import java.util.List;

public class Controleur {

	private Memory mem = new Memory();
	private ArrayList<Command> commands = new ArrayList<Command>();
	private String file;

    // OPTIONS
    private int option_p;
	
	public Controleur(String[] args){
		for(int i = 0; i < args.length; i++) {
            if(args[i].equals("-p")) option_p = i;
        }
		file = args[option_p + 1];
	}

	/**
	 * Run the program then display the memory
	 * @throws Exception
	 */
	public void run() throws Exception {
		commands = new ReadFile(file).readFile();
		commands.add(null); //ajout d'un element null pour gerer le cas ou le program fini par un ]
		execute(commands);
		mem.display();
	}

    /**
     * Execute les commandes, gere les [ ]
     * TODO La liste est elle correcte pour les fichiers de plusieurs lignes?
     * @param commands
     * @throws Exception
     */
	public void execute(ArrayList<Command> commands) throws Exception {
		int compteur = 0;
		int k;
        int n = commands.size();
		for(int j = 0 ; j < n ; j++) //Boucle d'execution des commandes, on joue sur j pour gerer les boucles
			                         // TODO transformer les deux if en methodes
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

	/**
	 *
	 * TODO ce n'est pas au controlleur de determiner l'extension mais au reader
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