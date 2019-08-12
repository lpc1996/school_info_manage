package com.lpc.mode;

public class SchoolYearModel {
	
	private String id;
	private String name;
	private String begin;
	private String end;

	/**
	 * @param id
	 * @param name
	 * @param begin
	 * @param end
	 */
	public SchoolYearModel(String id, String name, String begin, String end) {
		super();
		this.id = id;
		this.name = name;
		this.begin = begin;
		this.end = end;
	}

	public SchoolYearModel() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getBegin() {
		return begin;
	}

	public String getEnd() {
		return end;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public void setEnd(String end) {
		this.end = end;
	}

}
