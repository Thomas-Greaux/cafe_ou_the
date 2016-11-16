package CoT.bfck;

import java.awt.Color;
import java.util.ArrayList;

public class Decrement implements Command {

	public Color colorRGB = new Color(75, 0, 130);
	public String colorHexa = new String("4b0082");
	public String name = new String("DECR");
	public String nameShort = new String("-");

	public void execute(Memory m) throws Exception {
		m.decr();
	}
	
	public ArrayList<String> getProperties(){
		ArrayList<String> l = new ArrayList<String>();
		l.add(colorHexa);
		l.add(name);
		l.add(nameShort);
		return l;
	}
	
	public String getNameShort(){
		return this.nameShort;
	}
	
	public Color getRGBColor(){
		return this.colorRGB;
	}
}
