package com.pan.mytest1.utils;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.content.Context;

public class HttpUtil {

	private static HttpUtil httpUtilInstance = null;
	public HttpUtil() {
	}
	public HttpUtil(Context context) {
	}
	public static HttpUtil getInstance(Context context)
	{
		if (httpUtilInstance == null) {
			httpUtilInstance = new HttpUtil(context);
		}
		return httpUtilInstance;
	}
	/**
	 * 通过访问网址返回字符串
	 * @param url_str 网址
	 * @return
	 */
	public String getJsonContent(String url_str)
	{
		HttpGet httpRequest = new HttpGet(url_str);
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params, 3000);
		HttpConnectionParams.setSoTimeout(params, 3000);
		HttpClient httpClient = new DefaultHttpClient(params);
		
		try {
			HttpResponse response = httpClient.execute(httpRequest);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String content = EntityUtils.toString(response.getEntity(),"UTF-8");
				return content;
			}
			else return null;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
