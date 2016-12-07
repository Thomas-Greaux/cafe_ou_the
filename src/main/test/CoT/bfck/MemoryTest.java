package CoT.bfck;

import CoT.bfck.Command.Command;
import CoT.bfck.Exception.ImpossibleIndexException;
import CoT.bfck.Exception.NotACommandException;
import CoT.bfck.Exception.OutOfCapacityException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @author cafe_ou_the
 */
public class MemoryTest {
    private Memory mem;
    private Controleur c;
    private ArrayList<Command> commandes;
    private CommandFactory cf;

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Before
    public void setUp(){
        String line = ("-p file.bf");
        String[] myLine = line.split(" ");
        c = new Controleur(myLine);
        commandes = new ArrayList<Command>();
        cf = new CommandFactory();
    }


    @Test
    public void incr() throws NotACommandException, ImpossibleIndexException, OutOfCapacityException, IOException {

        thrown.expect(NotACommandException.class);
        thrown.expectMessage("Out of capacity (in the method )");

        for(int i=0;i<256;i++)
            commandes.add(cf.getCommand("+"));
        c.execute(commandes);
    }


}