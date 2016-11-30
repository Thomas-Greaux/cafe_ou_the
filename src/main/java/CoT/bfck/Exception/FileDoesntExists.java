package CoT.bfck.Exception;

/**
 * Exception raised if a file is not found.
 * @author cafe_ou_the
 */
public class FileDoesntExists extends Exception {
    public FileDoesntExists(String filename, String message) {
        super("Le fichier \"" + filename + "\" attendu dans " + message + " est inexistant.");
        System.out.println("Le fichier \"" + filename + "\" attendu dans " + message + " est inexistant.");
        System.exit(3);
    }
}
