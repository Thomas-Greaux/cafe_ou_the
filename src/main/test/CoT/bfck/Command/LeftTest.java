package CoT.bfck.Command;

import CoT.bfck.Exception.ImpossibleIndexException;
import CoT.bfck.Factory.Colors;
import CoT.bfck.Memory.Memory;
import org.junit.Test;

import java.awt.*;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class LeftTest {
    Left left;
    Colors colors;
    Memory m;
    Right right;

    @Test
    public void leftTest() throws ImpossibleIndexException {
        colors = new Colors();
        left = new Left();
        m = new Memory();
        right = new Right();
        assertEquals("LEFT", left.getName());
        assertEquals(new Color(148, 0, 211), left.getRGBColor());
        assertEquals("<", left.getNameShort());
        ArrayList<String> tab = new ArrayList<String>();
        tab.add(left.getColorHexa());
        tab.add(left.getName());
        tab.add(left.getNameShort());
        assertEquals(tab, left.getProperties());
        right.execute(m);
        left.execute(m);
        assertEquals(0, m.getIndex());

    }
}
