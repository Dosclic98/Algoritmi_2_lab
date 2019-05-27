package graph.dfs;

import java.util.ArrayList;

import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.GraphUtils;

public class BellmanFord {
	DirectedGraph graph;
	int sorg;
	Double[][] L;
	int[] from;
	
	public BellmanFord(DirectedGraph g, int sorgente) {
		graph = g;
		sorg = sorgente;
	}
	
	private void BellFord() {
		L = new Double[graph.getOrder() + 1][graph.getOrder()];
		DirectedGraph trasp = GraphUtils.reverseGraph(graph);
		from = new int[graph.getOrder()];
		for(int i = 0; i < graph.getOrder(); i++) {
			from[i] = -1;
		}
		int n = graph.getOrder();
		for(int v = 0; v < n; v++) {
			L[0][v] = Double.POSITIVE_INFINITY;
		}
		
		L[0][sorg] = 0.0;
		
		for(int i = 1; i <= n; i++) {
			Edge min = null;
			int m = 1;
			
			for(int v = 0; v < n; v++) {
				m = 1;
				min = null;
				for(Edge e:trasp.getOutEdges(v)) {
					if(m == 1) {
						min = e;
						
					}
					else {
						if(L[i-1][min.getHead()] + min.getWeight() > L[i-1][e.getHead()] + e.getWeight()) {
							min = e;
						}
					}
					m++;
				}
				if(min == null) L[i][v] = L[i-1][v];
				else L[i][v] = Math.min(L[i-1][v], L[i-1][min.getHead()] + min.getWeight());
				// da aggiungere costruzone di from
			}
		}
	}
	
	public Double getDist(int u) {
		BellFord();
		int n = graph.getOrder();
		for(int v = 0; v < graph.getOrder(); v++) {
			if(L[n][v] < L[n-1][v]) return Double.NEGATIVE_INFINITY;
		}
		return L[graph.getOrder()][u];
	}
	
	public boolean hasNegCycle() {
		BellFord();
		int n = graph.getOrder();
		for(int v = 0; v < graph.getOrder(); v++) {
			if(L[n][v] < L[n-1][v]) return true;
		}
		return false;
	}
	
	public ArrayList<Integer> getPath(int u) {		
		BellFord();
		
	}
}
