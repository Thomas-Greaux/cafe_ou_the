package CoT.bfck.Command;

import java.util.ArrayList;

/**
 * Interface to make sure that procedures and functions implements required methods
 * Created by GREAUX Thomas on 1/9/2017.
 */
public interface Method {
    String getName();
    ArrayList<Command> getCommand();
}
