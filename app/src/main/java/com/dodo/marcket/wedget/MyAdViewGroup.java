package com.dodo.marcket.wedget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dodo.marcket.bean.AdvBean;
import com.dodo.marcket.utils.ImageLoaders;
import com.dodo.marcket.utils.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

public class MyAdViewGroup extends ViewGroup{
    
    private String TAG = "dd";
    private List<View> viewList = new ArrayList<>(); 
    
    public MyAdViewGroup(Context context) {
        this(context,null);
    }

    public MyAdViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyAdViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.i(TAG, "onLayout: ");
    }

    @Override
    public void addView(View child) {
        super.addView(child);
        Log.i(TAG, "addView: ");
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        Log.i(TAG, "onMeasure: ");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(TAG, "onDraw: ");
    }

    public void myAddView(List<AdvBean> advBeanList){

        for (int i = 0; i < advBeanList.size(); i++) {
            ImageView mIcon = new ImageView(this.getContext());

            if (advBeanList.get(i).getKind().equals("")){
                mIcon.getLayoutParams().width= ScreenUtil.getScreenWidth(this.getContext());
            }else {
                mIcon.getLayoutParams().width= ScreenUtil.getScreenWidth(this.getContext())/2;
            }

            ImageLoaders.displayImage(mIcon,advBeanList.get(i).getUrl());
            viewList.add(mIcon);
        }

    }
}
