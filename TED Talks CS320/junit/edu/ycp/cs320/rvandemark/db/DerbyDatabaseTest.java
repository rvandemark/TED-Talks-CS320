package edu.ycp.cs320.rvandemark.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.rvandemark.model.Video;

public class DerbyDatabaseTest {
	
	private final static boolean CREATE = false;
	private static boolean NEW = true;
	
	private DerbyDatabase db;
	
	@Before
	public void setUp () throws IOException {
		db = new DerbyDatabase();
		
		if (CREATE && NEW) {
			NEW = false;
			db.init();
		}
	}
	
	@Test
	public void testGetUserById() throws SQLException {
		int i = 0;
		assertEquals(db.getUserById(++i).getEmail(), "rvandemark@ycp.edu");
		assertEquals(db.getUserById(++i).getEmail(), "emoore13@ycp.edu");
		assertEquals(db.getUserById(++i).getEmail(), "klangrill@ycp.edu");
		assertEquals(db.getUserById(++i).getEmail(), "jdebose@ycp.edu");
		assertEquals(db.getUserById(++i).getEmail(), "shamilton@ycp.edu");
		assertEquals(db.getUserById(++i).getEmail(), "djhake2@ycp.edu");
	}
	
	@Test
	public void testGetUserByEmail() throws SQLException {
		assertEquals(db.getUserByEmail("rvandemark@ycp.edu").getLastName(), "Vandemark");
		assertEquals(db.getUserByEmail("emoore13@ycp.edu").getLastName(), "Moore");
		assertEquals(db.getUserByEmail("klangrill@ycp.edu").getLastName(), "Langrill");
		assertEquals(db.getUserByEmail("jdebose@ycp.edu").getLastName(), "DeBose");
		assertEquals(db.getUserByEmail("shamilton@ycp.edu").getLastName(), "Hamilton");
		assertEquals(db.getUserByEmail("djhake2@ycp.edu").getLastName(), "Hake");
	}
	
	@Test
	public void testGetAdminById() throws SQLException {
		int i = 0;
		assertEquals(db.getAdminById(++i).getEmail(), "shamilton@ycp.edu");
		assertEquals(db.getAdminById(++i).getEmail(), "djhake2@ycp.edu");
	}
	
	@Test
	public void testValidCredentials() throws SQLException, IOException {
		assertTrue(db.validCredentials("rvandemark@ycp.edu", "p@$$word1"));
		assertTrue(db.validCredentials("klangrill@ycp.edu", "p@$$word3"));
		assertFalse(db.validCredentials("djhake2@ycp.edu", "p@$$word2"));
	}
	
	@Test
	public void testUserExists() throws SQLException, IOException {
		assertTrue(db.userExists("jdebose@ycp.edu"));
		assertTrue(db.userExists("emoore13@ycp.edu"));
		assertFalse(db.userExists("djhake3@ycp.edu"));
	}
	
	@Test
	public void testGetDisciplineById() throws SQLException {
		int i = 0;
		assertEquals(db.getDisciplineById(++i), "Civil Engineering");
		assertEquals(db.getDisciplineById(++i), "Computer Engineering");
		assertEquals(db.getDisciplineById(++i), "Computer Science");
		assertEquals(db.getDisciplineById(++i), "Graphic Design");
		assertEquals(db.getDisciplineById(++i), "Creativity");
		assertEquals(db.getDisciplineById(++i), "Physics");
		assertEquals(db.getDisciplineById(++i), "Mathematics");
		assertEquals(db.getDisciplineById(++i), "Psychology");
		assertEquals(db.getDisciplineById(++i), "Skateboarding");
		assertEquals(db.getDisciplineById(++i), "Rock Climbing");
		assertEquals(db.getDisciplineById(++i), "MMA");
		assertEquals(db.getDisciplineById(++i), "Entrepreneurship");
	}
	
	@Test
	public void testGetVideoById() throws SQLException, IOException {
		int i = 0;
		assertEquals(db.getVideoById(++i).getName(), "How I built a toaster — from scratch");
		assertEquals(db.getVideoById(++i).getName(), "When ideas have sex");
		assertEquals(db.getVideoById(++i).getName(), "Progress is not a zero-sum game");
	}
	
	@Test
	public void testGetVideoIdByName() throws SQLException, IOException {
		assertEquals(db.getVideoIdByName("When ideas have sex"), (Integer)2);
		assertEquals(db.getVideoIdByName("How to land on a comet"), (Integer)6);
		assertEquals(db.getVideoIdByName("Software (as) art"), (Integer)13);
		assertEquals(db.getVideoIdByName("Impossible photography"), (Integer)19);
	}
	
	@Test
	public void testVideoExistsByName() throws SQLException, IOException {
		assertTrue(db.videoExistsByName("When ideas have sex"));
		assertTrue(db.videoExistsByName("How photography connects us"));
		assertTrue(db.videoExistsByName("The passing of time, caught in a single photo"));
		assertFalse(db.videoExistsByName("Impossible Photography"));
		assertFalse(db.videoExistsByName(""));
	}
	
//	@Test
//	public void testVideoExistsByUrl() throws SQLException, IOException {
//		assertTrue(db.videoExistsByUrl("https://www.ted.com/talks/sanjay_dastoor_a_skateboard_with_a_boost"));
//		assertTrue(db.videoExistsByUrl("https://www.ted.com/talks/edward_burtynsky_on_manufactured_landscapes"));
//		assertFalse(db.videoExistsByUrl("https://www.youtube.com/talks/edward_burtynsky_on_manufactured_landscapes"));
//		assertFalse(db.videoExistsByUrl(""));
//	}
}