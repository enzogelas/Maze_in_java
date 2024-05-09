package exception;
/**
 * Cette exception apparait quand il n'y a pas le bon nombre de cases Depart et Arrivee
 * @author enzog
 */
public class BoxException extends Exception {
	public BoxException(String s){
		super(s);
	}
}
