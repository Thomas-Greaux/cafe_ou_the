package CoT.bfck.Command;

import CoT.bfck.Exception.OutOfCapacityException;
import CoT.bfck.Memory.Memory;

import java.awt.Color;
import java.util.ArrayList;

/**
 * The DECR instruction
 * @author cafe_ou_the
 */
public class Decrement implements Command {

	private Color colorRGB = new Color(75, 0, 130);
	private String colorHexa = new String("4b0082");
	private String name = new String("DECR");
	private String nameShort = new String("-");

	/**
	 * Exécution of the command
	 * @param m memory
	 * @throws OutOfCapacityException
	 */
	public void execute(Memory m) throws OutOfCapacityException{
		m.decr();
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

	/**
	 * Acessor of the hexa color
	 * @return hexa color
	 */
	public String getColorHexa(){
		return this.colorHexa;
	}
}
