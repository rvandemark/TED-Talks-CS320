package edu.ycp.cs320.rvandemark.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.rvandemark.model.User;
import edu.ycp.cs320.rvandemark.model.Engine;
public class UserPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		try {
			req.setAttribute("disciplines",Engine.getDB().getAllDisciplines() );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		User user = (User)req.getSession().getAttribute("user");
		if (user == null) {
			System.out.println("   User: <" + user + "> not logged in or session timed out");

			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/loginPage");
			return;
		}
		req.getRequestDispatcher("/_view/userPage.jsp").forward(req, resp);
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		User user = (User)req.getSession().getAttribute("user");
		System.out.println("Post");
		String Discipline = null;
		
		try {
			req.setAttribute("disciplines",Engine.getDB().getAllDisciplines() );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Discipline = req.getParameter("videoDiscipline");
		
		if(Discipline != null){
			System.out.println("hi");
		try {
			Engine.getDB().insertUserDiscipline2( user.getId(), Engine.getDB().getDisciplineId(Discipline));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		try {
			req.setAttribute("user", Engine.getDB().getUserByEmail(user.getEmail()));
		} catch (SQLException e) {
			
			e.printStackTrace();
	}
		for(int i = 0; i< user.getDisciplines().length;i++){
			if (req.getParameter("buttonNo" + i) != null) {
				try {
					Engine.getDB().deleteUserDiscipline(Engine.getDB().getDisciplineId(user.getDisciplines()[i]), user.getId());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		try {
			req.setAttribute("user", Engine.getDB().getUserByEmail(user.getEmail()));
		} catch (SQLException e) {
			
			e.printStackTrace();
	}
		
		
		
		req.getRequestDispatcher("/_view/userPage.jsp").forward(req, resp);
	}
}