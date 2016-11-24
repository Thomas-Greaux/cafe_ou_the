package CoT.bfck;

/**
 * Created by user on 23/11/2016.
 */

import jdk.nashorn.internal.runtime.ECMAException;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class MemoryTest {

    Memory mem ;

    @Before
    public void setUp(){
        mem = new Memory();
    }
    @Test
    public void test30Incr20Decr () throws Exception {
        for(int i = 0 ; i < 30 ; i++)
        {
            mem.incr();
        }
        for(int j = 0 ; j < 20 ; j++)
        {
            mem.decr();
        }
        mem.display();
    }
    @Test
    public void test20IncrInto2000Right() throws Exception {
        for(int i = 0 ; i < 2000 ; i ++){
            for(int j = 0 ; j < 20 ; j++){
                mem.incr();
            }
            mem.right();
        }
        mem.display();
    }           
}
