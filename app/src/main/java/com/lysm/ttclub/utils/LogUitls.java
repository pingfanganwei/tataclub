package com.lysm.ttclub.utils;

import android.util.Log;

/**
 * log输出工具
 * 
 * @author H_lang
 */
public class LogUitls
{
	public static boolean isShow = true;  //调试阶段。

	public static void i(String tag, String msg)
	{
		if (isShow)
		{
			Log.i(tag, msg);
		}
	}
}
