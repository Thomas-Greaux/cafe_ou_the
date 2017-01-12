package CoT.bfck;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the Translator class
 * Created by GREAUX Thomas on 12/19/2016.
 */
public class TranslatorTest {
    Translator translator;

    @Before
    public void setUp(){
        translator = new Translator(Translator.default_path);
    }

    @Test
    public void stackableTest(){
        assertTrue(true);
    }
}
