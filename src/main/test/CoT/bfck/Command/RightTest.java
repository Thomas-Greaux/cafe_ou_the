package CoT.bfck.Command;

import CoT.bfck.Factory.Colors;
import org.junit.Test;

import java.awt.*;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class RightTest {
    Right right;
    Colors colors;

    @Test
    public void RightTest(){
        colors = new Colors();
        right = new Right();
        assertEquals("RIGHT", right.getName());
        assertEquals(new Color(0, 0, 255), right.getRGBColor());
        assertEquals(">", right.getNameShort());
        ArrayList<String> tab = new ArrayList<String>();
        tab.add(right.getColorHexa());
        tab.add(right.getName());
        tab.add(right.getNameShort());
        assertEquals(tab, right.getProperties());
    }
}
