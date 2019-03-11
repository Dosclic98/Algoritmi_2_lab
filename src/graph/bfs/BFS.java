package graph.bfs;

import java.util.ArrayList;

import it.uniupo.graphLib.GraphInterface;

public class BFS {
	
	private static GraphInterface graph;
	
	private ArrayList<Integer> coda;
	private boolean[] scoperti;
	private int[] distanza;
	private ArrayList<Integer> risultato;
	
	public BFS(GraphInterface g) {
		// inizializzo l'array degli scoperti
		graph = g;
		reInit();
	}
	
	public ArrayList<Integer> getNodesInOrderOfVisit(int sorgente){
		reInit();
		
		coda.add(sorgente);
		scoperti[sorgente] = true;
		risultato.add(sorgente);
		// inizializza la distanza della sorgente
		distanza[sorgente] = 0;
		
		while(!coda.isEmpty()) {
			int tmp = coda.remove(0);
			for(Integer e:graph.getNeighbors(tmp)) {
				if(!discovered(e.intValue())) {
					coda.add(e);
					scoperti[e] = true;
					risultato.add(e);
					// la distanza di e è la distanza di tmp +1
					distanza[e] = distanza[tmp] + 1;
					// System.out.println("distanza di " + e + " = " + distanza[e]);
				}
			}
		}
		return risultato;
	}

	private boolean discovered(int elem) {
		return scoperti[elem];
	}
	
	private void reInit() {
		// inizializzo l'array delle distanze a -1
		distanza = new int[graph.getOrder()];
		for(int i = 0;i<graph.getOrder();i++) {
			distanza[i] = -1;
		}
		
		scoperti = new boolean[graph.getOrder()];
		for(int i = 0;i<graph.getOrder();i++) {
			scoperti[i] = false;
		}
		
		risultato = new ArrayList<Integer>(graph.getOrder());
		coda = new ArrayList<Integer>(graph.getOrder());
	}
	
	public int[] getDistance(int sorgente) {
		ArrayList<Integer> unused = getNodesInOrderOfVisit(sorgente);
		return distanza;
	}
}
