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
	// the current index (= position in.bf the table)
	private int index;
	private String in_file;
	private String out_file;

	private IOStream io_stream = new IOStream();

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
	 * If a file is given in.bf argument, print the actual value in.bf it. If not, print it in.bf the terminal.
	 */

	public void out(){
		io_stream.out( (byte) (memory[index]-128));
	}

	/**
	 * If a file is given in argument, read the actual value in.bf it and put it in.bf the actual memory[index]. If not, read it in.bf the terminal.
	 */
	public void in(){
        memory[index] = io_stream.in();
	}

	public void display() {
		System.out.println();
		for (int i = 0; i < 30000; i++) {
			if (memory[i] != -128)
				System.out.println("C" + i + ": " + (memory[i]+128) + " ");
		}
	}

    public String display_String() {
        String result = "";
        for (int i = 0; i < 30000; i++) {
            if (memory[i] != -128)
                result += "C" + i + ": " + (memory[i]+128) + " ";
        }
        return result;
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
		io_stream.setIn(filename);
	}

	public void setOut(String filename){
		io_stream.setOut(filename);
	}

	public int getValue(){
        Metrics.DATA_READ++;
		return memory[index]+128;
	}

	public void close_stream(){
        io_stream.close();
    }
}
