package CoT.bfck;

import CoT.bfck.Command.Command;

import java.util.ArrayList;

/**
 * Class containing the different options (--) which can be called.
 * @author cafe_ou_the
 */
public class OpOption {

    /**
     * This method is used to print in a String
     * @param commands ArrayList of commmands to print
     * @return String containing the commands in the shortened syntax
     */
    public static String rewrite(ArrayList<Command> commands) {
        StringBuilder res = new StringBuilder();
        int n = commands.size();
        for (int i = 0; i < n; i++) {
            if(commands.get(i) != null)  res.append(commands.get(i).getNameShort());
        }
        return res.toString();
    }

    /**
     * This method is used to print in the stdout the instruction in the shortened syntax
     * @param commands ArrayList of comamnds to print
     */
    public static void print(ArrayList<Command> commands) {
        int n = commands.size();
        for (int i = 0; i < n; i++) {
            if(commands.get(i) != null) System.out.print(commands.get(i).getNameShort());
        }
        System.out.print("\n");
    }

    /**
     * This method verify if the file is well parenthesized
     * @param commands ArrayList of commands to print
     */
    public static void check(ArrayList<Command> commands) {
        int n = commands.size();
        int compteur = 0;
        for(int i = 0; i < n; i++) {
            if (commands.get(i).getNameShort().equals("[")) compteur++;
            else if (commands.get(i).getNameShort().equals("]")) compteur--;

            if(compteur < 0) System.exit(4);
        }

        if(compteur > 0) System.exit(4);
    }

    /**
     * Return the extension of a file
     * @param filename name of the file
     * @return the extension of the file
     */
    public static String getFileExt(String filename) {
        int pos = filename.lastIndexOf(".");
        if (pos > -1) {
            return filename.substring(pos);
        } else {
            return "";
        }
    }
}
