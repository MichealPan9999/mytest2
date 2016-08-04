package com.pan.mytest1.utils;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

public class AppManager {

	private PackageManager pm;
	private Context mContext;
	public AppManager(Context context) 
	{
		super();
		this.mContext = context;
		pm = mContext.getPackageManager();
	}
	/**
	 * 根据指定路径安装apk
	 * @param uri apk所在的路径
	 */
	public void install(String uri)
	{
		File updateFile = new File(uri);
		try {
			String[] strs = {"chmod","604",updateFile.getPath()};
			Runtime.getRuntime().exec(strs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setDataAndType(Uri.fromFile(updateFile), "application/vnd.android.package-archive");
		mContext.startActivity(intent);
	}
}
