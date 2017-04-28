package edu.ycp.cs320.rvandemark.model;

public class LoginPageModel {
	
	private String email, password, errorMessage;
	private Boolean valid;
	
	public LoginPageModel(String u, String p) {
		email = u;
		password = p;
		errorMessage = null;
		valid = false;
	}
	
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public Boolean isValid() {
		return valid;
	}
	
	public void setErrorMessage(String e) {
		errorMessage = e;
	}
	public void setValid(Boolean b) {
		valid = b;
	}
}