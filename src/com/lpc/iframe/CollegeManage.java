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
	 * 学院信息管理
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
		this.setTitle("学院信息管理");
		setIconImage(Toolkit.getDefaultToolkit().createImage(Login.getImgURL()));
		setSize(600,330);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setLocation(new Point(0, 0));
		setLocationRelativeTo(null);
		
		contentPane = new ContentPane(new Dimension(600, 360));
		this.setContentPane(contentPane);
		
		JButton addBtn = new JButton("添加");
		addBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				text = new CollegeText("添加学院信息");
				text.setVisible(true);
				InitData();
			}
		});
		contentPane.setAddBtn(addBtn);
		JButton changeBtn = new JButton("修改");
		changeBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				int i = contentPane.getSelectRow();
				if(i < 0) {
					JOptionPane.showMessageDialog(getContentPane(), "请选择一行");
					return;
				}
				CollegeModel data = new CollegeModel();
				data.setId(contentPane.getValueAt(i, 0)+"");
				data.setName(contentPane.getValueAt(i, 1)+"");
				text = new CollegeText("修改学院信息", data);
				text.setVisible(true);
				InitData();
			}
		});
		contentPane.setChangeBtn(changeBtn);
		JButton deleteBtn = new JButton("删除");
		deleteBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				int i = contentPane.getSelectRow();
				if(i < 0) {
					JOptionPane.showMessageDialog(getContentPane(), "请选择一行");
					return;
				}
				String id = contentPane.getValueAt(i, 0)+"";
				if(JOptionPane.showConfirmDialog(getContentPane(), "确定要删除"+id+"吗？", "删除确认", JOptionPane.YES_NO_OPTION) == 1) {
					return;
				}
				if(dao.deleteData(id)) {
					JOptionPane.showMessageDialog(null, "删除"+id+"成功");
					InitData();
				}else {
					JOptionPane.showMessageDialog(null, "删除"+id+"失败");
				}
			}
		});
		contentPane.setDeleteBtn(deleteBtn);
		JButton refreshBtn = new JButton("刷新");
		refreshBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
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
			button.setText("添加");
			InitJDialog();
		}
		
		public CollegeText(String title,CollegeModel data) {
			super();
			this.title = title;
			button = new JButton("修改");
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
			JLabel idLab = new JLabel("ID：");
			idLab.setBounds(15,15,80,30);
			idText = new JTextField();
			idText.setBounds(100,15,120,30);
			JLabel nameLab = new JLabel("名称：");
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
						JOptionPane.showMessageDialog(getContentPane(), "上面两项信息都要填哟，不能遗漏！");
						return;
					}
					if(button.getText().equals("添加")) {
						if(collegeDao.insertdata(college)) {
							JOptionPane.showMessageDialog(getContentPane(), "成功");
							dispose();
						}else {
							JOptionPane.showMessageDialog(getContentPane(), "添加失败，请重试");
							return ;
						}
					}else if(button.getText().equals("修改")){
						if(collegeDao.updatedata(college, data.getId())) {
							JOptionPane.showMessageDialog(getContentPane(), "成功");
							dispose();
						}else {
							JOptionPane.showMessageDialog(getContentPane(), "修改失败，请重试");
							return ;
						}
					}
				}
			}
			
		}
	}
}




