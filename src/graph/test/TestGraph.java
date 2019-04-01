package graph.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import graph.dfs.BFS;
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
	
	@Test
	public void testHasCycle() {
		GraphInterface grafo = new DirectedGraph("1");
		DFS dfsTest = new DFS(grafo);
		assertFalse(dfsTest.hasDirCycle());
		
		grafo = new DirectedGraph("2;0 1");
		dfsTest = new DFS(grafo);
		assertFalse(dfsTest.hasDirCycle());
		
		grafo = new DirectedGraph("3;1 0;1 2;0 2");
		dfsTest = new DFS(grafo);
		assertFalse(dfsTest.hasDirCycle());
		
		grafo = new DirectedGraph("3;0 2;2 1;1 0");
		dfsTest = new DFS(grafo);
		assertTrue(dfsTest.hasDirCycle());		
		
		grafo = new DirectedGraph("6;4 3;4 1;1 3;3 0;3 2;0 1;2 0;0 5;2 5");
		dfsTest = new DFS(grafo);
		assertTrue(dfsTest.hasDirCycle());		
		
	}
	
	@Test
	public void testBuildingCycle() {
		GraphInterface grafo = new DirectedGraph("3;0 2;2 1;1 0");
		DFS dfsTest = new DFS(grafo);
		assertEquals(3,dfsTest.getDirCycle().size());		

		grafo = new DirectedGraph("2;0 1");
		dfsTest = new DFS(grafo);
		assertTrue(dfsTest.getDirCycle() == null);

		grafo = new DirectedGraph("1");
		dfsTest = new DFS(grafo);
		assertTrue(dfsTest.getDirCycle() == null);
		
		grafo = new DirectedGraph("5;2 3;3 4;4 2;4 1;4 0");
		dfsTest = new DFS(grafo);
		assertEquals(3,dfsTest.getDirCycle().size());		
		
	}
	
	@Test
	public void testIsConnected() {
		GraphInterface grafo = new UndirectedGraph("2");
		DFS dfsTest = new DFS(grafo);
		assertFalse(dfsTest.isConnected());
		
		grafo = new UndirectedGraph("2;0 1");
		dfsTest = new DFS(grafo);
		assertTrue(dfsTest.isConnected());
		
		grafo = new UndirectedGraph("1");
		dfsTest = new DFS(grafo);
		assertTrue(dfsTest.isConnected());
		
	}
	
	@Test
	public void testConComps() {
		GraphInterface grafo = new UndirectedGraph("2");
		BFS bfsTest = new BFS(grafo);
		assertEquals(2,bfsTest.getConnectedComponent().size());

		grafo = new UndirectedGraph("1");
		bfsTest = new BFS(grafo);
		assertEquals(1,bfsTest.getConnectedComponent().size());		
		
		grafo = new UndirectedGraph("2;0 1");
		bfsTest = new BFS(grafo);
		assertEquals(1,bfsTest.getConnectedComponent().size());

		grafo = new UndirectedGraph("4;0 1;2 3");
		bfsTest = new BFS(grafo);
		assertEquals(2,bfsTest.getConnectedComponent().size());
	
	}
	
	@Test
	public void testHasUndirCycle() {
		GraphInterface grafo = new UndirectedGraph("1");
		DFS dfsTest = new DFS(grafo);
		assertFalse(dfsTest.hasUndirectedCycle());
		
		grafo = new UndirectedGraph("3;0 1;1 2;2 0");
		dfsTest = new DFS(grafo);
		assertTrue(dfsTest.hasUndirectedCycle());

		grafo = new UndirectedGraph("2;0 1");
		dfsTest = new DFS(grafo);
		assertFalse(dfsTest.hasUndirectedCycle());		
	}

	@Test
	public void testGetUndirCycle() {
		GraphInterface grafo = new UndirectedGraph("1");
		DFS dfsTest = new DFS(grafo);
		assertTrue(dfsTest.getUndirCycle() == null);
		
		grafo = new UndirectedGraph("3;0 1;1 2;2 0");
		dfsTest = new DFS(grafo);
		assertEquals(3,dfsTest.getUndirCycle().size());
	}

	@Test
	public void testPath() {
		GraphInterface grafo = new UndirectedGraph("2");
		BFS bfsTest = new BFS(grafo);
		assertTrue(bfsTest.getShortestPath(0, 1) == null);
		
		grafo = new UndirectedGraph("2;0 1");
		bfsTest = new BFS(grafo);
		assertTrue(bfsTest.getShortestPath(0, 1) != null &&
				bfsTest.getShortestPath(0, 1).size() == 1);
		
		grafo = new UndirectedGraph("4;0 1;2 3");
		bfsTest = new BFS(grafo);
		assertTrue(bfsTest.getShortestPath(0, 3) == null);

		grafo = new UndirectedGraph("4;0 1;1 2;2 3");
		bfsTest = new BFS(grafo);
		assertTrue(bfsTest.getShortestPath(0, 3) != null &&
				   bfsTest.getShortestPath(0, 3).size() == 3);
		
	}
	
	@Test
	public void testVisitGeneralraph() {
		GraphInterface grafo = new UndirectedGraph("2");
		BFS bfsTest = new BFS(grafo);
		assertTrue(bfsTest.getNodesInOrderOfVisitGeneralGraph().size() == 2);		

		grafo = new DirectedGraph("4;0 3;2 1");
		bfsTest = new BFS(grafo);
		assertTrue(bfsTest.getNodesInOrderOfVisitGeneralGraph().size() == 4);
		
		System.out.println(bfsTest.getNodesInOrderOfVisitGeneralGraph().toString());
		
	}
	
	@Test
	public void returnNumberConComps() {
		GraphInterface grafo = new UndirectedGraph("2");
		BFS bfsTest = new BFS(grafo);
		assertTrue(bfsTest.getNumberOfConnectedComponents() == 2);		

		grafo = new UndirectedGraph("2;0 1");
		bfsTest = new BFS(grafo);
		assertTrue(bfsTest.getNumberOfConnectedComponents() == 1);
		
	}
	
	@Test
	public void testGetOrderVisitGeneral() {
		GraphInterface grafo = new UndirectedGraph("2");
		BFS bfsTest = new BFS(grafo);
		int[] order = bfsTest.getOrderOfVisitGeneralGraphCycle();
		assertTrue(order[0] == 0 && order[1] == 0);		
		
		grafo = new UndirectedGraph("2;0 1");
		bfsTest = new BFS(grafo);
		order = bfsTest.getOrderOfVisitGeneralGraphCycle();
		assertTrue(order[0] == 0 && order[1] == 1);
		
		grafo = new DirectedGraph("3;2 1;1 0");
		bfsTest = new BFS(grafo);
		order = bfsTest.getOrderOfVisitGeneralGraphCycle();
		assertTrue(order[0] == 0 && order[1] == 0 && order[2] == 0);

		grafo = new DirectedGraph("3;0 1;1 2");
		bfsTest = new BFS(grafo);
		order = bfsTest.getOrderOfVisitGeneralGraphCycle();
		assertTrue(order[0] == 0 && order[1] == 1 && order[2] == 2);		
	}
	
	@Test
	public void testBFSTree() {
		GraphInterface graph = new UndirectedGraph("2;0 1");
		BFS bfsTest = new BFS(graph);
		assertTrue(bfsTest.getBFSForest().hasEdge(0, 1));
		
		graph = new UndirectedGraph("2");
		bfsTest = new BFS(graph);
		assertFalse(bfsTest.getBFSForest().hasEdge(0, 1));

		graph = new UndirectedGraph("4;0 1;2 3");
		bfsTest = new BFS(graph);
		assertTrue(bfsTest.getBFSForest().hasEdge(0, 1));
		assertTrue(bfsTest.getBFSForest().hasEdge(2, 3));
		assertFalse(bfsTest.getBFSForest().hasEdge(0, 2));
		assertFalse(bfsTest.getBFSForest().hasEdge(3, 1));
	}
	
	@Test
	public void testOrderOfVisitDFSGeneral() {
		GraphInterface graph = new UndirectedGraph("2");
		DFS dfsTest = new DFS(graph);
		assertTrue(dfsTest.getNodesInOrderOfVisit().size() == 2);
		assertTrue(dfsTest.getNodesInOrderOfVisit().get(0) == 0);
		assertTrue(dfsTest.getNodesInOrderOfVisit().get(1) == 1);
	}
	
	@Test
	public void testOrderPostVisitDFSGeneral() {
		GraphInterface graph = new UndirectedGraph("2;0 1");
		DFS dfsTest = new DFS(graph);
		assertTrue(dfsTest.getNodesInOrderPostVisit().size() == 2);
		assertTrue(dfsTest.getNodesInOrderPostVisit().get(0) == 1);
		assertTrue(dfsTest.getNodesInOrderPostVisit().get(1) == 0);
		
		graph = new UndirectedGraph("2");
		dfsTest = new DFS(graph);
		assertTrue(dfsTest.getNodesInOrderPostVisit().size() == 2);
		assertTrue(dfsTest.getNodesInOrderPostVisit().get(0) == 0);
		assertTrue(dfsTest.getNodesInOrderPostVisit().get(1) == 1);
		
	}
	
	@Test
	public void testGetOrderOfVisit() {
		GraphInterface graph = new UndirectedGraph("2");
		DFS dfsTest = new DFS(graph);
		int[] arrOrd = dfsTest.getOrderOfVisit();
		assertEquals(0,arrOrd[0]);
		assertEquals(1,arrOrd[1]);
	}

	@Test
	public void testGetOrderPostVisit() {
		GraphInterface graph = new UndirectedGraph("2;0 1");
		DFS dfsTest = new DFS(graph);
		int[] arrOrdPost = dfsTest.getOrderPostVisit();
		assertEquals(1,arrOrdPost[0]);
		assertEquals(0,arrOrdPost[1]);
	}
	
	@Test
	public void testOrdTop() {
		GraphInterface graph = new DirectedGraph("4;0 2;1 2;1 0;3 0;3 1");
		DFS dfsTest = new DFS(graph);
		ArrayList<Integer> ordineTop = dfsTest.topologicalOrder();
		assertTrue(ordineTop.size() == 4);
		assertEquals(3,ordineTop.get(0).intValue());
		System.out.println(ordineTop);
		assertEquals(1,ordineTop.get(1).intValue());
		assertEquals(0,ordineTop.get(2).intValue());
		assertEquals(2,ordineTop.get(3).intValue());
	}
	
	@Test
	public void testOrdTopDefinition() {
		GraphInterface graph = new DirectedGraph("5; 0 1; 1 2;1 3; 2 4; 3 4");
		DFS dfsTest = new DFS(graph);
		ArrayList<Integer> topOrder = dfsTest.topologicalOrder();
		System.out.println(topOrder);
		for(int u = 0;u < topOrder.size();u++) {
			for(int v = u+1;v < topOrder.size();v++) {
				assertFalse(graph.hasEdge(v, u));
			}
		}
	}
	
}
