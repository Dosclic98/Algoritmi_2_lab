package graph.test;

import static org.junit.Assert.*;

import org.junit.Test;

import graph.dfs.Prim;
import it.uniupo.graphLib.UndirectedGraph;

public class TestPrim {

	@Test
	public void test() {
		UndirectedGraph graph = new UndirectedGraph("2;0 1 5");
		Prim testPrim = new Prim(graph);
		UndirectedGraph tree = testPrim.getMST();
		assertTrue(tree.hasEdge(0,1));
		
		int cost = testPrim.getMSTCost();
		assertEquals(5,cost);
		
		graph = new UndirectedGraph("4;0 1 5;0 2 1;2 1 3;1 3 3");
		testPrim = new Prim(graph);
		tree = testPrim.getMST();
		assertTrue(tree.hasEdge(0,2));
		assertTrue(tree.hasEdge(2,1));
		assertTrue(tree.hasEdge(1,3));
		assertTrue(!tree.hasEdge(0,1));
		
		cost = testPrim.getMSTCost();
		assertEquals(7,cost);
	}

}
