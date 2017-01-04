package CoT.bfck.Command;

import CoT.bfck.Exception.ImpossibleIndexException;
import CoT.bfck.Exception.OutOfCapacityException;
import CoT.bfck.Memory.Memory;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by user on 04/01/2017.
 */
public class Procedure implements Command {

    private String name;
    private ArrayList<Command> cmd = new ArrayList<Command>();
    private int caseM;

    public Procedure(String n, ArrayList<Command> c, int m){
        if(m > 30000 || m < 0){
            System.out.println("Memory case not found : " + m);
            System.exit(1);
        }
        name = n;
        cmd = c;
        caseM = m;
    }
    public Procedure(String s, int n, ArrayList<Command> c){
        name = s;
        caseM = n;
        cmd = c;
    }

    public void execute(Memory m) throws OutOfCapacityException, ImpossibleIndexException {
        int temp = m.getValue();
        m.setMemPoint(caseM);
        for(Command c : cmd){
            c.execute(m);
        }
        m.setMemPoint(temp);
    }

    public ArrayList<String> getProperties() {
        return null;
    }

    public String getNameShort() {
        String res = "";
        int compteur = 0;
        for (Command c : cmd) {
            res += c.getNameShort();
        }
        return res;
    }

    public Color getRGBColor() {
        return null;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Command> getCommand(){
        return cmd;
    }
}
