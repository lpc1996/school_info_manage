package com.lpc.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import com.lpc.iframe.Login;

public class MainView extends JFrame{

	private static JDesktopPane desktop;
	private JMenuBar mainMenu;
	private JMenu backstageMenu;
	private JMenuItem teacherMenu;
	private JMenuItem studentManageItem;
	private JMenuItem collegeManageItem;
	private JMenuItem departmentItem;
	private JMenuItem majorItem;
	private JMenuItem schoolYearItem;
	private JMenuItem schoolTremItem;
	private JMenuItem semesterItem;
	private JMenuItem xclassItem;
	private JMenuItem coursesItem;
	private JMenuItem offeringCoursesItem;
	private ActionListener itemListener;
	private JMenu userMenu;
	private JMenuItem changeItem;
	private JMenuItem changePassItem;
	private JMenuItem logoutItem;
	private JMenuItem exitItem;
	private JMenu courseMenu;

	public MainView() {
		// TODO �Զ����ɵĹ��캯�����
		super();
		try {
			InitJFrame();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	
	private void InitJFrame() throws IOException {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("�����ʵ��ѧ-����ϵͳ");
		this.setIconImage(Toolkit.getDefaultToolkit().createImage(Login.getImgURL()));
		this.setSize(new Dimension(800, 500));
		this.setResizable(false);
		this.setLocation(new Point((Toolkit.getDefaultToolkit().getScreenSize().width - 800)/2, 
				(Toolkit.getDefaultToolkit().getScreenSize().height-500)/2));
		itemListener = new BtnListener();
		if(Login.getUser().getType().equals("student")) {
			setJMenuBar(setStudentMenu());
		}else if(Login.getUser().getType().equals("teacher")) {
			setJMenuBar(setTeacherMenu());
		}else if(Login.getUser().getType().equals("admin")) {
			setJMenuBar(setadminMenuBar());
		}
		//URL url = getClass().getResource("/com/lpc/lib/images/background2.jpg");
//		final Image icon = ImageIO.read(url);
		desktop = new JDesktopPane(); //{ 
//			public void paint(Graphics g) {
//				// ��Ϊprotected void paintComponent(Graphics g) { ���
//				g.drawImage(icon, 0, 0, getWidth(), getHeight(), null);
//			};
//		};
//		desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);

		this.getContentPane().add(desktop);
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
		backstageMenu.setText("��̨����");
		teacherMenu = new JMenuItem();
		teacherMenu.setText("��ְ������");
		backstageMenu.add(teacherMenu);
		studentManageItem = new JMenuItem("ѧ����Ϣ����");
		backstageMenu.add(studentManageItem);
		mainMenu.add(backstageMenu);
		collegeManageItem =  new JMenuItem("ѧԺ��Ϣ����");
		backstageMenu.add(collegeManageItem);
		departmentItem = new JMenuItem("ϵ/����Ϣ����");
		backstageMenu.add(departmentItem);
		majorItem = new JMenuItem("רҵ��Ϣ����");
		backstageMenu.add(majorItem);
		schoolYearItem = new JMenuItem("ѧ����Ϣ����");
		backstageMenu.add(schoolYearItem);
		schoolTremItem = new JMenuItem("ѧ����Ϣ����");
		backstageMenu.add(schoolTremItem);
		semesterItem = new JMenuItem("�꼶��Ϣ����");
		backstageMenu.add(semesterItem);
		xclassItem = new JMenuItem("�༶��Ϣ����");
		backstageMenu.add(xclassItem);
		courseMenu = new JMenu("�γ̹���");
		coursesItem = new JMenuItem("�γ���Ϣ����");
		courseMenu.add(coursesItem);
		offeringCoursesItem = new JMenuItem("����γ̹���");
		courseMenu.add(offeringCoursesItem);
		mainMenu.add(courseMenu);
		
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
		return mainMenu;
	}
	
	private JMenuBar setTeacherMenu() {
		mainMenu = new JMenuBar();
		mainMenu.add(setPublicJMenu());
		
		return mainMenu;
	}
	
	private JMenu setPublicJMenu() {
		userMenu = new JMenu();
		userMenu.setText("�û���Ϣ");
		changeItem = new JMenuItem("������Ϣ�޸�");
		changePassItem = new JMenuItem("�޸�����");
		logoutItem = new JMenuItem("ע����½");
		exitItem = new JMenuItem("�˳�");
		
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

		private PersonInfoView personInfoView;
		private ChangePassView changePassView;

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			desktop.removeAll();
			if(e.getSource() == changeItem) {
				personInfoView = new PersonInfoView();
				personInfoView.setVisible(true);
				desktop.add(personInfoView);
			}else if(e.getSource() == changePassItem) {
				changePassView = new ChangePassView();
				changePassView.setVisible(true);
				desktop.add(changePassView);
			}else if(e.getSource() == exitItem) {
				System.exit(0);
			}else if(e.getSource() == teacherMenu) {
				
			}else if(e.getSource() == studentManageItem){
				
			}else if(e.getSource() == collegeManageItem) {
				
			}else if(e.getSource() == departmentItem) {
				
			}else if(e.getSource() == majorItem) {
				
			}else if(e.getSource() == schoolYearItem) {
				
			}else if(e.getSource() == schoolTremItem) {
				
			}else if(e.getSource() == semesterItem) {
				
			}else if(e.getSource() == logoutItem) {
				new Login().setVisible(true);
				dispose();
			}else if(e.getSource() == xclassItem) {
				 
			}else if(e.getSource() == coursesItem){
				
			}else if(e.getSource() == offeringCoursesItem ) {
				
			}
			desktop.updateUI();
		}
	}

}
