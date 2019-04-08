package graph.dfs;

import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.GraphInterface;

import java.util.ArrayList;

import it.uniupo.algoTools.MinHeap;

public class Dijkstra {
	private GraphInterface graph;
	private boolean[] scoperti;
	private int[] d;
	private GraphInterface tree;
	private int[] padri;
	private ArrayList<Integer> coda;
	
	public Dijkstra(GraphInterface g) {
		graph = g;
		if(hasNegativeWeight()) throw new IllegalArgumentException("Arco con peso negativo");
		reInit();
	}
	
	private boolean hasNegativeWeight() {
		reInit();
		boolean neg = false;
		
		for(int i = 0; i<graph.getOrder(); i++) {
			if(scoperti[i] == false) neg = BFS(i);
			if(neg) return true;
		}
		return false;
	}
	
	public boolean BFS(int partenza) {
		if(partenza < 0 || partenza >= graph.getOrder()) {
			throw new IllegalArgumentException();
		}
		
		reInitIn();
		
		boolean neg = false;
		coda.add(partenza);
		scoperti[partenza] = true;
		
		while(!coda.isEmpty()) {
			int tmp = coda.remove(0);
			for(Edge e:graph.getOutEdges(tmp)) {
				int u = e.getTail();
				assert(u == tmp);
				int w = e.getHead();
				if(scoperti[w] == false) {
					coda.add(w);
					scoperti[w] = true;
					if(e.getWeight() < 0) neg = true;
				}
			}			
		}
	
		return neg;
	}
	

	public GraphInterface getAlberoCamminiMinimi(int sorg) {
		getDistanze(sorg);
		
		return tree;
	}
	public int getDistanzaPerc(int partenza, int destinazione) {
		if(partenza < 0 || destinazione < 0 || 
				partenza >= graph.getOrder() || destinazione >= graph.getOrder()) {
			throw new IllegalArgumentException();
		}
		reInit();
		
		scoperti[partenza] = true;
		d[partenza] = 0;
		
		MinHeap<Edge,Integer> heap = new MinHeap<Edge,Integer>();
		
		for(Edge e:graph.getOutEdges(partenza)) {
			heap.add(e, d[partenza] + e.getWeight());
		}
		
		while(!heap.isEmpty()) {
			Edge tmp = heap.extractMin();
			int u = tmp.getTail();
			int w = tmp.getHead();
			
			if(scoperti[w] == false) {
				scoperti[w] = true;
				d[w] = d[u] + tmp.getWeight();
				padri[w] = u;
				for(Edge z:graph.getOutEdges(w)) {
					if(scoperti[z.getHead()] == false) {
						heap.add(z, d[w] + z.getWeight());
					}
				}
			}
		}
		return d[destinazione];		
	}
	
	public ArrayList<Edge> getCamminoMinimo (int sorgente, int destinazione){
		if(sorgente < 0 || destinazione < 0 || 
				sorgente >= graph.getOrder() || destinazione >= graph.getOrder()) {
			throw new IllegalArgumentException();
		}
		
		getDistanzaPerc(sorgente,destinazione);
		int tmp = destinazione;
		ArrayList<Edge> perc = new ArrayList<Edge>();
		while(padri[tmp]!=-1) {
			perc.add(0, new Edge(padri[tmp],tmp));
			tmp = padri[tmp];
		}
		if(tmp == sorgente) return perc;
		else return null;		
	}
	
	public int[] getDistanze(int sorg) {
		if(sorg < 0 || sorg >= graph.getOrder()) throw new IllegalArgumentException();
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
				tree.addEdge(tmp);
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
		padri = new int[graph.getOrder()];
		for(int i = 0;i<graph.getOrder();i++) {
			padri[i] = -1;
		}
		coda = new ArrayList<Integer>(graph.getOrder());
		tree = graph.create();
	}

	public void reInitIn() {
		coda = new ArrayList<Integer>(graph.getOrder());
	}

}
