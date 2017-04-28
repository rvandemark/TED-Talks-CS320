package edu.ycp.cs320.rvandemark.model;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class AdminTest {
	
	private Admin admin;
	private User author;
	private User flagger;
	
	private Video video;
	private Review review;
	
	@Before
	public void setUp() {
		admin = new Admin("rvandemark@ycp.edu", "rvandy.adm", "cool password", "Nick", "Vandemark", 0, new String[]{});
		author = new User("klangrill@ycp.edu", "vapegod", "another cool password", "Kyle", "Langrill", 0, new String[]{});
		flagger = new User("emoore13@ycp.edu", "ezzypoo", "bad password", "Ezra", "Moore", 0, new String[]{});
		
		try { video = new Video("http://www.ted.com/talks/rob_knight_how_our_microbes_make_us_who_we_are", 60, 14); }
		catch (IOException e) { e.printStackTrace(); }
		
//		review = new Review(author, video, "This video was awesome!", 1, 2, 2017, 0, 1);
	}
}