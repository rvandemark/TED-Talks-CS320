package edu.ycp.cs320.rvandemark.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class YCPClassTest {
	
	private YCPClass ycpClass;
	
	private Admin admin;
	private Assignment assign1, assign2;
	private User student1, student2;
	
	@Before
	public void setUp() {
		admin = new Admin("rvandemark@ycp.edu", "rvandy", "p@$$word", "Nick", "Vandemark", 34, new String[]{"Computer Engineering", "Computer Science", "Psychology"});
		
		ycpClass = new YCPClass(admin, "Intro to Civil Engineering", "CVE101", 103);
		
		assign1 = new SpecificVideoAssignment(ycpClass, "Watch this video", 4, 30, 2017, "https://www.ted.com/talks/jonathan_rossiter_a_robot_that_eats_pollution");
		assign2 = new VideoByDisciplineAssignment(ycpClass, "Watch ANOTHER video", 5, 25, 2017, "Civil Engineering", 2);
		
		student1 = new User("emoore13@ycp.edu", "ezzypoo", "p@$$word", "Ezra", "Moore", 342, new String[]{"Skateboarding", "Computer Science", "Graphic Design"});
		student2 = new User("klangrill@ycp.edu", "klangrill", "p@$$word", "Kyle", "Langrill", 42, new String[]{"Skateboarding", "Vaping"});
	}
	
	@Test
	public void testGetName() {
		assertEquals("CVE101", ycpClass.getName());
	}
	
	@Test
	public void testGetSection() {
		assertEquals(103, ycpClass.getSection());
	}
	
	@Test
	public void testGetAdmin() {
		assertEquals(admin, ycpClass.getAdmin());
	}
	
	@Test
	public void testGetAndInsertAssignment() {
		assertEquals(0, ycpClass.getAssignments().size());
		ycpClass.getAssignments().add(assign1);
		ycpClass.getAssignments().add(assign2);
		assertEquals(ycpClass.getAssignments().get(0), assign1);
		assertEquals(ycpClass.getAssignments().get(1), assign2);
	}
	
	@Test
	public void testGetAndInsertStudent() {
		assertEquals(0, ycpClass.getStudents().size());
		ycpClass.getStudents().add(student1);
		ycpClass.getStudents().add(student2);
		assertEquals(ycpClass.getStudents().get(0), student1);
		assertEquals(ycpClass.getStudents().get(1), student2);
	}
}