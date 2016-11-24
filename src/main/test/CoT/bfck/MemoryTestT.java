package CoT.bfck;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by cafe_ou_the on 24/11/16.
 */

public class MemoryTestT {
    Memory mem;
    @Before
    public void setUp() throws Exception {
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