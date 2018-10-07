package com.dodo.marcket.business.mine.fragment;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseFragment;
import com.dodo.marcket.bean.basebean.UserInfoBean;
import com.dodo.marcket.business.HomeActivity;
import com.dodo.marcket.business.mine.activity.BackBoxActivity;
import com.dodo.marcket.business.mine.activity.DisCountActivity;
import com.dodo.marcket.business.mine.activity.MyAddressActivity;
import com.dodo.marcket.business.mine.activity.MyBackBoxActivity;
import com.dodo.marcket.business.mine.activity.MyOrderActivity;
import com.dodo.marcket.business.mine.activity.SalesManActivity;
import com.dodo.marcket.business.mine.constrant.MineFragmentContract;
import com.dodo.marcket.business.mine.presenter.MineFragmentPresenter;
import com.dodo.marcket.utils.SharedPreferencesUtil;
import com.dodo.marcket.utils.statusbar.StatusBarUtils;
import com.dodo.marcket.wedget.CircleImageView;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MineFragment extends BaseFragment<MineFragmentPresenter> implements MineFragmentContract.View {


    public static MineFragment mineFragment;
    @BindView(R.id.mTxt_salesMan)
    TextView mTxtSalesMan;
    @BindView(R.id.mLL_salesMan)
    LinearLayout mLLSalesMan;
    @BindView(R.id.mTxt_postAddress)
    TextView mTxtPostAddress;
    @BindView(R.id.mLL_postAddress)
    LinearLayout mLLPostAddress;
    @BindView(R.id.mLL_allOrder)
    LinearLayout mLLAllOrder;
    @BindView(R.id.mLL_dealwith)
    LinearLayout mLLDealwith;
    @BindView(R.id.mLL_send)
    LinearLayout mLLSend;
    @BindView(R.id.mLL_deliver)
    LinearLayout mLLDeliver;
    @BindView(R.id.mLL_finish)
    LinearLayout mLLFinish;
    @BindView(R.id.mLL_discount)
    LinearLayout mLLDiscount;
    @BindView(R.id.mLL_address)
    LinearLayout mLLAddress;
    @BindView(R.id.mLL_backMoney)
    LinearLayout mLLBackMoney;
    @BindView(R.id.mLL_backBox)
    LinearLayout mLLBackBox;
    @BindView(R.id.mTxt_loginOut)
    TextView mTxtLoginOut;
    Unbinder unbinder;
    @BindView(R.id.mLL_status)
    LinearLayout mLLStatus;
    @BindView(R.id.mLL_cancel)
    LinearLayout mLLCancel;
    @BindView(R.id.mImg_icon)
    CircleImageView mImgIcon;
    @BindView(R.id.mTxt_userName)
    TextView mTxtUserName;
    @BindView(R.id.mTxt_memberRank)
    TextView mTxtMemberRank;
    @BindView(R.id.mTxt_point)
    TextView mTxtPoint;
    @BindView(R.id.mTxt_waitPay)
    TextView mTxtWaitPay;
    @BindView(R.id.mTxt_waitSend)
    TextView mTxtWaitSend;
    @BindView(R.id.mTxt_waitPost)
    TextView mTxtWaitPost;
    @BindView(R.id.mTxt_waitFinish)
    TextView mTxtWaitFinish;
    @BindView(R.id.mTxt_waitCancel)
    TextView mTxtWaitCancel;
    Unbinder unbinder1;


    public static MineFragment getInstance() {
        if (mineFragment == null)
            mineFragment = new MineFragment();
        return mineFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
    }

    @Override
    public void loadData() {
        initTitle();
        mPresenter.getUserMsg();//获取个人信息
    }

    private void initTitle() {
        mLLStatus.getLayoutParams().height = StatusBarUtils.getStatusBarHeight(mContext);

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
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            mPresenter.getUserMsg();
        }
    }


    @OnClick({R.id.mLL_salesMan, R.id.mLL_postAddress, R.id.mLL_allOrder, R.id.mLL_dealwith, R.id.mLL_send, R.id.mLL_deliver, R.id.mLL_finish, R.id.mLL_discount, R.id.mLL_address, R.id.mLL_backMoney, R.id.mLL_backBox, R.id.mTxt_loginOut})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mLL_salesMan://跳转业务员
                startActivity(SalesManActivity.class);
                break;
            case R.id.mLL_postAddress://跳转送货点列表

                break;
            case R.id.mLL_allOrder://查看所有订单
                Bundle bundle1 = new Bundle();
                bundle1.putInt("currentPage",0);
                startActivity(MyOrderActivity.class,bundle1);
                startActivity(MyOrderActivity.class);
                break;
            case R.id.mLL_dealwith://待处理
                Bundle bundle2 = new Bundle();
                bundle2.putInt("currentPage",2);
                startActivity(MyOrderActivity.class,bundle2);
                startActivity(MyOrderActivity.class);
                break;
            case R.id.mLL_send://待发送
                Bundle bundle3 = new Bundle();
                bundle3.putInt("currentPage",1);
                startActivity(MyOrderActivity.class,bundle3);
                break;
            case R.id.mLL_deliver://已发货
                Bundle bundle4 = new Bundle();
                bundle4.putInt("currentPage",4);
                startActivity(MyOrderActivity.class,bundle4);
                break;
            case R.id.mLL_finish://已完成
                Bundle bundle5 = new Bundle();
                bundle5.putInt("currentPage",3);
                startActivity(MyOrderActivity.class,bundle5);
                break;
            case R.id.mLL_discount://跳转优惠券
                startActivity(DisCountActivity.class);
                break;
            case R.id.mLL_address://跳转我的地址
                startActivity(MyAddressActivity.class);
                break;
            case R.id.mLL_backMoney://申请退框
                startActivity(BackBoxActivity.class);
                break;
            case R.id.mLL_backBox://我的退筐单
                startActivity(MyBackBoxActivity.class);
                break;
            case R.id.mTxt_loginOut://退出登陆
                SharedPreferencesUtil.clear(mContext);
                hastoken = false;
                ((HomeActivity) mActivity).selectRg(0);

                break;
        }
    }

    //获取个人信息
    @Override
    public void getUserMsg(UserInfoBean userInfoBean) {

        String username = userInfoBean.getUsername();
        String memberRank = userInfoBean.getMemberRank();
        boolean isHasSalesman = userInfoBean.isIsHasSalesman();

        int noPayOrderNumber = userInfoBean.getNoPayOrderNumber();//待付款订单数量
        int voucherNumber = userInfoBean.getVoucherNumber();//优惠券数量
        int noRecevedOrder = userInfoBean.getNoRecevedOrder();//待收货订单数量
        int hasPayOrder = userInfoBean.getHasPayOrder();//已完成订单数量

        if (noPayOrderNumber==0){//待付款订单数量
            mTxtWaitPay.setVisibility(View.GONE);
        }else {
            mTxtWaitPay.setVisibility(View.GONE);
            mTxtWaitPay.setText(noPayOrderNumber+"");
        }

        if (noRecevedOrder==0){//待收货订单数量
            mTxtWaitPost.setVisibility(View.GONE);
        }else {
            mTxtWaitPost.setVisibility(View.GONE);
            mTxtWaitPost.setText(noPayOrderNumber+"");
        }

        if (hasPayOrder==0){//已完成订单数量
            mTxtWaitFinish.setVisibility(View.GONE);
        }else {
            mTxtWaitFinish.setVisibility(View.GONE);
            mTxtWaitFinish.setText(noPayOrderNumber+"");
        }

        mTxtWaitCancel.setVisibility(View.GONE);
        mTxtWaitSend.setVisibility(View.GONE);


        //用户名
        mTxtUserName.setText(username);

        //会员等级
        if (TextUtils.isEmpty(memberRank)) {
            mTxtMemberRank.setVisibility(View.GONE);
        } else {
            mTxtMemberRank.setVisibility(View.VISIBLE);
            mTxtMemberRank.setText(memberRank);
        }

        //是否绑定业务员
        if (isHasSalesman) {
            mTxtSalesMan.setText(userInfoBean.getSalesmanName());
        } else {
            mTxtSalesMan.setText("去绑定业务员");
        }


    }

}
