package edu.ycp.cs320.rvandemark.model;

import java.io.File;

public class User {
	
	private Integer id;
	private String email;
	private String screenName;
	private String password;
	
	private String lastName;
	private String firstName;
	private int points;
	private String[] disciplines;
	
	public User(Integer i, String e, String u, String pa, String l, String f, int po, String[] d) {
		id = i;
		email = e;
		screenName = u;
		password = pa;
		lastName = l;
		firstName = f;
		points = po;
		disciplines = d;
	}
	
	public Integer getId() {
		return id;
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
	
	public void setId(Integer i) {
		id = i;
	}
	
	@Override
	public String toString() {
		return lastName + ", " + firstName + " {" + email + " : " + screenName + "}";
	}
}