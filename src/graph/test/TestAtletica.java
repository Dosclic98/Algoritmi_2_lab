package graph.test;

import static org.junit.Assert.*;

import org.junit.Test;

import graph.dfs.Atletica;

public class TestAtletica {

	@Test
	public void testScelta1() {
		int[] rendAtleta1 = {8, 4, 2, 6, 3};
		int[] rendAtleta2 = {3, 10, 7, 7, 4};
		int numeroDiscipline = 5;
		
		Atletica test = new Atletica(numeroDiscipline);
		assertEquals(2,test.scelta(rendAtleta1, rendAtleta2));
	}

	@Test
	public void testScelta2() {
		int[] rendAtleta1 = {8, 4, 2, 6, 3};
		int[] rendAtleta2 = {3, 10, 7, 4, 4};
		int numeroDiscipline = 5;
		
		Atletica test = new Atletica(numeroDiscipline);
		assertEquals(0,test.scelta(rendAtleta1, rendAtleta2));
	}

	@Test (expected = IllegalArgumentException.class)
	public void testScelta3() {
		int[] rendAtleta1 = {8, 4, 2, 6, 3, 1};
		int[] rendAtleta2 = {3, 10, 7, 4, 4};
		int numeroDiscipline = 5;
		
		Atletica test = new Atletica(numeroDiscipline);
		assertEquals(0,test.scelta(rendAtleta1, rendAtleta2));
	}
	
}
