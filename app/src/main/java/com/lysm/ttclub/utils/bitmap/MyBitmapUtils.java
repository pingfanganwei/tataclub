package com.lysm.ttclub.utils.bitmap;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * 三级缓存工具类
 * 
 * @author Kevin
 * @date 2015-9-4
 */
public class MyBitmapUtils {

	private NetCacheUtils mNetUtils;
	private LocalCacheUtils mLocalUtils;
	private MemoryCacheUtils mMemoryUtils;

	public MyBitmapUtils() {
		mMemoryUtils = new MemoryCacheUtils();
		mLocalUtils = new LocalCacheUtils();
		mNetUtils = new NetCacheUtils(mLocalUtils, mMemoryUtils);
	}

	public void display(ImageView ivPic, String url) {
		// 优先读内存缓存
		Bitmap bitmap = mMemoryUtils.getBitmapFromMemory(url);
		if (bitmap != null) {
			ivPic.setImageBitmap(bitmap);
			System.out.println("从内存读取图片...");
			return;
		}

		// 其次读本地缓存
		bitmap = mLocalUtils.getBitmapFromLocal(url);
		if (bitmap != null) {
			ivPic.setImageBitmap(bitmap);
			System.out.println("从本地读取图片...");
			
			//写内存缓存
			mMemoryUtils.setBitmap2Memory(url, bitmap);
			return;
		}

		// 最后读网络缓存
		mNetUtils.getBitmapFromNet(ivPic, url);
	}

}
