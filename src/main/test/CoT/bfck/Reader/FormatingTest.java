package CoT.bfck.Reader;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 13/01/2017.
 */
public class FormatingTest {
    public Formatting f;

    @Before
    public void setUp(){
        f = new Formatting();
    }

    @Test
    public void delComTest(){
        String s = "/MACRO +++ #zeze";
        assertEquals("/MACRO +++ ", f.deleteSyntaxAndComments(s));
        assertEquals("",f.delNullColor("000000"));
        String s1 = "/MACRO";
        assertEquals("/MACRO",f.deleteSyntaxAndComments(s1));
        String s2 = "\\n\\t";
        assertEquals("",f.deleteSyntaxAndComments(s2));
    }
}
