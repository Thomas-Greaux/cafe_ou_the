package CoT.bfck.Command;

import CoT.bfck.Exception.OutOfCapacityException;
import CoT.bfck.Factory.Colors;
import CoT.bfck.Memory.Memory;
import org.junit.Test;

import java.awt.*;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class DecrementTest {
    Decrement decrement;
    Increment increment;
    Colors colors;
    Memory m;

    @Test
    public void DecrementTest() throws OutOfCapacityException {
        colors = new Colors();
        decrement = new Decrement();
        increment = new Increment();
        m = new Memory();
        assertEquals("DECR", decrement.getName());
        assertEquals(new Color(75, 0, 130), decrement.getRGBColor());
        assertEquals("-", decrement.getNameShort());
        ArrayList<String> tab = new ArrayList<String>();
        tab.add(decrement.getColorHexa());
        tab.add(decrement.getName());
        tab.add(decrement.getNameShort());
        assertEquals(tab, decrement.getProperties());
        increment.execute(m);
        decrement.execute(m);
        assertEquals(0, m.getValue());
    }
}
