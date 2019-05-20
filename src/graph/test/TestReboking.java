package graph.test;

import static org.junit.Assert.*;

import org.junit.Test;

import graph.dfs.Rebooking;

public class TestReboking {

	@Test
	public void testMaxValue1() {
		int[] volume = {5, 8, 3};
		int[] value = {3 ,11, 9};
		int capacity = 10;
		
		Rebooking test = new Rebooking(capacity);
		assertEquals(12, test.maxSaving(volume, value));
	}

	@Test
	public void testMaxValue2() {
		int[] volume = {5, 8, 3};
		int[] value = {3 ,11, 9};
		int capacity = 20;
		
		Rebooking test = new Rebooking(capacity);
		assertEquals(23, test.maxSaving(volume, value));
	}

	@Test
	public void testMaxValue3() {
		int[] volume = {5, 8, 3};
		int[] value = {3 ,11, 9};
		int capacity = 0;
		
		Rebooking test = new Rebooking(capacity);
		assertEquals(0, test.maxSaving(volume, value));
	}

	@Test
	public void testMaxValue4() {
		int[] volume = {5, 8, 3};
		int[] value = {3 ,11, 9};
		int capacity = -1;
		
		Rebooking test = new Rebooking(capacity);
		assertEquals(-1, test.maxSaving(volume, value));
	}	

	@Test
	public void testMaxValue5() {
		int[] volume = {5, 8};
		int[] value = {3 ,11, 9};
		int capacity = -1;
		
		Rebooking test = new Rebooking(capacity);
		assertEquals(-1, test.maxSaving(volume, value));
	}	
	
}
