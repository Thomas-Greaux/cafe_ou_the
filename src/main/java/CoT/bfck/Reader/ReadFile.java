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
	
	private String file;
	
	public ReadFile(String args){
		file = args;
	}

	/**
	 * Open the file and sent every line to the function operation.
	 * @throws Exception 
	 */

	public ArrayList<Command> readFile() throws Exception{
		ArrayList<Command> list = new ArrayList<Command>();
		try {
			InputStream ips = new FileInputStream(new File(file));
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String line;
			while ((line = br.readLine()) != null) {
				list.addAll(read(line));
			}
			br.close();
		}	
		catch (FileNotFoundException e){
			System.out.println("File doesn't exist");
			System.exit(1);
		}
		return list;
	}
	
	/**
	 * Display the shortened version of the file.
	 * If this is already shortened, doesn't change.
	 * 
	 * @param file
	 * @throws Exception
	 */
	
	public String rewriteFile() throws Exception {
		try {
			InputStream ips = new FileInputStream(new File(file));
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			String res = null;
			
			//Todo : exceptions sur les instructions non valides

			while ((ligne = br.readLine()) != null) {
				if(shortened(ligne) == null){
					res = "Au moins une instruction est fausse";
					break;
				}
				res += shortened(ligne);
			}
			br.close();
			return res;
		}
		catch (FileNotFoundException e){
			System.out.println("File doesn't exist");
			System.exit(1);
		}
		return null;
	}
	
	public void checkFile() throws Exception{
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
			if(c>0){
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