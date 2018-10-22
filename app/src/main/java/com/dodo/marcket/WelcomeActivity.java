package com.dodo.marcket;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

import com.dodo.marcket.business.HomeActivity;
import com.dodo.marcket.utils.ColorState;
import com.dodo.marcket.utils.Tool;
import com.dodo.marcket.utils.statusbar.StatusBarUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;

public class WelcomeActivity extends AppCompatActivity {


    @BindView(R.id.mTxt_countTime)
    TextView mTxtCountTime;
    private int COUNTDOWNTIME = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        ButterKnife.bind(this);
        //层垫式状态栏
//        StatusBarUtils.setColor(this, getResources().getColor(R.color.white), 0);
//        ColorState.StatusBarLightMode(this, false);
        countTimeDown();
    }


    //倒计时 60 S
    private void countTimeDown() {
        Tool.countTime(COUNTDOWNTIME, new Subscriber<Integer>() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onCompleted() {
                startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
                finish();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                mTxtCountTime.setText(integer+"s");
            }
        });
    }
}
