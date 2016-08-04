package com.pan.mytest1.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StringTools {

	public StringTools() {
	}
	/**
	 * 解析Json格式
	 * @param content
	 */
	public static void  getWasuMovieInfo(String content)
	{
		JSONObject jsonObject = null;
		
		try {
			jsonObject = new JSONObject(content);
			JSONArray movieInfoArray = jsonObject.getJSONArray("videolist");
			int[] id = new int[32];
			String[] linkUrl = new String[32];
			String[] name = new String[32];
			String[] picUrl = new String[32];
			String[] program_id = new String[32];
			String[] showType = new String[32];
			int[] totalsize = new int[32];
			String[] videoUrl = new String[32];
			String[] viewPoint = new String[32];
			int count = 0;
			int lenght = movieInfoArray.length();
			System.out.println("lenght = "+lenght);
			for (int i = 0; i < lenght; i++) {
				JSONObject movie = (JSONObject) movieInfoArray.opt(i);
				id[count] = movie.getInt("id");
				linkUrl[count]=movie.getString("linkUrl");
				name[count] = movie.getString("name");
				System.out.println("电影名字 = "+name[count]);
				picUrl[count] = movie.getString("picUrl");
				program_id[count] = movie.getString("program_id");
				showType[count] = movie.getString("showType");
				totalsize[count] = movie.getInt("totalsize");
				videoUrl[count] = movie.getString("videoUrl");				
				//Hisa 2015.12.17 去掉wasu过长的描述文字 start
				//viewPoint[count] = movie.getString("viewPoint");
				String point = movie.getString("viewPoint");
				int len = point.length() > 20 ? 20 : point.length() - 1;
				viewPoint[count] = point.length() > 20 ? point.substring(0, len) + "..." : point.substring(0, len);
				//Hisa 2015.12.17 去掉wasu过长的描述文字  end
				count++;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
