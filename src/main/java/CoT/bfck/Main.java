package CoT.bfck;

import CoT.bfck.Exception.FileDoesntExists;
import CoT.bfck.Exception.ImpossibleIndexException;
import CoT.bfck.Exception.NotACommandException;
import CoT.bfck.Exception.OutOfCapacityException;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws NotACommandException, IOException, OutOfCapacityException, ImpossibleIndexException, FileDoesntExists {
		Controleur c = new Controleur(args);
		c.run();
	}

}
