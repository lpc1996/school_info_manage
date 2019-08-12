package com.lpc.iframe;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import com.lpc.dao.TeacherDao;
import com.lpc.mode.BaseInfoModel;
import com.lpc.mode.BaseInfoTable;
import com.lpc.mode.TeacherModel;

public class TeacherManage extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List teacherInfoList;
	private TeacherDao teacherDao;
	private JTextField collegeText;
	private JTextField departmentText;
	private JTextField levelText;
	private JTextField educationText;
	private JTextField yearText;
	private JButton addBtn;
	private JButton deleteBtn;
	private JButton changeBtn;
	private JTextJdialog textDialog;
	private JButton refrshBtn;
	private BaseInfoTable table;
	private JTextField searchText;
	private JButton searchBtn;

	public TeacherManage() {
		// TODO Auto-generated constructor stub
		super();
		teacherDao = new TeacherDao();
		InitJDialog();
		setOption();
	}
	
	public void InitJDialog() {
		setTitle("��ְ������");
		setSize(700, 505);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().createImage(Login.getImgURL()));
		setModal(true);
		setLocation(new Point(0, 0));
		setLocationRelativeTo(null);
		JPanel contentPane = new JPanel();
		JScrollPane teacherInfoJS = new JScrollPane();
		table = new BaseInfoTable("teacher");
		teacherInfoJS.setViewportView(table);
		teacherInfoJS.setBounds(new Rectangle(15, 15, 655, 300));
		
		JLabel collegeLab = new JLabel("����ѧԺ:");
		collegeLab.setBounds(new Rectangle(15, 320, 80, 30));
		collegeText = new JTextField();
		collegeText.setBounds(new Rectangle(100, 320, 120, 30));
		JLabel departmentLab = new JLabel("����ϵ/����");
		departmentLab.setBounds(new Rectangle(225, 320, 80, 30));
		departmentText = new JTextField();
		departmentText.setBounds(new Rectangle(310, 320, 120, 30));
		JLabel levelLab = new JLabel("ְ�ƣ�");
		levelLab.setBounds(new Rectangle(440, 320, 80, 30));
		levelText = new JTextField();
		levelText.setBounds(new Rectangle(525, 320, 120, 30));
		JLabel educationLab = new JLabel("ѧλ��");
		educationLab.setBounds(new Rectangle(15, 355, 80, 30));
		educationText = new JTextField();
		educationText.setBounds(new Rectangle(100, 355, 120, 30));
		JLabel yearLab = new JLabel("��ְʱ��");
		yearLab.setBounds(new Rectangle(225, 355, 80, 30));
		yearText = new JTextField();
		yearText.setBounds(new Rectangle(310, 355, 120, 30));
		
		JLabel searchLab = new JLabel("������Ҫ��ѯ����ʦ�Ĺ���\\������");
		searchLab.setBounds(new Rectangle(15, 390, 200, 30));
		searchText = new JTextField();
		searchText.setBounds(220,390,200,30);
		searchBtn = new JButton("��ѯ");
		searchBtn.setBounds(425, 390, 80, 30);
		
		addBtn = new JButton("���");
		addBtn.setBounds(15, 425, 80, 30);
		deleteBtn = new JButton("ɾ��");
		deleteBtn.setBounds(new Rectangle(100, 425, 80, 30));
		changeBtn = new JButton("�޸�");
		changeBtn.setBounds(new Rectangle(185, 425, 80, 30));
		refrshBtn = new JButton("ˢ��");
		refrshBtn.setBounds(new Rectangle(270, 425, 80, 30));
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String id = (String)table.getValueAt(table.getSelectedRow(),0);
				teacherInfoList = teacherDao.getList();
				int i = 0;
				for(; i < teacherInfoList.size() ; i++) {
					TeacherModel tea = (TeacherModel)teacherInfoList.get(i);
					if(tea.getId().equals(id)){
						break;
					}
				}
				if(i >= 0 && i < teacherInfoList.size()) {
					TeacherModel teacher = (TeacherModel)teacherInfoList.get(i);
					collegeText.setText(teacher.getCollege());
					departmentText.setText(teacher.getDepartment());
					levelText.setText(teacher.getLevel());
					educationText.setText(teacher.getEducation());
					yearText.setText(teacher.getYear());
				}
			}
		});
		
		contentPane.setLayout(null);
		contentPane.add(teacherInfoJS);
		contentPane.add(collegeLab);
		contentPane.add(collegeText);
		contentPane.add(departmentLab);
		contentPane.add(departmentText);
		contentPane.add(levelLab);
		contentPane.add(levelText);
		contentPane.add(educationLab);
		contentPane.add(educationText);
		contentPane.add(yearLab);
		contentPane.add(yearText);
		contentPane.add(searchLab);
		contentPane.add(searchText);
		contentPane.add(searchBtn);
		contentPane.add(addBtn);
		contentPane.add(deleteBtn);
		contentPane.add(changeBtn);
		contentPane.add(refrshBtn);
		contentPane.setBorder(BorderFactory.createTitledBorder("����һ�廯"));
		setContentPane(contentPane);
		
		BtnListener btnListener = new BtnListener();
		searchBtn.addActionListener(btnListener);
		addBtn.addActionListener(btnListener);
		deleteBtn.addActionListener(btnListener);
		changeBtn.addActionListener(btnListener);
		refrshBtn.addActionListener(btnListener);
	}
	
	
	private void setOption() {
		if(Integer.parseInt(Login.getLoginModel().getLimit()) < 9) {
			addBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
		}
		if(Integer.parseInt(Login.getLoginModel().getLimit()) < 7 ) {
			changeBtn.setEnabled(false);
		}
	}
	
	public class BtnListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == addBtn) {
				textDialog = new JTextJdialog("��ӽ�ְ����Ϣ", "���","teacher");
				textDialog.setVisible(true);
				table.InitData();
			}else if(e.getSource() == deleteBtn){
				int i = table.getSelectedRow();
				if(i >= 0) {
					if(JOptionPane.showConfirmDialog(getContentPane(), "ȷ��Ҫɾ��"+table.getValueAt(i, 1)+"��","ɾ��",JOptionPane.YES_NO_OPTION) == 1) {
						return;
					}
					String id = (String)table.getValueAt(i,0);
					if(teacherDao.deleteBaseInfo(id) && teacherDao.deleteTeacher(id)) {
						JOptionPane.showMessageDialog(getContentPane(), "�ɹ�");
						table.InitData();
					}else {
						JOptionPane.showMessageDialog(getContentPane(), "ʧ��");
					}
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "��ѡ��һ�У�");
					return;
				}
			}else if(e.getSource() == refrshBtn) {
				table.InitData();
			}else if(e.getSource() == changeBtn) {
				int i = table.getSelectedRow();
				TeacherModel teacher = null;
				BaseInfoModel base = new BaseInfoModel();
				if(i >= 0) {
					base.setId((String)table.getValueAt(i, 0));
					base.setName((String)table.getValueAt(i, 1));
					base.setFormarName((String)table.getValueAt(i, 2));
					base.setSex((String)table.getValueAt(i, 3));
					base.setAge(Integer.parseInt(table.getValueAt(i, 4)+""));
					base.setNativePlace((String)table.getValueAt(i, 5));
					base.setIDCARDTYPE((String)table.getValueAt(i, 6));
					base.setIDCARDNUM((String)table.getValueAt(i, 7));
					base.setTel((String)table.getValueAt(i, 8));
					base.setType("teacher");
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "��ѡ��һ�У�");
					return;
				}
				int j = 0;
				for(; j < teacherInfoList.size(); j++) {
					teacher = (TeacherModel)teacherInfoList.get(j);
					if(teacher.getId().equals(base.getId())) {
						break;
					}
				}
				if(j >= teacherInfoList.size()) {
					JOptionPane.showMessageDialog(getContentPane(), "��������");
					return ;
				}
				textDialog = new JTextJdialog("�޸Ľ�ְ����Ϣ", "�޸�",teacher,base);
				textDialog.setVisible(true);
				table.InitData();
			}else if(e.getSource() == searchBtn){
				String search = searchText.getText();
				if(search.equals("")) {
					JOptionPane.showMessageDialog(getContentPane(), "������Ҫ��ѯ�Ľ�ʦ�������򹤺�");
					return;
				}
				int i = 0;
				for(; i < table.getRowCount(); i++) {
					if(search.equals(table.getValueAt(i, 0)) || search.equals(table.getValueAt(i, 1))) {
						table.setRowSelectionInterval(i, i);
						break;
					}
				}
				if(i == table.getRowCount()) {
					JOptionPane.showMessageDialog(getContentPane(), "�Ҳ���"+search);
				}
			}
		}
		
	}

}

