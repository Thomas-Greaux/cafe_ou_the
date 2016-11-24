package CoT.bfck;

import CoT.bfck.Command.Command;
import CoT.bfck.Exception.NotACommandException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by fabien on 24/11/16.
 */
public class ControleurTest {
    Controleur c;
    CommandFactory cf;

    @Before
    public void setUp(){
        String s = "--rewrite -p bfck.bf ";
        c = new Controleur(s.split(" "));
        cf = new CommandFactory();
    }

    @Test
    public void rewriteTest() throws NotACommandException{
        ArrayList<Command> commands = new ArrayList<Command>();
        commands.add(cf.getCommand("INCR"));
        commands.add(cf.getCommand("DECR"));
        commands.add(cf.getCommand("RIGHT"));
        commands.add(cf.getCommand("LEFT"));
        commands.add(cf.getCommand("JUMP"));
        commands.add(cf.getCommand("BACK"));
        commands.add(cf.getCommand("IN"));
        commands.add(cf.getCommand("OUT"));
        assertEquals(c.rewrite(commands), "+-><[],.");
    }

    @Test
    public void getFileExtTest() {
        assertEquals(c.getFileExt("toto.java"), ".java");
        assertEquals(c.getFileExt("toto.bmp"), ".bmp");
        assertEquals(c.getFileExt("toto"), "");
        assertNotEquals(c.getFileExt("toto.java"), "java");
    }
}
