package com.demo.day09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class PostDemo {
	
	public String post(String url,Map<String,String> form) {
		HttpURLConnection conn = null;
		PrintWriter pw = null;
		BufferedReader rd = null;
		StringBuilder out = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		String line = null;
		String response = null;
		
		//将map里的值转换成name=zhangsan&age=18这种数据格式
		for(String key : form.keySet()) {
			if(out.length()!=0) {
				out.append("&");
			}
			out.append(key).append("=").append(form.get(key));
		}
		
		try {
			conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setReadTimeout(20000);
			conn.setConnectTimeout(20000);
			conn.setUseCaches(false);
			conn.connect();
			
			pw = new PrintWriter(conn.getOutputStream());
			pw.print(out.toString());
			pw.flush();
			
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			
			while((line = rd.readLine())!=null) {
				sb.append(line);
			}
			
			response = sb.toString();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			
		} catch(IOException e){
			e.printStackTrace();
			
		}finally {
			try {
				if(pw != null) {
					pw.close();
				}
				
				if(rd != null) {
					rd.close();
				}
				
				if(conn != null) {
					conn.disconnect();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(response);
		return response;
	}
	
	public static void main(String [] args) {
		PostDemo pd = new PostDemo();
		String url = "http://api.haomaiche.com/user/member/validate-phone";
		Map form = new HashMap<String,String>();
		form.put("data", "{userPhone: \"13917361800\"}");
		form.put("source", "102");
		form.put("time","1556527612468");
		//这种传参的方式要处理map里的东西，比较麻烦，最好用json格式
		/*
		data: {userPhone: "13917361800"}
		source: 102
		time: 1556527612468
		*/
		
		/*
		{"data":{"userPhone":"13917361800"},"time":1556527612468,"source":102}
		*/
		
		pd.post(url, form);
		

	}

}
