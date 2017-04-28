package edu.ycp.cs320.rvandemark.controller;

import edu.ycp.cs320.rvandemark.model.Engine;

public class UploadPageController {
	
	//Ezzypoo Wuz Here
	//Spankbank n chill

	public boolean insertVideo(String url, String e_url, String name, String speaker, String thumb, int tr, int nr, double rating, int uM, int uD, int uY, String dLine) {
		//System.out.println(url + " made it here too!");          Test line
		
		//Tests the input of the URL and information
		Integer new_video = Engine.getDB().insertVideo(url, e_url, name, speaker, thumb, tr, nr, rating, uM, uD, uY, dLine);
		
		//if the video_id returns zero or null, then the insertion has failed
		if (new_video > 0) {
			System.out.println("New URL (ID: " + new_video + ") successfully added to Videos table: <" + url + ">");
			return true;
		}
		else{
			return false;
		}
	}
}
