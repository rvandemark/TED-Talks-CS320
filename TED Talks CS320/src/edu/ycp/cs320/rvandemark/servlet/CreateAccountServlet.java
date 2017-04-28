package edu.ycp.cs320.rvandemark.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("In the Create Account Page servlet");
		
		req.getRequestDispatcher("/_view/createAccount.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp){
		String password = null;
		String passwordRepeat = null;
		
		password = req.getParameter("password");
		passwordRepeat=req.getParameter("passwordRepeat");
		
		if(password.equals(passwordRepeat)){
			
		}
		else{
			
		}
	}
}