package com.lpc.mode;

public class TeacherModel {
	
	private String id;
	private String college;
	private String department;
	private String level;
	private String education;
	private String year;

	public TeacherModel() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	

	/**
	 * @param id
	 * @param college
	 * @param department
	 * @param level
	 * @param education
	 * @param year
	 */
	public TeacherModel(String id, String college, String department, String level, String education, String year) {
		super();
		this.id = id;
		this.college = college;
		this.department = department;
		this.level = level;
		this.education = education;
		this.year = year;
	}



	public String getId() {
		return id;
	}

	public String getCollege() {
		return college;
	}

	public String getDepartment() {
		return department;
	}

	public String getLevel() {
		return level;
	}

	public String getEducation() {
		return education;
	}

	public String getYear() {
		return year;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
