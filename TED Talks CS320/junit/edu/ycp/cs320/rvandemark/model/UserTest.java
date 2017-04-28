package edu.ycp.cs320.rvandemark.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
	
	private User user;
	
	@Before
	public void setUp() {
		user = new User(
			"rvandemark@ycp.edu",
			"rvandy",
			"p@$$word",
			"Nick",
			"Vandemark",
			9001,
			new String[]{"Computer Engineering", "Computer Science", "Physics", "Math", "Psychology", "Philosophy"}
		);
	}
	
	@Test
	public void testGetEmail() {
		assertEquals(user.getEmail(), "rvandemark@ycp.edu");
	}
	@Test
	public void testGetUserID() {
		assertEquals(user.getScreenName(), "rvandy");
	}
	@Test
	public void testGetPassword() {
		assertEquals(user.getPassword(), "p@$$word");
	}
	@Test
	public void testGetFirstName() {
		assertEquals(user.getFirstName(), "Nick");
	}
	@Test
	public void testGetLastName() {
		assertEquals(user.getLastName(), "Vandemark");
	}
	@Test
	public void testGetPoints() {
		assertEquals(user.getPoints(), 9001);
	}
	@Test
	public void testGetDisciplines() {
		assertEquals(user.getDisciplines()[0], "Computer Engineering");
		assertEquals(user.getDisciplines()[1], "Computer Science");
		assertEquals(user.getDisciplines()[2], "Physics");
		assertEquals(user.getDisciplines()[3], "Math");
		assertEquals(user.getDisciplines()[4], "Psychology");
		assertEquals(user.getDisciplines()[5], "Philosophy");
	}
}