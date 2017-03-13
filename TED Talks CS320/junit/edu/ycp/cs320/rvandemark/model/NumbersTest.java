package edu.ycp.cs320.rvandemark.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class NumbersTest {
	private Numbers model;
	private final static double DELTA = 0.000001;
	
	@Before
	public void setUp () {
		model = new Numbers();
	}
	
	@Test
	public void testGetAndSetFirst () {
		model.setFirst(10.0);
		assertEquals(10.0, model.getFirst(), DELTA);
	}
	@Test
	public void testGetAndSetSecond () {
		model.setSecond(20.0);
		assertEquals(20.0, model.getSecond(), DELTA);
	}
	@Test
	public void testGetAndSetThird () {
		model.setThird(30.0);
		assertEquals(30.0, model.getThird(), DELTA);
	}
}