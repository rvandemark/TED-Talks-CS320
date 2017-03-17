package edu.ycp.cs320.rvandemark.model;

public class Notification {
	
	private User flagger;
	private Review target;
	
	public Notification(User f, Review t) {
		flagger = f;
		target = t;
	}
	
	public User getFlagger() {
		return flagger;
	}
	public Review getTarget() {
		return target;
	}
}