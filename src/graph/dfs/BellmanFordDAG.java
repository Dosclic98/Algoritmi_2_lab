package graph.dfs;

import java.util.ArrayList;

import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.GraphUtils;

public class BellmanFordDAG {
	DirectedGraph graph;
	int sorg;
	Double[] L;
	int[] from;
	
	public BellmanFordDAG(DirectedGraph g, int sorgente) {
		graph = g;
		sorg = sorgente;
	}
	
	private void BellFordDAG() {
		L = new Double[graph.getOrder()];
		from = new int[graph.getOrder()];
		DirectedGraph trasp = GraphUtils.reverseGraph(graph);
		
		for(int i = 0; i<graph.getOrder(); i++) {
			L[i] = Double.POSITIVE_INFINITY;
			from[i] = -1;
		}
		L[sorg] = 0.0;
		DFS help = new DFS(graph);
		ArrayList<Integer> topOrder;
		
		topOrder = help.topologicalOrder();
		for(Integer e:topOrder) {
			System.out.println(e);
		}
		for(Integer e:topOrder) {
			int i = 1;
			Edge min = null;
			for(Edge ed:trasp.getOutEdges(e.intValue())) {
				if(i == 1) min = ed;
				else if(L[ed.getHead()] + ed.getWeight() < L[min.getHead()] + min.getWeight()) {
					min = ed;
				}
				i++;
			}
			if(min != null) {
				L[e] = L[min.getHead()] + min.getWeight();
				from[e] = min.getHead();
			}
		}
	}
	
	public Double getDist(int u) {
		if(hasCycle()) return Double.POSITIVE_INFINITY;
		
		BellFordDAG();
		return L[u];
	}

	public ArrayList<Integer> getPath(int u) {
		if(hasCycle()) return null;
		ArrayList<Integer> path = new ArrayList<Integer>();
		BellFordDAG();
		if(from[u] == -1)  {
			path.add(-1);
			return path;
		}
		else {
			int ultimo = u;
			path.add(0, u);
			while(ultimo != sorg) {
				ultimo = from[ultimo];
				path.add(0, ultimo);
			}
			
			return path;
		}		
	}

	public boolean hasCycle() {
		DFS help = new DFS(graph);
		
		return help.hasDirCycle();
	}
}
