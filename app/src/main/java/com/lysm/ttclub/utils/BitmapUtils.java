package com.lysm.ttclub.utils;

import android.content.Context;

import com.example.android.bitmapfun.util.*;
import com.example.android.bitmapfun.util.ImageCache;
import com.lysm.ttclub.R;
import com.lysm.ttclub.app.BaseApplication;


/**
 * Created by H_lang on 2015/10/27.
 */
public class BitmapUtils
{

    private static ImageFetcher mImageFetcher = null;
    /**
     * get Single ImageLoader
     * @return ImageLoader
     */
    public static ImageFetcher getImageFetcher()
    {
        if (mImageFetcher == null)
        {
            // 单列枷锁
            synchronized (ImageFetcher.class)
            {		// 创建抓取器实例
                Context context = BaseApplication.getContext();
                mImageFetcher = new ImageFetcher(BaseApplication.getContext(), 100, 100);
                // 创建参数配置对象:包含 重要参数
                // ImageCache cacheOption=new ImageCache(this, 图片保存文件夹名字);//http shop linux文件名不能包含 ://==>urlEnocde
                ImageCache iamgeCache = new ImageCache(context,"tataCache");
                mImageFetcher.setImageCache(iamgeCache);
                //设置默认图片
                mImageFetcher.setLoadingImage(R.drawable.about);
            }
        }
        return mImageFetcher;
    }
}
