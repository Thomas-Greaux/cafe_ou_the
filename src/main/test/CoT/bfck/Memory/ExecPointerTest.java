package CoT.bfck.Memory;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 13/01/2017.
 */
public class ExecPointerTest {

    ExecPointer e;
    @Before
    public void setUp(){
        e = new ExecPointer();
    }

    @Test
    public void test(){
        e.incr();
        e.setValue(4);
        assertEquals(4,e.getValue());
    }
}
