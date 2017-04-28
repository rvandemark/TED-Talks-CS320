package edu.ycp.cs320.rvandemark.model;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class ReviewTest {
	
	private User user;
	private Video video;
	
	private Review extractedReview;
	private Review newReview;
	
	@Before
	public void setUp() {
		user = new User("rvandemark@ycp.edu", "rvandy", "P@$$WORD", "Nick", "Vandemark", 0, new String[]{});
		try { video = new Video("http://www.ted.com/talks/caitlin_doughty_a_burial_practice_that_nourishes_the_planet", 44, 10); }
		catch (IOException e) { e.printStackTrace(); }
		
//		extractedReview = new Review(user, video, "Here's some cool review text, bruh", 9, 20, 2017, 23, 1);
//		newReview = new Review(user, video, "I'm a new review, and here's a bad keyword", 3);
	}
	
	@Test
	public void testGetAuthor() {
		assertEquals(user, extractedReview.getAuthor());
		assertEquals(user, newReview.getAuthor());
	}
	@Test
	public void testGetParent() {
		assertEquals(video, extractedReview.getParent());
		assertEquals(video, newReview.getParent());
	}
	@Test
	public void testGetText() {
		assertEquals("Here's some cool review text, bruh", extractedReview.getText());
		assertEquals("I'm a new review, and here's a bad *******", newReview.getText());
	}
	@Test
	public void testGetScore() {
		assertEquals(23, extractedReview.getScore());
		assertEquals(0, newReview.getScore());
	}
	@Test
	public void testGetValue() {
		assertEquals(1, extractedReview.getValue());
		assertEquals(3, newReview.getValue());
	}
}