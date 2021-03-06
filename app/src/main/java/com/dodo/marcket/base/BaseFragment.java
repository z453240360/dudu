package com.dodo.marcket.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dodo.marcket.business.mine.activity.LoginActivity;
import com.dodo.marcket.http.constant.Constant;
import com.dodo.marcket.utils.SharedPreferencesUtil;
import com.dodo.marcket.utils.TUtil;
import com.dodo.marcket.wedget.toasty.Toasty;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;


public abstract class BaseFragment<T extends BasePresenter> extends Fragment {
    protected View mView;
    public T mPresenter;
    public Context mContext;
    public BaseActivity mActivity;
    public boolean showId = true;
    public String token;
    public String dumpcartId;
    public String disctCode;
    public static String needToken;
    public static boolean hastoken;
    public static boolean isFreshOrder = false;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        ButterKnife.bind(this, mView);
//        if (!EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().register(this);
//        }
        mContext = getContext();
        mActivity = (BaseActivity) getActivity();
        try {
            mPresenter = TUtil.getT(this, 0);
        } catch (Exception e) {

        }
        if (mPresenter != null) {
            mPresenter.mContext = mContext;
        }



        initPresenter();
        loadData();
        return mView;
    }

    public void goToLogin(){
        startActivity(LoginActivity.class);
    }

    public View getParentView() {
        return mView;
    }

    /*********************
     * 子类实现
     *****************************/
    //获取布局文件
    public abstract int getLayoutId();

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void initPresenter();

    //加载View、设置数据
    public abstract void loadData();


    @Override
    public void onResume() {
        super.onResume();
        needToken = (String) SharedPreferencesUtil.get(mContext, Constant.token, "");
        if (needToken.equals("")){
            hastoken = false;
        }else {
            hastoken = true;
        }

    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(mContext, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(mContext, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }

    @Override
    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public double[] getLocation(){
        return mActivity.getLocation();
    }

    public void showErrorToast(String e){
        Toasty.error(mContext,e,3,false).show();
    }
}
