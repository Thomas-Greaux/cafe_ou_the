package CoT.bfck.Exception;

/**
 * Exception raised if an instruction isn't a command
 * @author cafe_ou_the
 */
public class NotACommandException extends Exception {
    public NotACommandException(String message) {
        super(message);
    }
}
