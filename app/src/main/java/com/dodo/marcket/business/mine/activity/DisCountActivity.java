package com.dodo.marcket.business.mine.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseActivity;
import com.dodo.marcket.business.mine.adapter.ViewPagerFragmentAdapter;
import com.dodo.marcket.business.mine.constrant.DisCountContract;
import com.dodo.marcket.business.mine.fragment.DisCountFragment;
import com.dodo.marcket.business.mine.presenter.DisCountPresenter;
import com.dodo.marcket.wedget.XTabLayout.XTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DisCountActivity extends BaseActivity<DisCountPresenter> implements DisCountContract.View {


    @BindView(R.id.mXTab)
    XTabLayout mXTab;
    @BindView(R.id.mViewPage)
    ViewPager mViewPage;
    private List<Fragment> fragments = new ArrayList<>();
    private String[] titles = null;
    private ViewPagerFragmentAdapter myPagerAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_dis_count;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
    }

    @Override
    public void loadData() {
        mTitle.setTitle("优惠券");
        titles = new String[]{"未使用", "已使用/过期"};
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
        showErrorToast(msg);
    }

    @Override
    public void getDisCount() {

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
//        for (int i = 0; i < titles.length; i++) {
//            fragments.add(new DisCountFragment());
//        }
        fragments.add(new DisCountFragment(0));
        fragments.add(new DisCountFragment(1));

        myPagerAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), titles, fragments);
        mViewPage.setAdapter(myPagerAdapter);
    }

}
