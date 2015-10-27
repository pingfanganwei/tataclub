package com.lysm.ttclub.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class CacheUtils
{
	private static SharedPreferences mSP;

	public static String IS_USER_GUIDE = "is_user_GUIDE"; // 进入引导页面

	public static SharedPreferences getPreferences(Context context)
	{
		if (mSP == null)
		{
			mSP = context.getSharedPreferences("config_sp", Context.MODE_PRIVATE);
		}
		return mSP;
	}

	public static void putBoolean(Context context, String key, boolean value)
	{
		SharedPreferences sp = getPreferences(context);
		sp.edit().putBoolean(key, value).commit();
	}

	public static boolean getBoolean(Context context, String key, boolean value)
	{
		SharedPreferences sp = getPreferences(context);
		return sp.getBoolean(key, value);
	}

	public static void putString(Context context, String key, String value)
	{
		SharedPreferences sp = getPreferences(context);
		sp.edit().putString(key, value).commit();
	}

	public static String getString(Context context, String key, String defaultValue)
	{
		SharedPreferences sp = getPreferences(context);
		return sp.getString(key, defaultValue);
	}

	public static int getInt(Context context, String key, int defaultValue)
	{
		SharedPreferences sp = getPreferences(context);
		return sp.getInt(key, defaultValue);
	}

	public static void putInt(Context context, String key, int defaultValue)
	{
		SharedPreferences sp = getPreferences(context);
		sp.edit().putInt(key, defaultValue).commit();
	}

}
