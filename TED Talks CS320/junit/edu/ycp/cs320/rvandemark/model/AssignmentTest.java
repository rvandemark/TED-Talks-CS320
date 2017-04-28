package edu.ycp.cs320.rvandemark.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AssignmentTest {
	
	private SpecificVideoAssignment svmodel;
	private VideoByDisciplineAssignment vbdmodel;
	
	@Before
	public void setUp() {
		svmodel = new SpecificVideoAssignment(null, "CivE Video", 4, 10, 2017, "https://www.ted.com/talks/rose_george_let_s_talk_crap_seriously");
		vbdmodel = new VideoByDisciplineAssignment(null, "Building Bridges", 3, 15, 2017, "Civil Engineering", 2);
	}
	
	@Test
	public void testGetName() {
		assertEquals(svmodel.getName(), "CivE Video");
		assertEquals(vbdmodel.getName(), "Building Bridges");
	}
	
	@Test
	public void testGetYCPClass() {
		assertEquals(svmodel.getYCPClass(), null);
		assertEquals(vbdmodel.getYCPClass(), null);
	}
}