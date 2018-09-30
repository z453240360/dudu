package com.dodo.marcket.business.mine.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseActivity;
import com.dodo.marcket.business.mine.constrant.CommentOrderContract;
import com.dodo.marcket.business.mine.presenter.CommentOrderPresenter;

public class CommentOrderActivity extends BaseActivity<CommentOrderPresenter> implements CommentOrderContract.View {

    @Override
    public int getLayoutId() {
        return R.layout.activity_comment_order;
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

    //评价订单
    @Override
    public void discussOrder(int id) {

    }
}
