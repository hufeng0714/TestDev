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
			//������֮��
			conn = (HttpURLConnection) new URL(url).openConnection();
			//��������ʽGET��POST��ȫ����д
			conn.setRequestMethod("GET");
			//������Ӧ����
			conn.setDoInput(true);
			//��������Ӧ���ĳ�ʱʱ��
			conn.setReadTimeout(20000);
			//���ӷ������ĳ�ʱʱ��
			conn.setConnectTimeout(20000);
			//������ʹ�û���
			conn.setUseCaches(false);
			//��������
			conn.connect();
			
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			while((line = rd.readLine())!=null) {
				sb.append(line);//��ȡ��Ӧ����
			}
			response = sb.toString();
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			
		} catch(IOException e) {
			e.printStackTrace();
			
		}finally {
			//�ر�����http����
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
