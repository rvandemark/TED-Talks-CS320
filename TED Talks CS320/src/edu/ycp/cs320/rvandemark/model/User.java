package edu.ycp.cs320.rvandemark.model;

import java.io.File;

public class User {
	
	private String email;
	private String screenName;
	private String password;
	
	private String lastName;
	private String firstName;
	private int points;
	private String[] disciplines;
	
	public User(String e, String u, String pa, String l, String f, int po, String[] d) {
		email = e;
		screenName = u;
		password = pa;
		lastName = l;
		firstName = f;
		points = po;
		disciplines = d;
	}
	public User(String e, String pa, String l, String f) {
		email = e;
		password = pa;
		lastName = l;
		firstName = f;
		points = 0;
		disciplines = new String[0];
	}
	
	public String getEmail() {
		return email;
	}
	public String getScreenName() {
		return screenName;
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
	public String getIconLocation() {
		return "resources/" + email.substring(0, email.indexOf("@")) + "_icon.png";
	}
	public boolean getIconStatus() {
		return new File("war/" + getIconLocation()).exists();
	}
	
	@Override
	public String toString() {
		return lastName + ", " + firstName + " {" + email + " : " + screenName + "}";
	}
}