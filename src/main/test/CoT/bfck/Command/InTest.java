package CoT.bfck.Command;

import CoT.bfck.Factory.Colors;
import CoT.bfck.Memory.Memory;
import org.junit.Test;

import java.awt.*;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class InTest {
    In in;
    Colors colors;

    @Test
    public void InTest(){
        colors = new Colors();
        in = new In();
        assertEquals("IN", in.getName());
        assertEquals(new Color(255, 255, 0), in.getRGBColor());
        assertEquals(",", in.getNameShort());
        ArrayList<String> tab = new ArrayList<String>();
        tab.add(in.getColorHexa());
        tab.add(in.getName());
        tab.add(in.getNameShort());
        assertEquals(tab, in.getProperties());
    }
}
