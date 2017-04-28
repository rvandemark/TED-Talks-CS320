package edu.ycp.cs320.rvandemark.model;

import java.util.ArrayList;

public class SearchPageModel {
	
	private String searchTerm, filterIdx, sortIdx;
	private Integer start, finish;
	
	private ArrayList<Video> results;
	
	public String getSearchTerm() {
		return searchTerm;
	}
	public String getFilterIdx() {
		return filterIdx;
	}
	public String getSortIdx() {
		return sortIdx;
	}
	public Integer getStart() {
		return start;
	}
	public Integer getFinish() {
		return finish;
	}
	public ArrayList<Video> getResults() {
		return results;
	}
	
	public void setSearchTerm(String s) {
		searchTerm = s;
	}
	public void setFilterIdx(String f) {
		filterIdx = f;
	}
	public void setSortIdx(String s) {
		sortIdx = s;
	}
	public void setStart(Integer s) {
		start = s;
	}
	public void setFinish(Integer f) {
		finish = f;
	}
	public void setResults(ArrayList<Video> r) {
		results = r;
	}
}