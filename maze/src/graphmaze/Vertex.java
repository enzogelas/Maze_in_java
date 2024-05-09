package graphmaze;

import java.awt.Component;

import ui.MazeHexagon;
/**
 * Represente un sommet dans un graphe
 * @author enzog
 */
public interface Vertex {
	/**
	 * Renvoie une chaine de caractere qui caracterise la position du sommet
	 * @return Une chaine de caractere du type "x =  y = "
	 */
	public String getLabel() ; 
	/**
	 * Renvoie la coordonnee horizontale du sommet
	 * @return Un entier : la coordonnee horizontale du sommet
	 */
	public int getX() ; 
	/**
	 * Renvoie la coordonnee verticale du sommet
	 * @return Un entier : la coordonnee verticale du sommet
	 */
	public int getY() ; 
	/**
	 * Pour savoir si la case est la case Depart
	 * @return Un booleen : true si et seulement si la case est une instance de DepartureBox
	 */
	public boolean isDeparture();
	/**
	 * Pour savoir si la case est la case Arrivee
	 * @return Un booleen : true si et seulement si la case est une instance de ArrivalBox
	 */
	public boolean isArrival();
	/**
	 * Pour savoir si la case est une case vide
	 * @return Un booleen : true si et seulement si la case est une instance de EmptyBox
	 */
	public boolean isEmpty();
	/**
	 * Pour savoir si la case est un mur
	 * @return Un booleen : true si et seulement si la case est une instance de WallBox
	 */
	public boolean isWall();
}