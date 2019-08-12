package com.lpc.iframe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ContentPane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JPanel optionPane;
	private JTextField searchText;
	private JButton searchBtn;

	public ContentPane(Dimension size) {
		// TODO 自动生成的构造函数存根
		this.setSize(size);
		this.setBorder(BorderFactory.createTitledBorder("管理一体化"));
		this.setLayout(null);
		InitPane();
	}

	private void InitPane() {
		JScrollPane tableJs = new JScrollPane();
		tableJs.setBounds(15,15,getWidth()-45,getHeight()-170);
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableJs.setViewportView(table);
		
		optionPane = new JPanel();
		optionPane.setLayout(null);
		optionPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		optionPane.setBounds(new Rectangle(15, getHeight()-150, tableJs.getWidth(), 75));
		JLabel searchLab = new JLabel("请输入要查询的代号或名称：");
		searchLab.setBounds(15,2,180,30);
		searchText = new JTextField();
		searchText.setBounds(200,2,200,30);
		searchBtn = new JButton("查询");
		searchBtn.setBounds(405,2,80,30);
		optionPane.add(searchLab);
		optionPane.add(searchText);
		optionPane.add(searchBtn);
		add(tableJs);
		add(optionPane);
		
		searchBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				String str = searchText.getText();
				if(str.length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入代号或名称", "友情提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				int i = 0;
				for(; i < table.getRowCount(); i++) {
					if(table.getValueAt(i, 0).equals(str) || table.getValueAt(i, 1).equals(str)) {
						table.setRowSelectionInterval(i, i);
						table.scrollRectToVisible(table.getCellRect(i, 0, true));
						break;
					}
				}
				if(i == table.getRowCount()) {
					JOptionPane.showMessageDialog(null, "找不到"+str);
				}
			}
		});
	}
	
	public void setAddBtn(JButton addBtn) {
		addBtn.setBounds(15,35,80,30);
		optionPane.add(addBtn);
		optionPane.updateUI();
	}
	
	public void setChangeBtn(JButton changeBtn) {
		changeBtn.setBounds(100,35,80,30);
		optionPane.add(changeBtn);
		optionPane.updateUI();
	}
	
	public void setDeleteBtn(JButton deleteBtn) {
		deleteBtn.setBounds(185,35,80,30);
		optionPane.add(deleteBtn);
		optionPane.updateUI();
	}
	
	public void setRefreshBtn(JButton refreshBtn) {
		refreshBtn.setBounds(270,35,80,30);
		optionPane.add(refreshBtn);
		optionPane.updateUI();
	}
	
	public void setTableModel(DefaultTableModel model) {
		table.setModel(model);
	}
	
	public int getSelectRow() {
		return table.getSelectedRow();
	}
	
	public String getValueAt(int row,int column) {
		return table.getValueAt(row, column)+"";
	}
	public static void main(String [] argv) {
		ContentPane cont = new ContentPane(new Dimension(600,330));
		JFrame jf = new JFrame("测试");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(new Dimension(600, 360));
		jf.setContentPane(cont);
		jf.setVisible(true);
	}

}
