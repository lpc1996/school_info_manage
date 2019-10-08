package com.lpc.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.lpc.mode.BaseInfoModel;
import com.lpc.util.ConnXupt;

public class BaseInfoDao {
	
	private ConnXupt baseInfoDao;
	private boolean isOk;

	public BaseInfoDao() {
		// TODO Auto-generated constructor stub
		baseInfoDao = new ConnXupt();
	}
	
	public BaseInfoModel getUser(String id) {
		BaseInfoModel baseInfo = null;
		String sql = "SELECT * FROM base_info where user_id='"+id+"';";
		try {
			String [] column = baseInfoDao.getColumnName("base_info");
			ResultSet rs = baseInfoDao.excuteQuery(sql);
			if(rs.next()) {
				baseInfo = (BaseInfoModel) baseInfoDao.setValue(rs,"base_info",column); 
				
			}
			baseInfoDao.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			baseInfo = null;
		}
		return baseInfo;
	}
	
	public boolean changeInfo(BaseInfoModel baseinfo,String userName) {
		isOk = false;
		String sqlBase = "update base_info set user_id='"+baseinfo.getId()+"',name='"+baseinfo.getName()+"',formar_name='"+baseinfo.getFormarName()
		+"',sex='"+baseinfo.getSex()+"',age="+baseinfo.getAge()+",native_place='"+baseinfo.getNativePlace()+"',IDCARD_type='"+
				baseinfo.getIDCARDTYPE()+"',IDCARD_NUM='"+baseinfo.getIDCARDNUM()+"',tel='"+baseinfo.getTel()+"' where user_id='"+
		baseinfo.getId()+"';";
		String sqlLogin = "update login set name='"+userName+"' where id='"+baseinfo.getId()+"';";
		try {
			if(baseInfoDao.excuteUpdate(sqlBase) == 1)
				isOk = true;
			if(baseInfoDao.excuteUpdate(sqlLogin) == 1)
				isOk = true;
			baseInfoDao.close();
		} catch (Exception e) {
			// TODO: handle exception
			isOk = false;
		}
		return isOk;
	}
	
	public List getList(String type) {
		List baseInfoList = null;
		String sql = null;
		if(type.equals("")) {
			sql = "select * from base_info;";
		}
		sql = "select * from base_info where user_type = '"+type+"';";
		try {
			String [] column = baseInfoDao.getColumnName("base_info");
			ResultSet rs = baseInfoDao.excuteQuery(sql);
			baseInfoList = new ArrayList();
			while(rs.next()) {
				BaseInfoModel baseInfo = (BaseInfoModel) baseInfoDao.setValue(rs,"base_info",column);
				if(baseInfo != null) {
					baseInfoList.add(baseInfo);
				}
			}	
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			baseInfoDao.close();
		}
		return baseInfoList;
	}
	
	public boolean addBaseInfo(BaseInfoModel baseInfoModel) {
		isOk = false;
		String sql = "INSERT INTO base_info values(null,'"+baseInfoModel.getId()+"','"+baseInfoModel.getName()+"','"+baseInfoModel.getFormarName()+"','"+
				baseInfoModel.getSex()+"',"+baseInfoModel.getAge()+",'"+baseInfoModel.getNativePlace()+"','"+baseInfoModel.getIDCARDTYPE()+"','"+
				baseInfoModel.getIDCARDNUM()+"','"+baseInfoModel.getType()+"','"+baseInfoModel.getTel()+"');";
		try {
			if(baseInfoDao.excuteUpdate(sql) == 1) {
				isOk = true;
			}
			baseInfoDao.close();
		} catch (Exception e) {
			// TODO: handle exception
			isOk = false;
		}
		return isOk;
	}
	
		public boolean updateBaseInfo(BaseInfoModel baseInfo,String id) {
		isOk = false;
		String sql = "update base_info set user_id='"+baseInfo.getId()+"',name='"+baseInfo.getName()+"',formar_name='"+baseInfo.getFormarName()+"',sex='"+baseInfo.getSex()+
				"',age="+baseInfo.getAge()+",native_place='"+baseInfo.getNativePlace()+"',IDCARD_type='"+baseInfo.getIDCARDTYPE()+"',IDCARD_NUM='"+baseInfo.getIDCARDNUM()+
				"',tel='"+baseInfo.getTel()+"' where user_id='"+id+"';";
//		System.out.println(sql);
		try {
			if(baseInfoDao.excuteUpdate(sql) == 1) {
				isOk = true;
			}
			baseInfoDao.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			isOk = false;
		}
		return isOk;
	}
	
	public boolean deleteBaseInfo(String id) {
		isOk = false;
		String sql = "DELETE FROM base_info where user_id='"+id+"';";
		try {
			if(baseInfoDao.excuteUpdate(sql) == 1) {
				isOk = true;
			}
			baseInfoDao.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return isOk;
	}
}
