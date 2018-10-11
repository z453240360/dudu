package com.dodo.marcket.business.mine.activity;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseActivity;
import com.dodo.marcket.bean.LoginBean;
import com.dodo.marcket.business.mine.constrant.LoginContract;
import com.dodo.marcket.business.mine.presenter.LoginPresenter;
import com.dodo.marcket.http.constant.Constant;
import com.dodo.marcket.utils.SharedPreferencesUtil;
import com.dodo.marcket.utils.Tool;
import com.dodo.marcket.wedget.ClearEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
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
        mTitle.setTitle("登录");
    }

    @Override
    public void showLoading(String content) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorMsg(String msg, String type) {
        showErrorToast(msg);
    }


    @OnClick({R.id.mTxt_ss, R.id.mTxt_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mTxt_ss://倒计时获取验证码
                countTimeDown();
                String phoneNumber = mEdPhone.getText().toString().trim();

                phoneNumber = changePhoneNumber(phoneNumber).toString();
                mPresenter.getVerificationCode(phoneNumber);
                break;
            case R.id.mTxt_login://登陆
                String phone = mEdPhone.getText().toString().trim();
                String psw = mEdPsw.getText().toString().trim();
                phone = changePhoneNumber(phone).toString();
                mPresenter.getLogin(phone,psw);
                break;
        }
    }

    @NonNull
    private StringBuffer changePhoneNumber(String phone) {
        List<Character> phoneNumber = new ArrayList<>();
        char[] chars = phone.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                phoneNumber.add(chars[i]);
            }
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < phoneNumber.size(); i++) {
            buffer.append(phoneNumber.get(i).toString());
        }
        return buffer;
    }

    //倒计时 60 S
    private void countTimeDown() {
        Tool.countTime(60, new Subscriber<Integer>() {
            @Override
            public void onStart() {
                super.onStart();
                mTxtSs.setClickable(false);
                mTxtSs.setTextColor(Color.parseColor("#888888"));
            }

            @Override
            public void onCompleted() {
                String phone = mEdPhone.getText().toString().trim();

                if (phone.length() == 13) {
                    mTxtSs.setText("获取验证码");
                    mTxtSs.setClickable(true);
                    mTxtSs.setTextColor(getResources().getColor(R.color.basicColor));
                } else {
                    mTxtSs.setText("获取验证码");
                    mTxtSs.setClickable(true);
                    mTxtSs.setTextColor(getResources().getColor(R.color.basicColor));
                }
            }

            @Override
            public void onError(Throwable e) {
                mTxtSs.setText("获取验证码");
                mTxtSs.setClickable(true);
                mTxtSs.setTextColor(getResources().getColor(R.color.basicColor));
            }

            @Override
            public void onNext(Integer integer) {
                mTxtSs.setClickable(false);
                mTxtSs.setTextColor(Color.parseColor("#888888"));
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
