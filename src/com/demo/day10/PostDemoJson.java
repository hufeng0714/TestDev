package com.demo.day10;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class PostDemoJson {

	public String post(String url,String body) {
		
		CloseableHttpClient httpClient = null;
		HttpPost httpPost = null;
		
		try {
			httpClient = HttpClients.createDefault();
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();
			
			httpPost = new HttpPost(url);
			httpPost.setConfig(requestConfig);
			
			httpPost.setEntity(new StringEntity(body));
			CloseableHttpResponse response = httpClient.execute(httpPost);
			HttpEntity httpEntity = response.getEntity();
			System.out.println(EntityUtils.toString(httpEntity));//输出到控制台
			return EntityUtils.toString(httpEntity,"UTF-8");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(httpPost != null) {
					httpPost.releaseConnection();
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
		PostDemoJson pdj = new PostDemoJson();
		String url = "http://api.haomaiche.com/user/member/validate-phone";
		String json = "{\"data\":{\"userPhone\":\"13917361800\"},\"time\":1556527612468,\"source\":102}";
		pdj.post(url, json);
	}
}
