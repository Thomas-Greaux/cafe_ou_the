package CoT.bfck;

import CoT.bfck.Command.Command;

import java.util.ArrayList;

/**
 * Classe ...
 * @author cafe_ou_the
 */
public class JumpTable {
    private ArrayList<Integer[]> table;

    public JumpTable() {
        table = new ArrayList<Integer[]>();
    }

    public JumpTable(ArrayList<Command> commands){
        table = new ArrayList<Integer[]>();
        ArrayList<Integer> stack = new ArrayList<Integer>();
        int n = commands.size();

        for(int i = 0; i < n; i++){
            if(commands.get(i).getNameShort().equals("[")){
                stack.add(i);
            }

            else if(commands.get(i).getNameShort().equals("]")){
                table.add(new Integer[2]);
                table.get(table.size()-1)[0] = stack.remove(stack.size()-1);
                table.get(table.size()-1)[1] = i;
            }
        }

    }

    public void print(){
        Integer couple[] = new Integer[2];
        for(int i = 0; i < table.size(); i++){
            couple = table.get(i);
            System.out.println("Couple " + (i+1) + ": {" + couple[0] + ", " + couple[1] + "}");
        }
    }

    public int getComp(int x){
        int n = table.size();
        Integer couple[] = new Integer[2];
        for(int i = 0; i < n; i++){
            couple = table.get(i);
            if(couple[0] == x) return couple[1];
            else if(couple[1] == x) return couple[0];
        }
        return -1;
    }
}
