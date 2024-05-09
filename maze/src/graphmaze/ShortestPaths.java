package graphmaze;

import java.util.HashMap;
import java.util.List;
/**
 * Represente l'arborescence des plus courts chemins partant du sommet source lors de l'algorithme de Dijkstra
 * @author enzog
 */
public interface ShortestPaths {
	/**
	 * Renvoie le pere du sommet v dans l'arborescence
	 * @param v Un sommet
	 * @return Un sommet : le pere du sommet v
	 */
	public Vertex pred(Vertex v);
	/**
	 * Definit le sommet w comme etant le pere du sommet v dans l'arborescence
	 * @param v
	 * @param w
	 */
	public void setPred(Vertex v,Vertex w); 
	/**
	 * Renvoie la chemin entre le sommet source et le sommet endVertex
	 * @param endVertex Un sommet 
	 * @return Une liste contenant les sommets du chemin le plus court du sommet source au sommet endVertex
	 */
	public List<Vertex> getShortestPaths(Vertex endVertex);
}

