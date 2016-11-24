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
			throw new FileNotFoundException(e.toString());
		}
		return commands;
	}
}