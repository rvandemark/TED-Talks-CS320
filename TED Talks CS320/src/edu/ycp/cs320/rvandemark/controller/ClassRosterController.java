package edu.ycp.cs320.rvandemark.controller;

import java.io.IOException;
import java.util.ArrayList;

import edu.ycp.cs320.rvandemark.model.Engine;
import edu.ycp.cs320.rvandemark.model.User;


public class ClassRosterController {
	
	public ArrayList<User> getAllUsers() throws IOException{
		return new ArrayList<User> (Engine.getDB().getAllUsers());
	}
}