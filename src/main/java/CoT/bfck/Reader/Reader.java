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
				String []s = line.split(" ");
				if(isMacro(s[0])){
					if(s.length == 2) {
						cf.getMacro(s[0]).setNbExe(Integer.parseInt(s[1]));
					}
					list.add(cf.getCommand(s[0]));
					break;
				}
				else {
					list.add((cf.getCommand(Character.toString(line.charAt(i)))));
				}
			}
		}
		return list;
	}

	public boolean isMacro(String line){
		return cf.isMacro(line);
	}
	
	public boolean isChar(String l){
		for(int i=0;i<l.length();i++){
			if(!(l.codePointAt(i)>=65 && l.codePointAt(i)<=95)){
				return false;
			}
		}
		if(cf.isMacro(l)) return false;
		return true;
	}

	public boolean isHexaColor(String l) {
		int letters = 0;
		int numbers = 0;
		for (int i = 0; i < l.length(); i++) {
			if (l.codePointAt(i) >= 97 && l.codePointAt(i) <= 127) letters++;
			else if (l.codePointAt(i) >= 65 && l.codePointAt(i) <= 90) letters++;
			else if (l.codePointAt(i) >= 48 && l.codePointAt(i) <= 57) numbers++;
			if(l.codePointAt(i) == 32) return false;
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
		System.out.println(line.substring(i+1));
		cf.createMacro(name,read(line.substring(i+1)));
	}
}
