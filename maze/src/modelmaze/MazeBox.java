package modelmaze;

import graphmaze.Vertex;
import ui.MazeHexagon;
/**
 * Represente une case de labyrinthe
 * @author enzog
 */
public abstract class MazeBox implements Vertex {
	private final Maze maze ;
	private final int x ;
	private final int y ;
	
	public MazeBox(Maze maze, int x,int y) {
		this.maze = maze ;
		this.x = x ;
		this.y = y ;
	}
	/**
	 * Renvoie la coordonnee horizontale de la case
	 * @return Un entier : la coordonnee horizontale de la case
	 */
	public int getX() {
		return x;
	}
	/**
	 * Renvoie la coordonnee verticale de la case
	 * @return Un entier : La coordonnee verticale de la case
	 */
	public int getY() {
		return y;
	}
	/**
	 * Renvoie un chaine de caractere caracterisant la position de la case
	 * @return Cette chaine de caractere
	 */
	public String getLabel() {
		return("x= " + x + " y= " + y);
	}
	/**
	 * Pour savoir si la case est la case Depart
	 * @return Un booleen : true si et seulement si la case est une instance de DepartureBox
	 */
	public boolean isDeparture() {
		return false;
	}
	/**
	 * Pour savoir si la case est la case Arrivee
	 * @return Un booleen : true si et seulement si la case est une instance de ArrivalBox
	 */
	public boolean isArrival() {
		return false;
	}
	/**
	 * Pour savoir si la case est une case vide
	 * @return Un booleen : true si et seulement si la case est une instance de EmptyBox
	 */
	public boolean isEmpty() {
		return false;
	}
	/**
	 * Pour savoir si la case est un mur
	 * @return Un booleen : true si et seulement si la case est une instance de WallBox
	 */
	public boolean isWall() {
		return false;
	}
}
