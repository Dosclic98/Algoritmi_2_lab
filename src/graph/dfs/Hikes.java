package graph.dfs;

import it.uniupo.algoTools.MinHeap;
import it.uniupo.algoTools.UnionByRank;
import it.uniupo.algoTools.UnionFind;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.UndirectedGraph;

public class Hikes {
	UndirectedGraph graph;
	
	public Hikes(UndirectedGraph rifugi) {
		graph = rifugi;
	}
	
	public int minDistanza(int numZones) {
		if(numZones < 0) throw new IllegalArgumentException("numZones negativo");
		MinHeap<Edge, Integer> minHeap = new MinHeap<Edge, Integer>();
		addEdges(minHeap);
		UnionFind uf = new UnionByRank(graph.getOrder());
		
		while(!minHeap.isEmpty() && uf.getNumberOfSets() > numZones) {
			Edge tmp = minHeap.extractMin();
			int u = tmp.getTail();
			int w = tmp.getHead();

			if(uf.find(u) != uf.find(w)) {
				uf.union(uf.find(u), uf.find(w));
			}			
		}
		
		int spaz;
		int u;
		int w;
		do {
			Edge tmp = minHeap.extractMin();
			u = tmp.getTail();
			w = tmp.getHead();
			
			spaz = tmp.getWeight();
		} while(uf.find(u) == uf.find(w));
		
		return spaz;
	}
	
	private void addEdges(MinHeap<Edge,Integer> minHeap) {
		for(int i = 0;i<graph.getOrder();i++) {
			for(Edge e:graph.getOutEdges(i)) {
				if(e.getWeight() < 0) throw new IllegalArgumentException("Peso arco negativo"); 
				minHeap.add(e, e.getWeight());
			}
		}
	}
	
}
