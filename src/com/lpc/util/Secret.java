package com.lpc.util;
	
/**
 * 
 * @author xupt1602
 *�ַ�������
 */
public class Secret {

	private char [] strArray;
	
	public Secret(String str) {
		// TODO �Զ����ɵĹ��캯�����
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
