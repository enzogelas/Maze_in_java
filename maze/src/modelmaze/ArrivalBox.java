package modelmaze;
/**
 * Represente une case Arrivee
 * @author enzog
 */
public class ArrivalBox extends MazeBox {
	/**
	 * Cree une case Arrivee, dans un labyrinthe specifie et avec les coordonnees specifiees
	 * @param maze Le labyrinthe ou se trouve la case
	 * @param x La coordonnee horizontale de la case
	 * @param y La coordonnee verticale de la case
	 */
	public ArrivalBox(Maze maze,int x, int y) {
		super(maze,x, y);
	}
	@Override
	public boolean isArrival() {
		return true;
	}
}
