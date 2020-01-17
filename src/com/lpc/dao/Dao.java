package com.lpc.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
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
import com.lpc.util.ConnXupt;
import com.lpc.util.Secret;

public class Dao {

	private ConnXupt connXupt;
	private boolean isOk;
	private String tableName;

	public Dao(String tableName) {
		// TODO 自动生成的构造函数存根
		connXupt = new ConnXupt();
		this.tableName = tableName;
	}
	
	public List getList() {//获取表中所有数据
		List list = null;
		String sql = "SELECT * FROM "+tableName+";";
		try {
			String [] column = connXupt.getColumnName(tableName);
			ResultSet rs = connXupt.excuteQuery(sql);
			list = new ArrayList();
			while(rs.next()) {
				list.add(connXupt.setValue(rs, tableName,column)); 
			}
			connXupt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	private String StitchingStrings(int type,Object data) {//拼接字符串
		String sql = "";
		String sql1 = null;
		switch(type) {
		case 1:
			sql1 = "INSERT INTO "+tableName;
			if(tableName.equals("college")) {
				CollegeModel college = (CollegeModel)data;
				sql = sql1+" VALUES('"+college.getId()+"','"+college.getName()+"');";
			}else if(tableName.equals("base_info")) {
				BaseInfoModel baseInfoModel = (BaseInfoModel)data;
				sql = sql1+" values(null,'"+baseInfoModel.getId()+"','"+baseInfoModel.getName()+"','"+baseInfoModel.getFormarName()+"','"+
						baseInfoModel.getSex()+"',"+baseInfoModel.getAge()+",'"+baseInfoModel.getNativePlace()+"','"+baseInfoModel.getIDCARDTYPE()+"','"+
						baseInfoModel.getIDCARDNUM()+"','"+baseInfoModel.getType()+"','"+baseInfoModel.getTel()+"');";
			}else if(tableName.equals("student")) {
				StudentModel student = (StudentModel)data;
				sql = sql1+" VALUES('"+student.getId()+"','"+student.getYear()+"','"+student.getCollege()+"','"+student.getDepartment()+"','"+
						student.getMajor()+"','"+student.getGrade()+"','"+student.getClassId()+"','"+student.getCulture_level()+"','"+student.getType()+
						"','"+student.getEducation()+"');";
			}else if(tableName.equals("teacher")) {
				TeacherModel teacher = (TeacherModel)data;
				sql = sql1+" values('"+teacher.getId()+"','"+teacher.getCollege()+"','"+teacher.getDepartment()+"','"+
						teacher.getLevel()+"','"+teacher.getEducation()+"','"+teacher.getYear()+"');";
			}else if(tableName.equals("department")) {
				DepartmentModel department = (DepartmentModel)data;
				sql = sql1+" values('"+department .getId()+"','"+department.getName()+"','"+department.getCollegeId()+"');";
			}else if(tableName.equals("major")) {
				MajorModel major = (MajorModel)data;
				sql = sql1+" values('"+major .getId()+"','"+major.getName()+"','"+major.getCollegeId()+"','"+major.getDepartmentId()+"');";
			}else if(tableName.equals("school_year")) {
				SchoolYearModel school = (SchoolYearModel)data;
				sql = sql1+" values('"+school.getId()+"','"+school.getName()+"','"+school.getBegin()+"','"+school.getEnd()+"');";
			}else if(tableName.equals("school_trem")) {
				SchoolTremModel STM = (SchoolTremModel)data;
				sql = sql1+" values('"+STM.getId()+"','"+STM.getName()+"','"+STM.getSYId()+"','"+STM.getBegin()+"','"+STM.getEnd()+"');";
			}else if(tableName.equals("semester")) {
				String[] str = (String[])data;
				sql = sql1+" values('"+str[0]+"','"+str[1]+"');";
			}else if(tableName.equals("xclass")) {
				XClassModel xc = (XClassModel)data;
				sql = sql1+" values('"+xc.getId()+"','"+xc.getName()+"',"+xc.getNumber()+",'"+xc.getCollegeId()+"','"+
						xc.getDepartmentId()+"','"+xc.getMajorId()+"','"+xc.getSemester()+"');";
			}else if(tableName.equals("course")) {
				CourseModel course = (CourseModel)data;
				sql = sql1+" values('"+course.getId()+"','"+course.getName()+"','"+course.getCollegeId()+"','"+course.getDepartmentId()
				+"','"+course.getType()+"',"+course.getCredit()+");";
			}else if(tableName.equals("offering_courses")) {
				OfferingModel off = (OfferingModel)data;
				sql = sql1+" values('"+off.getId()+"','"+off.getCourseID()+"','"+off.getTeacherId()+"','"+off.getBegin()+"','"+
				off.getSY()+"','"+off.getST()+"','"+off.getSemesterId()+"',"+off.getNum()+");";
			}else if(tableName.equals("student_course")) {
				StudentCourseModel SCM = (StudentCourseModel)data;
				sql = sql1+" values(null,'"+SCM.getStudentId()+"','"+SCM.getCourseId()+"','"+SCM.getSY()+"','"+SCM.getST()+"','"+SCM.getTime()+"');";
			}else if(tableName.equals("login")) {
				LoginModel login = (LoginModel)data;
				sql = sql1+" values('"+login.getId()+"','"+login.getName()+"','"+login.getLimit()+"','"+new Secret("11111111").set()+"');";
			}
			break;
		case 2:
			sql1 = "UPDATE "+tableName+" set ";
			if(tableName.equals("college")) {
				CollegeModel college = (CollegeModel)data;
				sql = sql1+"id='"+college.getId()+"',name='"+college.getName()+"' ";
			}else if(tableName.equals("base_info")) {
				BaseInfoModel baseInfo = (BaseInfoModel)data;
				sql = sql1+" name='"+baseInfo.getName()+"',formar_name='"+baseInfo.getFormarName()+"',sex='"+baseInfo.getSex()+
						"',age="+baseInfo.getAge()+",native_place='"+baseInfo.getNativePlace()+"',IDCARD_type='"+baseInfo.getIDCARDTYPE()+"',IDCARD_NUM='"+baseInfo.getIDCARDNUM()+
						"',tel='"+baseInfo.getTel()+"' ";
			}else if(tableName.equals("student")) {
				StudentModel student = (StudentModel)data;
				sql = sql1+"year='"+student.getYear()+"',college='"+student.getCollege()+"',department='"+student.getDepartment()
					+"',major='"+student.getMajor()+"',grade='"+student.getGrade()+"',class='"+student.getClassId()+"',culture_level='"+
					student.getCulture_level()+"',student_type='"+student.getType()+"',education='"+student.getEducation()+"' ";
			}else if(tableName.equals("teacher")) {
				TeacherModel teacher = (TeacherModel)data;
				sql = sql1+"college='"+teacher.getCollege()+"',department='"+teacher.getDepartment()+
						"',level='"+teacher.getLevel()+"',Education='"+teacher.getEducation()+"',year='"+teacher.getYear()+"' ";
			}else if(tableName.equals("department")) {
				DepartmentModel department = (DepartmentModel)data;
				sql = sql1+"id='"+department.getId()+"',name='"+department.getName()+"',college_id='"+department.getCollegeId()+"' ";
			}else if(tableName.equals("major")) {
				MajorModel major = (MajorModel)data;
				sql = sql1+"id='"+major.getId()+"',name='"+major.getName()+"',college_id='"+major.getCollegeId()+"',department_id='"+major.getDepartmentId()+"' ";
			}else if(tableName.equals("school_year")) {
				SchoolYearModel school = (SchoolYearModel)data;
				sql = sql1+"id='"+school.getId()+"',name='"+school.getName()+"',begin='"+school.getBegin()+"',end='"+school.getEnd()+"' ";
			}else if(tableName.equals("school_trem")) {
				SchoolTremModel STM = (SchoolTremModel)data;
				sql = sql1+"id='"+STM.getId()+"',name='"+
						STM.getName()+"',school_year='"+STM.getSYId()+"',begin='"+STM.getBegin()+"',end='"+STM.getEnd()+"' ";
			}else if(tableName.equals("xclass")) {
				XClassModel xc = (XClassModel)data;
				sql = sql1+"id='"+xc.getId()+"',name='"+xc.getName()+"',number="+xc.getNumber()+",college_id='"+
						xc.getCollegeId()+"',department_id='"+xc.getDepartmentId()+"',major_id='"+xc.getMajorId()+"',semester='"+xc.getSemester()
						+"' ";
			}else if(tableName.equals("semester")) {
				String[] str = (String[])data;
				sql = sql1+"id='"+str[0]+"',name='"+str[1]+"' ";
			}else if(tableName.equals("course")) {
				CourseModel course = (CourseModel)data;
				sql = sql1+"id='"+course.getId()+"',name='"+course.getName()+"',college_id='"+course.getCollegeId()+"',department_id='"+
				course.getDepartmentId()+"',type='"+course.getType()+"',credit="+course.getCredit()+" ";
			}else if(tableName.equals("offering_courses")) {
				OfferingModel off = (OfferingModel)data;
				sql = sql1+"id='"+off.getId()+"',course_id='"+off.getCourseID()+"',teacher_id='"+off.getTeacherId()+"',begin='"+
				off.getBegin()+"',school_year_id='"+off.getSY()+"',school_trem_id='"+off.getST()+"',semester_id='"+off.getSemesterId()
				+"',max_num="+off.getNum()+" ";
			}else if(tableName.equals("student_course")) {
				StudentCourseModel SCM = (StudentCourseModel)data;
				sql = sql1+"student_id='"+SCM.getStudentId()+"',course_id='"+SCM.getCourseId()+"',school_year_id='"+SCM.getSY()+"',school_trem_id='"
				+SCM.getST()+"',time='"+SCM.getTime()+"' ";
			}else if(tableName.equals("login")) {
				LoginModel login = (LoginModel)data;
				sql = sql1+"id='"+login.getId()+"',name='"+login.getName()+"',login.`limit`="+login.getLimit()+" ";
			}
		}
		return sql;
	}
	public boolean insertdata(Object data) {//插入数据
		isOk = false;
		String sql = StitchingStrings(1, data);
		
		try {
			if(connXupt.excuteUpdate(sql) == 1) {
				isOk = true;
			}
			connXupt.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(sql);
			e.printStackTrace();
		}
		return isOk;
	}
	
	public boolean updatedata(Object data, String id) {//更新数据
		isOk = false;
		String sql = StitchingStrings(2,data)+"where id=";
		if(tableName.equals("student_course")){
			sql = sql+id+";";
		}else {
			sql = sql+"'"+id+"';";
		}
		try {
			if(connXupt.excuteUpdate(sql) == 1) {
				isOk = true;
			}
			connXupt.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(sql);
			e.printStackTrace();
		}
		return isOk;
	}
	
	public boolean deleteData(String id) {//删除数据
		isOk = false;
		String sql = "delete from "+tableName+" where id=";
		if(tableName.equals("student_course")) {
			sql = sql+id+";";
		}else {
			sql = sql+"'"+id+"';";
		}
		try {
			if(connXupt.excuteUpdate(sql) == 1) {
				isOk = true;
			}
			connXupt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return isOk;
	}
	
	public List getIdList() {//获取id链表
		List idList = null;
		String sql = "select id,name from "+tableName+" ;";
		if(tableName.equals("base_info")) {
			sql = "select user_id,name from "+tableName+";";
		}else if(tableName.equals("teacher")) {
			sql = "select user_id,name from base_info where user_type='teacher';";
		}else if(tableName.equals("student")) {
			sql = "select user_id,name from base_info where user_type='student';";
		}else if(tableName.equals("offering_courses")) {
			sql = "select id,course_id from "+tableName+";";
		}
		try {
			ResultSet rs = connXupt.excuteQuery(sql);
			idList = new ArrayList();
			while(rs.next()) {
				String id = null;
				id = rs.getString(1)+" "+rs.getString(2);
				idList.add(id);
			}
			connXupt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return idList;
	}
	
	public String getTableComment() {//获取表注释
		String sql = "SELECT TABLE_COMMENT from INFORMATION_SCHEMA.TABLES Where table_schema = 'xupt' AND table_name LIKE '"+tableName+"';";
		String tableComment = null;
		System.out.println(sql);
		try {
			ResultSet rs = connXupt.excuteQuery(sql); 
			if(rs.next()) {
				tableComment = rs.getString("TABLE_COMMENT");
			}
			connXupt.close();
		} catch (Exception e) {
			// TODO: handle exception
			tableComment = null;
			e.printStackTrace();
		}
		return tableComment;
	}

}
