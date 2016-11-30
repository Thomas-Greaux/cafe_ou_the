package CoT.bfck.Macro;

import CoT.bfck.Command.Command;
import CoT.bfck.Command.Out;
import CoT.bfck.Exception.ImpossibleIndexException;
import CoT.bfck.Exception.OutOfCapacityException;
import CoT.bfck.Memory;

import javax.crypto.Mac;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author cafe_ou_the
 */
public class Macro implements Command {

    private String name;
    private ArrayList<Command> cmd = new ArrayList<Command>();
    private int nbExeTemp = 1;

    public Macro(String n){
        name = n;
    }

    public Macro(String n, ArrayList<Command> c){
        name = n;
        cmd = c;
    }

    public void setName(String n){
        name = n;
    }

    public void add(Command c){
        cmd.add(c);
    }

    public void setNbExe(int n){nbExeTemp = n;}

    public ArrayList<Command> getCommand(){
        return cmd;
    }

    public void execute(Memory m) throws ImpossibleIndexException, OutOfCapacityException {
        for(int i = 0 ; i < nbExeTemp ; i++) {
            for (Command c : cmd) {
                c.execute(m);
            }
        }
        nbExeTemp = 1;
    }

    public ArrayList<String> getProperties() {
        return null;
    }

    public String getNameShort() {
        return "";
    }

    public Color getRGBColor() {
        return null;
    }

    public String getName() {
        return name;
    }

    public String toString(){return "Macro";}

}
