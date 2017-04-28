package edu.ycp.cs320.rvandemark.model;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class VideoTest {
	
	private Video video1;
	private Video video2;
	
	@Before
	public void setUp() {
		try { video1 = new Video("https://www.ted.com/talks/thomas_thwaites_how_i_built_a_toaster_from_scratch", 60, 12); }
		catch (IOException e) { System.err.println("error: 1"); }
		
		try { video2 = new Video("https://www.ted.com/talks/matt_ridley_when_ideas_have_sex", 90, 20); }
		catch (IOException e) { System.err.println("error: 2"); }
	}
	
	@Test
	public void testGetUrl() {
		assertEquals(video1.getUrl(), "https://www.ted.com/talks/thomas_thwaites_how_i_built_a_toaster_from_scratch");
		assertEquals(video2.getUrl(), "https://www.ted.com/talks/matt_ridley_when_ideas_have_sex");
	}
	@Test
	public void testGetEmbedUrl() {
		assertEquals(video1.getEmbedUrl(), "https://embed.ted.com/talks/thomas_thwaites_how_i_built_a_toaster_from_scratch");
		assertEquals(video2.getEmbedUrl(), "https://embed.ted.com/talks/matt_ridley_when_ideas_have_sex");
	}
	@Test
	public void testGetName() {
		assertEquals(video1.getName(), "How I built a toaster — from scratch");
		assertEquals(video2.getName(), "When ideas have sex");
	}
	@Test
	public void testGetSpeaker() {
		assertEquals(video1.getSpeaker(), "Thomas Thwaites");
		assertEquals(video2.getSpeaker(), "Matt Ridley");
	}
	@Test
	public void testGetDisciplines() {
		ArrayList<String> disciplines;
		int i;
		
		disciplines = video1.getDisciplines();
		i = 0;
		assertEquals(disciplines.get(i++), "Anthropocene");
		assertEquals(disciplines.get(i++), "Design");
		assertEquals(disciplines.get(i++), "Entertainment");
		assertEquals(disciplines.get(i++), "Technology");
		
		disciplines = video2.getDisciplines();
		i = 0;
		assertEquals(disciplines.get(i++), "Business");
		assertEquals(disciplines.get(i++), "Collaboration");
		assertEquals(disciplines.get(i++), "Design");
		assertEquals(disciplines.get(i++), "Technology");
	}
//	@Test
//	public void testGetRating() {
//		assertEquals(video1.getRating(), 5.0, 0.000001);
//		assertEquals(video2.getRating(), 4.5, 0.000001);
//	}
//	
//	@Test
//	public void testGetRatingClip() {
//		assertEquals(video1.getRatingClip(), "clip:rect(0px,255px,75px,0px)");
//		assertEquals(video2.getRatingClip(), "clip:rect(0px,352px,75px,0px)");
//	}
//	
//	@Test
//	public void testFormat() {
//		String[] urls = {
//			"http://www.ted.com/talks/michael_shermer_the_pattern_behind_self_deception",
//			"http://ted.com/talks/michael_shermer_the_pattern_behind_self_deception",
//			"ted.com/talks/michael_shermer_the_pattern_behind_self_deception",
//		};
//		
//		Video[] videos = new Video[urls.length];
//		for (int i = 0; i < videos.length; i++) {
//			try { videos[i] = new Video(urls[i], i, i); }
//			catch (IOException e) { e.printStackTrace(); }
//			assertEquals(videos[i].getEmbedUrl(), "https://embed.ted.com/talks/michael_shermer_the_pattern_behind_self_deception");
//		}
//	}
	
	@Test(expected=IOException.class)
	public void testFormatRequiresTEDProtocol() throws IOException {
		new Video("http://www.youtube.com/talks/michael_shermer_the_pattern_behind_self_deception", 5, 1);
	}
}