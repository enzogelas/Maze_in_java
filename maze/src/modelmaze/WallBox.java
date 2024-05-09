package modelmaze;
/**
 * Represente un mur
 * @author enzog
 */
public class WallBox extends MazeBox {
	/**
	 * Cree un mur, dans un labyrinthe specifie et avec les coordonnees specifiees
	 * @param maze Le labyrinthe ou se trouve le mur
	 * @param x La coordonnee horizontale du mur
	 * @param y La coordonnee verticale du mur
	 */
	public WallBox(Maze maze, int x, int y) {
		super(maze,x, y);
	}
	@Override
	public boolean isWall() {
		return true;
	}

}

