package edu.ycp.cs320.rvandemark.controller;

import java.io.IOException;
import java.sql.SQLException;

import edu.ycp.cs320.rvandemark.model.Engine;
import edu.ycp.cs320.rvandemark.model.LoginPageModel;

public class LoginPageController {
	
	private LoginPageModel model;
	
	public LoginPageController(LoginPageModel m) {
		model = m;
	}
	
	public void calcCredentials() {
		if (model.getEmail().isEmpty() || model.getPassword().isEmpty()) {
			model.setErrorMessage("Please fill out the email and password fields.");
		} else {
			try {
				model.setValid(Engine.getDB().validCredentials(model.getEmail(), model.getPassword()));
				if (!model.isValid()) {
					model.setErrorMessage("Invalid email or password. Please try again.");
				}
			} catch (SQLException | IOException e) {
				model.setErrorMessage("SYSTEM ERROR.");
			}
		}
	}
}