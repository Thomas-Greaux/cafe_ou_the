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
	private ArrayList<Procedure> p = new ArrayList<Procedure>();
	private ArrayList<Function> f = new ArrayList<Function>();

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
		for(Procedure procedure : p){
			if(procedure.getName().equals(command)){
				return procedure;
			}
		}
		for(Function function : f){
			if(function.getName().equals(command)){
				return function;
			}
		}

		if(command.equals("000000")) //Plus de problèmes au niveau de l'indentation et pixels noirs
			return null;

		throw new NotACommandException();
	}

	public void createMacro(String[] s, ArrayList<Command> cmd) {
		Macro A = null;
		if(s.length == 2) {
			A = new Macro(s[0], cmd);
		}
		if(s.length == 3){
			A = new Macro(s[0],s[1],cmd);
		}
		if(s.length == 4){
			A = new Macro(s[0],s[1],s[2],cmd);
		}
		if(!isMacro(s[0]))
			m.add(A);
		else {
			System.out.println("Macro déjà existante");
			System.exit(1);
		}
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

	public boolean isProc(String n){
		for(Procedure s : p){
			if(s.getName().equals(n))
				return true;
		}
		return false;
	}

	public boolean isFunction(String n){
		for(Function f : f){
			if(f.getName().equals(n))
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

	public void createProc(String nom,String mem,ArrayList<Command> cmd){
		Procedure A = new Procedure(nom,cmd,Integer.parseInt(mem));
		if(!isProc(nom))
			p.add(A);
	}

	public void createFunction(String nom, ArrayList<Command> cmd){
		Function A = new Function(nom, cmd);
		if(!isProc(nom))
			f.add(A);
	}

	public Procedure getProc(String n){
		for(Procedure s : p){
			if(s.getName().equals(n))
				return s;
		}
		return null;
	}

	public Function getFunction(String n){
		for(Function s : f){
			if(s.getName().equals(n))
				return s;
		}
		return null;
	}
}