package CoT.bfck;

import CoT.bfck.Command.*;

import java.io.IOException;
import java.util.ArrayList;

public class CommandFactory {
	ArrayList<String> properties = new ArrayList<String>();
	Increment i = new Increment();
	Decrement d = new Decrement();
	Left l = new Left();
	Right r = new Right();
	Jump j = new Jump();
	Back b = new Back();

	/**
	 * Used to make connection between the string given in parameter and
	 * the corresponding instruction. 
	 * 
	 * @param command
	 * @return the corresponding Command
	 * @throws Exception 
	 */
	public Command getCommand(String command) throws Exception {
		if (command == null) {
			return null;
		}
		properties = i.getProperties();
		for(String p : properties)
			if(p.equals(command)){return i;}
		
		properties = d.getProperties();
		for(String p : properties)
			if(p.equals(command)){return d;}
		
		properties = l.getProperties();
		for(String p : properties)
			if(p.equals(command)){return l;}
		
		properties = r.getProperties();
		for(String p : properties)
			if(p.equals(command)){return r;}
		
		properties = b.getProperties();
		for(String p : properties)
			if(p.equals(command)){return b;}
		
		properties = j.getProperties();
		for(String p : properties)
			if(p.equals(command)){return j;}
		
		throw new IOException();
	}
}