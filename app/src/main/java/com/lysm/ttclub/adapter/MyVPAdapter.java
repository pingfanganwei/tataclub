package com.lysm.ttclub.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lysm.ttclub.base.BaseFargment;

import java.util.List;

/**
 * Created by H_lang on 2015/10/26.
 */
public class MyVPAdapter extends FragmentPagerAdapter {

    List<BaseFargment> fargList;
    public MyVPAdapter(FragmentManager fm,List<BaseFargment> fragList) {
        super(fm);
        this.fargList = fragList;
    }

    @Override
    public Fragment getItem(int position) {

        return fargList.get(position);
    }

    @Override
    public int getCount() {
        return fargList.size();
    }

}
