package graph.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import graph.dfs.AllacciamentoIdrico;
import it.uniupo.graphLib.UndirectedGraph;

public class TestAllacciamentoIdrico {
	
	AllacciamentoIdrico allId;
	
	@Test
	public void testCreate() {
		UndirectedGraph mappa = new UndirectedGraph("4;1 2 2;1 0 5;2 3 3; 0 3 1");
		int[][] costoScavo = {{0,6,-1,8},{6,0,7,-1},{-1,7,0,10},{8,-1,10,0}};
		int costoTubo = 3;
		int puntoAll = 1;
		allId = new AllacciamentoIdrico(mappa, costoScavo, costoTubo,puntoAll);
		assertNotNull(allId);
	}

	@Test
	public void test01() {
		UndirectedGraph mappa = new UndirectedGraph("4;1 2 2;1 0 5;2 3 3; 0 3 1");
		int[][] costoScavo = {{0,6,-1,8},{6,0,7,-1},{-1,7,0,10},{8,-1,10,0}};
		int costoTubo = 3;
		int puntoAll = 1;
		allId = new AllacciamentoIdrico(mappa, costoScavo, costoTubo,puntoAll);
		assertTrue(allId.progettoComune().hasEdge(0,3));
		assertTrue(!allId.progettoComune().hasEdge(2,3));
		/*assertTrue(allId.progettoProprietari().hasEdge(2,3));
		assertTrue(!allId.progettoProprietari().hasEdge(0,3));
		assertEquals(2,allId.speseExtraComune());
		assertEquals(3,allId.speseExtraProprietario(3));*/
	}

}
