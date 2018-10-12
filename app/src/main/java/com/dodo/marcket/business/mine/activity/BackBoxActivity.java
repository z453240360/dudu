package com.dodo.marcket.business.mine.activity;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseActivity;
import com.dodo.marcket.bean.BackBoxBean;
import com.dodo.marcket.business.mine.adapter.BackBoxAdapter;
import com.dodo.marcket.business.mine.constrant.BackBoxContract;
import com.dodo.marcket.business.mine.presenter.BackBoxPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 申请退框
 */
public class BackBoxActivity extends BaseActivity<BackBoxPresenter> implements BackBoxContract.View {


    @BindView(R.id.mRv_backBox)
    RecyclerView mRvBackBox;
    @BindView(R.id.mTxt_backBox)
    TextView mTxtBackBox;
    @BindView(R.id.mLL_noDate)
    LinearLayout mLLNoDate;
    private List<BackBoxBean> mDatas = new ArrayList<>();
    private LinearLayoutManager manager;
    private BackBoxAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_back_box;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
    }

    @Override
    public void loadData() {
        mTitle.setTitle("申请退筐");
        initRv();

        mPresenter.getBackBoxList();
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

    private void initRv() {
        manager = new LinearLayoutManager(mContext);
        adapter = new BackBoxAdapter(mContext, mDatas);
        mRvBackBox.setLayoutManager(manager);
        mRvBackBox.setAdapter(adapter);
    }

    @Override
    public void getBackBoxList(List<BackBoxBean> s) {

        if (s == null || s.size() == 0) {
            mLLNoDate.setVisibility(View.VISIBLE);
            return;
        }

        mLLNoDate.setVisibility(View.GONE);
        mDatas.clear();
        mDatas.addAll(s);
        adapter.notifyDataSetChanged();
    }


    //生成退框单
    @OnClick(R.id.mTxt_backBox)
    public void onViewClicked() {
//        mPresenter.getBackOrder();
    }
}
