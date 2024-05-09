package graphmaze;
/**
 * Represente une fonction donnant le poids des aretes entre les sommets
 * @author enzog
 */
public interface Distance {
	/**
	 * Renvoie le poids de l'arete entre v et w, si elle existe
	 * @param v Un sommet
	 * @param w Un autre sommet
	 * @return La distance entre v et w, s'ils sont relies
	 */
	public int getDistance(Vertex v, Vertex w);
}
