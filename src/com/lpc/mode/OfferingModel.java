package com.lpc.mode;

public class OfferingModel {
	
	private String id;
	private String courseID;
	private String teacherId;
	private String begin;
	private String SY;
	private String ST;
	private String semesterId;
	private int Num;

	/**
	 * @param id
	 * @param courseID
	 * @param teacherId
	 * @param begin
	 * @param sY
	 * @param sT
	 * @param semesterId
	 * @param num
	 */
	public OfferingModel(String id, String courseID, String teacherId, String begin, String sY, String sT,
			String semesterId, int num) {
		super();
		this.id = id;
		this.courseID = courseID;
		this.teacherId = teacherId;
		this.begin = begin;
		SY = sY;
		ST = sT;
		this.semesterId = semesterId;
		Num = num;
	}

	public OfferingModel() {
		// TODO 自动生成的构造函数存根
	}

	public String getId() {
		return id;
	}

	public String getCourseID() {
		return courseID;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public String getBegin() {
		return begin;
	}

	public String getSY() {
		return SY;
	}

	public String getST() {
		return ST;
	}

	public String getSemesterId() {
		return semesterId;
	}

	public int getNum() {
		return Num;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public void setSY(String sY) {
		SY = sY;
	}

	public void setST(String sT) {
		ST = sT;
	}

	public void setSemesterId(String semesterId) {
		this.semesterId = semesterId;
	}

	public void setNum(int num) {
		Num = num;
	}

}
