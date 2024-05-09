package exception;
/**
 * Cette exception apparait a la lecture d'un fichier, quand il ne respecte pas le format voulu
 * C'est a dire des lignes toutes de la meme longueur, et seulement les caracteres 'D', 'A', 'E' et 'W'
 * @author enzog
 */
public class MazeReadingException extends Exception{
	public MazeReadingException(String s) {
		super(s);
	}
}
