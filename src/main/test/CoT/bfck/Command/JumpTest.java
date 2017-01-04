package CoT.bfck.Command;

import CoT.bfck.Factory.Colors;
import CoT.bfck.Memory.Memory;
import org.junit.Test;

import java.awt.*;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class JumpTest {
    Jump jump;
    Colors colors;
    Memory m;

    @Test
    public void JumpTest(){
        colors = new Colors();
        jump = new Jump();
        m = new Memory();
        assertEquals("JUMP", jump.getName());
        assertEquals(new Color(255, 127, 0), jump.getRGBColor());
        assertEquals("[", jump.getNameShort());
        ArrayList<String> tab = new ArrayList<String>();
        tab.add(jump.getColorHexa());
        tab.add(jump.getName());
        tab.add(jump.getNameShort());
        assertEquals(tab, jump.getProperties());
        assertEquals(tab, jump.getProperties());
        jump.execute(m);
    }
}
