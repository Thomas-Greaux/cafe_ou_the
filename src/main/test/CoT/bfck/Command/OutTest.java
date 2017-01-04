package CoT.bfck.Command;

import CoT.bfck.Factory.Colors;
import CoT.bfck.Memory.Memory;
import org.junit.Test;

import java.awt.*;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class OutTest {
    Out out;
    Colors colors;
    Memory m;

    @Test
    public void OutTest(){
        colors = new Colors();
        out = new Out();
        m = new Memory();
        assertEquals("OUT", out.getName());
        assertEquals(new Color(0, 255, 0), out.getRGBColor());
        assertEquals(".", out.getNameShort());
        ArrayList<String> tab = new ArrayList<String>();
        tab.add(out.getColorHexa());
        tab.add(out.getName());
        tab.add(out.getNameShort());
        assertEquals(tab, out.getProperties());
        out.execute(m);
    }
}
