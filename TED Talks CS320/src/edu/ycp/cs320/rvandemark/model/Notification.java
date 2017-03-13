package edu.ycp.cs320.rvandemark.model;

public class Notification {
	
	private User flagger;
	private Review target;
	
	public Notification(User f, Review t) {
		flagger = f;
		target = t;
	}
	
	public boolean notifyAdmin() {
		return true;
	}
	public boolean notifyReviewer() {
		return true;
	}
}