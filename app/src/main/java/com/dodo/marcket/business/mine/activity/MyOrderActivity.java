package com.dodo.marcket.business.mine.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseActivity;
import com.dodo.marcket.business.mine.adapter.ViewPagerFragmentAdapter;
import com.dodo.marcket.business.mine.adapter.ViewTestAdapter;
import com.dodo.marcket.business.mine.constrant.MyOrderContract;
import com.dodo.marcket.business.mine.fragment.OrderFragment;
import com.dodo.marcket.business.mine.presenter.MyOrderPresenter;
import com.dodo.marcket.wedget.XTabLayout.XTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyOrderActivity extends BaseActivity<MyOrderPresenter> implements MyOrderContract.View {

    @BindView(R.id.mXTab)
    XTabLayout mXTab;
    @BindView(R.id.mViewPage)
    ViewPager mViewPage;
    private List<Fragment> fragments = new ArrayList<>();
    private String[] titles = null;
    private ViewTestAdapter myPagerAdapter;


    @Override
    public int getLayoutId() {
        return R.layout.activity_my_order;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void loadData() {
        titles = new String[]{"全部","待付款","待发货","配送中","已完成","已取消"};
        setViewPagerAdapter();
        setTabBindViewPager();
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

    /**
     * tablayout和viewpager建立联系
     */
    private void setTabBindViewPager() {
        mXTab.setupWithViewPager(mViewPage);
        mXTab.setOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                mViewPage.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(XTabLayout.Tab tab) {

            }
        });

        mViewPage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 给viewpager设置适配器
     */
    private void setViewPagerAdapter() {
        fragments.clear();
        for (int i = 0; i < titles.length; i++) {
            fragments.add(new OrderFragment(i));
        }


        myPagerAdapter = new ViewTestAdapter(getSupportFragmentManager(),fragments,titles);
        mViewPage.setAdapter(myPagerAdapter);
    }
}
