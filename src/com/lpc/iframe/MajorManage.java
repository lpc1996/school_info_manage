package com.lpc.iframe;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.lpc.dao.Dao;
import com.lpc.mode.MajorModel;
import com.lpc.util.ConnXupt;
import com.lpc.util.tools;

public class MajorManage extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton addBtn;
	private JButton deleteBtn;
	private JButton changeBtn;
	private JButton refreshBtn;
	private ContentPane contentPane;
	private List majorList;
	private DefaultTableModel model;
	private BtnListener btnLis;
	public TextJDialog textJDialog;
	private Dao dao;

	public MajorManage() {
		// TODO Auto-generated constructor stub
		super();
		dao = new Dao("major");
		InitJDialog();
		InitData();
	}
	
	private void InitJDialog() {
		setTitle("רҵ��Ϣ����");
		setIconImage(Toolkit.getDefaultToolkit().createImage(Login.getImgURL()));
		setSize(600,330);
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
	
	private void InitData() {
		majorList = dao.getList();
		Vector title = new ConnXupt().getComment("major");
		model = new DefaultTableModel(title,majorList.size());
		for(int i = 0; i < model.getRowCount(); i++) {
			MajorModel major = (MajorModel)majorList.get(i);
			model.setValueAt(major.getId(), i, 0);
			model.setValueAt(major.getName(), i, 1);
			model.setValueAt(major.getCollegeId(), i, 2);
			model.setValueAt(major.getDepartmentId(), i, 3);
		}
		contentPane.setTableModel(model);
	}
	
	public class BtnListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == addBtn) {
				textJDialog = new TextJDialog();
				textJDialog.setVisible(true);
				InitData();
			}else if(e.getSource() == changeBtn) {
				int i = contentPane.getSelectRow();
				if(i < 0 ) {
					JOptionPane.showMessageDialog(getContentPane(), "��ѡ��һ��");
					return;
				}
				MajorModel major = new MajorModel();
				major.setId(contentPane.getValueAt(i, 0)+"");
				major.setName(contentPane.getValueAt(i, 1)+"");
				major.setCollegeId(contentPane.getValueAt(i, 2)+"");
				major.setDepartmentId(contentPane.getValueAt(i, 3)+"");
				textJDialog = new TextJDialog(major);
				textJDialog.setVisible(true);
				InitData();
			}else if(e.getSource() == deleteBtn) {
				int i = contentPane.getSelectRow();
				if(i < 0 ) {
					JOptionPane.showMessageDialog(getContentPane(), "��ѡ��һ��");
					return;
				}
				String id = contentPane.getValueAt(i, 0)+"";
				if(JOptionPane.showConfirmDialog(getContentPane(), "ȷ��Ҫɾ��"+id+"��", "ɾ��ȷ��", JOptionPane.YES_NO_OPTION) == 1) {
					return;
				}
				if(dao.deleteData(id)) {
					JOptionPane.showMessageDialog(getContentPane(), "ɾ���ɹ�");
					InitData();
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "ɾ��ʧ��");
				}
			}else if(e.getSource() == refreshBtn) {
				InitData();
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
		private JComboBox collegeIdBox;
		private JComboBox departmentBox;
		private MajorModel data;

		public TextJDialog() {
			super();
			setTitle("���רҵ��Ϣ");
			button = new JButton("���");
			InitJDialog();
		}
		
		public TextJDialog(MajorModel data) {
			super();
			setTitle("�޸�רҵ��Ϣ");
			button = new JButton("�޸�");
			this.data = data;
			InitJDialog();
			InitData();
		}
		
		private void InitJDialog() {
			setSize(250, 240);
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			setIconImage(Toolkit.getDefaultToolkit().createImage(Login.getImgURL()));
			setModal(true);
			setLocation(new Point(0, 0));
			setLocationRelativeTo(null);
			
			JLabel idLab = new JLabel("רҵ���ţ�");
			idLab.setBounds(15,15,80,30);
			idText = new JTextField();
			idText.setBounds(100,15,120,30);
			JLabel nameLab = new JLabel("���ƣ�");
			nameLab.setBounds(15,50,80,30);
			nameText = new JTextField();
			nameText.setBounds(100,50,120,30);
			JLabel collegeIdLab = new JLabel("����ѧԺ��");
			collegeIdLab.setBounds(15,85,80,30);
			collegeIdBox = new JComboBox();
			collegeIdBox.setBounds(100,85,120,30);
			JLabel departmentLab = new JLabel("����ϵ/����");
			departmentLab.setBounds(15,120,80,30);
			departmentBox = new JComboBox();
			departmentBox.setBounds(100,120,120,30);
			button.setBounds((getWidth() - 80)/2,155,80,30);
			
			JPanel contentPane = new JPanel();
			contentPane.setLayout(null);
			contentPane.add(idLab);
			contentPane.add(idText);
			contentPane.add(nameLab);
			contentPane.add(nameText);
			contentPane.add(collegeIdLab);
			contentPane.add(collegeIdBox);
			contentPane.add(departmentLab);
			contentPane.add(departmentBox);
			contentPane.add(button);
			setContentPane(contentPane);
			
			button.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					MajorModel major = new MajorModel();
					tools tool = new tools();
					major.setId(idText.getText());
					major.setName(nameText.getText());
					major.setCollegeId(tool.Split(collegeIdBox.getSelectedItem()+""));
					major.setDepartmentId(tool.Split(departmentBox.getSelectedItem()+""));
					if(major.getId().length() == 0 || major.getName().length() == 0 || major.getCollegeId().length() == 0 || major.getDepartmentId().length() == 0) {
						JOptionPane.showMessageDialog(getContentPane(), "��������Ǳ����������©");
						return ;
					}
					if(button.getText().equals("���")) {
						if(dao.insertdata(major)) {
							JOptionPane.showMessageDialog(getContentPane(), "��ӳɹ�");
							dispose();
						}else {
							JOptionPane.showMessageDialog(getContentPane(), "���ʧ��");
						}
					}else {
						if(dao.updatedata(major, data.getId())) {
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
				tools tool = new tools();
				idText.setText(data.getId());
				nameText.setText(data.getName());
				tool.setSelectedItem(collegeIdBox, data.getCollegeId());
				tool.setSelectedItem(departmentBox, data.getDepartmentId());
			}
		}
	}
}





