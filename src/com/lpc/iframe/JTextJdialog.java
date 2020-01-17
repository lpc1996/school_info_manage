package com.lpc.iframe;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.lpc.dao.Dao;
import com.lpc.dao.StudentDao;
import com.lpc.dao.TeacherDao;
import com.lpc.mode.BaseInfoModel;
import com.lpc.mode.StudentModel;
import com.lpc.mode.TeacherModel;
import com.lpc.util.DateChooser;
import com.lpc.util.tools;

public class JTextJdialog extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TeacherModel teacher;
	private BaseInfoModel baseInfo;
	private JButton button;
	private JTextField idText;
	private JTextField nameText;
	private JTextField formarNameText;
	private JComboBox sexBox;
	private JTextField ageText;
	private JTextField nativePlaceText;
	private JComboBox IDCARDTYPECom;
	private JTextField IDCARDNUMText;
	private JTextField telText;
	private JTextField levelText;
	private JTextField educationText;
	private JTextField yearText;
	private TeacherDao teacherDao;
	private StudentModel student;
	private JPanel contentPane;
	private JLabel yearLab;
	private JTextField cultureText;
	private JTextField typeText;
	private StudentDao studentDao;
	private DateChooser dateChooser;
	private JComboBox collegeBox;
	private JComboBox departmentBox;
	private JComboBox majorBox;
	private JComboBox gradeBox;
	private JComboBox classBox;
	private BaseInfoModel base;
	private TeacherModel teacherInfo;
	private StudentModel studentInfo;
	
	public JTextJdialog(String title,String text,String type) {
		super();
		teacherDao = new TeacherDao();
		this.setTitle(title);
		button = new JButton(text);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		if(type.equals("student")) {
			InitStudentJDialog();
			button.addActionListener(new StudentBtnListener());
		}else if(type.equals("teacher")) {
			InitTeacherJDialog();
			button.addActionListener(new TeacherBtnListener());
		}
	}

	public JTextJdialog(String title,String text,TeacherModel teacher,BaseInfoModel base) {
		super();
		teacherDao = new TeacherDao();
		this.setTitle(title);
		this.teacher = teacher;
		this.baseInfo = base;
		button = new JButton(text);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		InitTeacherJDialog();
		InitTeacherData();
		button.addActionListener(new TeacherBtnListener());
	}
	
	public JTextJdialog(String title,String text,StudentModel studentModel,BaseInfoModel base) {
		super();
		teacherDao = new TeacherDao();
		this.setTitle(title);
		this.student = studentModel;
		this.baseInfo = base;
		button = new JButton(text);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		InitStudentJDialog();
		InitStudentData();
		button.addActionListener(new StudentBtnListener());
	}
	
	private void InitBaseJDialog() {
		setModal(true);
		setLocation(new Point(0, 0));
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().createImage(Login.getImgURL()));
		JLabel idLab = new JLabel("学/工号*：");
		idLab.setBounds(new Rectangle(15, 15, 80, 30));
		idText = new JTextField();
		idText.setBounds(new Rectangle(100, 15, 120, 30));
		JLabel nameLab = new JLabel("姓名*：");
		nameLab.setBounds(new Rectangle(230, 15, 80, 30));
		nameText = new JTextField();
		nameText.setBounds(new Rectangle(315, 15, 120, 30));
		JLabel formarNameLab = new JLabel("曾用名：");
		formarNameLab.setBounds(new Rectangle(15, 50, 80, 30));
		formarNameText = new JTextField();
		formarNameText.setBounds(new Rectangle(100, 50, 120, 30));
		JLabel sexLab = new JLabel("性别*：");
		sexLab.setBounds(new Rectangle(230, 50, 80, 30));
		sexBox = new JComboBox();
		sexBox.setBounds(new Rectangle(315, 50, 120, 30));
		sexBox.addItem("男");
		sexBox.addItem("女");
		JLabel ageLab = new JLabel("年龄：");
		ageLab.setBounds(15,85,80,30);
		ageText = new JTextField();
		ageText.setBounds(new Rectangle(100, 85, 120, 30));
		JLabel nativePlaceLab = new JLabel("籍贯*：");
		nativePlaceLab.setBounds(new Rectangle(230, 85, 80, 30));
		nativePlaceText = new JTextField();
		nativePlaceText.setBounds(new Rectangle(315, 85, 120, 30));
		JLabel IDCARDTYPELab = new JLabel("证件类型*：");
		IDCARDTYPELab.setBounds(new Rectangle(15, 120, 80, 30));
		IDCARDTYPECom = new JComboBox();
		IDCARDTYPECom.setBounds(100,120,120,30);
		IDCARDTYPECom.addItem("居民身份证");
		JLabel IDCARDNUMLab = new JLabel("证件号码*：");
		IDCARDNUMLab.setBounds(new Rectangle(230, 120, 80, 30));
		IDCARDNUMText = new JTextField();
		IDCARDNUMText.setBounds(new Rectangle(315, 120, 120, 30));
		JLabel telLab = new JLabel("电话号码：");
		telLab.setBounds(new Rectangle(15, 155, 80, 30));
		telText = new JTextField();
		telText.setBounds(100,155,120,30);
		JLabel collegeLab = new JLabel("所属学院*：");
		collegeLab.setBounds(new Rectangle(230, 155, 80, 30));
		collegeBox = new JComboBox();
		collegeBox.setBounds(new Rectangle(315, 155, 120, 30));
		JLabel departmentLab = new JLabel("所属系/部*：");
		departmentLab.setBounds(new Rectangle(15, 190, 80, 30));
		departmentBox = new JComboBox();
		departmentBox.setBounds(new Rectangle(100, 190, 120, 30));
		
		contentPane.add(idLab);
		contentPane.add(idText);
		contentPane.add(nameLab);
		contentPane.add(nameText);
		contentPane.add(formarNameLab);
		contentPane.add(formarNameText);
		contentPane.add(sexLab);
		contentPane.add(sexBox);
		contentPane.add(ageLab);
		contentPane.add(ageText);
		contentPane.add(nativePlaceLab);
		contentPane.add(nativePlaceText);
		contentPane.add(IDCARDTYPELab);
		contentPane.add(IDCARDTYPECom);
		contentPane.add(IDCARDNUMLab);
		contentPane.add(IDCARDNUMText);
		contentPane.add(telLab);
		contentPane.add(telText);
		contentPane.add(collegeLab);
		contentPane.add(collegeBox);
		contentPane.add(departmentLab);
		contentPane.add(departmentBox);
		setContentPane(contentPane);
		contentPane.add(button);
		
		List list = new Dao("college").getIdList();
		for(int i = 0 ;i < list.size(); i++) {
			collegeBox.addItem(list.get(i));
		}
		list = new Dao("department").getIdList();
		for(int i = 0; i < list.size(); i++) {
			departmentBox.addItem(list.get(i));
		}
	}
	
	public void InitTeacherJDialog() {
		setSize(465, 360);
		JLabel levelLab = new JLabel("职称*：");
		levelLab.setBounds(new Rectangle(230, 190, 80, 30));
		levelText = new JTextField();
		levelText.setBounds(new Rectangle(315, 190, 120, 30));
		JLabel educationLab = new JLabel("学位*：");
		educationLab.setBounds(new Rectangle(15, 225, 80, 30));
		educationText = new JTextField();
		educationText.setBounds(new Rectangle(100, 225, 120, 30));
		button.setBounds((getWidth()-80)/2, 260, 80, 30);
		yearLab = new JLabel("入职时间：");
		yearLab.setBounds(230,225,80,30);
		yearText = new JTextField();
		dateChooser = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser.register(yearText);
		yearText.setBounds(new Rectangle(315, 225, 120, 30));
		
		contentPane.add(levelLab);
		contentPane.add(levelText);
		contentPane.add(educationLab);
		contentPane.add(educationText);
		contentPane.add(yearLab);
		contentPane.add(yearText);
		
		InitBaseJDialog();
	}
	
	public void InitStudentJDialog() {
		setSize(465, 430);
		JLabel majorLab = new JLabel("专业*：");
		majorLab.setBounds(230,190,80,30);
		majorBox = new JComboBox();
		majorBox.setBounds(315,190,120,30);
		JLabel gradeLab = new JLabel("年级*：");
		gradeLab.setBounds(15,225,80,30);
		gradeBox = new JComboBox();
		gradeBox.setBounds(100,225,120,30);
		yearLab = new JLabel("入学时间*：");
		yearLab.setBounds(230,225,80,30);
		yearText = new JTextField();
		dateChooser = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser.register(yearText);
		yearText.setBounds(new Rectangle(315, 225, 120, 30));
		JLabel classLab = new JLabel("班级*：");
		classLab.setBounds(15,260,80,30);
		classBox = new JComboBox();
		classBox.setBounds(100,260,120,30);
		JLabel cultureLab = new JLabel("培养层次*：");
		cultureLab.setBounds(230,260,80,30);
		cultureText = new JTextField();
		cultureText.setBounds(315,260,120,30);
		JLabel typeLab = new JLabel("学生类别*：");
		typeLab.setBounds(15,295,80,30);
		typeText = new JTextField();
		typeText.setBounds(100,295,120,30);
		JLabel EducationLab = new JLabel("学历：");
		EducationLab.setBounds(230,295,80,30);
		educationText = new JTextField();
		educationText.setBounds(315,295,120,30);
		button.setBounds((getWidth() - 80)/2, 330, 80, 30);
		
		contentPane.add(majorLab);
		contentPane.add(majorBox);
		contentPane.add(gradeLab);
		contentPane.add(gradeBox);
		contentPane.add(yearLab);
		contentPane.add(yearText);
		contentPane.add(classLab);
		contentPane.add(classBox);
		contentPane.add(cultureLab);
		contentPane.add(cultureText);
		contentPane.add(typeLab);
		contentPane.add(typeText);
		contentPane.add(EducationLab);
		contentPane.add(educationText);
		
		List list = new Dao("major").getIdList();
		for(int i = 0; i< list.size() ; i++) {
			majorBox.addItem(list.get(i));
		}
		list.clear();
		list = new Dao("semester").getIdList();
		for(int i = 0; i <list.size(); i++) {
			gradeBox.addItem(list.get(i));
		}
		list.clear();
		list = new Dao("xclass").getIdList();
		for(int i = 0; i < list.size(); i++) {
			classBox.addItem(list.get(i));
		}
		
		InitBaseJDialog();
		studentDao = new StudentDao();
	}
	
	private void getBaseText() {
		base = new BaseInfoModel();
		base.setId(idText.getText());
		base.setName(nameText.getText());
		base.setFormarName(formarNameText.getText());
		try {
			base.setAge(Integer.parseInt(ageText.getText()));
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			base.setAge(0);
		}
		base.setSex(sexBox.getSelectedItem()+"");
		base.setNativePlace(nativePlaceText.getText());
		base.setIDCARDTYPE(IDCARDTYPECom.getSelectedItem()+"");
		base.setIDCARDNUM(IDCARDNUMText.getText());
		base.setTel(telText.getText());
	}
	private void getTeacherText() {
		teacherInfo = new TeacherModel();
		tools tool = new tools();
		teacherInfo.setId(idText.getText());
		teacherInfo.setCollege(tool.Split(collegeBox.getSelectedItem()+""));
		teacherInfo.setDepartment(tool.Split(departmentBox.getSelectedItem()+""));
		teacherInfo.setLevel(levelText.getText());
		teacherInfo.setEducation(educationText.getText());
		teacherInfo.setYear(yearText.getText());
		getBaseText();
		base.setType("teacher");
	}
	
	private void getStudentText() {
		getBaseText();
		tools tool = new tools();
		studentInfo = new StudentModel();
		studentInfo.setId(idText.getText());
		studentInfo.setCollege(tool.Split(collegeBox.getSelectedItem()+""));
		studentInfo.setDepartment(tool.Split(departmentBox.getSelectedItem()+""));
		studentInfo.setMajor(tool.Split(majorBox.getSelectedItem()+""));
		studentInfo.setGrade(tool.Split(gradeBox.getSelectedItem()+""));
		studentInfo.setClassId(tool.Split(classBox.getSelectedItem()+""));
		studentInfo.setCulture_level(cultureText.getText());
		studentInfo.setType(typeText.getText());
		studentInfo.setEducation(educationText.getText());
		studentInfo.setYear(yearText.getText());
		base.setType("student");
		if(studentInfo.getEducation().equals("null")) {
			studentInfo.setEducation("");
		}
	}
	
	private void InitBaseData() {
		idText.setText(baseInfo.getId());
		nameText.setText(baseInfo.getName());
		formarNameText.setText(baseInfo.getFormarName());
		sexBox.setSelectedItem(baseInfo.getSex());
		ageText.setText(baseInfo.getAge()+"");
		nativePlaceText.setText(baseInfo.getNativePlace());
		IDCARDTYPECom.setSelectedItem(baseInfo.getIDCARDTYPE());
		IDCARDNUMText.setText(baseInfo.getIDCARDNUM());
		telText.setText(baseInfo.getTel());
	}
	
	public void InitTeacherData() {
		InitBaseData();
		collegeBox.setSelectedItem(teacher.getCollege());
		departmentBox.setSelectedItem(teacher.getDepartment());
		levelText.setText(teacher.getLevel());
		educationText.setText(teacher.getEducation());
		yearText.setText(teacher.getYear());
		
	}
	
	public void InitStudentData() {
		InitBaseData();
		collegeBox.setSelectedItem(student.getCollege());
		departmentBox.setSelectedItem(student.getDepartment());
		majorBox.setSelectedItem(student.getMajor());
		gradeBox.setSelectedItem(student.getGrade());
		classBox.setSelectedItem(student.getClassId());
		cultureText.setText(student.getCulture_level());
		typeText.setText(student.getType());
		educationText.setText(student.getEducation());
		yearText.setText(student.getYear());
	}

	public class TeacherBtnListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			getTeacherText();
			if(teacherInfo.getId().equals("") || teacherInfo.getCollege().equals("") || teacherInfo.getDepartment().equals("") || 
					teacherInfo.getLevel().equals("") || teacherInfo.getEducation().equals("") || teacherInfo.getYear().equals("") || 
					base.getId().equals("") || base.getName().equals("") || base.getSex().equals("") || 
					base.getIDCARDTYPE().equals("") || base.getIDCARDNUM().equals("") || base.getType().equals("")) {
				JOptionPane.showMessageDialog(getContentPane(), "带*的项目为必要项，请不要遗漏！");
				return;
			}
			if(button.getText().equals("添加")){
				if(teacherDao.addBaseInfo(base) && teacherDao.addTeacher(teacherInfo)) {
					JOptionPane.showMessageDialog(getContentPane(), "成功！");
					dispose();
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "失败！");
					return;
				}
			}else if(button.getText().equals("修改")) {
				if(teacherDao.updateBaseInfo(base,baseInfo.getId()) && teacherDao.updateTeacher(teacherInfo,teacher.getId())) {
					JOptionPane.showMessageDialog(getContentPane(), "成功");
					dispose();
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "失败！");
					return;
				}
			}
		}
	}
	
	public class StudentBtnListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			getStudentText();
			if(base.getId().equals("") || base.getName().equals("") || base.getSex().equals("") || base.getIDCARDTYPE().equals("") 
					|| base.getIDCARDNUM().equals("") || base.getType().equals("") || studentInfo.getCollege().equals("") || 
					studentInfo.getDepartment().equals("") || studentInfo.getMajor().equals("") || studentInfo.getGrade().equals("") || 
					studentInfo.getClassId().equals("") || studentInfo.getCulture_level().equals("" ) || studentInfo.getType().equals("")) {
				JOptionPane.showMessageDialog(getContentPane(), "带*的项目为必要项，请不要遗漏！");
				return;
			}
			if(button.getText().equals("添加")) {
				if(studentDao.insertBaseInfo(base) && studentDao.insertStudent(studentInfo)) { 
					JOptionPane.showMessageDialog(getContentPane(), "成功");
					dispose();
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "失败");
				}
			}else if(button.getText().equals("修改")) {
				if(studentDao.updateBaseInfo(base,baseInfo.getId()) && studentDao.upDateInfo(studentInfo,student.getId())) {
					JOptionPane.showMessageDialog(getContentPane(), "成功");
					dispose();
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "失败");
				}
			}
		}
	}
}
