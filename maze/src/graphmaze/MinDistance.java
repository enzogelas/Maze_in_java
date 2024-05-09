package graphmaze;
/**
 * Represente une fonction qui attribue a chaque sommet la longueur du plus court chemin entre lui et le sommet source lors de l'algorithme de Dijkstra
 * @author enzog
 */
public interface MinDistance {
	/**
	 * Attribuer au sommet v la distance minimale d
	 * @param v Un sommet
	 * @param d Un entier
	 */
	public void setMinDistance(Vertex v,int d); 
	/**
	 * Renvoie la distance minimale de v au sommet source
	 * @param v Un sommet
	 * @return Un entier : la distance minimale de v au sommet source
	 */
	public int getMinDistance(Vertex v); 

}