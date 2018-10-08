package com.dodo.marcket.wedget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MyRatingView extends LinearLayout{


    private List<ImageView> list = new ArrayList<>();

    public MyRatingView(Context context) {
        super(context);
    }

    public MyRatingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRatingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initView(){



    }
}
