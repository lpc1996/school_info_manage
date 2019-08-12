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
import com.lpc.mode.OfferingModel;
import com.lpc.util.ConnXupt;
import com.lpc.util.DateChooser;
import com.lpc.util.tools;

public class CourseClass extends JDialog{

	private static final long serialVersionUID = 1L;
	private final String tableName = "offering_courses";
	private Dao dao;
	private ContentPane contentPane;
	private JButton addBtn;
	private JButton changeBtn;
	private JButton deleteBtn;
	private JButton refreshBtn;

	public CourseClass() {
		// TODO 自动生成的构造函数存根
		super();
		dao = new Dao(tableName);
		InitPane();
		InitData();
	}
	
	private void InitPane() {
		setTitle("课程开设管理");
		setIconImage(Toolkit.getDefaultToolkit().createImage(Login.getImgURL()));
		setSize(700,430);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setLocation(new Point(0, 0));
		setLocationRelativeTo(null);
		
		contentPane = new ContentPane(new Dimension(getWidth(),getHeight()+30));
		this.setContentPane(contentPane);
		
		addBtn = new JButton("添加");
		changeBtn = new JButton("修改");
		deleteBtn = new JButton("删除");
		refreshBtn = new JButton("刷新");
		
		contentPane.setAddBtn(addBtn);
		contentPane.setChangeBtn(changeBtn);
		contentPane.setDeleteBtn(deleteBtn);
		contentPane.setRefreshBtn(refreshBtn);
		
		BtnListener btnLis = new BtnListener();
		addBtn.addActionListener(btnLis);
		changeBtn.addActionListener(btnLis);
		deleteBtn.addActionListener(btnLis);
		refreshBtn.addActionListener(btnLis);
	}
	
	private void InitData() {
		List offeringList = dao.getList();
		Vector title = new ConnXupt().getComment(tableName);
		DefaultTableModel model = new DefaultTableModel(title, offeringList.size());
		for(int i = 0; i < offeringList.size(); i++) {
			OfferingModel off = (OfferingModel)offeringList.get(i);
			model.setValueAt(off.getId(), i, 0);
			model.setValueAt(off.getCourseID(), i, 1);
			model.setValueAt(off.getTeacherId(), i, 2); 
			model.setValueAt(off.getBegin(), i, 3);
			model.setValueAt(off.getSY(), i, 4);
			model.setValueAt(off.getST(), i, 5);
			model.setValueAt(off.getSemesterId(), i, 6);
			model.setValueAt(off.getNum()+"", i, 7);
		}
		contentPane.setTableModel(model);
	}
	
	public class BtnListener implements ActionListener{

		private TextJDialog textJDialog;

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == addBtn) {
				textJDialog = new TextJDialog("添加开设课程信息");
				textJDialog.setVisible(true);
				InitData();
			}else if(e.getSource() == deleteBtn) {
				int i = contentPane.getSelectRow();
				if(i < 0 ) {
					JOptionPane.showMessageDialog(getContentPane(), "请选择一行");
					return;
				}
				String id = contentPane.getValueAt(i, 0)+"";
				
				if(JOptionPane.showConfirmDialog(getContentPane(), "确定要删除"+id+"吗？", "删除确认", JOptionPane.YES_NO_OPTION) == 1) {
					return;
				}
				if(dao.deleteData(id)) {
					JOptionPane.showMessageDialog(getContentPane(), "删除成功");
					InitData();
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "删除失败");
				}
			}else if(e.getSource() == changeBtn) {
				int i = contentPane.getSelectRow();
				if(i < 0 ) {
					JOptionPane.showMessageDialog(getContentPane(), "请选择一行");
					return;
				}
				OfferingModel off = new OfferingModel();
				off.setId(contentPane.getValueAt(i, 0));
				off.setCourseID(contentPane.getValueAt(i, 1));
				off.setTeacherId(contentPane.getValueAt(i, 2));
				off.setBegin(contentPane.getValueAt(i, 3));
				off.setSY(contentPane.getValueAt(i, 4));
				off.setST(contentPane.getValueAt(i, 5));
				off.setSemesterId(contentPane.getValueAt(i, 6));
				try {
					off.setNum(Integer.parseInt(contentPane.getValueAt(i, 7)));
				} catch (NumberFormatException e1) {
					// TODO 自动生成的 catch 块
					JOptionPane.showMessageDialog(null, "出错啦！");
				}
				
				textJDialog = new TextJDialog("修改开设课程信息",off);
				textJDialog.setVisible(true);
				InitData();
			}else if(e.getSource() == refreshBtn) {
				InitData();
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
		private OfferingModel offering;
		private JComboBox teacherIdBox;
		private JComboBox courseBox;
		private JTextField beginText;
		private JComboBox SYBox;
		private JComboBox STBox;
		private JComboBox semesterIdBox;
		private JTextField numText;

		public TextJDialog(String title) {
			super();
			this.setTitle(title);
			button = new JButton();
			button.setText("添加");
			InitJDialog();
		}
		
		public TextJDialog(String title, OfferingModel offering) {
			super();
			this.setTitle(title);
			this.offering = offering;
			button = new JButton();
			button.setText("修改");
			InitJDialog();
			InitData();
		}
		
		private void InitJDialog() {
			setSize(250, 370);
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			setIconImage(Toolkit.getDefaultToolkit().createImage(Login.getImgURL()));
			setModal(true);
			setLocation(new Point(0, 0));
			setLocationRelativeTo(null);
			JPanel contentPane = new JPanel();
			Vector title = new ConnXupt().getComment(tableName);
			for(int i = 0; i < title.size(); i++ ) {
				JLabel lab = new JLabel(title.get(i)+"：");
				lab.setBounds(15, 15+30*i+i*5, 80, 30);
				contentPane.add(lab);
			}
			idText = new JTextField();
			idText.setBounds(100,15,120,30);
			courseBox = new JComboBox();
			courseBox.setBounds(100,50,120,30);
			teacherIdBox = new JComboBox();
			teacherIdBox.setBounds(100,85,120,30);
			beginText = new JTextField();
			DateChooser dc = DateChooser.getInstance("yyyy-MM-dd");
			dc.register(beginText);
			beginText.setBounds(100,120,120,30);
			SYBox = new JComboBox();
			SYBox.setBounds(100,155,120,30);
			STBox = new JComboBox();
			STBox.setBounds(100,190,120,30);
			semesterIdBox = new JComboBox();
			semesterIdBox.setBounds(100,225,120,30);
			numText = new JTextField();
			numText.setBounds(100,260,120,30);
			button.setBounds((getWidth() - 80)/2,295,80,30);
			
			contentPane.setLayout(null);
			contentPane.add(idText);
			contentPane.add(courseBox);
			contentPane.add(teacherIdBox);
			contentPane.add(beginText);
			contentPane.add(SYBox);
			contentPane.add(STBox);
			contentPane.add(semesterIdBox);
			contentPane.add(numText);
			contentPane.add(button);
			setContentPane(contentPane);
			
			button.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					OfferingModel data = new OfferingModel();
					tools tool = new tools();
					data.setId(idText.getText());
					data.setCourseID(tool.Split(courseBox.getSelectedItem()+""));
					data.setTeacherId(tool.Split(teacherIdBox.getSelectedItem()+""));
					data.setBegin(beginText.getText());
					data.setSY(tool.Split(SYBox.getSelectedItem()+""));
					data.setST(tool.Split(STBox.getSelectedItem()+""));
					data.setSemesterId(tool.Split(semesterIdBox.getSelectedItem()+""));
					try {
						data.setNum(Integer.parseInt(numText.getText()));
					} catch (NumberFormatException e2) {
						// TODO 自动生成的 catch 块
						JOptionPane.showMessageDialog(null, "请输入正确班级人数");
						return;
					}
					
					if(data.getId().length() == 0 || data.getCourseID().length() == 0 || data.getTeacherId().length() == 0 || 
							data.getBegin().length() == 0 || data.getSY().length() == 0 || data.getST().length() == 0 || 
							data.getSemesterId().length() == 0 || data.getNum() == 0) {
						JOptionPane.showMessageDialog(getContentPane(), "以上六项为必填项，不可以遗漏哟");
						return;
					}
					
					if(button.getText().equals("添加")) {
						
						if(dao.insertdata(data)) {
							JOptionPane.showMessageDialog(getContentPane(), "添加成功");
							dispose();
						}else {
							JOptionPane.showMessageDialog(getContentPane(), "添加失败");
						}
					}else if(button.getText().equals("修改")) {
						
						if(dao.updatedata(data, offering.getId())) {
							JOptionPane.showMessageDialog(getContentPane(), "修改成功");
							dispose();
						}else {
							JOptionPane.showMessageDialog(getContentPane(), "修改失败");
							InitData();
						}
					}
				}
			});
			
			List courseList = new Dao("course").getIdList();
			for(int i = 0; i < courseList.size() ; i++) {
				courseBox.addItem(courseList.get(i));
			}
			courseList = null;
			List teacherList = new Dao("teacher").getIdList();
			for(int i = 0 ; i < teacherList.size(); i++) {
				teacherIdBox.addItem(teacherList.get(i));
			}
			List SYList = new Dao("school_year").getIdList();
			for(int i = 0; i < SYList.size(); i++) {
				SYBox.addItem(SYList.get(i));
			}
			List STList = new Dao("school_trem").getIdList();
			for(int i = 0; i < STList.size(); i++) {
				STBox.addItem(STList.get(i));
			}
			List semesterList = new Dao("semester").getIdList();
			for(int i = 0; i < semesterList.size() ; i++) {
				semesterIdBox.addItem(semesterList.get(i));
			}
		}
		
		private void InitData() {
			if(offering != null) {
				tools tool = new tools();
				idText.setText(offering.getId());
				tool.setSelectedItem(courseBox, offering.getCourseID());
				tool.setSelectedItem(teacherIdBox, offering.getTeacherId());
				beginText.setText(offering.getBegin());
				tool.setSelectedItem(SYBox, offering.getSY());
				tool.setSelectedItem(STBox, offering.getST());
				tool.setSelectedItem(semesterIdBox, offering.getSemesterId());
				numText.setText(offering.getNum()+"");
			}
		}
	}

}
