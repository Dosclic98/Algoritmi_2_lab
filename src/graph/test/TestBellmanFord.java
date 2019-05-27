package graph.test;

import static org.junit.Assert.*;

import org.junit.Test;

import graph.dfs.BellmanFord;
import it.uniupo.graphLib.DirectedGraph;

public class TestBellmanFord {

	@Test
	public void test1() {
		DirectedGraph test = new DirectedGraph("3;0 1 3;1 2 -2;0 2 2");
		BellmanFord bell = new BellmanFord(test,0);
		assertTrue(1 == bell.getDist(2));
	}

	@Test
	public void test2() {
		DirectedGraph test = new DirectedGraph("5;0 1 3;0 3 3;0 2 2;1 2 -3;3 4 -1;4 2 -1");
		BellmanFord bell = new BellmanFord(test,0);
		assertTrue(0 == bell.getDist(0));
		assertTrue(3 == bell.getDist(1));
		assertTrue(0 == bell.getDist(2));
		assertTrue(3 == bell.getDist(3));
		assertTrue(2 == bell.getDist(4));
		assertFalse(bell.hasNegCycle());
	}
	
	@Test
	public void test2NegCy() {
		DirectedGraph test = new DirectedGraph("3;0 1 4;1 2 -2;2 0 -3");
		BellmanFord bell = new BellmanFord(test,0);
		assertTrue(Double.NEGATIVE_INFINITY == bell.getDist(0));		
	}
	
	@Test
	public void testHasNegCycle() {
		DirectedGraph test = new DirectedGraph("3;0 1 4;1 2 -2;2 0 -3");
		BellmanFord bell = new BellmanFord(test,0);
		assertTrue(bell.hasNegCycle());		
	}	
}
