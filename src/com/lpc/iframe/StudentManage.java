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
import com.lpc.dao.StudentDao;
import com.lpc.mode.BaseInfoModel;
import com.lpc.mode.BaseInfoTable;
import com.lpc.mode.StudentModel;

public class StudentManage extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField collegeText;
	private JTextField deparmentText;
	private JTextField majorText;
	private JTextField gradeText;
	private JTextField classText;
	private JTextField cultureText;
	private JTextField typeText;
	private JTextField educationText;
	private JTextField yearText;
	private JButton addBtn;
	private JButton changeBtn;
	private JButton refreshBtn;
	private JButton deleteBtn;
	private JTextField searchText;
	private JButton searchBtn;
	private StudentDao studentDao;
	private BaseInfoTable table;
	private StudentModel student;
	private List studentList;
	public JTextJdialog text;

	public StudentManage() {
		// TODO Auto-generated constructor stub
		super();
		studentDao = new StudentDao();
		studentList = studentDao.getList("");
		InitJDialog();
	}
	
	private void InitJDialog() {
		setTitle("ѧ����Ϣ����");
		setSize(700, 550);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().createImage(Login.getImgURL()));
		setModal(true);
		setLocation(new Point(0, 0));
		setLocationRelativeTo(null);
		
		JScrollPane baseInfoJS = new JScrollPane();
		baseInfoJS.setBounds(new Rectangle(15, 15, 655, 300));
		table = new BaseInfoTable("student");
		baseInfoJS.setViewportView(table);
		JLabel collegeLab = new JLabel("����ѧԺ��");
		collegeLab.setBounds(new Rectangle(15, 320, 80, 30));
		collegeText = new JTextField();
		collegeText.setBounds(100,320,120,30);
		JLabel departmentLab = new JLabel("����ϵ/����");
		departmentLab.setBounds(230,320,80,30);
		deparmentText = new JTextField();
		deparmentText.setBounds(new Rectangle(315, 320, 120, 30));
		JLabel majorLab = new JLabel("רҵ��");
		majorLab.setBounds(445,320,80,30);
		majorText = new JTextField();
		majorText.setBounds(530,320,120,30);
		JLabel gradeLab = new JLabel("�꼶��");
		gradeLab.setBounds(15,355,80,30);
		gradeText = new JTextField();
		gradeText.setBounds(new Rectangle(100, 355, 120, 30));
		JLabel classLab = new JLabel("�༶��");
		classLab.setBounds(new Rectangle(230, 355, 80, 30));
		classText = new JTextField();
		classText.setBounds(315,355,120,30);
		JLabel cultureLab = new JLabel("������Σ�");
		cultureLab.setBounds(new Rectangle(445, 355, 80, 30));
		cultureText = new JTextField();
		cultureText.setBounds(new Rectangle(530, 355, 120, 30));
		JLabel typeLab = new JLabel("ѧ�����");
		typeLab.setBounds(new Rectangle(15, 390, 80, 30));
		typeText = new JTextField();
		typeText.setBounds(new Rectangle(100, 390, 120, 30));
		JLabel educationLab = new JLabel("ѧ����");
		educationLab.setBounds(new Rectangle(230, 390, 80, 30));
		educationText = new JTextField();
		educationText.setBounds(new Rectangle(315, 390, 120, 30));
		JLabel yearLab = new JLabel("��ѧʱ�䣺");
		yearLab.setBounds(new Rectangle(445, 390, 80, 30));
		yearText = new JTextField();
		yearText.setBounds(new Rectangle(530, 390, 120, 30));
		
		JLabel searchLab = new JLabel("������ѧ��ѧ�Ż�������");
		searchLab.setBounds(15,425,160,30);
		searchText = new JTextField();
		searchText.setBounds(180,425,200,30);
		searchBtn =  new JButton("��ѯ");
		searchBtn.setBounds(385,425,80,30);
		
		addBtn = new JButton("���");
		addBtn.setBounds(15, 460, 80, 30);
		changeBtn = new JButton("�޸�");
		changeBtn.setBounds(100, 460, 80, 30);
		deleteBtn = new JButton("ɾ��");
		deleteBtn.setBounds(185, 460, 80, 30);
		refreshBtn = new JButton("ˢ��");
		refreshBtn.setBounds(270, 460, 80, 30);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createTitledBorder("����һ�廯"));
		contentPane.setLayout(null);
		contentPane.add(baseInfoJS);
		contentPane.add(collegeLab);
		contentPane.add(collegeText);
		contentPane.add(departmentLab);
		contentPane.add(deparmentText);
		contentPane.add(majorLab);
		contentPane.add(majorText);
		contentPane.add(gradeLab);
		contentPane.add(gradeText);
		contentPane.add(classLab);
		contentPane.add(classText);
		contentPane.add(cultureLab);
		contentPane.add(cultureText);
		contentPane.add(typeLab);
		contentPane.add(typeText);
		contentPane.add(educationLab);
		contentPane.add(educationText);
		contentPane.add(yearLab);
		contentPane.add(yearText);
		contentPane.add(searchLab);
		contentPane.add(searchText);
		contentPane.add(searchBtn);
		contentPane.add(addBtn);
		contentPane.add(changeBtn);
		contentPane.add(deleteBtn);
		contentPane.add(refreshBtn);
		setContentPane(contentPane);
		
		BtnListener btnLis = new BtnListener();
		searchBtn.addActionListener(btnLis);
		addBtn.addActionListener(btnLis);
		deleteBtn.addActionListener(btnLis);
		changeBtn.addActionListener(btnLis);
		refreshBtn.addActionListener(btnLis);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String id = (String)table.getValueAt(table.getSelectedRow(),0);
				int i = 0;
				for(; i < studentList.size(); i++) {
					student = (StudentModel)studentList.get(i);
					if(student.getId().equals(id)) {
						break;
					}
				}
				if(i >= 0 && i < studentList.size()) {
					collegeText.setText(student.getCollege());
					deparmentText.setText(student.getDepartment());
					majorText.setText(student.getMajor());
					gradeText.setText(student.getGrade());
					classText.setText(student.getClassId());
					cultureText.setText(student.getCulture_level());
					typeText.setText(student.getType());
					educationText.setText(student.getEducation());
					yearText.setText(student.getYear());
				}
			}
		});
	}
	
	public class BtnListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == addBtn) {
				text = new JTextJdialog("���ѧ����Ϣ", "���", "student");
				text.setVisible(true);
				table.InitData();
				studentList = studentDao.getList("");
			}else if(e.getSource() == refreshBtn) {
				table.InitData();
				studentList = studentDao.getList("");
			}else if(e.getSource() == deleteBtn) {
				int i = table.getSelectedRow();
				if(i < 0) {
					JOptionPane.showMessageDialog(getContentPane(), "��ѡ��һ��");
					return;
				}
				if(JOptionPane.showConfirmDialog(getContentPane(), "ȷ��Ҫɾ��"+table.getValueAt(i, 1)+"��","ɾ��",JOptionPane.YES_NO_OPTION) == 1) {
					return;
				}
				String id = (String)table.getValueAt(i, 0);
				if(studentDao.deleteInfo(id) && studentDao.deleteBaseInfo(id)) {
					JOptionPane.showMessageDialog(getContentPane(), "�ɹ�");
					studentList = studentDao.getList("");
					table.InitData();
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "ʧ��");
				}
			}else if(e.getSource() == changeBtn) {
				int i = table.getSelectedRow();
				StudentModel student = null;
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
					base.setType("student");
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "��ѡ��һ��");
					return ;
				}
				for(int j = 0; j < studentList.size(); j++) {
					student = (StudentModel)studentList.get(j);
					if(student.getId().equals(base.getId())) {
						break;
					}
				}
				if(student == null) {
					JOptionPane.showMessageDialog(getContentPane(), "��������");
					return;
				}
				text = new JTextJdialog("ѧ����Ϣ�޸�", "�޸�", student, base);
				text.setVisible(true);
				table.InitData();
				studentList = studentDao.getList("");
			}else if(e.getSource() == searchBtn) {
				String search = searchText.getText();
				if(search.equals("")) {
					JOptionPane.showMessageDialog(getContentPane(), "������Ҫ��ѯ��ѧ����������ѧ�ţ�");
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
