package edu.ycp.cs320.rvandemark.model;

public class Engine {
	
	public static User THE_USER;
	public static Admin[] ALL_ADMINS = loadAdmins();
			
	public static Admin[] loadAdmins() {
		return new Admin[]{};
	}
	
	public static boolean videoIsValid(String url) {
//		Video v;
//		try { v = new Video(url, 0); }
//		catch (IOException e) {
//			return false;
//		}
		
//		String formattedUrl = v.getUrl();
		//search database for formattedUrl. If it exists somewhere, then the video does too.
		//otherwise, the url is valid ---> return (url does not exist in database);
		
		return true;
	}
	public static void createVideo(String url) {
		if (videoIsValid(url)) {
//			try {
//				Video v = new Video(url, 0);
//				//write v to database
//			} catch (IOException e) {
//				//an exception could never be thrown because the same check occurs in videoIsValid
//				e.printStackTrace();
//			}
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
	public static void flagReview(User flagger, Review review) {
		Engine.createNotification(flagger, review);
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
	
	public static void createNotification(User flagger, Review target) {
		Notification n = new Notification(flagger, target);
		for (int i = 0; i < ALL_ADMINS.length; i++) ALL_ADMINS[i].receive(n);
	}
}