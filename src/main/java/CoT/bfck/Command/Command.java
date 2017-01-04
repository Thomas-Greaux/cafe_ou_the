package CoT.bfck.Command;

import CoT.bfck.Exception.ImpossibleIndexException;
import CoT.bfck.Exception.OutOfCapacityException;
import CoT.bfck.Memory.Memory;

import java.awt.Color;
import java.util.ArrayList;

/**
 * The command interface
 * @author cafe_ou_the
 */
public interface Command {
	void execute(Memory m) throws OutOfCapacityException, ImpossibleIndexException;
	ArrayList<String> getProperties();
	String getNameShort();
	Color getRGBColor();
	String getName();
}
