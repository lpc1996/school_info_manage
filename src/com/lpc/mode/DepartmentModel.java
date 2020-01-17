package com.lpc.mode;

public class DepartmentModel {
	
	private String id;
	private String name;
	private String collegeId;

	/**
	 * @param id
	 * @param name
	 * @param collegeId
	 */
	public DepartmentModel(String id, String name, String collegeId) {
		super();
		this.id = id;
		this.name = name;
		this.collegeId = collegeId;
	}

	public DepartmentModel() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCollegeId() {
		return collegeId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCollegeId(String collegeId) {
		this.collegeId = collegeId;
	}

}
