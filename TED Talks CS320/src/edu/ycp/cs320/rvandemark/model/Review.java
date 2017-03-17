package edu.ycp.cs320.rvandemark.model;

public class Review {
	
	private final static String[] OFFENSIVE = {
		"fuck", "shit", "bitch", "dick", "cock", "pussy", "cunt", "tit", "nigger", "nigga"
	};
	
	private User author;
	private Video parent;
	private String text;
	private int score;
	private int value;
	
	//load a pre-existing review from the database
	public Review(User a, Video p, String t, int s, int v) {
		author = a;
		parent = p;
		text = t;
		score = s;
		value = v;
	}
	
	//create a new review object to be stored into the database
	public Review(User a, Video p, String t, int v) {
		author = a;
		parent = p;
		text = t;
		score = 0;
		value = v;
		
		censor();
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
	public int getScore() {
		return score;
	}
	public int getValue() {
		return value;
	}
	
	public void setText(String t) {
		text = t;
	}
	
	private void censor() {
		String remaining = text, censored = "", next;
		while (remaining.indexOf(' ') >= 0) {
			next = remaining.substring(0, remaining.indexOf(' '));
			
			if (isOffensive(Substitution.baseWord(next))) {
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
	
	private enum Substitution {
		
		_a('a', '@', '4'),
		_e('e', '3'),
		_i('i', '!', '1'),
		_l('l', '|'),
		_o('o', '0'),
		_s('s', '$', '5'),
		_t('t', '7'),
		_z('z', '2');
		
		private char character;
		private char[] substitutions;
		
		private Substitution(char c, char... s) {
			character = c;
			substitutions = s;
		}
		
		public static String baseWord (String word) {
			String base = "";
			
			Substitution s;
			for (int i = 0; i < word.length(); i++) {
				s = getSub(word.charAt(i));
				if (s != null) {
					base += s.character;
				} else {
					base += word.charAt(i);
				}
			}
			
			return base;
		}
		
		private static Substitution getSub(char s) {
			for (int i = 0; i < values().length; i++) {
				for (int j = 0; j < values()[i].substitutions.length; j++) {
					if (values()[i].substitutions[j] == s) return values()[i];
				}
			}
			return null;
		}
	}
}