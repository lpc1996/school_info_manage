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
import com.lpc.mode.StudentCourseModel;
import com.lpc.util.ConnXupt;
import com.lpc.util.DateChooser;
import com.lpc.util.tools;

public class StudentCourseManage extends JDialog {

	/**
	 * 学生选课管理
	 * 对学生选择课程进行管理
	 */
	private static final long serialVersionUID = 1L;
	private ContentPane contentPane;
	private JButton addBtn;
	private JButton deleteBtn;
	private JButton changeBtn;
	private JButton refreshBtn;
	private final String tableName = "student_course";
	private Dao dao;
	private DefaultTableModel model;
	private BtnListener btnLis;

	public StudentCourseManage() {
		// TODO 自动生成的构造函数存根
		super();
		dao = new Dao(tableName);
		InitContentPane();
		InitData();
	}
	
	private void InitContentPane() {
		this.setTitle("学生选课管理");
		setIconImage(Toolkit.getDefaultToolkit().createImage(Login.getImgURL()));
		setSize(700,430);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setLocation(new Point(0, 0));
		setLocationRelativeTo(null);
		
		contentPane = new ContentPane(new Dimension(getWidth(), getHeight()+30));
		setContentPane(contentPane);
		
		addBtn =  new JButton("添加");
		deleteBtn = new JButton("删除");
		changeBtn = new JButton("修改");
		refreshBtn = new JButton("刷新");
		
		contentPane.setAddBtn(addBtn);
		contentPane.setChangeBtn(changeBtn);
		contentPane.setDeleteBtn(deleteBtn);
		contentPane.setRefreshBtn(refreshBtn);
		
		btnLis = new BtnListener();
		addBtn.addActionListener(btnLis);
		deleteBtn.addActionListener(btnLis);
		changeBtn.addActionListener(btnLis);
		refreshBtn.addActionListener(btnLis);
	}
	
	private void InitData() {
		List list = dao.getList();
		Vector title = new ConnXupt().getComment(tableName);
		model = new DefaultTableModel(title,list.size());
		for(int i = 0; i < list.size(); i++) {
			StudentCourseModel SCM = (StudentCourseModel)list.get(i);
			model.setValueAt(SCM.getId()+"",i, 0);
			model.setValueAt(SCM.getStudentId(), i, 1);
			model.setValueAt(SCM.getCourseId(), i, 2);
			model.setValueAt(SCM.getSY(), i, 3);
			model.setValueAt(SCM.getST(), i, 4);
			model.setValueAt(SCM.getTime(), i, 5);
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
				InitData();
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
					InitData();
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "删除失败");
				}
			}else if(e.getSource() == changeBtn) {
				int i = contentPane.getSelectRow();
				if(i < 0) {
					JOptionPane.showMessageDialog(getContentPane(), "请选择一行");
					return;
				}
				StudentCourseModel SCM = new StudentCourseModel();
				SCM.setId(Integer.parseInt(contentPane.getValueAt(i, 0)));
				SCM.setStudentId(contentPane.getValueAt(i, 1));
				SCM.setCourseId(contentPane.getValueAt(i, 2));
				SCM.setSY(contentPane.getValueAt(i, 3));
				SCM.setST(contentPane.getValueAt(i, 4));
				SCM.setTime(contentPane.getValueAt(i, 5));
				textJDialog = new TextJDialog(SCM);
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
		private StudentCourseModel data;
		private JComboBox studentBox;
		private JComboBox courseBox;
		private JComboBox SYBox;
		private JComboBox STBox;
		private JTextField timeText;

		public TextJDialog() {
			super();
			setTitle("添加年级信息");
			button = new JButton("添加");
			InitJDialog();
		}
		
		public TextJDialog(StudentCourseModel data) {
			super();
			setTitle("修改年级信息");
			button = new JButton("修改");
			this.data = data;
			InitJDialog();
			InitData();
		}
		
		private void InitJDialog() {
			Vector column = new ConnXupt().getComment(tableName);
			setSize(250, 30+(column.size()+1)*(30+5));
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			setIconImage(Toolkit.getDefaultToolkit().createImage(Login.getImgURL()));
			setModal(true);
			setLocation(new Point(0, 0));
			setLocationRelativeTo(null);
			
			JPanel contentPane = new JPanel();
			contentPane.setLayout(null);
			
			for(int i = 1; i < column.size(); i++) {
				JLabel lab = new JLabel(column.get(i)+"：");
				lab.setBounds(15,15+30*(i-1)+(i-1)*5,80,30);
				contentPane.add(lab);
			}
			
			studentBox = new JComboBox();
			studentBox.setBounds(100,15,120,30);
			courseBox = new JComboBox();
			courseBox.setBounds(100,50,120,30);
			SYBox = new JComboBox();
			SYBox.setBounds(100,85,120,30);
			STBox = new JComboBox();
			STBox.setBounds(100,120,120,30);
			timeText = new JTextField();
			DateChooser dateChooser = DateChooser.getInstance("yyyy-MM-dd");
			dateChooser.register(timeText);
			timeText.setBounds(100,155,120,30);
			button.setBounds((getWidth() - 80)/2, 190, 80, 30);
			
			contentPane.add(studentBox);
			contentPane.add(courseBox);
			contentPane.add(SYBox);
			contentPane.add(STBox);
			contentPane.add(timeText);
			contentPane.add(button);
			setContentPane(contentPane);
			
			button.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					StudentCourseModel SCM = new StudentCourseModel();
					tools tool = new tools();
					SCM.setStudentId(tool.Split(studentBox.getSelectedItem()+""));
					SCM.setCourseId(tool.Split(courseBox.getSelectedItem()+""));
					SCM.setSY(tool.Split(SYBox.getSelectedItem()+""));
					SCM.setST(tool.Split(STBox.getSelectedItem()+""));
					SCM.setTime(timeText.getText());
					if(SCM.getStudentId().length() == 0 || SCM.getCourseId().length() == 0 || SCM.getSY().length() == 0 || 
							SCM.getST().length() == 0 || SCM.getTime().length() == 0) {
						JOptionPane.showMessageDialog(getContentPane(), "以上五项都是必填项，不能遗漏");
						return ;
					}
					if(button.getText().equals("添加")) {
						if(dao.insertdata(SCM)) {
							JOptionPane.showMessageDialog(getContentPane(), "添加成功");
							dispose();
						}else {
							JOptionPane.showMessageDialog(getContentPane(), "添加失败");
						}
					}else {
						if(dao.updatedata(SCM, data.getId()+"")) {
							JOptionPane.showMessageDialog(getContentPane(), "修改成功");
							dispose();
						}else {
							JOptionPane.showMessageDialog(getContentPane(), "修改失败");
							InitData();
						}
					}
				}
			});
			
			List list = new Dao("student").getIdList();
			for(int i = 0; i < list.size(); i++) {
				studentBox.addItem(list.get(i));
			}
			list.clear();
			list = new Dao("offering_courses").getIdList();
			for(int i = 0; i < list.size(); i++) {
				courseBox.addItem(list.get(i));
			}
			list.clear();
			list = new Dao("school_year").getIdList();
			for(int i = 0; i< list.size(); i++) {
				SYBox.addItem(list.get(i));
			}
			list.clear();
			list = new Dao("school_trem").getIdList();
			for(int i = 0; i < list.size(); i++) {
				STBox.addItem(list.get(i));
			}
		}
		
		private void InitData() {
			if(data != null) {
				tools tool = new tools();
				tool.setSelectedItem(studentBox, data.getStudentId());
				tool.setSelectedItem(courseBox, data.getCourseId());
				tool.setSelectedItem(SYBox, data.getSY());
				tool.setSelectedItem(STBox, data.getST());
				timeText.setText(data.getTime());
			}
		}
	}
}
