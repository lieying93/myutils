package com.sort;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
	private static  Properties prop = new Properties();// ���Լ��϶���   
	static {
	        FileInputStream fis=null;
			try {
				fis = new FileInputStream("config/config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// �����ļ�������   
	        try {
				prop.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// �������ļ���װ�ص�Properties������   
	        try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// �ر���   
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
        // ��Properties���ϱ��浽����   
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
		}// �ر��� 
		return true;
	}
	
	
	 public static void main(String[] args) throws Exception {   
	        // ��ȡ����ֵ��sitename�����ļ��ж���   
	        System.out.println("��ȡ����ֵ��sitename=" + ReadConfig.getValue("serialNum"));   
	        // ��ȡ����ֵ��countryδ���ļ��ж��壬���ڴ˳����з���һ��Ĭ��ֵ���������޸������ļ�   
	       // System.out.println("��ȡ����ֵ��country=" + prop.getProperty("country", "�й�"));
	        if(ReadConfig.setValue("serialNum", "10")){
	        	System.out.println("�������Գɹ�");
	        }else{
	        	System.out.println("����ʧ��");
	        }
	        System.out.println("��ȡ����ֵ��sitename=" + ReadConfig.getValue("serialNum"));   

	    } 
	 
}
