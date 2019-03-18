package graph.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
		for(Integer e:order) {
			System.out.println(e);
		}
	}
	
	@Test
	public void testGetForest() throws NotAllNodesReachedException {
		GraphInterface grafo = new UndirectedGraph ("4;2 0;3 1");
		DFS dfsTest = new DFS(grafo);	
		GraphInterface tree = dfsTest.getForest();
		assertTrue(tree.hasEdge(2, 0) && tree.hasEdge(3, 1));
		assertTrue(!tree.hasEdge(0, 1) && !tree.hasEdge(2, 3));
	}
	
}
