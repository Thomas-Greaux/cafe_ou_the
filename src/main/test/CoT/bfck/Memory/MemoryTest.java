package CoT.bfck.Memory;

import CoT.bfck.Command.Command;
import CoT.bfck.Command.Out;
import CoT.bfck.Controleur;
import CoT.bfck.Exception.ImpossibleIndexException;
import CoT.bfck.Exception.NotACommandException;
import CoT.bfck.Exception.OutOfCapacityException;
import CoT.bfck.Factory.CommandFactory;
import CoT.bfck.Memory.Memory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

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
        mem = new Memory();    }

    @Test
    public void incrTest() throws OutOfCapacityException {
        for(int i = 0 ; i < 30 ; i++)
        {
            mem.incr();
        }
        assertEquals(30, mem.getValue());
    }

    @Test
    public void decrTest() throws OutOfCapacityException {
        incrTest();
        for(int i=0;i<30;i++){
            mem.decr();
        }
        assertEquals(0, mem.getValue());
    }

    @Test
    public void displayTest() throws OutOfCapacityException {
        incrTest();
        assertEquals("C0: 30 ", mem.display_String());
    }

    @Test
    public void incrOutOfCapacity() throws OutOfCapacityException {
        thrown.expect(OutOfCapacityException.class);
        thrown.expectMessage("Out of capacity (in the method incr).");
        while (true)
            mem.incr();
    }
/*
    @Test
    public void incr() throws NotACommandException, ImpossibleIndexException, OutOfCapacityException, IOException {
        thrown.expect(OutOfCapacityException.class);
        thrown.expectMessage("Out of capacity (in the method incr)");

        for(int i=0;i<256;i++)
            commandes.add(cf.getCommand("+"));

        c.execute(commandes);
    }

*/
}