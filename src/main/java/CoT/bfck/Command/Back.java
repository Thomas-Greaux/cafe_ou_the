package CoT.bfck.Command;

import CoT.bfck.Memory;

import java.awt.Color;
import java.util.ArrayList;

public class Back implements Command {

	public Color colorRGB = new Color(255, 0, 0);
	public String colorHexa = new String("ff0000");
	public String name = new String("BACK");
  	public String nameShort = new String("]");

	public void execute(Memory m) throws Exception {
		m.back();
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