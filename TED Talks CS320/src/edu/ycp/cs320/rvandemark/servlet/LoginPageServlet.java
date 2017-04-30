package edu.ycp.cs320.rvandemark.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.rvandemark.controller.LoginPageController;
import edu.ycp.cs320.rvandemark.model.Engine;
import edu.ycp.cs320.rvandemark.model.LoginPageModel;


public class LoginPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Got some of this code from professor Hake
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("In the Login Page servlet");
		req.getRequestDispatcher("/_view/loginPage.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nLoginServlet: doPost");

		String email = null, pw = null;

		// Decode form parameters and dispatch to controller
		email = req.getParameter("email");
		pw    = req.getParameter("password");

		LoginPageModel model = new LoginPageModel(email, pw);
		LoginPageController controller = new LoginPageController(model);
		controller.calcCredentials();

		// Add parameters as request attributes
		req.setAttribute("model", model);

		// if login is valid, start a session
		if (model.isValid()) {
			// store user object in session
			try {
				req.getSession().setAttribute("user", Engine.getDB().getUserByEmail(email));
			} catch (SQLException e) {
				e.printStackTrace();
			}

			// redirect to /index page
			resp.sendRedirect(req.getContextPath() + "/userPage");

			return;
		}

		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/loginPage.jsp").forward(req, resp);
	}
}