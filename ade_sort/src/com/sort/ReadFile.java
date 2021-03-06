package com.sort;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class ReadFile {
//	   以字符为单位读取文件，常用于读文本，数字等类型的文件
	    public static String readFileByChars(String fileName) {
	    	 
	        File file = new File(fileName);
            StringBuffer sb=new StringBuffer();
	        Reader reader = null;
	        try {
	            // 一次读一个字符
	            reader = new InputStreamReader(new FileInputStream(file));
	            int tempchar;
	            while ((tempchar = reader.read()) != -1) {
	                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
	                // 但如果这两个字符分开显示时，会换两次行。
	                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
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
//	            System.out.println("以字符为单位读取文件内容，一次读多个字节：");
//	            // 一次读多个字符
//	            char[] tempchars = new char[30];
//	            int charread = 0;
//	            reader = new InputStreamReader(new FileInputStream(fileName));
//	            // 读入多个字符到字符数组中，charread为一次读取字符数
//	            while ((charread = reader.read(tempchars)) != -1) {
//	                // 同样屏蔽掉\r不显示
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
	    		System.out.println("生成sql成功");
	    	}else{
	    		System.err.println("生成sql失败");
	    	}
		}
}
