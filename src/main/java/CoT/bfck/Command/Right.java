package CoT.bfck.Command;

import CoT.bfck.Exception.ImpossibleIndexException;
import CoT.bfck.Memory.Memory;

import java.awt.Color;
import java.util.ArrayList;

/**
 * The RIGHT instruction
 * @author cafe_ou_the
 */
public class Right implements Command {

	private Color colorRGB = new Color(0, 0, 255);
	private String colorHexa = new String("0000ff");
	private String name = new String("RIGHT");
	private String nameShort = new String(">");

	/**
	 * Exécution of the command
	 * @param m memory
	 * @throws ImpossibleIndexException
	 */
	public void execute(Memory m) throws ImpossibleIndexException {
		m.right();
	}

	/**
	 * Accessor of the long syntax name of the function
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Accessor of the properties of the command
	 * @return Properties list
	 */
	public ArrayList<String> getProperties(){
		ArrayList<String> l = new ArrayList<String>();
		l.add(colorHexa);
		l.add(name);
		l.add(nameShort);
		return l;
	}

	/**
	 * Accessor of the short syntax
	 * @return Short name of the command
	 */
	public String getNameShort(){
		return this.nameShort;
	}

	/**
	 * Accessor of the RGB color of the command
	 * @return Color of the command
	 */
	public Color getRGBColor(){
		return this.colorRGB;
	}
}
