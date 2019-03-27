package graph.dfs;

import it.uniupo.graphLib.DirectedGraph;

public class SoftwareSystem {
	boolean[] scoperti;
	boolean[] terminati;
	private DirectedGraph graph;
	
	public SoftwareSystem(DirectedGraph system) {
		graph = system;
		reInit();
	}
	
	public boolean hasCycle() {
		reInit();
		for(int i = 0;i < graph.getOrder();i++) {
			if(scoperti[i] == false) {
				if(dfsVisit(i)) return true;
			}
		}
		return false;
	}
	
	private boolean dfsVisit(int sorg) {
		scoperti[sorg] = true;
		boolean cycle = false;
		for(Integer e:graph.getNeighbors(sorg)) {
			if(scoperti[e] == false) { 
				cycle = dfsVisit(e);
			}
			else if(scoperti[e] == true && terminati[e] == false) { 
				terminati[sorg] = true;
				return true;
			}
		}
		terminati[sorg] = true;
		return cycle;
	}
	
	private void reInit() {
		scoperti = new boolean[graph.getOrder()];
		for(int i = 0;i < graph.getOrder();i++) {
			scoperti[i] = false;
		}
		terminati = new boolean[graph.getOrder()];
		for(int i = 0;i < graph.getOrder();i++) {
			terminati[i] = false;
		}
		
	}
}
