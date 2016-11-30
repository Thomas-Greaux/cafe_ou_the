package CoT.bfck.Exception;

/**
 * Exception raised if an instruction isn't a command
 * @author cafe_ou_the
 */
public class NotACommandException extends Exception {
    public NotACommandException() {
        super("Une des commandes choisies n'existe pas");
        System.out.println("Une des commandes choisies n'existe pas");
        System.exit(5);
    }
}
