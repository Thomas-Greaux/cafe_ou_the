package CoT.bfck.Reader;

import CoT.bfck.Command.Command;
import CoT.bfck.CommandFactory;
import CoT.bfck.Exception.NotACommandException;

import java.util.ArrayList;

/**
 * This class read the instructions given by the ReadFile and ReadImage.
 * @author cafe_ou_the
 */
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

    /**
     * Read is used to read the instructions given in a String.
     * The instructions are given by both the ReadFile and the ReadImage
      * @param line containing the instructions
     * @return ArayList of Command (in the right syntax to be executed)
     * @throws Exception
     */
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
			}else if(line.charAt(i) == ' ' || line.charAt(i) == '\t'){ //TODO Empty body ?

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

    /**
     * Verify if a line is a macro
     * @param line The curent line
     * @return true if the line is a Macro, false otherwise
     */
	public boolean isMacro(String line){
		return cf.isMacro(line);
	}

    /**
     * Verify if the line is full of character.
     * @param line the current line
     * @return true if every character is letter (a-z), false if it's a macro or it isn't full of minuscule letters
     */
	public boolean isChar(String line){
		for(int i=0;i<line.length();i++){
			if(!(line.codePointAt(i)>=65 && line.codePointAt(i)<=95)){
				return false;
			}
		}

		return !cf.isMacro(line);
	}

    /**
     * Verify if the line is a code of an hexadecimal color
     * @param line the current line
     * @return true if it is an hexadecimal color (6 letters min or maj and numbers), false otherwise
     */
	public boolean isHexaColor(String line) {
		int letters = 0;
		int numbers = 0;
		for (int i = 0; i < line.length(); i++) {
			if (line.codePointAt(i) >= 97 && line.codePointAt(i) <= 127) letters++;
			else if (line.codePointAt(i) >= 65 && line.codePointAt(i) <= 90) letters++;
			else if (line.codePointAt(i) >= 48 && line.codePointAt(i) <= 57) numbers++;
			if(line.codePointAt(i) == 32) return false;
		}

		return (numbers + letters == 6);
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
