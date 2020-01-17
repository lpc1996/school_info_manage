package com.lpc.iframe;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.lpc.dao.Dao;
import com.lpc.util.ConnXupt;

public class SemesterManage extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton addBtn;
	private JButton deleteBtn;
	private JButton changeBtn;
	private JButton refreshBtn;
	private ContentPane contentPane;
	private DefaultTableModel model;
	private BtnListener btnLis;
	private Dao dao;

	public SemesterManage() {
		// TODO Auto-generated constructor stub
		super();
		dao = new Dao("semester");
		InitJDialog();
		InitDate();
	}
	
	public void InitJDialog() {
		setTitle("�꼶��Ϣ����");
		setIconImage(Toolkit.getDefaultToolkit().createImage(Login.getImgURL()));
		setSize(700,330);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setLocation(new Point(0, 0));
		setLocationRelativeTo(null);
	
		addBtn =  new JButton("���");
		deleteBtn = new JButton("ɾ��");
		changeBtn = new JButton("�޸�");
		refreshBtn = new JButton("ˢ��");
		
		contentPane = new ContentPane(new Dimension(getWidth(), getHeight()+30));
		setContentPane(contentPane);
		
		btnLis = new BtnListener();
		addBtn.addActionListener(btnLis);
		deleteBtn.addActionListener(btnLis);
		changeBtn.addActionListener(btnLis);
		refreshBtn.addActionListener(btnLis);
		
		contentPane.setAddBtn(addBtn);
		contentPane.setChangeBtn(changeBtn);
		contentPane.setDeleteBtn(deleteBtn);
		contentPane.setRefreshBtn(refreshBtn);
	}
	
	private void InitDate() {
		List semesterList = dao.getList();
		if(semesterList == null) {
			JOptionPane.showMessageDialog(getContentPane(), "ץȡ����ʧ��", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Vector title = new ConnXupt().getComment("semester");
		model = new DefaultTableModel(title,semesterList.size());
		for (int i  = 0; i < model.getRowCount(); i++) {
			String[] str = (String[]) semesterList.get(i);
			model.setValueAt(str[0], i, 0);
			model.setValueAt(str[1], i, 1);
		}
		contentPane.setTableModel(model);
	}
	
	public class BtnListener implements ActionListener{

		private TextJDialog textJDialog;

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == addBtn) {
				textJDialog = new TextJDialog();
				textJDialog.setVisible(true);
				InitDate();
			}else if(e.getSource() == deleteBtn) {
				int i = contentPane.getSelectRow();
				if(i < 0) {
					JOptionPane.showMessageDialog(getContentPane(), "��ѡ��һ��");
					return;
				}
				String id = contentPane.getValueAt(i, 0)+"";
				if(JOptionPane.showConfirmDialog(getContentPane(), "ȷ��Ҫɾ��"+id+"��", "ɾ��ȷ��", JOptionPane.YES_NO_OPTION) == 1) {
					return;
				}
				if(dao.deleteData(id)) {
					JOptionPane.showMessageDialog(getContentPane(), "ɾ���ɹ�");
					InitDate();
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "ɾ��ʧ��");
				}
			}else if(e.getSource() == changeBtn) {
				int i = contentPane.getSelectRow();
				if(i < 0) {
					JOptionPane.showMessageDialog(getContentPane(), "��ѡ��һ��");
					return;
				}
				String[] str = new String[2];
				str[0] = contentPane.getValueAt(i, 0)+"";
				str[1] = contentPane.getValueAt(i, 1)+"";
				textJDialog = new TextJDialog(str);
				textJDialog.setVisible(true);
				InitDate();
			}else if(e.getSource() == refreshBtn) {
				InitDate();
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
		private String[] data;

		public TextJDialog() {
			super();
			setTitle("����꼶��Ϣ");
			button = new JButton("���");
			InitJDialog();
		}
		
		public TextJDialog(String [] data) {
			super();
			setTitle("�޸��꼶��Ϣ");
			button = new JButton("�޸�");
			this.data = data;
			InitJDialog();
			InitData();
		}
		
		private void InitJDialog() {
			setSize(250, 160);
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			setIconImage(Toolkit.getDefaultToolkit().createImage(Login.getImgURL()));
			setModal(true);
			setLocation(new Point(0, 0));
			setLocationRelativeTo(null);
			
			JLabel idLab = new JLabel("�꼶���ţ�");
			idLab.setBounds(15,15,80,30);
			idText = new JTextField();
			idText.setBounds(100,15,120,30);
			JLabel nameLab = new JLabel("�꼶���ƣ�");
			nameLab.setBounds(15,50,80,30);
			nameText = new JTextField();
			nameText.setBounds(100,50,120,30);
			button.setBounds((getWidth() - 80)/2, 85, 80, 30);
			
			JPanel contentPane = new JPanel();
			contentPane.setLayout(null);
			contentPane.add(idLab);
			contentPane.add(idText);
			contentPane.add(nameLab);
			contentPane.add(nameText);
			contentPane.add(button);
			setContentPane(contentPane);
			
			button.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String [] str = new String[2];
					str[0] = idText.getText();
					str[1] = nameText.getText();
					if(str[0].length() == 0 || str[1].length() == 0 ) {
						JOptionPane.showMessageDialog(getContentPane(), "��������Ǳ����������©");
						return ;
					}
					if(button.getText().equals("���")) {
						if(dao.insertdata(str)) {
							JOptionPane.showMessageDialog(getContentPane(), "��ӳɹ�");
							dispose();
						}else {
							JOptionPane.showMessageDialog(getContentPane(), "���ʧ��");
						}
					}else {
						if(dao.updatedata(str, data[0])) {
							JOptionPane.showMessageDialog(getContentPane(), "�޸ĳɹ�");
							dispose();
						}else {
							JOptionPane.showMessageDialog(getContentPane(), "�޸�ʧ��");
							InitData();
						}
					}
				}
			});
		}
		
		private void InitData() {
			if(data != null) {
				idText.setText(data[0]);
				nameText.setText(data[1]);
			}
		}
	}
}
