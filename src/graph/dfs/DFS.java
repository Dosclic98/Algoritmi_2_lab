package graph.dfs;


import java.util.ArrayList;

import it.uniupo.graphLib.GraphInterface;

public class DFS {
	
	private static GraphInterface graph;
	private GraphInterface tree;
	boolean[] scoperti;
	boolean[] terminati;
	private ArrayList<Integer> risultato;
	private ArrayList<Integer> finiti;
	private ArrayList<Integer> ordineTop;
	private int[] padre;
	private int[] orderOfVisitArr;
	private int[] orderPostVisitArr;
	private int orderOfVisit;
	private int orderPostVisit;
	int lastVisited;
	int arcInd;

	
	public DFS(GraphInterface g) {
		// inizializzo l'array degli scoperti
		graph = g;
		reInit();
	}
	
	private void dfsVisit(int sorg) {
		orderOfVisitArr[sorg] = orderOfVisit;
		orderOfVisit++;
		
		scoperti[sorg] = true;
		risultato.add(sorg);
		for(Integer e:graph.getNeighbors(sorg)) {
			if(!discovered(e.intValue())) {
				tree.addEdge(sorg, e);
				dfsVisit(e);
			}
		}
		orderPostVisitArr[sorg] = orderPostVisit;
		orderPostVisit++;
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

	public ArrayList<Integer> getNodesInOrderOfVisit(){
		reInit();
		for(int i = 0;i < graph.getOrder();i++) {
			if(!discovered(i)) dfsVisit(i);
		}
		return risultato;
	}
	
	public int[] getOrderOfVisit() {
		reInit();
		for(int i = 0;i < graph.getOrder();i++) {
			if(!discovered(i)) dfsVisit(i);
		}
		return orderOfVisitArr;		
	}

	public int[] getOrderPostVisit() {
		reInit();
		for(int i = 0;i < graph.getOrder();i++) {
			if(!discovered(i)) dfsVisit(i);
		}
		return orderPostVisitArr;		
	}
	
	public ArrayList<Integer> getNodesInOrderPostVisit(){
		reInit();
		for(int i = 0;i < graph.getOrder();i++) {
			if(!discovered(i)) dfsVisit(i);
		}
		return finiti;
	} 
	
	private boolean discovered(int elem) {
		return scoperti[elem];
	}
		
	private void reInit() {
		scoperti = new boolean[graph.getOrder()];
		for(int i = 0;i<graph.getOrder();i++) {
			scoperti[i] = false;
		}
		terminati = new boolean[graph.getOrder()];
		for(int i = 0;i<graph.getOrder();i++) {
			terminati[i] = false;
		}
		padre = new int[graph.getOrder()];
		for(int i = 0;i<graph.getOrder();i++) {
			padre[i] = -1;
		}
		ordineTop = new ArrayList<Integer>(graph.getOrder());
		orderOfVisit = 0;
		orderPostVisit = 0;
		orderOfVisitArr = new int[graph.getOrder()];
		orderPostVisitArr = new int[graph.getOrder()];
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

	public boolean hasDirCycle(){
		reInit();
		boolean cycle = false;
		for(int i = 0;i<graph.getOrder();i++) {
			if(i >= graph.getOrder() || i < 0) throw new java.lang.IllegalArgumentException();
			if(scoperti[i] == false) cycle = dfsVisitCycle(i);
			if(cycle) return true;
		}
		return false;
	}
	
	private boolean dfsVisitCycle(int sorg) {
		scoperti[sorg] = true;
		risultato.add(sorg);
		boolean cycle = false;
		for(Integer e:graph.getNeighbors(sorg)) {
			if(!discovered(e.intValue())) {
				padre[e] = sorg;
				cycle = dfsVisitCycle(e);
			}
			else if(discovered(e.intValue()) && terminati[e] == false){
				lastVisited = sorg;
				arcInd = e;
				cycle = true;
				return true;
			}
		}
		terminati[sorg] = true;
		return cycle;
	}

	private boolean dfsVisitCycleUn(int sorg) {
		scoperti[sorg] = true;
		risultato.add(sorg);
		boolean cycle = false;
		for(Integer e:graph.getNeighbors(sorg)) {
			if(!discovered(e.intValue())) {
				padre[e] = sorg;
				cycle = dfsVisitCycleUn(e);
			}
			else if(discovered(e.intValue()) && terminati[e] == false && e != padre[sorg]){
				lastVisited = sorg;
				arcInd = e;
				cycle = true;
				terminati[sorg] = true;
				return true;
			}
		}
		terminati[sorg] = true;
		return cycle;
	}
	
	public ArrayList<Integer> getDirCycle(){
		reInit();
		boolean cycle = false;
		for(int i = 0;i<graph.getOrder();i++) {
			if(i >= graph.getOrder() || i < 0) throw new java.lang.IllegalArgumentException();
			if(scoperti[i] == false) cycle = dfsVisitCycle(i);
			if(cycle) return buildCycle();
		}
		return null;		
	}

	public ArrayList<Integer> getUndirCycle(){
		reInit();
		boolean cycle = false;
		for(int i = 0;i<graph.getOrder();i++) {
			if(i >= graph.getOrder() || i < 0) throw new java.lang.IllegalArgumentException();
			if(scoperti[i] == false) cycle = dfsVisitCycleUn(i);
			if(cycle) return buildCycle();
		}
		return null;		
	}
	
	private ArrayList<Integer> buildCycle() {
		ArrayList<Integer> cycle = new ArrayList<Integer>(graph.getOrder());
		int start = lastVisited;
		cycle.add(start);
		while(start != arcInd) { 
			start = padre[start];
			cycle.add(padre[start]);
		}
		return cycle;
	}
	
	public boolean hasUndirectedCycle() {
		reInit();
		boolean cycle = false;
		for(int i = 0;i<graph.getOrder();i++) {
			if(i >= graph.getOrder() || i < 0) throw new java.lang.IllegalArgumentException();
			if(scoperti[i] == false) cycle = dfsVisitCycleUn(i);
			if(cycle) return true;
		}
		return false;		
	}
	
	public boolean isConnected() {
		for(int i = 0;i < graph.getOrder();i++) {
			reInit();
			dfsVisit(i);
			for(int j = 0;j < graph.getOrder();j++) {
				if(!scoperti[j]) return false;
			}
		}
		return true;
		
	}
	
	public ArrayList<Integer> topologicalOrder(){
		if(!isDag()) throw new IllegalArgumentException();
		reInit();
		for(int i = 0;i<graph.getOrder();i++) {
			if(scoperti[i] == false) dfsVisitTopOrder(i);
		}
		return ordineTop;
		
	}

	public void dfsVisitTopOrder(int sorg) {		
		scoperti[sorg] = true;
		risultato.add(sorg);
		
		for(Integer e:graph.getNeighbors(sorg)) {
			if(!discovered(e.intValue())) {
				dfsVisitTopOrder(e);
			}
		}
		ordineTop.add(0, sorg);;
	}
	
	private boolean isDag() {
		reInit();
		boolean cycle = false;
		for(int i = 0;i<graph.getOrder();i++) {
			if(scoperti[i] == false) cycle = dfsVisitCycle(i);
			if(cycle) return false;
		}
		return true;
	}
}
