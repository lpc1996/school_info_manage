package com.lpc.mode;

public class StudentCourseModel {
	
	private int id;
	private String studentId;
	private String courseId;
	private String SY;
	private String ST;
	private String time;

	public StudentCourseModel() {
		// TODO 自动生成的构造函数存根
	}
	
	/**
	 * @param id
	 * @param studentId
	 * @param courseId
	 * @param sY
	 * @param sT
	 * @param time
	 */
	public StudentCourseModel(int id, String studentId, String courseId, String sY, String sT, String time) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.courseId = courseId;
		SY = sY;
		ST = sT;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public String getStudentId() {
		return studentId;
	}

	public String getCourseId() {
		return courseId;
	}

	public String getSY() {
		return SY;
	}

	public String getST() {
		return ST;
	}

	public String getTime() {
		return time;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public void setSY(String sY) {
		SY = sY;
	}

	public void setST(String sT) {
		ST = sT;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	

}
