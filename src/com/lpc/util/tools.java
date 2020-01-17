package com.lpc.util;

import javax.swing.JComboBox;

public class tools {

	public tools() {
		// TODO Auto-generated constructor stub
	}
	
	public String Split(String str) {
		String [] strArray = str.split(" ");
		return strArray[0];
	}
	
	public void setSelectedItem(JComboBox box,String item) {
		for(int i = 0; i < box.getItemCount(); i++) {
			if(item.equals(Split(box.getItemAt(i)+"")))
				box.setSelectedIndex(i);
		}
	}
	
	public static void main(String [] argv) {
		tools tool = new tools();
		System.out.println(tool.Split("nih asdf")); 
	}
}
