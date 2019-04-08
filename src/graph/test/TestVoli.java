package graph.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import graph.dfs.Voli;
import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.Edge;

public class TestVoli {

	@Test
	public void testTempo() {
		DirectedGraph graph = new DirectedGraph("3;0 1 3;0 2 6;1 2 1");
		Voli testVoli = new Voli(graph);
		int tempo = testVoli.tempo(0, 2);
		assertEquals(6,tempo);
		
		graph = new DirectedGraph("3;0 1 3");
		testVoli = new Voli(graph);
		tempo = testVoli.tempo(0, 2);
		assertEquals(-1,tempo);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testTempoExc1() {
		DirectedGraph graph = new DirectedGraph("3;0 1 3;0 2 6;1 2 1");
		Voli testVoli = new Voli(graph);
		testVoli.tempo(-1, 2);		
		fail();
	}

	@Test (expected = IllegalArgumentException.class)
	public void testTempoExc2() {
		DirectedGraph graph = new DirectedGraph("3;0 1 3;0 2 6;1 2 1");
		Voli testVoli = new Voli(graph);
		testVoli.tempo(0, 3);		
		fail();
	}

	@Test
	public void testScali() {
		DirectedGraph graph = new DirectedGraph("3;0 1 3;0 2 6;1 2 1");
		Voli testVoli = new Voli(graph);
		int scali = testVoli.scali(0, 2);
		assertEquals(1,scali);
		
		graph = new DirectedGraph("3;0 1 3");
		testVoli = new Voli(graph);
		scali = testVoli.tempo(0, 2);
		assertEquals(-1,scali);
		
	}
	
	@Test
	public void testTempoMinimo() {
		DirectedGraph graph = new DirectedGraph("3;0 1 3;0 2 6;1 2 1");
		Voli testVoli = new Voli(graph);
		int tempoMinimo = testVoli.tempoMinimo(0, 2);
		assertEquals(4,tempoMinimo);

		graph = new DirectedGraph("3;0 1 3");
		testVoli = new Voli(graph);
		tempoMinimo = testVoli.tempoMinimo(0, 2);
		assertEquals(-1,tempoMinimo);
		
	}
	
	@Test
	public void testPercTempoMinimo() {
		DirectedGraph graph = new DirectedGraph("3;0 1 3;0 2 6;1 2 1");
		Voli testVoli = new Voli(graph);
		ArrayList<Edge> percTempMin = testVoli.trattaVeloce(0, 2);
		assertEquals(2,percTempMin.size());
		assertEquals(percTempMin.get(0),new Edge(0,1));
		assertEquals(percTempMin.get(1),new Edge(1,2));
		
		graph = new DirectedGraph("3;0 1 3");
		testVoli = new Voli(graph);
		percTempMin = testVoli.trattaVeloce(0, 2);
		assertTrue(percTempMin == null);
		
		percTempMin = testVoli.trattaVeloce(0, 1);
		assertEquals(1,percTempMin.size());
		assertEquals(percTempMin.get(0),new Edge(0,1));
			
	}

	@Test
	public void testPercScaliMinimo() {
		DirectedGraph graph = new DirectedGraph("3;0 1 3;0 2 6;1 2 1");
		Voli testVoli = new Voli(graph);
		ArrayList<Integer> percScaliMin = testVoli.percorsoScali(0, 2);
		assertEquals(2,percScaliMin.size());
		assertEquals(0,percScaliMin.get(0).intValue());
		
		graph = new DirectedGraph("3;0 1 3");
		testVoli = new Voli(graph);
		percScaliMin = testVoli.percorsoScali(0, 2);
		assertTrue(percScaliMin == null);
		
		graph = new DirectedGraph("1");
		percScaliMin = testVoli.percorsoScali(0, 0);
		testVoli = new Voli(graph);
		assertEquals(1,percScaliMin.size());
		assertEquals(0,percScaliMin.get(0).intValue());
	}
	
}
