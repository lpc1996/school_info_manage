package com.lpc.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
	
	private File file;

	public ReadFile(String filePath) {
		// TODO Auto-generated constructor stub
		file = new File(filePath);
	}
	
	public String[] ReadVersion() throws IOException {
		String [] version = new String[2];
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String strLine = null;
		int i = 0;
		while((strLine = bufferedReader.readLine()) != null) { 
			strLine = strLine.trim();
			version[i] = strLine;
			i++;
			if(i == 2) {
				break;
			}
		}
		
		return version;
	}
}
