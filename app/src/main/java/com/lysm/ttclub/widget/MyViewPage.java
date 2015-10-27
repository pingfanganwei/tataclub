package com.lysm.ttclub.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * 自定义ViewPage控件
 * 
 * @author H_lang
 * 
 */
public class MyViewPage extends ViewGroup
{
	private GestureDetector mDetector; // 手势操作器
	private Scroller mScroller; // 滑动器
	private int mStarX;
	private int mStarY;
	private OnPageChangeListener mListener;

	public MyViewPage(Context context)
	{
		super(context);
		init();
	}

	public MyViewPage(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		init();
	}

	public MyViewPage(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init();
	}

	private void init()
	{
		mDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener()
		{
			// 滑动手势的回调
			@Override
			public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
			{
				// 参1:起点动作, 参2:终点动作;参3:x方向滑动距离;参4:y方向滑动距离
				// 滑动页面
				scrollBy((int) distanceX, 0);
				// 页面滑动一定距离;x:水平方向的滑动偏移量;y:竖直方向滑动的偏移量;(相对偏移)
				// scrollTo(x, y);//绝对位移;和当前位置无关,滑动到确定好的位置上

				return super.onScroll(e1, e2, distanceX, distanceY);
			}
		});

		// 设置一个滑动器
		mScroller = new Scroller(getContext());
	}

	/**
	 * 更新滑动的距离
	 */

	/**
	 * 设置布局的宽高
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		// widthMeasureSpec 宽高 由模式 和长度组成十进制。
		// MeasureSpec.AT_MOST;
		// MeasureSpec.EXACTLY;
		// MeasureSpec.UNSPECIFIED;

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		// 必须手动测量子控件的宽高
		for (int i = 0; i < getChildCount(); i++)
		{
			getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
		}

	}

	// 设置布局 的位置
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b)
	{
		// 遍历孩子的数量
		for (int i = 0; i < getChildCount(); i++)
		{
			// 控制子控件的绘制的位置 让子控件一字排开 这里必须设置控件的宽高
			getChildAt(i).layout(getWidth() * i, 0, getWidth() * (i + 1), getHeight());
		}

	}

	/**
	 * 事件分发的方法。
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev)
	{
		return super.dispatchTouchEvent(ev);
	}

	/**
	 * 是否需要拦截 触摸事件 false拦截 , 事件的拦截机制 返回对调该事件。
	 */

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev)
	{
		// 判断是左右位移和上下位移之间的事件处理。 s
		switch (ev.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			mStarX = (int) ev.getX();
			mStarY = (int) ev.getY();

			// 重置手势监听器
			mDetector.onTouchEvent(ev);
			break;
		case MotionEvent.ACTION_MOVE:
			int endX = (int) ev.getX();
			int endY = (int) ev.getY();
			// 判断偏移量
			int dx = endX - mStarX;
			int dy = endY - mStarY;
			if (Math.abs(dx) > Math.abs(dy))
				// 阻断事件向下传递。
				return true;
			break;
		default:
			break;
		}
		return false;

	}

	// 触摸事件处理
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		mDetector.onTouchEvent(event);
		// 设置滑动距离监听
		switch (event.getAction())
		{
		// 手势离开以后
		case MotionEvent.ACTION_UP:
			int scaleX = getScrollX();// 获取到滚动的距离
			int pos = scaleX / getWidth(); // 获取到位置
			int offset = scaleX % getWidth();// 获取到偏移量

			if (offset > getWidth() / 2)
			{
				pos++;
			}
			//
			if (pos > getChildCount() - 1)
			{
				pos = getChildCount() - 1;
			}
			System.out.println(pos + "posation");
			setCurrentItem(pos);
			break;
		default:
			break;
		}

		return true;
	}

	/**
	 * 根据位置设置页面
	 * 
	 * @param pos
	 */
	public void setCurrentItem(int pos)
	{
		int distance = pos * getWidth() - getScrollX();

		// computeScroll 这里会不断调用偏离的距离 设置滑动距离
		mScroller.startScroll(getScrollX(), 0, distance, 0, Math.abs(distance));
		// 重新绘制位置
		invalidate();

		// 当页面改动的时候。
		if (mListener != null)
		{
			mListener.onPageChange(pos);
		}

		// 这里回调先不写
	}

	// View会在滑动的时候 回调这个方法。
	@Override
	public void computeScroll()
	{
		if (mScroller.computeScrollOffset())
		{
			int currx = mScroller.getCurrX();
			scrollTo(currx, 0);// 滑动到目标位置
			invalidate();// 更新
			System.out.println("currx" + currx);
			
		}

	}

	// 创建回调监听事件

	public interface OnPageChangeListener
	{
		// 创建回调监听事件
		public void onPageChange(int posiot);
	}

	public void setOnPageChangeListener(OnPageChangeListener listener)
	{
		// 创建 图片改变监听
		mListener = listener;
	}

}
