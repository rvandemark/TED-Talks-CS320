package edu.ycp.cs320.rvandemark.model;

public class Video {
	
	private String url;
	private String name;
	private String[] disciplines;
	private double rating;
	
	public Video (String u, String n, String[] d, double r) {
		url = format(u);
		name = n;
		disciplines = d;
		rating = r;
	}
	
	public String getUrl() {
		return url;
	}
	public String getName() {
		return name;
	}
	public String getDiscipline(int i) {
		return disciplines[i];
	}
	public double getRating() {
		return rating;
	}
	public String getRatingClip() {
		return "clip:rect(0px," + (int)(375*rating/5) + "px,75px,0px)";
	}
	
	private String format(String url) {
		if (url.indexOf("ted.com/") == -1) {
			System.err.println("MUST BE TED.COM");
			return null;
		}
		
		int protocolIdx = url.indexOf("https://");
		if (protocolIdx == -1) {
			protocolIdx = url.indexOf("http://");
			if (protocolIdx == -1) {
				url = "https://" + url;
			} else {
				url = url.replace("http://", "https://");
			}
		}
		
		if (url.indexOf("www.") >= 0) {
			url = url.substring(0, url.indexOf("www.")) + url.substring(url.indexOf("www.")+4);
		}
		
		return url.substring(0,8) + "embed." + url.substring(8);
	}
}