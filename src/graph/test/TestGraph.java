package graph.test;

import static org.junit.Assert.*;

import org.junit.Test;

import graph.bfs.BFS;
import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.UndirectedGraph;

public class TestGraph {

	@Test
	public void testCreate() {
		GraphInterface grafo = new DirectedGraph(3);
		BFS bfsTest = new BFS(grafo);
		assertNotNull(bfsTest);
	}
	
	@Test
	public void testScoperti() {
		GraphInterface grafo = new DirectedGraph("3;0 1;1 2;2 0");
		BFS bfsTest = new BFS(grafo);
		bfsTest.getNodesInOrderOfVisit(0);
	}
	
	@Test
	public void numeroNodiVisitati() {
		GraphInterface grafoOne = new UndirectedGraph (1);
		BFS bfsTestOne = new BFS(grafoOne);
		assertEquals(1,bfsTestOne.getNodesInOrderOfVisit(0).size());

		GraphInterface grafoTwo = new UndirectedGraph ("2;0 1");
		BFS bfsTestTwo = new BFS(grafoTwo);
		assertEquals(2,bfsTestTwo.getNodesInOrderOfVisit(0).size());
		
		GraphInterface grafo = new UndirectedGraph ("4;0 2;0 1;2 3;1 3");
		BFS bfsTest = new BFS(grafo);
		assertEquals(4,bfsTest.getNodesInOrderOfVisit(2).size());
	}
	
	@Test
	public void testBFSOrder(){
		GraphInterface grafo = new UndirectedGraph ("4;0 2;0 1;2 3;1 3"); 
		BFS bfsTest = new BFS(grafo);
		
		assertTrue(bfsTest.getNodesInOrderOfVisit(2).get(2) == 0 || 
				   bfsTest.getNodesInOrderOfVisit(2).get(2) == 3);
		assertFalse(bfsTest.getNodesInOrderOfVisit(2).get(2) == 1);
	}
	
	@Test
	public void testInitLunghezza() {
		GraphInterface grafo = new UndirectedGraph("4;0 2;0 1;2 3;1 3");
		BFS bfsTest = new BFS(grafo);
		int numeroNodi = bfsTest.getNodesInOrderOfVisit(0).size();
		assertEquals(4,numeroNodi);
		
		numeroNodi = bfsTest.getNodesInOrderOfVisit(2).size();
		assertEquals(4,numeroNodi);
	}

	@Test
	public void testInitOrdineNodi() {
		GraphInterface grafo = new UndirectedGraph("4;0 2;0 1;2 3;1 3");
		BFS bfsTest = new BFS(grafo);
		
		assertTrue(bfsTest.getNodesInOrderOfVisit(2).get(2) != 1);
		assertTrue(bfsTest.getNodesInOrderOfVisit(1).get(2) != 0);
		assertTrue(bfsTest.getNodesInOrderOfVisit(0).get(2) != 3);
	}
	
	@Test
	public void testInitDistanzaUno() {
		GraphInterface grafo = new UndirectedGraph(1);
		BFS bfsTest = new BFS(grafo);
		int[] test = bfsTest.getDistance(0);
		assertEquals(0,test[0]);
	}

	@Test
	public void testInitDistanzaDue() {
		GraphInterface grafo = new UndirectedGraph("2;0 1");
		BFS bfsTest = new BFS(grafo);
		int[] test = bfsTest.getDistance(0);
		assertEquals(0,test[0]);
		assertEquals(1,test[1]);
	}

	@Test
	public void testInitDistanzaQuattro() {
		GraphInterface grafo = new UndirectedGraph("4;0 1;0 2;1 3;2 3");
		BFS bfsTest = new BFS(grafo);
		int[] test = bfsTest.getDistance(2);
		assertEquals(0,test[2]);
		assertEquals(1,test[0]);
		assertEquals(1,test[3]);
		assertEquals(2,test[1]);

		int[] test1 = bfsTest.getDistance(0);
		assertEquals(1,test1[2]);
		assertEquals(0,test1[0]);
		assertEquals(2,test1[3]);
		assertEquals(1,test1[1]);		
	}
	
	@Test
	public void testInit() {
		GraphInterface grafo = new UndirectedGraph(4);
		BFS bfsTest = new BFS(grafo);
		int[] test = bfsTest.getDistance(0);
		assertEquals(-1,test[2]);
		assertEquals(0,test[0]);
		assertEquals(-1,test[3]);
		assertEquals(-1,test[1]);				
		
	}
}
