package com.lysm.ttclub.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lysm.ttclub.R;
import com.lysm.ttclub.adapter.Holder.ViewHolder;

import java.util.List;

/**
 * 自己通用的适配器
 * Created by H_lang on 2015/10/27.
 */
public abstract class CommonAdapter<T> extends BaseAdapter
{

    protected final Context mContext;
    protected final List<T> mDatas;
    protected LayoutInflater mInflater;
    protected int itemLayoutId;

    /**
     *
     * @param context
     * @param mDatas 填充数据
     * @param itemLayoutId 列表布局
     */
    public CommonAdapter(Context context, List<T> mDatas, int itemLayoutId)
    {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mDatas = mDatas;
        this.itemLayoutId = itemLayoutId;
    }

    @Override
    public int getCount()
    {
        return mDatas.size();
    }

    @Override
    public T getItem(int position)
    {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder = getViewHolder(position,convertView,parent);
        convert(viewHolder, getItem(position));
        return viewHolder.geteConvertView();
    }

    /**
     * @param viewHolder 当前保存的ViweHolder
     * @param item 当前T值。
     */
    public abstract void convert(ViewHolder viewHolder, T item);

    /**
     * 获取一个ViewHolder
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    private ViewHolder getViewHolder(int position, View convertView, ViewGroup parent)
    {
      return ViewHolder.get(mContext, convertView, parent,itemLayoutId, position);
    }
}
