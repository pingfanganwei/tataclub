package com.lysm.ttclub.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lysm.ttclub.R;
import com.lysm.ttclub.adapter.MyVPAdapter;
import com.lysm.ttclub.base.BaseFargment;

import java.util.ArrayList;
import java.util.List;

/**
 * 她他社设计
 */
public class TtclubFragment extends BaseFargment implements View.OnClickListener{

    private ViewPager viewPager;
    private TextView tvTansuo;
    private TextView tvGruanzhu;
    private ImageButton ibtnLogin;

    List<BaseFargment> fragList;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_ttclub;
    }

    @Override
    public void ininView() {
        viewPager = (ViewPager) rootView.findViewById(R.id.vp_pager);
        tvTansuo = (TextView) rootView.findViewById(R.id.tv_tansuo);
        tvGruanzhu = (TextView) rootView.findViewById(R.id.tv_guanzhu);
        ibtnLogin = (ImageButton) rootView.findViewById(R.id.ibtn_login);
        changeTitleState(true);
        initData();
        initListener();
    }

    private void initListener() {
        //
        viewPager.setOnPageChangeListener(pagerListener);
        tvGruanzhu.setOnClickListener(this);
        tvTansuo.setOnClickListener(this);
    }

    private void changeTitleState(boolean isSelectVideo) {
        // 改变标题颜色状态
        tvTansuo.setSelected(isSelectVideo);
        tvGruanzhu.setSelected(!isSelectVideo);
        // 缩放标题

    }


    ViewPager.OnPageChangeListener pagerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            changeTitleState(position == 0);
        }
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void initData() {
        //初始数据

        fragList = new ArrayList<>();
        fragList.add(new TansuoFragment());
        fragList.add(new GuanzhuFragment());

    MyVPAdapter adapter = new MyVPAdapter(activity.getSupportFragmentManager(),fragList);
    viewPager.setAdapter(adapter);
}

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tv_guanzhu:
                viewPager.setCurrentItem(0);
            case R.id.tv_tansuo:
                viewPager.setCurrentItem(1);
        }
    }


}
