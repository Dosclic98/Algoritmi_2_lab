package graph.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;


import graph.dfs.Kosaraju;
import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.GraphUtils;

public class TestKosaraju {

	@Test
	public void testOrdPost() {
		DirectedGraph graph = new DirectedGraph("4;0 2;1 2;1 0;3 0;3 1");
		Kosaraju kosarajuTest = new Kosaraju(graph);
		ArrayList<Integer> ordinePost = kosarajuTest.postVisitList();
		assertTrue(ordinePost.size() == 4);
		assertEquals(2,ordinePost.get(0).intValue());
		System.out.println(ordinePost);
		assertEquals(0,ordinePost.get(1).intValue());
		assertEquals(1,ordinePost.get(2).intValue());
		assertEquals(3,ordinePost.get(3).intValue());
	}
	
	@Test
	public void testSCC() {
		DirectedGraph graph = new DirectedGraph("1");
		Kosaraju kosarajuTest = new Kosaraju(graph);
		int[] scc = kosarajuTest.getSCC();
		
		assertTrue(scc.length == 1);
		assertEquals(1,scc[0]);
		
		graph = new DirectedGraph("4; 0 1; 1 2; 2 3; 3 0");
		kosarajuTest = new Kosaraju(graph);
		scc = kosarajuTest.getSCC();
		assertEquals(1,scc[0]);
		assertEquals(1,scc[1]);
		assertEquals(1,scc[2]);
		assertEquals(1,scc[3]);
		System.out.println(scc[0] + " " + scc[1] + " " + scc[2] + " " + scc[3]);
		
		graph = new DirectedGraph("5; 0 1; 1 2;1 3; 2 4; 3 4");
		kosarajuTest = new Kosaraju(graph);
		scc = kosarajuTest.getSCC();
		assertEquals(1,scc[0]);
		assertEquals(2,scc[1]);
		assertEquals(4,scc[2]);
		assertEquals(3,scc[3]);
		assertEquals(5,scc[4]);
		System.out.println(scc[0] + " " + scc[1] + " " + scc[2] + " " + scc[3] + " " + scc[4]);
		
		graph = new DirectedGraph("5;0 2;2 0;4 0;1 2;1 4;4 3;3 1");
		kosarajuTest = new Kosaraju(graph);
		scc = kosarajuTest.getSCC();
		assertEquals(2,scc[0]);
		assertEquals(1,scc[1]);
		assertEquals(2,scc[2]);
		assertEquals(1,scc[3]);
		assertEquals(1,scc[4]);
		System.out.println(scc[0] + " " + scc[1] + " " + scc[2] + " " + scc[3] + " " + scc[4]);
	}
	
	@Test
	public void testSCCTrasp() {
		DirectedGraph graph1 = new DirectedGraph("4; 0 1; 1 2; 2 3; 3 0");
		DirectedGraph graph2 = GraphUtils.reverseGraph(graph1);
		
		Kosaraju kosarajuTest1 = new Kosaraju(graph1);
		int[] scc1 = kosarajuTest1.getSCC();
		Kosaraju kosarajuTest2 = new Kosaraju(graph2);
		int[] scc2 = kosarajuTest2.getSCC();
		
		assertEquals(scc1[0],scc2[0]);
		assertEquals(scc1[1],scc2[1]);
		assertEquals(scc1[2],scc2[2]);
		assertEquals(scc1[3],scc2[3]);
		
	}
	// Non ha molto senso ma nel dubbio l'ho fatto
	@Test
	public void testOrdTop() {
		DirectedGraph graph = new DirectedGraph("5; 0 1; 1 2;1 3; 2 4; 3 4");
		Kosaraju kosarajuTest = new Kosaraju(graph);
		kosarajuTest.getSCC();
		ArrayList<Integer> topOrder = kosarajuTest.orderPostVisit;
		System.out.println(topOrder);
		// inverto e ottengo l'ordine topologico
		Collections.reverse(topOrder);
		System.out.println(topOrder);
		for(int u = 0;u < topOrder.size();u++) {
			for(int v = u+1;v < topOrder.size();v++) {
				assertFalse(graph.hasEdge(v, u));
			}
		}
	}
	
	@Test
	public void testIsReached() {
		DirectedGraph graph = new DirectedGraph("3;0 1;1 2;2 1");
		Kosaraju kosarajuTest = new Kosaraju(graph);
		assertTrue(kosarajuTest.startReached(0, 1));
		assertTrue(kosarajuTest.startReached(0, 2));
		assertFalse(kosarajuTest.startReached(1, 0));
		assertFalse(kosarajuTest.startReached(2, 0));
		
		graph = new DirectedGraph("4;0 1;2 3;3 2");
		kosarajuTest = new Kosaraju(graph);
		assertTrue(kosarajuTest.startReached(0, 1));
		assertFalse(kosarajuTest.startReached(0, 2));
		assertFalse(kosarajuTest.startReached(1, 3));
		assertTrue(kosarajuTest.startReached(2, 3));
		assertTrue(kosarajuTest.startReached(3, 2));
	}
	
	@Test
	public void testTrueSCC() {
		DirectedGraph graph = new DirectedGraph("5;0 2;2 0;4 0;1 2;1 4;4 3;3 1");
		Kosaraju kosarajuTest = new Kosaraju(graph);
		int[] scc = kosarajuTest.getSCC();
		assertEquals(2,scc[0]);
		assertEquals(1,scc[1]);
		assertEquals(2,scc[2]);
		assertEquals(1,scc[3]);
		assertEquals(1,scc[4]);
		// System.out.println(scc[0] + " lol" + scc[1] + " " + scc[2] + " " + scc[3] + " " + scc[4]);
		for(int i = 0;i<scc.length;i++) {
			for(int j = 0;j<scc.length;j++) {
				if(i != j && scc[i] == scc[j]) {
					assertTrue(kosarajuTest.startReached(i, j));
				}
				else if(i != j && scc[i] != scc[j]) {
					assertFalse(kosarajuTest.startReached(i, j) && kosarajuTest.startReached(j, i));
				}
			}
		}
	}
	
	@Test
	public void testOrderOfMaxSCC() {
		DirectedGraph graph = new DirectedGraph("5;0 2;2 0;4 0;1 2;1 4;4 3;3 1");
		Kosaraju kosarajuTest = new Kosaraju(graph);
		int[] scc = kosarajuTest.getSCC();
		assertEquals(2,scc[0]);
		assertEquals(1,scc[1]);
		assertEquals(2,scc[2]);
		assertEquals(1,scc[3]);
		assertEquals(1,scc[4]);

		assertEquals(3,kosarajuTest.getOrdMaxSCC());
	}
}
