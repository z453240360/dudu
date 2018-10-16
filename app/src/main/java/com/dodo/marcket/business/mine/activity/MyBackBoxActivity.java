package com.dodo.marcket.business.mine.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseActivity;
import com.dodo.marcket.bean.MyBackBoxBean;
import com.dodo.marcket.business.mine.adapter.MyBackBoxAdapter;
import com.dodo.marcket.business.mine.constrant.BackMoneyContract;
import com.dodo.marcket.business.mine.presenter.BackMoneyPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 我的退框单
 */
public class MyBackBoxActivity extends BaseActivity<BackMoneyPresenter> implements BackMoneyContract.View {


    @BindView(R.id.mRv_myBackBox)
    XRecyclerView mRvMyBackBox;
    @BindView(R.id.mLL_noDate)
    LinearLayout mLLNoDate;
    private List<MyBackBoxBean.ListBean> mDatas = new ArrayList<>();
    private LinearLayoutManager manager;
    private MyBackBoxAdapter adapter;
    private int pageNumber = 1;
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

        mTitle.setTitle("我的退筐单");

        initRv();

        mPresenter.getMyBackBoxList(pageNumber, pageSize);//获取我的退框单列表
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
        if (pageNumber==1){
            mRvMyBackBox.refreshComplete();
        }else {
            mRvMyBackBox.loadMoreComplete();
        }
    }

    //我的退框单列表
    @Override
    public void myBackBoxList(MyBackBoxBean backBoxBean) {
        if (pageNumber==1){
            mRvMyBackBox.refreshComplete();
        }else {
            mRvMyBackBox.loadMoreComplete();
        }

        if (backBoxBean == null) {
            mLLNoDate.setVisibility(View.VISIBLE);
            return;
        }

        List<MyBackBoxBean.ListBean> list = backBoxBean.getList();
        if (list==null||list.size()==0){
            mLLNoDate.setVisibility(View.VISIBLE);
            return;
        }

        mLLNoDate.setVisibility(View.GONE);
        if (pageNumber == 1) {
            mDatas.clear();
        }
        mDatas.addAll(list);
        adapter.notifyDataSetChanged();
    }

    //初始化列表
    public void initRv() {
        manager = new LinearLayoutManager(mContext);
        adapter = new MyBackBoxAdapter(mContext, mDatas);
        mRvMyBackBox.setAdapter(adapter);
        mRvMyBackBox.setLayoutManager(manager);

        mRvMyBackBox.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                pageNumber = 1;
                mPresenter.getMyBackBoxList(pageNumber,pageSize);
            }

            @Override
            public void onLoadMore() {
                pageNumber++;
                mPresenter.getMyBackBoxList(pageNumber,pageSize);
            }
        });
    }

}
