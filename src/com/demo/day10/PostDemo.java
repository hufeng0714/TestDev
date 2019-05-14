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
			 开始构建表单参数，表参参数的数据格式是：name=zhangsan&age=18，
			其中，name与age为key，分别对应的value为zhangshan和18
			正因为表单参数的数据格式为key-value的键值对形式，所以入参为一个
			Map<String,String> params
			 */
			List<NameValuePair> ps = new ArrayList<NameValuePair>();
			for(String pKey : params.keySet()) {
				ps.add(new BasicNameValuePair(pKey,params.get(pKey)));
			}
			
			//构建表单参数完毕，产生一个List<NameValuePair>的对象，其中BasicNameValuePair是产生一个key-value的键值对对象
			
			/*
			new UrlEncodedFormEntity(ps)中的UrlEncodedFormEntity对象是将List<NameValuePair>转换为表单参数的数据格式，
			并将Content-Type设置为application/x-www-form-urlencoded
			所以不需要我们自己设置Content-Type了
			*/
			
			httpPost.setEntity(new UrlEncodedFormEntity(ps));//设置请求正文
			CloseableHttpResponse response = httpClient.execute(httpPost);//执行请求，并获取response对象
			HttpEntity httpEntity = response.getEntity();//获取正文对象
			return EntityUtils.toString(httpEntity,"UTF-8");//按编码输出正文
			
			
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
		//这种传参的方式要处理map里的东西，比较麻烦，最好用json格式
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
