package com.lpc.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.lpc.mode.LoginModel;
import com.lpc.util.ConnXupt;

public class LoginDao {
	
	private ConnXupt loginDao;
	
	public LoginDao() {
		// TODO Auto-generated constructor stub
		loginDao = new ConnXupt();
	}
	
	public LoginModel login(String user,String pass) {
		String sql = "Select * from login where id='"+user+"' and pass='"+pass+"';";
		LoginModel loginModel = null;
		try {
			ResultSet rs = loginDao.excuteQuery(sql);
			if(rs.next()) {
				loginModel = new LoginModel();
				loginModel.setId(user);
				loginModel.setName(rs.getString("name"));
				loginModel.setLimit(rs.getInt("limit"));
			}
			loginDao.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return loginModel;
	}
	
	public boolean insertUser(LoginModel user,String pass) {
		boolean isOk = false;
		String sql = "insert into login values('"+user.getId()+"','"+user.getName()+"','"+user.getLimit()+"','"+pass+"';";
		try {
			if(loginDao.excuteUpdate(sql) == 1) {
				isOk = true;
				loginDao.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			isOk = false;
		}
		return isOk;
	}
	
	public List getList(String limit){
		List list = null;
		String sql = "SELECT * FROM login where limit < '"+limit+"';";
		try {
			ResultSet rs = loginDao.excuteQuery(sql);
			list = new ArrayList();
			while(rs.next()) {
				LoginModel loginModel = new LoginModel();
				loginModel.setId(rs.getString("id"));
				loginModel.setName(rs.getString("name"));
				loginModel.setLimit(rs.getInt("limit"));
				list.add(loginModel);
			}
			loginDao.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			list = null;
		}
		return list;
	}
	
	public boolean updatePass(String oldPass,String newPass,String id) {
		boolean isOk = false;
		String sql = "update login set pass='"+newPass+"' where id='"+id+"' and pass='"+oldPass+"';";
		try {
			if(loginDao.excuteUpdate(sql) == 1) {
				isOk = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			isOk = false;
		}
		return isOk;
	}
}
