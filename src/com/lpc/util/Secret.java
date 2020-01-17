package com.lpc.util;
	
/**
 * 
 * @author xupt1602
 *字符串加密
 */
public class Secret {

	private char [] strArray;
	
	public Secret(String str) {
		// TODO 自动生成的构造函数存根
		strArray = str.toCharArray();
	}
	
	public Secret(char [] ch) {
		strArray = ch;
	}
	
	public String set() {
		String result = null;
		for(int i = 0 ; i < strArray.length ; i++) {
			strArray[i] = (char)(strArray[i]^30000);
		}
		result = new String(strArray);
		return result;
	}

}
