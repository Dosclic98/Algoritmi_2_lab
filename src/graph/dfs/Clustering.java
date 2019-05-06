package graph.dfs;

import it.uniupo.algoTools.MinHeap;
import it.uniupo.algoTools.UnionByRank;
import it.uniupo.algoTools.UnionFind;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.UndirectedGraph;

public class Clustering {
	UndirectedGraph graph;
	int numCluster;
	
	public Clustering(UndirectedGraph g, int numClu) {
		graph = g;
		numCluster = numClu;
	}
		
	public int spaziamento() {
		MinHeap<Edge,Integer> minHeap = new MinHeap<Edge,Integer>(); 
		addEdges(minHeap);
		UnionFind uf = new UnionByRank(graph.getOrder());
		
		while(!minHeap.isEmpty() && uf.getNumberOfSets() > numCluster) {
			Edge tmp = minHeap.extractMin();
			int u = tmp.getTail();
			int w  = tmp.getHead();
			
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
			w  = tmp.getHead();
			
			spaz = tmp.getWeight();
		} while(uf.find(u) == uf.find(w));
		
		return spaz;
	}
	
	private void addEdges(MinHeap<Edge,Integer> minHeap) {
		for(int i = 0;i<graph.getOrder();i++) {
			for(Edge e:graph.getOutEdges(i)) {
				minHeap.add(e, e.getWeight());
			}
		}
	}
	
	public boolean sameCluster(int a, int b) {
		MinHeap<Edge,Integer> minHeap = new MinHeap<Edge,Integer>(); 
		addEdges(minHeap);
		UnionFind uf = new UnionByRank(graph.getOrder());
		
		while(!minHeap.isEmpty() && uf.getNumberOfSets() > numCluster) {
			Edge tmp = minHeap.extractMin();
			int u = tmp.getTail();
			int w  = tmp.getHead();
			
			if(uf.find(u) != uf.find(w)) {
				uf.union(uf.find(u), uf.find(w));
			}
		}
		
		if(uf.find(a) == uf.find(b)) {
			return true;
		} else return false;
	}
	
}
