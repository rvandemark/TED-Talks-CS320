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
	
	public String getEmail() {
		return email;
	}
	public String getUserID() {
		return userID;
	}
	public String getPassword() {
		return password;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public int getPoints() {
		return points;
	}
	public String[] getDisciplines() {
		return disciplines;
	}
}