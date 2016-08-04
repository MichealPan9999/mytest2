package com.pan.mytest1.test;

import com.pan.mytest1.MainActivity;
import com.pan.mytest1.utils.HttpUtil;
import com.pan.mytest1.utils.NetTool;
import com.pan.mytest1.utils.StringTools;

import android.content.Context;
import android.test.AndroidTestCase;

public class MyTest extends AndroidTestCase{

	private Context mContext;
	public static String WASU_MOVIE_CAROUSEL = "http://bs3-api.sdk.wasu.tv/XmlData/Videos";
	private HttpUtil http;
	public MyTest() {
		mContext = MainActivity.mContext;
		http = HttpUtil.getInstance(mContext);
	}
	public void test()
	{
		System.out.println("hello test");
		System.out.println(http.getJsonContent(WASU_MOVIE_CAROUSEL));
	}
	public void paraseJason()
	{
		StringTools.getWasuMovieInfo(http.getJsonContent(WASU_MOVIE_CAROUSEL));
	}
	public void getNetInfo()
	{
		NetTool mNetTool = new NetTool(getContext());
		String netStatus = mNetTool.getNetworkType();
		System.out.println("网络类型:"+netStatus);
	}
}
