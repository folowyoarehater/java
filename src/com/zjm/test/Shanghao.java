package com.zjm.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;

public class Shanghao{

	public void readFileToJsonArrayFile(String filePath, int totalNumber, int devices){
		int spiltNumber = totalNumber / devices;
		if (totalNumber % devices != 0) {
			spiltNumber++;
		}
		JSONArray array = new JSONArray();
		File file = new File(filePath);
		List<JSONArray> arrayList = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(file))){
			String line = null;
			while ((line = reader.readLine()) != null && !line.equals("")) {
				String[] strings = line.split("----");// 这里的规则需要根据文档更改，注意查看打印出来的文件数据格式，并做相应调整
				array.add(strings[0].trim() + " " + strings[1].trim());
				if ((array.size() % spiltNumber) == 0) {
					arrayList.add(array);
					System.out.println(array);
					System.out.println(array.size());
					array = new JSONArray();
				}
			}
			// 读完之后自动把资源关闭，所以while下面的代码不执行
		} catch (Exception e) {
			array = new JSONArray();
		}
		String fileName = file.getName();
		fileName = fileName.substring(0, fileName.indexOf("."));
		System.out.println(fileName);
		if (arrayList.size() > 0) {
			for(int i = 0; i < arrayList.size(); i++){
				JSONArray eachArray = arrayList.get(i);
				File writeToFile = new File(filePath.substring(0, filePath.lastIndexOf("/") + 1) + fileName + "/" + i);
				if (!writeToFile.exists()) {
					System.out.println(writeToFile.getAbsolutePath());
					writeToFile.mkdirs();
				}
				writeToFile = new File(writeToFile, "shanghao.txt");
				try (BufferedWriter writer = new BufferedWriter(new FileWriter(writeToFile))){
					writer.write(eachArray.toJSONString());
					writer.flush();
				} catch (Exception e) {
					System.out.println(e.toString());
				}
			}
		}
		if (array.size() > 0) {
			// 将剩余的个数放到一个新的文件夹下
			File writeToFile = new File(filePath.substring(0, filePath.lastIndexOf("/") + 1) + fileName + "/" + arrayList.size());
			if (!writeToFile.exists()) {
				System.out.println(writeToFile.getAbsolutePath());
				writeToFile.mkdirs();
			}
			writeToFile = new File(writeToFile, "shanghao.txt");
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(writeToFile))){
				writer.write(array.toJSONString());
				writer.flush();
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			System.out.println("剩余个数：" + array.size());
		}
	}

}
