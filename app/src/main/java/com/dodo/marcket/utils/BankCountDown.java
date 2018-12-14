package com.dodo.marcket.utils;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

import com.dodo.marcket.iCallback.MiaoShaCountdownListener;

import java.text.SimpleDateFormat;

/**
 * Created by yanghongyu on 2017/12/28.
 * 绑定银行卡倒计时
 */

public class BankCountDown extends CountDownTimer {

    private MiaoShaCountdownListener mListener;
    private SimpleDateFormat hour,minute,second;
    public BankCountDown(long millisInFuture, long countDownInterval, MiaoShaCountdownListener mListener) {
        super(millisInFuture, countDownInterval);
        this.mListener = mListener;
        hour  = new SimpleDateFormat("HH");
        minute  = new SimpleDateFormat("mm");
        second  = new SimpleDateFormat("ss");
    }

    @Override
    public void onTick(long millisUntilFinished) {
        int tian = (int) (millisUntilFinished/1000/60/60/24);
        int xiaoshi = (int) (millisUntilFinished/1000/60/60)%60;
        int fenzhong = (int) (millisUntilFinished/1000/60)%60;
        int secound = (int) ((millisUntilFinished/1000)%60);

        mListener.timeCountdown(tian+"",xiaoshi+"",fenzhong+"",secound+"");
    }

    @Override
    public void onFinish() {
        // 在这里进行设置解决时间停留的问题
//        mListener.timeCountdown("00","00","00");
        mListener.timeFinish();
    }
}
