package CoT.bfck;

import CoT.bfck.Command.Command;

import java.util.ArrayList;

/**
 * Created by Thoma on 11/30/2016.
 */
public class JumpTable {
    ArrayList<Integer[]> table;

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
}
