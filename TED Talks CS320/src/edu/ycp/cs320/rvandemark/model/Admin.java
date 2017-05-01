package edu.ycp.cs320.rvandemark.model;

//this class is mostly just to label the object as "Admin" versus a normal "User"
public class Admin extends User {
	
	public Admin(Integer i, String e, String u, String pa, String f, String l, int po, String[] d) {
		super(i, e, u, pa, f, l, po, d);
	}
	
	public String receive(Notification n) {
		User u = n.getFlagger();
		Review r = n.getTarget();
		String text = "User " + u.getScreenName() + " (" + u.getFirstName() + " " + u.getLastName() + ") flagged a review.";
				text += "\n\tOriginal review left by user " + r.getAuthor().getScreenName() + " (" + r.getAuthor().getFirstName() + " " + r.getAuthor().getLastName() + ")";
				text += "\n\tLeft under video \"" + r.getParent().getName() + "\"";
		return text;
	}
}