package com.lpc.mode;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.lpc.dao.BaseInfoDao;

public class BaseInfoTable extends JTable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8099341795301233483L;
	private BaseInfoDao baseInfoDao = null;
	private DefaultTableModel model = null;
	private List baseInfoList = null;
	private String type;

	public BaseInfoTable(String type) {
		// TODO Auto-generated constructor stub
		super();
		baseInfoDao = new BaseInfoDao();
		this.type = type;
		InitData();
	}
	
	public void InitData() {
		baseInfoList = baseInfoDao.getList(type);
		String [] title = {"学/工号","姓名","曾用名","性别","年龄","籍贯","证件类别","证件号码","电话号码"};
		model = new DefaultTableModel(title, baseInfoList.size());
		BaseInfoModel baseInfo = null;
		for(int i = 0; i < baseInfoList.size(); i++) {
			baseInfo = (BaseInfoModel)baseInfoList.get(i);
			model.setValueAt(baseInfo.getId(), i, 0);
			model.setValueAt(baseInfo.getName(),i , 1);
			model.setValueAt(baseInfo.getFormarName(), i, 2);
			model.setValueAt(baseInfo.getSex(), i, 3);
			model.setValueAt(baseInfo.getAge()+"", i, 4);
			model.setValueAt(baseInfo.getNativePlace(), i, 5);
			model.setValueAt(baseInfo.getIDCARDTYPE(), i, 6);
			model.setValueAt(baseInfo.getIDCARDNUM(), i, 7);
			model.setValueAt(baseInfo.getTel(), i, 8);
		}
		setModel(model);
	}

	public List getBaseInfoList() {
		return baseInfoList;
	}

	public void setBaseInfoList(List baseInfoList) {
		this.baseInfoList = baseInfoList;
	}

}
