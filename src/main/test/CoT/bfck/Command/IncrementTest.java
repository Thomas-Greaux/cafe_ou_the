package CoT.bfck.Command;

import CoT.bfck.Factory.Colors;
import org.junit.Test;

import java.awt.*;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class IncrementTest {
    Increment increment;
    Colors colors;

    @Test
    public void IncrementTest(){
        colors = new Colors();
        increment = new Increment();
        assertEquals("INCR", increment.getName());
        assertEquals(new Color(255, 255, 255), increment.getRGBColor());
        assertEquals("+", increment.getNameShort());
        ArrayList<String> tab = new ArrayList<String>();
        tab.add(increment.getColorHexa());
        tab.add(increment.getName());
        tab.add(increment.getNameShort());
        assertEquals(tab, increment.getProperties());
    }
}
