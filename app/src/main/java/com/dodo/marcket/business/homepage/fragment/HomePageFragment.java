package com.dodo.marcket.business.homepage.fragment;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseFragment;
import com.dodo.marcket.bean.BannerBean;
import com.dodo.marcket.bean.HomePageActivityBean;
import com.dodo.marcket.bean.MiaoShaBean;
import com.dodo.marcket.bean.ProducHeadBean;
import com.dodo.marcket.bean.ProductBean;
import com.dodo.marcket.bean.ProductParmsBean;
import com.dodo.marcket.bean.SpecificationsBean;
import com.dodo.marcket.business.HomeActivity;
import com.dodo.marcket.business.clasify.activity.ClasifyActivity;
import com.dodo.marcket.business.clasify.activity.SearchActivity;
import com.dodo.marcket.business.homepage.activity.HotActivity;
import com.dodo.marcket.business.homepage.activity.ProductDetailActivity;
import com.dodo.marcket.business.homepage.activity.WebActivity;
import com.dodo.marcket.business.homepage.adapter.HomeHotAdapter;
import com.dodo.marcket.business.homepage.adapter.HomeMiaoShaAdapter;
import com.dodo.marcket.business.homepage.adapter.ProductDetailAdapter;
import com.dodo.marcket.business.homepage.constrant.HomePageContract;
import com.dodo.marcket.business.homepage.presenter.HomePagePresenter;
import com.dodo.marcket.business.mine.activity.LoginActivity;
import com.dodo.marcket.http.constant.Constant;
import com.dodo.marcket.iCallback.MiaoShaCountdownListener;
import com.dodo.marcket.utils.BankCountDown;
import com.dodo.marcket.utils.DateUtil;
import com.dodo.marcket.utils.GlideImageLoader;
import com.dodo.marcket.utils.ImageLoaders;
import com.dodo.marcket.utils.NumberUtils;
import com.dodo.marcket.utils.ScreenUtil;
import com.dodo.marcket.utils.SharedPreferencesUtil;
import com.dodo.marcket.utils.ToastUtils;
import com.dodo.marcket.utils.photo.PopupWindowHelper;
import com.dodo.marcket.utils.statusbar.StatusBarUtils;
import com.dodo.marcket.wedget.YhFlowLayout;
import com.dodo.marcket.wedget.dialog.AdDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class HomePageFragment extends BaseFragment<HomePagePresenter> implements HomePageContract.View {

    @BindView(R.id.mBanner)
    Banner mBanner;
    @BindView(R.id.mLL_yh)
    LinearLayout mLLYh;
    @BindView(R.id.mRv_main)
    RecyclerView mRvMain;
    @BindView(R.id.mYH_img)
    YhFlowLayout mYHImg;
    Unbinder unbinder;
    @BindView(R.id.mLL_hotGoods)
    LinearLayout mLLHotGoods;
    public static HomePageFragment homePageFragment;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.mTxt_hour)
    TextView mTxtHour;
    @BindView(R.id.mTxt_min)
    TextView mTxtMin;
    @BindView(R.id.mTxt_second)
    TextView mTxtSecond;
    @BindView(R.id.mTxt_day)
    TextView mTxtDay;
    @BindView(R.id.mRv_miaoshao)
    RecyclerView mRvMiaoshao;
    @BindView(R.id.mLL_miaoshao)
    LinearLayout mLLMiaoshao;
    Unbinder unbinder1;
    @BindView(R.id.mLL_status)
    LinearLayout mLLStatus;
    @BindView(R.id.mEd_search)
    TextView mEdSearch;
    @BindView(R.id.mLL_search)
    LinearLayout mLLSearch;
    private LinearLayoutManager manager;
    private GridLayoutManager miaoShaManager;
    private HomeHotAdapter adapter;
    private HomeMiaoShaAdapter miaoShaAdapter;


    private List<ProductBean> mDates;
    private View pickVIew;
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
    private PopupWindow popupWindow;
    private HomeActivity homeActivity;
    private List<SpecificationsBean> specificationsBeanList = new ArrayList<>();
    private List<ProducHeadBean> mDates2 = new ArrayList<>();
    private List<MiaoShaBean.ProductInfoListBean> miaoShaBeanList = new ArrayList<>();
    private ProductDetailAdapter productDetailAdapter;
    private Button mBtn_select;
    private boolean isCanBuy;
    private boolean isMiaoShaFinished = false;
    private long buyId;
    private BankCountDown bankCountDown;

    public static HomePageFragment getInstance() {
        if (homePageFragment == null)
            homePageFragment = new HomePageFragment();
        return homePageFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
    }

    @Override
    public void loadData() {
//        mLLStatus.getLayoutParams().height = StatusBarUtils.getStatusBarHeight(mContext);
        homeActivity = (HomeActivity) mActivity;
        initBanner();
        initRv();
        initPickView();
        initMiaoShaRv();
        initRefresh();
        mPresenter.getAllPromotion();//首页获取活动模块信息
        mPresenter.getBanner(2);//首页banner
        mPresenter.getHotProduct();//获取热销商品信息
        mPresenter.getAdPosition(1);//弹窗广告

        mPresenter.getMiaoShaDate();//获取秒杀数据信息
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
        if (refreshLayout.isRefreshing()) {
            refreshLayout.finishRefresh(false);//结束刷新（刷新失败）
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        needToken = (String) SharedPreferencesUtil.get(mContext, Constant.token, "");
        if (needToken.equals("")) {
            hastoken = false;
        } else {
            hastoken = true;
        }
    }

    //首页轮播
    private void initBanner() {
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.setBannerAnimation(Transformer.DepthPage);
        mBanner.isAutoPlay(true);
        mBanner.setDelayTime(5000);

    }

    //下拉刷新
    private void initRefresh() {

        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setEnableFooterTranslationContent(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.getBanner(2);//首页banner
                mPresenter.getMiaoShaDate();//获取秒杀数据信息
//                mPresenter.getAdPosition(1);//弹窗广告
                mPresenter.getAllPromotion();//首页获取活动模块信息
                mPresenter.getHotProduct();//获取热销商品信息

            }
        });
    }


    //首页热销商品
    private void initRv() {
        mDates = new ArrayList<>();
        manager = new LinearLayoutManager(mContext);
        adapter = new HomeHotAdapter(mContext, mDates2);
        mRvMain.setLayoutManager(manager);
        mRvMain.setAdapter(adapter);
        mRvMain.setHasFixedSize(true);
        mRvMain.setNestedScrollingEnabled(false);
        adapter.setOnItemClickListener(new HomeHotAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int parentPos, int childPos) {
                Intent intent = new Intent(mActivity, ProductDetailActivity.class);
                intent.putExtra("productId", mDates2.get(parentPos).getProducts().get(childPos).getId());
                startActivity(intent);
            }

            @Override
            public void onSelectedClick(int parentPos, int childPos, boolean isSelectAll) {

            }

            @Override
            public void onJianClicked(int parentPos, int childPos) {

            }

            @Override
            public void onJiaClicked(int parentPos, int childPos) {

            }

            @Override
            public void onAddClicked(int parentPos, int childPos) {
                if (!hastoken) {
                    goToLogin();
                    return;
                }

                ProductBean productBean = mDates2.get(parentPos).getProducts().get(childPos);
                long id = productBean.getId();
                mPresenter.addProduct(1, new ProductParmsBean(id));
            }

            @Override
            public void onMutiSizeClicked(int parentPos, int childPos) {
//                shoePOP();
                mPresenter.getProductDetailById(mDates2.get(parentPos).getProducts().get(childPos).getId());
            }
        });
    }

    /**
     * 秒杀活动初始化
     */
    private void initMiaoShaRv() {
        miaoShaAdapter = new HomeMiaoShaAdapter(mContext, miaoShaBeanList);
        miaoShaManager = new GridLayoutManager(mContext, 3);
        mRvMiaoshao.setLayoutManager(miaoShaManager);
        mRvMiaoshao.setAdapter(miaoShaAdapter);

        miaoShaAdapter.setOnItemClickListener(new HomeMiaoShaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, MiaoShaBean.ProductInfoListBean productInfoListBean) {

                if (!isMiaoShaFinished){
                    showErrorToast("此活动结束");
                    return;
                }
                long id = productInfoListBean.getId();
//                mPresenter.addProduct(1, new ProductParmsBean(id));
                Intent intent = new Intent(mActivity, ProductDetailActivity.class);
                intent.putExtra("productId", id);
                startActivity(intent);
            }
        });
    }

    //热销列表
    @Override
    public void getHotProducts(List<ProductBean> productList, List<ProducHeadBean> type) {

        if (refreshLayout.isRefreshing()) {
            refreshLayout.finishRefresh();
        }
        if (type == null || type.size() == 0) {
            mLLHotGoods.setVisibility(View.GONE);
            return;
        }

        mLLHotGoods.setVisibility(View.VISIBLE);
        mDates2.clear();
        mDates2.addAll(type);


        adapter.notifyDataSetChanged();
    }

    //首页广告弹窗
    @Override
    public void getAdPosition(List<BannerBean> bannerBeanList) {
        if (bannerBeanList != null && bannerBeanList.size() != 0) {
            String path = bannerBeanList.get(0).getPath();

            if (!path.equals("")) {
                AdDialog dialog = new AdDialog(mContext);
                dialog.setImg(path);
                dialog.show();
            }
        }

    }

    //首页banner
    @Override
    public void getBanner(final List<BannerBean> bannerBeanList) {

        if (bannerBeanList != null && bannerBeanList.size() != 0) {
            List<String> images = new ArrayList<>();
            for (int i = 0; i < bannerBeanList.size(); i++) {
                String path = bannerBeanList.get(i).getPath();
                if (!path.equals("")) {
                    images.add(path);
                }
            }

            mBanner.setImages(images);
            mBanner.start();
            mBanner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {

                    String value = bannerBeanList.get(position).getValue();
                    if (value == null || value.equals("")) {
                        return;
                    }
                    switch (bannerBeanList.get(position).getType()) {


                        case "productCategory"://跳转分类页面，根据id显示默认选中哪一个分类标签

                            Intent intentClassfy = new Intent(mActivity, ClasifyActivity.class);
                            startActivity(intentClassfy);

                            break;
                        case "product"://跳转产品详情页面
                            long productId = NumberUtils.string2Long(value);
                            if (productId != 0L) {
                                Intent intentProduct = new Intent(mActivity, ProductDetailActivity.class);
                                intentProduct.putExtra("productId", productId);
                                startActivity(intentProduct);
                            }
                            break;
                        case "weburl"://跳转url页面
                            if (value.contains("{token}")){
                                String token = (String) SharedPreferencesUtil.get(mContext, Constant.token, "");
                                if (token.equals("")){
                                    startActivity(LoginActivity.class);
                                    return;
                                }else {
                                    value = value.replace("{token}",token);
                                }
                            }
                            Intent intentWeb = new Intent(mActivity, WebActivity.class);
                            intentWeb.putExtra("title", bannerBeanList.get(position).getTitle());
                            intentWeb.putExtra("weburl", value);
                            startActivity(intentWeb);
                            break;
                        case "search"://跳转搜索页面
                            Intent intentSearch = new Intent(mActivity, SearchActivity.class);
                            intentSearch.putExtra("search", value);
                            startActivity(intentSearch);
                            break;
                        case "promotion"://热销活动页面
                            long l = NumberUtils.string2Long(value);
                            if (l != 0L) {
                                Intent intent = new Intent(mActivity, HotActivity.class);
                                intent.putExtra("mId", l);
                                startActivity(intent);
                            }
                            break;
                    }
                }
            });
        }
    }

    //获取活动模块商品
    @Override
    public void getAllPromotion(List<HomePageActivityBean> activityBeans) {
        if (activityBeans == null || activityBeans.size() == 0) {
            mLLYh.setVisibility(View.GONE);
            return;
        }
        mLLYh.setVisibility(View.VISIBLE);
        mYHImg.removeAllViews();
        displayUI(activityBeans, mYHImg, mContext);
    }

    //获取添加商品的结果
    @Override
    public void addProduct(boolean isAdd) {
        if (isAdd) {
            ToastUtils.show(mContext, "加入购物车成功");
            homeActivity.updateCarNum();
        } else {
            ToastUtils.show(mContext, "加入购物车失败");
        }
    }


    //获取商品详情（用于多重规格的商品的展示）
    @Override
    public void getProductDetailById(ProductBean productBean) {
        if (productBean == null) {
            showErrorToast("获取商品信息错误");
            return;
        }

        if (productBean == null) {
            return;
        }

        isCanBuy = false;
        if (productBean.getStock() == null) {//不限制库存
            isCanBuy = true;
        } else if (productBean.getStock() == 0) {
            isCanBuy = false;
        } else if (productBean.getStock() > 0) {
            isCanBuy = true;
        }

        //是否可以购买
        if (isCanBuy) {
            mBtn_select.setClickable(true);
            mBtn_select.setBackgroundResource(R.color.basicColor);
        } else {
            mBtn_select.setClickable(false);
            mBtn_select.setBackgroundResource(R.color.defalute);
        }

        List<SpecificationsBean> specifications = productBean.getSpecifications();//商品规格
        List<String> productImages = productBean.getProductImages();//banner图片
        String name = productBean.getName();//商品名称
        String introduction = productBean.getIntroduction();//商品描述
        String memo = productBean.getMemo();//备注
        double price = productBean.getPrice();//价格
        String unit = productBean.getUnit();//单位
        double unitPrice = productBean.getUnitPrice();
        String image = productBean.getImage();
        String packaging = productBean.getPackaging();
        long id = productBean.getId();
        buyId = id;

        ImageLoaders.displayImage(mImg_productImg, image);
        mTxt_productName.setText(name);
        mTxt_productMsg.setText(memo);
        mTxt_price.setText("¥" + unitPrice + "/" + unit);
        mTxt_package.setText(packaging);
        mTxt_productPrice.setText("" + price + "");

        if (specifications == null || specifications.size() == 0) {
//            carId = mId;
        } else {
            specificationsBeanList.clear();
            specificationsBeanList.addAll(specifications);
            productDetailAdapter.notifyDataSetChanged();
        }

        shoePOP();
    }

    /**
     * 获取秒杀数据
     */
    @Override
    public void getMiaoSha(MiaoShaBean miaoShaBean) {

        if (miaoShaBean == null) {
            mLLMiaoshao.setVisibility(View.GONE);
            return;
        }

        List<MiaoShaBean.ProductInfoListBean> mList = miaoShaBean.getProductInfoList();
        if (mList == null || mList.size() == 0) {
            mLLMiaoshao.setVisibility(View.GONE);
            return;
        }

        mLLMiaoshao.setVisibility(View.VISIBLE);
        miaoShaBeanList.clear();
        miaoShaBeanList.addAll(mList);

        miaoShaAdapter.notifyDataSetChanged();

        if (bankCountDown != null) {
            bankCountDown.cancel();
            bankCountDown = null;
        }

        String endDate = miaoShaBean.getEndDate();
        long l = DateUtil.stringToMill(endDate);
        long currentTimeMillis = System.currentTimeMillis();
        long countDown = 0;
        if (l <= -1L) {
            countDown = 1L;
        } else {
            countDown = l - currentTimeMillis;
        }
        if (countDown <= 0) {
            isMiaoShaFinished = false;
            countDown = 1L;
        }else {
            isMiaoShaFinished = true;
        }
        bankCountDown = new BankCountDown(countDown, 1000, new MiaoShaCountdownListener() {
            @Override
            public void timeCountdown(String day,String hour, String minute, String scound) {

                if (day.length() == 1) {
                    day = "0" + day;
                }

                if (hour.length() == 1) {
                    hour = "0" + hour;
                }

                if (minute.length() == 1) {
                    minute = "0" + minute;
                }

                if (scound.length() == 1) {
                    scound = "0" + scound;
                }
                mTxtDay.setText(day);
                mTxtHour.setText(hour);
                mTxtMin.setText(minute);
                mTxtSecond.setText(scound);
            }

            @Override
            public void timeFinish() {
                mTxtDay.setText("00");
                mTxtHour.setText("00");
                mTxtMin.setText("00");
                mTxtSecond.setText("00");
                isMiaoShaFinished = false;
                bankCountDown.cancel();
            }
        });

        bankCountDown.start();
    }


    private void displayUI(final List<HomePageActivityBean> mDatas, YhFlowLayout yhFlowLayout, final Context context) {

        for (int i = 0; i < mDatas.size(); i++) {
            HomePageActivityBean specificationValuesBean = mDatas.get(i);

            View view = LayoutInflater.from(context).inflate(R.layout.wedget_home_act, null);

            ImageView mTxtTag = (ImageView) view.findViewById(R.id.mImg_home_act);
            mTxtTag.setScaleType(ImageView.ScaleType.FIT_XY);
            int screenWidth = ScreenUtil.getScreenWidth(context);//屏幕宽

            mTxtTag.getLayoutParams().height = ScreenUtil.dip2px(context, 100);

//            specificationValuesBean.setKind(i%2);

            if (specificationValuesBean.getSize().equals("small")) {
                mTxtTag.getLayoutParams().width = (screenWidth - ScreenUtil.dip2px(context, 30)) / 2;
                ImageLoaders.displayConnerImg(mTxtTag, specificationValuesBean.getSmallMobileImage(), ScreenUtil.dip2px(context, 5));
            } else {
                mTxtTag.getLayoutParams().width = screenWidth - ScreenUtil.dip2px(context, 20);
                ImageLoaders.displayConnerImg(mTxtTag, specificationValuesBean.getMobileImage(), ScreenUtil.dip2px(context, 5));
            }

            final int finalI = i;
            mTxtTag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mActivity, HotActivity.class);
                    intent.putExtra("mId", mDatas.get(finalI).getId());
                    startActivity(intent);
                }
            });
            yhFlowLayout.addView(view);
        }
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
        //选好了
        mBtn_select = pickVIew.findViewById(R.id.mBtn_select);

        popupWindow = PopupWindowHelper.createPopupWindow(pickVIew, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setAnimationStyle(R.style.slide_up_in_down_out);

        //点击空白地方
        view_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        productDetailAdapter = new ProductDetailAdapter(mContext, specificationsBeanList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mRv_mutle.setAdapter(productDetailAdapter);
        mRv_mutle.setLayoutManager(linearLayoutManager);

        productDetailAdapter.setOnItemClickListener(new ProductDetailAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int parentPos, String specParam) {
                mPresenter.getProductBySize(buyId, specParam);

            }
        });

        //点击加号
        mImg_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = mTxt_num.getText().toString().trim();
                int i = NumberUtils.string2Int(s);
                i++;
                mTxt_num.setText(i + "");
            }
        });

        //点击减号
        mImg_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = mTxt_num.getText().toString().trim();
                int i = NumberUtils.string2Int(s);
                if (i <= 1) {
                    ToastUtils.show(mContext, "已经是最少了");
                    return;
                } else {
                    i--;
                    mTxt_num.setText(i + "");
                }
            }
        });

        //点击选好了
        mBtn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = mTxt_num.getText().toString().trim();
                int i = NumberUtils.string2Int(s);
                mPresenter.addProduct(i, new ProductParmsBean(buyId));
            }
        });
    }

    @OnClick({R.id.mLL_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mLL_search://跳转搜索页面
                startActivity(SearchActivity.class);
                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            StatusBarUtils.setColor(mActivity, getResources().getColor(R.color.white), 0);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bankCountDown.cancel();
    }
}
