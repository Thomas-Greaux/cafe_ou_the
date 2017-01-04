package CoT.bfck.Memory;

import CoT.bfck.Exception.FileDoesntExists;
import CoT.bfck.Exception.ImpossibleIndexException;
import CoT.bfck.Exception.OutOfCapacityException;

import java.io.*;

/**
 * The class Memory contain every implemented operations (INCR, DECR, RIGHT,
 * LEFT) and has the role to make the instructions do their job.
 * 
 * @author cafe_ou_the
 */
public class Memory {

	// the table of cells
	private byte[] memory;

	private IOStream io_stream = new IOStream();

	private MemPointer index;

	/**
	 * Create the memory : initializing the table and index
	 */
	public Memory() {
		memory = new byte[30000];
		for (int i = 0; i < 30000; i++) {
			memory[i] = -128;
		}

		index = new MemPointer();
	}

	/**
	 * Increment the value of the byte at the current position.
	 * @throws OutOfCapacityException
	 */
	public void incr() throws OutOfCapacityException {
		if (memory[index.getValue()] + 129 > 255 )
		    throw new OutOfCapacityException("incr");
		Metrics.DATA_WRITE++;
        memory[index.getValue()]++;
	}

	/**
	 * Decrement the value of the byte at the current position.
	 * @throws OutOfCapacityException
	 */
	public void decr() throws OutOfCapacityException {
		if (memory[index.getValue()] + 127 < 0)
			throw new OutOfCapacityException("decr");
		memory[index.getValue()]--;
        Metrics.DATA_WRITE++;
	}

	/**
	 * Move the position to the right : increment the index.
	 * @throws ImpossibleIndexException
	 */
	public void right() throws ImpossibleIndexException {
		if (index.getValue() < 30000) index.incr();
		else throw new ImpossibleIndexException("right");
	}

	/**
	 * Move the position to the left : decrement the index.
	 * @throws ImpossibleIndexException
	 */
	public void left() throws ImpossibleIndexException {
		if (index.getValue() > 0 ) index.decr();
		else {
			throw new ImpossibleIndexException("left");
		}
			
	}

	/**
	 * If a file is given in.bf argument, print the actual value in.bf it. If not, print it in.bf the terminal.
	 */
	public void out(){
        Metrics.DATA_READ++;
        io_stream.out( (byte) (memory[index.getValue()]-128));
	}

	/**
	 * If a file is given in argument, read the actual value in.bf it and put it in.bf the actual memory[index]. If not, read it in.bf the terminal.
	 */
	public void in(){
        Metrics.DATA_WRITE++;
        memory[index.getValue()] = io_stream.in();
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
        Metrics.DATA_READ++;
	}

	/**
	 * Do nothing, the controleur handle jump & back
	 */
	public void jump() {
        Metrics.DATA_READ++;
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
		return memory[index.getValue()]+128;
	}

	public void setMemPoint(int n){
		index.setValue(n);
	}

	public int getIndex(){
		return this.index.getValue();
	}

	/**
	 * Close the io_stream
	 */
	public void close_stream(){
		io_stream.close();
    }
}
