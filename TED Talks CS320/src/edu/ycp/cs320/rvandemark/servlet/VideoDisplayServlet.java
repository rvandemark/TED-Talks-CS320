package edu.ycp.cs320.rvandemark.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.rvandemark.model.Video;

public class VideoDisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/_view/videoDisplay.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String URL = "http://www.ted.com/talks/caitlin_doughty_a_burial_practice_that_nourishes_the_planet";
		String name = "The pattern behind self-deception";
		String[] disciplines = {"Mechanical Engineering", "Creativity", "Art"};
		double rating = 1.25;
		
		req.setAttribute("video", new Video(URL, name, disciplines, rating));
		
		req.getRequestDispatcher("/_view/videoDisplay.jsp").forward(req, resp);
	}
}
