package edu.ycp.cs320.rvandemark.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.rvandemark.model.Engine;
import edu.ycp.cs320.rvandemark.model.SearchPageModel;
import edu.ycp.cs320.rvandemark.model.Video;

public class SearchPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (req.getSession().getAttribute("searchDiscipline") != null) {
			SearchPageModel model = new SearchPageModel();
			model.setSearchTerm((String)req.getSession().getAttribute("searchDiscipline"));
			
			try {
				ArrayList<Video> results = Engine.getDB().getAllVideosFilteredBy(model.getSearchTerm(), "discipline", null, 0, 20);
				req.getSession().setAttribute("searchResults", results);
				model.setResults(results);
			} catch (SQLException | NullPointerException e) {
				e.printStackTrace();
			}
			
			req.setAttribute("model", model);
		}
		
		if (req.getSession().getAttribute("searchSpeaker") != null) {
			SearchPageModel model = new SearchPageModel();
			model.setSearchTerm((String)req.getSession().getAttribute("searchSpeaker"));
			
			try {
				ArrayList<Video> results = Engine.getDB().getAllVideosFilteredBy(model.getSearchTerm(), "speaker", null, 0, 20);
				req.getSession().setAttribute("searchResults", results);
				model.setResults(results);
			} catch (SQLException | NullPointerException e) {
				e.printStackTrace();
			}
			
			req.setAttribute("model", model);
		}
		
		req.getRequestDispatcher("/_view/searchPage.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (req.getSession().getAttribute("searchResults") != null) {
			ArrayList<Video> results = (ArrayList<Video>)req.getSession().getAttribute("searchResults");
			int idx = -1;
			for (int i = 0; i < results.size(); i++) {
				if (req.getParameter("searchNo" + i + ".x") != null) {
					idx = i;
					break;
				}
			}
			
			if (idx != -1) {
				Video video = results.get(idx);
				req.getSession().setAttribute("video", video);
				resp.sendRedirect("/rvandemark/videoDisplay");
				return;
			}
		}
		
		String keyword = null;
		String filter = null, sort = null;
		Integer start = null, finish = null;
		
		if (req.getParameter("keyword") != null) {
			keyword = (String)req.getParameter("keyword");
		}
		if (req.getParameter("filter") != null) {
			filter = (String)req.getParameter("filter");
		}
		if (req.getParameter("sort") != null) {
			sort = (String)req.getParameter("sort");
		}
		
		if (req.getAttribute("start") == null) {
			start = 0;
			finish = 20;
			req.setAttribute("start", start);
			req.setAttribute("finish", finish);
		} else {
			start = (Integer)req.getAttribute("finish");
			finish = start+20;
			req.setAttribute("start", start);
			req.setAttribute("finish", finish);
		}
		
		SearchPageModel model = new SearchPageModel();
		model.setSearchTerm(keyword);
		model.setFilterIdx(filter);
		model.setSortIdx(sort);
		model.setStart(start);
		model.setFinish(finish);
		
		try {
			ArrayList<Video> results = Engine.getDB().getAllVideosFilteredBy(keyword, filter, sort, start, finish);
			req.getSession().setAttribute("searchResults", results);
			model.setResults(results);
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		
		req.setAttribute("model", model);
		
		req.getRequestDispatcher("/_view/searchPage.jsp").forward(req, resp);
	}
}