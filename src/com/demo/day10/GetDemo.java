package com.demo.day10;


import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class GetDemo {
	
	public String get(String url) {
		String strResponse = "111";
		CloseableHttpClient httpClient = null;
		HttpGet httpGet = null;
		
		try {
			//ʵ����HttpClient����
			httpClient = HttpClients.createDefault();
			//����RequestClient����
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();
			//����HttpGet����
			httpGet = new HttpGet(url);
			//����RequestConfig
			httpGet.setConfig(requestConfig);
			//ִ�����󲢻�ȡresponse����
			CloseableHttpResponse response = httpClient.execute(httpGet);
			//��ȡ��Ӧ���Ķ���
			HttpEntity httpEntity = response.getEntity();
			//���Դ���
			strResponse = EntityUtils.toString(httpEntity);
			System.out.println(strResponse);
			
			//�����������Ӧ����
			//return EntityUtils.toString(httpEntity,"UTF-8");
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			
		} catch(IOException e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				if(httpGet != null) {
					httpGet.releaseConnection();
				}
				
				if(httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return null;
	}
	
	public static void main(String [] args) {
		GetDemo gd = new GetDemo();
		String url = "http://api-istage.haomaiche.com/ware/car/310000/c74216cf2f4441c1b870b060c6ae97c1/car-type?time=1556522969505&source=102";
		gd.get(url);
	}

}
