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
import com.lpc.mode.CollegeModel;
import com.lpc.util.ConnXupt;

public class CollegeManage extends JDialog{

	/**
	 * ѧԺ��Ϣ����
	 */
	private static final long serialVersionUID = 1L;
	private List collegeList;
	private Dao dao;
	private ContentPane contentPane;
	protected CollegeText text;

	public CollegeManage(){
		// TODO Auto-generated constructor stub
		super();
		dao = new Dao("college");
		InitContentPane();
		InitData();
	}
	
	private void InitContentPane() {
		this.setTitle("ѧԺ��Ϣ����");
		setIconImage(Toolkit.getDefaultToolkit().createImage(Login.getImgURL()));
		setSize(600,330);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setLocation(new Point(0, 0));
		setLocationRelativeTo(null);
		
		contentPane = new ContentPane(new Dimension(600, 360));
		this.setContentPane(contentPane);
		
		JButton addBtn = new JButton("���");
		addBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				text = new CollegeText("���ѧԺ��Ϣ");
				text.setVisible(true);
				InitData();
			}
		});
		contentPane.setAddBtn(addBtn);
		JButton changeBtn = new JButton("�޸�");
		changeBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				int i = contentPane.getSelectRow();
				if(i < 0) {
					JOptionPane.showMessageDialog(getContentPane(), "��ѡ��һ��");
					return;
				}
				CollegeModel data = new CollegeModel();
				data.setId(contentPane.getValueAt(i, 0)+"");
				data.setName(contentPane.getValueAt(i, 1)+"");
				text = new CollegeText("�޸�ѧԺ��Ϣ", data);
				text.setVisible(true);
				InitData();
			}
		});
		contentPane.setChangeBtn(changeBtn);
		JButton deleteBtn = new JButton("ɾ��");
		deleteBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
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
					JOptionPane.showMessageDialog(null, "ɾ��"+id+"�ɹ�");
					InitData();
				}else {
					JOptionPane.showMessageDialog(null, "ɾ��"+id+"ʧ��");
				}
			}
		});
		contentPane.setDeleteBtn(deleteBtn);
		JButton refreshBtn = new JButton("ˢ��");
		refreshBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				InitData();
			}
		});
		contentPane.setRefreshBtn(refreshBtn);
	}
	private void InitData() {
		dao = new Dao("college");
		collegeList = dao.getList();
		Vector titleVec = new ConnXupt().getComment("college");
		DefaultTableModel model = new DefaultTableModel(titleVec,collegeList.size());
		for(int i = 0; i < collegeList.size() ; i++) {
			CollegeModel college = (CollegeModel)collegeList.get(i);
			model.setValueAt(college.getId(),i , 0);
			model.setValueAt(college.getName(), i, 1);
		}
		contentPane.setTableModel(model);
	}
	
	class CollegeText extends JDialog{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String title;
		private JButton button;
		private JTextField idText;
		private JTextField nameText;
		private CollegeModel data;
		private Dao collegeDao;
		
		public CollegeText(String title) {
			super();
			this.title = title;
			button = new JButton();
			button.setText("���");
			InitJDialog();
		}
		
		public CollegeText(String title,CollegeModel data) {
			super();
			this.title = title;
			button = new JButton("�޸�");
			this.data = data;
			InitJDialog();
			InitData();
		}
		
		public void InitJDialog() {
			setTitle(title);
			setSize(250, 170);
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			setIconImage(Toolkit.getDefaultToolkit().createImage(Login.getImgURL()));
			setModal(true);
			setLocation(new Point(0, 0));
			setLocationRelativeTo(null);
			
			collegeDao = new Dao("college");
			JLabel idLab = new JLabel("ID��");
			idLab.setBounds(15,15,80,30);
			idText = new JTextField();
			idText.setBounds(100,15,120,30);
			JLabel nameLab = new JLabel("���ƣ�");
			nameLab.setBounds(15,50,80,30);
			nameText = new JTextField();
			nameText.setBounds(100,50,120,30);
			
			button.setBounds((getWidth() - 80)/2,85,80,30);
			
			JPanel contentPane = new JPanel();
			contentPane.setLayout(null);
			contentPane.add(idLab);
			contentPane.add(idText);
			contentPane.add(nameLab);
			contentPane.add(nameText);
			
			contentPane.add(button);
			
			setContentPane(contentPane);	
			BtnListener btnLis = new BtnListener();
			button.addActionListener(btnLis);
		}
		
		public void InitData() {
			idText.setText(data.getId());
			nameText.setText(data.getName());
		}
		
		public class BtnListener implements ActionListener{

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource() == button) {
					CollegeModel college = new CollegeModel();
					college.setId(idText.getText());
					college.setName(nameText.getText());
					if(college.getId().length() == 0 || college.getName().length() == 0) {
						JOptionPane.showMessageDialog(getContentPane(), "����������Ϣ��Ҫ��Ӵ��������©��");
						return;
					}
					if(button.getText().equals("���")) {
						if(collegeDao.insertdata(college)) {
							JOptionPane.showMessageDialog(getContentPane(), "�ɹ�");
							dispose();
						}else {
							JOptionPane.showMessageDialog(getContentPane(), "���ʧ�ܣ�������");
							return ;
						}
					}else if(button.getText().equals("�޸�")){
						if(collegeDao.updatedata(college, data.getId())) {
							JOptionPane.showMessageDialog(getContentPane(), "�ɹ�");
							dispose();
						}else {
							JOptionPane.showMessageDialog(getContentPane(), "�޸�ʧ�ܣ�������");
							return ;
						}
					}
				}
			}
			
		}
	}
}




