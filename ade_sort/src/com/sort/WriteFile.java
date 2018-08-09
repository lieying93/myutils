package com.sort;

import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
	public static 	boolean saveSql(String sql){
		FileWriter writer;
        try {
        	String fileName = ReadConfig.getValue("outfile");
            writer = new FileWriter(fileName);
            writer.write(sql);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
		return true;
	}
}
