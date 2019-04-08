package graph.dfs;

import java.util.ArrayList;

import it.uniupo.algoTools.MinHeap;
import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.Edge;

public class Voli {
	private DirectedGraph graph;
	private boolean[] scoperti;
	private int[] d;
	private ArrayList<Integer> coda;
	private int[] padri;
	
	public Voli(DirectedGraph collegamenti) {
		graph = collegamenti;
		reInit();
	}
	
	public int tempo(int partenza, int destinazione) {
		if(partenza < 0 || destinazione < 0 || 
				partenza >= graph.getOrder() || destinazione >= graph.getOrder()) {
			throw new IllegalArgumentException();
		}
		
		reInit();
		
		coda.add(partenza);
		scoperti[partenza] = true;
		d[partenza] = 0;
		
		while(!coda.isEmpty()) {
			int tmp = coda.remove(0);
			for(Edge e:graph.getOutEdges(tmp)) {
				int u = e.getTail();
				assert(u == tmp);
				int w = e.getHead();
				if(scoperti[w] == false) {
					coda.add(w);
					scoperti[w] = true;
					d[w] = d[u] + e.getWeight();
					padri[w] = u;
				}
			}			
		}
	
		return d[destinazione];
	}

	public int scali(int partenza, int destinazione) {
		if(partenza < 0 || destinazione < 0 || 
				partenza >= graph.getOrder() || destinazione >= graph.getOrder()) {
			throw new IllegalArgumentException();
		}
		
		reInit();
		
		coda.add(partenza);
		scoperti[partenza] = true;
		d[partenza] = 0;
		
		while(!coda.isEmpty()) {
			int tmp = coda.remove(0);
			for(Edge e:graph.getOutEdges(tmp)) {
				int u = e.getTail();
				assert(u == tmp);
				int w = e.getHead();
				if(scoperti[w] == false) {
					coda.add(w);
					scoperti[w] = true;
					d[w] = d[u] + 1; 
				}
			}			
		}
		return d[destinazione];
	}
	
	public int tempoMinimo(int partenza, int destinazione) {
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
	
	public ArrayList<Edge> trattaVeloce (int sorgente, int destinazione){
		tempoMinimo(sorgente,destinazione);
		int tmp = destinazione;
		ArrayList<Edge> perc = new ArrayList<Edge>();
		while(padri[tmp]!=-1) {
			perc.add(0, new Edge(padri[tmp],tmp));
			tmp = padri[tmp];
		}
		if(tmp == sorgente) return perc;
		else return null;
	}
	
	public ArrayList<Integer> percorsoScali(int sorgente, int destinazione){
		tempo(sorgente,destinazione);
		int tmp = destinazione;
		ArrayList<Integer> perc = new ArrayList<Integer>();
		while(padri[tmp]!=-1) {
			perc.add(0, tmp);
			tmp = padri[tmp];
		}
		perc.add(0,tmp);
		if(tmp == sorgente) return perc;
		else return null;
		
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
		
		coda = new ArrayList<Integer>(graph.getOrder());
		padri = new int[graph.getOrder()];
		for(int i = 0;i<graph.getOrder();i++) {
			padri[i] = -1;
		}
		
	}
	
}
