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
    private int[] param = {1,0};
    private int[] paramEx = {1,0};

    public Macro(String s, Macro m){
        name = s;
        cmd = m.getCommand();
        for(int i = 0 ; i < 2 ; i++) {
            param[i] = m.getParam(i);
            paramEx[i] = param[i];
        }

    }

    public Macro(String s[],ArrayList<Command> c){
        name = s[0];
        cmd = c;
        if(s.length > 2){
            for(int i = 1 ; i < s.length -1 ; i ++){
                param[i-1] = Integer.parseInt(s[i]);
                paramEx[i-1] = param[i-1];
            }
        }
        if(param[1] > c.size()) {
            System.out.println("Argument numéro 2 invalide");
            System.exit(1);
        }
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

    public void setParamEx(String[] tab){
        for(int i = 1 ; i < tab.length ; i ++){
            paramEx[i-1] = Integer.parseInt(tab[i]);
        }
    }

     public void execute(Memory m) throws ImpossibleIndexException, OutOfCapacityException {
        int compteur = 0;
         for (int i = 0; i < paramEx[0]; i++) {
            for (Command c : cmd) {
                if(compteur == paramEx[1])
                    c.execute(m);
                else compteur ++;
            }
            compteur = 0;
            }
         for(int i = 0 ; i < 2 ; i++) {
             paramEx[i] = param[i];
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

    public int getParam(int i){
        return param[i];
    }

}
