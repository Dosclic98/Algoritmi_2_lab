package graph.test;

import static org.junit.Assert.*;


import org.junit.Test;

import graph.dfs.Metro;
import it.uniupo.graphLib.UndirectedGraph;

public class TestMetro {

	@Test
	public void testPath() {
		UndirectedGraph graph = new UndirectedGraph("2");
		Metro metroTest = new Metro(graph);
		assertTrue(metroTest.fermate(0, 1) == null);
		
		graph = new UndirectedGraph("2;0 1");
		metroTest = new Metro(graph);
		assertTrue(metroTest.fermate(0, 1).size() == 2 &&
				   metroTest.fermate(0, 1).get(0) == 0 &&
				   metroTest.fermate(0, 1).get(1) == 1);

		graph = new UndirectedGraph("4;0 1;2 3");
		metroTest = new Metro(graph);
		assertTrue(metroTest.fermate(1, 3) == null);

		graph = new UndirectedGraph("6;0 1;2 3;1 2;1 3;3 4;4 5");
		metroTest = new Metro(graph);
		assertTrue(metroTest.fermate(0, 4).size() == 4);
		System.out.println(metroTest.fermate(0, 4));
		
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void testPathExc() {
		UndirectedGraph graph = new UndirectedGraph("3");
		Metro metroTest = new Metro(graph);
		metroTest.fermate(0, 3);	
		fail();
	}
}
