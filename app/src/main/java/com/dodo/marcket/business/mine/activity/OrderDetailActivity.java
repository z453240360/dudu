package com.dodo.marcket.business.mine.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseActivity;
import com.dodo.marcket.business.mine.constrant.OrderDetailContract;
import com.dodo.marcket.business.mine.presenter.OrderDetailPresenter;

public class OrderDetailActivity extends BaseActivity<OrderDetailPresenter> implements OrderDetailContract.View {


    @Override
    public int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    public void initPresenter() {

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
}
