package com.lysm.ttclub.widget;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lysm.ttclub.R;
import com.lysm.ttclub.utils.ImageCache;

/**
 * 自动定义广告条。
 */
public class AutoScrollViewPager extends ViewPager {
	// .xml
	public AutoScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		imageCache = new ImageCache(getContext());
	}

	// 暴露添加图片地址的方法
	private ImageCache imageCache = null;
	private List<String> imgurls = new ArrayList<String>();

	public void setImgurls(List<String> imgurls) {
		this.imgurls = imgurls;
	}

	// 暴露添加标题的方法
	private List<String> titles = null;
	private TextView titleView;

	public void setTitles(List<String> titles, TextView titleView) {
		this.titles = titles;
		this.titleView = titleView;
		this.titleView.setText(titles.get(0));
	}

	private int pageCount = 2;
	// 支持无限滑动
	private boolean isLooping = false;

	public void setLooping(boolean flag) {
		isLooping = flag;
	}

	private Handler handler = new Handler() {
		// 回调函数: 1.重写 2.系统或其它对象调用
		// on
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				Log.i("wzx", "切换到下一页");
				// 切换到下一页
				int curr = getCurrentItem();
				++curr;
				setCurrentItem(curr);// 显示指定页面
				// 重写给自己发一个消息
				// Message msg=new Message();
				Message msg2 = handler.obtainMessage();// obtain=get 是一种优化写法
				// 内部查找可重用的Message 如果有就重用 没有呢 才创建新的。
				msg2.what = 1;
				handler.sendMessageDelayed(msg2, 3000);// ---->handleMessage
				// 循环发送息
			}
		};
	};

	// 支持自动播放
	// 暴露给外面调用。
	public void startScroll() {
		// 3000
		// Message msg=new Message();
		Message msg = handler.obtainMessage();// obtain=get 是一种优化写法
		// 内部查找可重用的Message 如果有就重用 没有呢 才创建新的。
		msg.what = 1;
		handler.sendMessageDelayed(msg, 3000);// ---->handleMessage

	}

	/**
	 * 停止播放
	 */
	public void stopScroll() {
		// 清除所有消息 handleMessage就不能执行
		// 使用回调函数没有条件满足
		handler.removeCallbacksAndMessages(null);//

	}

	// 选择器:selector 管理素材的对象 根据 不同的状态显示不同的图片 press
	// select=true false
	// view.setSlected()
	private List<ImageView> dots = new ArrayList<ImageView>();

	public void init(int pageNumber, LinearLayout layoutDot) {
		// 3
		pageCount = pageNumber;
		// .xml
		// .java
		for (int i = 0; i < pageNumber; i++) {
			ImageView img = new ImageView(getContext());
			img.setBackgroundResource(R.drawable.dot_selector);
			img.setSelected(false);// 红
			// .xml layout_width layout_height
			// .java LinearLayout.LayoutParams 布局参数
			LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(//
					LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			// .xml marginRight
			// .java rightMargin
			p.rightMargin = 6;
			layoutDot.addView(img, p);
			dots.add(img);
		}
		dots.get(0).setSelected(true);
		// 设置内容
		PagerAdapter adpater = new ImageViewAdapter();
		this.setAdapter(adpater);// PageAdpater FragmentPageAdapter

		// ④　添加监听OnPageChangedListener
		// ⑤　自动滚动保持点的同步
		// ⑥　手动滚动时 停止滚动
		OnPageChangeListener listener = new MyOnPageChangeListener();
		this.setOnPageChangeListener(listener);// OnPageChangeListener监听滑动到第几页
	}

	private class MyOnPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		// 滑动第几页面
		@Override
		public void onPageSelected(int arg0) {

			// 3
			// 0 1 2 3 4 5
			// 取余操作
			// 0 1 2 ->0 1 2
			// 旧点不亮
			dots.get(currPageIndex % pageCount).setSelected(false);
			// 新点高亮
			currPageIndex = arg0;
			dots.get(currPageIndex % pageCount).setSelected(true);
			if (titleView != null && titles != null) {
				titleView.setText(titles.get(currPageIndex % pageCount));
			}

		}

	}

	private int currPageIndex = 0;

	private class ImageViewAdapter extends PagerAdapter {

		// 页数
		@Override
		public int getCount() {
			if (isLooping) {
				return Integer.MAX_VALUE;
			} else {
				return pageCount;
			}
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		// 当前显示视图
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// 创建显示的页面 是一张图片
			ImageView imag = new ImageView(getContext());
			// .xml scaleType=""
			// .java setScaleType
			imag.setScaleType(ScaleType.CENTER_CROP);
            //设置默认图片
			imag.setBackgroundResource(R.drawable.read_btn);
			// .xml layout_width layout_height
			// .java ViewPager.LayoutParams
			LayoutParams p = new LayoutParams();
			p.width = LayoutParams.MATCH_PARENT;
			p.height = LayoutParams.MATCH_PARENT;
			container.addView(imag, p);
			if (imgurls != null&&imgurls.size()>0) {
				imageCache.disPlayImage(imag, imgurls.get(position % pageCount));
			}
			// Button OnClickListener OnLongClickListener
			// OnTouchListener什么是touch事件?
			// touch由 ACTION_DOWN ACTION_MOVE.... ACTION_UP ACTION_CANCLE
			// ACTION_DOWN 调用停止 ACTION_UP ACTION_CANCLE 结束
			// 视频：公交车上有色狼 ...
			OnTouchListener listener = new MyOnTouchListener();
			imag.setOnTouchListener(listener);
			return imag;
		}

		private class MyOnTouchListener implements OnTouchListener {

			private int downX = 0;
			private long downTime = 0;

			// MotionEvent 归属地位置改变 小火箭
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {// 获取动作
				case MotionEvent.ACTION_DOWN:// 按下
					stopScroll();
					downX = (int) event.getX();// 获取按下位
					downTime = System.currentTimeMillis();// 保存按下时间
					Log.i("wzx", "ACTION_DOWN");
					break;
				case MotionEvent.ACTION_MOVE:// 移动
					Log.i("wzx", "ACTION_MOVE");
					break;
				case MotionEvent.ACTION_UP:// 提起
					Log.i("wzx", "ACTION_UP");
					int upX = (int) event.getX();
					if (downX == upX && System.currentTimeMillis() - downTime < 300) {

						// 使用Command设计模式 可以增加一个接口作监听器
						// 1.创建interface
						// 2.抽象方法 (抽取代码 去掉方法体)
						// 3.添加监听器
						// 4.监听器是要被调用。
						// 让开发者把代码写监听器里（控件外边）
						// onItemClick();
						if (listener != null) {
							listener.onItemClick();// 方法内部的代码
						}
					}
					startScroll();
					break;
				case MotionEvent.ACTION_CANCEL:// 取消
					Log.i("wzx", "ACTION_CANCEL");
					startScroll();
					break;
				}
				return true;// 返回值 处理这个事件就返回true
			}

		}

		// 移除显示

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((ImageView) object);
		}

	}

	public static interface OnViewItemClickListener {
		// 使用Command设计模式 可以增加一个接口作监听器
		// 1.创建interface
		// 2.抽象方法 (抽取代码 去掉方法体)
		public void onItemClick();
		// 3.添加监听器
		// 4.监听器是要被调用。
		// 让开发者把代码写监听器里（控件外边）
	}

	private OnViewItemClickListener listener;

	public void setOnViewItemClickListener(OnViewItemClickListener listener) {
		this.listener = listener;
	}

	// {
	// Toast.makeText(getContext(), "我点了 打开下一页100", 0).show();
	// }

}
