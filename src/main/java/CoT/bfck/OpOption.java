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
        for (Command command : commands) {
            if (command != null) res.append(command.getNameShort());
        }
        return res.toString();
    }

    /**
     * This method is used to print in the stdout the instruction in the shortened syntax
     * @param commands ArrayList of comamnds to print
     */
    public static void print(ArrayList<Command> commands) {
        for (Command command : commands) {
            if (command != null) System.out.print(command.getNameShort());
        }
        System.out.print("\n");
    }

    /**
     * This method verify if the file is well parenthesized
     * @param commands ArrayList of commands to print
     */
    public static void check(ArrayList<Command> commands) {
        int compteur = 0;
        for (Command command : commands) {
            if (command.getNameShort().equals("[")) compteur++;
            else if (command.getNameShort().equals("]")) compteur--;

            if (compteur < 0) System.exit(4);
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
