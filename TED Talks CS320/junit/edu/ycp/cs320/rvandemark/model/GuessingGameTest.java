package edu.ycp.cs320.rvandemark.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.rvandemark.model.GuessingGame;

public class GuessingGameTest {
	private GuessingGame model;
	
	@Before
	public void setUp() {
		model = new GuessingGame();
	}
	
	@Test
	public void testSetMin() {
		model.setMin(1);
		assertEquals(1, model.getMin());
	}
	
	@Test
	public void testSetMax() {
		model.setMax(100);
		assertEquals(100, model.getMax());
	}
	
	@Test
	public void testGetGuess() {
		model.setMin(50);
		model.setMax(52);
		assertEquals(51, model.getGuess());
		
		model.setMax(75);
		assertEquals(62, model.getGuess());
	}
	
	@Test
	public void testSetIsLessThan () {
		model.setIsLessThan(42);
		assertEquals(41, model.getMax());
	}
	
	@Test
	public void testSetIsGreaterThan () {
		model.setIsGreaterThan(21);
		assertEquals(22, model.getMin());
	}
	
	@Test
	public void testIsDone () {
		model.setIsGreaterThan(40);
		model.setIsLessThan(42);
		assertTrue(model.isDone());
	}
}
