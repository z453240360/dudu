package com.dodo.marcket.business.mine.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseActivity;
import com.dodo.marcket.bean.BackBoxBean;
import com.dodo.marcket.bean.MyBackBoxBean;
import com.dodo.marcket.business.mine.adapter.MyBackBoxAdapter;
import com.dodo.marcket.business.mine.constrant.BackMoneyContract;
import com.dodo.marcket.business.mine.presenter.BackMoneyPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的退框单
 */
public class MyBackBoxActivity extends BaseActivity<BackMoneyPresenter> implements BackMoneyContract.View {


    @BindView(R.id.mRv_myBackBox)
    RecyclerView mRvMyBackBox;
    private List<MyBackBoxBean> mDatas = new ArrayList<>();
    private LinearLayoutManager manager;
    private MyBackBoxAdapter adapter;
    private int pageNumber=1;
    private int pageSize = 10;


    @Override
    public int getLayoutId() {
        return R.layout.activity_back_money;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
    }

    @Override
    public void loadData() {

        initRv();

        mPresenter.getMyBackBoxList(pageNumber,pageSize);//获取我的退框单列表
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

    //我的退框单列表
    @Override
    public void myBackBoxList(List<MyBackBoxBean> backBoxBeanList) {
        if (backBoxBeanList==null||backBoxBeanList.size()==0){
            return;
        }

        if (pageNumber==1){
            mDatas.clear();
        }
        mDatas.addAll(backBoxBeanList);
        adapter.notifyDataSetChanged();
    }

    //初始化列表
    public void initRv(){
        manager = new LinearLayoutManager(mContext);
        adapter = new MyBackBoxAdapter(mContext,mDatas);
        mRvMyBackBox.setAdapter(adapter);
        mRvMyBackBox.setLayoutManager(manager);
    }

}