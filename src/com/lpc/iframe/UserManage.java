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
import com.lpc.mode.LoginModel;
import com.lpc.util.ConnXupt;
import com.lpc.util.tools;

public class UserManage extends JDialog {

	/**
	 * 用户信息管理
	 */
	private static final long serialVersionUID = 1L;
	private JButton addBtn;
	private JButton deleteBtn;
	private JButton changeBtn;
	private JButton refreshBtn;
	private ContentPane contentPane;
	private BtnListener btnLis;
	private Dao dao;
	private DefaultTableModel model;
	private static String tableName = "login";

	public UserManage() {
		// TODO 自动生成的构造函数存根
		dao = new Dao(tableName);
		InitcontPane();
		InitDate();
	}
	
	private void InitcontPane() {
		setTitle("用户信息管理");
		setIconImage(Toolkit.getDefaultToolkit().createImage(Login.getImgURL()));
		setSize(700,330);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setLocation(new Point(0, 0));
		setLocationRelativeTo(null);
	
		addBtn =  new JButton("添加");
		deleteBtn = new JButton("删除");
		changeBtn = new JButton("修改");
		refreshBtn = new JButton("刷新");
		
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
		List loginList = dao.getList();
		if(loginList == null) {
			JOptionPane.showMessageDialog(getContentPane(), "抓取数据失败", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Vector title = new ConnXupt().getComment(tableName);
		title.remove(title.size()-1);
		model = new DefaultTableModel(title,loginList.size());
		for (int i  = 0; i < model.getRowCount(); i++) {
			LoginModel login = (LoginModel)loginList.get(i);
			model.setValueAt(login.getId(), i, 0);
			model.setValueAt(login.getName(), i, 1);
			model.setValueAt(login.getLimit(), i, 2);
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
					JOptionPane.showMessageDialog(getContentPane(), "请选择一行");
					return;
				}
				String id = contentPane.getValueAt(i, 0)+"";
				if(JOptionPane.showConfirmDialog(getContentPane(), "确定要删除"+id+"吗？", "删除确认", JOptionPane.YES_NO_OPTION) == 1) {
					return;
				}
				if(dao.deleteData(id)) {
					JOptionPane.showMessageDialog(getContentPane(), "删除成功");
					InitDate();
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "删除失败");
				}
			}else if(e.getSource() == changeBtn) {
				int i = contentPane.getSelectRow();
				if(i < 0) {
					JOptionPane.showMessageDialog(getContentPane(), "请选择一行");
					return;
				}
				LoginModel login = new LoginModel();
				login.setId(contentPane.getValueAt(i, 0));
				login.setName(contentPane.getValueAt(i, 1));
				login.setLimit(Integer.parseInt(contentPane.getValueAt(i, 2)));
				textJDialog = new TextJDialog(login);
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
		private JComboBox idBox;
		private JTextField nameText;
		private LoginModel data;
		private JComboBox limitBox;

		public TextJDialog() {
			super();
			setTitle("添加用户信息");
			button = new JButton("添加");
			InitJDialog();
		}
		
		public TextJDialog(LoginModel login) {
			super();
			setTitle("修改用户信息");
			button = new JButton("修改");
			this.data = login;
			InitJDialog();
			InitData();
		}
		
		private void InitJDialog() {
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			setIconImage(Toolkit.getDefaultToolkit().createImage(Login.getImgURL()));
			setModal(true);
			setLocation(new Point(0, 0));
			setLocationRelativeTo(null);
			
			JPanel contentPane = new JPanel();
			Vector title = new ConnXupt().getComment(tableName);
			setSize(250, (title.size()+2)*30+(title.size() + 1)*5);
			for(int i = 0; i < title.size() - 1; i++) {
				JLabel lab = new JLabel(title.get(i)+"");
				lab.setBounds(15,15+i*30+i*5,80,30);
				contentPane.add(lab);
			}
			idBox = new JComboBox();
			idBox.setBounds(100,15,120,30);
			nameText = new JTextField();
			nameText.setBounds(100,50,120,30);
			limitBox = new JComboBox();
			limitBox.setBounds(100,85,120,30); 
			for(int i = 1; i < 10; i++) {
				limitBox.addItem(i+"");
			}
			button.setBounds((getWidth() - 80)/2, 120, 80, 30);
			
			contentPane.setLayout(null);
			contentPane.add(idBox);
			contentPane.add(nameText);
			contentPane.add(limitBox);
			contentPane.add(button);
			setContentPane(contentPane);
			
			List idBoxList = new Dao("base_info").getIdList();
			for(int i = 0; i < idBoxList.size() ;i++) {
				idBox.addItem(idBoxList.get(i));
			}
			
			button.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					LoginModel login = new LoginModel();
					tools tool = new tools();
					login.setId(tool.Split(new tools().Split(idBox.getSelectedItem()+"")));
					login.setName(nameText.getText());
					login.setLimit(Integer.parseInt(tool.Split(limitBox.getSelectedItem()+"")));
					if(login.getId().length() == 0 || login.getName().length() == 0 || (login.getLimit() > 0 && login.getLimit() <= 9)) {
						JOptionPane.showMessageDialog(getContentPane(), "以上三项都是必填项，不能遗漏");
						return ;
					}
					if(button.getText().equals("添加")) {
						if(dao.insertdata(login)) {
							JOptionPane.showMessageDialog(getContentPane(), "添加成功");
							dispose();
						}else {
							JOptionPane.showMessageDialog(getContentPane(), "添加失败");
						}
					}else {
						if(dao.updatedata(login, data.getId())) {
							JOptionPane.showMessageDialog(getContentPane(), "修改成功");
							dispose();
						}else {
							JOptionPane.showMessageDialog(getContentPane(), "修改失败");
							InitData();
						}
					}
				}
			});
		}
		
		private void InitData() {
			if(data != null) {
				new tools().setSelectedItem(idBox, data.getId());
				nameText.setText(data.getName());
				limitBox.setSelectedItem(data.getLimit());
			}
		}
	}
}
