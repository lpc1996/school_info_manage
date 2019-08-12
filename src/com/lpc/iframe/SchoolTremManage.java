package com.lpc.iframe;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.lpc.dao.Dao;
import com.lpc.mode.SchoolTremModel;
import com.lpc.util.DateChooser;
import com.lpc.util.tools;

public class SchoolTremManage extends JDialog{

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

	public SchoolTremManage() {
		// TODO Auto-generated constructor stub
		super();
		dao = new Dao("school_trem");
		InitJDialog();
		InitDate();
	}
	
	public void InitJDialog() {
		setTitle("ѧ����Ϣ����");
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
	
	private void InitDate() {
		List SYList = dao.getList();
		String [] title = {"ѧ�ڱ��","ѧ������","ѧ����","��ʼʱ��","��ֹʱ��"};
		model = new DefaultTableModel(title,SYList.size());
		for (int i  = 0; i < model.getRowCount(); i++) {
			SchoolTremModel school = (SchoolTremModel)SYList.get(i);
			model.setValueAt(school.getId(), i, 0);
			model.setValueAt(school.getName(), i, 1);
			model.setValueAt(school.getSYId(), i, 2);
			model.setValueAt(school.getBegin(), i, 3);
			model.setValueAt(school.getEnd(), i, 4);
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
				SchoolTremModel ST = new SchoolTremModel();
				ST.setId(contentPane.getValueAt(i, 0)+"");
				ST.setName(contentPane.getValueAt(i, 1)+"");
				ST.setSYId(contentPane.getValueAt(i, 2)+"");
				ST.setBegin(contentPane.getValueAt(i, 3)+"");
				ST.setEnd(contentPane.getValueAt(i, 4)+"");
				textJDialog = new TextJDialog(ST);
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
		private JTextField endText;
		private SchoolTremModel data;
		private JTextField beginText;
		private JComboBox schoolYearBox;

		public TextJDialog() {
			super();
			setTitle("���ѧ����Ϣ");
			button = new JButton("���");
			InitJDialog();
		}
		
		public TextJDialog(SchoolTremModel data) {
			super();
			setTitle("�޸�ѧ����Ϣ");
			button = new JButton("�޸�");
			this.data = data;
			InitJDialog();
			InitData();
		}
		
		private void InitJDialog() {
			setSize(250, 280);
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			setIconImage(Toolkit.getDefaultToolkit().createImage(Login.getImgURL()));
			setModal(true);
			setLocation(new Point(0, 0));
			setLocationRelativeTo(null);
			
			JLabel idLab = new JLabel("ѧ�ڴ��ţ�");
			idLab.setBounds(15,15,80,30);
			idText = new JTextField();
			idText.setBounds(100,15,120,30);
			JLabel nameLab = new JLabel("���ƣ�");
			nameLab.setBounds(15,50,80,30);
			nameText = new JTextField();
			nameText.setBounds(100,50,120,30);
			JLabel schoolYearLab = new JLabel("ѧ����ţ�");
			schoolYearLab.setBounds(15,85,80,30);
			schoolYearBox = new JComboBox();
			schoolYearBox.setBounds(100,85,120,30);
			JLabel beginLab = new JLabel("��ʼʱ�䣺");
			beginLab.setBounds(15,120,80,30);
			beginText = new JTextField();
			DateChooser dateChooser = DateChooser.getInstance("yyyy-MM-dd");
			dateChooser.register(beginText);
			beginText.setBounds(100,120,120,30);
			JLabel endLab = new JLabel("��ֹʱ�䣺");
			endLab.setBounds(15,155,80,30);
			endText = new JTextField();
			DateChooser dateChooser2 = DateChooser.getInstance("yyyy-MM-dd");
			dateChooser2.register(endText);
			endText.setBounds(100,155,120,30);
			button.setBounds((getWidth() - 80)/2,190,80,30);
			
			JPanel contentPane = new JPanel();
			contentPane.setLayout(null);
			contentPane.add(idLab);
			contentPane.add(idText);
			contentPane.add(nameLab);
			contentPane.add(nameText);
			contentPane.add(schoolYearLab);
			contentPane.add(schoolYearBox);
			contentPane.add(beginLab);
			contentPane.add(beginText);
			contentPane.add(endLab);
			contentPane.add(endText);
			contentPane.add(button);
			setContentPane(contentPane);
			
			button.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					SchoolTremModel ST = new SchoolTremModel();
					tools tool = new tools();
					ST.setId(idText.getText());
					ST.setName(nameText.getText());
					ST.setSYId(tool.Split(schoolYearBox.getSelectedItem()+""));
					ST.setBegin(beginText.getText());
					ST.setEnd(endText.getText());
					if(ST.getId().length() == 0 || ST.getName().length() == 0 || ST.getSYId().length() == 0 ||ST.getBegin().length() == 0 || ST.getEnd().length() == 0) {
						JOptionPane.showMessageDialog(getContentPane(), "��������Ǳ����������©");
						return ;
					}
					if(button.getText().equals("���")) {
						if(dao.insertdata(ST)) {
							JOptionPane.showMessageDialog(getContentPane(), "��ӳɹ�");
							dispose();
						}else {
							JOptionPane.showMessageDialog(getContentPane(), "���ʧ��");
						}
					}else {
						if(dao.updatedata(ST, data.getId())) {
							JOptionPane.showMessageDialog(getContentPane(), "�޸ĳɹ�");
							dispose();
						}else {
							JOptionPane.showMessageDialog(getContentPane(), "�޸�ʧ��");
							InitData();
						}
					}
				}
			});
			List SYIdList = new Dao("school_year").getIdList();
			for(int i = 0; i < SYIdList.size(); i++) {
				schoolYearBox.addItem(SYIdList.get(i));
			}
		}
		
		private void InitData() {
			if(data != null) {
				idText.setText(data.getId());
				nameText.setText(data.getName());
				new tools().setSelectedItem(schoolYearBox, data.getSYId());
				beginText.setText(data.getBegin());
				endText.setText(data.getEnd());
			}
		}
	}

}
