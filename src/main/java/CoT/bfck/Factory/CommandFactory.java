package CoT.bfck.Factory;

import CoT.bfck.Command.*;
import CoT.bfck.Exception.NotACommandException;
import CoT.bfck.Macro.Macro;

import java.util.ArrayList;

/**
 * Command factory is used to make a relation between the commands and the readers.
 * @author cafe_ou_the
 */
public class CommandFactory {
	private ArrayList<String> properties = new ArrayList<String>();
	private Increment i = new Increment();
	private Decrement d = new Decrement();
	private Left l = new Left();
	private Right r = new Right();
	private Jump j = new Jump();
	private Back b = new Back();
	private In in = new In();
	private Out out = new Out();
	private ArrayList<Macro> m = new ArrayList<Macro>();

	/**
	 * Used to make connection between the string given in.bf parameter and
	 * the corresponding instruction.
	 * 
	 * @param command
	 * @return the corresponding Command
	 * @throws NotACommandException
	 */
	public Command getCommand(String command) throws NotACommandException{
        //System.out.println(command);

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

		properties = in.getProperties();
		for(String p : properties)
			if(p.equals(command)){return in;}

		properties = out.getProperties();
		for(String p : properties)
			if(p.equals(command)){return out;}

		for(Macro macro : m){
			if(macro.getName().equals(command)){
				return macro;
			}
		}


		if(command.equals("000000")) //Plus de problèmes au niveau de l'indentation et pixels noirs
			return null;

		throw new NotACommandException();
	}

	public void createMacro(String[] s, ArrayList<Command> cmd) {
		Macro A = new Macro(s,cmd);
		if(!isMacro(s[0]))
			m.add(A);
	}

	public void createMacro(String s, String macro){
		Macro A = new Macro(s,getMacro(macro));
		if(!isMacro(s))
			m.add(A);
	}

	public boolean isMacro(String n ){
		for(Macro s : m){
			if(s.getName().equals(n))
				return true;
		}
		return false;
	}

	public Macro getMacro(String n){
		for(Macro s : m){
			if(s.getName().equals(n))
				return s;
		}
		return null;
	}
}