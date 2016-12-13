package CoT.bfck.Reader;

import CoT.bfck.Command.Command;
import CoT.bfck.Factory.CommandFactory;
import CoT.bfck.Exception.NotACommandException;

import java.util.ArrayList;

import CoT.bfck.Reader.Formatting;

/**
 * This class read the instructions given by the ReadFile and ReadImage.
 * @author cafe_ou_the
 */
public class Reader {

	private CommandFactory cf;
	private Formatting f;

	public Reader() {
		cf = new CommandFactory();
		f = new Formatting();
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
	 * @throws NotACommandException
	 */
	public String shortened(String s) throws NotACommandException{
		return cf.getCommand(s).getNameShort();
	}

	/**
	 * Read is used to read the instructions given in a String.
	 * The instructions are given by both the ReadFile and the ReadImage
	 * @param line containing the instructions
	 * @return ArayList of Command (in the right syntax to be executed)
	 * @throws NotACommandException
	 */
	public ArrayList<Command> read(String line) throws NotACommandException{
		ArrayList<Command> list = new ArrayList<Command>();
		line = f.deleteSyntaxAndComments(line);
		for(int i=0;i<line.length();i++){
			//Si on rencontre un '/', la suite est une macro on la cree
			if(line.charAt(i) == '/'){
				createMacro(line.substring(1));
				break;
			}else if(isChar(line) || isHexaColor(line)){
				list.add((cf.getCommand(line)));
				break;
			}else if(line.charAt(i) == ' ' ){
				//DO nothing : commentary
			}else{
				String []s = line.split(" ");
				if(isMacro(s[0])){
					if(s.length > 3) {
						System.out.println("Too much args for the macro");
						System.exit(4);
					}
					else{
						cf.getMacro(s[0]).setParamEx(s);
						list.add(cf.getMacro(s[0]));
						return list;
					}
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

	/**
	 *
	 * @param line
	 * @throws NotACommandException
	 */
	public void createMacro(String line) throws NotACommandException{
		String []s = line.split(" ");
		if(s.length < 2){
			System.out.println("Pas assez d'argument");
			System.exit(1);
		}
		if(cf.isMacro(s[1])) {
			cf.createMacro(s[0],s[1]); //Macro de macro
		}
		else {
			cf.createMacro(s,read(s[s.length-1]));
		}
	}
}
