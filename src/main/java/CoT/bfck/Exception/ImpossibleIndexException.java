package CoT.bfck.Exception;

/**
 * Exception raised if a file is not found.
 * @author cafe_ou_the
 */
public class ImpossibleIndexException extends Exception {
    public ImpossibleIndexException(String message) {
        super("Impossible index (in the method " + message+ ").");
        System.out.println("Impossible index (in the method " + message+ ").");
        System.exit(2);
    }
}
