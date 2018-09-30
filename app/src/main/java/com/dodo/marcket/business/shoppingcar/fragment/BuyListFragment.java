package com.dodo.marcket.business.shoppingcar.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseFragment;
import com.dodo.marcket.bean.ProductBean;
import com.dodo.marcket.bean.ProductParmsBean;
import com.dodo.marcket.bean.SpecificationsBean;
import com.dodo.marcket.business.clasify.adapter.ProductAdapter;
import com.dodo.marcket.business.homepage.activity.ProductDetailActivity;
import com.dodo.marcket.business.homepage.adapter.ProductDetailAdapter;
import com.dodo.marcket.business.shoppingcar.constrant.BuyListFragmentContract;
import com.dodo.marcket.business.shoppingcar.presenter.BuyListFragmentPresenter;
import com.dodo.marcket.utils.ToastUtils;
import com.dodo.marcket.utils.photo.PopupWindowHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


//展示商品列表页面
@SuppressLint("ValidFragment")
public class BuyListFragment extends BaseFragment<BuyListFragmentPresenter> implements BuyListFragmentContract.View {


    public static BuyListFragment buyListFragment;
    @BindView(R.id.mRv_buyList)
    RecyclerView mRvBuyList;
    @BindView(R.id.mLL_noDate)
    LinearLayout mLLNoDate;
    @BindView(R.id.mLL_mother)
    LinearLayout mLLMother;
    Unbinder unbinder;
    private LinearLayoutManager manager;
    private long mId;                       //一级分类ID
    private int page = 1;
    private int pageSize = 10;
    private List<ProductBean> mDates;
    private ProductAdapter adapter;
    private View popupLayout;
    private PopupWindow popupWindow;
    private View pickVIew;
    private List<SpecificationsBean> specificationsBeanList = new ArrayList<>();
    private ImageView mImg_productImg;
    private TextView mTxt_productName;
    private TextView mTxt_productMsg;
    private TextView mTxt_package;
    private TextView mTxt_price;
    private TextView mTxt_productPrice;
    private RecyclerView mRv_mutle;
    private ImageView mImg_jian;
    private ImageView mImg_jia;
    private TextView mTxt_num;
    private ProductDetailAdapter productDetailAdapter;

    public BuyListFragment(int id) {
        super();
        this.mId = id;
    }

    public static BuyListFragment getInstance() {
        if (buyListFragment == null)
            buyListFragment = new BuyListFragment(0);
        return buyListFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_buy_car;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
    }

    @Override
    public void loadData() {
        initRv();
        initPickView();
        mPresenter.getProductList(mId, page, pageSize);
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
        mDates = new ArrayList<>();
        manager = new LinearLayoutManager(mContext);
        adapter = new ProductAdapter(mContext, mDates);
        mRvBuyList.setLayoutManager(manager);
        mRvBuyList.setAdapter(adapter);

        adapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int parentPos) {//查看详情
                Intent intent = new Intent(mActivity, ProductDetailActivity.class);
                intent.putExtra("productId", mDates.get(parentPos).getId());
                startActivity(intent);
            }

            @Override
            public void onSelectedClick(int pos, boolean isSelectAll) {//选中

            }

            @Override
            public void onJianClicked(int pos) {//减号

            }

            @Override
            public void onJiaClicked(int pos) {//加号

            }

            @Override
            public void onAddClicked(int pos) {//单规格的加入购物车
                ProductBean productBean = mDates.get(pos);
                long id = productBean.getId();
                mPresenter.addProduct(1, new ProductParmsBean(id));
            }

            @Override
            public void onMutiSizeClicked(int pos) {//多规格的添加购物车
//                ToastUtils.show(mContext, "多规格");
                mPresenter.getProductDetailById(mDates.get(pos).getId());
            }
        });
    }

    //获取商品列表
    @Override
    public void getProductList(List<ProductBean> list) {
        if (list == null || list.size() == 0) {
            if (mDates.size() == 0) {
                mLLNoDate.setVisibility(View.VISIBLE);
                mRvBuyList.setVisibility(View.GONE);
            } else {
                mRvBuyList.setVisibility(View.VISIBLE);
                mLLNoDate.setVisibility(View.GONE);
            }

            return;
        }
        mDates.addAll(list);

        if (mDates.size() == 0) {
            mLLNoDate.setVisibility(View.VISIBLE);
            mRvBuyList.setVisibility(View.GONE);
        } else {
            mRvBuyList.setVisibility(View.VISIBLE);
            mLLNoDate.setVisibility(View.GONE);
        }

        adapter.notifyDataSetChanged();
    }

    //添加商品结果
    @Override
    public void addProduct(boolean isAdd) {
        if (isAdd) {
            ToastUtils.show(mContext, "加入购物车成功");
        } else {
            ToastUtils.show(mContext, "加入购物车失败");
        }
    }

    //获取商品详情（用于多重规格的商品的展示）
    @Override
    public void getProductDetailById(ProductBean productBean) {
        List<SpecificationsBean> specifications = productBean.getSpecifications();

        shoePOP();
    }


    public void shoePOP() {
        popupWindow.showAtLocation(mActivity.getWindow().getDecorView(),
                Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                PopupWindowHelper.backgroundAlpha(mActivity, 1f);
            }
        });
        PopupWindowHelper.backgroundAlpha(mActivity, 0.7f);
    }


    private void initPickView() {
        pickVIew = LayoutInflater.from(mActivity).inflate(R.layout.layout_popup_goods, null, false);

        View view_pop = pickVIew.findViewById(R.id.view_pop);
        //产品图片
        mImg_productImg = pickVIew.findViewById(R.id.mImg_productImg);
        //名称
        mTxt_productName = pickVIew.findViewById(R.id.mTxt_productName);
        //备注
        mTxt_productMsg = pickVIew.findViewById(R.id.mTxt_productMsg);
        //包装
        mTxt_package = pickVIew.findViewById(R.id.mTxt_package);
        //价格
        mTxt_price = pickVIew.findViewById(R.id.mTxt_price);
        //售价
        mTxt_productPrice = pickVIew.findViewById(R.id.mTxt_productPrice);
        //标签列表
        mRv_mutle = pickVIew.findViewById(R.id.mRv_mutle);
        //加号
        mImg_jian = pickVIew.findViewById(R.id.mImg_jian);
        //减号
        mImg_jia = pickVIew.findViewById(R.id.mImg_jia);
        //数量
        mTxt_num = pickVIew.findViewById(R.id.mTxt_num);

        popupWindow = PopupWindowHelper.createPopupWindow(pickVIew, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setAnimationStyle(R.style.slide_up_in_down_out);

        //点击空白地方
        view_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });


        //多选标签
        for (int i = 0; i < 5; i++) {
            specificationsBeanList.add(new SpecificationsBean());
        }
        productDetailAdapter = new ProductDetailAdapter(mContext, specificationsBeanList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mRv_mutle.setAdapter(productDetailAdapter);
        mRv_mutle.setLayoutManager(linearLayoutManager);


        //点击加号
        mImg_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //点击减号
        mImg_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}
