package com.lpc.mode;

public class SchoolTremModel {
	
	private String id;
	private String name;
	private String SYId;
	private String begin;
	private String end;

	/**
	 * @param id
	 * @param name
	 * @param sYId
	 * @param begin
	 * @param end
	 */
	public SchoolTremModel(String id, String name, String sYId, String begin, String end) {
		super();
		this.id = id;
		this.name = name;
		SYId = sYId;
		this.begin = begin;
		this.end = end;
	}

	public SchoolTremModel() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSYId() {
		return SYId;
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

	public void setSYId(String sYId) {
		SYId = sYId;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public void setEnd(String end) {
		this.end = end;
	}

}
