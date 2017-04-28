package edu.ycp.cs320.rvandemark.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class NotificationTest {
	
	private User flagger;
	private Review target;
	private Notification notification;
	
	@Before
	public void setUp() {
		flagger = new User("rvandemark@ycp.edu", "rvandy", "p@$$word", "Nick", "Vandemark", 0, new String[]{});
//		target = new Review(null, null, "Offensive text within here bruh", 4, 5, 2017, 32, 5);
		notification = new Notification(flagger, target);
	}
	
	@Test
	public void testGetFlagger() {
		assertEquals(notification.getFlagger(), flagger);
	}
	@Test
	public void testGetTarget() {
		assertEquals(notification.getTarget(), target);
	}
}