package edu.ycp.cs320.rvandemark.model;

import java.io.IOException;

import edu.ycp.cs320.rvandemark.db.DerbyDatabase;

public class Engine {
	
	private static DerbyDatabase db = new DerbyDatabase();
	
	public static DerbyDatabase getDB() {
		return db;
	}
	
	public static boolean videoIsValid(String url) {
		return db.videoExistsByUrl(url);
	}
	public static void createVideo(String url) {
		if (videoIsValid(url)) {
			try {
				Video v = new Video(url);
				db.insertVideo(v.getUrl(), v.getEmbedUrl(), v.getName(), v.getSpeaker(), v.getThumbnailUrl(), v.getTotalRating(),
					v.getNumRatings(), v.getRating(), v.getUploadMonth(), v.getUploadDay(), v.getUploadYear(), v.getDisciplineLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void createReview(User author, Video parent, String text, int pointValue) {
//		Review r = new Review(author, parent, text, pointValue);
		/*
		 * 
		 * write review data to:
		 * 		author's reviews
		 * 		video's reviews
		 * 		review text
		 * 		reward points to author
		 * 
		 */
	}
	public static void editReview(User flagger, Review review, String text) {
		if (flagger instanceof Admin || review.getAuthor().equals(flagger)) {
			review.setText(text);
		}
	}
	public static void deleteReview(User flagger, Review review) {
		if (flagger instanceof Admin || review.getAuthor().equals(flagger)) {
			/*
			 * 
			 * delete references of review in:
			 * 		user's reviews
			 * 		video's reviews
			 * 		assignment requirements
			 */
		}
	}
}