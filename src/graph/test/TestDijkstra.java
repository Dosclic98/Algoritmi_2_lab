package graph.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import graph.dfs.Dijkstra;
import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.Edge;
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

	@Test (expected = IllegalArgumentException.class)
	public void testDijkstraConstExc() {
		GraphInterface graph = new DirectedGraph("3;0 1 3;0 2 -6;1 2 1");
		new Dijkstra(graph);
		fail();
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testDijkstraExc() {
		GraphInterface graph = new DirectedGraph("3;0 1 3;0 2 6;1 2 1");
		Dijkstra testDij = new Dijkstra(graph);
		testDij.getDistanze(-1);
		fail();
	}
	
	@Test
	public void testDijkstraTree() {
		GraphInterface graph = new DirectedGraph("3;0 1 3;0 2 6;1 2 1");
		Dijkstra testDij = new Dijkstra(graph);
		GraphInterface tree = testDij.getAlberoCamminiMinimi(0);
		
		assertEquals(3,tree.getOrder());
		assertTrue(tree.hasEdge(0, 1) && tree.hasEdge(1, 2) && !tree.hasEdge(0, 2));
		
	}
	
	@Test
	public void testDijkstraCamminoMinimo() {
		GraphInterface graph = new DirectedGraph("4;0 1 3;0 2 6;1 2 1;2 3 4");
		Dijkstra testDij = new Dijkstra(graph);
		ArrayList<Edge> cammino = testDij.getCamminoMinimo(0, 2);
		assertEquals(2,cammino.size());
		assertEquals(new Edge(0,1),cammino.get(0));
		assertEquals(new Edge(1,2),cammino.get(1));
		
		graph = new DirectedGraph("4;0 1 3;0 2 6;1 2 1");
		testDij = new Dijkstra(graph);
		cammino = testDij.getCamminoMinimo(0, 3);
		assertTrue(cammino == null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testDijkstraCamminoMinimoExc() {
		GraphInterface graph = new DirectedGraph("4;0 1 3;0 2 6;1 2 1;2 3 4");
		Dijkstra testDij = new Dijkstra(graph);
		testDij.getCamminoMinimo(0, 6);
	}

}
