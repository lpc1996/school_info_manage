package com.lpc.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.lpc.mode.BaseInfoModel;
import com.lpc.mode.TeacherModel;
import com.lpc.util.ConnXupt;

public class TeacherDao {
	
	private ConnXupt teacherDao;
	private boolean isOk;

	public TeacherDao() {
		// TODO Auto-generated constructor stub
		teacherDao = new ConnXupt(); 
	}
	
	public List getList() {
		List teacherInfoList = null;
		String sql = "select * from teacher;";
		try {
			ResultSet rs = teacherDao.excuteQuery(sql);
			teacherInfoList = new ArrayList();
			while(rs.next()) {
				TeacherModel teacher = setValue(rs);
				if(teacher != null) {
					teacherInfoList.add(teacher);
				}
			}
			teacherDao.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return teacherInfoList;
	}
	
	private TeacherModel setValue(ResultSet rs) {
		TeacherModel teacher = null;
		try {
			teacher = new TeacherModel();
			teacher.setId(rs.getString("id"));
			teacher.setCollege(rs.getString("college"));
			teacher.setDepartment(rs.getString("department"));
			teacher.setLevel(rs.getString("level"));
			teacher.setEducation(rs.getString("Education"));
			teacher.setYear(rs.getString("year"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			teacher = null;
		}
		return teacher;
	}
	
	public boolean addTeacher(TeacherModel teacher) {
		isOk = false;
		String sql = "INSERT INTO teacher values('"+teacher.getId()+"','"+teacher.getCollege()+"','"+teacher.getDepartment()+"','"+
		teacher.getLevel()+"','"+teacher.getEducation()+"','"+teacher.getYear()+"');";
		try {
			if(teacherDao.excuteUpdate(sql) ==1 ) {
				isOk = true;
			}
			teacherDao.close();
		} catch (Exception e) {
			// TODO: handle exception
			isOk = false;
		}
		return isOk;
	}
	
	public boolean addBaseInfo(BaseInfoModel baseInfoModel) {
		isOk = false;
		isOk = new BaseInfoDao().addBaseInfo(baseInfoModel);
		return isOk;
	}
	
	public List getBaseInfoList(String type) {
		return new BaseInfoDao().getList(type);
	}
	
	public boolean updateTeacher(TeacherModel teacher,String id) {
		isOk = false;
		String sql = "update teacher set id='"+teacher.getId()+"',college='"+teacher.getCollege()+"',department='"+teacher.getDepartment()+
				"',level='"+teacher.getLevel()+"',Education='"+teacher.getEducation()+"',year='"+teacher.getYear()+"' where id='"+id+"';";
		try {
			if(teacherDao.excuteUpdate(sql) == 1) {
				isOk = true;
			}
			teacherDao.close();
		} catch (Exception e) {
			// TODO: handle exception
			isOk = false;
			e.printStackTrace();
		}
		return isOk;
	}
	
	public boolean updateBaseInfo(BaseInfoModel baseInfo,String id) {
		return new BaseInfoDao().updateBaseInfo(baseInfo,id);
	}
	
	public boolean deleteTeacher(String id) {
		isOk = false;
		String sql = "DELETE FROM teacher where id='"+id+"';";
		try {
			if(teacherDao.excuteUpdate(sql) == 1) {
				isOk = true;
			}
			teacherDao.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return isOk;
	}
	
	public boolean deleteBaseInfo(String id) {
		return new BaseInfoDao().deleteBaseInfo(id);
	}
	
	public List getIdList() {
		List IdList = null;
		String sql = "select id from teacher";
		try {
			ResultSet rs = teacherDao.excuteQuery(sql);
			IdList = new ArrayList();
			while(rs.next()) {
				String id = rs.getString("id");
				IdList.add(id);
			}
			teacherDao.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return IdList;
	}

}
