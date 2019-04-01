package graph.dfs;

import java.util.ArrayList;

import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.GraphUtils;

public class Kosaraju {
	
	private boolean[] scoperti;
	private DirectedGraph graph;
	// public per testing
	public ArrayList<Integer> orderPostVisit;
	private int[] scc;
	private int sccCount;
	
	public Kosaraju(DirectedGraph g) {
		graph = g;
		reInitIn();
	}
	
	public ArrayList<Integer> postVisitList(){
		reInitIn();
		
		for(int i = 0;i<graph.getOrder();i++) {
			if(scoperti[i] == false) dfsVisitPostOrder(i);
		}
		return orderPostVisit;
		
	}
	
	private void dfsVisitSCC(int sorg) {
		scoperti[sorg] = true;
		scc[sorg] = sccCount;
		
		for(Integer e:graph.getNeighbors(sorg)) {
			if(scoperti[e] == false) {
				dfsVisitSCC(e);
			}
		}		
	}
	
	private void dfsVisitPostOrder(int sorg) {
		scoperti[sorg] = true;
		
		for(Integer e:graph.getNeighbors(sorg)) {
			if(scoperti[e] == false) {
				dfsVisitPostOrder(e);
			}
		}
		orderPostVisit.add(sorg);;		
	}

	public int[] getSCC() {
		// Passo 1
		reInitIn();
		ArrayList<Integer> orderPost = this.postVisitList();
		
		// Passo 2
		DirectedGraph graphTras = GraphUtils.reverseGraph(graph);
		graph = graphTras;
		// Passo 3
		reInit();
		for(int i = graph.getOrder()-1; i >= 0;i--) {
			if(scoperti[orderPost.get(i)] == false) {
				dfsVisitSCC(orderPost.get(i));
				sccCount++;
			}
		}
		return scc;
		
	}	
	
	public void reInitIn() {
		scoperti = new boolean[graph.getOrder()];
		for(int i = 0;i < graph.getOrder();i++) scoperti[i] = false;
		
		orderPostVisit = new ArrayList<Integer>(graph.getOrder());
		scc = new int[graph.getOrder()];
		sccCount = 1;
	}
	
	public void reInit() {
		scoperti = new boolean[graph.getOrder()];
		for(int i = 0;i < graph.getOrder();i++) scoperti[i] = false;
	}
	// Da fare test e teoria
}
