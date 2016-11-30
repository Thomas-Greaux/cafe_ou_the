package CoT.bfck.Exception;

/**
 * Exception raised if a file is not found.
 * @author cafe_ou_the
 */
public class OutOfCapacityException extends Exception {
    public OutOfCapacityException(String method) {
        super("Out of capacity");
        System.out.println("Out of capacity (in the method " + method + ").");
        System.exit(1);
    }
}
