package graph.dfs;

import java.util.ArrayList;

import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.GraphInterface;

public class BFS {
	
	private static GraphInterface graph;
	
	private ArrayList<Integer> coda;
	boolean[] scoperti;
	private ArrayList<Integer> risultato;
	private ArrayList<Integer> risultatoConps;
	private int[] order;
	private ArrayList<ArrayList<Integer>> conComps;
	private int[] padre;
	private ArrayList<Integer> cammino;
	private GraphInterface tree;
	
	public BFS(GraphInterface g) {
		// inizializzo l'array degli scoperti
		graph = g;
		reInit();
	}
	
	public ArrayList<Integer> getNodesInOrderOfVisit(int sorgente){
		reInit();
		
		coda.add(sorgente);
		scoperti[sorgente] = true;
		ArrayList<Integer> result = new ArrayList<Integer>(graph.getOrder());
		result.add(sorgente);
		
		
		while(!coda.isEmpty()) {
			int tmp = coda.remove(0);
			for(Integer e:graph.getNeighbors(tmp)) {
				if(!discovered(e.intValue())) {
					coda.add(e);
					scoperti[e] = true;
					result.add(e);
				}
			}
		}
		return result;
	}

	public ArrayList<Integer> getNodesInOrderOfVisitComponent(int sorgente){
		reInitComp();
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
	public int[] getOrderOfVisitGeneralGraphCycle() {
		reInit();
		for(int i = 0;i < graph.getOrder();i++) {
			if(scoperti[i] == false) {
				getOrderOfVisitGeneralGraph(i);
			}
		}
		return order;
	}
	
	public void getOrderOfVisitGeneralGraph(int sorgente){
		reInitComp();
		
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
	}
	
	private boolean discovered(int elem) {
		return scoperti[elem];
	}

	private void initPadre(int sorg) {
		reInit();
	
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
	public ArrayList<Edge> getShortestPath(int sorg, int dest){
		reInit();
		initPadre(sorg);
		
		if(existPath(sorg,dest)) {
			cammino.add(0, sorg);
			ArrayList<Edge> path = new ArrayList<Edge>(cammino.size());
			for(int i = 0;i<cammino.size()-1;i++) {
				path.add(new Edge(cammino.get(i),cammino.get(i+1)));
			}
			return path;
		}
		else return null;
	}
	
	private boolean existPath(int sorg, int dest) {
		while(dest!=-1 && dest != sorg) {
			cammino.add(0, dest);
			dest = padre[dest];
		}
		if(dest == -1) return false;
		assert(dest == sorg);
		return true;
	}

	public ArrayList<ArrayList<Integer>> getConnectedComponent() {
		reInit();
		for(int i = 0;i < graph.getOrder();i++) {
			if(scoperti[i] == false) {
				conComps.add(getNodesInOrderOfVisitComponent(i));
			}
		}
		return conComps;
	}
	
	public ArrayList<Integer> getNodesInOrderOfVisitGeneralGraph(){
		reInit();
		for(int i = 0;i < graph.getOrder();i++) {
			if(scoperti[i] == false) {
				getNodesInOrderOfVisitComponentGeneralGraph(i);
			}
		}
		return risultatoConps;		
	}

	public void getNodesInOrderOfVisitComponentGeneralGraph(int sorgente){
		reInitComp();
		coda.add(sorgente);
		scoperti[sorgente] = true;
		risultatoConps.add(sorgente);
		
		
		while(!coda.isEmpty()) {
			int tmp = coda.remove(0);
			for(Integer e:graph.getNeighbors(tmp)) {
				if(!discovered(e.intValue())) {
					coda.add(e);
					scoperti[e] = true;
					risultatoConps.add(e);
				}
			}
		}
	}

	public void getNodesInOrderOfVisitTree(int sorgente){
		reInitComp();
		coda.add(sorgente);
		scoperti[sorgente] = true;
		risultatoConps.add(sorgente);
		
		
		while(!coda.isEmpty()) {
			int tmp = coda.remove(0);
			for(Integer e:graph.getNeighbors(tmp)) {
				if(!discovered(e.intValue())) {
					coda.add(e);
					scoperti[e] = true;
					tree.addEdge(tmp, e);
					risultatoConps.add(e);
				}
			}
		}
	}
	
	public int getNumberOfConnectedComponents() {
		reInit();
		int cont = 0;
		for(int i = 0;i < graph.getOrder();i++) {
			if(scoperti[i] == false) {
				cont++;
				getNodesInOrderOfVisitComponentGeneralGraph(i);
			}
		}
		return cont;				
	}
	
	public GraphInterface getBFSForest() {
		reInit();
		for(int i = 0;i < graph.getOrder();i++) {
			if(scoperti[i] == false) {
				getNodesInOrderOfVisitTree(i);
			}
		}
		return tree;
	}
	
	private void reInitComp() {
		risultato = new ArrayList<Integer>(graph.getOrder());
		coda = new ArrayList<Integer>(graph.getOrder());
	}
	
	private void reInit() {
		scoperti = new boolean[graph.getOrder()];
		for(int i = 0;i<graph.getOrder();i++) {
			scoperti[i] = false;
		}
		padre = new int[graph.getOrder()];
		for(int i = 0;i<graph.getOrder();i++) {
			padre[i] = -1;
		}
		tree = graph.create();
		cammino = new ArrayList<Integer>(graph.getOrder());
		risultatoConps = new ArrayList<Integer>(graph.getOrder());
		conComps = new ArrayList<ArrayList<Integer>>(graph.getOrder());
		order = new int[graph.getOrder()];
		risultato = new ArrayList<Integer>(graph.getOrder());
		coda = new ArrayList<Integer>(graph.getOrder());
	}
}
