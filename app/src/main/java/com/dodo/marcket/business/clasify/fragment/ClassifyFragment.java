package com.dodo.marcket.business.clasify.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseFragment;
import com.dodo.marcket.bean.FirstClassfyBean;
import com.dodo.marcket.business.clasify.activity.SearchActivity;
import com.dodo.marcket.business.clasify.adapter.ClassifyAdapter;
import com.dodo.marcket.business.clasify.constrant.ClassifyFragmentContract;
import com.dodo.marcket.business.clasify.presenter.ClassifyFragmentPresenter;
import com.dodo.marcket.business.shoppingcar.fragment.BuyListFragment;
import com.dodo.marcket.utils.photo.PopupWindowHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class ClassifyFragment extends BaseFragment<ClassifyFragmentPresenter> implements ClassifyFragmentContract.View {


    public static ClassifyFragment classifyFragment;
    @BindView(R.id.mRv_first)
    RecyclerView mRvFirst;
    @BindView(R.id.mFrame_second)
    FrameLayout mFrameSecond;
    Unbinder unbinder;
    @BindView(R.id.mEd_search)
    TextView mEdSearch;
    @BindView(R.id.mLL_search)
    LinearLayout mLLSearch;
    @BindView(R.id.mTxt_sendPrice)
    TextView mTxtSendPrice;
    @BindView(R.id.mLL_sendPrice)
    LinearLayout mLLSendPrice;
    @BindView(R.id.mImg_huishou)
    ImageView mImgHuishou;
    @BindView(R.id.mTxt_selectAll)
    TextView mTxtSelectAll;
    @BindView(R.id.ll_img)
    LinearLayout llImg;
    @BindView(R.id.mTxt_price)
    TextView mTxtPrice;
    @BindView(R.id.mTxt_pay)
    TextView mTxtPay;
    @BindView(R.id.mLL_bottomView)
    LinearLayout mLLBottomView;
    Unbinder unbinder1;

    private List<FirstClassfyBean> mDates = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();
    private FragmentManager fragmentManager;
    private Fragment lastFragment;
    private LinearLayoutManager firstManager;
    private ClassifyAdapter firstAdapter;
    private View pickVIew;
    private PopupWindow popupWindow;

    public static ClassifyFragment getInstance() {
        if (classifyFragment == null)
            classifyFragment = new ClassifyFragment();
        return classifyFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_classify;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
    }

    @Override
    public void loadData() {

        initRvFirst();
        initPickView();
        mPresenter.getFirstKind();
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
    public void getFirstKind(List<FirstClassfyBean> firstClassfyBeanList) {
        if (firstClassfyBeanList == null || firstClassfyBeanList.size() == 0) {
            return;
        }

        mDates.clear();
        mDates.addAll(firstClassfyBeanList);
        mDates.get(0).setFirstSelected(true);
        mDates.get(0).setShowList(false);
        List<FirstClassfyBean.SubProductCategoryBean> subProductCategory = mDates.get(0).getSubProductCategory();
        if (subProductCategory.size() > 0) {
            subProductCategory.get(0).setSelected(true);
        }
        firstAdapter.notifyDataSetChanged();
        initSecondClassfy(mDates);
    }


    //一级列表
    private void initRvFirst() {
        firstManager = new LinearLayoutManager(mContext);
        firstAdapter = new ClassifyAdapter(mContext, mDates);
        mRvFirst.setLayoutManager(firstManager);
        mRvFirst.setAdapter(firstAdapter);
        firstAdapter.setOnItemClickListener(new ClassifyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, int id) {
                fragmentManager.beginTransaction().hide(lastFragment).commit();
                if (fragmentList.get(pos).isAdded()) {
                    fragmentManager.beginTransaction().show(fragmentList.get(pos)).commit();
                } else {
                    fragmentManager.beginTransaction().add(R.id.mFrame_second, fragmentList.get(pos)).commit();
                }
                lastFragment = fragmentList.get(pos);
            }

            @Override
            public void onChildClick(int pos, int id) {
                fragmentManager.beginTransaction().hide(lastFragment).commit();
                if (fragmentList.get(pos).isAdded()) {
                    fragmentManager.beginTransaction().show(fragmentList.get(pos)).commit();
                } else {
                    fragmentManager.beginTransaction().add(R.id.mFrame_second, fragmentList.get(pos)).commit();
                }
                lastFragment = fragmentList.get(pos);
            }
        });
    }

    //二级商品列表
    public void initSecondClassfy(List<FirstClassfyBean> firstClassfyList) {
        for (int i = 0; i < firstClassfyList.size(); i++) {

            List<FirstClassfyBean.SubProductCategoryBean> subProductCategory = firstClassfyList.get(i).getSubProductCategory();
            if (subProductCategory.size() != 0) {
                for (int j = 0; j < subProductCategory.size(); j++) {

                    int id = subProductCategory.get(j).getId();
                    BuyListFragment buyListFragment = new BuyListFragment(id);
                    fragmentList.add(buyListFragment);
                }
            }

        }

        fragmentManager = mActivity.getSupportFragmentManager();

        lastFragment = fragmentList.get(0);
        fragmentManager.beginTransaction().add(R.id.mFrame_second, fragmentList.get(0)).commit();
    }


    //查看已经选择的商品
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


    //查看已经选择的商品
    private void initPickView() {
        pickVIew = LayoutInflater.from(mActivity).inflate(R.layout.layout_popup_car_goods, null, false);

        View view_pop = pickVIew.findViewById(R.id.view_pop);


        popupWindow = PopupWindowHelper.createPopupWindow(pickVIew, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setAnimationStyle(R.style.slide_up_in_down_out);

        //点击空白地方
        view_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    @OnClick({R.id.mLL_search, R.id.ll_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mLL_search://跳转搜索页面
                startActivity(SearchActivity.class);
                break;
            case R.id.ll_img://弹出已经加入购物车弹窗
                shoePOP();
                break;
        }
    }

}
