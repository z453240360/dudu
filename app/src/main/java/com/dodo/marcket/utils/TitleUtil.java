package com.dodo.marcket.utils;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.wedget.DrawableCenterTextView;


/**
 * xiejingwen
 */
public class TitleUtil {
    private Toolbar mToolbar;
    private DrawableCenterTextView mLeft;
    private DrawableCenterTextView home_left;
    private DrawableCenterTextView mClose;
    private TextView mTitle;
    private DrawableCenterTextView mRight;
    private AppCompatActivity mActivity;

    //activity构造
    public TitleUtil(AppCompatActivity activity, View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        if (mToolbar == null) {
            return;
        }
        home_left = (DrawableCenterTextView) view.findViewById(R.id.home_left);
        mLeft = (DrawableCenterTextView) view.findViewById(R.id.tv_left);
        mClose = (DrawableCenterTextView) view.findViewById(R.id.tv_close);
        mTitle = (TextView) view.findViewById(R.id.tv_title);

//        tv_titleLeft = (TextView) view.findViewById(R.id.tv_titleLeft);
        mRight = (DrawableCenterTextView) view.findViewById(R.id.tv_right);
        this.mActivity = activity;
        mActivity.setSupportActionBar(mToolbar);
        mActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    /**
     * 设置title与返回键
     *
     * @param isShowBack 是否显示返回按钮
     * @param listener   返回按钮的点击事件，默认为传入null  销毁当前activity
     * @param title      标题
     */
    public void setTitle(boolean isShowBack, View.OnClickListener listener, int toolbarColor, String title) {
        mTitle.setText(title);
//        tv_titleLeft.setText(title);
        mToolbar.setBackgroundResource(toolbarColor);
        if (isShowBack) {
            Drawable left = ContextCompat.getDrawable(mActivity, R.mipmap.back);
            left.setBounds(0, 0, left.getMinimumWidth(), left.getMinimumHeight());
            mLeft.setCompoundDrawables(left, null, null, null);
            if (listener != null) {
                mLeft.setOnClickListener(listener);
            } else {
                mLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mActivity.finish();
                    }
                });
            }
        } else {
            mLeft.setCompoundDrawables(null, null, null, null);
            mLeft.setClickable(false);
        }
    }

    /**
     * 设置首页点击侧边蓝按钮
     *
     * @param listener
     */
    public void setHomeMenu(View.OnClickListener listener) {
        mLeft.setVisibility(View.GONE);
        home_left.setVisibility(View.VISIBLE);
        Drawable left = ContextCompat.getDrawable(mActivity, R.mipmap.caidan);
        left.setBounds(0, 0, left.getMinimumWidth(), left.getMinimumHeight());
        home_left.setCompoundDrawables(left, null, null, null);
        if (listener != null) {
            home_left.setOnClickListener(listener);
        }
    }

    public void setHomeMenu(String title, boolean isShowBack) {
        home_left.setVisibility(View.VISIBLE);
        home_left.setText(title);
        if (isShowBack) {
            Drawable left = ContextCompat.getDrawable(mActivity, R.mipmap.back);
            left.setBounds(0, 0, left.getMinimumWidth(), left.getMinimumHeight());
            mLeft.setCompoundDrawables(left, null, null, null);
            mLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mActivity.finish();
                }
            });
        } else {
            mLeft.setCompoundDrawables(null, null, null, null);
            mLeft.setClickable(false);
        }
    }

    public void setHomeTitleMenu(String title, boolean isShowBack, View.OnClickListener listener) {
        if (isShowBack) {
            Drawable left = ContextCompat.getDrawable(mActivity, R.mipmap.back);
            left.setBounds(0, 0, left.getMinimumWidth(), left.getMinimumHeight());
            mLeft.setCompoundDrawables(left, null, null, null);
            if (listener != null) {
                mLeft.setOnClickListener(listener);
            } else {
                mLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mActivity.finish();
                    }
                });
            }
        } else {
            mLeft.setCompoundDrawables(null, null, null, null);
            mLeft.setClickable(false);
        }

        home_left.setVisibility(View.VISIBLE);
        home_left.setText(title);


//        Drawable left = ContextCompat.getDrawable(mActivity, R.mipmap.caidan);
//        left.setBounds(0, 0, left.getMinimumWidth(), left.getMinimumHeight());
//        home_left.setCompoundDrawables(left, null, null, null);
//        if (listener!=null) {
//            home_left.setOnClickListener(listener);
//        }
    }

    /**
     * 设置title与返回键
     *
     * @param isShowBack 是否显示返回按钮
     * @param listener   返回按钮的点击事件，默认为传入null  销毁当前activity
     * @param title      标题
     */
    public void setTitle(boolean isShowBack, View.OnClickListener listener, String title) {
        mTitle.setText(title);
//        tv_titleLeft.setText(title);
        if (isShowBack) {
            Drawable left = ContextCompat.getDrawable(mActivity, R.mipmap.back);
            left.setBounds(0, 0, left.getMinimumWidth(), left.getMinimumHeight());
            mLeft.setCompoundDrawables(left, null, null, null);
            if (listener != null) {
                mLeft.setOnClickListener(listener);
            } else {
                mLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mActivity.finish();
                    }
                });
            }
        } else {
            mLeft.setCompoundDrawables(null, null, null, null);
            mLeft.setClickable(false);
        }
    }


    /**
     * 设置title与返回键
     *
     * @param isShowBack 是否显示返回按钮
     * @param listener   返回按钮的点击事件，默认为传入null  销毁当前activity
     * @param title      标题
     */
    public void setTitle(boolean isShowBack, View.OnClickListener listener, int title) {
        mTitle.setText(title);
//        tv_titleLeft.setText(title);
        if (isShowBack) {
            Drawable left = ContextCompat.getDrawable(mActivity, R.mipmap.back);
            left.setBounds(0, 0, left.getMinimumWidth(), left.getMinimumHeight());
            mLeft.setCompoundDrawables(left, null, null, null);
            if (listener != null) {
                mLeft.setOnClickListener(listener);
            } else {
                mLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mActivity.finish();
                    }
                });
            }
        } else {
            mLeft.setCompoundDrawables(null, null, null, null);
            mLeft.setClickable(false);
        }
    }

    /**
     * 设置title与返回键
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        setTitle(true, null, title);
    }

    public void setTitle(int title) {
        setTitle(true, null, title);
    }

    /**
     * 设置title与返回键
     *
     * @param isShowBack
     * @param title
     */
    public void setTitle(boolean isShowBack, String title) {
        setTitle(isShowBack, null, title);
    }

    /**
     * 设置title与返回键,背景色
     *
     * @param isShowBack
     * @param title
     */
    public void setTitle(boolean isShowBack, int toolbarColor, String title) {
        setTitle(isShowBack, null, toolbarColor, title);
    }

    public void setTitleTextSize(float size) {
        mTitle.setTextSize(size);
    }

    /**
     * 显示关闭按钮
     */
    public void showClose(View.OnClickListener listener) {
        mClose.setVisibility(View.VISIBLE);
        mClose.setText(mActivity.getResources().getString(R.string.text_close));
        mClose.setTextColor(mActivity.getResources().getColor(R.color.colorAccent));
        if (listener != null) {
            mClose.setOnClickListener(listener);
        } else {
            mClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mActivity.finish();
                }
            });
        }
    }

    /**
     * 隐藏关闭按钮
     */
    public void hintClose() {
        mClose.setVisibility(View.GONE);
    }

    /**
     * 设置右边文字
     *
     * @param right
     * @param rightOnClick
     */
    public void setRightTitle(String right, View.OnClickListener rightOnClick) {
        mRight.setText(right);
        if (rightOnClick == null) {
            mRight.setClickable(false);
            return;
        }
        mRight.setOnClickListener(rightOnClick);
    }

    /**
     * 设置右边字体颜色
     *
     * @param rightTileColor
     */
    public void setRightTileColor(@ColorRes int rightTileColor) {
        mRight.setTextColor(mActivity.getResources().getColor(rightTileColor));
    }

    /**
     * 设置右边文字和颜色
     *
     * @param right
     * @param rightOnClick
     */
    public void setRightTitleAndColor(String right, int color, View.OnClickListener rightOnClick) {
        mRight.setText(right);
        mRight.setTextColor(color);
        if (rightOnClick == null) {
            mRight.setClickable(false);
            return;
        }
        mRight.setOnClickListener(rightOnClick);
    }

    /**
     * 设置右边文字
     *
     * @param right
     * @param rightOnClick
     */
    public void setRightTitle(SpannableStringBuilder right, View.OnClickListener rightOnClick) {
        mRight.setText(right);
        if (rightOnClick == null) {
            mRight.setClickable(false);
            return;
        }
        mRight.setOnClickListener(rightOnClick);
    }


    /**
     * 设置右边文字
     *
     * @param right
     */
    public void setRightTitle(String right) {
        mRight.setText(right);
    }

    /**
     * 设置右边文字
     */
    public void setRightTitleColor(String color) {
        mRight.setTextColor(Color.parseColor(color));
    }

    /**
     * 设置标题右边图片
     *
     * @param rightRes
     * @param rightOnClick
     */
    public void setRightTitle(int rightRes, View.OnClickListener rightOnClick) {
        if (rightRes > 0) {
            Drawable img = ContextCompat.getDrawable(mActivity, rightRes);
            img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
            mRight.setCompoundDrawables(img, null, null, null);
        } else {
            mRight.setCompoundDrawables(null, null, null, null);
        }
        if (rightOnClick != null) {
            mRight.setOnClickListener(rightOnClick);
        } else {
            mRight.setClickable(false);
        }
    }

    public void setLeftText(String text) {
        mLeft.setText(text);
    }

    public void setLeftTitle(int rightRes, View.OnClickListener rightOnClick) {
        if (rightRes > 0) {
            Drawable img = ContextCompat.getDrawable(mActivity, rightRes);
            img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
            mLeft.setCompoundDrawables(img, null, null, null);
        } else {
            mLeft.setCompoundDrawables(null, null, null, null);
        }
        if (rightOnClick != null) {
            mLeft.setOnClickListener(rightOnClick);
        } else {
            mLeft.setClickable(false);
        }
    }

}
