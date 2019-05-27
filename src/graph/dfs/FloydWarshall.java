package graph.dfs;

import java.util.ArrayList;

import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.Edge;

public class FloydWarshall {
	DirectedGraph graph;
	Double[][] dist;
	int[][] maxNodo;
	
	public FloydWarshall(DirectedGraph g) {
		graph = g;
	}
	
	public Double getDist(int u, int v) {
		int n = graph.getOrder();
		dist = new Double[n][n];
		maxNodo = new int[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(graph.hasEdge(i, j) || i == j) maxNodo[i][j] = i;
				if(i == j) dist[i][j] = 0.0;
				else if(graph.hasEdge(i, j)) {
					for(Edge e:graph.getOutEdges(i)) {
						if(e.getHead() == j) dist[i][j] = (double) e.getWeight();
						assert(e.getTail() == i);
					}
				} 
				else dist[i][j] = Double.POSITIVE_INFINITY;
			}
		}
		
		for(int k = 0; k < n; k++) {
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
					if(dist[i][j] > dist[i][k] + dist[k][j]) maxNodo[i][j] = k;
				}
			}
		}
		
		return dist[u][v];
	}
	
	public boolean hasNegCycle() {
		getDist(0,0);
		
		int n = graph.getOrder();
		for(int i = 0; i < n; i++) {
			if(dist[i][i] < 0) return true;		
		}
		return false;
	}

	public ArrayList<Integer> getCammino(int u, int v){
		getDist(0,0);
		// da chiedere
		return null;
	} 
}
