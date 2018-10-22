package com.dodo.marcket.business.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseActivity;
import com.dodo.marcket.bean.AreaParamBean;
import com.dodo.marcket.bean.ChildAddressBean;
import com.dodo.marcket.bean.MyAddressBean;
import com.dodo.marcket.business.mine.adapter.ChildAddressAdapter;
import com.dodo.marcket.business.mine.constrant.AddNewAddressContract;
import com.dodo.marcket.business.mine.presenter.AddNewAddressPresenter;
import com.dodo.marcket.utils.ScreenUtil;
import com.dodo.marcket.utils.photo.PopupWindowHelper;
import com.dodo.marcket.wedget.toasty.Toasty;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNewAddressActivity extends BaseActivity<AddNewAddressPresenter> implements AddNewAddressContract.View {


    @BindView(R.id.mTxt_userName)
    TextView mTxtUserName;
    @BindView(R.id.mEd_myName)
    EditText mEdMyName;
    @BindView(R.id.mTxt_userPhone)
    TextView mTxtUserPhone;
    @BindView(R.id.mEd_myPhone)
    EditText mEdMyPhone;
    @BindView(R.id.mTxt_userAddress)
    TextView mTxtUserAddress;
    @BindView(R.id.mEd_myAddress)
    TextView mEdMyAddress;
    @BindView(R.id.mTxt_userDoorNumber)
    TextView mTxtUserDoorNumber;
    @BindView(R.id.mEd_myDoor)
    EditText mEdMyDoor;
    @BindView(R.id.mTxt_keep)
    TextView mTxtKeep;

    @BindView(R.id.mCheckBox)
    CheckBox mCheckBox;
    @BindView(R.id.mTxt_deleteAddress)
    TextView mTxt_deleteAddress;

    private List<ChildAddressBean> mDatas = new ArrayList<>();
    private List<ChildAddressBean> mDatas2 = new ArrayList<>();
    private List<ChildAddressBean> mDatas3 = new ArrayList<>();
    private PopupWindow popupWindow;
    private View pickVIew;
    private RecyclerView mRv_addAddress;
    private RecyclerView mRv_addAddress2;
    private RecyclerView mRv_addAddress3;
    private View view_pop;
    private ChildAddressAdapter addressAdapter;
    private LinearLayoutManager manager;
    private long addressId = -1;
    private boolean checked = false;
    private MyAddressBean myAddressBean;
    private String fromWhere;
    private ChildAddressAdapter addressAdapter2;
    private LinearLayoutManager manager2;
    private ChildAddressAdapter addressAdapter3;
    private LinearLayoutManager manager3;

    private String province = "";//省
    private String city = "";//城市
    private String district = "";//区
    private TextView mCancel;
    private TextView mSure;


    @Override
    public int getLayoutId() {
        return R.layout.activity_add_new_address;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
    }

    @Override
    public void loadData() {
        Intent intent = getIntent();

        Bundle extras = intent.getExtras();
        fromWhere = extras.getString("fromWhere");
        if (TextUtils.equals(fromWhere, "1")) {//来自编辑按钮
            mTitle.setTitle("编辑地址");

            mTxt_deleteAddress.setVisibility(View.VISIBLE);

            myAddressBean = (MyAddressBean) extras.getSerializable("addressBean");
            addressId = myAddressBean.getId();

            mEdMyAddress.setText(myAddressBean.getProvince() + " " + myAddressBean.getCity() + " " + myAddressBean.getDistrict());
            mEdMyDoor.setText(myAddressBean.getAddress());
            mEdMyName.setText(myAddressBean.getConsignee());
            mEdMyPhone.setText(myAddressBean.getPhone());
            mCheckBox.setChecked(myAddressBean.isDefaultX());

        } else {
            mTxt_deleteAddress.setVisibility(View.GONE);
            mTitle.setTitle("新增地址");
        }


        initPickView();

//        mPresenter.getFirstArea();
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

    //添加会员地址
    @Override
    public void addAddress(Object myAddressBeanList) {

        finish();
//        if (myAddressBeanList) {
//        } else {
//            Toasty.info(mContext, "添加地址失败").show();
//        }
    }

    //获取一级区域地址
    @Override
    public void getFirstArea(List<ChildAddressBean> childAddressBeans) {
        if (childAddressBeans == null || childAddressBeans.size() == 0) {
            Toasty.error(mContext, "获取区域失败", 5, false).show();
            return;
        } else {
            mDatas.clear();
            mDatas.addAll(childAddressBeans);
            mDatas.get(0).setSelect(true);
            mPresenter.getlistChildArea(mDatas.get(0).getId());
            province = mDatas.get(0).getName();
            addressAdapter.notifyDataSetChanged();

            shoePOP();
        }
    }

    //获取子区域的地址（二级区域）
    @Override
    public void getlistChildArea(List<ChildAddressBean> childAddressBeans) {
        if (childAddressBeans == null || childAddressBeans.size() == 0) {
            Toasty.error(mContext, "获取区域失败", 5, false).show();
            return;
        } else {
            mDatas2.clear();
            mDatas2.addAll(childAddressBeans);
            mDatas2.get(0).setSelect(true);
            city = mDatas2.get(0).getName();
            mPresenter.getlistChildArea2(mDatas2.get(0).getId());
            addressAdapter2.notifyDataSetChanged();
        }


    }

    //获取子区域的地址（三级区域）
    @Override
    public void getlistChildArea2(List<ChildAddressBean> childAddressBeans) {
        if (childAddressBeans == null || childAddressBeans.size() == 0) {
            Toasty.error(mContext, "获取区域失败", 5, false).show();
            return;
        } else {
            mDatas3.clear();
            mDatas3.addAll(childAddressBeans);
            mDatas3.get(0).setSelect(true);
            district = mDatas3.get(0).getName();
            addressAdapter3.notifyDataSetChanged();
        }
    }


    //删除会员地址结果
    @Override
    public void deleteAddress(boolean b) {
        if (b) {
            showErrorToast("删除成功");
            finish();
        } else {
            showErrorToast("删除失败");
        }
    }


    @OnClick({R.id.mEd_myAddress, R.id.mTxt_keep, R.id.mLL_checked, R.id.mTxt_deleteAddress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mEd_myAddress:
//                mPresenter.getlistChildArea(792);
                mPresenter.getFirstArea();
                break;
            case R.id.mTxt_keep:
                String name = mEdMyName.getText().toString().trim();
                String phone = mEdMyPhone.getText().toString().trim();
                String doorAddress = mEdMyDoor.getText().toString().trim();
                String trim = mEdMyAddress.getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    Toasty.info(mContext, "请输入姓名").show();
                    return;
                } else if (TextUtils.isEmpty(phone)) {
                    Toasty.info(mContext, "请输入电话号码").show();
                    return;
                } else if (TextUtils.isEmpty(doorAddress)) {
                    Toasty.info(mContext, "请输入地址").show();
                    return;
                } else if (TextUtils.isEmpty(trim)) {
                    Toasty.info(mContext, "请输选择市区").show();
                    return;
                }

                if (fromWhere.equals("1")) {
                    mPresenter.upDateAddress(addressId, name, phone, doorAddress, mCheckBox.isChecked(), province, city, district, new AreaParamBean(addressId));
                } else {
                    mPresenter.addAddress(0, name, phone, doorAddress, mCheckBox.isChecked(), province, city, district, new AreaParamBean(addressId));
                }

                break;
            case R.id.mLL_checked:
                if (mCheckBox.isChecked()) {
                    mCheckBox.setChecked(false);
                } else {
                    checked = false;
                    mCheckBox.setChecked(true);
                }

                break;

            case R.id.mTxt_deleteAddress: //删除地址
                mPresenter.deleteAddress(myAddressBean.getId());
                break;
        }
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
        PopupWindowHelper.backgroundAlpha(mActivity, 0.5f);
    }


    //查看已经选择的商品
    private void initPickView() {
        pickVIew = LayoutInflater.from(mActivity).inflate(R.layout.layout_popup_addaddress, null, false);
        popupWindow = PopupWindowHelper.createPopupWindow(pickVIew, ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtil.dip2px(mContext, 300));
        popupWindow.setAnimationStyle(R.style.slide_up_in_down_out);

        mCancel = (TextView) pickVIew.findViewById(R.id.mTxt_cancel);
        mSure = (TextView) pickVIew.findViewById(R.id.mTxt_sure);

        view_pop = pickVIew.findViewById(R.id.view_pop);
        mRv_addAddress = pickVIew.findViewById(R.id.mRv_addAddress);
        mRv_addAddress2 = pickVIew.findViewById(R.id.mRv_addAddress2);
        mRv_addAddress3 = pickVIew.findViewById(R.id.mRv_addAddress3);


        addressAdapter = new ChildAddressAdapter(mContext, mDatas);
        manager = new LinearLayoutManager(mContext);
        mRv_addAddress.setAdapter(addressAdapter);
        mRv_addAddress.setLayoutManager(manager);

        addressAdapter2 = new ChildAddressAdapter(mContext, mDatas2);
        manager2 = new LinearLayoutManager(mContext);
        mRv_addAddress2.setAdapter(addressAdapter2);
        mRv_addAddress2.setLayoutManager(manager2);

        addressAdapter3 = new ChildAddressAdapter(mContext, mDatas3);
        manager3 = new LinearLayoutManager(mContext);
        mRv_addAddress3.setAdapter(addressAdapter3);
        mRv_addAddress3.setLayoutManager(manager3);


        //一级区域
        addressAdapter.setOnItemClickListener(new ChildAddressAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int parentPos, long id, String name) {
                province = name;
                mPresenter.getlistChildArea(id);
                addressAdapter.setItem(parentPos);
            }
        });

        //二级区域
        addressAdapter2.setOnItemClickListener(new ChildAddressAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int parentPos, long id, String name) {
                mEdMyAddress.setText(name);
                city = name;
                mPresenter.getlistChildArea2(id);
                addressAdapter2.setItem(parentPos);

            }
        });

        //三级区域
        addressAdapter3.setOnItemClickListener(new ChildAddressAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int parentPos, long id, String name) {
                mEdMyAddress.setText(name);
                district = name;
                addressAdapter3.setItem(parentPos);

            }
        });

        //点击空白地方
        view_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        //点击确定
        mSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEdMyAddress.setText(province + " " + city + " " + district);
                popupWindow.dismiss();
            }
        });

        //点击取消
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

}
