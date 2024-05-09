package graphmaze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * Une classe pour implementer l'interface ShortestPaths
 * @author enzog
 *
 */
public class ShortestPathsImpl implements ShortestPaths{
	/**
	 * Cree un objet ShortestPathsImpl avec la Hashmap specifiee
	 * Dans la HashMap, les valeurs sont les peres de clefs
	 */
	private HashMap<Vertex,Vertex> shortestpaths;
	
	public ShortestPathsImpl(HashMap<Vertex,Vertex> shortestpaths) {
		this.shortestpaths = shortestpaths;
	}
	
	public Vertex pred(Vertex v) {
		return shortestpaths.get(v);	
	}

	public void setPred(Vertex v, Vertex w) { 
		shortestpaths.put(v, w);  		
	}
	public List<Vertex> getShortestPaths(Vertex endVertex){
		Vertex v = endVertex;
		List<Vertex> path = new ArrayList<Vertex>();
		path.add(v);
		while (shortestpaths.containsKey(v)) {
			v = pred(v);
			path.add(v);		
		}
		return path;
	}

}
