package com.gsres;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class Login {
	static HttpClient client = new HttpClient();
    private static String username="622822196703044525";
    private static String password= "wsq0304";
	public static void main(String[] args) {
		String valCode = getImage();
		System.out.println("��֤��Ϊ��" + valCode);
		postRequest(username,password,valCode);
	}
	//ģ���¼
	 private static void postRequest(String username, String password,String valCode) {
	        PostMethod postMethod = new PostMethod("http://tyzm.gsres.cn/index.php?app=Home&mod=Ajax&act=getLoginName");
	        NameValuePair[] data = {
	        		new NameValuePair("inputName", username),
	                new NameValuePair("verifyCode", username),
	                new NameValuePair("unreset", "1") };
	        postMethod.setRequestBody(data);
	        try {
	            client.executeMethod(postMethod);
	            String text = postMethod.getResponseBodyAsString();
	            System.out.println("text="+text);
	            // ƥ���������Ƿ���ƥ����ַ���
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	
	//��ȡ��֤��ֵ
	private static String getImage(){
		File storeFile =null;
		GetMethod get = new GetMethod("http://tyzm.gsres.cn/index.php?app=Home&mod=Ajax&act=getVerifyCode");
		try {
			client.executeMethod(get);
			storeFile = new File("D:/verifycode.png"); // �����ڱ��ص�·��
			InputStream is = get.getResponseBodyAsStream();
			FileOutputStream fos = new FileOutputStream(storeFile);
			byte[] b = new byte[1024];
			while ((is.read(b)) != -1) {
				fos.write(b);
			}
			is.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String valCode = null;
		try {
			valCode = new OCR().recognizeText(storeFile, "png");
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valCode;
	}
}
