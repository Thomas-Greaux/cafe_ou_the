package CoT.bfck.Macro;

import CoT.bfck.Command.Command;
import CoT.bfck.Exception.ImpossibleIndexException;
import CoT.bfck.Exception.OutOfCapacityException;
import CoT.bfck.Memory.Memory;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author cafe_ou_the
 */
public class Macro implements Command {

    private String name;
    private ArrayList<Command> cmd = new ArrayList<Command>();
    private int[] param = {1,0};
    public Macro(String s, Macro m){
        name = s;
        cmd = m.getCommand();
        for(int i = 0 ; i < 2 ; i++) {
            param[i] = m.getParam(i);
        }
    }
    public Macro(String nom, String para1,ArrayList<Command> c ){
        name = nom;
        param[0] = Integer.parseInt(para1);
        cmd = c;
    }
    public Macro(String nom,ArrayList<Command> c ){
        name = nom;
        cmd = c;
    }
    public Macro(String s, String para1, String para2,ArrayList<Command> c){
        name = s;
        cmd = c;
        param[0] = Integer.parseInt(para1);
        param[1] = Integer.parseInt(para2);
    }

    public void setName(String n){
        name = n;
    }

    public void add(Command c){
        cmd.add(c);
    }

    public ArrayList<Command> getCommand(){
        return cmd;
    }

    public ArrayList<Command> getInstru(){
        ArrayList<Command> instru = new ArrayList<Command>();
        int compteur = 0;
        for (int i = 0; i < param[0]; i++) {
            for (Command c : cmd) {
                if(compteur == param[1])
                    instru.add(c);
                else compteur ++;
            }
            compteur = 0;
        }
        return instru;
    }

     public void execute(Memory m) throws ImpossibleIndexException, OutOfCapacityException {
    }

    public ArrayList<String> getProperties() {
        return null;
    }

    public String getNameShort() {
        String res = "";
        int compteur = 0;
        for (int i = 0; i < param[0]; i++) {
            for (Command c : cmd) {
                if(compteur == param[1])
                    res += c.getNameShort();
                else compteur ++;
            }
            compteur = 0;
        }
        return res;
    }

    public Color getRGBColor() {return null;}

    public String getName() {
        return name;
    }

    public int getParam(int i){
        return param[i];
    }

}
