package edu.ycp.cs320.rvandemark.model;

import java.io.IOException;
import java.sql.SQLException;

public class SpecificVideoAssignment extends Assignment {
	
	private Video video;
	
	public SpecificVideoAssignment(YCPClass y, String n, Integer am, Integer ad, Integer ay, Integer dm, Integer dd, Integer dy, Integer v) {
		super(y, n, am, ad, ay, dm, dd, dy);
		try {
			video = Engine.getDB().getVideoById(v);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	public SpecificVideoAssignment(YCPClass y, String n, Integer dm, Integer dd, Integer dy, String videoName) {
		super(y, n, dm, dd, dy);
		try {
			video = Engine.getDB().getVideoById(Engine.getDB().getVideoIdByName(videoName));
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video v) {
		video = v;
	}
	
	@Override
	public String getSummary() {
		return super.getSummary() + ": watch and leave a review on " + video.toString();
	}
}