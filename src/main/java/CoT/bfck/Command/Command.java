package CoT.bfck.Command;

import CoT.bfck.Memory;

import java.awt.Color;
import java.util.ArrayList;

/** The Command interface */

public interface Command {

	void execute(Memory m) throws Exception;
	ArrayList<String> getProperties();
	String getNameShort();
	Color getRGBColor();
	/*
	Color getColorRGB();
	String getColorHexa();
	String getName();
	String getNameShort();
	*/
}
