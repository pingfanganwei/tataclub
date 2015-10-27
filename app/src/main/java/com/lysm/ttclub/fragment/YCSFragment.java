package com.lysm.ttclub.fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.AbsoluteLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lysm.ttclub.R;
import com.lysm.ttclub.adapter.MyVPAdapter;
import com.lysm.ttclub.base.BaseFargment;

import java.util.ArrayList;
import java.util.List;


public class YCSFragment extends BaseFargment implements View.OnClickListener{

    private LinearLayout llGroup;
    private LinearLayout llLine;
    private TextView tvTest;
    private TextView tvGirl;
    private TextView tvAnswer;

    private List<BaseFargment> ycsFragList;
    private ViewPager viewpaer;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_yuanchuangshe;
    }

    @Override
    public void ininView() {
        llGroup = (LinearLayout) rootView.findViewById(R.id.ll_group);
        llLine = (LinearLayout) rootView.findViewById(R.id.ll_line);
        viewpaer = (ViewPager) rootView.findViewById(R.id.vp_pager);


        tvTest = (TextView) rootView.findViewById(R.id.tv_test);
        tvGirl = (TextView) rootView.findViewById(R.id.tv_girl);
        tvAnswer = (TextView) rootView.findViewById(R.id.tv_answer);

        initData();
    }

    @Override
    public void initData() {
        ycsFragList = new ArrayList<BaseFargment>();
        ycsFragList.add(new TestFragment());
        ycsFragList.add(new GrilFragment());
        ycsFragList.add(new AnswerFragment());

        MyVPAdapter myVPAdapter = new MyVPAdapter(activity.getSupportFragmentManager(), ycsFragList);
        viewpaer.setAdapter(myVPAdapter);

        initlistener();
    }

    private void initlistener() {

        tvTest.setOnClickListener(this);
        tvGirl.setOnClickListener(this);
        tvAnswer.setOnClickListener(this);

        viewpaer.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeTitleState(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 设置标题数据
     * @param postion
     */
    private void changeTitleState(int postion) {
        // 改变标题颜色状态
        switch (postion)
        {
            case 0:
                tvTest.setSelected(true);
                tvGirl.setSelected(false);
                tvAnswer.setSelected(false);
                viewpaer.setCurrentItem(0);
                break;
            case 1:
                tvTest.setSelected(false);
                tvGirl.setSelected(true);
                tvAnswer.setSelected(false);
                viewpaer.setCurrentItem(1);
                break;
            case 2:
                tvTest.setSelected(false);
                tvGirl.setSelected(false);
                tvAnswer.setSelected(true);
                viewpaer.setCurrentItem(2);
                break;
            default:break;
        }
        // 缩放标题
    }

    private void setLise() {
        //设置位置
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tv_test:
                viewpaer.setCurrentItem(0);
                changeTitleState(0);
            case R.id.tv_girl:
                viewpaer.setCurrentItem(1);
                changeTitleState(1);
            case R.id.tv_answer:
                viewpaer.setCurrentItem(2);
                changeTitleState(2);

        }
    }
}
