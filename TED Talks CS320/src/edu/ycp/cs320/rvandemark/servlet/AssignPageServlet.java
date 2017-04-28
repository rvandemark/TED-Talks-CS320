package edu.ycp.cs320.rvandemark.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.rvandemark.controller.AssignmentController;
import edu.ycp.cs320.rvandemark.model.Assignment;
import edu.ycp.cs320.rvandemark.model.SpecificVideoAssignment;
import edu.ycp.cs320.rvandemark.model.VideoByDisciplineAssignment;

public class AssignPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final static String[] disciplines = {"Civil Engineering", "Creativity", "TEDMED", "Astronomy"};
	private final static ArrayList<Assignment> assignments = new ArrayList<Assignment>();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("disciplines", disciplines);
		req.setAttribute("assignments", assignments);
		req.getRequestDispatcher("/_view/assignPage.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String assignName = req.getParameter("assignName");
		String date = req.getParameter("dueDate");
		
		Integer month = getMonth(date);
		Integer day = getDay(date);
		Integer year = getYear(date);
		
		String videoName = null;
		String videoDiscipline = null;
		Integer videoCount = null;
		
		Assignment model = null;
		String btn1 = "", btn2 = "";
		
		if (req.getParameter("specificVideo") != null) {
			btn1 = "disabled";
		} else if (req.getParameter("disciplineVideos") != null) {
			btn2 = "disabled";
		} else if (req.getParameter("specificVideoIsHidden") != null
					&& req.getParameter("specificVideoIsHidden").equals("disabled")) {
			btn1 = "disabled";
			videoName = req.getParameter("videoName");
			model = new SpecificVideoAssignment(null, assignName, month, day, year, videoName);
		} else {
			btn2 = "disabled";
			videoDiscipline = req.getParameter("videoDiscipline");
			
			String videoCountStr = req.getParameter("videoCount");
			if (videoCountStr.equals("")) videoCountStr = "0";
			
			videoCount = Integer.parseInt(videoCountStr);
			model = new VideoByDisciplineAssignment(null, assignName, month, day, year, videoDiscipline, videoCount);
		}
		
		AssignmentController controller = new AssignmentController(model);
		Integer validity = controller.getAssignmentValidity();
		
		if (validity == 0) assignments.add(model);
		
		req.setAttribute("disciplines", disciplines);
		req.setAttribute("assignments", assignments);
		req.setAttribute("error", controller.getErrorMessage(validity));
		req.setAttribute("inputName", assignName);
		req.setAttribute("inputDate", date);
		req.setAttribute("videoName", videoName);
		req.setAttribute("videoDiscipline", videoDiscipline);
		req.setAttribute("videoCount", videoCount);
		req.setAttribute("specificVideoDisabled", btn1);
		req.setAttribute("disciplineVideosDisabled", btn2);
		req.getRequestDispatcher("/_view/assignPage.jsp").forward(req, resp);
	}
	
	private Integer getMonth(String date) {
		try {
			return Integer.parseInt(date.substring(5, 7));
		} catch (Exception e) {
			return null;
		}
	}
	private Integer getDay(String date) {
		try {
			return Integer.parseInt(date.substring(8));
		} catch (Exception e) {
			return null;
		}
	}
	private Integer getYear(String date) {
		try {
			return Integer.parseInt(date.substring(0, 4));
		} catch (Exception e) {
			return null;
		}
	}
}