package com.dodo.marcket.business.homepage.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseActivity;
import com.dodo.marcket.bean.HotBean;
import com.dodo.marcket.bean.ProductBean;
import com.dodo.marcket.bean.ProductParmsBean;
import com.dodo.marcket.business.clasify.adapter.ProductAdapter;
import com.dodo.marcket.business.homepage.constrant.HotContract;
import com.dodo.marcket.business.homepage.presenter.HotPresenter;
import com.dodo.marcket.utils.ImageLoaders;
import com.dodo.marcket.utils.ScreenUtil;
import com.dodo.marcket.utils.ToastUtils;
import com.dodo.marcket.wedget.MyWebView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotActivity extends BaseActivity<HotPresenter> implements HotContract.View {

    @BindView(R.id.mImg_hot)
    ImageView mImgHot;
    @BindView(R.id.mRv_hot)
    RecyclerView mRvHot;
    @BindView(R.id.mLL_noDate)
    LinearLayout mLLNoDate;
    @BindView(R.id.mWeb)
    MyWebView mWeb;
    private LinearLayoutManager manager;
    private ProductAdapter adapter;
    private List<ProductBean> mDates = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_hot;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
    }

    @Override
    public void loadData() {
        mTitle.setTitle("热销商品活动");
        initRv();
        long mId = getIntent().getLongExtra("mId", 0L);
        mPresenter.getPromotionDetail(mId);//热销商品活动
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
        mDates = new ArrayList<>();
        manager = new LinearLayoutManager(mContext);
        adapter = new ProductAdapter(mContext, mDates);
        mRvHot.setLayoutManager(manager);
        mRvHot.setAdapter(adapter);
        adapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int parentPos) {
                Intent intent = new Intent(mActivity, ProductDetailActivity.class);
                intent.putExtra("productId", mDates.get(parentPos).getId());
                startActivity(intent);
            }

            @Override
            public void onSelectedClick(int pos, boolean isSelectAll) {

            }

            @Override
            public void onJianClicked(int pos) {

            }

            @Override
            public void onJiaClicked(int pos) {

            }

            @Override
            public void onAddClicked(int pos) {
                if (!hastoken) {
                    goToLogin();
                    return;
                }


                ProductBean productBean = mDates.get(pos);
                long id = productBean.getId();
                mPresenter.addProduct(1, new ProductParmsBean(id));
            }

            @Override
            public void onMutiSizeClicked(int pos) {

                if (!hastoken) {
                    goToLogin();
                    return;
                }

                mPresenter.getProductDetailById(mDates.get(pos).getId());
            }
        });
    }

    //获取商品列表
    @Override
    public void getProduct(HotBean hotBean) {

        if (hotBean == null) {
            mImgHot.setVisibility(View.GONE);
            mLLNoDate.setVisibility(View.VISIBLE);
            return;
        } else {
            mLLNoDate.setVisibility(View.GONE);
        }

        String introduction = hotBean.getIntroduction();
        if (!introduction.equals("")){

            introduction = introduction.replace("<img", "<img style=\"width:100%\"");
//            mWeb.loadData(introduction, "text/html", "UTF-8");
            mWeb.loadDataWithBaseURL(null, introduction, "text/html", "utf-8", null);
        }


        if (hotBean.getSize().equals("small")) {
            if (hotBean.getSmallMobileImage().equals("")) {
                mImgHot.setVisibility(View.GONE);

            } else {
                ImageLoaders.displayConnerImg(mImgHot, hotBean.getSmallMobileImage(), ScreenUtil.dip2px(mContext, 5));
                mImgHot.setVisibility(View.GONE);
            }
        } else {
            if (hotBean.getSmallMobileImage().equals("")) {
                mImgHot.setVisibility(View.GONE);

            } else {
                ImageLoaders.displayConnerImg(mImgHot, hotBean.getMobileImage(), ScreenUtil.dip2px(mContext, 5));
                mImgHot.setVisibility(View.GONE);
            }

        }

        List<ProductBean> productList = hotBean.getProductInfoList();
        if (productList == null || productList.size() == 0) {
            mLLNoDate.setVisibility(View.VISIBLE);
            return;
        } else {
            mLLNoDate.setVisibility(View.GONE);
        }

        mDates.clear();
        mDates.addAll(productList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void addProduct(boolean isAdd) {
        if (isAdd) {
            ToastUtils.show(mContext, "加入购物车成功");
        } else {
            ToastUtils.show(mContext, "加入购物车失败");
        }
    }

    @Override
    public void getProductDetailById(ProductBean productBean) {

    }
}
