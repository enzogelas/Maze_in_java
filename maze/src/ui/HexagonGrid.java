package ui;
/**
 * Represente une grille d'hexagones.
 * Sert pour dessiner le labyrinthe dans l'application.
 * @author enzog
 */
public class HexagonGrid {
	
	private final int largeur;
	private final int hauteur;
	private final MazeHexagon[][] hexagons;
	/**
	 * Cree une grille d'hexagone, avec la largeur, la hauteur et les hexagones specifies
	 * @param largeur La largeur de la grille
	 * @param hauteur La hauteur de la grille
	 * @param hexagons Un tableau des hexagones presents dans la grille
	 */
	public HexagonGrid(int largeur, int hauteur, MazeHexagon[][] hexagons) {
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.hexagons = hexagons;
	}
	/**
	 * Renvoie la largeur de la grille
	 * @return Un entier : la largeur de la grille
	 */
	public int getLargeur() {
		return largeur;
	}
	/**
	 * Renvoie la hauteur de la grille
	 * @return Un entier : la hauteur de la grille
	 */
	public int getHauteur() {
		return hauteur;
	}
	/**
	 * Renvoie les hexagones presents dans la grille
	 * @return Un tableau d'hexagones representant la grille
	 */
	public MazeHexagon[][] getHexagons() {
		return hexagons;
	}
}
