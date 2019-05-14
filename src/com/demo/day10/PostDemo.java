package com.demo.day10;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class PostDemo {

	public String post(String url,Map<String,String> params) {
		
		CloseableHttpClient httpClient = null;
		HttpPost httpPost = null;
		
		try {
			httpClient = HttpClients.createDefault();
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();
			httpPost = new HttpPost(url);
			httpPost.setConfig(requestConfig);
			/*
			 ��ʼ��������������β��������ݸ�ʽ�ǣ�name=zhangsan&age=18��
			���У�name��ageΪkey���ֱ��Ӧ��valueΪzhangshan��18
			����Ϊ�����������ݸ�ʽΪkey-value�ļ�ֵ����ʽ���������Ϊһ��
			Map<String,String> params
			 */
			List<NameValuePair> ps = new ArrayList<NameValuePair>();
			for(String pKey : params.keySet()) {
				ps.add(new BasicNameValuePair(pKey,params.get(pKey)));
			}
			
			//������������ϣ�����һ��List<NameValuePair>�Ķ�������BasicNameValuePair�ǲ���һ��key-value�ļ�ֵ�Զ���
			
			/*
			new UrlEncodedFormEntity(ps)�е�UrlEncodedFormEntity�����ǽ�List<NameValuePair>ת��Ϊ�����������ݸ�ʽ��
			����Content-Type����Ϊapplication/x-www-form-urlencoded
			���Բ���Ҫ�����Լ�����Content-Type��
			*/
			
			httpPost.setEntity(new UrlEncodedFormEntity(ps));//������������
			CloseableHttpResponse response = httpClient.execute(httpPost);//ִ�����󣬲���ȡresponse����
			HttpEntity httpEntity = response.getEntity();//��ȡ���Ķ���
			return EntityUtils.toString(httpEntity,"UTF-8");//�������������
			
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch(IOException e) {
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
		PostDemo pd = new PostDemo();
		String url = "http://api.haomaiche.com/user/member/validate-phone";
		Map params = new HashMap<String,String>();
		params.put("data", "{userPhone: \"13917361800\"}");
		params.put("source", "102");
		params.put("time","1556527612468");
		//���ִ��εķ�ʽҪ����map��Ķ������Ƚ��鷳�������json��ʽ
		/*
		data: {userPhone: "13917361800"}
		source: 102
		time: 1556527612468
		*/
		
		/*
		{"data":{"userPhone":"13917361800"},"time":1556527612468,"source":102}
		*/
		
		pd.post(url, params);
	}
}
