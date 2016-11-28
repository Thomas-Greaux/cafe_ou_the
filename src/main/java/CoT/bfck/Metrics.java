package CoT.bfck;

/**
 * Created by Thoma on 11/28/2016.
 */
public class Metrics {
    public static int PROG_SIZE = 0;
    public static double EXEC_TIME = 0;
    public static int EXEC_MOVE = 0;
    public static int DATA_MOVE = 0;
    public static int DATA_WRITE = 0;
    public static int DATA_READ = 0;

    public static void display() {
        System.out.println("Metrics:");
        System.out.println("\t PROG_SIZE: " + PROG_SIZE);
        System.out.println("\t EXEC_TIME: " + EXEC_TIME);
        System.out.println("\t EXEC_MOVE: " + EXEC_MOVE);
        System.out.println("\t DATA_MOVE: " + DATA_MOVE);
        System.out.println("\t DATA_WRITE: " + DATA_WRITE);
        System.out.println("\t DATA_READ: " + DATA_READ);
    }
}
