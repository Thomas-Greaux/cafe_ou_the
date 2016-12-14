package CoT.bfck.Memory;

/**
 * Created by Thoma on 12/3/2016.
 */
public class ExecPointer {
    private int value;

    public ExecPointer(){
        value = 0;
    }

    public void incr(){
        value++;
        Metrics.EXEC_MOVE++;
    }

    public void setValue(int i){
        value = i;
        Metrics.EXEC_MOVE++;
    }

    public int getValue(){
        return value;
    }
}
