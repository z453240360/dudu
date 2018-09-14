package com.dodo.marcket.business;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseActivity;
import com.dodo.marcket.bean.BannerBean;
import com.dodo.marcket.bean.basebean.MyMessageEvent;
import com.dodo.marcket.business.clasify.fragment.ClassifyFragment;
import com.dodo.marcket.business.homepage.constrant.HomeContract;
import com.dodo.marcket.business.homepage.fragment.HomePageFragment;
import com.dodo.marcket.business.homepage.presenter.HomePresenter;
import com.dodo.marcket.business.mine.fragment.MineFragment;
import com.dodo.marcket.business.shoppingcar.fragment.ShoppingCartFragment;
import com.dodo.marcket.utils.statusbar.StatusBarUtils;
import com.dodo.marcket.wedget.MyRadioButton;
import com.dodo.marcket.wedget.toasty.Toasty;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import q.rorbin.badgeview.QBadgeView;

public class HomeActivity extends BaseActivity<HomePresenter> implements HomeContract.View {


    @BindView(R.id.container)
    FrameLayout mFrame;
    @BindView(R.id.group)
    RadioGroup group;
    @BindView(R.id.rb_home)
    MyRadioButton rbHome;
    @BindView(R.id.rb_classify)
    MyRadioButton rbClassify;
    @BindView(R.id.rb_buyCar)
    MyRadioButton rbBuyCar;
    @BindView(R.id.rb_mime)
    MyRadioButton rbMime;
    @BindView(R.id.mTxt_carNum)
    TextView mTxtCarNum;
    private FragmentManager manager;
    private Fragment lastFragment;

    private List<Fragment> fragmentsList = new ArrayList<>();
    private MineFragment mineFragment;
    private ClassifyFragment classifyFragment;
    private ShoppingCartFragment shoppingCartFragment;
    private HomePageFragment homePageFragment;
    private QBadgeView badgeView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
    }

    @Override
    public void loadData() {
        EventBus.getDefault().register(this);
        initFragment();

        mPresenter.getBanner(2);
    }


    @Override
    public void initStatusBar() {
        StatusBarUtils.setTranslucentForImageViewInFragment(this, 0, null);
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

    private void initFragment() {

        manager = getSupportFragmentManager();

        homePageFragment = new HomePageFragment();
        mineFragment = new MineFragment();
        classifyFragment = new ClassifyFragment();
        shoppingCartFragment = new ShoppingCartFragment();
        fragmentsList.add(homePageFragment);
        fragmentsList.add(classifyFragment);
        fragmentsList.add(shoppingCartFragment);
        fragmentsList.add(mineFragment);

        manager.beginTransaction().add(R.id.container, fragmentsList.get(0)).commit();
        lastFragment = fragmentsList.get(0);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton select = (RadioButton) findViewById(i);
                int index = Integer.parseInt(select.getTag().toString());
                if (fragmentsList.get(index).isAdded()) {
                    manager.beginTransaction().show(fragmentsList.get(index)).commit();
                } else {
                    manager.beginTransaction().add(R.id.container, fragmentsList.get(index)).commit();
                }
                manager.beginTransaction().hide(lastFragment).commit();
                lastFragment = fragmentsList.get(index);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCarNum();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCarMessage(MyMessageEvent event) {
//        Toasty.error(mContext,"购物车数量："+event.getCarNum()+"").show();
    }

    //获取购物车数量
    public void updateCarNum(){
        mPresenter.getCarNum();
    }

    @Override
    public void getCarNum(int num) {
        if (num<=0){
            mTxtCarNum.setVisibility(View.GONE);
        }else if (num>99){
            mTxtCarNum.setVisibility(View.VISIBLE);
            mTxtCarNum.setText("99+");
        }else {
            mTxtCarNum.setVisibility(View.VISIBLE);
            mTxtCarNum.setText(num+"");
        }

        EventBus.getDefault().post(new MyMessageEvent(num));
    }

    @Override
    public void getBanner(List<BannerBean> bannerBeanList) {

    }

}
