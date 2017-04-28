package edu.ycp.cs320.rvandemark.model;

import java.util.Calendar;

public class Review {
	
	private final static String[] OFFENSIVE = {"keyword"};
	
	private User author;
	private Video parent;
	private String text;
	private Calendar postDate;
	private int score;
	private int value;
	private boolean mustSee, greatForEngineer, greatForAnyone, interesting, limited, outdated, notRecommended;
	
	public Review(User a, Video p, String t, int pm, int pd, int py, int s, int v, boolean m, boolean eng, boolean any, boolean i, boolean l, boolean o, boolean nr) {
		author = a;
		parent = p;
		text = t;
		postDate = Calendar.getInstance();
		postDate.setTimeInMillis(getDateMillis(pm, pd, py));
		score = s;
		value = v;
		
		mustSee = m;
		greatForEngineer = eng;
		greatForAnyone = any;
		interesting = i;
		limited = l;
		outdated = o;
		notRecommended = nr;
	}
	
	public Review(User a, Video p, String t, int v, boolean m, boolean eng, boolean any, boolean i, boolean l, boolean o, boolean nr) {
		author = a;
		parent = p;
		text = t;
		postDate = Calendar.getInstance();
		score = 0;
		value = v;
		
		mustSee = m;
		greatForEngineer = eng;
		greatForAnyone = any;
		interesting = i;
		limited = l;
		outdated = o;
		notRecommended = nr;
		
		censor();
		
		parent.addReview(this);
	}
	
	public User getAuthor() {
		return author;
	}
	public Video getParent() {
		return parent;
	}
	public String getText() {
		return text;
	}
	public Calendar getPostDate() {
		return postDate;
	}
	public int getScore() {
		return score;
	}
	public int getValue() {
		return value;
	}
	
	public int getPostMonth() {
		return postDate.get(Calendar.MONTH);
	}
	public int getPostDay() {
		return postDate.get(Calendar.DAY_OF_MONTH);
	}
	public int getPostYear() {
		return postDate.get(Calendar.YEAR);
	}
	
	public boolean isMustSee() {
		return mustSee;
	}
	public boolean isGreatForEngineer() {
		return greatForEngineer;
	}
	public boolean isGreatForAnyone() {
		return greatForAnyone;
	}
	public boolean isInteresting() {
		return interesting;
	}
	public boolean isLimited() {
		return limited;
	}
	public boolean isOutdated() {
		return outdated;
	}
	public boolean isNotRecommended() {
		return notRecommended;
	}
	
	public void setText(String t) {
		text = t;
	}
	
	private void censor() {
		String remaining = text, censored = "", next;
		while (remaining.indexOf(' ') >= 0) {
			next = remaining.substring(0, remaining.indexOf(' '));
			
			if (isOffensive(next)) {
				for (int i = 0; i < next.length(); i++) censored += "*";
			} else {
				censored += next;
			}
			
			censored += " ";
			
			if (remaining.length() - next.length() == 1) remaining = "";
			else remaining = remaining.substring(remaining.indexOf(' ')+1);
			
			if (!remaining.equals("") && remaining.indexOf(' ') == -1) remaining += " ";
		}
		
		text = censored.substring(0, censored.length() - 1);
	}
	private boolean isOffensive(String word) {
		word = word.toLowerCase();
		for (int i = 0; i < OFFENSIVE.length; i++) {
			if (word.equals(OFFENSIVE[i])) return true;
		}
		return false;
	}
	
	private long getDateMillis(int month, int day, int year) {
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
	
	public void removeFromParent() {
		parent.deleteReview(this);
	}
	
	@Override
	public String toString() {
		return "Review {\n\t" + author.getScreenName() + "\n\t\"" + parent.getConcatenatedName() + "\"\n\t\"" + text +
				"\"\n\t" + postDate.get(Calendar.MONTH) + "/" + postDate.get(Calendar.DAY_OF_MONTH) + "/" + postDate.get(Calendar.YEAR) +
				"\n}";
	}
}