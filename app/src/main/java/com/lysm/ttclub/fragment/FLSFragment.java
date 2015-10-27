package com.lysm.ttclub.fragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.lysm.ttclub.R;
import com.lysm.ttclub.adapter.CommonAdapter;
import com.lysm.ttclub.adapter.Holder.ViewHolder;
import com.lysm.ttclub.base.BaseFargment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FLSFragment extends BaseFargment
{
    Context context;
    private ListView listview;
    private List<String> mDatas = new ArrayList<String>(Arrays.asList("谢谢你！","帅哥"));

    @Override
    public int getLayoutId()
    {
        return R.layout.fragment_fulishe;
    }

    @Override
    public void ininView()
    {
        listview = (ListView) rootView.findViewById(R.id.lv_listview);

        initData();
    }

    @Override
    public void initData()
    {
        context =  getActivity();
        CommonAdapter<String> adapter = new CommonAdapter<String>(context,mDatas,R.layout.item_lsit)
        {
            @Override
            public void convert(ViewHolder viewHolder, String item)
            {
                //这里的Item做许多优化
               viewHolder.setText(R.id.tv_title,item);
                viewHolder.setImageByUrl(R.id.image,"https://github.com/nostra13/Android-Universal-Image-Loader/raw/master/wiki/UIL_Flow.png");
            }
        };
        listview.setAdapter(adapter);
    }

}
