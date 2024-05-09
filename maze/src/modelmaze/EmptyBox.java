package modelmaze;
/**
 * Represente un case vide
 * @author enzog
 */
public class EmptyBox extends MazeBox {
	/**
	 * Cree une case vide, dans un labyrinthe specifie et avec les coordonnees specifiees
	 * @param maze Le labyrinthe ou se trouve la case
	 * @param x La coordonnee horizontale de la case
	 * @param y La coordonnee verticale de la case
	 */
	public EmptyBox(Maze maze,int x, int y) {
		super(maze,x, y);
	}
	@Override
	public boolean isEmpty() {
		return true;
	}

}
