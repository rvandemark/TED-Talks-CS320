package edu.ycp.cs320.rvandemark.model;

//this class is mostly just to label the object as "Admin" versus a normal "User"
public class Admin extends User {
	
	public Admin(String e, String u, String pa, String f, String l, int po, String[] d) {
		super(e, u, pa, f, l, po, d);
	}
	
	public void receive(Notification n) {
		User u = n.getFlagger();
		Review r = n.getTarget();
		String text = "User " + u.getUserID() + " (" + u.getFirstName() + " " + u.getLastName() + ") flagged a review.";
				text += "\n\tOriginal review left by user " + r.getAuthor().getUserID() + " (" + r.getAuthor().getFirstName() + " " + r.getAuthor().getLastName() + ")";
				text += "\n\tLeft under video \"" + r.getParent().getName() + "\"";
		System.out.println(text);
	}
}