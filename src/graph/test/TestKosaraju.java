package graph.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


import graph.dfs.Kosaraju;
import it.uniupo.graphLib.DirectedGraph;

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
	

}
