package CoT.bfck.Macro;

import CoT.bfck.Command.Command;
import CoT.bfck.Command.In;
import CoT.bfck.Command.Increment;
import CoT.bfck.Exception.ImpossibleIndexException;
import CoT.bfck.Exception.NotACommandException;
import CoT.bfck.Exception.OutOfCapacityException;
import CoT.bfck.Factory.CommandFactory;
import CoT.bfck.Memory.Memory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 30/11/2016.
 */
public class MacroTest {

    private Macro m;
    private Memory mem;

    @Before
    public void setUp()
    {   m = new Macro("INCR",new ArrayList<Command>());
        mem = new Memory();

    }

    @Test
    public void constructor() {
        m = new Macro("INCR",new Macro("DECR",new ArrayList<Command>()));
        m = new Macro("INCR", "2",new ArrayList<Command>());
        m = new Macro("INCR", "2", "4", new ArrayList<Command>());
    }

    @Test
    public void setterGetter() throws NotACommandException {
        m.setName("HELLO");
        assertEquals("HELLO",m.getName());
        assertEquals("[]",m.getCommand().toString());
        m.add(new CommandFactory().getCommand("INCR"));
        assertEquals("INCR",m.getCommand().get(0).getName());
    }

    @Test
    public void command() throws OutOfCapacityException, ImpossibleIndexException {
        m = new Macro("INCR", "2", "4", new ArrayList<Command>());
        m.execute(mem);
        assertEquals(null,m.getProperties());
        assertEquals(null,m.getRGBColor());
        assertEquals(2,m.getParam(0));

    }

    @Test
    public void getShort(){
        assertEquals("",m.getNameShort());
        ArrayList cmd = new ArrayList<Command>();
        for(int i = 0; i < 10 ; i++)cmd.add(new Increment());
        m = new Macro("INCR","1","3",cmd );
        assertEquals("+++++++",m.getNameShort());
        assertEquals(7,m.getInstru().size());

    }
}
