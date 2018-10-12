package com.dodo.marcket.business.mine.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseFragment;
import com.dodo.marcket.bean.DisCountBean;
import com.dodo.marcket.business.HomeActivity;
import com.dodo.marcket.business.mine.adapter.DisCountAdapter;
import com.dodo.marcket.business.mine.constrant.DisCountFragmentContract;
import com.dodo.marcket.business.mine.presenter.DisCountFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


@SuppressLint("ValidFragment")
public class DisCountFragment extends BaseFragment<DisCountFragmentPresenter> implements DisCountFragmentContract.View {


    public static DisCountFragment orderFragment;
    @BindView(R.id.mRv_discount)
    RecyclerView mRvDiscount;

    private int status;
    protected boolean isViewInitiated;
    protected boolean isVisibleToUser;
    protected boolean isDataInitiated = true;

    private LinearLayoutManager manager;
    private DisCountAdapter adapter;
    private List<DisCountBean> mDates = new ArrayList<>();

    public static DisCountFragment getInstance() {
        if (orderFragment == null)
            orderFragment = new DisCountFragment();
        return orderFragment;
    }

    public DisCountFragment() {

    }

    public DisCountFragment(int status) {
        this.status = status;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_discount;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
    }

    @Override
    public void loadData() {
        initRv();

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
        adapter = new DisCountAdapter(mContext, mDates);
        manager = new LinearLayoutManager(mContext);
        mRvDiscount.setAdapter(adapter);
        mRvDiscount.setLayoutManager(manager);
        adapter.setOnItemClickListener(new DisCountAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int parentPos) {

            }

            @Override
            public void onUseClic(int pos) {
                startActivity(HomeActivity.class);
            }
        });
    }


    //当前页面是否可见
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        getDate();
    }

    //当前是否创建
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        getDate();
    }

    //获取网络数据
    public void getDate() {
        if (isViewInitiated && isVisibleToUser && isDataInitiated) {
            mPresenter.getDisCount(status);
        }
    }

    //获取优惠券
    @Override
    public void getDisCount(List<DisCountBean> s) {
        if (s == null || s.size() == 0) {
            return;
        }

        mDates.clear();
        mDates.addAll(s);
        adapter.notifyDataSetChanged();
    }
}
