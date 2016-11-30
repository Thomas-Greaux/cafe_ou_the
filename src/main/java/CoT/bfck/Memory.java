package CoT.bfck;

import CoT.bfck.Exception.FileDoesntExists;
import CoT.bfck.Exception.ImpossibleIndexException;
import CoT.bfck.Exception.OutOfCapacityException;

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
	}

	/**
	 * Increment the value of the byte at the current position.
	 * @throws OutOfCapacityException
	 */
	public void incr() throws OutOfCapacityException {
		if (memory[index] + 129 > 255 ) {
			throw new OutOfCapacityException("incr");
		}
			memory[index] = (byte) (memory[index] + 1);
	}

	/**
	 * Decrement the value of the byte at the current position.
	 * @throws OutOfCapacityException
	 */
	public void decr() throws OutOfCapacityException {
		if (memory[index] + 127 < 0) {
			throw new OutOfCapacityException("decr");
		}
		memory[index] = (byte) (memory[index] - 1);

	}

	/**
	 * Move the position to the right : increment the index.
	 * @throws ImpossibleIndexException
	 */
	public void right() throws ImpossibleIndexException {
		if (this.index < 30000)
			this.index++;
		else {
			throw new ImpossibleIndexException("right");
		}
	}

	/**
	 * Move the position to the left : decrement the index.
	 * @throws ImpossibleIndexException
	 */
	public void left() throws ImpossibleIndexException {
		if (this.index > 0 )
			this.index--;
		else {
			throw new ImpossibleIndexException("left");
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

	/**
	 * Method used to display the cell who changed in the stdout
	 */
	public void display() {
		System.out.println();
		for (int i = 0; i < 30000; i++) {
			if (memory[i] != -128)
				System.out.println("C" + i + ": " + (memory[i]+128) + " ");
		}
	}

	/**
	 * Method used to display the cell, and store it in a String
	 */
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

	/**
	 * Modify the value of the String inFile to correspond with the name of the file we want to read in
	 * @param filename
	 * @throws FileDoesntExists
	 */
	public void setIn(String filename) throws FileDoesntExists{
		io_stream.setIn(filename);
	}

	/**
	 * Modify the value of the String outFile to correspond with the name of the file we want to write in
	 * @param filename
	 * @throws IOException
	 */
	public void setOut(String filename)throws IOException{
		io_stream.setOut(filename);
	}

	/**
	 * Accessor of the current pointed cell of the memory
	 * @return value of the pointer at the index position
	 */
	public int getValue(){
        Metrics.DATA_READ++;
		return memory[index]+128;
	}

	/**
	 * Close the io_stream
	 */
	public void close_stream(){
		io_stream.close();
    }
}
