package CoT.bfck.Reader;

import CoT.bfck.Command.Command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * This class read a file, line by line (there is only a command on each line).
 * Every line correspond to a keyword, this keyword is sent to the function
 * operation.
 * 
 * @author cafe_ou_the
 */

public class ReadFile extends Reader {

	/**
	 * Open the file and sent every line to the function operation.
     * @return all the commands stored on the file
	 * @throws Exception 
	 */

	public ArrayList<Command> readFile(String file) throws Exception{
		ArrayList<Command> commands = new ArrayList<Command>();
		try {

			InputStream ips = new FileInputStream(new File(file));
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);

			String line;
			while ((line = br.readLine()) != null) {
				commands.addAll(read(line));
			}
			br.close();
		}	
		catch (FileNotFoundException e){
			System.out.println("File doesn't exist");
			System.exit(1);
		}
		return commands;
	}
	
	public void checkFile(String file) throws Exception{
		try {
			InputStream ips = new FileInputStream(new File(file));
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String line;
			int c = 0;
			
			while ((line = br.readLine()) != null) {
				for(int i=0;i<shortened(line).length();i++){
					// � ranger dans une exception !
					if(c<0){
						System.out.println("Bad pharentesis");
						System.exit(3);
					}
					if(shortened(line).charAt(i)=='[')
						c++;
					else if(shortened(line).charAt(i)==']')
						c--;
				}
			}
			if(c != 0){
				System.out.println("Bad pharentesis");
				System.exit(3);
			}
			br.close();
		}
		catch (FileNotFoundException e){
			System.out.println("File doesn't exist");
			System.exit(1);
		}
	}
}