package com.lpc.mode;

public class XClassModel {
	
	private String id;
	private String name;
	private int number;
	private String collegeId;
	private String departmentId;
	private String majorId;
	private String semester;
	/**
	 * @param id
	 * @param name
	 * @param number
	 * @param collegeId
	 * @param departmentId
	 * @param majorId
	 */
	public XClassModel(String id, String name, int number, String collegeId, String departmentId, String majorId,String semester) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
		this.collegeId = collegeId;
		this.departmentId = departmentId;
		this.majorId = majorId;
		this.semester = semester;
	}

	public XClassModel() {
		// TODO 自动生成的构造函数存根
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getNumber() {
		return number;
	}

	public String getCollegeId() {
		return collegeId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public String getMajorId() {
		return majorId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setCollegeId(String collegeId) {
		this.collegeId = collegeId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

}
