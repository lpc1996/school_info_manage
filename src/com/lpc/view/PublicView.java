package com.lpc.view;

import javax.swing.JInternalFrame;

public class PublicView extends JInternalFrame{

	public PublicView(String title) {
		// TODO �Զ����ɵĹ��캯�����
		super(title);
		setResizable(true);// �����������ɵ�����С
		setClosable(true);// �����ṩ�رհ�ť
		setIconifiable(true);// �����ṩͼ�껯��ť
		setMaximizable(true);// �����ṩ��󻯰�ť
	}

}
