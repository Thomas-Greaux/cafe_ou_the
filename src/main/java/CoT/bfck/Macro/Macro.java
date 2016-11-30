package CoT.bfck.Macro;

import CoT.bfck.Command.Command;
import CoT.bfck.Command.Out;
import CoT.bfck.Exception.ImpossibleIndexException;
import CoT.bfck.Exception.OutOfCapacityException;
import CoT.bfck.Memory;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author cafe_ou_the
 */
public class Macro implements Command {

    private String name;
    private ArrayList<Command> cmd = new ArrayList<Command>();
    private int nbExeTemp = 1;
    private int[] param = {1,0};

    public Macro(String s, Macro m){
        name = s;
        cmd = m.getCommand();
        nbExeTemp = m.getNbExeTemp();
        for(int i = 0 ; i < 2 ; i++) {
            param[i] = m.getParam(i);
        }
    }

    public Macro(String s[],ArrayList<Command> c){
        name = s[0];
        cmd = c;
        if(s.length > 2){
            for(int i = 1 ; i < s.length -1 ; i ++){
                param[i-1] = Integer.parseInt(s[i]);
            }
        }
        if(param[1] > c.size()) {
            System.out.println("Argument num�ro 2 invalide");
            System.exit(1);
        }
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
        int compteur = 0;
        for(int j = 0 ; j < nbExeTemp ; j ++) {
            for (int i = 0; i < param[0]; i++) {
                for (Command c : cmd) {
                    if(compteur == param[1])
                        c.execute(m);
                    else compteur ++;
                }
                compteur = 0;
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

    public int getNbExeTemp(){
        return nbExeTemp;
    }

    public int getParam(int i){
        return param[i];
    }

}
