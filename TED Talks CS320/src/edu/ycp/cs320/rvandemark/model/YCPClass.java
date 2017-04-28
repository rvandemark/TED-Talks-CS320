package edu.ycp.cs320.rvandemark.model;

import java.util.ArrayList;

public class YCPClass {
	
	private String name;
	private String prefix;
	private int section;
	private Admin admin;
	private ArrayList<Assignment> assignments;
	private ArrayList<User> students;
	
	public YCPClass(Admin a, String n, String p, int s) {
		name = n;
		prefix = p;
		section = s;
		admin = a;
		assignments = new ArrayList<Assignment> ();
		students = new ArrayList<User> ();
	}
	public YCPClass(Admin a, String n, String p, int s, ArrayList<Assignment> as, ArrayList<User> st) {
		name = n;
		prefix = p;
		section = s;
		admin = a;
		assignments = as;
		students = st;
	}
	
	public String getName() {
		return name;
	}
	public String getPrefix() {
		return prefix;
	}
	public int getSection() {
		return section;
	}
	public Admin getAdmin() {
		return admin;
	}
	public ArrayList<Assignment> getAssignments() {
		return assignments;
	}
	public ArrayList<User> getStudents() {
		return students;
	}
	
	public void insertAssignment(Assignment a) {
		assignments.add(a);
	}
	public void insertStudent(User u) {
		students.add(u);
	}
	
	@Override
	public String toString() {
		return name + " (" + prefix + "." + section + "): " + admin.getFirstName() + " " + admin.getLastName();
	}
}