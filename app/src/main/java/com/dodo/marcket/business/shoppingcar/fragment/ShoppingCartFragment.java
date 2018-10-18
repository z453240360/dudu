package com.dodo.marcket.business.shoppingcar.fragment;


import android.content.Intent;
import android.graphics.Paint;
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
import com.dodo.marcket.bean.GoToPayBean;
import com.dodo.marcket.bean.ProductParmsBean;
import com.dodo.marcket.bean.ShoppingCarBean;
import com.dodo.marcket.bean.params.PayParamsBean;
import com.dodo.marcket.bean.params.PayParamsFatherBean;
import com.dodo.marcket.business.HomeActivity;
import com.dodo.marcket.business.homepage.activity.ProductDetailActivity;
import com.dodo.marcket.business.shoppingcar.activity.GoToPayActivity;
import com.dodo.marcket.business.shoppingcar.adapter.ShoppingCartAdapter;
import com.dodo.marcket.business.shoppingcar.constrant.ShoppingCartFragmentContract;
import com.dodo.marcket.business.shoppingcar.presenter.ShoppingCartFragmentPresenter;
import com.dodo.marcket.utils.ToastUtils;
import com.dodo.marcket.utils.statusbar.StatusBarUtils;

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
    @BindView(R.id.mLL_showPrice)
    LinearLayout mLLShowPrice;
    @BindView(R.id.mTxt_carClear)
    TextView mTxtCarClear;
    @BindView(R.id.mTxt_carDelete)
    TextView mTxtCarDelete;
    @BindView(R.id.mLL_showEdit)
    LinearLayout mLLShowEdit;
    @BindView(R.id.mTxt_carEdit)
    TextView mTxtCarEdit;
    @BindView(R.id.mTxt_sendPrice)
    TextView mTxtSendPrice;
    @BindView(R.id.mLL_sendPrice)
    LinearLayout mLLSendPrice;
    @BindView(R.id.mTxt_afterFinalMony)
    TextView mTxtAfterFinalMony;
    @BindView(R.id.mLL_realPrice)
    LinearLayout mLLRealPrice;
    Unbinder unbinder;

    private List<CartItemsBean> mDates = new ArrayList<>();
    private ShoppingCartAdapter adapter;
    private LinearLayoutManager manager;
    private HomeActivity homeActivity;
    private boolean isShowPay = true;
    private double minPrice = 0;
    private double freeFreight;

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
        homeActivity = (HomeActivity) mActivity;
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
        showErrorToast(msg);
    }


    private void initStatus() {
        mLLStatus.getLayoutParams().height = StatusBarUtils.getStatusBarHeight(mContext);
    }

    private void initBottomView() {
        isShowPay = false;
        if (isShowPay) {
            mLLShowPrice.setVisibility(View.GONE);
            mLLShowEdit.setVisibility(View.VISIBLE);
            isShowPay = false;
            mTxtCarEdit.setText("完成");
        } else {
            mLLShowPrice.setVisibility(View.VISIBLE);
            mLLShowEdit.setVisibility(View.GONE);
            isShowPay = true;
            mTxtCarEdit.setText("编辑");
        }
    }

    //初始化列表
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
                initBottomPrice();

            }

            @Override
            public void onJianClicked(int pos) {//数量减1
                ProductParmsBean productParmsBean = new ProductParmsBean(mDates.get(pos).getProductInfo().getId());
                mPresenter.updateNum(-1, productParmsBean, pos);
            }

            @Override
            public void onJiaClicked(int pos) {//添加 1
                int quantity = mDates.get(pos).getQuantity();

                ProductParmsBean productParmsBean = new ProductParmsBean(mDates.get(pos).getProductInfo().getId());
                mPresenter.updateNum(1, productParmsBean, pos);
            }

            @Override
            public void onAddClicked(int pos) {//加入购物车

            }
        });
    }

    //点击事件
    @OnClick({R.id.ll_img, R.id.mTxt_pay, R.id.mTxt_carEdit, R.id.mTxt_carClear, R.id.mTxt_carDelete})
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

                initBottomPrice();
                break;

            case R.id.mTxt_pay://去支付
                List<PayParamsFatherBean> payList = adapter.getPayList();
                if (payList.size() == 0) {
                    showErrorMsg("您没有选择结算的商品", "");
                }

                Intent intent = new Intent(mActivity, GoToPayActivity.class);
                intent.putExtra("payList", (Serializable) payList);
                startActivity(intent);
                break;

            case R.id.mTxt_carEdit://点击编辑按钮

                if (isShowPay) {
                    mLLShowPrice.setVisibility(View.GONE);
                    mLLShowEdit.setVisibility(View.VISIBLE);
                    isShowPay = false;
                    mTxtCarEdit.setText("完成");
                } else {
                    mLLShowPrice.setVisibility(View.VISIBLE);
                    mLLShowEdit.setVisibility(View.GONE);
                    isShowPay = true;
                    mTxtCarEdit.setText("编辑");
                }

                break;

            case R.id.mTxt_carClear://清除购物车

                mPresenter.clearShoppingCar();

                break;

            case R.id.mTxt_carDelete://删除选中商品

                List<PayParamsFatherBean> mList = adapter.getPayList();

                if (mList.size() == 0) {
                    showErrorMsg("您没有选择商品", "");
                }

                for (int i = 0; i < mList.size(); i++) {
                    PayParamsBean productParam = mList.get(i).getProductParam();
                    long categoryId = productParam.getCategoryId();
                    long id = productParam.getId();
                    ProductParmsBean productParmsBean = new ProductParmsBean(id, categoryId);
                    mPresenter.deleteProduct(mList.get(i).getQuantity(), productParmsBean);
                }

                break;
        }
    }

    //获取购物车商品
    @Override
    public void getProducts(ShoppingCarBean productBeans) {
        if (productBeans == null) {
            mLLNoDate.setVisibility(View.VISIBLE);
            mRvFirstList.setVisibility(View.GONE);
            mLLBottomView.setVisibility(View.GONE);
            mTxtCarEdit.setVisibility(View.GONE);
            return;
        }
//        mLLBottomView.setVisibility(View.VISIBLE);
        mLLNoDate.setVisibility(View.GONE);
        mRvFirstList.setVisibility(View.VISIBLE);
        mTxtCarEdit.setVisibility(View.GONE);

        double boxAmount = productBeans.getBoxAmount();//框的押金
        //满多少减运费
        freeFreight = productBeans.getFreeFreight();
        double freight = productBeans.getFreight();//不满200收20运费
        //minprice是订单最小金额，就是商品大于minprice才能下单，做批发的
        minPrice = productBeans.getMinPrice();
        double productAmount = productBeans.getProductAmount();//productamount是商品金额

//        mTxtSendPrice.setText("还差"+minPrice+"元起送");
//        mTxtSendPrice.setText("满"+freeFreight+"免运费");
//        mTxtCarTotalMoney.setText(productAmount + "");//合计
//        mTxtCarBoxMoney.setText(boxAmount + "");
//        mTxtCarPostMoney.setText(freight + "");

        List<CartItemsBean> cartItems = productBeans.getCartItems();
        if (cartItems == null || cartItems.size() == 0) {
            mLLNoDate.setVisibility(View.VISIBLE);
            mRvFirstList.setVisibility(View.GONE);
            mLLBottomView.setVisibility(View.GONE);
            mTxtCarEdit.setVisibility(View.INVISIBLE);
            return;
        }
        mTxtCarEdit.setVisibility(View.VISIBLE);
//        mLLBottomView.setVisibility(View.VISIBLE);
        mRvFirstList.setVisibility(View.VISIBLE);
        mDates.clear();
        mDates.addAll(cartItems);
        adapter.notifyDataSetChanged();

        initBottomPrice();
    }

    //计算购物车界面底部价格
    private void initBottomPrice() {
        List<PayParamsFatherBean> payList = adapter.getPayList();
        if (payList.size() == 0) {
//            showErrorMsg("您没有选择结算的商品", "");
            mTxtCarTotalMoney.setText("" + 0);
            mTxtCarBoxMoney.setText("" + 0);
            mTxtHuishou.setBackgroundResource(R.color.defalute);
            mTxtHuishou.setClickable(false);
        } else {
            mPresenter.payProducts(payList);
        }
    }

    //修改购物车数量
    @Override
    public void updateNum(int qty, boolean b, int pos) {
        if (b) {

            int quantity = mDates.get(pos).getQuantity();
            mDates.get(pos).setQuantity((quantity + qty));
            if ((quantity + qty) <= 0) {
                mDates.remove(pos);
                homeActivity.updateCarNum();
            }

            if (mDates.size() == 0) {
                mLLNoDate.setVisibility(View.VISIBLE);
                mRvFirstList.setVisibility(View.GONE);
            } else {
                mLLNoDate.setVisibility(View.GONE);
                mRvFirstList.setVisibility(View.VISIBLE);
            }
            adapter.notifyDataSetChanged();
            initBottomPrice();//重新计算价格
        } else {
            ToastUtils.show(mContext, "失败");
        }


    }

    //删除商品
    @Override
    public void deleteProduct(boolean b) {
        mPresenter.getProducts();
        homeActivity.updateCarNum();
        initBottomPrice();//重新计算价格
        if (mDates.size() == 0) {
            initBottomView();
        }
    }

    //清空购物车
    @Override
    public void clearShoppingCar(boolean b) {
        mPresenter.getProducts();
        homeActivity.updateCarNum();
        initBottomPrice();//重新计算价格
        initBottomView();//底部隐藏
    }

    //获取结算价格
    @Override
    public void getPayMsg(GoToPayBean payBean) {

        if (payBean == null) {
            mLLBottomView.setVisibility(View.GONE);
            return;
        }

        mLLBottomView.setVisibility(View.VISIBLE);

        double boxAmount = payBean.getBoxAmount();//筐的金额
        double productAmount = payBean.getProductAmount();//总价格
        double freight = payBean.getFreight();//运费
        double afterDiscountAmount = payBean.getAfterDiscountAmount();
        if (minPrice >= productAmount) {//低于购买价格，不允许购买
            mTxtHuishou.setBackgroundResource(R.color.defalute);
            mTxtHuishou.setClickable(false);
        } else {
            mTxtHuishou.setBackgroundResource(R.color.basicColor);
            mTxtHuishou.setClickable(true);
        }
        mTxtSendPrice.setText("满" + minPrice + "起送,满"+ freeFreight +"免运费");

        if (afterDiscountAmount==productAmount){
            mLLRealPrice.setVisibility(View.GONE);
        }else {
            mLLRealPrice.setVisibility(View.VISIBLE);
            mTxtAfterFinalMony.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); // 设置中划线并加清晰
            mTxtAfterFinalMony.setText("" +productAmount);
        }

        mTxtCarTotalMoney.setText("" + afterDiscountAmount);
        mTxtCarBoxMoney.setText("" + boxAmount);
        if ((freight - 0) <= 0) {
            mLLCarPost.setVisibility(View.GONE);
        } else {
            mLLCarPost.setVisibility(View.VISIBLE);
            mTxtCarPostMoney.setText("" + freight);
        }
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            mPresenter.getProducts();//获取购物车商品
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (hastoken) {
            mPresenter.getProducts();//获取购物车商品
        }
    }

}
