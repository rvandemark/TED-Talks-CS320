package edu.ycp.cs320.rvandemark.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Video {

	private String url;
	private String embedUrl;
	private String name;
	private String speaker;
	private String[] disciplines;
	private double rating;
	
	public Video (String u, String e, String n, String s, String[] d, double r) {
		url = u;
		embedUrl = e;
		name = n;
		speaker = s;
		disciplines = d;
		rating = r;
	}
	public Video (String u, double r) throws IOException {
		url = u;
		embedUrl = format(url);
		
		String sourceCode = extractUrlSource();
		name = extractName(sourceCode);
		speaker = extractSpeaker(sourceCode);
		disciplines = extractDisciplines(sourceCode);
		rating = r;
	}

	public String getUrl() {
		return url;
	}
	public String getEmbedUrl() {
		return embedUrl;
	}
	public String getName() {
		return name;
	}
	public String getSpeaker() {
		return speaker;
	}
	public String[] getDisciplines() {
		return disciplines;
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

	private String extractUrlSource() throws IOException {
		URL theURL = new URL(url);
		URLConnection conn = theURL.openConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		String line, str = "";
		while ((line = br.readLine()) != null) {
			str += line;
		}
		br.close();

		return str;
	}
	
	private String extractName(String sourceCode) {
		final String keyStart = "<span class='player-hero__title__content'>", keyEnd = "</span>";
		String src = sourceCode.substring(sourceCode.indexOf(keyStart));
		return src.substring(src.indexOf(keyStart) + keyStart.length(), src.indexOf(keyEnd));
	}
	
	private String extractSpeaker(String sourceCode) {
		final String keyStart = "<span class='player-hero__speaker__content'>", keyEnd = ":</span>";
		String src = sourceCode.substring(sourceCode.indexOf(keyStart));
		return src.substring(src.indexOf(keyStart) + keyStart.length(), src.indexOf(keyEnd));
	}
	
	private String[] extractDisciplines(String sourceCode) {
		ArrayList<String> theDisciplinesArrList = new ArrayList<String> ();
		final String keyStart = "<li class='talk-topics__item'>", keyEnd = "</a>";
		String src = sourceCode, obj;
		
		if (src.indexOf(keyStart) == 0) {
			return new String[]{};
		} else {
			src = src.substring(src.indexOf(keyStart));
		}
		
		while (src.indexOf(keyStart) >= 0) {
			obj = src.substring(src.indexOf(keyStart)+keyStart.length());
			theDisciplinesArrList.add(obj.substring(obj.indexOf(">")+1, obj.indexOf(keyEnd)));
			src = obj.substring(obj.indexOf(keyEnd));
		}
		
		String[] theDisciplinesArr = new String[theDisciplinesArrList.size()];
		for (int i = 0; i < theDisciplinesArr.length; i++) theDisciplinesArr[i] = theDisciplinesArrList.get(i);
		
		return theDisciplinesArr;
	}
}