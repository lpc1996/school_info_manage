package com.lpc.iframe;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.lpc.util.ReadFile;

public class MainFrame extends JFrame {

	/**
	 * 主窗口
	 */
	private static final long serialVersionUID = 1L;
	private JMenuBar mainMenu;
	private JMenuItem changeItem;
	private JMenuItem changePassItem;
	private JMenuItem exitItem;
	private BtnListener itemListener;
	private JMenu backstageMenu;
	private JMenuItem teacherMenu;
	private JMenuItem studentManageItem;
	public PersonInfoChange person;
	public TeacherManage teacherManage;
	public StudentManage studentManage;
	private JMenuItem collegeManageItem;
	private JMenu userMenu;
	private JMenuItem departmentItem;
	public CollegeManage collegeManage;
	public DepartmentManage departmentManage;
	private JMenuItem majorItem;
	private JMenuItem schoolYearItem;
	private JMenuItem schoolTremItem;
	private JMenuItem semesterItem;
	private JMenuItem logoutItem;
	public SchoolYearManage schoolYearManage;
	public MajorManage majorManage;
	public SchoolTremManage schoolTremManage;
	public SemesterManage semesterManage;
	private JMenuItem xclassItem;
	public XClassManage xclassManage;
	private JMenuItem coursesItem;
	private JMenuItem offeringCoursesItem;
	private JMenu courseMenu;
	public ChangePass changePass;
	public courseManage courseManage;
	public CourseClass offeringManage;
	private JMenuItem studentCourseItem;
	public StudentCourseManage studentCourseManage;
	private JMenu settingMenu;
	private JMenuItem userManageItem;
	public UserManage userManage;
	private JMenu studentMenu;
	private JMenuItem infoSearchItem;
	private JMenuItem courseSearchItem;

	public MainFrame() {
		// TODO Auto-generated constructor stub
		super();
		initFrame();
	}
	
	private void initFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("西安邮电大学-教务系统");
		this.setIconImage(Toolkit.getDefaultToolkit().createImage(Login.getImgURL()));
		this.setSize(new Dimension(800, 500));
		this.setResizable(false);
		this.setLocation(new Point((Toolkit.getDefaultToolkit().getScreenSize().width - 800)/2, 
				(Toolkit.getDefaultToolkit().getScreenSize().height-500)/2));
		itemListener = new BtnListener();
		if(Login.getLoginModel().getLimit() < 4) {
			setJMenuBar(setStudentMenu());
		}else if(Login.getLoginModel().getLimit() >= 4 && Login.getLoginModel().getLimit() < 7) {
			setJMenuBar(setTeacherMenu());
		}else if(Login.getLoginModel().getLimit() >= 7) {
			setJMenuBar(setadminMenuBar());
		}
		
		JPanel contentPane = new JPanel();
		JLabel versionLab = new JLabel();
		String[] version = null;
		try {
			version = new ReadFile("src\\com\\lpc\\lib\\settings\\version.ini").ReadVersion();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		versionLab.setText("<html><body><div>"+version[0]+"<br/>"+version[1]+"</div></body></html>");
		contentPane.add(versionLab);
		setContentPane(contentPane);
	} 
	
	private JMenuBar setStudentMenu() {
		mainMenu = new JMenuBar();
		mainMenu.add(setPublicJMenu());
		return mainMenu;
	}
	
	private JMenuBar setadminMenuBar() {
		mainMenu = new JMenuBar();
		mainMenu.add(setPublicJMenu());
		backstageMenu = new JMenu();
		backstageMenu.setText("后台管理");
		teacherMenu = new JMenuItem();
		teacherMenu.setText("教职工管理");
		backstageMenu.add(teacherMenu);
		studentManageItem = new JMenuItem("学生信息管理");
		backstageMenu.add(studentManageItem);
		mainMenu.add(backstageMenu);
		collegeManageItem =  new JMenuItem("学院信息管理");
		backstageMenu.add(collegeManageItem);
		departmentItem = new JMenuItem("系/部信息管理");
		backstageMenu.add(departmentItem);
		majorItem = new JMenuItem("专业信息管理");
		backstageMenu.add(majorItem);
		schoolYearItem = new JMenuItem("学年信息管理");
		backstageMenu.add(schoolYearItem);
		schoolTremItem = new JMenuItem("学期信息管理");
		backstageMenu.add(schoolTremItem);
		semesterItem = new JMenuItem("年级信息管理");
		backstageMenu.add(semesterItem);
		xclassItem = new JMenuItem("班级信息管理");
		backstageMenu.add(xclassItem);
		courseMenu = new JMenu();
		courseMenu.setText("课程管理");
		mainMenu.add(courseMenu);
		coursesItem = new JMenuItem("课程信息管理");
		courseMenu.add(coursesItem);
		offeringCoursesItem = new JMenuItem("开设课程管理");
		courseMenu.add(offeringCoursesItem);
		studentCourseItem = new JMenuItem("学生选课管理");
		courseMenu.add(studentCourseItem);
		studentMenu = new JMenu("学生信息管理");
		infoSearchItem = new JMenuItem("学生信息查询");
		studentMenu.add(infoSearchItem);
		courseSearchItem = new JMenuItem("学生成绩查询");
		studentMenu.add(courseSearchItem);
		mainMenu.add(studentMenu);
		settingMenu = new JMenu("系统设置");
		mainMenu.add(settingMenu);
		userManageItem = new JMenuItem("用户管理"); 
		settingMenu.add(userManageItem);
		
		teacherMenu.addActionListener(itemListener);
		studentManageItem.addActionListener(itemListener);
		collegeManageItem.addActionListener(itemListener);
		departmentItem.addActionListener(itemListener);
		majorItem.addActionListener(itemListener);
		schoolYearItem.addActionListener(itemListener);
		schoolTremItem.addActionListener(itemListener);
		semesterItem.addActionListener(itemListener);
		xclassItem.addActionListener(itemListener);
		coursesItem.addActionListener(itemListener);
		offeringCoursesItem.addActionListener(itemListener);
		studentCourseItem.addActionListener(itemListener);
		infoSearchItem.addActionListener(itemListener);
		courseSearchItem.addActionListener(itemListener);
		userManageItem.addActionListener(itemListener);
		return mainMenu;
	}
	
	private JMenuBar setTeacherMenu() {
		mainMenu = new JMenuBar();
		mainMenu.add(setPublicJMenu());
		
		return mainMenu;
	}
	
	private JMenu setPublicJMenu() {
		userMenu = new JMenu();
		userMenu.setText("用户信息");
		changeItem = new JMenuItem("个人信息修改");
		changePassItem = new JMenuItem("修改密码");
		logoutItem = new JMenuItem("注销登陆");
		exitItem = new JMenuItem("退出");
		
		userMenu.add(changeItem);
		userMenu.add(changePassItem);
		userMenu.add(logoutItem);
		userMenu.addSeparator();
		userMenu.add(exitItem);
		changeItem.addActionListener(itemListener);
		changePassItem.addActionListener(itemListener);
		logoutItem.addActionListener(itemListener);
		exitItem.addActionListener(itemListener);
		
		return userMenu;
	}
	
	public class BtnListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == changeItem) {
				person = new PersonInfoChange();
				person.setVisible(true);
			}else if(e.getSource() == changePassItem) {
				changePass = new ChangePass();
				changePass.setVisible(true);
			}else if(e.getSource() == exitItem) {
				System.exit(0);
			}else if(e.getSource() == teacherMenu) {
				teacherManage = new TeacherManage();
				teacherManage.setVisible(true);
			}else if(e.getSource() == studentManageItem){
				studentManage = new StudentManage();
				studentManage.setVisible(true);
			}else if(e.getSource() == collegeManageItem) {
				collegeManage = new CollegeManage();
				collegeManage.setVisible(true);
			}else if(e.getSource() == departmentItem) {
				departmentManage = new DepartmentManage();
				departmentManage.setVisible(true);
			}else if(e.getSource() == majorItem) {
				majorManage = new MajorManage();
				majorManage.setVisible(true);
			}else if(e.getSource() == schoolYearItem) {
				schoolYearManage = new SchoolYearManage();
				schoolYearManage.setVisible(true);
			}else if(e.getSource() == schoolTremItem) {
				schoolTremManage = new SchoolTremManage();
				schoolTremManage.setVisible(true);
			}else if(e.getSource() == semesterItem) {
				semesterManage = new SemesterManage();
				semesterManage.setVisible(true);
			}else if(e.getSource() == logoutItem) {
				new Login().setVisible(true);
				dispose();
			}else if(e.getSource() == xclassItem) {
				 xclassManage = new XClassManage();
				 xclassManage.setVisible(true);
			}else if(e.getSource() == coursesItem){
				courseManage = new courseManage();
				courseManage.setVisible(true);
			}else if(e.getSource() == offeringCoursesItem ) {
				offeringManage = new CourseClass();
				offeringManage.setVisible(true);
			}else if(e.getSource() == studentCourseItem) {
				studentCourseManage = new StudentCourseManage();
				studentCourseManage.setVisible(true);
			}else if(e.getSource() == userManageItem) { 
				userManage = new UserManage();
				userManage.setVisible(true);
			}else if(e.getSource() == infoSearchItem) {
				JOptionPane.showMessageDialog(null,"功能开发中，敬请期待");
			}else if(e.getSource() == courseSearchItem) {
				JOptionPane.showMessageDialog(null,"功能开发中，敬请期待");
			}
		}
	}
}
