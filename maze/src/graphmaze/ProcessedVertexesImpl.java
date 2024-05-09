package graphmaze;

import java.util.HashSet;
/**
 * Une classe pour implementer l'interface ProcessedVertexes
 * @author enzog
 */
public class ProcessedVertexesImpl implements ProcessedVertexes{
	private HashSet<Vertex> processedvertexes;
	/**
	 * Cree un objet ProcessedVertexImpl avec le HashSet specifie
	 * @param processedvertexes Le HashSet specifie
	 */
	public ProcessedVertexesImpl(HashSet<Vertex> processedvertexes) {
		this.processedvertexes = processedvertexes;
	}
	
	public void addVertex(Vertex v) {
		processedvertexes.add(v);	
	}

	public boolean invertex(Vertex v) {
		return processedvertexes.contains(v);
	}

}
