package com.lpc.iframe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import com.lpc.dao.BaseInfoDao;
import com.lpc.dao.LoginDao;
import com.lpc.mode.BaseInfoModel;
import com.lpc.mode.LoginModel;
import com.lpc.util.Secret;
import com.lpc.view.MainView;

public class Login extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static BaseInfoModel user = null;
	private static LoginModel loginModel = null;
	private static URL imgURL = null;
	private JButton loginBtn;
	private JPanel cententPane;
	private JButton exitBtn;
	private static URL imgURL2;
	private JTextField nameText;
	private JPasswordField passText;
	public LoginDao loginDao;
	public static MainView mainView;
	private static URL imgURL3;
	private static URL imgURL4;
	private static URL imgMan;
	private static URL imgWoman;
	private static MainFrame mainFrame;
	
	/**
	 * @throws HeadlessException
	 */
	public Login() throws HeadlessException {
		super();
		loginDao = new LoginDao();
		if( Login.mainFrame != null)
			Login.mainFrame.dispose();
		if(Login.mainView != null)
			Login.mainView.dispose();
		Login.imgURL = this.getClass().getResource("/com/lpc/lib/images/school_logo.jpg");
		Login.imgURL2 = this.getClass().getResource("/com/lpc/lib/images/icon.png");
		Login.imgURL3 = this.getClass().getResource("/com/lpc/lib/images/background1.jpg");
		Login.imgURL4 = this.getClass().getResource("/com/lpc/lib/images/user.gif");
		Login.imgMan = this.getClass().getResource("/com/lpc/lib/images/male.gif");
		Login.imgWoman = this.getClass().getResource("/com/lpc/lib/images/female.gif");
		initFrame();
	}

	public void initFrame() {
		
		this.setSize(new Dimension(296, 356));
		this.setResizable(false);
		this.setTitle("欢迎登陆");
		
		this.setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(imgURL));
		this.setLocationRelativeTo(null);
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		loginBtn = new JButton();
		loginBtn.setText("登陆");
		loginBtn.setBounds((296/2 - 85), 285, 80, 32); 
		
		exitBtn = new JButton();
		exitBtn.setText("退出");
		exitBtn.setBounds((296/2 + 5), 285, 80, 32);
	
		JLabel userLab = new JLabel();
		imgURL4 = this.getClass().getResource("/com/lpc/lib/images/user.gif");
		userLab.setIcon(new ImageIcon(imgURL4));
		userLab.setBounds(new Rectangle(0, 160, 280, 110));
		
		JLabel nameLab = new JLabel("用户名：");
		nameLab.setBounds(new Rectangle(40, 195, 80, 32));
		JLabel passLab = new JLabel("密码：");
		passLab.setBounds(new Rectangle(40, 230, 80,32));
		
		nameText = new JTextField();
		nameText.setBounds(new Rectangle(120, 195, 124, 32));
		
		passText = new JPasswordField();
		passText.setBounds(new Rectangle(120, 230, 124, 32));
		
		JLabel logoLab = new JLabel();
		logoLab.setIcon(new ImageIcon(imgURL3));
		logoLab.setBounds(new Rectangle(0, 0, 296, 150));
		
		getRootPane().setDefaultButton(loginBtn);
		
		cententPane = new JPanel();
		cententPane.setLayout(null);
		cententPane.setBackground(new Color(255, 255, 255));
		cententPane.add(logoLab);
		cententPane.add(loginBtn);
		cententPane.add(exitBtn);
		cententPane.add(nameLab);
		cententPane.add(nameText);
		cententPane.add(passLab);
		cententPane.add(passText);
		cententPane.add(userLab);
		this.setContentPane(cententPane);
		
		BtnListener btnListener = new BtnListener();
		loginBtn.addActionListener(btnListener);
		exitBtn.addActionListener(btnListener);
	}
	
	public class BtnListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == loginBtn) {
				String id = nameText.getText();
				String pass = new Secret(new String(passText.getPassword())).set();
				if(id.length() != 8) {
					JOptionPane.showMessageDialog(getContentPane(), "请输入正确的用户名");
					return;
				}
				if(pass.length() == 0) {
					JOptionPane.showMessageDialog(getContentPane(), "请输入密码");
					return ;
				}
				loginModel = loginDao.login(id, pass);
				if(loginModel != null) {
					BaseInfoDao baseInfoDao = new BaseInfoDao();
					Login.setUser(baseInfoDao.getUser(loginModel.getId()));
					JOptionPane.showMessageDialog(getContentPane(), loginModel.getName()+"欢迎");
					dispose();
					mainFrame = new MainFrame();
					mainFrame.setVisible(true);
//					mainView = new MainView();
//					mainView.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "用户名或密码错误,登陆失败");
					return ;
				}
			}else if(e.getSource() == exitBtn) {
				System.exit(0);
			}
		}
		
	}
	
	public static void main(String [] args) {
		Login login = new Login();
		login.setVisible(true);
	}

	public static BaseInfoModel getUser() {
		return user;
	}

	public static void setUser(BaseInfoModel user) {
		Login.user = user;
	}
	
	public static LoginModel getLoginModel() {
		return loginModel;
	}

	public static void setLoginModel(LoginModel loginModel) {
		Login.loginModel = loginModel;
	}
	
	public static URL getImgURL() {
		return imgURL;
	}

	public static URL getImgURL2() {
		return imgURL2;
	}

	public static URL getImgURL3() {
		return imgURL3;
	}

	public static URL getImgURL4() {
		return imgURL4;
	}

	public static URL getImgMan() {
		return imgMan;
	}

	public static URL getImgWoman() {
		return imgWoman;
	}
	
	public static String getNowTime() {
		Calendar now = Calendar.getInstance(); 
		String year = now.get(Calendar.YEAR)+"";
		String month = now.get(Calendar.MONTH)+"";
		String day = now.get(Calendar.DAY_OF_MONTH)+"";
		return year+"-"+month+"-"+day;
	}

}
