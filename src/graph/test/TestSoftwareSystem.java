package graph.test;

import static org.junit.Assert.*;

import org.junit.Test;

import graph.dfs.SoftwareSystem;
import it.uniupo.graphLib.DirectedGraph;

public class TestSoftwareSystem {

	@Test
	public void testHasCycle() {
		DirectedGraph graph = new DirectedGraph("3;0 1;1 2;0 2");
		SoftwareSystem ssTest = new SoftwareSystem(graph);
		assertFalse(ssTest.hasCycle());
		
		
		graph = new DirectedGraph("5;3 2;3 4;4 2");
		ssTest = new SoftwareSystem(graph);
		assertFalse(ssTest.hasCycle());
		
		graph = new DirectedGraph("3;0 1;1 2;2 0");
		ssTest = new SoftwareSystem(graph);
		assertTrue(ssTest.hasCycle());
		
		graph = new DirectedGraph("5;0 1;2 1;2 0;2 3;3 4;4 2");
		ssTest = new SoftwareSystem(graph);
		assertTrue(ssTest.hasCycle());

		graph = new DirectedGraph("6;4 3;4 1;1 3;3 0;3 2;0 1;2 0;0 5;2 5");
		ssTest = new SoftwareSystem(graph);
		assertTrue(ssTest.hasCycle());		
	}

}
