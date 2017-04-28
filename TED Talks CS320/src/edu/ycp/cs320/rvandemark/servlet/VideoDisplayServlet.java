package edu.ycp.cs320.rvandemark.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.rvandemark.model.Engine;
import edu.ycp.cs320.rvandemark.model.Video;

public class VideoDisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static ArrayList<Video> videos;
	static {
		try {
			videos = Engine.getDB().getAllVideos();
		} catch (SQLException | IOException e) {
			System.err.println("ERROR: CANNOT GET VIDEOS");
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (req.getSession().getAttribute("video") == null) {
			Video video = random();
			req.getSession().setAttribute("video", video);
		}
		
		req.setAttribute("suggested", videos);
		req.getRequestDispatcher("/_view/videoDisplay.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int suggestedIdx = indexOf(req, "suggestedNo", ".x", videos);
		if (suggestedIdx != -1) {
			Video video = videos.get(suggestedIdx);
			req.getSession().setAttribute("video", video);
		}
		
		Video video = (Video)req.getSession().getAttribute("video");
		
		int disciplineIdx = indexOf(req, "disciplineNo", "", video.getDisciplines());
		if (disciplineIdx != -1) {
			req.getSession().setAttribute("searchDiscipline", video.getDisciplines().get(disciplineIdx));
			resp.sendRedirect("/rvandemark/searchPage");
			return;
		}
		
		if (req.getParameter("speakerNo0") != null) {
			req.getSession().setAttribute("searchSpeaker", video.getSpeaker());
			resp.sendRedirect("/rvandemark/searchPage");
			return;
		}
		
		req.setAttribute("video", req.getSession().getAttribute("video"));
		req.setAttribute("suggested", videos);
		req.getRequestDispatcher("/_view/videoDisplay.jsp").forward(req, resp);
	}
	
	private <E> int indexOf(HttpServletRequest req, String namePrefix, String nameSuffix, ArrayList<E> list) {
		int idx = -1;
		for (int i = 0; i < list.size(); i++) {
			if (req.getParameter(namePrefix + i + nameSuffix) != null) {
				idx = i;
				break;
			}
		}
		return idx;
	}
	
	private static Video random() {
		return videos.get(new Random().nextInt(videos.size()));
	}
}
