package CoT.bfck.Macro;

import CoT.bfck.Command.Command;
import CoT.bfck.CommandFactory;
import CoT.bfck.Exception.NotACommandException;
import CoT.bfck.Memory;
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
    {//m = new Macro("INCR",new ArrayList<Command>());
        mem = new Memory();
    }

    @Test
    public void setterGetter() throws NotACommandException {
        m.setName("HELLO");
        assertEquals("HELLO",m.getName());
        assertEquals("[]",m.getCommand().toString());
        m.add(new CommandFactory().getCommand("INCR"));
        assertEquals("INCR",m.getCommand().get(0).getName());
    }
}
