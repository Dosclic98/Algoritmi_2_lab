package graph.test;

import static org.junit.Assert.*;

import org.junit.Test;

import graph.dfs.Kruskal;
import it.uniupo.graphLib.UndirectedGraph;

public class TestKruskal {

	@Test
	public void testKruskal() {
		UndirectedGraph g = new UndirectedGraph("3;0 2 5;0 1 2;1 2 2");
		Kruskal kr = new Kruskal(g);
		UndirectedGraph tree = kr.algoKruskal();
		assertTrue(tree.hasEdge(0, 1));
		assertTrue(tree.hasEdge(1, 2));
		
		g = new UndirectedGraph("3;0 2 1;0 1 2;1 2 2");
		kr = new Kruskal(g);
		tree = kr.algoKruskal();
		assertTrue(tree.hasEdge(0, 1));
		assertTrue(tree.hasEdge(0, 2));
	}

}
