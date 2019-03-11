package graph.bfs;

import java.util.ArrayList;

import it.uniupo.graphLib.GraphInterface;

public class BFS {
	
	private static GraphInterface graph;
	
	private ArrayList<Integer> coda;
	boolean[] scoperti;
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
		
		while(!coda.isEmpty()) {
			int tmp = coda.remove(0);
			for(Integer e:graph.getNeighbors(tmp)) {
				if(!discovered(e.intValue())) {
					coda.add(e);
					scoperti[e] = true;
					risultato.add(e);
				}
			}
		}
		return risultato;
	}

	private boolean discovered(int elem) {
		return scoperti[elem];
	}
	
	private void reInit() {
		scoperti = new boolean[graph.getOrder()];
		for(int i = 0;i<graph.getOrder();i++) {
			scoperti[i] = false;
		}
		
		risultato = new ArrayList<Integer>(graph.getOrder());
		coda = new ArrayList<Integer>(graph.getOrder());
	}
}
