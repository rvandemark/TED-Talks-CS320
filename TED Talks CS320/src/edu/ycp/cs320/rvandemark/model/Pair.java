package edu.ycp.cs320.rvandemark.model;

public class Pair {
	
	private String labelLeft, labelRight;
	private Integer left, right;
	
	public Pair (String ll, String lr, Integer l, Integer r) {
		labelLeft = ll;
		labelRight = lr;
		left = l;
		right = r;
	}
	
	public String getLabelLeft() {
		return labelLeft;
	}
	public String getLabelRight() {
		return labelRight;
	}
	public Integer getLeft() {
		return left;
	}
	public Integer getRight() {
		return right;
	}
	
	@Override
	public String toString() {
		return "[" + labelLeft + ": " + left + ", " + labelRight + ": " + right + "]";
	}
}