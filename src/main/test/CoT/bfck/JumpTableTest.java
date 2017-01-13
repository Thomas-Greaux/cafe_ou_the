package CoT.bfck;

import CoT.bfck.Command.Back;
import CoT.bfck.Command.Command;
import CoT.bfck.Command.Jump;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 13/01/2017.
 */
public class JumpTableTest {

    JumpTable j;

    @Before
    public void setUp(){
        ArrayList<Command> a = new ArrayList<Command>();
        a.add(new Jump());
        a.add(new Back());
        j = new JumpTable(a);
    }

    @Test
    public void testCouple(){
        assertEquals(-1,j.getComp(4));
        j.print();
    }
}
