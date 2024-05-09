package graphmaze;

import java.util.HashMap;
/**
 * Une classe pour implementer l'interface MinDistance
 * @author enzog
 */
public class MinDistanceImpl implements MinDistance{
	private HashMap<Vertex,Integer> mindistance;
	/**
	 * Cree un objet MinDistanceImpl avec la HashMap specifiee
	 * @param mindistance La HashMap specifiee
	 */
	public MinDistanceImpl(HashMap<Vertex,Integer> mindistance) {
		this.mindistance = mindistance;
	}
	
	public void setMinDistance(Vertex v, int d) {
		mindistance.put(v,d);		
	}
	
	public int getMinDistance(Vertex v) {
		return mindistance.get(v).intValue();
	}

}
