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
import com.lpc.mode.DepartmentModel;
import com.lpc.util.ConnXupt;
import com.lpc.util.tools;

public class DepartmentManage extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ContentPane contentPane;
	private JButton addBtn;
	private JButton deleteBtn;
	private JButton changeBtn;
	private JButton refreshBtn;
	private List departmentList;
	private DefaultTableModel model;
	private BtnListener btnLis;
	public TextJDialog textJDialog;
	private Dao dao;

	public DepartmentManage() {
		// TODO Auto-generated constructor stub
		super();
		dao = new Dao("department");
		InitJDialog();
		InitData();
	}
	
	private void InitJDialog() {
		setTitle("ϵ/����Ϣ����");
		setIconImage(Toolkit.getDefaultToolkit().createImage(Login.getImgURL()));
		setSize(600,330);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setLocation(new Point(0, 0));
		setLocationRelativeTo(null);
		
		contentPane = new ContentPane(new Dimension(600, 360));
		
		addBtn =  new JButton("���");
		deleteBtn = new JButton("ɾ��");
		deleteBtn.setLocation(201, 253);
		changeBtn = new JButton("�޸�");
		refreshBtn = new JButton("ˢ��");
		
		btnLis = new BtnListener();
		addBtn.addActionListener(btnLis);
		deleteBtn.addActionListener(btnLis);
		changeBtn.addActionListener(btnLis);
		refreshBtn.addActionListener(btnLis);
		
		contentPane.setAddBtn(addBtn);
		contentPane.setChangeBtn(changeBtn);
		contentPane.setDeleteBtn(deleteBtn);
		contentPane.setRefreshBtn(refreshBtn);
		setContentPane(contentPane);
	}
	
	private void InitData() {
		departmentList = dao.getList();
		Vector title = new ConnXupt().getComment("department");
		model = new  DefaultTableModel(title, departmentList.size());
		for(int i = 0; i < departmentList.size() ; i++) {
			DepartmentModel dep = (DepartmentModel)departmentList.get(i);
			model.setValueAt(dep.getId(),i,0);
			model.setValueAt(dep.getName(), i, 1);
			model.setValueAt(dep.getCollegeId(), i, 2);
		}
		contentPane.setTableModel(model);
	}
	
	public class BtnListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == addBtn) {
				textJDialog = new TextJDialog("���ϵ/����Ϣ");
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
			}else if(e.getSource() == changeBtn) {
				int i = contentPane.getSelectRow();
				if(i < 0 ) {
					JOptionPane.showMessageDialog(getContentPane(), "��ѡ��һ��");
					return;
				}
				DepartmentModel dep = new DepartmentModel();
				dep.setId(contentPane.getValueAt(i, 0)+"");
				dep.setName(contentPane.getValueAt(i, 1)+"");
				dep.setCollegeId(contentPane.getValueAt(i, 2)+"");
				textJDialog = new TextJDialog("�޸�ϵ/����Ϣ",dep);
				textJDialog.setVisible(true);
				InitData();
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
		private DepartmentModel department;

		public TextJDialog(String title) {
			super();
			this.setTitle(title);
			button = new JButton();
			button.setText("���");
			InitJDialog();
		}
		
		public TextJDialog(String title, DepartmentModel department) {
			super();
			this.setTitle(title);
			this.department = department;
			button = new JButton();
			button.setText("�޸�");
			InitJDialog();
			InitData();
		}
		
		private void InitJDialog() {
			setSize(250, 200);
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			setIconImage(Toolkit.getDefaultToolkit().createImage(Login.getImgURL()));
			setModal(true);
			setLocation(new Point(0, 0));
			setLocationRelativeTo(null);
			
			JLabel idLab = new JLabel("ϵ/�����ţ�");
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
			button.setBounds((getWidth() - 80)/2,120,80,30);
			
			JPanel contentPane = new JPanel();
			contentPane.setLayout(null);
			contentPane.add(idLab);
			contentPane.add(idText);
			contentPane.add(nameLab);
			contentPane.add(nameText);
			contentPane.add(collegeIdLab);
			contentPane.add(collegeIdBox);
			contentPane.add(button);
			setContentPane(contentPane);
			
			List list = new Dao("college").getIdList();
			for(int i = 0 ;i <list.size(); i++) {
				collegeIdBox.addItem(list.get(i));
			}
			
			button.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					DepartmentModel department = new DepartmentModel();
					tools tool = new tools();
					department.setId(idText.getText());
					department.setName(nameText.getText());
					department.setCollegeId(tool.Split(collegeIdBox.getSelectedItem()+""));
					if(department.getId().length() == 0 || department.getName().length() == 0 || department.getCollegeId().length() == 0) {
						JOptionPane.showMessageDialog(getContentPane(), "��������Ϊ�������������©Ӵ");
						return;
					}
					
					if(button.getText().equals("���")) {
						
						if(dao.insertdata(department)) {
							JOptionPane.showMessageDialog(getContentPane(), "��ӳɹ�");
							dispose();
						}else {
							JOptionPane.showMessageDialog(getContentPane(), "���ʧ��");
						}
					}else if(button.getText().equals("�޸�")) {
						DepartmentModel data = new DepartmentModel();
						data.setId(idText.getText());
						data.setName(nameText.getText());
						data.setCollegeId(collegeIdBox.getSelectedItem()+"");
						if(data.getId().equals("") || data.getName().equals("") || data.getCollegeId().equals("")) {
							JOptionPane.showMessageDialog(getContentPane(), "��������Ϊ�������������©Ӵ");
							return;
						}
						if(dao.updatedata(data, TextJDialog.this.department.getId())) {
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
			if(department != null) {
				tools tool = new tools();
				idText.setText(department.getId());
				nameText.setText(department.getName());
				tool.setSelectedItem(collegeIdBox, department.getCollegeId());
			}
		}
	}
}


