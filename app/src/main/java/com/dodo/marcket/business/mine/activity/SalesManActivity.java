package com.dodo.marcket.business.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseActivity;
import com.dodo.marcket.bean.SalesMan;
import com.dodo.marcket.business.mine.constrant.SalesManContract;
import com.dodo.marcket.business.mine.presenter.SalesManPresenter;
import com.dodo.marcket.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 我的业务员
 */
public class SalesManActivity extends BaseActivity<SalesManPresenter> implements SalesManContract.View {

    @BindView(R.id.mEd_scanCode)
    EditText mEdScanCode;
    @BindView(R.id.mLL_addSalesMan)
    LinearLayout mLLAddSalesMan;
    @BindView(R.id.mTxt_addSure)
    TextView mTxtAddSure;
    @BindView(R.id.mLL_noSalesMan)
    LinearLayout mLLNoSalesMan;
    @BindView(R.id.mTxt_showSalesManName)
    TextView mTxtShowSalesManName;
    @BindView(R.id.mTxt_showSalesManCode)
    TextView mTxtShowSalesManCode;
    @BindView(R.id.mTxt_showSalesManEdit)
    TextView mTxtShowSalesManEdit;
    @BindView(R.id.mLL_hasSalesman)
    LinearLayout mLLHasSalesman;
    @BindView(R.id.mTxt_editSure)
    TextView mTxtEditSure;
    @BindView(R.id.mTxt_editDelete)
    TextView mTxtEditDelete;
    @BindView(R.id.mLL_editSalesman)
    LinearLayout mLLEditSalesman;
    @BindView(R.id.mEd_editSalesCode)
    EditText mEdEditSalesCode;

    @Override
    public int getLayoutId() {
        return R.layout.activity_sales_man;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
    }

    @Override
    public void loadData() {
        initTitle();
        mPresenter.getSalesMan();//获取业务员信息
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

    //初始化标题
    private void initTitle() {
        mTitle.setTitle("我的业务员");
    }

    //绑定业务员
    @Override
    public void bindSalesMan(boolean s) {
        if (s) {
            mPresenter.getSalesMan();
        } else {
            showErrorMsg("绑定失败失败", "");
        }
    }

    //删除业务员
    @Override
    public void cancleSalesMan(boolean b) {
        if (b) {
            mLLNoSalesMan.setVisibility(View.VISIBLE);//添加业务员显示
            mLLEditSalesman.setVisibility(View.GONE);//编辑显示
            mLLHasSalesman.setVisibility(View.GONE);//存在业务员显示
            showErrorToast("删除业务员成功");
        } else {
            showErrorToast("删除业务员失败");
        }
    }

    //获取业务员
    @Override
    public void getSalesMan(SalesMan salesMan) {
        if (salesMan == null || salesMan.getCode() == null || salesMan.getCode().equals("")) {
            mLLNoSalesMan.setVisibility(View.VISIBLE);//添加业务员显示
            mLLEditSalesman.setVisibility(View.GONE);//编辑显示
            mLLHasSalesman.setVisibility(View.GONE);//存在业务员显示
            return;
        } else {
            mLLNoSalesMan.setVisibility(View.GONE);//添加业务员显示
            mLLEditSalesman.setVisibility(View.GONE);//编辑显示
            mLLHasSalesman.setVisibility(View.VISIBLE);//存在业务员显示
            mTxtShowSalesManName.setText(salesMan.getName());
            mTxtShowSalesManCode.setText(salesMan.getCode());
            mEdEditSalesCode.setText(salesMan.getCode());
        }
    }

    @OnClick({R.id.mTxt_addSure, R.id.mTxt_editSure, R.id.mTxt_editDelete, R.id.mTxt_showSalesManEdit, R.id.mLL_scanCode, R.id.mImg_scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mTxt_addSure://添加业务员确定按钮
                String code = mEdScanCode.getText().toString().trim();
                if (TextUtils.equals("", code)) {
                    ToastUtils.show(mContext, "请输入编码");
                    return;
                }
                addSalesMan(code);
                break;
            case R.id.mTxt_editSure://编辑业务员确定按钮
                String code1 = mEdEditSalesCode.getText().toString().trim();
                if (TextUtils.equals("", code1)) {
                    ToastUtils.show(mContext, "请输入编码");
                    return;
                }
                addSalesMan(code1);
                break;
            case R.id.mTxt_editDelete://删除业务员按钮
                mPresenter.cancleSalesMan();
                break;
            case R.id.mTxt_showSalesManEdit://编辑按钮
                mLLNoSalesMan.setVisibility(View.GONE);//添加业务员显示
                mLLEditSalesman.setVisibility(View.VISIBLE);//编辑显示
                mLLHasSalesman.setVisibility(View.GONE);//存在业务员显示
                break;

            case R.id.mLL_scanCode://扫描二维码
            case R.id.mImg_scan:
//                startActivity(ZXingActivity.class);
                startActivityForResult(new Intent(mActivity, ZXingActivity.class), 500);
                break;
        }
    }

    private void addSalesMan(String code) {
        mPresenter.bindSalesMan(code);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 500) {
            switch (resultCode) {
                case 500:
                    String stringExtra = data.getStringExtra("result");

                    mTxtShowSalesManCode.setText(stringExtra);
                    mEdEditSalesCode.setText(stringExtra);
                    mEdScanCode.setText(stringExtra);
                    break;
            }
        }

    }
}
