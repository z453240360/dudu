package com.dodo.marcket.business.mine.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseActivity;
import com.dodo.marcket.bean.MyPointBean;
import com.dodo.marcket.business.mine.adapter.MyPointAdapter;
import com.dodo.marcket.business.mine.constrant.MyPointContract;
import com.dodo.marcket.business.mine.presenter.MyPointPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyPointActivity extends BaseActivity<MyPointPresenter> implements MyPointContract.View {

    @BindView(R.id.mTxt_myPoint)
    TextView mTxtMyPoint;
    @BindView(R.id.mLL_point)
    LinearLayout mLLPoint;
    @BindView(R.id.mRv_myPoint)
    XRecyclerView mRvMyPoint;
    @BindView(R.id.mLL_noDate)
    LinearLayout mLLNoDate;

    private int page = 1;
    private int pageSize = 10;
    private MyPointAdapter adapter;
    private LinearLayoutManager manager;
    private List<MyPointBean> myPointBeanList;
    private String myPoint;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_point;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
    }

    @Override
    public void loadData() {
        Bundle bundle = getIntent().getExtras();
        myPoint = bundle.getString("myPoint", "");

        if (!myPoint.equals("")){
            mTxtMyPoint.setText("我的积分 "+myPoint);
        }

        initTitle();
        initRv();
        mPresenter.getMyPoint(page,pageSize,"sd");
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
        if (page==1){
            mRvMyPoint.refreshComplete();
        }else {
            mRvMyPoint.loadMoreComplete();
        }
    }

    /**
     * 初始化标题
     */
    private void initTitle() {
        mTitle.setTitle("我的积分");
    }

    /**
     * 初始化列表
     */
    private void initRv() {
        myPointBeanList = new ArrayList<>();
        adapter = new MyPointAdapter(mContext, myPointBeanList);
        manager = new LinearLayoutManager(mContext);

        mRvMyPoint.setLayoutManager(manager);
        mRvMyPoint.setAdapter(adapter);

        mRvMyPoint.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mPresenter.getMyPoint(page,pageSize,"");
            }

            @Override
            public void onLoadMore() {
                mPresenter.getMyPoint(page,pageSize,"");
            }
        });
    }

    /**
     * 获取积分列表
     */
    @Override
    public void getMyPoint(List<MyPointBean> myPoints) {
        if (page==1){
            mRvMyPoint.refreshComplete();
        }else {
            mRvMyPoint.loadMoreComplete();
        }
        if (myPoints == null) {
            mLLPoint.setVisibility(View.GONE);
            mRvMyPoint.setVisibility(View.GONE);
            mLLNoDate.setVisibility(View.VISIBLE);
            return;
        }

        if (myPoints.size()==0){
            return;
        }

        if (page==1){
            myPointBeanList.clear();
            page++;
        }else {
            mRvMyPoint.loadMoreComplete();
        }

        mLLPoint.setVisibility(View.VISIBLE);
        mRvMyPoint.setVisibility(View.VISIBLE);
        mLLNoDate.setVisibility(View.GONE);

        myPointBeanList.addAll(myPoints);
        adapter.notifyDataSetChanged();
    }


}
