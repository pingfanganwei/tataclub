package com.lysm.ttclub.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.lysm.ttclub.R;
import com.lysm.ttclub.base.BaseFargment;
import com.lysm.ttclub.base.BaseFragmentActivity;
import com.lysm.ttclub.fragment.FLSFragment;
import com.lysm.ttclub.fragment.MeFragment;
import com.lysm.ttclub.fragment.TtclubFragment;
import com.lysm.ttclub.fragment.YCSFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 主Acitvity .
 */
public class MainActivity extends BaseFragmentActivity {

    List<BaseFargment> mainFragList = new ArrayList<BaseFargment>();
    private FrameLayout flMian;
    private RadioGroup rgGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragList.add(new TtclubFragment());
        mainFragList.add(new YCSFragment());
        mainFragList.add(new FLSFragment());
        mainFragList.add(new MeFragment());

        initeView();
        getData();
    }

    private void initeView() {
        flMian = (FrameLayout) findViewById(R.id.fl_fragment_main);
        rgGroup = (RadioGroup) findViewById(R.id.rg_bottom_group);
    }

    private void getData()
    {
        rgGroup.setOnCheckedChangeListener(MyChangeListener);
        rgGroup.check(R.id.rb_ttclub);


    }

    /**
     * 底部菜单的设计
     *
     **/
    RadioGroup.OnCheckedChangeListener MyChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId)
            {
                case R.id.rb_ttclub:
                    replace(R.id.fl_fragment_main, mainFragList.get(0),"ttclub");
                    break;
                case R.id.rb_yuanchuangshe:
                    replace(R.id.fl_fragment_main, mainFragList.get(1),"rb_yuanchuangshe");
                    break;
                case R.id.rb_fulishe:
                    replace(R.id.fl_fragment_main, mainFragList.get(2),"rb_fulishe");
                    break;
                case R.id.rb_me:
                    replace(R.id.fl_fragment_main, mainFragList.get(3),"me");
                    break;
                default:
                    break;
            }
        }
    };

}
