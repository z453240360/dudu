package com.dodo.marcket.business.mine.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseFragment;
import com.dodo.marcket.bean.OrderList;
import com.dodo.marcket.business.mine.activity.OrderDetailActivity;
import com.dodo.marcket.business.mine.adapter.MultiAdapter;
import com.dodo.marcket.business.mine.constrant.OrderFragmentContract;
import com.dodo.marcket.business.mine.presenter.OrderFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;


public class OrderFragment extends BaseFragment<OrderFragmentPresenter> implements OrderFragmentContract.View {


    public static OrderFragment orderFragment;
    @BindView(R.id.mRv_order)
    RecyclerView mRvOrder;
    Unbinder unbinder;

    protected boolean isViewInitiated;
    protected boolean isVisibleToUser;
    protected boolean isDataInitiated = true;

    private LinearLayoutManager manager;
    private MultiAdapter adapter;
    private List<OrderList> mDates = new ArrayList<>();

    public static OrderFragment getInstance() {
        if (orderFragment == null)
            orderFragment = new OrderFragment();
        return orderFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order;
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

    }

    //初始化列表
    private void initRv() {
        adapter = new MultiAdapter(mContext,mDates);
        manager = new LinearLayoutManager(mContext);
        mRvOrder.setAdapter(adapter);
        mRvOrder.setLayoutManager(manager);

        adapter.setOnItemClickListener(new MultiAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, View view) {
                startActivity(OrderDetailActivity.class);
            }
        });

    }
    //获取订单列表
    @Override
    public void getOrderList(List<OrderList> orderLists) {
        if (orderLists==null||orderLists.size()==0){
            return;
        }
        isDataInitiated = false;
        mDates.addAll(orderLists);
        adapter.notifyDataSetChanged();
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
            mPresenter.getOrder("");
        }
    }
}
