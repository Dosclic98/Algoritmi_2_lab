package graph.dfs;

import java.util.ArrayList;

import it.uniupo.graphLib.UndirectedGraph;

public class Metro {
	UndirectedGraph graph;
	ArrayList<Integer> coda;
	private boolean[] scoperti;
	private int[] padri;
	
	public Metro(UndirectedGraph mappa) {
		graph = mappa;
		reInit();
	}
	
	public ArrayList<Integer> fermate(int stazPartenza, int stazArrivo){
		if(stazPartenza < 0 || stazArrivo >= graph.getOrder()) throw new IllegalArgumentException();
		
		reInit();
		BFSVisit(stazPartenza);
		ArrayList<Integer> path = new ArrayList<Integer>(graph.getOrder());
		int part = stazArrivo;
		if(existPath(stazPartenza, stazArrivo)) {
			while(part != -1 && part != stazPartenza) {
				path.add(0, part);
				part = padri[part];
			}
			path.add(0, stazPartenza);
			return path;
		}
		else return null;
	}
	
	private boolean existPath(int stazPartenza, int stazArrivo) {
		while(stazArrivo != -1 && stazArrivo != stazPartenza) {
			stazArrivo = padri[stazArrivo];
		}
		if(stazArrivo == stazPartenza)return true;
		else return false;
	}

	public void BFSVisit(int stazPartenza) {
		
		coda.add(stazPartenza);
		scoperti[stazPartenza] = true;
		
		while(!coda.isEmpty()) {
			int src = coda.remove(0);
			for(Integer e:graph.getNeighbors(src)) {
				if(scoperti[e] == false) {
					coda.add(e);
					scoperti[e] = true;
					padri[e] = src;
				}
			}
		}		
	}
	
	public void reInit() {
		coda = new ArrayList<Integer>(graph.getOrder());
		scoperti = new boolean[graph.getOrder()];
		for(int i = 0;i < graph.getOrder();i++) {
			scoperti[i] = false;
		}
		padri = new int[graph.getOrder()];
		for(int i = 0;i < graph.getOrder();i++) {
			padri[i] = -1;
		}
		
	}
}
