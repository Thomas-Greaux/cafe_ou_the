package CoT.bfck.Command;

import CoT.bfck.Memory;
import CoT.bfck.Metrics;

import java.awt.Color;
import java.util.ArrayList;

public class Increment implements Command {

	protected Color colorRGB = new Color(255, 255, 255);
	protected String colorHexa = new String("ffffff");
	protected String name = new String("INCR");
	protected String nameShort = new String("+");

	public void execute(Memory m) throws Exception {
		Metrics.DATA_WRITE++;
		m.incr();
	}
	
	public ArrayList<String> getProperties(){
		ArrayList<String> l = new ArrayList<String>();
		l.add(colorHexa);
		l.add(name);
		l.add(nameShort);
		return l;
	}
	public String getName() {
		return this.name;
	}

	
	public String getNameShort(){
		return this.nameShort;
	}
	
	public Color getRGBColor(){
		return this.colorRGB;
	}
}
