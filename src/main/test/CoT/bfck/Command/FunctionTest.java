package CoT.bfck.Command;

import CoT.bfck.Exception.ImpossibleIndexException;
import CoT.bfck.Exception.OutOfCapacityException;
import CoT.bfck.Memory.Memory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 13/01/2017.
 */
public class FunctionTest {

    Function f;

    @Before
    public void setUp(){
        f = new Function("fonc",new ArrayList<Command>());
    }

    @Test
    public void test() throws ImpossibleIndexException, OutOfCapacityException {
        f.execute(new Memory());
        assertEquals(null,f.getProperties());
        assertEquals("fonc",f.getName());
        assertEquals(0,f.isFunctionPossible(new Memory()));
    }
}
