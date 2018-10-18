package com.dodo.marcket.wedget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dodo.marcket.R;
import com.dodo.marcket.utils.ImageLoaders;
import com.dodo.marcket.utils.ScreenUtil;
import com.dodo.marcket.wedget.photoview.PhotoView;


/**
 * Created 2017/12/25.
 */

public class AdDialog extends Dialog implements DialogInterface.OnKeyListener {

    private static AdDialog dialog;
    private LinearLayout ll;
    private ImageView big_Image,mImg_cancel;
    private Context mContext;

    public static AdDialog getInstance(Context context) {
        if (dialog == null) {
            dialog = new AdDialog(context);
        }
        return dialog;
    }

    public AdDialog(Context context) {
        this(context, R.style.dialog);
        setCanceledOnTouchOutside(false);
    }

    public AdDialog(Context context, int theme) {
        super(context, theme);
        setContentView(R.layout.layout_ad);
        setOnKeyListener(this);
        mContext = context;
        int screenWidth = ScreenUtil.getScreenWidth(context);
        int screenHeight = ScreenUtil.getScreenHeight(context);

        big_Image = findViewById(R.id.mIng_bigImage);
        mImg_cancel = findViewById(R.id.mImg_cancel);

        ll = findViewById(R.id.ll);
        big_Image.getLayoutParams().width = screenWidth-ScreenUtil.dip2px(mContext,60);
        big_Image.getLayoutParams().height = (screenWidth-ScreenUtil.dip2px(mContext,60))* (6/5) ;

        mImg_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    public void setImg(String url){
        ImageLoaders.displayImage2(big_Image,url);
    }



    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (mOnKeyBackListener != null) {
                mOnKeyBackListener.keyBack();
                dismiss();
            }
        }
        return false;
    }

    onKeyBackListener mOnKeyBackListener;

    public void setOnKeyBackListener(onKeyBackListener listener) {
        mOnKeyBackListener = listener;
    }

    public interface onKeyBackListener {
        void keyBack();
    }



    private OnKeepListener onKeepListener;

    public void setOnKeepListener(OnKeepListener listener) {
        onKeepListener = listener;
    }

    public interface OnKeepListener {
        void setMessage(String selectDate, String amOrPm);
    }
}
