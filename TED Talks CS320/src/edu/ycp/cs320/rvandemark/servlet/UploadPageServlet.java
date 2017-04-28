package edu.ycp.cs320.rvandemark.servlet;

// Testing Url
// https://www.ted.com/talks/david_r_williams_how_racism_makes_us_sick

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.rvandemark.controller.UploadPageController;
import edu.ycp.cs320.rvandemark.model.Engine;
import edu.ycp.cs320.rvandemark.model.Video;

public class UploadPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Get material from page
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("In the Upload Page servlet");

		req.getRequestDispatcher("/_view/uploadPage.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nUploadPageServlet: doPost");

		
		String video_url = req.getParameter("video_url");
		String errorMessage = null;
		String successMessage = null;

		// Tests to assure the URL space was not empty
		if (video_url == null || video_url.isEmpty()) {
			errorMessage = "Please enter a valid URL";
		} 
		//The Url is tested for validity and then the embedURL, name, speaker, thumbnail, ratings, dates, and disciplines
		// are gathered and sent to the controller for insertion into the database
		else {
			UploadPageController controller = new UploadPageController();
			if (Engine.videoIsValid(video_url)) {
				Video video = new Video (video_url, 0, 0);
				String e_url = video.getEmbedUrl();
				String name = video.getName();
				String speaker = video.getSpeaker();
				String thumb = video.getThumbnailUrl();
				int tr = 0;
				int nr = 0;
				double rating = 0.0;
				int uploadMonth = video.getUploadMonth();
				int uploadDay = video.getUploadDay();
				int uploadYear = video.getUploadYear();
				String discipline_line = video.getDisciplineLine();
				// If the insertion is successfull (true) then the successmessage in changed to the name
				if(controller.insertVideo(video_url, e_url, name, speaker, thumb, tr, nr, rating, uploadMonth, uploadDay, uploadYear, discipline_line)){
					successMessage = name;
				}
			}
			//if the test fails then the user is moved to the index page (further implementation may vary)
			else {
				System.out.println("URL was not valid...");
				resp.sendRedirect("/rvandemark/index");
				return;
			}
		}

		req.setAttribute("video_url", video_url);
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("successMessage", successMessage);
		

		req.getRequestDispatcher("/_view/uploadPage.jsp").forward(req, resp);
	}
}