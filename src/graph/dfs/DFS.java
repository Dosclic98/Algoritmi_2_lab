package graph.dfs;


import java.util.ArrayList;

import it.uniupo.graphLib.GraphInterface;

public class DFS {
	
	private static GraphInterface graph;
	private GraphInterface tree;
	boolean[] scoperti;
	private ArrayList<Integer> risultato;
	private ArrayList<Integer> finiti;

	
	public DFS(GraphInterface g) {
		// inizializzo l'array degli scoperti
		graph = g;
		reInit();
	}
	
	private void dfsVisit(int sorg) {
		scoperti[sorg] = true;
		risultato.add(sorg);
		for(Integer e:graph.getNeighbors(sorg)) {
			if(!discovered(e.intValue())) {
				tree.addEdge(sorg, e);
				dfsVisit(e);
			}
		}
		finiti.add(sorg);
	}
	
	public GraphInterface getTree(int sorg) throws NotAllNodesReachedException {
		if(sorg >= graph.getOrder() || sorg < 0) throw new java.lang.IllegalArgumentException();
		reInit();
		dfsVisit(sorg);
		for(int i = 0;i<graph.getOrder();i++) {
			if(!scoperti[i]) throw new NotAllNodesReachedException(); 
		}
		return tree;
	}
	
	public ArrayList<Integer> getNodesInOrderPostVisit(int sorg) throws NotAllNodesReachedException{
		if(sorg >= graph.getOrder() || sorg < 0) throw new java.lang.IllegalArgumentException();
		reInit();
		dfsVisit(sorg);
		for(int i = 0;i<graph.getOrder();i++) {
			if(!scoperti[i]) throw new NotAllNodesReachedException(); 
		}
		return finiti;
	} 
	
	public GraphInterface getForest() throws NotAllNodesReachedException{
		reInit();
		for(int i = 0;i<graph.getOrder();i++) {
			if(i >= graph.getOrder() || i < 0) throw new java.lang.IllegalArgumentException();
			if(scoperti[i] == false) dfsVisit(i);
		}
		for(int i = 0;i<graph.getOrder();i++) {
			if(!scoperti[i]) throw new NotAllNodesReachedException(); 
		}
		return tree;

	}
	
	public ArrayList<Integer> getNodesInOrderOfVisit(int sorg){
		if(sorg >= graph.getOrder() || sorg < 0) throw new java.lang.IllegalArgumentException();
		reInit();
		dfsVisit(sorg);
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
		finiti = new ArrayList<Integer>(graph.getOrder());
		risultato = new ArrayList<Integer>(graph.getOrder());
		tree = graph.create();
	}
	
	public int[] getOrderOfVisit(int sorg) {
		if(sorg >= graph.getOrder() || sorg < 0) throw new java.lang.IllegalArgumentException();
		ArrayList<Integer> visit = getNodesInOrderOfVisit(sorg);
		int[] order = new int[visit.size()];
		for(int i = 0;i<visit.size();i++) {
			order[visit.get(i)] = i;
		}
		return order;
	}
	
	public int[] getOrderPostVisit(int sorg) throws NotAllNodesReachedException {
		if(sorg >= graph.getOrder() || sorg < 0) throw new java.lang.IllegalArgumentException();
		ArrayList<Integer> visit = getNodesInOrderPostVisit(sorg);
		int[] order = new int[visit.size()];
		for(int i = 0;i<visit.size();i++) {
			order[visit.get(i)] = i;
		}
		return order;
	}
	
}
