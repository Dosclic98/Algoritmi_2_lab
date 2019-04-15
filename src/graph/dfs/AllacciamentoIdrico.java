package graph.dfs;

import it.uniupo.algoTools.MinHeap;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.UndirectedGraph;

public class AllacciamentoIdrico {
	private UndirectedGraph graph;
	int[][] costoScavo;
	int costoTubo;
	int puntoAllacciamento;
	
	private boolean[] scoperti;
	private int[] d;
	private UndirectedGraph tree;
	private int[] padri;	
	
	public AllacciamentoIdrico (UndirectedGraph mappa, int[][] costoScavo,
								int costoTubo, int puntoAllacciamento) {
		graph = mappa;
		this.costoScavo = costoScavo;
		this.costoTubo = costoTubo;
		this.puntoAllacciamento = puntoAllacciamento; 
		reInit();
	}
	
	public UndirectedGraph progettoComune() {
		reInit();
		int s = 0;
		scoperti[s] = true;
		
		MinHeap<Edge, Integer> minHeap = new MinHeap<Edge, Integer>();
		for(Edge e:graph.getOutEdges(s)) {
			minHeap.add(e, costoScavo[e.getTail()][e.getHead()]);
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
						minHeap.add(add, costoScavo[add.getTail()][add.getHead()]);
					}
				}
			}
		}
		return tree;		
	}
	
	public UndirectedGraph progettoProprietari() {
		
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
		padri = new int[graph.getOrder()];
		for(int i = 0;i<graph.getOrder();i++) {
			padri[i] = -1;
		}
		Integer inp = graph.getOrder();
		tree = new UndirectedGraph(inp.toString());		
	}
}
