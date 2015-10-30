package com.lysm.ttclub.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil
{
	static boolean toasts = true;

	public static void show(Context context, String text)
	{
		if (toasts)
		{
			Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
		}
	}
}
