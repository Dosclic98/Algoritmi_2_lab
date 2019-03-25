package graph.dfs;

import java.util.ArrayList;

import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.GraphInterface;

public class BFS {
	
	private static GraphInterface graph;
	
	private ArrayList<Integer> coda;
	boolean[] scoperti;
	private ArrayList<Integer> risultato;
	private int[] order;
	private int[] padre;
	
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

	public GraphInterface bfsTree(int sorgente){
		reInit();
		
		coda.add(sorgente);
		scoperti[sorgente] = true;
		risultato.add(sorgente);
		GraphInterface tree = graph.create();
		
		while(!coda.isEmpty()) {
			int tmp = coda.remove(0);
			for(Integer e:graph.getNeighbors(tmp)) {
				if(!discovered(e.intValue())) {
					coda.add(e);
					scoperti[e] = true;
					risultato.add(e);
					// aggiungo l'arco scoperto a tree
					Edge tm = new Edge(tmp,e);
					// System.out.println(tm.toString());
					// la add edge non aggiunge l arco (0,1)
					// anche se effettivamente viene fatto il tentativo
					tree.addEdge(tm);
				}
			}
		}
		return tree;
	}

	public int[] getOrderOfVisit(int sorgente){
		reInit();
		
		coda.add(sorgente);
		scoperti[sorgente] = true;
		risultato.add(sorgente);
		order[sorgente] = 0;
		int cur = 0;
		
		while(!coda.isEmpty()) {
			int tmp = coda.remove(0);
			for(Integer e:graph.getNeighbors(tmp)) {
				if(!discovered(e.intValue())) {
					coda.add(e);
					scoperti[e] = true;
					risultato.add(e);
					order[e] = cur + 1;
					cur++;
				}
			}
		}
		return order;
	}
	
	private boolean discovered(int elem) {
		return scoperti[elem];
	}

	private void initPadre(int sorg) {
		reInit();
		
		padre = new int[graph.getOrder()];
		for(int i = 0;i<graph.getOrder();i++) {
			padre[i] = -1;
		}

		coda.add(sorg);
		scoperti[sorg] = true;
		risultato.add(sorg);
		
		
		while(!coda.isEmpty()) {
			int tmp = coda.remove(0);
			for(Integer e:graph.getNeighbors(tmp)) {
				if(!discovered(e.intValue())) {
					coda.add(e);
					scoperti[e] = true;
					risultato.add(e);
					padre[e] = tmp;
				}
			}
		}		
	}
/*	
	private boolean hashDirCyclePriv(int sorg) {
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
*/	
	private void reInit() {
		scoperti = new boolean[graph.getOrder()];
		for(int i = 0;i<graph.getOrder();i++) {
			scoperti[i] = false;
		}
		order = new int[graph.getOrder()];
		risultato = new ArrayList<Integer>(graph.getOrder());
		coda = new ArrayList<Integer>(graph.getOrder());
	}
}
