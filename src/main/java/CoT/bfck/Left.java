package CoT.bfck;

import java.awt.Color;
import java.util.ArrayList;

public class Left implements Command {


	public Color colorRGB = new Color(148, 0, 211);
	public String colorHexa = new String("9400d3");
	public String name = new String("LEFT");
	public String nameShort = new String("<");

	public void execute(Memory m) throws Exception {
		m.left();
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
