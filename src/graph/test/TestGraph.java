package graph.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import graph.dfs.DFS;
import graph.dfs.NotAllNodesReachedException;
import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.UndirectedGraph;

public class TestGraph {

	@Test
	public void testCreate() {
		GraphInterface grafo = new DirectedGraph(3);
		DFS bfsTest = new DFS(grafo);
		assertNotNull(bfsTest);
	}
	
	@Test
	public void testScoperti() throws NotAllNodesReachedException {
		GraphInterface grafo = new UndirectedGraph ("3;0 1;1 2;2 0");
		DFS dfsTest = new DFS(grafo);
		dfsTest.getTree(0);		
	}
	
	@Test
	public void testNumeroArchi() throws NotAllNodesReachedException {
		GraphInterface grafo = new UndirectedGraph ("4;2 0;2 3;0 1;3 1");
		DFS dfsTest = new DFS(grafo);	
		GraphInterface tree = dfsTest.getTree(2);
		assertEquals(3,tree.getEdgeNum());
	}
	
	@Test
	public void testArchiDFS() throws NotAllNodesReachedException {
		GraphInterface grafo = new UndirectedGraph ("4;2 0;2 3;0 1;3 1");
		DFS dfsTest = new DFS(grafo);	
		GraphInterface tree = dfsTest.getTree(2);
		
		assertTrue( (tree.hasEdge(2, 0) && tree.hasEdge(0, 1) && tree.hasEdge(3, 1))
													||
					(tree.hasEdge(2, 3) && tree.hasEdge(3, 1) && tree.hasEdge(1, 0)));
	}

	@Test
	public void testInitAlbero() throws NotAllNodesReachedException {
		GraphInterface grafo = new UndirectedGraph ("4;2 0;2 3;0 1;3 1");
		DFS dfsTest = new DFS(grafo);	
		GraphInterface treeFirst = dfsTest.getTree(2);
		GraphInterface treeSecond = dfsTest.getTree(1);
		
		assertTrue(treeFirst.getEdgeNum() == 3 && treeSecond.getEdgeNum() == 3);
		assertTrue( (treeFirst.hasEdge(2, 3) && !treeFirst.hasEdge(2, 0))
						||
				    (!treeFirst.hasEdge(2, 3) && treeFirst.hasEdge(2, 0))
					);
		assertTrue( (treeSecond.hasEdge(3, 1) && !treeSecond.hasEdge(0, 1))
						||
					 (!treeSecond.hasEdge(3, 1) && treeSecond.hasEdge(0, 1))
				);
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void testIllExcSup() throws NotAllNodesReachedException{ 
		GraphInterface grafo = new UndirectedGraph ("4;2 0;2 3;0 1;3 1");
		DFS dfsTest = new DFS(grafo);	
		dfsTest.getTree(4);		 
	}

	@Test (expected = IllegalArgumentException.class) 
	public void testIllExcInf() throws NotAllNodesReachedException{ 
		GraphInterface grafo = new UndirectedGraph ("4;2 0;2 3;0 1;3 1");
		DFS dfsTest = new DFS(grafo);	
		dfsTest.getTree(-1);		 
	}

	@Test (expected = NotAllNodesReachedException.class) 
	public void testNotVisitedExc() throws NotAllNodesReachedException{ 
		GraphInterface grafo = new UndirectedGraph ("4;2 0;0 1");
		DFS dfsTest = new DFS(grafo);	
		dfsTest.getTree(2);		 
	}
	
	@Test
	public void testOrderOfVisit() throws NotAllNodesReachedException{
		GraphInterface grafo = new UndirectedGraph ("4;2 0;2 3;0 1;3 1");
		DFS dfsTest = new DFS(grafo);	
		ArrayList<Integer> order = dfsTest.getNodesInOrderPostVisit(2);
		assertTrue(Arrays.asList(3, 1, 0, 2).equals(order)
				  ||
				  Arrays.asList(0, 1, 3, 2).equals(order));
	}
	
	@Test
	public void testGetForest() throws NotAllNodesReachedException {
		GraphInterface grafo = new UndirectedGraph ("4;2 0;3 1");
		DFS dfsTest = new DFS(grafo);	
		GraphInterface tree = dfsTest.getForest();
		assertTrue(tree.hasEdge(2, 0) && tree.hasEdge(3, 1));
		assertTrue(!tree.hasEdge(0, 1) && !tree.hasEdge(2, 3));
	}
	
	@Test
	public void testDfsOrder() {
		GraphInterface grafo = new UndirectedGraph("4;2 0;2 3;0 1;3 1");
		DFS dfsTest = new DFS(grafo);
		ArrayList<Integer> orderVisit = dfsTest.getNodesInOrderOfVisit(2);
		assertTrue(Arrays.asList(2, 0, 1, 3).equals(orderVisit)
				  ||
				  Arrays.asList(2, 3, 1, 0).equals(orderVisit));
		assertFalse(Arrays.asList(2, 0, 3, 1).equals(orderVisit)
				  ||
				   Arrays.asList(2, 3, 0, 1).equals(orderVisit));

	}
	
	@Test
	public void testInitLunghezza() {
		GraphInterface grafo = new UndirectedGraph("4;2 0;2 3;0 1;3 1");
		DFS dfsTest = new DFS(grafo);
		ArrayList<Integer> orderVisitFirst = dfsTest.getNodesInOrderOfVisit(2);
		ArrayList<Integer> orderVisitSecond = dfsTest.getNodesInOrderOfVisit(1);
		
		assertTrue(orderVisitFirst.size() == orderVisitSecond.size() &&
				   orderVisitFirst.size() == 4);
	}
	
	@Test
	public void testInitContenuti() {
		GraphInterface grafo = new UndirectedGraph("4;2 0;2 3;0 1;3 1");
		DFS dfsTest = new DFS(grafo);
		assertTrue(dfsTest.getNodesInOrderOfVisit(2).get(1) != 3);
		assertTrue(dfsTest.getNodesInOrderOfVisit(1).get(0) != 2);
		assertTrue(dfsTest.getNodesInOrderOfVisit(0).get(2) != 1);
	}
	
	@Test
	public void testOrderPerNode() {
		GraphInterface grafo = new UndirectedGraph("4;2 0;2 3;0 1;3 1");
		DFS dfsTest = new DFS(grafo);
		int[] order = dfsTest.getOrderOfVisit(2);
		assertTrue(order[2] == 0
		        && order[0] == 1
		        && order[1] == 2
		        && order[3] == 3);
	}

	@Test
	public void testOrderPostPerNode() throws NotAllNodesReachedException {
		GraphInterface grafo = new UndirectedGraph("4;2 0;2 3;0 1;3 1");
		DFS dfsTest = new DFS(grafo);
		int[] order = dfsTest.getOrderPostVisit(2);
		assertTrue(order[3] == 0
				&& order[1] == 1
				&& order[0] == 2
				&& order[2] == 3);
	}
	
}
