package com.dodo.marcket.business.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseActivity;
import com.dodo.marcket.bean.MyAddressBean;
import com.dodo.marcket.business.mine.adapter.AddressAdapter;
import com.dodo.marcket.business.mine.constrant.MyAddressContract;
import com.dodo.marcket.business.mine.presenter.MyAddressPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAddressActivity extends BaseActivity<MyAddressPresenter> implements MyAddressContract.View {

    @BindView(R.id.mTxt_addNewAddress)
    TextView mTxtAddNewAddress;
    @BindView(R.id.mRv_address)
    RecyclerView mRvAddress;

    private List<MyAddressBean> mDatas = new ArrayList<>();
    private AddressAdapter addressAdapter;
    private LinearLayoutManager manager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_address;
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

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getMyAddress();
    }

    //新增会员地址
    @OnClick(R.id.mTxt_addNewAddress)
    public void onViewClicked() {

        Bundle bundle = new Bundle();
        bundle.putString("fromWhere","2");//1 说明是从编辑接口过来的，2 表示直接进行编辑

        startActivity(AddNewAddressActivity.class,bundle);
    }

    //获取会员地址
    @Override
    public void getMyAddress(List<MyAddressBean> myAddressBeanList) {
        if (myAddressBeanList==null||myAddressBeanList.size()==0){

            return;
        }else {
            mDatas.clear();
            mDatas.addAll(myAddressBeanList);
            addressAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void deleteReceiver() {

    }


    //初始化列表
    private void initRv() {
        manager = new LinearLayoutManager(mContext);
        addressAdapter = new AddressAdapter(mContext, mDatas);
        mRvAddress.setLayoutManager(manager);
        mRvAddress.setAdapter(addressAdapter);
        addressAdapter.setOnItemClickListener(new AddressAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, MyAddressBean areaId) {//点击编辑地址

                Bundle bundle = new Bundle();
                bundle.putString("fromWhere","1");//1 说明是从编辑接口过来的，2 表示直接进行编辑
                bundle.putSerializable("addressBean",areaId);

                startActivity(AddNewAddressActivity.class,bundle);
            }
        });
    }

}
