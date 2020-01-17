package com.lpc.mode;

public class StudentModel {
	
	private String id;
	private String year;
	private String college;
	private String department;
	private String major;
	private String grade;
	private String classId;
	private String culture_level;
	private String type;
	private String education;

	public StudentModel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param year
	 * @param college
	 * @param department
	 * @param major
	 * @param grade
	 * @param classId
	 * @param culture_level
	 * @param type
	 * @param education
	 */
	public StudentModel(String id, String year, String college, String department, String major, String grade,
			String classId, String culture_level, String type, String education) {
		super();
		this.id = id;
		this.year = year;
		this.college = college;
		this.department = department;
		this.major = major;
		this.grade = grade;
		this.classId = classId;
		this.culture_level = culture_level;
		this.type = type;
		this.education = education;
	}

	public String getId() {
		return id;
	}

	public String getYear() {
		return year;
	}

	public String getCollege() {
		return college;
	}

	public String getDepartment() {
		return department;
	}

	public String getMajor() {
		return major;
	}

	public String getGrade() {
		return grade;
	}

	public String getClassId() {
		return classId;
	}

	public String getCulture_level() {
		return culture_level;
	}

	public String getType() {
		return type;
	}

	public String getEducation() {
		return education;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public void setCulture_level(String culture_level) {
		this.culture_level = culture_level;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setEducation(String education) {
		this.education = education;
	}

}
