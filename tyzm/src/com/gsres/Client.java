package com.gsres;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import net.sf.json.JSONObject;

public class Client {
    private String accout="";
    private String password="wsq0304";
    CloseableHttpClient client = HttpClients.createDefault();//实例化httpclient
    HttpResponse response = null;
    String rawHtml;
	private CloseableHttpResponse execute=null;
	private HttpEntity entity = null;

	public String login() {
		String result =""; 
        HttpGet getLoginPage = new HttpGet("http://tyzm.gsres.cn/index.php?app=Home&mod=Index&act=login");//教务处登陆页面get
        try {
            //打开教务处
      //      execute = client.execute(getLoginPage);
            //获取验证码
            getVerifyingCode();
            //提醒用户并输入验证码
            String code=Client.getImage("D:/verifycode.png");
            System.out.println("code="+code);
//            Scanner in = new Scanner(System.in);
//            code = in.nextLine();
//            in.close();
            //设定post参数，和上图中的内容一致
            HttpPost post = new HttpPost("http://tyzm.gsres.cn/index.php?app=Home&mod=Ajax&act=getLoginName");//构建post对象
            ArrayList<BasicNameValuePair> postData = new ArrayList<BasicNameValuePair>();
            postData.add(new BasicNameValuePair("inputName", "622822198111064513"));
            postData.add(new BasicNameValuePair("verifyCode", code));
            postData.add(new BasicNameValuePair("unreset", "1"));//验证码
            post.setEntity(new UrlEncodedFormEntity(postData));//捆绑参数
            response = client.execute(post);//执行登陆行为
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
        HttpGet getVerifyCode = new HttpGet("http://tyzm.gsres.cn/index.php?app=Home&mod=Ajax&act=getVerifyCode");//验证码get
        FileOutputStream fileOutputStream = null;
        HttpResponse response;
        try {
            response = client.execute(getVerifyCode);//获取验证码
            /*验证码写入文件,当前工程的根目录,保存为verifyCode.jped*/
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
    	JSONObject userInfo = null;
    	String status = jsonObj.getString("status");
    	if("200".equals(status)){
    	    userInfo = jsonObj.getJSONObject("data"); 
    		userInfo.put("isZjUser", 0);
    		userInfo.put("password", this.password);
    	}
  	    return userInfo;
    }
    public JSONObject getLoginTicket() throws Exception{
    	String ssoRestUrl = "http://sso.edu.gsres.cn/sso//login?service=http%3A%2F%2Fi.changyan.com%2Ficloud%3Ffrom%3Dew";
    	HttpGet sLogin = new HttpGet(ssoRestUrl);
    	execute = client.execute(sLogin);
    	String  loginContent =EntityUtils.toString(execute.getEntity(), "utf-8"); 
    	loginContent = loginContent.replace("/\t/g", "")
    			                   .replace("\\", "")
    			                   .replace("(", "")
    			                   .replace(")", "")
    			                   .replace("'", "");
  //  	System.out.println(loginContent);
    	JSONObject jsonObj = JSONObject.fromObject(loginContent);
    	JSONObject jsonData = jsonObj.getJSONObject("data");
    	System.out.println("jsonData-lt="+jsonData.getString("lt"));
    	System.out.println("jsonData-execution="+jsonData.getString("execution"));
    	return jsonData;
    }
    public String getSSOLogin(JSONObject userInfo,JSONObject ticketData) throws Exception{
    	String url = "http://sso.edu.gsres.cn/sso//login";
    	
    	ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
    	params.add(new BasicNameValuePair("_eventId", "submit"));
    	params.add(new BasicNameValuePair("appId", "KtSNKxk3"));
    	params.add(new BasicNameValuePair("callback", "jQuery17209531084851449882_1535173119476"));
    	params.add(new BasicNameValuePair("encode", "true"));
    	params.add(new BasicNameValuePair("execution", ticketData.getString("execution")));
    	params.add(new BasicNameValuePair("key", "login_name"));
    	params.add(new BasicNameValuePair("lt", ticketData.getString("lt")));
    	//王淑琴登录密码
    	//params.add(new BasicNameValuePair("password", "893b23f52c1e8eefb5e27b0e365a5ec3"));
    	//李维科登录密码
    	params.add(new BasicNameValuePair("password", "7e993773c6570a3e1a74500bdb3d70a2"));
    	params.add(new BasicNameValuePair("service", "http://i.changyan.com/icloud?from=ew"));
    	params.add(new BasicNameValuePair("username",userInfo.getString("loginName") ));
    	String  str = EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8));
    	HttpGet sLogin = new HttpGet(url + "?" + str);
    	execute = client.execute(sLogin );
    	String  content =EntityUtils.toString(execute.getEntity(), "utf-8");
    	System.out.println("content="+content);
    	return "";
    }
    public String index() throws Exception{
    	String url = "http://www.gsres.cn/cloud/index.php?from=ew";
    	HttpGet sLogin = new HttpGet(url);
    	execute = client.execute(sLogin );
    	String  content =EntityUtils.toString(execute.getEntity(), "utf-8");
    	//System.out.println("index-->content="+content);
    	return "";
    }
    public String gsjsxy () throws Exception{
    	String url = "http://tyzm.gsres.cn/index.php?app=Home&mod=App&act=openApp&appId=4000000000000000013";
    	HttpGet sLogin = new HttpGet(url);
    	execute = client.execute(sLogin );
    	String  content =EntityUtils.toString(execute.getEntity(), "utf-8");
    	System.out.println("gsjsxy-->content="+content);
    	return "";
    }
    public String readHistory() throws Exception{
    	String url = "http://rwsy.gsres.cn/wx/readhistory.htm";
    	HttpGet sLogin = new HttpGet(url);
    	execute = client.execute(sLogin );
    	String  content =EntityUtils.toString(execute.getEntity(), "utf-8");
    	System.out.println("readhistory-->content="+content);
    	return "";
    	
    }
    public String[][] unRead() throws Exception{
    	//每页20条记录,每次运行只读取首页的未读记录数,第一列是href地址,第二列是标题
    	String[][] unRead = new String [20][2];
    	String url = "http://rwsy.gsres.cn/wx/unreadhistory.htm";
    	HttpGet sLogin = new HttpGet(url);
    	execute = client.execute(sLogin );
    	String  content =EntityUtils.toString(execute.getEntity(), "utf-8");
    	System.out.println("unreadhistory-->content="+content);
    	Document doc = Jsoup.parse(content);
    	Elements liArray = doc.select("div.Main").select("ul").select("li");
    	for(int i =0; i< liArray.size();i++){
    		System.out.println("第"+(i+1)+"条未读记录:");
    		System.out.println("标题:"+liArray.get(i).select("div.text_qishu").html());
    		System.out.println("链接:"+liArray.get(i).select("a").attr("href"));
    		unRead[i][0]=liArray.get(i).select("a").attr("href");
    		unRead[i][1]=liArray.get(i).select("div.text_qishu").html();
    	}
    	return unRead;
    }
    public String read(String href,String title) throws Exception{
    	String unReadHref = "http://rwsy.gsres.cn/wx/read.htm";
    	System.out.println(unReadHref+href.replace("content.htm", ""));
    	HttpGet readUrl = new HttpGet(unReadHref+href.replace("content.htm", ""));
    	execute = client.execute(readUrl );
    	String  content =EntityUtils.toString(execute.getEntity(), "utf-8");
    	System.out.println("doingRead-->content="+content);
    	return "";
    }
    public static String getImage(String  fileName){
		File storeFile =new File(fileName);
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
    public static void main(String[] args) throws Exception {
		Client client = new Client();
		String getLoginNameResult = client.login();
		//王淑琴登录信息
		//String getLoginNameResult="{\"status\":200,\"data\":{\"inputType\":\"id_card_no\",\"oldLoginName\":\"\\u738b\\u6dd1\\u7434\",\"loginName\":\"gsres_iflytek_d9dd7d07a7ca9c9a5c7c33f510afddc8\",\"isOldUser\":1,\"reviewStatus\":\"110002\",\"userType\":\"002\",\"userId\":\"3162000039000195022\"}}";
		//李维科登录信息
		/*String getLoginNameResult="{\"status\":200,\"data\":{\"inputType\":\"id_card_no\",\"oldLoginName\":\"\\u674e\\u7ef4\\u79d1\",\"loginName\":\"gsres_iflytek_2c471eee6665563d20102373f016c4dd\",\"isOldUser\":1,\"reviewStatus\":\"110002\",\"userType\":\"002\",\"userId\":\"3162000039000311760\"}}";
		JSONObject userInfo = client.parseGetLoginName(getLoginNameResult);		
		//经过测试  登录是这个方法
		JSONObject ticketData = client.getLoginTicket();
		client.getSSOLogin(userInfo,ticketData);
		//登录成功 
		client.index();
		//甘肃教学师范主页
		client.gsjsxy(); 
		//获取未读的内容
		String [][]  unRead = client.unRead();
		//每天读取的文章数设置
		int readNum = 20;
		for(int i =0;i<readNum;i++){
			client.read(unRead[i][0],unRead[i][1]);
			System.out.println("程序睡眠2分钟开始。。。");
			Thread.sleep(1*60*1000);
			System.out.println("程序睡眠2分钟结束。。。");
		}*/
		//读出内容
		//client.readHistory();
	}
}

