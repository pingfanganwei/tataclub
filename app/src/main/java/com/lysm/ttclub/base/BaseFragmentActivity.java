package com.lysm.ttclub.base;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


/**
 * Created by H_lang on 2015/10/8.
 */
public class BaseFragmentActivity extends FragmentActivity {
    protected void startPage(Class<?> activity) {
        Intent intent = new Intent(getBaseContext(), activity);
        startActivity(intent);

    }
    // 增删改
    /**
     * 添加片段
     *
     */
    protected void add(int layoutId, Fragment f, String tag) {
        // id int
        // tag String
        // 获取管理者
        FragmentManager fm = getSupportFragmentManager();
        // 打开事务
        FragmentTransaction ft = fm.beginTransaction();
        // 替换
        ft.add(layoutId, f, tag);
        // 关闭
        ft.commit();
    }

    public void replace(int layoutId, Fragment f, String tag) {
        // id int
        // tag String
        // 获取管理者
        FragmentManager fm = getSupportFragmentManager();

        // 打开事务
        FragmentTransaction ft = fm.beginTransaction();
        // 替换
        ft.replace(layoutId, f, tag);
        // 关闭
        ft.commit();
    }

    protected void remove(Fragment f) {
        // id int
        // tag String
        // 获取管理者
        FragmentManager fm = getSupportFragmentManager();
        // 打开事务
        FragmentTransaction ft = fm.beginTransaction();
        // 替换
        ft.remove(f);
        // 关闭
        ft.commit();
    }

    protected Fragment find(String tag) {
        // id int
        // tag String
        // 获取管理者
        FragmentManager fm = getSupportFragmentManager();

        Fragment f = fm.findFragmentByTag(tag);

        return f;
    }


}
