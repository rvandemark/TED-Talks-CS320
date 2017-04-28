package edu.ycp.cs320.rvandemark.model;

import java.util.Calendar;

public abstract class Assignment {
	
	private YCPClass ycpClass;
	private String name;
	private Calendar assignDate;
	private Calendar dueDate;
	private boolean completed;
	
	public Assignment(YCPClass y, String n, Integer am, Integer ad, Integer ay, Integer dm, Integer dd, Integer dy) {
		ycpClass = y;
		name = n;
		try {
			setAssignDate(getDateMillis(am, ad, ay));
			setDueDate(getDateMillis(dm, dd, dy));
		} catch (Exception e) {
			assignDate = null;
			dueDate = null;
		}
	}
	public Assignment(YCPClass y, String n, Integer dm, Integer dd, Integer dy) {
		ycpClass = y;
		name = n;
		try {
			setAssignDate(getTodayMillis());
			setDueDate(getDateMillis(dm, dd, dy));
		} catch (Exception e) {
			assignDate = null;
			dueDate = null;
		}
	}
	
	public String getName() {
		return name;
	}
	public YCPClass getYCPClass() {
		return ycpClass;
	}
	public Calendar getAssignDate() {
		return assignDate;
	}
	public Calendar getDueDate() {
		return dueDate;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setName(String n) {
		name = n;
	}
	public void setAssignDate(long millis) throws Exception {
		if (millis == -1) {
			assignDate = null;
			throw new Exception();
		} else {
			assignDate = Calendar.getInstance();
			assignDate.setTimeInMillis(millis);
		}
	}
	public void setDueDate(long millis) throws Exception {
		if (millis == -1) {
			dueDate = null;
			throw new Exception();
		} else {
			dueDate = Calendar.getInstance();
			dueDate.setTimeInMillis(millis);
		}
	}
	public void setCompleted(boolean c) {
		completed = c;
	}
	
	private long getTodayMillis() {
		try {
			return Calendar.getInstance().getTimeInMillis();
		} catch (Exception e) {
			return -1;
		}
	}
	public static long getDateMillis(int month, int day, int year) {
		try {
			Calendar date = Calendar.getInstance();
			date.set(Calendar.MONTH, month-1);
			date.set(Calendar.DAY_OF_MONTH, day);
			date.set(Calendar.YEAR, year);
			return date.getTimeInMillis();
		} catch (Exception e) {
			return -1;
		}
	}
	
	public String getSummary() {
		return name;
	}
}