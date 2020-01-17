package com.lpc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import com.lpc.iframe.Login;
import com.lpc.mode.BaseInfoModel;
import com.lpc.mode.CollegeModel;
import com.lpc.mode.CourseModel;
import com.lpc.mode.DepartmentModel;
import com.lpc.mode.LoginModel;
import com.lpc.mode.MajorModel;
import com.lpc.mode.OfferingModel;
import com.lpc.mode.SchoolTremModel;
import com.lpc.mode.SchoolYearModel;
import com.lpc.mode.StudentCourseModel;
import com.lpc.mode.StudentModel;
import com.lpc.mode.TeacherModel;
import com.lpc.mode.XClassModel;

public class ConnXupt {
	
	private String Driver = "com.mysql.cj.jdbc.Driver";
	private String URL = "jdbc:mysql://localhost:3306/xupt?useSSL = false&serverTimezone=UTC&charset=UTF8&allowPublicKeyRetrieval=true&useInformationSchema=true";
	private String USER = "root";
	private String PASS = "甁甉甉甆";
	private Connection conn;
	
	public ConnXupt() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName(Driver);
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "加载数据库驱动失败,请检查驱动版本！");
			e.printStackTrace();
		}
		refreshConnection();
	}
	
	private void refreshConnection() {
		try {
			conn = DriverManager.getConnection(URL, USER, new Secret(PASS).set());
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "数据库连接失败，请检查数据库设置！");
		}
	}
	
	public ResultSet excuteQuery(String sql) {
		ResultSet rs = null;
		try {
			if(conn.isClosed())
				refreshConnection();
			rs = conn.createStatement().executeQuery(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			rs = null;
		}
		return rs;
	}
	
	public int excuteUpdate(String sql) {
		int i = 0;
		try {
			if(conn.isClosed())
				refreshConnection();
			i = conn.createStatement().executeUpdate(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			i = 0;
			System.out.println(sql);
		}
		return i;
	}
	
	public void close() {
		try {
			if( !conn.isClosed() ) {
				conn.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public static void main(String [] argv) {
		ConnXupt xupt = new ConnXupt();
		String[] title = xupt.getColumnName("base_info");
		for(int i = 0; i < title.length; i++) {
			System.out.println(title[i]);
		}
		List commentList = xupt.getComment("base_info");
		for(int i = 0; i < commentList.size(); i++) {
			System.out.println(commentList.get(i));
		}
	}
	
	public Object setValue(ResultSet rs,String tableName,String [] column) {
		try {
			if(tableName.equals("student")) {
				StudentModel student = new StudentModel();
				student.setId(rs.getString(column[0]));
				student.setYear(rs.getString(column[1]));
				student.setCollege(rs.getString(column[2]));
				student.setDepartment(rs.getString(column[3]));
				student.setMajor(rs.getString(column[4]));
				student.setGrade(rs.getString(column[5]));
				student.setClassId(rs.getString(column[6]));
				student.setCulture_level(rs.getString(column[7]));
				student.setType(rs.getString(column[8]));
				student.setEducation(rs.getString(column[9]));
				return student;
			}else if(tableName.equals("base_info")) {
				BaseInfoModel baseInfo = new BaseInfoModel();
				baseInfo.setId(rs.getString(column[1]));
				baseInfo.setName(rs.getString(column[2]));
				baseInfo.setFormarName(rs.getString(column[3]));
				baseInfo.setSex(rs.getString(column[4]));
				baseInfo.setAge(rs.getInt(column[5]));
				baseInfo.setNativePlace(rs.getString(column[6]));
				baseInfo.setIDCARDTYPE(rs.getString(column[7]));
				baseInfo.setIDCARDNUM(rs.getString(column[8]));
				baseInfo.setType(rs.getString(column[9]));
				baseInfo.setTel(rs.getString(column[10]));
				return baseInfo;
			}else if(tableName.equals("teacher")) {
				TeacherModel teacher = new TeacherModel();
				teacher.setId(rs.getString(column[0]));
				teacher.setCollege(rs.getString(column[1]));
				teacher.setDepartment(rs.getString(column[2]));
				teacher.setLevel(rs.getString(column[3]));
				teacher.setEducation(rs.getString(column[4]));
				teacher.setYear(rs.getString(column[5]));
				return teacher;
			}else if(tableName.equals("college")) {
				CollegeModel college = new CollegeModel();
				college.setId(rs.getString(column[0]));
				college.setName(rs.getString(column[1]));
				return college;
			}else if(tableName.equals("department")) {
				DepartmentModel department = new DepartmentModel();
				department.setId(rs.getString(column[0]));
				department.setName(rs.getString(column[1]));
				department.setCollegeId(rs.getString(column[2]));
				return department;
			}else if(tableName.equals("major")) {
				MajorModel major = new MajorModel();
				major.setId(rs.getString(column[0]));
				major.setName(rs.getString(column[1]));
				major.setCollegeId(rs.getString(column[2]));
				major.setDepartmentId(rs.getString(column[3]));
				return major;
			}else if(tableName.equals("school_year")) {
				SchoolYearModel school = new SchoolYearModel();
				school.setId(rs.getString(column[0]));
				school.setName(rs.getString(column[1]));
				school.setBegin(rs.getString(column[2]));
				school.setEnd(rs.getString(column[3]));
				return school;
			}else if(tableName.equals("school_trem")) {
				SchoolTremModel ST = new SchoolTremModel();
				ST.setId(rs.getString(column[0]));
				ST.setName(rs.getString(column[1]));
				ST.setSYId(rs.getString(column[2]));
				ST.setBegin(rs.getString(column[3]));
				ST.setEnd(rs.getString(column[4]));
				return ST;
			}else if(tableName.equals("semester")) {
				String[] semester = new String[2];
				semester[0] = rs.getString(column[0]);
				semester[1] = rs.getString(column[1]);
				return semester;
			}else if(tableName.equals("login")) {
				LoginModel loginModel = new LoginModel();
				loginModel.setId(rs.getString(column[0]));
				loginModel.setName(rs.getString(column[1]));
				loginModel.setLimit(Integer.parseInt(rs.getString(column[2])));
				return loginModel;
			}else if(tableName.equals("xclass")) {
				XClassModel xclass = new XClassModel();
				xclass.setId(rs.getString(column[0]));
				xclass.setName(rs.getString(column[1]));
				xclass.setNumber(rs.getInt(column[2]));
				xclass.setCollegeId(rs.getString(column[3]));
				xclass.setDepartmentId(rs.getString(column[4]));
				xclass.setMajorId(rs.getString(column[5]));
				xclass.setSemester(rs.getString(column[6]));
				return xclass;
			}else if(tableName.equals("course")) {
				CourseModel course = new CourseModel();
				course.setId(rs.getString(column[0]));
				course.setName(rs.getString(column[1]));
				course.setCollegeId(rs.getString(column[2]));
				course.setDepartmentId(rs.getString(column[3]));
				course.setType(rs.getString(column[4]));
				course.setCredit(rs.getDouble(column[5]));
				return course;
			}else if(tableName.equals("offering_courses")) {
				OfferingModel off = new OfferingModel();
				off.setId(rs.getString(column[0]));
				off.setCourseID(rs.getString(column[1]));
				off.setTeacherId(rs.getString(column[2]));
				off.setBegin(rs.getDate(column[3])+"");
				off.setSY(rs.getString(column[4]));
				off.setST(rs.getString(column[5]));
				off.setSemesterId(rs.getString(column[6]));
				off.setNum(rs.getInt(column[7]));
				return off;
			}else if(tableName.equals("student_course")) {
				StudentCourseModel SCM = new StudentCourseModel();
				SCM.setId(rs.getInt(column[0]));
				SCM.setStudentId(rs.getString(column[1]));
				SCM.setCourseId(rs.getString(column[2]));
				SCM.setSY(rs.getString(column[3]));
				SCM.setST(rs.getString(column[4]));
				SCM.setTime(rs.getString(column[5]));
				return SCM;
			}else if(tableName.equals("login")){
				LoginModel login = new LoginModel();
				login.setId(rs.getString("id"));
				login.setName(rs.getString("name"));
				login.setLimit(rs.getInt(("limit")));
				return login;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}
	
	public String[] getColumnName(String tableName) {
		String[] title = null;
		try {
			if(conn.isClosed())
				refreshConnection();
			PreparedStatement pst = conn.prepareStatement("select * from "+tableName);
			ResultSet re = pst.executeQuery();
			ResultSetMetaData data = re.getMetaData();
			title = new String[data.getColumnCount()];
			for(int i = 0; i < title.length; i++) {
				title[i] = new String(data.getColumnName(i+1));
			}
			close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			title = null;
		}
		return title;
	}
	
	public Vector getComment(String tableName) {
		Vector comments = null;
		String sql = "SHOW FULL FIELDS FROM "+tableName+";";
		try {
			ResultSet rs = excuteQuery(sql);
			comments = new Vector<String>();
			while(rs.next()) {
				comments.add(rs.getString("comment"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return comments;
	}

}
