package graph.dfs;

import java.util.ArrayList;

import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.GraphUtils;

public class BellmanFordDAG {
	DirectedGraph graph;
	int sorg;
	Double[] L;
	
	public BellmanFordDAG(DirectedGraph g, int sorgente) {
		graph = g;
		sorg = sorgente;
	}
	
	private void BellFordDAG() {
		L = new Double[graph.getOrder()];
		DirectedGraph trasp = GraphUtils.reverseGraph(graph);
		
		for(int i = 0; i<graph.getOrder(); i++) {
			L[i] = Double.POSITIVE_INFINITY;
		}
		L[sorg] = 0.0;
		DFS help = new DFS(graph);
		ArrayList<Integer> topOrder;
		
		topOrder = help.topologicalOrder();
		for(Integer e:topOrder) {
			for(Edge ed:trasp.getOutEdges(e.intValue())) {
				// da qui
			}
		}
	}
	
	public Double getDist(int u) {
		
	}
	
	public ArrayList<Integer> getPath(int u) {
		
	}
	
	public boolean hasCycle() {
		
	}
}
