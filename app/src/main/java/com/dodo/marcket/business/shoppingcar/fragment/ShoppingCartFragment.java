package com.dodo.marcket.business.shoppingcar.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseFragment;
import com.dodo.marcket.bean.CartItemsBean;
import com.dodo.marcket.bean.ShoppingCarBean;
import com.dodo.marcket.bean.params.PayParamsFatherBean;
import com.dodo.marcket.business.homepage.activity.ProductDetailActivity;
import com.dodo.marcket.business.shoppingcar.activity.GoToPayActivity;
import com.dodo.marcket.business.shoppingcar.adapter.ShoppingCartAdapter;
import com.dodo.marcket.business.shoppingcar.constrant.ShoppingCartFragmentContract;
import com.dodo.marcket.business.shoppingcar.presenter.ShoppingCartFragmentPresenter;
import com.dodo.marcket.utils.ToastUtils;
import com.dodo.marcket.utils.statusbar.StatusBarUtils;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 购物车Tab
 */
public class ShoppingCartFragment extends BaseFragment<ShoppingCartFragmentPresenter> implements ShoppingCartFragmentContract.View {

    public static ShoppingCartFragment shoppingCartFragment;
    @BindView(R.id.mRv_firstList)
    RecyclerView mRvFirstList;
    @BindView(R.id.mImg_huishou)
    ImageView mImgHuishou;
    @BindView(R.id.ll_img)
    LinearLayout llImg;
    @BindView(R.id.mTxt_pay)
    TextView mTxtHuishou;
    @BindView(R.id.mLL_bottomView)
    LinearLayout mLLBottomView;
    @BindView(R.id.mLL_noDate)
    LinearLayout mLLNoDate;
    @BindView(R.id.mTxt_selectAll)
    TextView mTxtSelectAll;
    @BindView(R.id.mLL_status)
    LinearLayout mLLStatus;
    @BindView(R.id.mTxt_carTotalMoney)
    TextView mTxtCarTotalMoney;
    @BindView(R.id.mTxt_carBoxMoney)
    TextView mTxtCarBoxMoney;
    @BindView(R.id.mTxt_carPostMoney)
    TextView mTxtCarPostMoney;
    @BindView(R.id.mLL_carPost)
    LinearLayout mLLCarPost;

    private List<CartItemsBean> mDates = new ArrayList<>();
    private ShoppingCartAdapter adapter;
    private LinearLayoutManager manager;


    public static ShoppingCartFragment getInstance() {
        if (shoppingCartFragment == null)
            shoppingCartFragment = new ShoppingCartFragment();
        return shoppingCartFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shoppingcart;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
    }

    @Override
    public void loadData() {
        initStatus();
        initRv();
        mPresenter.getProducts();//获取购物车商品
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


    private void initStatus() {
        mLLStatus.getLayoutParams().height = StatusBarUtils.getStatusBarHeight(mContext);
    }

    private void initRv() {
        manager = new LinearLayoutManager(mContext);
        adapter = new ShoppingCartAdapter(mContext, mDates);
        mRvFirstList.setAdapter(adapter);
        mRvFirstList.setLayoutManager(manager);
        adapter.setOnItemClickListener(new ShoppingCartAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int parentPos) {//查看商品详情
                Intent intent = new Intent(mActivity, ProductDetailActivity.class);
                intent.putExtra("productId", mDates.get(parentPos).getProductInfo().getId());
                startActivity(intent);
            }

            @Override
            public void onSelectedClick(int pos, boolean isSelectAll) {//选中
                if (isSelectAll) {
                    mImgHuishou.setImageResource(R.mipmap.xuanzhong1);
                } else {
                    mImgHuishou.setImageResource(R.mipmap.xuanzhong2);
                }
            }

            @Override
            public void onJianClicked(int pos) {//数量减1

            }

            @Override
            public void onJiaClicked(int pos) {//添加 1

            }

            @Override
            public void onAddClicked(int pos) {//加入购物车

            }
        });
    }


    @OnClick({R.id.ll_img, R.id.mTxt_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_img://全选
                if (adapter.isSelectAll()) {
                    mImgHuishou.setImageResource(R.mipmap.xuanzhong2);
                    adapter.setSelectAll(false);
                } else {
                    mImgHuishou.setImageResource(R.mipmap.xuanzhong1);
                    adapter.setSelectAll(true);
                }


                break;
            case R.id.mTxt_pay://去支付
                List<PayParamsFatherBean> payList = adapter.getPayList();
                if (payList.size()==0){
                    showErrorMsg("您没有选择结算的商品","");
                }

                Intent intent = new Intent(mActivity,GoToPayActivity.class);
                intent.putExtra("payList",(Serializable) payList);
                startActivity(intent);
                break;
        }
    }

    //获取购物车商品
    @Override
    public void getProducts(ShoppingCarBean productBeans) {
        if (productBeans == null) {
            mLLNoDate.setVisibility(View.VISIBLE);
            mRvFirstList.setVisibility(View.GONE);
            return;
        }

        double boxAmount = productBeans.getBoxAmount();//框的押金
        double freeFreight = productBeans.getFreeFreight();//满多少减运费
        double freight = productBeans.getFreight();//不满200收20运费
        double minPrice = productBeans.getMinPrice();//minprice是订单最小金额，就是商品大于minprice才能下单，做批发的
        double productAmount = productBeans.getProductAmount();//productamount是商品金额

        mTxtCarTotalMoney.setText(productAmount + "");//合计
        mTxtCarBoxMoney.setText(boxAmount+"");

        mTxtCarPostMoney.setText(freight+"");


        List<CartItemsBean> cartItems = productBeans.getCartItems();
        if (cartItems == null || cartItems.size() == 0) {
            mLLNoDate.setVisibility(View.VISIBLE);
            mRvFirstList.setVisibility(View.GONE);
            return;
        }
        mRvFirstList.setVisibility(View.VISIBLE);
        mDates.clear();
        mDates.addAll(cartItems);
        adapter.notifyDataSetChanged();
    }

    //修改购物车数量
    @Override
    public void updateNum(boolean b) {

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            mPresenter.getProducts();//获取购物车商品
        }
    }
}
