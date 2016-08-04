package com.pan.mytest1.utils;

import android.util.Log;

/**
 * 带开关的log打印
 * @author panzq
 *
 */
public class Logger {

	public static int LOG_LEVEL  = 6;
	public static int VERBOSE  = 5;
	public static int DEBUG  = 4;
	public static int INFO  = 3;
	public static int WARN  = 2;
	public static int ERROR  = 1;
	public static boolean LOGDEBUG = true; 
	public static void v(String tag,String msg)
	{
		if (LOGDEBUG) {
			if (LOG_LEVEL > VERBOSE) {
				Log.v(tag, msg);
			}
		}
	}
	public static void d(String tag,String msg)
	{
		if (LOGDEBUG) {
			if (LOG_LEVEL > DEBUG) {
				Log.d(tag, msg);
			}
		}
	}
	public static void i(String tag,String msg)
	{
		if (LOGDEBUG) {
			if (LOG_LEVEL > INFO) {
				Log.i(tag, msg);
			}
		}
	}
	public static void w(String tag,String msg)
	{
		if (LOGDEBUG) {
			if (LOG_LEVEL > WARN) {
				Log.w(tag, msg);
			}
		}
	}
	public static void e(String tag,String msg)
	{
		if (LOGDEBUG) {
			if (LOG_LEVEL > ERROR) {
				Log.e(tag, msg);
			}
		}
	}
	private static final String TAG = "System.out";
	public static void V(String msg)
	{
		v(TAG, msg);
	}
	public static void I(String msg)
	{
		i(TAG, msg);
	}
	public static void D(String msg)
	{
		d(TAG, msg);
	}
	public static void W(String msg)
	{
		w(TAG, msg);
	}
	public static void E(String msg)
	{
		e(TAG, msg);
	}
}
