package CoT.bfck.Factory;

import CoT.bfck.Command.Command;
import CoT.bfck.Macro.Macro;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 04/01/2017.
 */
public class CommandFactoryTest {

    CommandFactory cf;

    @Before
    public void setUp(){
        cf = new CommandFactory();
    }

    @Test
    public void macro(){
        String[] s = {"Test","++"};
        cf.createMacro(s,new ArrayList<Command>());
        String[] c = {"Test3","2","+++"};
        cf.createMacro(c,new ArrayList<Command>());
        String[] r = {"Test4","2","3","+++"};
        cf.createMacro(r,new ArrayList<Command>());
        cf.createMacro("Test5","Test4");
        assertEquals(true,cf.isMacro("Test4"));
        assertEquals(null,cf.getMacro("lol"));
    }

    @Test
    public void procedure(){
        cf.createProc("proc","23",new ArrayList<Command>());
        assertEquals(true,cf.isProc("proc"));
        assertEquals(false,cf.isProc("proc2"));
        assertEquals(null,cf.getProc("proc2"));
        assertEquals("proc",cf.getProc("proc").getName());
    }
}
