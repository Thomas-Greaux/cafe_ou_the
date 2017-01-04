package CoT.bfck.Command;

import CoT.bfck.Factory.Colors;
import CoT.bfck.Memory.Memory;
import org.junit.Test;

import java.awt.*;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class BackTest {
    Back back;
    Colors colors;
    Memory m;

    @Test
    public void backTest(){
        colors = new Colors();
        back = new Back();
        m = new Memory();
        assertEquals("BACK", back.getName());
        assertEquals(new Color(255, 0, 0), back.getRGBColor());
        assertEquals("]", back.getNameShort());
        ArrayList<String> tab = new ArrayList<String>();
        tab.add(back.getColorHexa());
        tab.add(back.getName());
        tab.add(back.getNameShort());
        assertEquals(tab, back.getProperties());
        back.execute(m);
    }
}
