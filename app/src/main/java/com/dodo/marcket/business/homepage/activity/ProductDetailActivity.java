package com.dodo.marcket.business.homepage.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseActivity;
import com.dodo.marcket.base.CustomLinearLayoutManager;
import com.dodo.marcket.bean.ProductBean;
import com.dodo.marcket.bean.ProductParmsBean;
import com.dodo.marcket.bean.SpecificationsBean;
import com.dodo.marcket.business.HomeActivity;
import com.dodo.marcket.business.homepage.adapter.ProductDetailAdapter;
import com.dodo.marcket.business.homepage.constrant.ProductDetailContract;
import com.dodo.marcket.business.homepage.presenter.ProductDetailPresenter;
import com.dodo.marcket.http.constant.Constant;
import com.dodo.marcket.utils.GlideImageLoader;
import com.dodo.marcket.utils.MathUtils;
import com.dodo.marcket.utils.NumberUtils;
import com.dodo.marcket.utils.ScreenUtil;
import com.dodo.marcket.utils.SharedPreferencesUtil;
import com.dodo.marcket.utils.ToastUtils;
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
    @BindView(R.id.mTxt_unitPrice)
    TextView mTxtUnitPrice;

    @BindView(R.id.mTxt_carNum)
    TextView mTxtCarNum;
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
    EditText mTxtNum;
    @BindView(R.id.mImg_jia)
    ImageView mImgJia;
    @BindView(R.id.mLL_guige)
    LinearLayout mLLGuige;
    @BindView(R.id.mWebView)
    WebView mWebView;
    @BindView(R.id.mTxt_stock)
    TextView mTxtStock;
    @BindView(R.id.mTxt_package)
    TextView mTxtPackage;
    @BindView(R.id.mTxt_packageWe)
    TextView mTxtPackageWe;
    @BindView(R.id.ll_boxs)
    LinearLayout llBoxs;
    @BindView(R.id.mLL_noDate)
    LinearLayout mLLNoDate;
    private List<SpecificationsBean> specificationsList = new ArrayList<>();
    private ProductDetailAdapter adapter;
    private CustomLinearLayoutManager manager;
    private long mId;//传过来的值
    private long carId = -1;//最终加入购物车的值
    private int cartNumber = 1;
    private boolean isCanBuy = true;
    private int canBuyNumber;

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
        mTitle.setTitle("商品详情");
        Intent intent = getIntent();
        mId = intent.getLongExtra("productId", 0L);
        initBanner();
        initNumber();
        initRv();
        mTxtNum.setText(cartNumber + "");

        mPresenter.getProductDetailById(mId);//获取商品详情

        if (hastoken) {
            mPresenter.getCarNum();//获取购物车数量
        }
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

    @Override
    protected void onResume() {
        super.onResume();

        needToken = (String) SharedPreferencesUtil.get(mContext, Constant.token, "");
        if (needToken.equals("")) {
            hastoken = false;
        } else {
            hastoken = true;
        }

        if (hastoken) {
            mPresenter.getCarNum();//获取购物车数量
        }
    }


    //初始化输入数量
    private void initNumber() {

    }

    private void initRv() {

        CustomLinearLayoutManager manager = new CustomLinearLayoutManager(mContext);
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

    @OnClick({R.id.mImg_jian, R.id.mImg_jia, R.id.mTxt_pay, R.id.ll_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mImg_jia://点击jia号

                if (!hastoken) {
                    goToLogin();
                    return;
                }


                if (!isCanBuy) {
                    return;
                }

                String textNum = mTxtNum.getText().toString().trim();

                cartNumber = NumberUtils.string2Int(textNum);

                if (cartNumber < canBuyNumber) {
                    cartNumber++;
                    mTxtNum.setText(cartNumber + "");
                } else {
                    ToastUtils.show(mContext, "超出库存");
                }
                break;
            case R.id.mImg_jian://点击加号

                if (!hastoken) {
                    goToLogin();
                    return;
                }


                if (!isCanBuy) {
                    return;
                }

                String textNum2 = mTxtNum.getText().toString().trim();

                cartNumber = NumberUtils.string2Int(textNum2);

                if (cartNumber <= 1) {
                    return;
                }
                cartNumber--;
                mTxtNum.setText(cartNumber + "");
                break;
            case R.id.mTxt_pay://点击加入购物车

                if (!hastoken) {
                    goToLogin();
                    return;
                }


                if (!isCanBuy) {
                    return;
                }
                String textNum3 = mTxtNum.getText().toString().trim();

                cartNumber = NumberUtils.string2Int(textNum3);
                if (cartNumber<=0){
                    ToastUtils.show(mContext,"数量不能小于0");
                    return;
                }
                mPresenter.updateNum(cartNumber, new ProductParmsBean(mId), 1);
                break;

            case R.id.ll_img://点击购物车图标

                if (!hastoken) {
                    goToLogin();
                    return;
                }

                Bundle bundle = new Bundle();
                bundle.putInt("fromWhere", 1);
                startActivity(HomeActivity.class, bundle);
                break;

        }
    }

    //获取商品详情
    @Override
    public void getProductDetailById(ProductBean productBean) {
        if (productBean == null) {
            mLLNoDate.setVisibility(View.VISIBLE);
            return;
        }

        if (productBean.getPrice() == 0d || productBean.getName() == null || productBean.getName().equals("")) {
            mLLNoDate.setVisibility(View.VISIBLE);
            return;
        }
        mLLNoDate.setVisibility(View.GONE);
        isCanBuy = false;
        if (productBean.getStock() == null) {//不限制库存
            isCanBuy = true;
            mTxtStock.setText("库存：9999");
            canBuyNumber = 9999;
        } else if (productBean.getStock() == 0) {
            isCanBuy = false;
            canBuyNumber = 0;
            mTxtStock.setText("库存：" + productBean.getStock());
        } else if (productBean.getStock() > 0) {
            isCanBuy = true;
            canBuyNumber = productBean.getStock();
            mTxtStock.setText("库存：" + productBean.getStock());
        }

        //是否可以购买
        if (isCanBuy) {
            mTxtPay.setClickable(true);
            mTxtPay.setBackgroundResource(R.color.basicColor);
        } else {
            mTxtPay.setClickable(false);
            mTxtPay.setBackgroundResource(R.color.defalute);
        }

        List<SpecificationsBean> specifications = productBean.getSpecifications();//商品规格
        List<String> productImages = productBean.getProductImages();//banner图片
        String name = productBean.getName();//商品名称
        String introduction = productBean.getIntroduction();//商品描述
        String memo = productBean.getMemo();//备注
        double price = productBean.getPrice();//价格
        String unit = productBean.getUnit();//单位
        cartNumber = 1;//购物车此种商品
        String image = productBean.getImage();
        long id = productBean.getId();
        mId = id;
        if (productImages == null || productImages.size() == 0) {
            if (!image.equals("")) {
                productImages.add(image);
                mBanner.setImages(productImages);
                mBanner.start();
            }
        } else {
            mBanner.setImages(productImages);
            mBanner.start();
        }

        String packaging = productBean.getPackaging();
        if (packaging.equals("")) {
            mTxtPackage.setVisibility(View.GONE);
        } else {
            mTxtPackage.setText(packaging);
            mTxtPackage.setVisibility(View.VISIBLE);
        }

        mTxtPackageWe.setText("约 " + productBean.getWeight() + " " + productBean.getUnit());

        mTxtNum.setText(cartNumber + "");
        mTxtProductPrice.setText(price + "");
        mTxtProductName.setText(name);
        mTxtProductMsg.setText(memo);
        mTxtUnitPrice.setText("¥ " + MathUtils.round(productBean.getUnitPrice(),2) + "/" + unit);
        if (TextUtils.equals(introduction, "")) {
            mTxtDesc.setText("暂无商品描述");
        } else {
            mTxtDesc.setVisibility(View.GONE);
        }


        mWebView.setWebChromeClient(webChromeClient);
        mWebView.setWebViewClient(webViewClient);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);//允许使用js


        /**
         * LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
         * LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
         * LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
         * LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
         */
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);//不使用缓存，只从网络获取数据.
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        //支持屏幕缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        int screenWidth = ScreenUtil.getScreenWidth(mContext);
        introduction = introduction.replace("<img", "<img style=\"width:100%\"");
//        introduction="<img style=\"width:100%\" src = \"http://cdn.duoduofresh.com/upload/image/201810/8072dad4-23ad-4078-b653-d17495618d3c.gif\" alt = \"\" >\n" +
//                "<img style=\"width:100%\" src = \"http://cdn.duoduofresh.com/upload/image/201810/776ecd13-5ed2-410c-bd33-6999d9a8c7c2.jpg\" alt = \"\">\n" +
//                "<img style=\"width:100%\" src = \"http://cdn.duoduofresh.com/upload/image/201810/1a63dceb-4f82-4733-86b0-c02097d9e39b.jpg\"\n" +
//                "alt = \"\" >";
//        mWebView.loadData(introduction, "text/html", "UTF-8");
        mWebView.loadDataWithBaseURL(null, introduction, "text/html", "utf-8", null);
        if (specifications == null || specifications.size() == 0) {
//            carId = mId;
            mRvSize.setVisibility(View.GONE);
        } else {
            mRvSize.setVisibility(View.VISIBLE);
            specificationsList.clear();
            specificationsList.addAll(specifications);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getProductBySize(ProductBean productBean) {

    }

    //加入购物车
    @Override
    public void updateNum(int qty, boolean b, int pos) {
        if (b) {
            ToastUtils.show(mContext, "加入购物车成功");
            canBuyNumber = canBuyNumber - cartNumber;
//            mTxtStock.setText(canBuyNumber);
            mPresenter.getProductDetailById(mId);//获取商品详情
            mPresenter.getCarNum();
        } else {
            ToastUtils.show(mContext, "加入购物车失败");
        }
    }

    //获取购物车数量
    @Override
    public void getCarNum(int num) {
        if (num <= 0) {
            mTxtCarNum.setVisibility(View.GONE);
        } else if (num > 99) {
            mTxtCarNum.setVisibility(View.VISIBLE);
            mTxtCarNum.setText("99+");
        } else {
            mTxtCarNum.setVisibility(View.VISIBLE);
            mTxtCarNum.setText(num + "");
        }
    }


    //WebChromeClient主要辅助WebView处理Javascript的对话框、网站图标、网站title、加载进度等
    private WebChromeClient webChromeClient = new WebChromeClient() {
        //不支持js的alert弹窗，需要自己监听然后通过dialog弹窗
        @Override
        public boolean onJsAlert(WebView webView, String url, String message, JsResult result) {
            AlertDialog.Builder localBuilder = new AlertDialog.Builder(webView.getContext());
            localBuilder.setMessage(message).setPositiveButton("确定", null);
            localBuilder.setCancelable(false);
            localBuilder.create().show();

            //注意:
            //必须要这一句代码:result.confirm()表示:
            //处理结果为确定状态同时唤醒WebCore线程
            //否则不能继续点击按钮
            result.confirm();
            return true;
        }

        //获取网页标题
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            Log.i("ansen", "网页标题:" + title);
        }

        //加载进度回调
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
//            progressBar.setProgress(newProgress);
        }
    };


    //WebViewClient主要帮助WebView处理各种通知、请求事件
    private WebViewClient webViewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {//页面加载完成
//            progressBar.setVisibility(View.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {//页面开始加载
//            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.i("ansen", "拦截url:" + url);
            if (url.equals("http://www.google.com/")) {
                return true;//表示我已经处理过了
            }
            return super.shouldOverrideUrlLoading(view, url);
        }

    };


    @Override
    protected void onDestroy() {
        super.onDestroy();

        //释放资源
        mWebView.destroy();
        mWebView = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
