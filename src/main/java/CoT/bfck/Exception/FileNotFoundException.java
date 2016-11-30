package CoT.bfck.Exception;

/**
 * Exception raised if a file is not found.
 * @author cafe_ou_the
 */
public class FileNotFoundException extends Exception {
    public FileNotFoundException(String message) {
        super(message);
        System.exit(1);

    }
}
