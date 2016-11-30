package CoT.bfck.Macro;

import CoT.bfck.Command.Command;
import CoT.bfck.Memory;

import javax.crypto.Mac;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by user on 28/11/2016.
 */
public class Macro implements Command {

    private String name;
    private ArrayList<Command> cmd = new ArrayList<Command>();
    private int nbExe;

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

    public void setNbExe(int n){nbExe = n;}

    public ArrayList<Command> getCommand(){
        return cmd;
    }

    public void execute(Memory m) throws Exception {
        for(int i = 0 ; i < nbExe ; i++) {
            for (Command c : cmd) {
                c.execute(m);
            }
        }
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
