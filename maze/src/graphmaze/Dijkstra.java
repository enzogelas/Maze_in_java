package graphmaze;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import exception.NoPathException;

import modelmaze.Maze;
/**
 * Une classe permettant d'executer l'algorithme de Dijkstra
 * @author enzog
 */
public class Dijkstra {
	/**
	 * Applique l'algorithme de Dijkstra pour trouver le plus court chemin de startVertex a endVertex
	 * @param graph Le graphe ou appliquer Dijkstra
	 * @param startVertex Le sommet de depart
	 * @param endVertex Le sommet d'arrivee
	 * @param processedVertexes Un ensemble de sommet representant les sommets traites lors de l'algorithme
	 * @param minDistance Une fonction (HashMap) associant a un sommet la longueur du plus court chemin entre startVertex et lui
	 * @param distance Permet d'avoir la distance entre deux sommets, s'ils sont relies par une arete
	 * @param shortestPaths Une fonction (HashMap) qui a chaque sommet lui attribue son pere dans l'arborescence des plus courts chemins partant de startVertex
	 * @return La fonction shortestPaths
	 * @throws NoPathException Si il n'y a pas de chemin entre startVertex et endVertex
	 */
	public static ShortestPaths dijkstra(Graph graph,Vertex startVertex, Vertex endVertex, ProcessedVertexes processedVertexes, MinDistance minDistance, Distance distance, ShortestPaths shortestPaths) throws NoPathException {
		processedVertexes.addVertex(startVertex);
		Vertex pivotVertex = startVertex;
		minDistance.setMinDistance(startVertex,0) ;
		List<Vertex> allVertexes = graph.getAllVertexes();
		for (Vertex v : allVertexes ) {
			if (v != startVertex)
				minDistance.setMinDistance(v, 1000000);
		}
		int comptErreur = 0;  
		while(!processedVertexes.invertex(endVertex)) {
				for(Vertex succv : graph.getSuccessors(pivotVertex)) {
					if(!processedVertexes.invertex(succv)) {
						int d = minDistance.getMinDistance(pivotVertex) + distance.getDistance(pivotVertex,succv); 
						if (d < minDistance.getMinDistance(succv)){
							minDistance.setMinDistance(succv,d);
							shortestPaths.setPred(succv,pivotVertex);
						}
					}
				}
				int min = 1000000;
				Vertex nextVertex = pivotVertex;
				for(Vertex v : allVertexes ) {
					if(!processedVertexes.invertex(v)) {
						int newmin = minDistance.getMinDistance(v);
						if (newmin < min) {
							min = newmin;
							nextVertex = v;
						}
					}
				}
				pivotVertex = nextVertex;
				processedVertexes.addVertex(pivotVertex);
				
				comptErreur++;
				if (comptErreur>graph.getSize())
				throw new NoPathException("boucle infinie");							
		}
	return shortestPaths;
	}
	/**
	 * Applique l'algorithme de Dijkstra pour trouver le plus court chemin de startVertex a endVertex 
	 * @param graph Le graphe ou appliquer Dijkstra
	 * @param startVertex Le sommet de depart
	 * @param endVertex Le sommet d'arrivee
	 * @return Une fonction (HashMap) qui a chaque sommet lui attribue son pere dans l'arborescence des plus courts chemins partant de startVertex
	 * @throws NoPathException Si il n'y a pas de chemin entre startVertex et endVertex
	 */
	public static ShortestPaths dijkstra(Graph graph, Vertex startVertex, Vertex endVertex) throws NoPathException {
		ProcessedVertexes pv = new ProcessedVertexesImpl(new HashSet<Vertex>());
		MinDistance md = new MinDistanceImpl(new HashMap<Vertex,Integer>());
		ShortestPaths sp = new ShortestPathsImpl(new HashMap<Vertex,Vertex>());
		Maze maze = (Maze) graph;
		return Dijkstra.dijkstra(graph,startVertex,endVertex,pv,md,maze,sp);
	}
}

