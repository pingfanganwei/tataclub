package com.lysm.ttclub.adapter.Holder;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 打造
 * Created by H_lang on 2015/10/27.
 */
public class ViewHolder
{
    /**
     * 我这里采用稀疏数组排列方式，  比Map更有速度优势，
     */
    private final SparseArray<View> mViews;
    private View mConvertView;

    private ViewHolder(Context context, ViewGroup parent, int layoutId)
    {
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);

        mConvertView.setTag(this);
    }

    /**
     * 拿到一个ViewHolder对象
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId, int position)
    {
        if (convertView == null)
        {
            return new ViewHolder(context, parent, layoutId);
        }

        return (ViewHolder) convertView.getTag();
    }

    /**
     * 通过控件的Id获取到该控件，如果没有折加入mViws
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId)
    {
        View view = mViews.get(viewId);

        if (view== null)
        {
            view = mConvertView .findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T)view;
    }

    public View geteConvertView()
    {
        return mConvertView;
    }


}
