package CoT.bfck.Command;

import CoT.bfck.Exception.ImpossibleIndexException;
import CoT.bfck.Exception.OutOfCapacityException;
import CoT.bfck.Memory.Memory;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by fabie on 08/01/2017.
 */
public class Function implements Command, Method {

    private String name;
    private ArrayList<Command> cmd = new ArrayList<Command>();

    public Function(String s, ArrayList<Command> c) {
        this.name=s;
        this.cmd=c;
    }

    public void execute(Memory m) throws OutOfCapacityException, ImpossibleIndexException {
        if(isFunctionPossible(m)==-1){
            System.out.println("Not enough memory to run the function : " + name + " .");
            System.exit(1);
        }else{
            int temp = m.getIndex(); //On récupère l'indexe intial
            m.setMemPoint(isFunctionPossible(m)); //On se place où l'on doit pour démarrer la fonction
            ArrayList<Integer> changes = new ArrayList<Integer>();

            for(Command c : cmd){
                c.execute(m);
                if(!changes.contains(m.getIndex()) && m.getIndex()!=temp){
                    changes.add(m.getIndex());
                }
            }
            int value = m.getValue();//On récupère valeur finale fonction
            m.setMemPoint(temp);//On se replace sur l'indexe intial
            m.setValue(value);

            for(Integer c : changes){
                m.setMemPoint(c);
                m.setValue(0);
            }

        }
    }

    public ArrayList<String> getProperties() {
        return null;
    }

    public String getNameShort() {
        String res = "";
        for (Command c : cmd) {
            res += c.getNameShort();
        }
        return res;
    }

    public Color getRGBColor() {
        return null;
    }

    public String getName() {
        return this.name;
    }

    public int isFunctionPossible(Memory m){
        int compt = 0;
        for(Command c : cmd){
            if(compt<0){
                System.out.println("Invalid function syntax : your current index is : -1");
                System.exit(1);
            }

            if(">".equals(c.getNameShort())){
                compt++;
            }else if("<".equals(c.getNameShort())){
                compt--;
            }
        }

        int temp = m.getIndex();
        ArrayList<Integer> free = m.displayList();
        free.add(1);
        int freeMemoryCompt = 0;
        int longestFreeMemory = 0;
        int compteur = 0;
        int start = 0;
        for(Integer l : free){
            if(l==0){
                freeMemoryCompt++;
            }else{
                if(freeMemoryCompt>longestFreeMemory){
                    longestFreeMemory=freeMemoryCompt;
                    start = compteur-longestFreeMemory;
                }
                freeMemoryCompt=0;
            }
            compteur++;
        }

        m.setMemPoint(temp);

        if(1+compt<=longestFreeMemory)
            return start;

        return -1;
    }

    public ArrayList<Command> getCommand(){
        return cmd;
    }
}
