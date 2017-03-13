package edu.ycp.cs320.rvandemark.model;

public class User {
	
	private String email;
	private String userID;
	private String password;
	
	private String firstName;
	private String lastName;
	private int points;
	private String[] disciplines;
	
	public User(String e, String u, String pa, String f, String l, int po, String[] d) {
		email = e;
		userID = u;
		password = pa;
		firstName = f;
		lastName = l;
		points = po;
		disciplines = d;
	}
	
	public void flagReview(Review review) {
		
	}
	public void editReview(Review review) {
		
	}
	public void deleteReview(Review review) {
		
	}
}