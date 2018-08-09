package com.sort;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class ReadFile {
//	   ���ַ�Ϊ��λ��ȡ�ļ��������ڶ��ı������ֵ����͵��ļ�
	    public static String readFileByChars(String fileName) {
	    	 
	        File file = new File(fileName);
            StringBuffer sb=new StringBuffer();
	        Reader reader = null;
	        try {
	            // һ�ζ�һ���ַ�
	            reader = new InputStreamReader(new FileInputStream(file));
	            int tempchar;
	            while ((tempchar = reader.read()) != -1) {
	                // ����windows�£�\r\n�������ַ���һ��ʱ����ʾһ�����С�
	                // ������������ַ��ֿ���ʾʱ���ỻ�����С�
	                // ��ˣ����ε�\r����������\n�����򣬽������ܶ���С�
	                if (((char) tempchar) != '\n'||((char) tempchar) != '\r') {
	                    sb.append((char) tempchar);
	                }
	            }
	            reader.close();
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "";
	        }
	        
//	        try {
//	            System.out.println("���ַ�Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�����ֽڣ�");
//	            // һ�ζ�����ַ�
//	            char[] tempchars = new char[30];
//	            int charread = 0;
//	            reader = new InputStreamReader(new FileInputStream(fileName));
//	            // �������ַ����ַ������У�charreadΪһ�ζ�ȡ�ַ���
//	            while ((charread = reader.read(tempchars)) != -1) {
//	                // ͬ�����ε�\r����ʾ
//	                if ((charread == tempchars.length)
//	                        && (tempchars[tempchars.length - 1] != '\r')) {
//	                    System.out.print(tempchars);
//	                } else {
//	                    for (int i = 0; i < charread; i++) {
//	                        if (tempchars[i] == '\r') {
//	                            continue;
//	                        } else {
//	                            System.out.print(tempchars[i]);
//	                        }
//	                    }
//	                }
//	            }
//
//	        } catch (Exception e1) {
//	            e1.printStackTrace();
//	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                    return sb.toString();
	                } catch (IOException e1) {
	                }
	            }
	            String content=sb.toString().trim();
	            return content;
	            
	        }
	    
	    public static void main(String[] args) {
	    	  String fileName =  ReadConfig.getValue("infile");
	    	  String content = ReadFile.readFileByChars(fileName);
	    	//  System.out.println(content);
	    	String sql =  SortSql.getQuery(content);
	    	System.out.println(sql);
	    	if(WriteFile.saveSql(sql)){
	    		System.out.println("����sql�ɹ�");
	    	}else{
	    		System.err.println("����sqlʧ��");
	    	}
		}
}
