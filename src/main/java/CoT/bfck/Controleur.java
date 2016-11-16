package CoT.bfck;

import java.util.ArrayList;
import java.util.List;

public class Controleur {

	private Memory mem = new Memory();
	private List<Command> commands = new ArrayList<Command>();
	private String file;
	
	public Controleur(String[] args){
		file = args[args.length - 1];
	}

	public void run() throws Exception {
		execute(new ReadFile(file).readFile(), 0);
		mem.display();
	}

	public void execute(ArrayList<Command> command, int i ) throws Exception {
		Object[] list = command.toArray();
		int compteur = 0;
		int k = 0;
		for(int j = i ; j < list.length ; j++)
		{
			if(mem.getCell() == 0 && ((Command) list[j]).getNameShort().equals("[")){
				compteur ++;
				for(k = j+1 ; compteur != 0 ; k++) {
					if( ((Command) list[k]).getNameShort().equals("[")) compteur ++;
					if( ((Command) list[k]).getNameShort().equals("]")) compteur --;
				}
				j = k;
			}
			
			if(mem.getCell() != 0 && ((Command) list[j]).getNameShort().equals("]")){
				compteur ++;
				for(k = j-1 ; compteur != 0 ; k--) {
					System.out.println(compteur);
					if( ((Command) list[k]).getNameShort().equals("[")) compteur --;
					if( ((Command) list[k]).getNameShort().equals("]")) compteur ++;
				}
				j = k+1;
			}
			((Command) list[j]).execute(mem);
		}
	}
	

	public static String getFileExt(String filename) {
		int pos = filename.lastIndexOf(".");
		if (pos > -1) {
			return filename.substring(pos);
		} else {
			return filename;
		}
	}
}