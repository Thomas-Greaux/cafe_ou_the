package CoT.bfck.Command;

import CoT.bfck.Memory;
import CoT.bfck.Metrics;

import java.awt.Color;
import java.util.ArrayList;

public class Jump implements Command {

	public Color colorRGB = new Color(255, 127, 0);
	public String colorHexa = new String("ff7f00");
	public String name = new String("JUMP");
  	public String nameShort = new String("[");

	public void execute(Memory m) throws Exception {
		Metrics.DATA_READ++;
		m.jump();
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
