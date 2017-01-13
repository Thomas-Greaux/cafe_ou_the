package CoT.bfck.Memory;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 13/01/2017.
 */
public class MemPointerTest {

    MemPointer m;
    @Before
    public void setUp(){
        m = new MemPointer();
    }

    @Test
    public void test(){
        m.incr();
        m.decr();
        m.setValue(4);
        assertEquals(4,m.getValue());
    }
}
