package com.dodo.marcket.business.shoppingcar.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseActivity;
import com.dodo.marcket.bean.MyAddressBean;
import com.dodo.marcket.business.shoppingcar.constrant.GoToPayContract;
import com.dodo.marcket.business.shoppingcar.presenter.GoToPayPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 去结算页面
 */
public class GoToPayActivity extends BaseActivity<GoToPayPresenter> implements GoToPayContract.View {

    @BindView(R.id.mLL_showAddress1)
    LinearLayout mLLShowAddress1;
    @BindView(R.id.mLL_showAddress2)
    LinearLayout mLLShowAddress2;
    @BindView(R.id.mLL_address)
    LinearLayout mLLAddress;
    @BindView(R.id.mTxt_arriveTime)
    TextView mTxtArriveTime;
    @BindView(R.id.mTxt_payMethed)
    TextView mTxtPayMethed;
    @BindView(R.id.ll_update)
    LinearLayout llUpdate;
    @BindView(R.id.mRv_product)
    RecyclerView mRvProduct;
    @BindView(R.id.mTxt_give)
    TextView mTxtGive;
    @BindView(R.id.mLL_give)
    LinearLayout mLLGive;
    @BindView(R.id.mTxt_coupon)
    TextView mTxtCoupon;
    @BindView(R.id.mLL_coupon)
    LinearLayout mLLCoupon;
    @BindView(R.id.mTxt_point)
    TextView mTxtPoint;
    @BindView(R.id.mLL_point)
    LinearLayout mLLPoint;
    @BindView(R.id.mTxt_realPay)
    TextView mTxtRealPay;
    @BindView(R.id.mTxt_postMoney)
    TextView mTxtPostMoney;
    @BindView(R.id.mTxt_sendPrice)
    TextView mTxtSendPrice;
    @BindView(R.id.mLL_sendPrice)
    LinearLayout mLLSendPrice;
    @BindView(R.id.mTxt_pay)
    TextView mTxtPay;
    @BindView(R.id.mLL_bottomView)
    LinearLayout mLLBottomView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_go_to_pay;
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

    //获取地址
    @Override
    public void getMyAddress(List<MyAddressBean> s) {
        if (s == null || s.size() == 0) {
            mLLShowAddress1.setVisibility(View.VISIBLE);
            mLLShowAddress2.setVisibility(View.GONE);
        }else {
            mLLShowAddress1.setVisibility(View.GONE);
            mLLShowAddress2.setVisibility(View.VISIBLE);

            for (int i = 0; i < s.size(); i++) {
                MyAddressBean myAddressBean = s.get(i);
            }
        }
    }

}
