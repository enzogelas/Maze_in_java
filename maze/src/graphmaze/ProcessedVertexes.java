package graphmaze;

import java.util.List;
/**
 * Represente l'ensemble des sommets traites lors de l'algorithme de Dijkstra
 * @author enzog
 */
public interface ProcessedVertexes {
	/**
	 * Ajoute le sommet v parmi les sommets traites
	 * @param v Le sommet traite
	 */
	public void  addVertex(Vertex v) ; 
	/**
	 * Pour savoir si le sommet v a ete traite
	 * @param v Un sommet
	 * @return Un booleen : true si et seulement si v a ete traite
	 */
	public boolean invertex(Vertex v) ; 
	}
