package CoT.bfck.Factory;

import CoT.bfck.Exception.NotACommandException;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 04/01/2017.
 */
public class ColorsTest {

    Colors c;

    @Before
    public void setUp(){
        c = new Colors();
    }

    @Test
    public void returnColor () throws NotACommandException {
        assertEquals(new Color(255,255,255),c.returnColor("ffffff"));
        assertEquals(new Color(0,0,0),c.returnColor("000000"));
    }

    @Test
    public void getHexa() throws IOException {
    }
}
