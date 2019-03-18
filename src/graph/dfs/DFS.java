package graph.dfs;

import java.util.ArrayList;

import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.GraphInterface;

public class DFS {
	
	private static GraphInterface graph;
	private GraphInterface tree;
	
	private ArrayList<Integer> coda;
	boolean[] scoperti;
	private ArrayList<Integer> risultato;

	
	public DFS(GraphInterface g) {
		// inizializzo l'array degli scoperti
		graph = g;
		reInit();
	}
	
	private void dfsVisit(int sorg) {
		scoperti[sorg] = true;
		for(Integer e:graph.getNeighbors(sorg)) {
			if(!discovered(e.intValue())) {
				tree.addEdge(sorg, e);
				dfsVisit(e);
			}
		}
	}
	
	public GraphInterface getTree(int sorg) {
		reInit();
		dfsVisit(sorg);
		return tree;
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
		tree = graph.create();
	}
}
