package graph.test;

import static org.junit.Assert.*;

import org.junit.Test;

import graph.dfs.FloydWarshall;
import it.uniupo.graphLib.DirectedGraph;

public class TestFloydWarshall {

	@Test
	public void test1() {
		DirectedGraph test = new DirectedGraph("3;0 1 3;1 2 -2;0 2 2");
		FloydWarshall flo = new FloydWarshall(test);
		assertTrue(1 == flo.getDist(0, 2));
		
	}
	
	@Test
	public void test2() {
		DirectedGraph test = new DirectedGraph("5;0 1 3;0 3 3;0 2 2;1 2 -3;3 4 -1;4 2 -1");
		FloydWarshall flo = new FloydWarshall(test);
		assertTrue(0 == flo.getDist(0, 0));
		assertTrue(3 == flo.getDist(0, 1));
		assertTrue(0 == flo.getDist(0, 2));
		assertTrue(3 == flo.getDist(0, 3));
		assertTrue(2 == flo.getDist(0, 4));
		assertFalse(flo.hasNegCycle());
	}
	
	@Test
	public void testNegCycle() {
		DirectedGraph test = new DirectedGraph("3;0 1 4;1 2 -2;2 0 -3");
		FloydWarshall bell = new FloydWarshall(test);
		assertTrue(bell.hasNegCycle());				
	}

}
