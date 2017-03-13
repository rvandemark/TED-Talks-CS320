package edu.ycp.cs320.rvandemark.model;

public class Numbers {
	private Double first, second, third, result;
	
	public Numbers () {
		first = new Double(0);
		second = new Double(0);
		third = new Double(0);
		result = new Double(0);
	}
	
	public Double getFirst() {
		return first;
	}
	public Double getSecond() {
		return second;
	}
	public Double getThird() {
		return third;
	}
	public Double getResult() {
		return result;
	}
	public void setFirst(Double first) {
		this.first = first;
	}
	public void setSecond(Double second) {
		this.second = second;
	}
	public void setThird(Double third) {
		this.third = third;
	}
	public void setResult(Double result) {
		this.result = result;
	}
}