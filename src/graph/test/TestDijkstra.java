package graph.test;

import static org.junit.Assert.*;

import org.junit.Test;

import graph.dfs.Dijkstra;
import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.UndirectedGraph;

public class TestDijkstra {

	@Test
	public void testDijkstra() {
		GraphInterface graph = new DirectedGraph("3;0 1 3;0 2 6;1 2 1");
		Dijkstra testDij = new Dijkstra(graph);
		int[] d = testDij.getDistanze(0);
		assertEquals(0,d[0]);
		assertEquals(3,d[1]);
		assertEquals(4,d[2]);
		
		graph = new UndirectedGraph("3;0 1 3;0 2 6;1 2 1");
		testDij = new Dijkstra(graph);
		d = testDij.getDistanze(0);
		assertEquals(0,d[0]);
		assertEquals(3,d[1]);
		assertEquals(4,d[2]);

		graph = new UndirectedGraph("3;0 1 6");
		testDij = new Dijkstra(graph);
		d = testDij.getDistanze(0);
		assertEquals(0,d[0]);
		assertEquals(6,d[1]);
		assertEquals(-1,d[2]);		
	}

}
