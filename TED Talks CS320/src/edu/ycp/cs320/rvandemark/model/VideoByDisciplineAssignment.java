package edu.ycp.cs320.rvandemark.model;

public class VideoByDisciplineAssignment extends Assignment {
	
	private String discipline;
	private Integer count;
	
	public VideoByDisciplineAssignment(YCPClass y, String n, Integer am, Integer ad, Integer ay, Integer dm, Integer dd, Integer dy, String d, Integer c) {
		super(y, n, am, ad, ay, dm, dd, dy);
		discipline = d;
		count = c;
	}
	public VideoByDisciplineAssignment(YCPClass y, String n, Integer dm, Integer dd, Integer dy, String d, Integer c) {
		super(y, n, dm, dd, dy);
		discipline = d;
		count = c;
	}
	
	public String getDiscipline() {
		return discipline;
	}
	public Integer getCount() {
		return count;
	}
	
	public void setDiscipline(String d) {
		discipline = d;
	}
	public void setCount(Integer c) {
		count = c;
	}

	@Override
	public String getSummary() {
		return super.getSummary() + ": watch " + count + " video" + (count != 1 ? "s" : "") + " of the \"" + discipline + "\" discipline";
	}
}