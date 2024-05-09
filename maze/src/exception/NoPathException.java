package exception;
/**
 * Cette exception apparait quand on cherche a afficher le chemin entre les cases Depart et Arrivee alors qu'il n'y en a pas
 * @author enzog
 */
public class NoPathException extends Exception{
	public NoPathException(String s) {
		super(s);
	}
}
