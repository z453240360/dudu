package com.dodo.marcket.business.shoppingcar.activity;

import android.graphics.Color;
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

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.dodo.marcket.MainActivity;
import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseActivity;
import com.dodo.marcket.base.CustomLinearLayoutManager;
import com.dodo.marcket.bean.DisCountBean;
import com.dodo.marcket.bean.GoToPayBean;
import com.dodo.marcket.bean.MyAddressBean;
import com.dodo.marcket.bean.PayMethodBean;
import com.dodo.marcket.bean.SelectPostTimeBean;
import com.dodo.marcket.bean.params.PayParamsFatherBean;
import com.dodo.marcket.business.clasify.adapter.DiscountPopAdapter;
import com.dodo.marcket.business.clasify.adapter.PayPopAdapter;
import com.dodo.marcket.business.clasify.adapter.TimePop1Adapter;
import com.dodo.marcket.business.clasify.adapter.TimePop2Adapter;
import com.dodo.marcket.business.mine.activity.MyAddressActivity;
import com.dodo.marcket.business.shoppingcar.adapter.PayAdapter;
import com.dodo.marcket.business.shoppingcar.constrant.GoToPayContract;
import com.dodo.marcket.business.shoppingcar.presenter.GoToPayPresenter;
import com.dodo.marcket.utils.photo.PopupWindowHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 去结算页面
 */
public class GoToPayActivity extends BaseActivity<GoToPayPresenter> implements GoToPayContract.View {

    @BindView(R.id.mLL_showAddress1)
    LinearLayout mLLShowAddress1;
    @BindView(R.id.mLL_showAddress2)
    LinearLayout mLLShowAddress2;
    @BindView(R.id.mLL_address)
    LinearLayout mLLAddress;
    @BindView(R.id.mTxt_arriveTime)
    TextView mTxtArriveTime;
    @BindView(R.id.mTxt_payMethed)
    TextView mTxtPayMethed;
    @BindView(R.id.ll_update)
    LinearLayout llUpdate;
    @BindView(R.id.mRv_product)
    RecyclerView mRvProduct;
    @BindView(R.id.mTxt_give)
    TextView mTxtGive;
    @BindView(R.id.mLL_give)
    LinearLayout mLLGive;
    @BindView(R.id.mTxt_coupon)
    TextView mTxtCoupon;
    @BindView(R.id.mLL_coupon)
    LinearLayout mLLCoupon;
    @BindView(R.id.mTxt_point)
    TextView mTxtPoint;
    @BindView(R.id.mLL_point)
    LinearLayout mLLPoint;
    @BindView(R.id.mTxt_realPay)
    TextView mTxtRealPay;
    @BindView(R.id.mTxt_postMoney)
    TextView mTxtPostMoney;
    @BindView(R.id.mTxt_sendPrice)
    TextView mTxtSendPrice;
    @BindView(R.id.mLL_sendPrice)
    LinearLayout mLLSendPrice;
    @BindView(R.id.mTxt_pay)
    TextView mTxtPay;
    @BindView(R.id.mLL_bottomView)
    LinearLayout mLLBottomView;
    @BindView(R.id.mTxt_goToPayAddressName)
    TextView mTxtGoToPayAddressName;
    @BindView(R.id.mTxt_goToPayAddressPhone)
    TextView mTxtGoToPayAddressPhone;
    @BindView(R.id.mTxt_goToPayAddress)
    TextView mTxtGoToPayAddress;
    @BindView(R.id.mLL_postTime)
    LinearLayout mLLPostTime;
    @BindView(R.id.mLL_proList)
    LinearLayout mLLProList;
    private List<PayParamsFatherBean> payList;
    private View discountPopView;
    private PopupWindow discountPopWindow;
    private PopupWindow postTimeWindow;
    private View postTimePopView;
    private View payPopView;
    private PopupWindow payWindow;
    private List<GoToPayBean.CartsBean> proList = new ArrayList<>();
    private List<PayMethodBean> payMethodBeanList = new ArrayList<>();
    private List<DisCountBean> disCountBeanList = new ArrayList<>();
    private List<SelectPostTimeBean> selectPostTimeBeanList = new ArrayList<>();
    private List<SelectPostTimeBean.ItemBean> itemBeanList = new ArrayList<>();
    private PayAdapter proAdapter;
    private RecyclerView mRv_discountPop;
    private RecyclerView mRv_payPop;
    private LinearLayoutManager payManager;
    private PayPopAdapter payPopAdapter;
    private DiscountPopAdapter discountPopAdapter;
    private LinearLayoutManager discountManager;
    private OptionsPickerView<Object> pvCustomOptions;

    @Override
    public int getLayoutId() {
        return R.layout.activity_go_to_pay;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
    }

    @Override
    public void loadData() {

        initTitle();
        initRv();//初始化商品列表
        initDiscountPop();//初始化优惠券弹框
        initPayPop();//初始化支付弹框
        initPostTimePop();//初始换送达时间弹框


        payList = (List<PayParamsFatherBean>) getIntent().getSerializableExtra("payList");//获取选中的商品列表

        if (payList.size() > 0) {
            mPresenter.payProducts(payList);
            mPresenter.getAddress();//获取地址信息
            mPresenter.getPostTime();//获取送达时间
            mPresenter.getPayMethod();//获取支付方式
            mPresenter.getDisCount(0);//获取可用优惠券信息
        }
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

    //点击事件
    @OnClick({R.id.mLL_showAddress1, R.id.mLL_showAddress2, R.id.mLL_give, R.id.mLL_coupon, R.id.mLL_point, R.id.ll_update, R.id.mLL_postTime})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mLL_showAddress1://跳转我的地址页面
            case R.id.mLL_showAddress2:
                startActivity(MyAddressActivity.class);
                break;

            case R.id.mLL_give: //赠品

                break;

            case R.id.mLL_coupon://优惠券选择
                showDiscountPop(discountPopWindow);
                break;

            case R.id.mLL_point://积分选择

                break;

            case R.id.mLL_postTime://选择送达时间弹框
                showDiscountPop(postTimeWindow);
                break;

            case R.id.ll_update://支付方式选择
                showDiscountPop(payWindow);
                break;

        }
    }

    private void initTitle() {
        mTitle.setTitle("去结算");
    }

    //初始化商品列表
    private void initRv() {
        proList = new ArrayList<>();
        CustomLinearLayoutManager proManager = new CustomLinearLayoutManager(mContext);
        proAdapter = new PayAdapter(mContext, proList);
        mRvProduct.setFocusableInTouchMode(false);
        mRvProduct.setNestedScrollingEnabled(false);
        mRvProduct.setLayoutManager(proManager);
        mRvProduct.setAdapter(proAdapter);
    }


    //获取地址
    @Override
    public void getMyAddress(List<MyAddressBean> s) {
        if (s == null || s.size() == 0) {
            mLLShowAddress1.setVisibility(View.VISIBLE);
            mLLShowAddress2.setVisibility(View.GONE);
            return;
        } else {
            mLLShowAddress1.setVisibility(View.GONE);
            mLLShowAddress2.setVisibility(View.VISIBLE);
        }

        //判断是否含有默认地址信息
        MyAddressBean defaultAddress = getDefaultAddress(s);
        if (defaultAddress == null) {
            mLLShowAddress1.setVisibility(View.VISIBLE);
            mLLShowAddress2.setVisibility(View.GONE);
        } else {
            mLLShowAddress1.setVisibility(View.GONE);
            mLLShowAddress2.setVisibility(View.VISIBLE);

            String address = defaultAddress.getAddress();
            String consignee = defaultAddress.getConsignee();
            String phone = defaultAddress.getPhone();
            String province = defaultAddress.getProvince();

            MyAddressBean.AreaInfoBean areaInfo = defaultAddress.getAreaInfo();
            mTxtGoToPayAddress.setText(areaInfo.getName() + address);
            mTxtGoToPayAddressName.setText(consignee);
            mTxtGoToPayAddressPhone.setText(phone);
        }
    }

    //获取支付信息详情
    @Override
    public void getPayMsg(GoToPayBean payBean) {
        if (payBean == null) {
            return;
        }
        //获取支付方式列表
        List<GoToPayBean.PaymentMethodsBean> paymentMethods = payBean.getPaymentMethods();

        //获取商品列表信息
        List<GoToPayBean.CartsBean> carts = payBean.getCarts();
        if (carts != null && carts.size() > 0) {
            proList.clear();
            proList.addAll(carts);
            proAdapter.notifyDataSetChanged();
            mLLProList.setVisibility(View.VISIBLE);
        } else {
            mLLProList.setVisibility(View.GONE);
        }

        //获取赠品信息
        List<GoToPayBean.GiftItemInfosBean> giftItemInfos = payBean.getGiftItemInfos();
        String name = giftItemInfos.get(0).getName();
        mTxtGive.setText(name);


        double boxAmount = payBean.getBoxAmount();//筐的金额
        int freight = payBean.getFreight();//运费
        double onlineDiscount = payBean.getOnlineDiscount();
        double productAmount = payBean.getProductAmount();
        Object receiverInfo = payBean.getReceiverInfo();
        int userPoint = payBean.getUserPoint();
    }

    //列出送达时间
    @Override
    public void getPostTime(List<SelectPostTimeBean> s) {
        if (s == null || s.size() == 0) {
            return;
        }


        SelectPostTimeBean selectPostTimeBean = s.get(0);

        String selectDay = selectPostTimeBean.getSelectDay();
        String s1 = selectPostTimeBean.getSelectTimes().get(0);
        mTxtArriveTime.setText(selectDay + " " + s1);

        selectPostTimeBeanList.clear();
        for (int i = 0; i < s.size(); i++) {
            if (i==0){
                String selectDay1 = s.get(i).getSelectDay();
                List<String> selectTimes = s.get(i).getSelectTimes();

                SelectPostTimeBean selectPostTimeBean1 = new SelectPostTimeBean();
                List<SelectPostTimeBean.ItemBean> itemBeansList = new ArrayList<>();
                for (int j = 0; j < selectTimes.size(); j++) {
                    if (j==0) {
                        SelectPostTimeBean.ItemBean itemBean = new SelectPostTimeBean.ItemBean();
                        itemBean.setName(selectTimes.get(i));
                        itemBean.setSelected(true);
                        itemBeansList.add(itemBean);
                    }else {
                        SelectPostTimeBean.ItemBean itemBean = new SelectPostTimeBean.ItemBean();
                        itemBean.setName(selectTimes.get(i));
                        itemBean.setSelected(false);
                        itemBeansList.add(itemBean);
                    }
                }
                selectPostTimeBean1.setItemBeansList(itemBeansList);
                selectPostTimeBean1.setSelected(true);
                selectPostTimeBean1.setSelectDay(selectDay1);
                selectPostTimeBeanList.add(selectPostTimeBean1);
            }else {
                String selectDay1 = s.get(i).getSelectDay();
                List<String> selectTimes = s.get(i).getSelectTimes();
                SelectPostTimeBean selectPostTimeBean1 = new SelectPostTimeBean();
                List<SelectPostTimeBean.ItemBean> itemBeansList = new ArrayList<>();
                for (int j = 0; j < selectTimes.size(); j++) {
                    if (j==0) {
                        SelectPostTimeBean.ItemBean itemBean = new SelectPostTimeBean.ItemBean();
                        itemBean.setName(selectTimes.get(i));
                        itemBean.setSelected(true);
                        itemBeansList.add(itemBean);
                    }else {
                        SelectPostTimeBean.ItemBean itemBean = new SelectPostTimeBean.ItemBean();
                        itemBean.setName(selectTimes.get(i));
                        itemBean.setSelected(false);
                        itemBeansList.add(itemBean);
                    }
                }

                selectPostTimeBean1.setItemBeansList(itemBeansList);
                selectPostTimeBean1.setSelected(false);
                selectPostTimeBean1.setSelectDay(selectDay1);
                selectPostTimeBeanList.add(selectPostTimeBean1);
            }
        }

    }

    //列出支付方式
    @Override
    public void getPayMethod(List<PayMethodBean> s) {
        if (s == null || s.size() == 0) {
            return;
        }

        PayMethodBean payMethodBean = s.get(0);
        mTxtPayMethed.setText(payMethodBean.getName());

        payMethodBeanList.clear();
        payMethodBeanList.addAll(s);
        payPopAdapter.notifyDataSetChanged();
    }

    //获取优惠券信息
    @Override
    public void getDisCount(List<DisCountBean> s) {
        if (s == null || s.size() == 0) {
            mTxtCoupon.setText("无优惠券可用");
            return;
        }
        mTxtCoupon.setText("您有"+s.size()+"张优惠券可用");

        disCountBeanList.clear();
        disCountBeanList.addAll(s);
        discountPopAdapter.notifyDataSetChanged();

    }

    //判断是否含有默认地址信息
    public MyAddressBean getDefaultAddress(List<MyAddressBean> s) {
        for (int i = 0; i < s.size(); i++) {
            if (s.get(i).isDefaultX()) {
                return s.get(i);
            }
        }
        return null;
    }

    //查看popview
    public void showDiscountPop(PopupWindow pw) {
        pw.showAtLocation(mActivity.getWindow().getDecorView(),
                Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 0);
        pw.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                PopupWindowHelper.backgroundAlpha(mActivity, 1f);
            }
        });
        PopupWindowHelper.backgroundAlpha(mActivity, 0.7f);
    }


    //初始化修改优惠券弹框
    private void initDiscountPop() {
        discountPopView = LayoutInflater.from(mActivity).inflate(R.layout.layout_discount_popview, null, false);
        View view_pop = discountPopView.findViewById(R.id.view_pop);
        mRv_discountPop = (RecyclerView) discountPopView.findViewById(R.id.mRv_discountPop);

        discountPopAdapter = new DiscountPopAdapter(mContext,disCountBeanList);
        discountManager = new LinearLayoutManager(mContext);
        mRv_discountPop.setAdapter(discountPopAdapter);
        mRv_discountPop.setLayoutManager(discountManager);
        discountPopAdapter.setOnItemClickListener(new DiscountPopAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int parentPos, int id) {
                discountPopAdapter.selectItem(parentPos);
            }
        });

        discountPopWindow = PopupWindowHelper.createPopupWindow(discountPopView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        discountPopWindow.setAnimationStyle(R.style.slide_up_in_down_out);

        //点击空白地方
        view_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discountPopWindow.dismiss();
            }
        });
    }

    //初始化时间选择弹框
    private void initPostTimePop() {
        postTimePopView = LayoutInflater.from(mActivity).inflate(R.layout.layout_posttime_popview, null, false);
        View view_pop = postTimePopView.findViewById(R.id.view_pop);
        RecyclerView mRv_day = (RecyclerView) postTimePopView.findViewById(R.id.mRv_day);
        RecyclerView mRv_time = (RecyclerView) postTimePopView.findViewById(R.id.mRv_time);
        LinearLayoutManager datManager = new LinearLayoutManager(mContext);
        LinearLayoutManager timeManager = new LinearLayoutManager(mContext);

        final TimePop1Adapter timePop1Adapter = new TimePop1Adapter(mContext,selectPostTimeBeanList);
        final TimePop2Adapter timePop2Adapter = new TimePop2Adapter(mContext,itemBeanList);

        mRv_day.setLayoutManager(datManager);
        mRv_day.setAdapter(timePop1Adapter);

        mRv_time.setLayoutManager(timeManager);
        mRv_time.setAdapter(timePop2Adapter);

        //左侧时间被点击,显示
        timePop1Adapter.setOnItemClickListener(new TimePop1Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, String select) {
                timePop1Adapter.selectItem(pos);
                List<SelectPostTimeBean.ItemBean> itemBeansList = selectPostTimeBeanList.get(pos).getItemBeansList();
                itemBeanList.clear();
                itemBeanList.addAll(itemBeansList);
                timePop2Adapter.notifyDataSetChanged();
            }
        });

        //右侧时间被点击
        timePop2Adapter.setOnItemClickListener(new TimePop2Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, String select) {
                timePop2Adapter.selectItem(pos);
            }
        });

        postTimeWindow = PopupWindowHelper.createPopupWindow(postTimePopView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        postTimeWindow.setAnimationStyle(R.style.slide_up_in_down_out);

        //点击空白地方
        view_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postTimeWindow.dismiss();
            }
        });
    }


    //初始化支付选择弹框
    private void initPayPop() {
        payPopView = LayoutInflater.from(mActivity).inflate(R.layout.layout_pay_popview, null, false);
        View view_pop = payPopView.findViewById(R.id.view_pop);
        mRv_payPop = (RecyclerView) payPopView.findViewById(R.id.mRv_payPop);


        payManager = new LinearLayoutManager(mContext);
        payPopAdapter = new PayPopAdapter(mContext,payMethodBeanList);
        mRv_payPop.setAdapter(payPopAdapter);
        mRv_payPop.setLayoutManager(payManager);
        payPopAdapter.setOnItemClickListener(new PayPopAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int parentPos, int id) {
                payPopAdapter.selectItem(parentPos);
                mTxtPayMethed.setText(payMethodBeanList.get(parentPos).getName());
            }
        });


        payWindow = PopupWindowHelper.createPopupWindow(payPopView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        payWindow.setAnimationStyle(R.style.slide_up_in_down_out);

        //点击空白地方
        view_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payWindow.dismiss();
            }
        });
    }


}


