
# tataclub
高纺她他社框架

### 添加
自定义控件`MyViewPage`源码
自定广告条`AutoScrollViewPager`，有圆点标题等一系列的功能。\

1. 这里使用比较麻烦，添加自己修改图片加载框架
2. 修改默认图片
3. 设置小圆点的选择器和图片。
4. 为了灵活性，需要自己定义标题位置，和小圆点的位置。

```
    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="160dp"
        tools:context=".MainActivity" >
    
        <com.itheima.autoscroll.view.AutoScrollViewPager
            android:id="@+id/sroll_viewpager"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:background="#47659E" />
    
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_alignParentBottom="true"
            android:background="#431E15"
            android:orientation="horizontal" >
            <!-- 标题 -->
    
            <TextView
                android:id="@+id/titleview"
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:layout_gravity="center_vertical"
                android:layout_weight="1"
                android:singleLine="true"
                android:text="习近平去了美国看到凤姐没?"
                android:textColor="#FFFFFF"
                android:textSize="18sp" />
    
            <LinearLayout
                android:id="@+id/layout_dots"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center_vertical"
                android:orientation="horizontal" >
            </LinearLayout>
            <!-- 指示点 -->
        </LinearLayout>
    
    </RelativeLayout>    
```

### 通用适配器制造`Adapter`
使用大量的OO的设计原则，打造了一个通用的牛B的适配器。自己都有点膜拜自己。

``` java
      CommonAdapter<String> adapter = new CommonAdapter<String>(context,mDatas,R.layout.item_lsit)
        {
            @Override
            public void convert(ViewHolder viewHolder, String item)
            {
                viewHolder.setText(item);
            }
        };
        listview.setAdapter(adapter);
```

### 添加网络加载框架。
binMapFun。 google提供的。发现还有许多参数没有设置