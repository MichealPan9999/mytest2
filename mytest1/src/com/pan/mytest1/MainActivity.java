package com.pan.mytest1;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pan.mytest1.utils.AppManager;
import com.pan.mytest1.utils.BlurUtils;
import com.pan.mytest1.utils.LruCacheUtils;
import com.pan.mytest1.utils.NetTool;

public class MainActivity extends Activity {

	public static Context mContext;
	private static final String SDCardPath = Environment
			.getExternalStorageDirectory().getAbsolutePath();
	private AppManager appManager;
	private LruCacheUtils mCacheUtils;
	private RelativeLayout rLayout;
	protected Context context;
	protected SharedPreferences sp;
	private boolean isblur = false;
	private TextView networktype ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		mContext = MainActivity.this;
		context = MainActivity.this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		appManager = new AppManager(mContext);
		sp = getSharedPreferences("shenma", MODE_PRIVATE);
		sp.edit().putString("open_blur", "开").commit();
		sp.edit().putString("wallpaperFileName", "4.jpg").commit();
		String apkPath = SDCardPath + File.separator + "HiMarket.apk";
		System.out.println(apkPath);
		// 安装程序
		File apkFile = new File(apkPath);
		if (apkFile.exists()) {
			appManager.install(apkPath);
		} else {
			System.out.println(apkPath + "不存在");
		}
		mCacheUtils = LruCacheUtils.getInstance();
		findViews();

	}

	/**
	 * 点击按钮来切换高斯模糊图片跟清晰图片
	 * 
	 * @param v
	 */
	public void changeImage(View v) {

		String fileName = sp.getString("wallpaperFileName", null);
		if (isblur) {
			// 更换背景
			System.out.println(fileName);
			if (fileName != null && !"".equals(fileName)) {
				changeBackImage(fileName);
			}
		} else {
			Bitmap bmp = mCacheUtils.getBitmapFromMemCache(SDCardPath + "/"
					+ fileName);
			if (bmp == null) {
				Bitmap tempBmp = BitmapFactory.decodeFile(SDCardPath + "/"
						+ fileName);
				mCacheUtils.addBitmapToMemoryCache(SDCardPath + "/" + fileName,
						tempBmp);
			}
			rLayout.setBackgroundDrawable(new BitmapDrawable(getResources(),
					bmp));
		}
		isblur = !isblur;
	}

	private void findViews() {
		rLayout = (RelativeLayout) findViewById(R.id.r1_bg);
		networktype = (TextView) findViewById(R.id.networktype);
		Bitmap bmp = mCacheUtils.getBitmapFromMemCache(String
				.valueOf(R.drawable.bg));
		if (bmp == null) {
			bmp = BitmapFactory.decodeResource(getResources(),
					R.drawable.main_bg);
			mCacheUtils.addBitmapToMemoryCache(
					String.valueOf(R.drawable.main_bg), bmp);
		}
		rLayout.setBackgroundDrawable(new BitmapDrawable(getResources(), bmp));
	}

	private void changeBackImage(String fileName) {
		if (fileName == null)
			return;
		File picFile = new File(SDCardPath+"/"+fileName);
		if (picFile.exists()) {
			Bitmap bmp = null;
			if ("开".equals(sp.getString("open_blur", "关"))) {
				// 开启高斯模糊背景效果
				bmp = mCacheUtils.getBitmapFromMemCache(SDCardPath + "/"
						+ fileName + "_blur");
				if (bmp == null) {
					try {
						Bitmap tempBmp = mCacheUtils
								.getBitmapFromMemCache(SDCardPath + "/"
										+ fileName);
						if (tempBmp == null) {
							tempBmp = BitmapFactory.decodeFile(context
									.getFilesDir().getAbsolutePath()
									+ "/"
									+ fileName);
							mCacheUtils.addBitmapToMemoryCache(context
									.getFilesDir().getAbsolutePath()
									+ "/"
									+ fileName, tempBmp);
						}
						bmp = BlurUtils.doBlur(tempBmp, 7, false);
						mCacheUtils.addBitmapToMemoryCache(context
								.getFilesDir().getAbsolutePath()
								+ "/"
								+ fileName + "_blur", bmp);
					} catch (OutOfMemoryError oom) {
						mCacheUtils.clearAllImageCache();
						Bitmap tempBmp = mCacheUtils
								.getBitmapFromMemCache(SDCardPath + "/"
										+ fileName);
						if (tempBmp == null) {
							tempBmp = BitmapFactory.decodeFile(context
									.getFilesDir().getAbsolutePath()
									+ "/"
									+ fileName);
							mCacheUtils.addBitmapToMemoryCache(context
									.getFilesDir().getAbsolutePath()
									+ "/"
									+ fileName, tempBmp);
						}
						bmp = BlurUtils.doBlur(tempBmp, 7, false);
						mCacheUtils.addBitmapToMemoryCache(context
								.getFilesDir().getAbsolutePath()
								+ "/"
								+ fileName + "_blur", bmp);
					}
				}
				rLayout.setBackgroundDrawable(new BitmapDrawable(
						getResources(), bmp));

			} else {
				bmp = mCacheUtils.getBitmapFromMemCache(SDCardPath + "/"
						+ fileName);
				if (bmp == null) {
					try {
						bmp = BitmapFactory.decodeFile(SDCardPath + "/"
								+ fileName);
						mCacheUtils.addBitmapToMemoryCache(context
								.getFilesDir().getAbsolutePath()
								+ "/"
								+ fileName, bmp);
					} catch (OutOfMemoryError oom) {
						mCacheUtils.clearAllImageCache();
						bmp = BitmapFactory.decodeFile(SDCardPath + "/"
								+ fileName);
						mCacheUtils.addBitmapToMemoryCache(context
								.getFilesDir().getAbsolutePath()
								+ "/"
								+ fileName, bmp);
					}
				}
				rLayout.setBackgroundDrawable(new BitmapDrawable(
						getResources(), bmp));
			}
		}
	}
	public void getNetworkType(View v)
	{
		NetTool mNetTool = new NetTool(mContext);
		String netStatus = mNetTool.getNetworkType();
		System.out.println("网络类型:"+netStatus);
		networktype.setText(netStatus);
	}
}
