package modelmaze;
/**
 * Represente une case Depart
 * @author enzog
 */
public class DepartureBox extends MazeBox {
	/**
	 * Cree une case Depart, dans un labyrinthe specifie et avec les coordonnees specifiees
	 * @param maze Le labyrinthe ou se trouve la case
	 * @param x La coordonnee horizontale de la case
	 * @param y La coordonnee verticale de la case
	 */
	public DepartureBox(Maze maze,int x, int y) {
		super(maze,x, y);
	}
	@Override
	public boolean isDeparture() {
		return true;
	}


}