package CoT.bfck.Reader;

import CoT.bfck.Command.Command;
import CoT.bfck.CommandFactory;
import CoT.bfck.Exception.NotACommandException;
import CoT.bfck.Macro.Macro;

import java.io.IOException;
import java.util.ArrayList;


public class Reader {
	
	private CommandFactory cf;
	
	public Reader() {
		cf = new CommandFactory();
	}
	
	/**
     * Return the string corresponding to the string in.bf param :
		 * 	- if the string is in.bf long representation, give the shortened representation
		 * 	- if string in.bf short representation, return the same string
		 * 	- else return nothing, with System.exit(23);
     * @param s
		 * 		The line of bf code we want to make short
     * @return String result
		 * 		The bf code in.bf shorten representation.
	 * @throws Exception 
     */

	public String shortened(String s) throws Exception{
		try{
			return cf.getCommand(s).getNameShort();
		}catch(NotACommandException e){
			//e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Command> read(String line) throws Exception {
		ArrayList<Command> list = new ArrayList<Command>();
		for(int i=0;i<line.length();i++){
			if(line.charAt(i) == '/'){
				createMacro(line, i+1);
				break;
			}
			if(line.charAt(i)=='#' || line.charAt(i)=='\n'){
				break;
			}else if(isChar(line) || isHexaColor(line)){
				list.add((cf.getCommand(line)));
				break;
			}else if(line.charAt(i) == ' ' || line.charAt(i) == '\t'){

			}else{
				list.add((cf.getCommand(Character.toString(line.charAt(i)))));
			}
		}
		return list;
	}
	
	public boolean isChar(String l){
		for(int i=0;i<l.length();i++){
			if(!(l.codePointAt(i)>=65 && l.codePointAt(i)<=95)){
				return false;
			}
		}
		return true;
	}

	public boolean isHexaColor(String l) {
		int letters = 0;
		int numbers = 0;
		for (int i = 0; i < l.length(); i++) {
			if (l.codePointAt(i) >= 97 && l.codePointAt(i) <= 127) letters++;
			else if (l.codePointAt(i) >= 65 && l.codePointAt(i) <= 90) letters++;
			else if (l.codePointAt(i) >= 48 && l.codePointAt(i) <= 57) numbers++;
		}
		if (numbers + letters == 6)
			return true;
		else
			return false;
	}

	public void createMacro(String line , int i) throws Exception {
		String name = "";
		while(line.charAt(i) != 32){
			name += line.charAt(i);
			i++;
		}
		cf.createMacro(name,read(line.substring(i)));
	}
}
