package CoT.bfck;

import CoT.bfck.Command.Command;

import java.util.ArrayList;

/**
 * Created by Thoma on 11/30/2016.
 */
public class OpOption {

    public static String rewrite(ArrayList<Command> commands) {
        StringBuilder res = new StringBuilder();
        int n = commands.size();
        for (int i = 0; i < n; i++) {
            if(commands.get(i) != null)  res.append(commands.get(i).getNameShort());
        }
        return res.toString();
    }

    public static void print(ArrayList<Command> commands) {
        int n = commands.size();
        for (int i = 0; i < n; i++) {
            if(commands.get(i) != null) System.out.print(commands.get(i).getNameShort());
        }
        System.out.print("\n");
    }

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
     *
     * @param filename
     * @return the extension of the file to read
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
