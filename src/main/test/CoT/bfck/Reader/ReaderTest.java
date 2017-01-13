package CoT.bfck.Reader;

import CoT.bfck.Command.Command;
import CoT.bfck.Command.Increment;
import CoT.bfck.Exception.NotACommandException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 13/01/2017.
 */
public class ReaderTest {

    public Reader r;

    @Before
    public void setUp(){
        r = new Reader();
    }

    @Test
    public void shortenedTest() throws NotACommandException {
        String s = "INCR";
        assertEquals("+",r.shortened(s));
        String s1 = "DECR";
        assertEquals("-",r.shortened(s1));
    }

    @Test
    public void isCharTest(){
        String s = "absu";
        assertEquals(false,r.isChar(s));
        assertEquals(true,r.isChar("INCR"));
    }

    @Test
    public void isHexaTest(){
        String s = "ffffff";
        assertEquals(true,r.isHexaColor(s));
        assertEquals(false,r.isHexaColor("12DHKEU"));
    }

    @Test
    public void onlySpaceTest(){
        assertEquals(true,r.onlySpaces("    "));
        assertEquals(false,r.onlySpaces("ez"));
    }

    @Test
    public void readerTest() throws NotACommandException {
        String s = "+++";
        String macro = "/MACRO2 +++";
        String proc = "void PROC 2000 ++";
        assertEquals(new ArrayList<Command>(),r.read("   "));
        assertEquals(new ArrayList<Command>(),r.read(macro));
        assertEquals(new ArrayList<Command>(),r.read(proc));
        assertEquals(3,r.read(s).size());
    }
}
