package edu.ycp.cs320.rvandemark.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Video {
	
	private int id;
	private String url;
	private String embedUrl;
	private String name;
	private String speaker;
	private String thumbnailUrl;
	private String disciplineLine;
	private ArrayList<String> disciplines;
	private int totalRating;
	private int numRatings;
	private double rating;
	private Calendar uploadDate;
	private ArrayList<Review> reviews;
	
	private final static int MAX_LENGTH = 50;

	public Video (int i, String u, String e, String n, String s, String t, int tr, int nr, double r, int m, int d, int y, String dl) throws IOException {
		id = i;
		url = u;
		embedUrl = e;
		name = n;
		speaker = s;
		thumbnailUrl = t;
		disciplineLine = dl;
		disciplines = parseDisciplineLine(disciplineLine);
		totalRating = tr;
		numRatings = nr;
		rating = r;
		uploadDate = new GregorianCalendar(y, m, d);
		reviews = collectReviews();
	}
	
	public Video (String u, int tr, int nr) throws IOException {
		url = u;
		embedUrl = extractEmbedUrl(url);

		String sourceCode = extractUrlSource();
		name = extractName(sourceCode);
		speaker = extractSpeaker(sourceCode);
		thumbnailUrl = extractThumbnailSource(sourceCode);
		disciplines = extractDisciplines(sourceCode);
		disciplineLine = buildDisciplineLine(disciplines);
		totalRating = tr;
		numRatings = nr;
		rating = numRatings == 0 ? 0 : (double)totalRating / numRatings;
		uploadDate = Calendar.getInstance();
		reviews = null;
	}
	
	public Video (String u) throws IOException {
		this(u, 0, 0);
	}
	
	public static int getMaxLength() {
		return MAX_LENGTH;
	}
	
	public int getId() {
		return id;
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
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	public String getDisciplineLine() {
		return disciplineLine;
	}
	public ArrayList<String> getDisciplines() {
		return disciplines;
	}
	public int getTotalRating() {
		return totalRating;
	}
	public int getNumRatings() {
		return numRatings;
	}
	public double getRating() {
		return rating;
	}
	public int getUploadMonth() {
		return uploadDate.get(Calendar.MONTH);
	}
	public int getUploadDay() {
		return uploadDate.get(Calendar.DAY_OF_MONTH);
	}
	public int getUploadYear() {
		return uploadDate.get(Calendar.YEAR);
	}
	public ArrayList<Review> getReviews() {
		return reviews;
	}
	
	public void setDisciplines(ArrayList<String> d) {
		disciplines = d;
	}
	public void setReviews(ArrayList<Review> r) {
		reviews = r;
	}
	
	public void addReview(Review r) {
		reviews.add(r);
	}
	public void deleteReview(Review r) {
		reviews.remove(r);
	}
	
	public String getRatingClip() {
		return "clip:rect(0px," + (int)(375*rating/5) + "px,75px,0px)";
	}
	public String getConcatenatedName() {
		if (name.length() >= MAX_LENGTH-3) {
			return name.substring(0, MAX_LENGTH-4) + "...";
		} else {
			return name;
		}
	}
	public String getFancyUploadDate() {
		return new SimpleDateFormat("MMM dd, 20yy").format(uploadDate.getTime());
	}

	private String extractEmbedUrl(String url) throws IOException {
		if (url.indexOf("ted.com/") == -1) {
			throw new IOException();
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
		try {
			String urlCopy = url;
			if (urlCopy.indexOf("http://") == -1 && urlCopy.indexOf("https://") == -1) urlCopy = "https://" + urlCopy;
			URL theURL = new URL(urlCopy);
			URLConnection conn = theURL.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line, str = "";
			while ((line = br.readLine()) != null) {
				str += line;
			}
			br.close();

			return str;
		} catch (IOException e) {
			System.err.println("Error extracting URL source for " + url);
			throw e;
		}
	}

	private String extractName(String sourceCode) {
		final String keyStart = "<span class='player-hero__title__content'>", keyEnd = "</span>";
		try {
			String src = sourceCode.substring(sourceCode.indexOf(keyStart));
			return src.substring(src.indexOf(keyStart) + keyStart.length(), src.indexOf(keyEnd));
		} catch (StringIndexOutOfBoundsException e) {
			try { return extractName(extractUrlSource()); }
			catch (IOException e1) { System.err.println("Error extracting name for " + url); e1.printStackTrace(); return null; }
		}
	}

	private String extractSpeaker(String sourceCode) {
		final String keyStart = "<span class='player-hero__speaker__content'>", keyEnd = ":</span>";
		try {
			String src = sourceCode.substring(sourceCode.indexOf(keyStart));
			return src.substring(src.indexOf(keyStart) + keyStart.length(), src.indexOf(keyEnd));
		} catch (StringIndexOutOfBoundsException e) {
			try { return extractSpeaker(extractUrlSource()); }
			catch (IOException e1) { System.err.println("Error extracting speaker for " + url); e1.printStackTrace(); return null; }
		}
	}
	
	private String extractThumbnailSource(String sourceCode) {
		final String keyStart = "og:image\" content=\"", keyEnd = "?c=1050%2C550&amp;play=&amp;w=1050";
		try {
			String src = sourceCode.substring(sourceCode.indexOf(keyStart));
			return src.substring(src.indexOf(keyStart) + keyStart.length(), src.indexOf(keyEnd));
		} catch (StringIndexOutOfBoundsException e) {
			try { return extractThumbnailSource(extractUrlSource()); }
			catch (IOException e1) { System.err.println("Error extracting thumbnail source for " + url); e1.printStackTrace(); return null; }
		}
	}

	private ArrayList<String> extractDisciplines(String sourceCode) {
		ArrayList<String> theArrList = new ArrayList<String> ();
		final String keyStart = "<li class='talk-topics__item'>", keyEnd = "</a>";
		String src = sourceCode, obj;

		if (src.indexOf(keyStart) == -1) {
			return new ArrayList<String> ();
		} else {
			src = src.substring(src.indexOf(keyStart));
		}

		while (src.indexOf(keyStart) >= 0) {
			obj = src.substring(src.indexOf(keyStart)+keyStart.length());
			theArrList.add(obj.substring(obj.indexOf(">")+1, obj.indexOf(keyEnd)));
			src = obj.substring(obj.indexOf(keyEnd));
		}

		return theArrList;
	}
	
	private String buildDisciplineLine(ArrayList<String> disciplines) {
		String disciplineLine = "";
		for (int i = 0; i < disciplines.size(); i++) {
			disciplineLine += disciplines.get(i);
			if (i != disciplines.size() - 1) disciplineLine += '|';
		}
		return disciplineLine;
	}
	private ArrayList<String> parseDisciplineLine(String disciplineLine) {
		ArrayList<String> disciplines = new ArrayList<String> ();
		
		if (!disciplineLine.isEmpty()) {
			String disciplineLineCopy = disciplineLine;
			
			while(disciplineLineCopy.indexOf('|') > 0) {
				disciplines.add(disciplineLineCopy.substring(0, disciplineLineCopy.indexOf('|')));
				disciplineLineCopy = disciplineLineCopy.substring(disciplineLineCopy.indexOf('|')+1);
			}
			
			disciplines.add(disciplineLineCopy);
		}
		
		return disciplines;
	}
	private ArrayList<Review> collectReviews() {
		try {
			return Engine.getDB().getAllReviewsForVideo(this);
		} catch (SQLException | IOException e) {
			System.err.println("Error collecting reviews for video " + toString());
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public String toString() {
		return "\"" + name + "\" by " + speaker;
	}
	
	public String detailed() {
		String str = "Video {";
		str += "\n\tid: " + id;
		str += "\n\turl: " + url;
		str += "\n\tembedUrl: " + embedUrl;
		str += "\n\tname: " + name;
		str += "\n\tspeaker: " + speaker;
		str += "\n\tthumbnailUrl: " + thumbnailUrl;
		str += "\n\tdisciplineLine: " + disciplineLine;
		str += "\n\tdisciplines: {";
		if (disciplines != null) for (int i = 0; i < disciplines.size(); i++) str += "\n\t\t" + disciplines.get(i);
		else str += "\n\t\tNULL";
		str += "\n\t}";
		str += "\n\ttotalRatings: " + totalRating;
		str += "\n\tnumRatings: " + numRatings;
		str += "\n\trating: " + rating;
		str += "\n\tuploadDateMonth: " + getUploadMonth();
		str += "\n\tuploadDateDay: " + getUploadDay();
		str += "\n\tuploadDateYear: " + getUploadYear();
		str += "\n\treviews: {";
		if (reviews != null) for (int i = 0; i < reviews.size(); i++) str += "\n\t\t" + reviews.get(i).toString();
		else str += "\n\t\tNULL";
		str += "\n\t}\n}";
		return str;
	}
}