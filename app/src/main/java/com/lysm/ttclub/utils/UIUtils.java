package com.lysm.ttclub.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;

import com.lysm.ttclub.app.BaseApplication;

/**
 * 封装相应的 Appacation.
 * 
 * @author H_lang
 * 
 */
public class UIUtils
{

	// **获取Appacaion 相关方法**/
	public static Context getContext()
	{
		return BaseApplication.getContext();
	}

	public static Handler getHandler()
	{
		return BaseApplication.getHandler();
	}

	/**
	 * 获取主线程的id
	 */
	public static int getMainThreadId()
	{
		return BaseApplication.getMianThreadId();
	}

	/*
	 * dip和px之间的相互转换 dp=px/设备密度
	 */

	// dip转换px
	public static int dip2px(int dip)
	{
		// 获取到设备密度
		final float density = getContext().getResources().getDisplayMetrics().density;
		int px = (int) (dip * (density + 0.5));

		return px;
	}

	public static float px2dip(int px)
	{
		final float density = getContext().getResources().getDisplayMetrics().density;

		return px / density;
	}

	// //////////////////////// 获取到资源信息///////////////////////////

	// 获取资源
	public static Drawable getDrawable(int id)
	{
		return getContext().getResources().getDrawable(id);
	}

	public static ColorStateList getColorStateList(int id)
	{
		return getContext().getResources().getColorStateList(id);
	}

	public static String[] getStringArray(int id)
	{
		return getContext().getResources().getStringArray(id);
	}

	/**
	 * 根据id获取尺寸
	 */
	public static int getDimen(int id)
	{
		return getContext().getResources().getDimensionPixelSize(id);
	}

	/**
	 * 加载布局获取一个View对象
	 * 
	 * @param id
	 * @return
	 */
	public static View Inflate(int id)
	{
		return View.inflate(getContext(), id, null);
	}

	// 判断是否在主线程
	public static boolean isRunOnUiThread()
	{
		return android.os.Process.myTid() == getMainThreadId();
	}

	public static void runOnUiThread(Runnable run)
	{
		if (isRunOnUiThread())
		{
			//判断是否主线程
			run.run();
		} else
		{
			getHandler().post(run);
		}
	}

	public static int getColor(int id)
	{
		return getContext().getResources().getColor(id);
	}

}
