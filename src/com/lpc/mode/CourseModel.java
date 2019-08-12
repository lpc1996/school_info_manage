package com.lpc.mode;

public class CourseModel {
	
	private String id;
	private String name;
	private String collegeId;
	private String departmentId;
	private String type;
	private double credit;

	/**
	 * @param id
	 * @param name
	 * @param collegeId
	 * @param departmentId
	 * @param type
	 * @param credit
	 */
	public CourseModel(String id, String name, String collegeId, String departmentId, String type, double credit) {
		super();
		this.id = id;
		this.name = name;
		this.collegeId = collegeId;
		this.departmentId = departmentId;
		this.type = type;
		this.credit = credit;
	}

	public CourseModel() {
		// TODO 自动生成的构造函数存根
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

	public String getType() {
		return type;
	}

	public double getCredit() {
		return credit;
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

	public void setType(String type) {
		this.type = type;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

}
