package com.sort;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
	private static  Properties prop = new Properties();// 属性集合对象   
	static {
	        FileInputStream fis=null;
			try {
				fis = new FileInputStream("config/config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// 属性文件输入流   
	        try {
				prop.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// 将属性文件流装载到Properties对象中   
	        try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// 关闭流   
	}
	public static String getValue(String key) {
		return prop.getProperty(key,"unknown");
	}
	public static boolean setValue(String key,String con) {
		prop.setProperty(key,con);
		FileOutputStream fos=null;
		try {
			fos = new FileOutputStream("config/config.properties");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}   
        // 将Properties集合保存到流中   
        try {
			prop.store(fos, "Copyright (c)");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}   
        try {
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 关闭流 
		return true;
	}
	
	
	 public static void main(String[] args) throws Exception {   
	        // 获取属性值，sitename已在文件中定义   
	        System.out.println("获取属性值：sitename=" + ReadConfig.getValue("serialNum"));   
	        // 获取属性值，country未在文件中定义，将在此程序中返回一个默认值，但并不修改属性文件   
	       // System.out.println("获取属性值：country=" + prop.getProperty("country", "中国"));
	        if(ReadConfig.setValue("serialNum", "10")){
	        	System.out.println("设置属性成功");
	        }else{
	        	System.out.println("设置失败");
	        }
	        System.out.println("获取属性值：sitename=" + ReadConfig.getValue("serialNum"));   

	    } 
	 
}
