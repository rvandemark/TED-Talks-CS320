package edu.ycp.cs320.rvandemark.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.rvandemark.model.Numbers;

public class NumbersControllerTest {
	private NumbersController controller;
	private Numbers model;
	private final static double DELTA = 0.000001;
	
	@Before
	public void setUp () {
		controller = new NumbersController();
		model = new Numbers();
		controller.setModel(model);
	}
	
	@Test
	public void testAdd1 () {
		model.setFirst(10.0);
		model.setSecond(20.0);
		model.setThird(7.5);
		controller.add();
		assertEquals(model.getResult(), model.getFirst() + model.getSecond() + model.getThird(), DELTA);
	}
	@Test
	public void testAdd2 () {
		model.setFirst(10.0);
		model.setSecond(20.0);
		model.setThird(-5.0);
		controller.add();
		assertEquals(model.getResult(), model.getFirst() + model.getSecond() + model.getThird(), DELTA);
	}
	@Test
	public void testMultiply1 () {
		model.setFirst(10.0);
		model.setSecond(20.0);
		model.setThird(7.5);
		controller.multiply();
		assertEquals(model.getResult(), model.getFirst() * model.getSecond() * model.getThird(), DELTA);
	}
	@Test
	public void testMultiply2 () {
		model.setFirst(10.0);
		model.setSecond(20.0);
		model.setThird(-1.0);
		controller.multiply();
		assertEquals(model.getResult(), model.getFirst() * model.getSecond() * model.getThird(), DELTA);
	}
	@Test
	public void testMultiply3 () {
		model.setFirst(10.0);
		model.setSecond(20.0);
		model.setThird(0.0);
		controller.multiply();
		assertEquals(model.getResult(), model.getFirst() * model.getSecond() * model.getThird(), DELTA);
	}
}