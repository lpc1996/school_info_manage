package com.lpc.mode;

public class MajorModel {
	
	private String id;
	private String name;
	private String collegeId;
	private String departmentId;

	public MajorModel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param name
	 * @param collegeId
	 * @param departmentId
	 */
	public MajorModel(String id, String name, String collegeId, String departmentId) {
		super();
		this.id = id;
		this.name = name;
		this.collegeId = collegeId;
		this.departmentId = departmentId;
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

	public String getDepartmentId() {
		return departmentId;
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

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

}
