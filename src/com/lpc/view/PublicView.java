package com.lpc.view;

import javax.swing.JInternalFrame;

public class PublicView extends JInternalFrame{

	public PublicView(String title) {
		// TODO 自动生成的构造函数存根
		super(title);
		setResizable(true);// 设置允许自由调整大小
		setClosable(true);// 设置提供关闭按钮
		setIconifiable(true);// 设置提供图标化按钮
		setMaximizable(true);// 设置提供最大化按钮
	}

}
