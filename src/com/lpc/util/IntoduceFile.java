package com.lpc.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class IntoduceFile {

	private File file;
	private String filePath;

	public IntoduceFile() {
		// TODO Auto-generated constructor stub
	}
	
	public List readIntroduce(String fileName) throws IOException {
		List introduceList = null;
		filePath = "bin\\com\\lpc\\lib\\introduce\\"+fileName; 
		file = new File(fileName);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String strLine = null;
		introduceList = new ArrayList();
		while((strLine = bufferedReader.readLine()) != null) {
			strLine = strLine.trim();
			introduceList.add(strLine);
		}
		return introduceList;
	}
	
	public boolean createFile(String fileName,List content) throws IOException{
		/**
		 * fileName 完整的文件名带扩展名
		 * content  要写入文件的内容
		 */
		boolean isOk =  false;
		filePath = "bin\\com\\lpc\\lib\\introduce\\"+fileName;
		file = new File(filePath);
		if(!file.exists()) {
			file.createNewFile();
			isOk = true;
			FileWriter write = new FileWriter(file,true);
			for(int i = 0; i < content.size() ; i++) {
				write.append(content.get(i)+"");
			}
			write.flush();
			write.close();
		}else {
			isOk = false;
			JOptionPane.showMessageDialog(null, fileName+"已存在");
			return false;
		}
		
		return isOk;
	}
	
	public boolean writeContent(String content,String fileName) throws IOException{
		boolean isOk = false;
		filePath = "bin\\com\\lpc\\lib\\introduce\\"+fileName;
		file = new File(filePath);
		if(file.exists()) {
			FileWriter write = new FileWriter(file,true);
			write.append(content);
			write.flush();
			write.close();
			isOk = true;
		}
		return isOk;
	}

}
