package graph.dfs;

import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.GraphInterface;
import it.uniupo.algoTools.MinHeap;

public class Dijkstra {
	private GraphInterface graph;
	private boolean[] scoperti;
	private int[] d;
	
	public Dijkstra(GraphInterface g) {
		graph = g;
		reInit();
	}
	
	public int[] getDistanze(int sorg) {
		reInit();
		
		scoperti[sorg] = true;
		d[sorg] = 0;
		
		MinHeap<Edge,Integer> heap = new MinHeap<Edge,Integer>();
		
		for(Edge e:graph.getOutEdges(sorg)) {
			heap.add(e, d[sorg] + e.getWeight());
		}
		
		while(!heap.isEmpty()) {
			Edge tmp = heap.extractMin();
			int u = tmp.getTail();
			int w = tmp.getHead();
			
			if(scoperti[w] == false) {
				scoperti[w] = true;
				d[w] = d[u] + tmp.getWeight();
				for(Edge z:graph.getOutEdges(w)) {
					if(scoperti[z.getHead()] == false) {
						heap.add(z, d[w] + z.getWeight());
					}
				}
			}
		}
		return d;
	}
	
	public void reInit() {
		scoperti = new boolean[graph.getOrder()];
		for(int i = 0;i<graph.getOrder();i++) {
			scoperti[i] = false;
		}

		d = new int[graph.getOrder()];
		for(int i = 0;i<graph.getOrder();i++) {
			d[i] = -1;
		}
		
	}
}
