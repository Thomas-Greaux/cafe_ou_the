package CoT.bfck.Memory;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by user on 13/01/2017.
 */
public class MetricTest {
    Metrics m;

    @Before
    public void setUp(){
        m = new Metrics();
    }

    @Test
    public void test(){
        m.setExecTime(3);
        m.setProgSize(4);
        m.display();
    }
}
