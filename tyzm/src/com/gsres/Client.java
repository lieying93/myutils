package com.gsres;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

public class Client {
    private String accout="";
    private String password="wsq0304";
    CloseableHttpClient client = HttpClients.createDefault();//ʵ����httpclient
    HttpResponse response = null;
    String rawHtml;
    
    public Client(String accout, String password) {
        super();
        this.accout = accout;
        this.password = password;
    }
 
    public Client() {
		// TODO Auto-generated constructor stub
	}

	public String login() {
		String result =""; 
        HttpGet getLoginPage = new HttpGet("http://tyzm.gsres.cn/index.php?app=Home&mod=Index&act=login");//���񴦵�½ҳ��get
        
        try {
            //�򿪽���
            client.execute(getLoginPage);
            //��ȡ��֤��
            getVerifyingCode();
            
            //�����û���������֤��
            System.out.println("verifying code has been save as verifyCode.jpeg, input its content");
            String code=null;
            Scanner in = new Scanner(System.in);
            code = in.nextLine();
            in.close();
            
            //�趨post����������ͼ�е�����һ��
            ArrayList<BasicNameValuePair> postData = new ArrayList<BasicNameValuePair>();
            postData.add(new BasicNameValuePair("inputName", "622822196703044525"));
            postData.add(new BasicNameValuePair("verifyCode", code));
            postData.add(new BasicNameValuePair("unreset", "1"));//��֤��
            
            HttpPost post = new HttpPost("http://tyzm.gsres.cn/index.php?app=Home&mod=Ajax&act=getLoginName");//����post����
            post.setEntity(new UrlEncodedFormEntity(postData));//�������
            response = client.execute(post);//ִ�е�½��Ϊ
            rawHtml = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(rawHtml);
            result = rawHtml;
        } catch (ClientProtocolException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    
  private  void getVerifyingCode() {
        HttpGet getVerifyCode = new HttpGet("http://tyzm.gsres.cn/index.php?app=Home&mod=Ajax&act=getVerifyCode");//��֤��get
        FileOutputStream fileOutputStream = null;
        HttpResponse response;
        try {
            response = client.execute(getVerifyCode);//��ȡ��֤��
            /*��֤��д���ļ�,��ǰ���̵ĸ�Ŀ¼,����ΪverifyCode.jped*/
            fileOutputStream = new FileOutputStream(new File("D:/verifycode.png"));
            response.getEntity().writeTo(fileOutputStream);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
    public JSONObject parseGetLoginName(String result){
    	
    	JSONObject jsonObj = JSONObject.fromObject(result);
    	String status = jsonObj.getString("status");
    	if("200".equals(status)){
    		JSONObject userInfo = jsonObj.getJSONObject("data"); 
    		userInfo.put("isZjUser", 0);
    		userInfo.put("password", this.password);
    	}
  	    return jsonObj;
    }
    public static void main(String[] args) {
		Client client = new Client();
		String getLoginNameResult = client.login();
		client.parseGetLoginName(getLoginNameResult);
	}
}

