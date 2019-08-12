package com.lpc.iframe;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.lpc.dao.Dao;
import com.lpc.mode.XClassModel;
import com.lpc.util.ConnXupt;
import com.lpc.util.tools;

public class XClassManage extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton addBtn;
	private JButton deleteBtn;
	private JButton changeBtn;
	private JButton refreshBtn;
	private ContentPane contentPane;
	private BtnListener btnLis;
	private DefaultTableModel model;
	private Dao dao;

	public XClassManage() {
		// TODO 自动生成的构造函数存根
		super();
		dao = new Dao("xclass");
		InitJDialog();
		InitDate();
	}
	
	public void InitJDialog() {
		setTitle("班级信息管理");
		setIconImage(Toolkit.getDefaultToolkit().createImage(Login.getImgURL()));
		setSize(600,330);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setLocation(new Point(0, 0));
		setLocationRelativeTo(null);
		
		addBtn =  new JButton("添加");
		deleteBtn = new JButton("删除");
		changeBtn = new JButton("修改");
		refreshBtn = new JButton("刷新");
		
		contentPane = new ContentPane(new Dimension(getWidth(), getHeight()+30));
		setContentPane(contentPane);
		
		btnLis = new BtnListener();
		addBtn.addActionListener(btnLis);
		deleteBtn.addActionListener(btnLis);
		changeBtn.addActionListener(btnLis);
		refreshBtn.addActionListener(btnLis);
		
		contentPane.setAddBtn(addBtn);
		contentPane.setChangeBtn(changeBtn);
		contentPane.setDeleteBtn(deleteBtn);
		contentPane.setRefreshBtn(refreshBtn);
	}
	
	private void InitDate() {
		List XCList = dao.getList();
		Vector title = new ConnXupt().getComment("xclass");
		model = new DefaultTableModel(title,XCList.size());
		for (int i  = 0; i < model.getRowCount(); i++) {
			XClassModel xc = (XClassModel)XCList.get(i);
			model.setValueAt(xc.getId(), i, 0);
			model.setValueAt(xc.getName(), i, 1);
			model.setValueAt(xc.getNumber()+"", i, 2);
			model.setValueAt(xc.getCollegeId(), i, 3);
			model.setValueAt(xc.getDepartmentId(), i, 4);
			model.setValueAt(xc.getMajorId(), i, 5);
			model.setValueAt(xc.getSemester(), i, 6);
		}
		contentPane.setTableModel(model);
	}
	
	public class BtnListener implements ActionListener{

		private TextJDialog textJDialog;

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == addBtn) {
				textJDialog = new TextJDialog();
				textJDialog.setVisible(true);
				InitDate();
			}else if(e.getSource() == deleteBtn) {
				int i = contentPane.getSelectRow();
				if(i < 0) {
					JOptionPane.showMessageDialog(getContentPane(), "请选择一行");
					return;
				}
				String id = contentPane.getValueAt(i, 0)+"";
				if(JOptionPane.showConfirmDialog(getContentPane(), "确定要删除"+id+"吗？", "删除确认", JOptionPane.YES_NO_OPTION) == 1) {
					return;
				}
				if(dao.deleteData(id)) {
					JOptionPane.showMessageDialog(getContentPane(), "删除成功");
					InitDate();
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "删除失败");
				}
			}else if(e.getSource() == changeBtn) {
				int i = contentPane.getSelectRow();
				if(i < 0) {
					JOptionPane.showMessageDialog(getContentPane(), "请选择一行");
					return;
				}
				XClassModel xc = new XClassModel();
				xc.setId(contentPane.getValueAt(i, 0)+"");
				xc.setName(contentPane.getValueAt(i, 1)+"");
				xc.setNumber(Integer.parseInt((contentPane.getValueAt(i, 2)+"")));
				xc.setCollegeId(contentPane.getValueAt(i, 3)+"");
				xc.setDepartmentId(contentPane.getValueAt(i, 4)+"");
				xc.setSemester(contentPane.getValueAt(i, 5)+"");
				textJDialog = new TextJDialog(xc);
				textJDialog.setVisible(true);
				InitDate();
			}else if(e.getSource() == refreshBtn) {
				InitDate();
			}
		}
		
	}
	
	class TextJDialog extends JDialog{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JButton button;
		private JTextField idText;
		private JTextField nameText;
		private XClassModel data;
		private JTextField numberText;
		private JComboBox collegeBox;
		private JComboBox DPBox;
		private JComboBox semesterBox;
		private JComboBox majorBox;

		public TextJDialog() {
			super();
			setTitle("添加班级信息");
			button = new JButton("添加");
			InitJDialog();
		}
		
		public TextJDialog(XClassModel data) {
			super();
			setTitle("修改班级信息");
			button = new JButton("修改");
			this.data = data;
			InitJDialog();
			InitData();
		}
		
		private void InitJDialog() {
			setSize(250, 350);
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			setIconImage(Toolkit.getDefaultToolkit().createImage(Login.getImgURL()));
			setModal(true);
			setLocation(new Point(0, 0));
			setLocationRelativeTo(null);
			
			JLabel idLab = new JLabel("班级代号：");
			idLab.setBounds(15,15,80,30);
			idText = new JTextField();
			idText.setBounds(100,15,120,30);
			JLabel nameLab = new JLabel("名称：");
			nameLab.setBounds(15,50,80,30);
			nameText = new JTextField();
			nameText.setBounds(100,50,120,30);
			JLabel numberLab = new JLabel("班级人数：");
			numberLab.setBounds(15,85,80,30);
			numberText = new JTextField();
			numberText.setBounds(100,85,120,30);
			JLabel collegeLab = new JLabel("所属学院：");
			collegeLab.setBounds(15,120,80,30);
			collegeBox = new JComboBox();
			collegeBox.setBounds(100,120,120,30);
			JLabel DPLab = new JLabel("所属系/部：");
			DPLab.setBounds(15,155,80,30);
			DPBox = new JComboBox();
			DPBox.setBounds(100,155,120,30);
			JLabel majorLab = new JLabel("所属专业：");
			majorLab.setBounds(15,190,80,30);
			majorBox = new JComboBox();
			majorBox.setBounds(100,190,120,30);
			JLabel semesterLab = new JLabel("所属年级：");
			semesterLab.setBounds(15,225,80,30);
			semesterBox = new JComboBox();
			semesterBox.setBounds(100,225,120,30);
			button.setBounds((getWidth() - 80)/2,260,80,30);
			
			JPanel contentPane = new JPanel();
			contentPane.setLayout(null);
			contentPane.add(idLab);
			contentPane.add(idText);
			contentPane.add(nameLab);
			contentPane.add(nameText);
			contentPane.add(numberLab);
			contentPane.add(numberText);
			contentPane.add(collegeLab);
			contentPane.add(collegeBox);
			contentPane.add(DPLab);
			contentPane.add(DPBox);
			contentPane.add(majorLab);
			contentPane.add(majorBox);
			contentPane.add(semesterLab);
			contentPane.add(semesterBox);
			contentPane.add(button);
			setContentPane(contentPane);
			
			button.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					XClassModel xc = new XClassModel();
					tools tool = new tools();
					xc.setId(idText.getText());
					xc.setName(nameText.getText());
					try {
						xc.setNumber(Integer.parseInt(numberText.getText()));
					} catch (Exception e2) {
						// TODO: handle exception
						xc.setNumber(0);
					}
					xc.setCollegeId(tool.Split(collegeBox.getSelectedItem()+""));
					xc.setDepartmentId(tool.Split(DPBox.getSelectedItem()+""));
					xc.setMajorId(tool.Split(majorBox.getSelectedItem()+""));
					xc.setSemester(tool.Split(semesterBox.getSelectedItem()+""));
					if(xc.getId().length() == 0 || xc.getName().length() == 0 || xc.getNumber() == 0 || xc.getCollegeId().length() == 0 || xc.getDepartmentId().length() == 0 || xc.getMajorId().length() == 0 || xc.getSemester().length() == 0) {
						JOptionPane.showMessageDialog(getContentPane(), "以上七项都是必填项，不能遗漏");
						return ;
					}
					if(button.getText().equals("添加")) {
						if(dao.insertdata(xc)) {
							JOptionPane.showMessageDialog(getContentPane(), "添加成功");
							dispose();
						}else {
							JOptionPane.showMessageDialog(getContentPane(), "添加失败");
						}
					}else {
						if(dao.updatedata(xc, data.getId())) {
							JOptionPane.showMessageDialog(getContentPane(), "修改成功");
							dispose();
						}else {
							JOptionPane.showMessageDialog(getContentPane(), "修改失败");
							InitData();
						}
					}
				}
			});
			List collegeIdList = new Dao("college").getIdList();
			for(int i = 0; i < collegeIdList.size() ; i++) {
				String collegeId = collegeIdList.get(i)+"";
				collegeBox.addItem(collegeId);
			}
			List departmentIdList = new Dao("department").getIdList();
			for(int i = 0; i < departmentIdList.size(); i++) {
				String departmentId = departmentIdList.get(i)+"";
				DPBox.addItem(departmentId);
			}
			List majorIdList = new Dao("major").getIdList();
			for(int i = 0; i < majorIdList.size() ; i++) {
				String majorId = majorIdList.get(i)+"";
				majorBox.addItem(majorId);
			}
			List semesterIdList = new Dao("semester").getIdList();
			for(int i = 0; i < semesterIdList.size(); i++) {
				String semesterId = semesterIdList.get(i)+"";
				semesterBox.addItem(semesterId);
			}
		}
		
		private void InitData() {
			if(data != null) {
				tools tool = new tools();
				idText.setText(data.getId());
				nameText.setText(data.getName());
				numberText.setText(data.getNumber()+"");
				tool.setSelectedItem(collegeBox, data.getCollegeId());
				tool.setSelectedItem(DPBox, data.getDepartmentId());
				tool.setSelectedItem(majorBox, data.getMajorId());
				tool.setSelectedItem(semesterBox, data.getSemester());
			}
		}
	}

}

