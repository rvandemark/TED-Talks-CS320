package edu.ycp.cs320.rvandemark.controller;

import edu.ycp.cs320.rvandemark.model.Numbers;

public class NumbersController {
	private Numbers model;
	
	public void setModel (Numbers m) {
		model = m;
	}
	
	public void add() {
		model.setResult(model.getFirst() + model.getSecond() + model.getThird());
	}
	public void multiply() {
		model.setResult(model.getFirst() * model.getSecond() * model.getThird());
	}
}
