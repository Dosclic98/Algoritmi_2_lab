package graph.dfs;

import it.uniupo.algoTools.MinHeap;
import it.uniupo.algoTools.UnionByRank;
import it.uniupo.algoTools.UnionFind;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.UndirectedGraph;

public class Kruskal {
	UndirectedGraph graph;
	UndirectedGraph tree;
	
	public Kruskal(UndirectedGraph g) {
		graph = g;
	}
	
	public UndirectedGraph algoKruskal() {
		tree = new UndirectedGraph(graph.getOrder());
		MinHeap<Edge,Integer> minHeap = new MinHeap<Edge,Integer>(); 
		
		addEdges(minHeap);
		
		UnionFind uf = new UnionByRank(graph.getOrder());
		
		while(!minHeap.isEmpty()) {
			Edge tmp = minHeap.extractMin();
			int u = tmp.getTail();
			int w  = tmp.getHead();
			
			if(uf.find(u) != uf.find(w)) {
				tree.addEdge(tmp);
				uf.union(uf.find(u), uf.find(w));
			}
		}
		
		return tree;
	}
	
	private void addEdges(MinHeap<Edge,Integer> minHeap) {
		for(int i = 0;i<graph.getOrder();i++) {
			for(Edge e:graph.getOutEdges(i)) {
				minHeap.add(e, e.getWeight());
			}
		}
	}
}
