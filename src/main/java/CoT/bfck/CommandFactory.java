package CoT.bfck;

import CoT.bfck.Command.*;
import CoT.bfck.Exception.NotACommandException;
import CoT.bfck.Macro.Macro;

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
	In in = new In();
	Out out = new Out();
	ArrayList<Macro> m = new ArrayList<Macro>();

	/**
	 * Used to make connection between the string given in.bf parameter and
	 * the corresponding instruction.
	 * 
	 * @param command
	 * @return the corresponding Command
	 * @throws Exception 
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

		throw new NotACommandException("Une des commandes choisies n'existe pas.");
	}

	public void createMacro(String n, ArrayList<Command> cmd) {
		Macro A = new Macro(n,cmd);
		if(!isMacro(n))
			m.add(A);
	}

	public boolean isMacro(String n ){
		for(Macro s : m){
			if(s.getName().equals(n)) return true;
		}
		return false;
	}

	public Macro getMacro(String n){
		for(Macro s : m){
			if(s.getName().equals(n)) return s;
		}
		return null;
	}
}