package com.dodo.marcket.business.homepage.fragment;


import android.content.Context;
import android.content.Intent;
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
import com.dodo.marcket.bean.BannerBean;
import com.dodo.marcket.bean.HomePageActivityBean;
import com.dodo.marcket.bean.ProductBean;
import com.dodo.marcket.bean.ProductParmsBean;
import com.dodo.marcket.bean.SpecificationsBean;
import com.dodo.marcket.business.HomeActivity;
import com.dodo.marcket.business.clasify.activity.ClasifyActivity;
import com.dodo.marcket.business.clasify.activity.SearchActivity;
import com.dodo.marcket.business.clasify.adapter.ProductAdapter;
import com.dodo.marcket.business.homepage.activity.HotActivity;
import com.dodo.marcket.business.homepage.activity.ProductDetailActivity;
import com.dodo.marcket.business.homepage.activity.WebActivity;
import com.dodo.marcket.business.homepage.adapter.ProductDetailAdapter;
import com.dodo.marcket.business.homepage.constrant.HomePageContract;
import com.dodo.marcket.business.homepage.presenter.HomePagePresenter;
import com.dodo.marcket.utils.GlideImageLoader;
import com.dodo.marcket.utils.ImageLoaders;
import com.dodo.marcket.utils.NumberUtils;
import com.dodo.marcket.utils.ScreenUtil;
import com.dodo.marcket.utils.ToastUtils;
import com.dodo.marcket.utils.photo.PopupWindowHelper;
import com.dodo.marcket.wedget.YhFlowLayout;
import com.dodo.marcket.wedget.dialog.AdDialog;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
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
    private LinearLayoutManager manager;
    private ProductAdapter adapter;
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
    private ProductDetailAdapter productDetailAdapter;

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
        homeActivity = (HomeActivity) mActivity;
        initBanner();
        initRv();
        initPickView();

        mPresenter.getBanner(2);//首页banner
        mPresenter.getHotProduct();//获取热销商品信息
        mPresenter.getAdPosition(1);//弹窗广告
        mPresenter.getAllPromotion();//首页获取活动模块信息
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

    //首页轮播
    private void initBanner() {
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.setBannerAnimation(Transformer.DepthPage);
        mBanner.isAutoPlay(true);
        mBanner.setDelayTime(5000);

    }


    //首页热销商品
    private void initRv() {
        mDates = new ArrayList<>();
        manager = new LinearLayoutManager(mContext);
        adapter = new ProductAdapter(mContext, mDates);
        mRvMain.setLayoutManager(manager);
        mRvMain.setAdapter(adapter);

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
            public void onAddClicked(int pos) {//点击了添加购物车按钮（单一规格）

                if (!hastoken){
                    goToLogin();
                    return;
                }
                ProductBean productBean = mDates.get(pos);
                long id = productBean.getId();
                mPresenter.addProduct(1, new ProductParmsBean(id));
            }

            @Override
            public void onMutiSizeClicked(int pos) {//点击了添加购物车按钮（多重规格）
                ToastUtils.show(mContext, "多规格");
                shoePOP();

            }
        });
    }

    //热销列表
    @Override
    public void getHotProducts(List<ProductBean> productList) {
        if (productList == null || productList.size() == 0) {

            mLLHotGoods.setVisibility(View.GONE);
            return;
        }

        mLLHotGoods.setVisibility(View.VISIBLE);
        mDates.clear();
        mDates.addAll(productList);
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
                    if (value==null||value.equals("")){
                        return;
                    }
                    switch (bannerBeanList.get(position).getType()){


                        case "productCategory"://跳转分类页面，根据id显示默认选中哪一个分类标签

                            Intent intentClassfy = new Intent(mActivity, ClasifyActivity.class);
                            startActivity(intentClassfy);

                            break;
                        case "product"://跳转产品详情页面
                            long productId = NumberUtils.string2Long(value);
                            if (productId!=0L) {
                                Intent intentProduct = new Intent(mActivity, ProductDetailActivity.class);
                                intentProduct.putExtra("productId", productId);
                                startActivity(intentProduct);
                            }
                            break;
                        case "weburl"://跳转url页面
                            Intent intentWeb = new Intent(mActivity, WebActivity.class);
                            intentWeb.putExtra("title",bannerBeanList.get(position).getTitle());
                            intentWeb.putExtra("weburl", value);
                            startActivity(intentWeb);
                            break;
                        case "search"://跳转搜索页面
                            Intent intentSearch = new Intent(mActivity, SearchActivity.class);
                            intentSearch.putExtra("search",value);
                            startActivity(intentSearch);
                            break;
                        case "promotion"://热销活动页面
                            long l = NumberUtils.string2Long(value);
                            if (l!=0L){
                                Intent intent = new Intent(mActivity, HotActivity.class);
                                intent.putExtra("mId",l);
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
                mTxtTag.getLayoutParams().width = (screenWidth - ScreenUtil.dip2px(context, 30))/2;
                ImageLoaders.displayConnerImg(mTxtTag, specificationValuesBean.getSmallMobileImage(), ScreenUtil.dip2px(context, 5));
            } else {
                mTxtTag.getLayoutParams().width = screenWidth - ScreenUtil.dip2px(context, 20);
                ImageLoaders.displayConnerImg(mTxtTag, specificationValuesBean.getMobileImage(), ScreenUtil.dip2px(context, 5));
            }

            final int finalI = i;
            mTxtTag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mActivity,HotActivity.class);
                    intent.putExtra("mId",mDatas.get(finalI).getId());
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
