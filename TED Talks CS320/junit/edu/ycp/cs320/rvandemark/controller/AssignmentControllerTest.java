package edu.ycp.cs320.rvandemark.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.rvandemark.model.Admin;
import edu.ycp.cs320.rvandemark.model.Assignment;
import edu.ycp.cs320.rvandemark.model.SpecificVideoAssignment;
import edu.ycp.cs320.rvandemark.model.VideoByDisciplineAssignment;
import edu.ycp.cs320.rvandemark.model.YCPClass;

public class AssignmentControllerTest {
	
	private AssignmentController controller;
	private Assignment model;
	
	private Admin admin = new Admin("rvandemark@ycp.edu", "rvandy", "p@$$word", "Vandemark", "Nick", 2, new String[]{"Computer Science", "Computer Engineering"});
	private YCPClass ycpClass = new YCPClass(admin, "Intro to Civil Engineering", "CVE100", 101);
	
	@Before
	public void setUp() {
		controller = new AssignmentController(model);
	}
	
	@Test
	public void testValidityForNullModel() {
		model = null;
		controller.setModel(model);
		assertEquals((Integer)1, controller.getAssignmentValidity());
	}
	
	@Test
	public void testValidityForNullModelName() {
		model = new VideoByDisciplineAssignment(ycpClass, null, 1, 1, 2017, 1, 1, 2018, "Computer Science", 2);
		controller.setModel(model);
		assertEquals((Integer)2, controller.getAssignmentValidity());
	}
	
	@Test
	public void testValidityForNullModelDueDate() throws Exception {
		model = new VideoByDisciplineAssignment(ycpClass, "Assignment Name", 1, 1, 2017, 1, null, null, "Computer Science", 2);
		controller.setModel(model);
		assertEquals((Integer)3, controller.getAssignmentValidity());
	}
	
	@Test
	public void testValidityForDueDateBeforeAssignDate() throws Exception {
		model = new VideoByDisciplineAssignment(ycpClass, "Assignment Name", 1, 1, 2017, 1, 1, 2016, "Computer Science", 2);
		controller.setModel(model);
		assertEquals((Integer)4, controller.getAssignmentValidity());
	}
	
	@Test
	public void testValidityForNullSpecificVideo() throws Exception {
		model = new SpecificVideoAssignment(ycpClass, "Assignment Name", 1, 1, 2017, 1, 1, 2018, 0);
		controller.setModel(model);
		assertEquals((Integer)5, controller.getAssignmentValidity());
	}
	
	@Test
	public void testValidityForNullDiscipline() throws Exception {
		model = new VideoByDisciplineAssignment(ycpClass, "Assignment Name", 1, 1, 2017, 1, 1, 2018, null, 2);
		controller.setModel(model);
		assertEquals((Integer)6, controller.getAssignmentValidity());
	}
	
	@Test
	public void testValidityForNullOrInvalidCount() throws Exception {
		model = new VideoByDisciplineAssignment(ycpClass, "Assignment Name", 1, 1, 2017, 1, 1, 2018, "Computer Science", null);
		controller.setModel(model);
		assertEquals((Integer)7, controller.getAssignmentValidity());
	}
	
	@Test
	public void testValid() throws Exception {
		model = new VideoByDisciplineAssignment(ycpClass, "Assignment Name", 1, 1, 2017, 1, 1, 2018, "Computer Science", 2);
		controller.setModel(model);
		assertEquals((Integer)0, controller.getAssignmentValidity());
	}
}