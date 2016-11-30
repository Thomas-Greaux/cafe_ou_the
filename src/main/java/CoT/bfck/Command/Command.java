package CoT.bfck.Command;

import CoT.bfck.Memory;

import java.awt.Color;
import java.util.ArrayList;

/**
 * The command interface
 * @author cafe_ou_the
 */
public interface Command {

	void execute(Memory m) throws Exception;
	ArrayList<String> getProperties();
	String getNameShort();
	Color getRGBColor();
	String getName();
}
