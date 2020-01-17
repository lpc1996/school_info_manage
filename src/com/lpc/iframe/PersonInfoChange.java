package com.lpc.iframe;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.lpc.dao.BaseInfoDao;
import com.lpc.mode.BaseInfoModel;

public class PersonInfoChange extends JDialog{

	/**
	 * ������Ϣ�޸�ҳ��
	 */
	private static final long serialVersionUID = 1L;
	private JTextField idText;
	private JTextField userNameText;
	private TextField nameText;
	private JTextField formarNameText;
	private JComboBox sexBox;
	private JTextField ageText;
	private JTextField nativePlaceText;
	private JComboBox idcardTypeBox;
	private JTextField idcardNumText;
	private JTextField telText;
	private JButton submitBtn;
	private JButton changeBtn;
	private BaseInfoDao baseInfoDao;

	public PersonInfoChange() {
		// TODO Auto-generated constructor stub
		super();
		baseInfoDao = new BaseInfoDao();
		initJDialog();
		initData();
		EnAble(false);
	}
	
	public void initJDialog() {
		this.setTitle("�޸ĸ�����Ϣ");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setIconImage(Toolkit.getDefaultToolkit().createImage(Login.getImgURL()));
		this.setSize(new Dimension(470, 300));
		this.setModal(true);
		this.setLocation(new Point(0, 0));
		this.setLocationRelativeTo(null);
		JPanel contentPane = new JPanel();
		JLabel idLab = new JLabel();
		idLab.setText("ID:");
		idLab.setBounds(new Rectangle(15, 15, 80, 30));
		idText = new JTextField();
		idText.setBounds(new Rectangle(100, 15, 120, 30));
		JLabel userNameLab = new JLabel();
		userNameLab.setText("�ǳƣ�");
		userNameLab.setBounds(new Rectangle(225, 15, 80, 30));
		userNameText = new JTextField();
		userNameText.setBounds(new Rectangle(310, 15, 120, 30));
		JLabel nameLab = new JLabel("������");
		nameLab.setBounds(new Rectangle(15, 50, 80, 30) );
		nameText = new TextField();
		nameText.setBounds(new Rectangle(100, 50, 120, 30));
		JLabel formarNameLab = new JLabel("��������");
		formarNameLab.setBounds(new Rectangle(225, 50, 80, 30));
		formarNameText = new JTextField();
		formarNameText.setBounds(new Rectangle(310, 50, 120, 30));
		JLabel sexLab = new JLabel("�Ա�");
		sexLab.setBounds(new Rectangle(15, 85, 80, 30));
		sexBox = new JComboBox();
		sexBox.setBounds(new Rectangle(100, 85, 120, 30));
		sexBox.addItem("��");
		sexBox.addItem("Ů");
		JLabel ageLab = new JLabel("���䣺");
		ageLab.setBounds(new Rectangle(225, 85, 80, 30));
		ageText = new JTextField();
		ageText.setBounds(new Rectangle(310, 85, 120, 30));
		JLabel nativePlaceLab = new JLabel("���᣺");
		nativePlaceLab.setBounds(new Rectangle(15, 120, 80, 30));
		nativePlaceText = new JTextField();
		nativePlaceText.setBounds(new Rectangle(100, 120, 120, 30));
		JLabel idcardTypeLab = new JLabel("֤�����ͣ�");
		idcardTypeLab.setBounds(new Rectangle(225, 120, 80, 30));
		idcardTypeBox = new JComboBox();
		idcardTypeBox.setBounds(new Rectangle(310, 120, 120, 30));
		idcardTypeBox.addItem("�������֤");
		JLabel idcarNumLab = new JLabel("֤�����룺");
		idcarNumLab.setBounds(new Rectangle(15, 155, 80, 30));
		idcardNumText = new JTextField();
		idcardNumText.setBounds(new Rectangle(100, 155, 120, 30));
		JLabel telLab = new JLabel("�绰���룺");
		telLab.setBounds(new Rectangle(225, 155, 80, 30));
		telText = new JTextField();
		telText.setBounds(new Rectangle(310, 155, 120, 30));
		changeBtn = new JButton();
		changeBtn.setText("�޸�");
		changeBtn.setBounds((this.getWidth()/2-85), 190, 80, 30);
		submitBtn = new JButton();
		submitBtn.setText("�ύ");
		submitBtn.setBounds((this.getWidth()/2+5), 190, 80, 30);
		JLabel textLab = new JLabel("����޸İ�ť�޸���Ϣ��Ȼ�����ύ��ť�����޸�");
		textLab.setBounds(new Rectangle(15, 230, this.getWidth()-30, 30));
		
		
		contentPane.setLayout(null);
		contentPane.add(idLab);
		contentPane.add(idText);
		contentPane.add(userNameLab);
		contentPane.add(userNameText);
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
		contentPane.add(idcardTypeLab);
		contentPane.add(idcardTypeBox);
		contentPane.add(idcarNumLab);
		contentPane.add(idcardNumText);
		contentPane.add(telLab);
		contentPane.add(telText);
		contentPane.add(submitBtn);
		contentPane.add(changeBtn);
		contentPane.add(textLab);
		contentPane.setBorder(BorderFactory.createTitledBorder("����һ�廯"));
		this.setContentPane(contentPane);
		
		BtnActionListener btnAntion = new BtnActionListener();
		changeBtn.addActionListener(btnAntion);
		submitBtn.addActionListener(btnAntion);
	}
	
	public void initData() {
		BaseInfoModel baseInfo = Login.getUser();
		idText.setText(baseInfo.getId());
		nameText.setText(baseInfo.getName());
		userNameText.setText(Login.getLoginModel().getName());
		formarNameText.setText(baseInfo.getFormarName());
		sexBox.setSelectedItem(baseInfo.getSex());
		ageText.setText(""+baseInfo.getAge());
		nativePlaceText.setText(baseInfo.getNativePlace());
		idcardTypeBox.setSelectedItem(baseInfo.getIDCARDTYPE());
		idcardNumText.setText(baseInfo.getIDCARDNUM());
		telText.setText(baseInfo.getTel());
	}
	
	public void EnAble(boolean able) {
		idText.setEnabled(able);
		formarNameText.setEnabled(able);
		sexBox.setEnabled(able);
		ageText.setEnabled(able);
		nativePlaceText.setEnabled(able);
		idcardTypeBox.setEnabled(able);
		idcardNumText.setEnabled(able);
		nameText.setEnabled(able);
		userNameText.setEnabled(able);
		telText.setEnabled(able);
		submitBtn.setEnabled(able);
	}

	public class BtnActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == changeBtn) {
				EnAble(true);
			}else if(e.getSource() == submitBtn) {
				BaseInfoModel baseInfo = new BaseInfoModel();
				baseInfo.setId(idText.getText());
				baseInfo.setName(nameText.getText());
				baseInfo.setFormarName(formarNameText.getText());
				baseInfo.setSex(sexBox.getSelectedItem()+"");
				baseInfo.setAge(Integer.parseInt(ageText.getText()));
				baseInfo.setNativePlace(nativePlaceText.getText());
				baseInfo.setIDCARDTYPE(idcardTypeBox.getSelectedItem()+"");
				baseInfo.setIDCARDNUM(idcardNumText.getText());
				baseInfo.setTel(telText.getText());
				baseInfo.setType(Login.getUser().getType());
				if(baseInfoDao.changeInfo(baseInfo, userNameText.getText())) {
					Login.setUser(baseInfo);
					Login.getLoginModel().setName(userNameText.getText());
					JOptionPane.showMessageDialog(getContentPane(), "�޸ĳɹ�");
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "�޸�ʧ�ܣ�");
				}
				EnAble(false);
			}
		}
		
	}
}
