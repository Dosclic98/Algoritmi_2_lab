package graph.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import graph.dfs.Zaino;

public class TestZaino {

	@Test
	public void testMaxValue1() {
		int[] value = {1 ,1, 3};
		int[] volume = {1, 2, 3};
		int capacity = 3;
		
		Zaino test = new Zaino(value, volume, capacity);
		assertEquals(3, test.getMaxVal());
	}

	@Test
	public void testMaxValue2() {
		int[] value = {1 ,1, 3};
		int[] volume = {1, 2, 4};
		int capacity = 3;
		
		Zaino test = new Zaino(value, volume, capacity);
		assertEquals(2, test.getMaxVal());
	}

	@Test
	public void testMaxValue3() {
		int[] value = {4 , 4, 4};
		int[] volume = {1, 2, 4};
		int capacity = 4;
		
		Zaino test = new Zaino(value, volume, capacity);
		assertEquals(8, test.getMaxVal());
	}
	
	@Test
	public void testSolCreat1() {
		int[] value = {1 ,1, 3};
		int[] volume = {1, 2, 4};
		int capacity = 3;
		
		Zaino test = new Zaino(value, volume, capacity);
		ArrayList<Integer> sol = test.buildSol();
		
		assertEquals(2, sol.size());
		assertEquals(0, sol.get(0).intValue());
		assertEquals(1, sol.get(1).intValue());
	}

	@Test
	public void testSolCreat2() {
		int[] value = {1 ,1, 3};
		int[] volume = {1, 2, 3};
		int capacity = 3;
		
		Zaino test = new Zaino(value, volume, capacity);
		ArrayList<Integer> sol = test.buildSol();
		
		assertEquals(1, sol.size());
		assertEquals(2, sol.get(0).intValue());
	}
	
}
