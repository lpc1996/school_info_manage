package com.lpc.mode;

public class CollegeModel {
	
	private String id;
	private String name;

	/**
	 * @param id
	 * @param name
	 * @param president
	 * @param introduce
	 */
	public CollegeModel(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public CollegeModel() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
}
