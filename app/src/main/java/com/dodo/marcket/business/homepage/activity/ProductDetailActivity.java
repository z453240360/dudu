package com.dodo.marcket.business.homepage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseActivity;
import com.dodo.marcket.base.CustomLinearLayoutManager;
import com.dodo.marcket.bean.ProductBean;
import com.dodo.marcket.bean.SpecificationsBean;
import com.dodo.marcket.business.homepage.adapter.ProductDetailAdapter;
import com.dodo.marcket.business.homepage.constrant.ProductDetailContract;
import com.dodo.marcket.business.homepage.presenter.ProductDetailPresenter;
import com.dodo.marcket.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductDetailActivity extends BaseActivity<ProductDetailPresenter> implements ProductDetailContract.View {


    @BindView(R.id.mBanner)
    Banner mBanner;
    @BindView(R.id.mTxt_productName)
    TextView mTxtProductName;
    @BindView(R.id.mTxt_productMsg)
    TextView mTxtProductMsg;
    @BindView(R.id.mTxt_productPriceName)
    TextView mTxtProductPriceName;
    @BindView(R.id.mTxt_productPrice)
    TextView mTxtProductPrice;
    @BindView(R.id.mTxt_desc)
    TextView mTxtDesc;
    @BindView(R.id.mLL_desc)
    LinearLayout mLLDesc;
    @BindView(R.id.mRv_size)
    RecyclerView mRvSize;
    @BindView(R.id.mImg_huishou)
    ImageView mImgHuishou;
    @BindView(R.id.ll_img)
    LinearLayout llImg;
    @BindView(R.id.mTxt_pay)
    TextView mTxtPay;
    @BindView(R.id.mLL_bottomView)
    LinearLayout mLLBottomView;
    @BindView(R.id.mImg_jian)
    ImageView mImgJian;
    @BindView(R.id.mTxt_num)
    TextView mTxtNum;
    @BindView(R.id.mImg_jia)
    ImageView mImgJia;
    @BindView(R.id.mLL_guige)
    LinearLayout mLLGuige;
    private List<SpecificationsBean> specificationsList = new ArrayList<>();
    private ProductDetailAdapter adapter;
    private CustomLinearLayoutManager manager;
    private long mId;//传过来的值
    private long carId = -1;//最终加入购物车的值

    @Override
    public int getLayoutId() {
        return R.layout.activity_product_detail;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
    }

    @Override
    public void loadData() {
        Intent intent = getIntent();
        mId = intent.getLongExtra("productId", 0L);
        initBanner();
        initRv();
        mPresenter.getProductDetailById(mId);
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

    private void initRv() {

        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(mContext);
//        manager.setScrollEnabled(false);
        adapter = new ProductDetailAdapter(mContext, specificationsList);
        mRvSize.setAdapter(adapter);
        mRvSize.setLayoutManager(manager);
        adapter.setOnItemClickListener(new ProductDetailAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int parentPos, String specParam) {
                mPresenter.getProductBySize(mId, specParam);
            }

        });

    }

    //首页轮播
    private void initBanner() {
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.setBannerAnimation(Transformer.DepthPage);
        mBanner.isAutoPlay(true);
        mBanner.setDelayTime(5000);
    }

    //获取商品详情
    @Override
    public void getProductDetailById(ProductBean productBean) {
        if (productBean == null) {
            return;
        }

        List<SpecificationsBean> specifications = productBean.getSpecifications();//商品规格
        List<String> productImages = productBean.getProductImages();//banner图片
        String name = productBean.getName();//商品名称
        String introduction = productBean.getIntroduction();//商品描述
        String memo = productBean.getMemo();//备注
        double price = productBean.getPrice();//价格
        String unit = productBean.getUnit();//单位
        long id = productBean.getId();
        mId = id;
        if (productImages == null || productImages.size() == 0) {

        } else {
            mBanner.setImages(productImages);
            mBanner.start();
        }

        mTxtProductPrice.setText(price+"");
        mTxtProductName.setText(name);
        mTxtProductMsg.setText(memo);

        if (TextUtils.equals(introduction, "")) {
            mTxtDesc.setText("暂无商品描述");
        } else {
            mTxtDesc.setText(introduction);
        }


        if (specifications == null || specifications.size() == 0) {
//            carId = mId;
        } else {
            specificationsList.clear();
            specificationsList.addAll(specifications);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getProductBySize(ProductBean productBean) {

    }


    @OnClick({R.id.mImg_jian, R.id.mImg_jia})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mImg_jian:
                break;
            case R.id.mImg_jia:
                break;
        }
    }
}
