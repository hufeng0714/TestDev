package com.demo.day09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetDemo {
	
	public String get(String url) {
		HttpURLConnection conn = null;
		BufferedReader rd = null;
		StringBuilder sb = new StringBuilder();
		String line = null;
		String response = null;
		
		try {
			//打开请求之门
			conn = (HttpURLConnection) new URL(url).openConnection();
			//设置请求方式GET或POST，全部大写
			conn.setRequestMethod("GET");
			//允许响应正文
			conn.setDoInput(true);
			//服务器响应正文超时时长
			conn.setReadTimeout(20000);
			//连接服务器的超时时长
			conn.setConnectTimeout(20000);
			//不允许使用缓存
			conn.setUseCaches(false);
			//建立连接
			conn.connect();
			
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			while((line = rd.readLine())!=null) {
				sb.append(line);//获取响应正文
			}
			response = sb.toString();
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			
		} catch(IOException e) {
			e.printStackTrace();
			
		}finally {
			//关闭流与http连接
			try {
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
		System.out.println(sb.toString());
		return response;
		
	}
	
	public static void main(String [] args) {
		GetDemo gd = new GetDemo();
		String url = "http://api-istage.haomaiche.com/ware/car/310000/c74216cf2f4441c1b870b060c6ae97c1/car-type?time=1556522969505&source=102";
		gd.get(url);
	}

}
