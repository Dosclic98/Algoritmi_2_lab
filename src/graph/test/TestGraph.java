package graph.test;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import graph.dfs.DFS;
import it.uniupo.graphLib.DirectedGraph;
import it.uniupo.graphLib.Edge;
import it.uniupo.graphLib.GraphInterface;
import it.uniupo.graphLib.UndirectedGraph;

public class TestGraph {

	@Test
	public void testCreate() {
		GraphInterface grafo = new DirectedGraph(3);
		DFS bfsTest = new DFS(grafo);
		assertNotNull(bfsTest);
	}

}
