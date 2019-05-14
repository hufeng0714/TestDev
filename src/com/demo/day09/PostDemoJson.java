package com.demo.day09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PostDemoJson {

	public String post(String url,String json) {
		HttpURLConnection conn = null;
		PrintWriter pw = null;
		BufferedReader rd = null;
		StringBuilder sb = new StringBuilder();
		String line = null;
		String response = null;
		
		try {
			conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setReadTimeout(20000);
			conn.setConnectTimeout(20000);
			conn.setUseCaches(false);
			conn.setRequestProperty("Content-type", "application/json");
			conn.connect();
			
			pw = new PrintWriter(conn.getOutputStream());
			pw.print(json);
			pw.flush();
			
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			while((line = rd.readLine())!=null) {
				sb.append(line);
			}
			
			response = sb.toString();
		}  catch(MalformedURLException e) {
			e.printStackTrace();
			
		}catch (IOException e) {
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
	
	public static void main(String[] args) {
		PostDemoJson pdj = new PostDemoJson();
		String url = "http://api.haomaiche.com/user/member/validate-phone";
		String json = "{\"data\":{\"userPhone\":\"13917361800\"},\"time\":1556527612468,\"source\":102}";
		pdj.post(url, json);
	}
}
