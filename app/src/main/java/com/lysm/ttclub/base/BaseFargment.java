
package com.lysm.ttclub.base;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.lysm.ttclub.activity.MainActivity;


public abstract class BaseFargment extends Fragment
{
    public MainActivity activity;

	public void loadImage(String url, ImageView img)
	{

	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
        activity = (MainActivity) getActivity();
		super.onCreate(savedInstanceState);
	}

	/**
	 * 	获取到屏幕宽度
 	 */
	public int getSreenWidth()
	{
		WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;// dp--> px
	}


	public View rootView; // 根布局
	private Context mContext;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{

		if (rootView == null)
		{
			rootView = View.inflate(getActivity(), getLayoutId(), null);
			ininView();
		} else
		{
			ViewGroup parent = (ViewGroup) rootView.getParent();
			if (parent != null)
			{
				parent.removeView(rootView);
			}
		}
		return rootView;
	}

	public abstract int getLayoutId();

	public abstract void ininView();

	public abstract void initData();

}

