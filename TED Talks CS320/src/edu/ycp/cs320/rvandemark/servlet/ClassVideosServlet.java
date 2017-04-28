package edu.ycp.cs320.rvandemark.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClassVideosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setAttribute("YCPClasses", new String[]{"CVE100.101: Intro Video", "CVE340.103: Structural Research", "ME250.101: Engineering Ethics"});
		req.getRequestDispatcher("/_view/classVideos.jsp").forward(req, resp);
	}
}