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
import com.lpc.mode.CourseModel;
import com.lpc.util.ConnXupt;
import com.lpc.util.tools;

public class courseManage extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dao dao;
	private ContentPane contentPane;
	private JButton addBtn;
	private JButton deleteBtn;
	private JButton changeBtn;
	private JButton refreshBtn;
	private List courseList;
	private DefaultTableModel model;

	public courseManage() {
		// TODO �Զ����ɵĹ��캯�����
		super();
		dao = new Dao("course");
		InitJPane();
		InitData();
	}
	
	private void InitJPane() {
		setTitle("ϵ/����Ϣ����");
		setIconImage(Toolkit.getDefaultToolkit().createImage(Login.getImgURL()));
		setSize(600,330);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setLocation(new Point(0, 0));
		setLocationRelativeTo(null);
		
		contentPane = new ContentPane(new Dimension(getWidth(), getHeight()+30));
		setContentPane(contentPane);
		addBtn = new JButton("���");
		changeBtn = new JButton("�޸�");
		deleteBtn = new JButton("ɾ��");
		refreshBtn = new JButton("ˢ��");
		
		BtnListener btnLis = new BtnListener();
		addBtn.addActionListener(btnLis);
		changeBtn.addActionListener(btnLis);
		deleteBtn.addActionListener(btnLis);
		refreshBtn.addActionListener(btnLis);
		
		contentPane.setAddBtn(addBtn);
		contentPane.setChangeBtn(changeBtn);
		contentPane.setDeleteBtn(deleteBtn);
		contentPane.setRefreshBtn(refreshBtn);
	}
	
	private void InitData() {
		courseList = dao.getList();
		Vector title = new ConnXupt().getComment("course");
		model = new DefaultTableModel(title, courseList.size());
		for(int i = 0; i < courseList.size(); i++) {
			CourseModel c = (CourseModel)courseList.get(i);
			model.setValueAt(c.getId(), i, 0);
			model.setValueAt(c.getName(), i, 1);
			model.setValueAt(c.getCollegeId(), i, 2);
			model.setValueAt(c.getDepartmentId(), i, 3);
			model.setValueAt(c.getType(), i, 4);
			model.setValueAt(c.getCredit()+"", i, 5);
		}
		contentPane.setTableModel(model);
	}
	
	public class BtnListener implements ActionListener{

		private TextJDialog textJDialog;

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == addBtn) {
				textJDialog = new TextJDialog("���ϵ/����Ϣ");
				textJDialog.setVisible(true);
				InitData();
			}else if(e.getSource() == deleteBtn) {
				int i = contentPane.getSelectRow();
				if(i < 0 ) {
					JOptionPane.showMessageDialog(getContentPane(), "��ѡ��һ��");
					return;
				}
				String id = contentPane.getValueAt(i, 0)+"";
				
				if(JOptionPane.showConfirmDialog(getContentPane(), "ȷ��Ҫɾ��"+id+"��", "ɾ��ȷ��", JOptionPane.YES_NO_OPTION) == 1) {
					return;
				}
				if(dao.deleteData(id)) {
					JOptionPane.showMessageDialog(getContentPane(), "ɾ���ɹ�");
					InitData();
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "ɾ��ʧ��");
				}
			}else if(e.getSource() == changeBtn) {
				int i = contentPane.getSelectRow();
				if(i < 0 ) {
					JOptionPane.showMessageDialog(getContentPane(), "��ѡ��һ��");
					return;
				}
				CourseModel course = new CourseModel();
				course.setId(contentPane.getValueAt(i, 0));
				course.setName(contentPane.getValueAt(i, 1));
				course.setCollegeId(contentPane.getValueAt(i, 2));
				course.setDepartmentId(contentPane.getValueAt(i, 3));
				course.setType(contentPane.getValueAt(i, 4));
				course.setCredit(Double.parseDouble(contentPane.getValueAt(i, 5)));
				
				textJDialog = new TextJDialog("�޸Ŀγ���Ϣ",course);
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
		private JTextField nameText;
		private CourseModel course;
		private JComboBox collegeIdBox;
		private JComboBox departmentBox;
		private JComboBox typeBox;
		private JTextField creditText;

		public TextJDialog(String title) {
			super();
			this.setTitle(title);
			button = new JButton();
			button.setText("���");
			InitJDialog();
		}
		
		public TextJDialog(String title, CourseModel course) {
			super();
			this.setTitle(title);
			this.course = course;
			button = new JButton();
			button.setText("�޸�");
			InitJDialog();
			InitData();
		}
		
		private void InitJDialog() {
			setSize(250, 300);
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			setIconImage(Toolkit.getDefaultToolkit().createImage(Login.getImgURL()));
			setModal(true);
			setLocation(new Point(0, 0));
			setLocationRelativeTo(null);
			JPanel contentPane = new JPanel();
			Vector title = new ConnXupt().getComment("course");
			for(int i = 0; i < title.size(); i++ ) {
				JLabel lab = new JLabel(title.get(i)+"��");
				lab.setBounds(15, 15+30*i+i*5, 80, 30);
				contentPane.add(lab);
			}
			idText = new JTextField();
			idText.setBounds(100,15,120,30);
			nameText = new JTextField();
			nameText.setBounds(100,50,120,30);
			collegeIdBox = new JComboBox();
			collegeIdBox.setBounds(100,85,120,30);
			departmentBox = new JComboBox();
			departmentBox.setBounds(100,120,120,30);
			typeBox = new JComboBox();
			typeBox.setBounds(100,155,120,30);
			creditText = new JTextField();
			creditText.setBounds(100,190,120,30);
			button.setBounds((getWidth() - 80)/2,225,80,30);
			
			contentPane.setLayout(null);
			contentPane.add(idText);
			contentPane.add(nameText);
			contentPane.add(collegeIdBox);
			contentPane.add(departmentBox);
			contentPane.add(typeBox);
			contentPane.add(creditText);
			contentPane.add(button);
			setContentPane(contentPane);
			
			button.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					CourseModel data = new CourseModel();
					tools tool = new tools();
					data.setId(idText.getText());
					data.setName(nameText.getText());
					data.setCollegeId(tool.Split(collegeIdBox.getSelectedItem()+""));
					data.setDepartmentId(tool.Split(departmentBox.getSelectedItem()+""));
					data.setType(typeBox.getSelectedItem()+"");
					try {
						data.setCredit(Double.parseDouble(creditText.getText()));
					} catch (Exception e1) {
						// TODO �Զ����ɵ� catch ��
						JOptionPane.showMessageDialog(null, "��������ȷ��ѧ��");
						return;
					}
					
					if(data.getId().length() == 0 || data.getName().length() == 0 || data.getCollegeId().length() == 0 || data.getDepartmentId().length() == 0 || data.getType().length() == 0 || data.getCredit() == 0) {
						JOptionPane.showMessageDialog(getContentPane(), "��������Ϊ�������������©Ӵ");
						return;
					}
					
					if(button.getText().equals("���")) {
						
						if(dao.insertdata(data)) {
							JOptionPane.showMessageDialog(getContentPane(), "��ӳɹ�");
							dispose();
						}else {
							JOptionPane.showMessageDialog(getContentPane(), "���ʧ��");
						}
					}else if(button.getText().equals("�޸�")) {
						
						if(dao.updatedata(data, TextJDialog.this.course.getId())) {
							JOptionPane.showMessageDialog(getContentPane(), "�޸ĳɹ�");
							dispose();
						}else {
							JOptionPane.showMessageDialog(getContentPane(), "�޸�ʧ��");
							InitData();
						}
					}
				}
			});
			
			List collegeList = new Dao("college").getIdList();
			for(int i = 0; i < collegeList.size() ; i++) {
				collegeIdBox.addItem(collegeList.get(i));
			}
			List departmentList = new Dao("department").getIdList();
			for(int i = 0 ; i < departmentList.size(); i++) {
				departmentBox.addItem(departmentList.get(i));
			}
			typeBox.addItem("���޿�");
			typeBox.addItem("ѡ�޿�");
		}
		
		private void InitData() {
			if(course != null) {
				tools tool = new tools();
				idText.setText(course.getId());
				nameText.setText(course.getName());
				tool.setSelectedItem(collegeIdBox, course.getCollegeId());
				tool.setSelectedItem(departmentBox, course.getDepartmentId());
				tool.setSelectedItem(typeBox, course.getType());
				creditText.setText(course.getCredit()+"");
			}
		}
	}
}
