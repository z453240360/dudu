package com.dodo.marcket.business.mine.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseActivity;
import com.dodo.marcket.bean.LoginBean;
import com.dodo.marcket.business.HomeActivity;
import com.dodo.marcket.business.mine.constrant.LoginContract;
import com.dodo.marcket.business.mine.presenter.LoginPresenter;
import com.dodo.marcket.http.constant.Constant;
import com.dodo.marcket.utils.SharedPreferencesUtil;
import com.dodo.marcket.utils.Tool;
import com.dodo.marcket.wedget.ClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {


    @BindView(R.id.mEd_phone)
    ClearEditText mEdPhone;
    @BindView(R.id.mEd_psw)
    EditText mEdPsw;
    @BindView(R.id.mTxt_ss)
    TextView mTxtSs;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void showLoading(String content) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorMsg(String msg, String type) {

    }


    @OnClick({R.id.mTxt_ss, R.id.mTxt_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mTxt_ss://倒计时获取验证码
                countTimeDown();
                String phoneNumber = mEdPhone.getText().toString().trim();
                mPresenter.getVerificationCode(phoneNumber);
                break;
            case R.id.mTxt_login://登陆
                String phone = mEdPhone.getText().toString().trim();
                String psw = mEdPsw.getText().toString().trim();

                mPresenter.getLogin(phone,psw);
                break;
        }
    }

    //倒计时 60 S
    private void countTimeDown() {
        Tool.countTime(60, new Subscriber<Integer>() {
            @Override
            public void onStart() {
                super.onStart();
                mTxtSs.setClickable(false);
                mTxtSs.setTextColor(Color.parseColor("#ffffff"));
            }

            @Override
            public void onCompleted() {
                String phone = mEdPhone.getText().toString().trim();
                if (phone.length() == 13) {
                    mTxtSs.setText("获取验证码");
                    mTxtSs.setClickable(true);
                    mTxtSs.setTextColor(Color.parseColor("#ffffff"));
                } else {
                    mTxtSs.setText("获取验证码");
                    mTxtSs.setClickable(true);
                    mTxtSs.setTextColor(Color.parseColor("#99ffffff"));
                }
            }

            @Override
            public void onError(Throwable e) {
                mTxtSs.setText("获取验证码");
                mTxtSs.setClickable(true);
                mTxtSs.setTextColor(Color.parseColor("#99ffffff"));
            }

            @Override
            public void onNext(Integer integer) {
                mTxtSs.setText("" + integer + "s");
            }
        });
    }

    @Override
    public void getVerificationCode() {

    }

    @Override
    public void getLogin(LoginBean loginBean) {
        SharedPreferencesUtil.put(mContext, Constant.token,loginBean.getToken());
        finish();
    }
}
