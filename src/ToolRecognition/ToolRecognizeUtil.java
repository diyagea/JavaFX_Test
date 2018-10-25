package ToolRecognition;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ToolRecognizeUtil {
	
	
	public static String startRecognize(String path) {
		String result = null;
		//do recoginze tool action
		writeCommand(path);
		boolean flag = true;
		int count = 0;
		while (flag) {
			try {
				//loop 2 sec to read tool info
				Thread.sleep(2000);
			} catch (Exception e) {
			}

			String data = readToolInfo(path);
			if (data != null && data.length() > 0) {
				result = data;
				flag = false;
			}

			//count > 10 cancel to read
			if(count > 10){
				flag = false;
			}
			count++;
		}
		return result;
	}

	/*
	 * write command to drive camera
	 */
	private static void writeCommand(String path) {
		File f = new File(path + "相机控制.txt");
		String content = "1";
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			if (!f.exists()) {
				f.createNewFile();
			}
			//fw = new FileWriter(f.getAbsoluteFile(), true); //true表示可以追加新内容  
			fw = new FileWriter(f.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * get recoginzed tool info
	 */
	private static String readToolInfo(String path) {
		String s = "";
		File f = new File(path + "识别信息.txt");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f));
			String temp;
			while ((temp = br.readLine()) != null) {
				s += temp;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("file content:" + s);
		return s;
	}

}
