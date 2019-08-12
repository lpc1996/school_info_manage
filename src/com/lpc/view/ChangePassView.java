package com.lpc.view;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import com.lpc.dao.LoginDao;
import com.lpc.iframe.Login;
import com.lpc.util.Secret;

public class ChangePassView extends PublicView{

	private JPasswordField oldPassText;
	private JPasswordField newPassText;
	private JPasswordField rePassText;
	private JButton changeBtn;
	private JPanel contentPane;

	public ChangePassView() {
		// TODO 自动生成的构造函数存根
		super("修改用户密码");
		initFrame();
	}
	
	public void initFrame() {
		this.setTitle("修改密码");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(256, 240);
		this.setLocation(new Point(0, 0));
		
		JLabel oldPassLab = new JLabel("旧密码：");
		oldPassLab.setBounds(new Rectangle(30, 30, 60, 30));
		oldPassText = new JPasswordField();
		oldPassText.setBounds(new Rectangle(90, 30, 120, 30));
		JLabel newPassLab = new JLabel("新密码：");
		newPassLab.setBounds(new Rectangle(30, 70, 60, 30));
		newPassText = new JPasswordField();
		newPassText.setBounds(new Rectangle(90, 70, 120, 30));
		JLabel rePassLab = new JLabel("新密码：");
		rePassLab.setBounds(new Rectangle(30, 110, 60, 30));
		rePassText = new JPasswordField();
		rePassText.setBounds(new Rectangle(90, 110, 120, 30));
		changeBtn = new JButton();
		changeBtn.setText("提交");
		changeBtn.setBounds((int)(this.getSize().getWidth()-80)/2, 150, 80, 30);
		
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(BorderFactory.createTitledBorder("操作一体化"));
		contentPane.add(oldPassLab);
		contentPane.add(oldPassText);
		contentPane.add(newPassLab);
		contentPane.add(newPassText);
		contentPane.add(rePassLab);
		contentPane.add(rePassText);
		contentPane.add(changeBtn);
		this.setContentPane(contentPane);
		
		changeBtn.addActionListener(new ActionListener() {
			
			private LoginDao loginDao;

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String oldPass = new Secret(new String(oldPassText.getPassword())).set();
				String newPass = new String(newPassText.getPassword());
				String rePass = new String(rePassText.getPassword());
				if(oldPass.length() == 0) {
					JOptionPane.showMessageDialog(getContentPane(), "请输入旧密码");
					return ;
				}else if(newPass.length() == 0) {
					JOptionPane.showMessageDialog(getContentPane(), "请输入新密码");
					return ;
				}else if(rePass.length() == 0) {
					JOptionPane.showMessageDialog(getContentPane(), "请重新输入密码");
					return ;
				}
				if(newPass.equals(rePass))
					newPass = new Secret(rePass).set();
				else{
					JOptionPane.showMessageDialog(getContentPane(), "两次输入的密码不相同，请重新输入");
					return ;
				}
				loginDao = new LoginDao();
				if(loginDao.updatePass(oldPass, newPass, Login.getLoginModel().getId())) {
					JOptionPane.showMessageDialog(getContentPane(), "修改成功请重新登录！");
					new Login().setVisible(true);
					Dispose();
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "修改失败");
				}
			}
		});
	}
	
	private void Dispose() {
		this.dispose();
	}

}
