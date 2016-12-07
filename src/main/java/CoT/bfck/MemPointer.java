package CoT.bfck;

/**
 * Created by Thoma on 12/3/2016.
 */
public class MemPointer {
    private int value;

    public MemPointer(){
        value = 0;
    }

    public void incr(){
        value++;
        Metrics.DATA_MOVE++;
    }

    public void decr(){
        value--;
        Metrics.DATA_MOVE++;
    }

    public int getValue(){
        return value;
    }
}
