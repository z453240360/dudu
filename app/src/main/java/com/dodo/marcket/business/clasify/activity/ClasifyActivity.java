package com.dodo.marcket.business.clasify.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseActivity;
import com.dodo.marcket.business.clasify.constrant.ClassifyContract;
import com.dodo.marcket.business.clasify.fragment.ClassifyFragment;
import com.dodo.marcket.business.clasify.presenter.ClassifyPresenter;

public class ClasifyActivity extends BaseActivity<ClassifyPresenter> implements ClassifyContract.View {


    private FragmentManager manager;
    private ClassifyFragment classifyFragment;
    private long mId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_clasify;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
    }

    @Override
    public void loadData() {
        initTitle();
        Intent intent = getIntent();
        mId = intent.getLongExtra("productId", 0L);
        manager = getSupportFragmentManager();
        if (mId==0L){
            classifyFragment = new ClassifyFragment();
        }else {
            classifyFragment = new ClassifyFragment(mId);
        }
        manager.beginTransaction().add(R.id.mFrame, classifyFragment).commit();
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

    //初始化标题
    private void initTitle() {
        mTitle.setTitle("分类");
    }
}
