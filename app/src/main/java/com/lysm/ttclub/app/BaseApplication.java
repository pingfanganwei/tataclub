package com.lysm.ttclub.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by H_lang on 2015/10/27.
 */
public class BaseApplication extends Application
{
    private static Context context;
    private static Handler handler;
    private static int mianThreadId;

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = getApplicationContext();
        handler = new Handler();
        mianThreadId = android.os.Process.myTid();

    }

    public static Context getContext()
    {
        return context;
    }

    public static Handler getHandler()
    {
        return handler;
    }

    public static int getMianThreadId()
    {
        return mianThreadId;
    }


}
