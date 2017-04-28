package edu.ycp.cs320.rvandemark.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import edu.ycp.cs320.rvandemark.controller.ClassRosterController;
import edu.ycp.cs320.rvandemark.model.User;

public class ClassRosterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ClassRosterController controller = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//author DJHake
		User user = (User)req.getSession().getAttribute("user");
		if (user == null) {
			System.out.println("   User: <" + user + "> not logged in or session timed out");

			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/loginPage");
			return;
		}

		// now we have the user's User object,
		// proceed to handle request...

		//System.out.println("   User: <" + user + "> logged in");
		
		ArrayList<User> users = null;
		String errorMessage = null;

		controller = new ClassRosterController();
		// get list of authors returned from query
		users = controller.getAllUsers();
		
		// any authors found?
		if (users == null) {
			errorMessage = "No students were found in this Section";
		}
		System.out.println(users);
		// Add result objects as request attributes
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("users", users);

		req.getRequestDispatcher("/_view/classRoster.jsp").forward(req, resp);

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/_view/classRoster.jsp").forward(req, resp);
	}
}