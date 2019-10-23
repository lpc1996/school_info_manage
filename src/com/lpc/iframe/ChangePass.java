package com.lpc.iframe;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
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
import com.lpc.util.Secret;


public class ChangePass extends JDialog {

	/**
	 * 
	 * 
	 * �޸ĸ����˻�����
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField oldPassText;
	private JPasswordField newPassText;
	private JPasswordField rePassText;
	private JButton changeBtn;
	private LoginDao loginDao;

	public ChangePass() {
		// TODO Auto-generated constructor stub
		super();
		loginDao = new LoginDao();
		initFrame();
	}
	
	public void initFrame() {
		this.setIconImage(Toolkit.getDefaultToolkit().createImage(Login.getImgURL()));
		this.setTitle("�޸�����");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(256, 240);
		this.setModal(true);
		this.setLocation(new Point(0, 0));
		this.setLocationRelativeTo(null);
		
		JLabel oldPassLab = new JLabel("�����룺");
		oldPassLab.setBounds(new Rectangle(30, 30, 60, 30));
		oldPassText = new JPasswordField();
		oldPassText.setBounds(new Rectangle(90, 30, 120, 30));
		JLabel newPassLab = new JLabel("�����룺");
		newPassLab.setBounds(new Rectangle(30, 70, 60, 30));
		newPassText = new JPasswordField();
		newPassText.setBounds(new Rectangle(90, 70, 120, 30));
		JLabel rePassLab = new JLabel("�����룺");
		rePassLab.setBounds(new Rectangle(30, 110, 60, 30));
		rePassText = new JPasswordField();
		rePassText.setBounds(new Rectangle(90, 110, 120, 30));
		changeBtn = new JButton();
		changeBtn.setText("�ύ");
		changeBtn.setBounds((int)(this.getSize().getWidth()-80)/2, 150, 80, 30);
		
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(BorderFactory.createTitledBorder("����һ�廯"));
		contentPane.add(oldPassLab);
		contentPane.add(oldPassText);
		contentPane.add(newPassLab);
		contentPane.add(newPassText);
		contentPane.add(rePassLab);
		contentPane.add(rePassText);
		contentPane.add(changeBtn);
		this.setContentPane(contentPane);
		
		changeBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String oldPass = new Secret(new String(oldPassText.getPassword())).set();
				String newPass = new String(newPassText.getPassword());
				String rePass = new String(rePassText.getPassword());
				if(oldPass.length() == 0) {
					JOptionPane.showMessageDialog(getContentPane(), "�����������?");
					return ;
				}else if(newPass.length() == 0) {
					JOptionPane.showMessageDialog(getContentPane(), "������������");
					return ;
				}else if(rePass.length() == 0) {
					JOptionPane.showMessageDialog(getContentPane(), "��������������");
					return ;
				}
				if(newPass.equals(rePass))
					newPass = new Secret(rePass).set();
				else{
					JOptionPane.showMessageDialog(getContentPane(), "������������벻��ͬ������������?");
					return ;
				}
				if(loginDao.updatePass(oldPass, newPass, Login.getLoginModel().getId())) {
					JOptionPane.showMessageDialog(getContentPane(), "�޸ĳɹ������µ�¼��");
					new Login().setVisible(true);
					Dispose();
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "�޸�ʧ��");
				}
			}
		});
	}
	
	private void Dispose() {
		this.dispose();
	}
}
