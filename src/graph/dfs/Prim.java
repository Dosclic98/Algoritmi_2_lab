package graph.dfs;

import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.UndirectedGraph;


import it.uniupo.algoTools.MinHeap;

public class Prim {
	private UndirectedGraph graph;
	private boolean[] scoperti;
	private int[] d;
	private UndirectedGraph tree;
	private int[] padri;
	int cost;
	
	public Prim(UndirectedGraph g) {
		graph = g;
		reInit();
	}
	
	public UndirectedGraph getMST() {
		reInit();
		int s = 0;
		scoperti[s] = true;
		
		MinHeap<Edge, Integer> minHeap = new MinHeap<Edge, Integer>();
		for(Edge e:graph.getOutEdges(s)) {
			minHeap.add(e, e.getWeight());
		}
		
		while(!minHeap.isEmpty()) {
			Edge tmp = minHeap.extractMin();
			int w = tmp.getHead();
			
			if(scoperti[w] == false) {
				scoperti[w] = true;
				tree.addEdge(tmp);
				for(Edge add:graph.getOutEdges(w)) {
					int v = add.getHead();
					if(scoperti[v] == false) {
						minHeap.add(add, add.getWeight());
					}
				}
			}
		}
		return tree;
	}

	public int getMSTCost() {
		reInit();
		int s = 0;
		scoperti[s] = true;
		
		MinHeap<Edge, Integer> minHeap = new MinHeap<Edge, Integer>();
		for(Edge e:graph.getOutEdges(s)) {
			minHeap.add(e, e.getWeight());
		}
		
		while(!minHeap.isEmpty()) {
			Edge tmp = minHeap.extractMin();
			int w = tmp.getHead();
			
			if(scoperti[w] == false) {
				scoperti[w] = true;
				tree.addEdge(tmp);
				cost += tmp.getWeight();
				for(Edge add:graph.getOutEdges(w)) {
					int v = add.getHead();
					if(scoperti[v] == false) {
						minHeap.add(add, add.getWeight());
					}
				}
			}
		}
		return cost;
	}
	
	/*
	public int getMSTCost () {
		
	}	
	*/
	
	public void reInit() {
		scoperti = new boolean[graph.getOrder()];
		for(int i = 0;i<graph.getOrder();i++) {
			scoperti[i] = false;
		}

		d = new int[graph.getOrder()];
		for(int i = 0;i<graph.getOrder();i++) {
			d[i] = -1;
		}
		padri = new int[graph.getOrder()];
		for(int i = 0;i<graph.getOrder();i++) {
			padri[i] = -1;
		}
		Integer inp = graph.getOrder();
		tree = new UndirectedGraph(inp.toString());
		cost = 0;
	}
}
