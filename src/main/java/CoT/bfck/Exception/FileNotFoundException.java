package CoT.bfck.Exception;

/**
 * Created by fabien on 24/11/16.
 */
public class FileNotFoundException extends Exception {
    public FileNotFoundException(String message) {
        super(message);
        System.exit(1);
    }
}
