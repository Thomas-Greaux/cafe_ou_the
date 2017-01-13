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
public class ProcedureTest {
    Procedure p;

    @Before
    public void setUp(){
        p = new Procedure("nom",67,new ArrayList<Command>());
        p = new Procedure("nom",new ArrayList<Command>(),88);
    }

    @Test
    public void test() throws ImpossibleIndexException, OutOfCapacityException {
        p.execute(new Memory());
        assertEquals(null,p.getProperties());
        assertEquals("",p.getNameShort());
        assertEquals(null,p.getRGBColor());
        assertEquals("nom",p.getName());
    }
}
