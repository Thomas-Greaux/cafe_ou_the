package CoT.bfck;

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
	 * 
	 * @param byte at the current position.
	 * @return the value of the modified byte.
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
	 * 
	 * @param byte at the current position.
	 * @return the value of the modified byte.
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
	
	public void back(){
		if((memory[index] != (byte) (-128))){
		}
	}
	
	public void jump(){
		if((memory[index] == (byte) (-128))){
		}
	}
	
	public int getCell(){
		return memory[index] + 128;
	}

	public void display() {
		for (int i = 0; i < 30000; i++) {
			if (memory[i] != -128)
				System.out.println("C" + i + ": " + (memory[i]+128));
		}
	}
}
