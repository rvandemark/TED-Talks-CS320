package edu.ycp.cs320.rvandemark.controller;

import edu.ycp.cs320.rvandemark.model.Assignment;
import edu.ycp.cs320.rvandemark.model.SpecificVideoAssignment;
import edu.ycp.cs320.rvandemark.model.VideoByDisciplineAssignment;

public class AssignmentController {
	private Assignment model;
	//Hello Nick!
	//Hello Kyle!
	public AssignmentController(Assignment m) {
		model = m;
	}
	
	public void setModel(Assignment m) {
		model = m;
	}
	
	public Integer getAssignmentValidity() {
		if (model == null) return 1;
		if (model.getName() == null) return 2;
		if (model.getDueDate() == null) return 3;
		if (model.getDueDate().before(model.getAssignDate())) return 4;
		
		if (model instanceof SpecificVideoAssignment) {
			SpecificVideoAssignment svmodel = (SpecificVideoAssignment)model;
			if (svmodel.getVideo() == null) return 5;
		} else {
			VideoByDisciplineAssignment vbdmodel = (VideoByDisciplineAssignment)model;
			if (vbdmodel.getDiscipline() == null) return 6;
			if (vbdmodel.getCount() == null || vbdmodel.getCount() < 1) return 7;
		}
		
		return 0;
	}
	public String getErrorMessage(int validity) {
		switch (validity) {
			case 1: return "Be sure to fill in all input areas!";
			case 2: return "Enter a valid name!";
			case 3: return "Enter a valid due date!";
			case 4: return "Due date is in the past!";
			case 5: return "No video by that name exists!";
			case 6: return "Select a required discipline!";
			case 7: return "Invalid video count!";
			default: return "";
		}
	}
}