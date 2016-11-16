package CoT.bfck.Command;

import CoT.bfck.Memory;

import java.awt.Color;
import java.util.ArrayList;

public class Right implements Command {

	public Color colorRGB = new Color(0, 0, 255);
	public String colorHexa = new String("0000ff");
	public String name = new String("RIGHT");
	public String nameShort = new String(">");

	public void execute(Memory m) throws Exception {
		m.right();
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
/*
	@Override
	public Color getColorRGB(){
		return this.colorRGB;
	}
	
	@Override
	public String getColorHexa(){
		return this.colorHexa;
	}
	
	@Override
	public String getName(){
		return this.name;
	}
	
	@Override
	public String getNameShort(){
		return this.nameShort;
	}
	*/
	public Color getRGBColor(){
		return this.colorRGB;
	}
}
