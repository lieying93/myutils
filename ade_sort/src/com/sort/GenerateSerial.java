package com.sort;

import javax.swing.JOptionPane;

public class GenerateSerial {
	 public static String  generateNum(String content){
		 String con = content.trim();
		 int num=0;
		 StringBuffer sb = new StringBuffer("");
		 try {
			num = Integer.parseInt(con);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			num=0;
			JOptionPane.showMessageDialog(null, "Ҫ�����������������", "���ִ��� ", JOptionPane.ERROR_MESSAGE);
		}
		 if(num!=0){
			 //�õ�������ŵĸ���,Ĭ��Ϊ20��,�����������ļ��� �����޸�
			 String sSerialNum = ReadConfig.getValue("serialNum").trim();
			 int serialNum = Integer.parseInt(sSerialNum);
			 for(int i=0;i<serialNum;i++){
				 sb.append(num+"\n");
				 num = num+10;
			 }
		 }
		 return sb.toString();
	 }
	 public static void main(String[] args) {
		String con =  GenerateSerial.generateNum("3010");
		System.out.print(con);
	}
}
