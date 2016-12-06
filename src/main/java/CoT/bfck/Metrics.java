package CoT.bfck;

/**
 * Class used to print the different metrics.
 * @author cafe_ou_the
 */
public class Metrics {
    static long PROG_SIZE = 0;
    static long EXEC_TIME = 0;
    static long EXEC_MOVE = 0;
    static long DATA_MOVE = 0;
    static long DATA_WRITE = 0;
    static long DATA_READ = 0;


    public static void display() {
        System.out.println("\nMetrics:");
        System.out.println("\t PROG_SIZE: \t" + PROG_SIZE);
        System.out.println("\t EXEC_TIME: \t" + EXEC_TIME + "ms");
        System.out.println("\t EXEC_MOVE: \t" + EXEC_MOVE);
        System.out.println("\t DATA_MOVE: \t" + DATA_MOVE);
        System.out.println("\t DATA_WRITE: \t" + DATA_WRITE);
        System.out.println("\t DATA_READ: \t" + DATA_READ);
    }
}
