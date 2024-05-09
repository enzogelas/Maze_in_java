package graphmaze;

import java.util.List;
/**
 * Represente un graphe
 * @author enzog
 */
public interface Graph {
	/**
	 * Renvoie les sommets du graphe
	 * @return Une liste qui contient tous les sommets de labyrinthe
	 */
	public List<Vertex> getAllVertexes();
	/**
	 * Renvoie les voisins du sommet v dans le graphe
	 * @param v Un sommet
	 * @return Une liste contenant les voisins du sommet v
	 */
	public List<Vertex> getSuccessors(Vertex v);
	/**
	 * Renvoie la largeur du labyrinthe
	 * @return Un entier : la largeur du labyrinthe
	 */
	public int getLargeur();
	/**
	 * Renvoie la hauteur du labyrinthe
	 * @return Un entier : la hauteur du labyrinthe
	 */
	public int getHauteur(); 
	/**
	 * Renvoie le nombre de sommets du graphe
	 * @return Un entier : le nombre de sommets du graphe
	 */
	public int getSize(); 
}
