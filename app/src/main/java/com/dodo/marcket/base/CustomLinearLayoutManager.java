package com.dodo.marcket.base;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * 使用RecycleView 时，如果数据量很少只有几个，需求不需要它上下左右滑动，在xml配置中加上 android:scrollbars=”none”，这只是去掉了滑动bar。
 * CustomLinearLayoutManager linearLayoutManager = new CustomLinearLayoutManager(mContext);
 linearLayoutManager.setScrollEnabled(false);
 mDevicesRV.setLayoutManager(linearLayoutManager);
 */
public class CustomLinearLayoutManager extends LinearLayoutManager {
    private boolean isScrollEnabled = true;

    public CustomLinearLayoutManager(Context context) {
        super(context);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollVertically();
    }
}