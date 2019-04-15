package graph.dfs;

import it.uniupo.algoTools.MinHeap;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.UndirectedGraph;

public class AllacciamentoIdrico {
	private UndirectedGraph graph;
	int[][] costoScavo;
	int costoTubo;
	int puntoAllacciamento;
	
	private boolean[] scoperti;
	private int[] d;
	private UndirectedGraph treeProprietari;
	private UndirectedGraph treeComune;
	private int[] padri;
	private int costoComuneProgProp;
	private int costoComuneProgCom;
	
	public AllacciamentoIdrico (UndirectedGraph mappa, int[][] costoScavo,
								int costoTubo, int puntoAllacciamento) {
		graph = mappa;
		this.costoScavo = costoScavo;
		this.costoTubo = costoTubo;
		this.puntoAllacciamento = puntoAllacciamento;
		costoComuneProgProp = 0;
		costoComuneProgCom = 0;

		reInit();
	}
	
	public UndirectedGraph progettoComune() {
		reInit();
		int s = 0;
		scoperti[s] = true;
		
		MinHeap<Edge, Integer> minHeap = new MinHeap<Edge, Integer>();
		for(Edge e:graph.getOutEdges(s)) {
			minHeap.add(e, costoScavo[e.getTail()][e.getHead()]);
		}
		
		while(!minHeap.isEmpty()) {
			Edge tmp = minHeap.extractMin();
			int u = tmp.getTail();
			int w = tmp.getHead();
			
			if(scoperti[w] == false) {
				scoperti[w] = true;
				treeComune.addEdge(tmp);
				costoComuneProgCom += costoScavo[u][w];
				//padri[w] = u;
				for(Edge add:graph.getOutEdges(w)) {
					int v = add.getHead();
					if(scoperti[v] == false) {
						minHeap.add(add, costoScavo[add.getTail()][add.getHead()]);
					}
				}
			}
		}
		return treeComune;		
	}

	public UndirectedGraph progettoComunePadri() {
		reInit();
		// Non è fondamentale partire dal punto di allacciamento
		// visto che l'algoritmo di Prim non restituisce un MST dipendente dalla
		// sorgente di partenza. Supporre di partire dal punto di allacciamento 
		// facilita invece la costruzione dell'array padri usato nel metodo
		// speseExtraProprietario() ed evita di dover effettuare una BFS o una DFS
		// a su treeComune a partire dall allacciamento fino alla villa desiderata.
		// E' corretto??
		int s = puntoAllacciamento;
		scoperti[s] = true;
		
		MinHeap<Edge, Integer> minHeap = new MinHeap<Edge, Integer>();
		for(Edge e:graph.getOutEdges(s)) {
			minHeap.add(e, costoScavo[e.getTail()][e.getHead()]);
		}
		
		while(!minHeap.isEmpty()) {
			Edge tmp = minHeap.extractMin();
			int u = tmp.getTail();
			int w = tmp.getHead();
			
			if(scoperti[w] == false) {
				scoperti[w] = true;
				treeComune.addEdge(tmp);
				costoComuneProgCom += costoScavo[u][w];
				padri[w] = u;
				for(Edge add:graph.getOutEdges(w)) {
					int v = add.getHead();
					if(scoperti[v] == false) {
						minHeap.add(add, costoScavo[add.getTail()][add.getHead()]);
					}
				}
			}
		}
		return treeComune;		
	}
	
	
	public UndirectedGraph progettoProprietari() {		
		singoloProgettoProprietari(puntoAllacciamento);
		
		return treeProprietari;
	}

	private void singoloProgettoProprietari(int partenza) {
		if(partenza < 0 || partenza >= graph.getOrder()) {
			throw new IllegalArgumentException();
		}
		reInit();
		
		scoperti[partenza] = true;
		d[partenza] = 0;
		
		MinHeap<Edge,Integer> heap = new MinHeap<Edge,Integer>();
		
		for(Edge e:graph.getOutEdges(partenza)) {
			heap.add(e, d[partenza] + (e.getWeight()*costoTubo));
		}
		
		while(!heap.isEmpty()) {
			Edge tmp = heap.extractMin();
			int u = tmp.getTail();
			int w = tmp.getHead();
			
			if(scoperti[w] == false) {
				scoperti[w] = true;
				d[w] = d[u] + (tmp.getWeight()*costoTubo);
				costoComuneProgProp += costoScavo[u][w]; 
				treeProprietari.addEdge(tmp);
				for(Edge z:graph.getOutEdges(w)) {
					if(scoperti[z.getHead()] == false) {
						heap.add(z, d[w] + (z.getWeight()*costoTubo));
					}
				}
			}
		}	
	}
	
	public int speseExtraComune() {
		costoComuneProgProp = 0;
		costoComuneProgCom = 0;
		progettoProprietari();
		progettoComune();
		
		return costoComuneProgProp - costoComuneProgCom;
	}
	
	public int speseExtraProprietario(int villetta) {
		if(villetta < 0 || villetta >= graph.getOrder()) throw new IllegalArgumentException();
		
		padri = new int[graph.getOrder()];
		for(int i = 0;i<graph.getOrder();i++) {
			padri[i] = -1;
		}		
		progettoComunePadri();
		progettoProprietari();
		int costoPropProgProp = d[villetta];
		int costoPropProgCom = 0;
		int tmp = villetta;
		while(padri[tmp] != -1) {
			for(Edge e:graph.getOutEdges(tmp)) {
				if(e.getHead() == padri[tmp]) {
					assert(e.getTail() == tmp);
					costoPropProgCom += e.getWeight()*costoTubo;
				}
			}
			tmp = padri[tmp];
		}
		
		for(int i = 0;i<graph.getOrder();i++) {
			System.out.println(padri[i]);
		}
		
		//System.out.println(d[villetta]);
		return costoPropProgCom - costoPropProgProp;
	}
	
	public void reInit() {
		scoperti = new boolean[graph.getOrder()];
		for(int i = 0;i<graph.getOrder();i++) {
			scoperti[i] = false;
		}

		d = new int[graph.getOrder()];
		for(int i = 0;i<graph.getOrder();i++) {
			d[i] = -1;
		}
		Integer inp = graph.getOrder();
		treeProprietari = new UndirectedGraph(inp.toString());		
		treeComune = new UndirectedGraph(inp.toString());
	}
}
