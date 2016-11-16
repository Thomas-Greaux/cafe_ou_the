package CoT.bfck;

import java.io.IOException;
import java.util.ArrayList;


public class Reader {
	
	private CommandFactory cf;
	
	public Reader() {
		cf = new CommandFactory();
	}
	
	/**
     * Return the string corresponding to the string in param :
		 * 	-	if the string is in long representation, give the shortened representation
		 * 	- if string in short representation, return the same string
		 * 	- else return nothing, with System.exit(23);
     * @param String s
		 * 		The line of bf code we want to make short
     * @return String result
		 * 		The bf code in shorten representation.
	 * @throws Exception 
     */

	public String shortened(String s) throws Exception{
		try{
			return cf.getCommand(s).getNameShort();
		}catch(IOException e){
			return null;
		}
	}
	
	public ArrayList<Command> read(String line) throws Exception {
		ArrayList<Command> list = new ArrayList<Command>();
		for(int i=0;i<line.length();i++){
			if(isChar(line, i) || isCharMin(line, i)){
					list.add((cf.getCommand(line)));
					break;
			}else{
				list.add((cf.getCommand(Character.toString(line.charAt(i)))));
			}
		}
		return list;
	}
	
	public boolean isChar(String l, int i){
		return l.codePointAt(i)>=65 && l.codePointAt(i)<=90;
	}
	public boolean isCharMin(String l, int i){
		return l.codePointAt(i)>=97 && l.codePointAt(i)<=127;
	}
}
