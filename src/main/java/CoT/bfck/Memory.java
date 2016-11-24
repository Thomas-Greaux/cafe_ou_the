package CoT.bfck;

import java.io.*;
import java.util.Scanner;
/**
 * The class Memory contain every implemented operations (INCR, DECR, RIGHT,
 * LEFT) and has the role to make the instructions do their job.
 * 
 * @author cafe_ou_the
 */

public class Memory {

	// the table of cells
	private byte[] memory;
	// the current index (= position in the table)
	private int index;
	private String in_file;
	private String out_file;

	/**
	 * Create the memory : initializing the table and index
	 */

	public Memory() {
		memory = new byte[30000];
		for (int i = 0; i < 30000; i++) {
			memory[i] = -128;
		}
		index = 0;
		in_file = "";
		out_file = "";
	}

	/**
	 * Increment the value of the byte at the current position.
	 */

	public void incr() throws Exception {
		if (memory[index] + 129 > 255 ) {
			System.out.println("Out of capacity");
			System.exit(1);
		}
			memory[index] = (byte) (memory[index] + 1);
	}

	/**
	 * Decrement the value of the byte at the current position.
	 */

	public void decr() throws Exception {
		if (memory[index] + 127 < 0) {
			System.out.println("Out of capacity");
			System.exit(1);
		}
		memory[index] = (byte) (memory[index] - 1);

	}

	/**
	 * Move the position to the right : increment the index.
	 */

	public void right() throws Exception {
		if (this.index < 30000)
			this.index++;
		else {
			System.out.println("index impossible");
			System.exit(2);
		}
	}

	/**
	 * Move the position to the left : decrement the index.
	 */

	public void left() throws Exception {
		if (this.index > 0 )
			this.index--;
		else {
			System.out.println("index impossible");
			System.exit(2);
		}
			
	}

	/**
	 * If a file is given in argument, print the actual value in it. If not, print it in the terminal.
	 */

	public void out(){
		if(!out_file.equals("")){
			try{
				PrintWriter writer = new PrintWriter(out_file, "UTF-8");
				writer.print((char)( memory[index]+128));
				writer.close();
            } catch (Exception e) {
				System.out.println("Le chemin choisi n'existe pas.");
				System.exit(3);
			}
		}else{
			System.out.print((char)( memory[index]+128));
		}
	}

	/**
	 * If a file is given in argument, read the actual value in it and put it in the actual memory[index]. If not, read it in the terminal.
	 */
	public void in(){
		if(!in_file.equals("")){
			try{
				InputStream flux=new FileInputStream(in_file);
				InputStreamReader lecture=new InputStreamReader(flux);
				BufferedReader buff=new BufferedReader(lecture);
				int data=Integer.parseInt(buff.readLine());
				memory[index]= (byte) (data-128);
				buff.close();
			}catch (FileNotFoundException e) {
				System.out.println("Le fichier n'existe pas.");
				System.exit(3);
			}catch (IOException e){}
		}else{
			Scanner sc = new Scanner(System.in);
			int i = sc.nextInt();
			memory[index]= (byte) (i-128);
		}
	}

	public void display() {
		System.out.println();
		for (int i = 0; i < 30000; i++) {
			if (memory[i] != -128)
				System.out.println("C" + i + ": " + (memory[i]+128));
		}
	}

	/**
	 * Do nothing, the controleur handle jump & back
	 */
	public void back() {

	}

	/**
	 * Do nothing, the controleur handle jump & back
	 */
	public void jump() {

	}

	public void setIn(String filename){
		in_file = filename;
	}

	public void setOut(String filename){
		out_file = filename;
	}

	public int getValue(){
		return memory[index]+128;
	}
}
