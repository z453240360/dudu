package com.dodo.marcket.business.shoppingcar.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseActivity;
import com.dodo.marcket.base.CustomLinearLayoutManager;
import com.dodo.marcket.bean.AliPayBean;
import com.dodo.marcket.bean.AuthResult;
import com.dodo.marcket.bean.CartItemsBean;
import com.dodo.marcket.bean.DisCountBean;
import com.dodo.marcket.bean.GoToPayBean;
import com.dodo.marcket.bean.MakeOrderBean;
import com.dodo.marcket.bean.MyAddressBean;
import com.dodo.marcket.bean.PayMethodBean;
import com.dodo.marcket.bean.PayResult;
import com.dodo.marcket.bean.SelectPostTimeBean;
import com.dodo.marcket.bean.params.GoToPayParamsBean;
import com.dodo.marcket.bean.params.PayParamsFatherBean;
import com.dodo.marcket.business.clasify.adapter.DiscountPopAdapter;
import com.dodo.marcket.business.clasify.adapter.GiftPopAdapter;
import com.dodo.marcket.business.clasify.adapter.PayPopAdapter;
import com.dodo.marcket.business.clasify.adapter.TimePop1Adapter;
import com.dodo.marcket.business.clasify.adapter.TimePop2Adapter;
import com.dodo.marcket.business.mine.activity.MyAddressActivity;
import com.dodo.marcket.business.shoppingcar.adapter.PayAdapter;
import com.dodo.marcket.business.shoppingcar.constrant.GoToPayContract;
import com.dodo.marcket.business.shoppingcar.presenter.GoToPayPresenter;
import com.dodo.marcket.http.constant.Constant;
import com.dodo.marcket.utils.MathUtils;
import com.dodo.marcket.utils.NumberUtils;
import com.dodo.marcket.utils.photo.PopupWindowHelper;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    @BindView(R.id.mTxt_boxMoney)
    TextView mTxtBoxMoney;
    @BindView(R.id.mTxt_discountMoney)
    TextView mTxtDiscountMoney;
    @BindView(R.id.mTxt_pointMoney)
    TextView mTxtPointMoney;
    @BindView(R.id.mTxt_payOnlineMoney)
    TextView mTxtPayOnlineMoney;
    @BindView(R.id.mTxt_finalMony)
    TextView mTxtFinalMony;
    @BindView(R.id.mEd_say)
    EditText mEdSay;
    @BindView(R.id.mLL_say)
    LinearLayout mLLSay;
    @BindView(R.id.mCheckBox)
    CheckBox mCheckBox;
    @BindView(R.id.mTxt_youHuiMoney)
    TextView mTxtYouHuiMoney;
    @BindView(R.id.mTxt_afterFinalMony)
    TextView mTxtAfterFinalMony;
    @BindView(R.id.mLL_realPrice)
    LinearLayout mLLRealPrice;


    private List<PayParamsFatherBean> payList;
    private View discountPopView;
    private PopupWindow discountPopWindow;
    private PopupWindow postTimeWindow;
    private View postTimePopView;
    private View payPopView;
    private PopupWindow payWindow;
    private List<CartItemsBean> proList = new ArrayList<>();
    private List<PayMethodBean> payMethodBeanList = new ArrayList<>();
    private List<DisCountBean> disCountBeanList = new ArrayList<>();
    private List<SelectPostTimeBean> selectPostTimeBeanList = new ArrayList<>();
    private List<SelectPostTimeBean.ItemBean> itemBeanList = new ArrayList<>();
    private List<GoToPayBean.GiftItemInfosBean> giftList = new ArrayList<>();
    private PayAdapter proAdapter;
    private RecyclerView mRv_discountPop;
    private RecyclerView mRv_payPop;
    private LinearLayoutManager payManager;
    private PayPopAdapter payPopAdapter;
    private DiscountPopAdapter discountPopAdapter;
    private LinearLayoutManager discountManager;
    private OptionsPickerView<Object> pvCustomOptions;
    private GoToPayParamsBean goToPayParamsBean;
    private TimePop1Adapter timePop1Adapter;
    private TimePop2Adapter timePop2Adapter;
    private double productAmount;
    private double userPoint;
    private double pointMoney;
    private double boxAmount;
    private double freight;
    private double onlineDiscount;
    private double onLineMoney;
    private double dicCount = 0;
    private String selectTime;
    private String selectDay;
    private TextView mTxt_arriveTimePop;
    private View giftPopView;
    private ImageView giftCha;
    private PopupWindow giftPopWindow;
    private RecyclerView mRv_giftPop;
    private GiftPopAdapter giftAdapter;
    private double discountAmount;
    private double afterDiscountAmount;
    private double pointRmb;


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
        mTxtAfterFinalMony.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); // 设置中划线并加清晰
        initGoToPayParams(); //初始化支付参数
        initTitle();
        initRv();//初始化商品列表
        initDiscountPop();//初始化优惠券弹框
        initPayPop();//初始化支付弹框
        initPostTimePop();//初始换送达时间弹框
        initGiftPop();//初始化赠品

        //请求数据
        if (payList.size() > 0) {
            mPresenter.payProducts(payList);
//            mPresenter.getAddress();//获取地址信息
            mPresenter.getPostTime();//获取送达时间
            mPresenter.getPayMethod();//获取支付方式
//            mPresenter.getDisCount(0);//获取可用优惠券信息
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
    @OnClick({R.id.mLL_showAddress1, R.id.mLL_showAddress2, R.id.mLL_give, R.id.mLL_coupon, R.id.mLL_point, R.id.ll_update, R.id.mLL_postTime, R.id.mTxt_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mLL_showAddress1://跳转我的地址页面
            case R.id.mLL_showAddress2:
                Bundle bundle = new Bundle();
                bundle.putBoolean("needBackResult", true);
                startActivityForResult(MyAddressActivity.class, bundle, 100);
                break;

            case R.id.mLL_give: //赠品
                if (giftList.size() == 0) {
                    showErrorToast("没有赠品");
                    return;
                }
                showDiscountPop(giftPopWindow);
                break;

            case R.id.mLL_coupon://优惠券选择
                if (disCountBeanList.size() == 0) {
                    showErrorToast("您没有可以使用的优惠券");
                    return;
                }
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

            case R.id.mTxt_pay://去支付

                String trim = mEdSay.getText().toString().trim();
                goToPayParamsBean.setMemo(trim);

                if (checkGoToParams(goToPayParamsBean)) {
                    mPresenter.makeOrderId(goToPayParamsBean);
                }

                break;


        }
    }

    //检查支付参数是否有空
    private boolean checkGoToParams(GoToPayParamsBean params) {

        long receiverId = params.getReceiverId();
        List<GoToPayParamsBean.CartItemParam> cartItemParam = params.getCartItemParam();
        String receiverDate = params.getReceiverDate();
        Long paymentMethodId = params.getPaymentMethodId();
        if (receiverId == 0L) {
            showErrorToast("请填写收货人信息");
            return false;
        }

        if (cartItemParam == null || cartItemParam.size() == 0) {
            showErrorToast("获取商品列表失败");
            return false;
        }

        if (receiverDate.equals("")) {
            showErrorToast("请选择收货时间");
            return false;
        }

        if (paymentMethodId == 0L) {
            showErrorToast("请选择支付方式");
            return false;
        }

        return true;
    }

    //初始化支付参数
    private void initGoToPayParams() {
        goToPayParamsBean = new GoToPayParamsBean();
        payList = (List<PayParamsFatherBean>) getIntent().getSerializableExtra("payList");//获取选中的商品列表
    }

    //初始化标题
    private void initTitle() {
        mTitle.setTitle("去结算");
    }

    //初始化商品列表
    private void initRv() {

        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (userPoint == 0d) {
                        mTxtPointMoney.setText("- ¥ " + 0.0);
//                        showErrorToast("没有可用积分");
                        return;
                    }

                    if (pointRmb<=0){
                        showErrorToast("积分换算比例异常");
                        return;
                    }
                    pointMoney = userPoint / pointRmb;

                    initTotalPrice();

                } else {
                    mTxtPointMoney.setText("- ¥ " + 0.0);
                    pointMoney = 0;
                    goToPayParamsBean.setUsePoint(0d);
                    initTotalPrice();
                }
            }
        });

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

//            MyAddressBean.AreaInfoBean areaInfo = defaultAddress.getAreaInfo();
            mTxtGoToPayAddress.setText(defaultAddress.getProvince() + defaultAddress.getCity() + defaultAddress.getDistrict() + address);
            mTxtGoToPayAddressName.setText(consignee);
            mTxtGoToPayAddressPhone.setText(phone);
        }
    }

    //获取结算页面信息详情
    @Override
    public void getPayMsg(GoToPayBean payBean) {
        if (payBean == null) {
            return;
        }

        //获取地址信息
        MyAddressBean receiverInfo = payBean.getReceiverInfo();
        if (receiverInfo == null) {
            mLLShowAddress1.setVisibility(View.VISIBLE);
            mLLShowAddress2.setVisibility(View.GONE);
//            return;
        } else {
            mLLShowAddress1.setVisibility(View.GONE);
            mLLShowAddress2.setVisibility(View.VISIBLE);
            try {
                String address = receiverInfo.getAddress();
                String consignee = receiverInfo.getConsignee();
                String phone = receiverInfo.getPhone();
//                MyAddressBean.AreaInfoBean areaInfo = receiverInfo.getAreaInfo();
                mTxtGoToPayAddress.setText(receiverInfo.getProvince() + receiverInfo.getCity() + receiverInfo.getDistrict() + address);
                mTxtGoToPayAddressName.setText(consignee);
                mTxtGoToPayAddressPhone.setText(phone);

                goToPayParamsBean.setReceiverId(receiverInfo.getId());//设置地址参数
            } catch (Exception e) {
                mLLShowAddress1.setVisibility(View.VISIBLE);
                mLLShowAddress2.setVisibility(View.GONE);
                goToPayParamsBean.setReceiverId(0L);//设置地址参数
            }
        }

        //获取商品列表信息
        List<CartItemsBean> carts = payBean.getCarts();
        if (carts != null && carts.size() > 0) {
            proList.clear();
            proList.addAll(carts);
            proAdapter.notifyDataSetChanged();
            mLLProList.setVisibility(View.VISIBLE);

            List<GoToPayParamsBean.CartItemParam> proPramsList = new ArrayList<>();
            for (int i = 0; i < proList.size(); i++) {
                CartItemsBean.ProductInfoBean productParam = proList.get(i).getProductInfo();
                long id = productParam.getId();
                int quantity = proList.get(i).getQuantity();

                GoToPayParamsBean.CartItemParam cartItemParam = new GoToPayParamsBean.CartItemParam();
                GoToPayParamsBean.CartItemParam.ProductParam productParam1 = new GoToPayParamsBean.CartItemParam.ProductParam();
                productParam1.setId(id);

                cartItemParam.setProductParam(productParam1);
                cartItemParam.setQuantity(quantity);

                proPramsList.add(cartItemParam);
            }

            goToPayParamsBean.setCartItemParam(proPramsList);//设置商品列表参数

        } else {
            mLLProList.setVisibility(View.GONE);
            goToPayParamsBean.setCartItemParam(null);//设置商品列表参数
        }


        //获取赠品信息
        List<GoToPayBean.GiftItemInfosBean> giftItemInfos = payBean.getGiftItemInfos();
        if (giftItemInfos == null || giftItemInfos.size() == 0) {
            mTxtGive.setText("无赠品");
        } else {
            giftList.clear();
            giftList.addAll(giftItemInfos);
            giftAdapter.notifyDataSetChanged();
//            String name = giftItemInfos.get(0).getName();
//            mTxtGive.setText(name);
//            int id = giftItemInfos.get(0).getId();
//            goToPayParamsBean.setGiftId(id + "");//设置赠品信息
            mTxtGive.setText("您有 " + giftList.size() + " 种赠品可选");

        }


        //获取优惠券信息
        List<DisCountBean> anHaos = payBean.getAnHaos();
        if (anHaos == null || anHaos.size() == 0) {
            mTxtCoupon.setText("无优惠券可用");
        } else {
            mTxtCoupon.setText("您有" + anHaos.size() + "张优惠券可用");
            disCountBeanList.clear();
            disCountBeanList.addAll(anHaos);
            discountPopAdapter.notifyDataSetChanged();
        }


        //筐的金额
        boxAmount = payBean.getBoxAmount();
        //运费
        freight = payBean.getFreight();
        //在线支付金额
        onlineDiscount = payBean.getOnlineDiscount();
        //总价格
        productAmount = payBean.getProductAmount();

        //折扣金额
        discountAmount = payBean.getDiscountAmount();

        //折后金额
        afterDiscountAmount = payBean.getAfterDiscountAmount();

        //用户积分
        userPoint = payBean.getUserPoint();

        if(userPoint<=0){
            userPoint = 0d;
        }

        //积分换算比例
        pointRmb = payBean.getPointRmb();

        if (userPoint <= 0D) {
            mCheckBox.setChecked(false);
            mCheckBox.setFocusable(false);
        } else {
            mCheckBox.setChecked(false);
            mCheckBox.setFocusable(true);
        }

        if (mCheckBox.isChecked()) {
            if (pointRmb == 0d) {
                pointMoney = 0;
            } else {
                pointMoney = userPoint / pointRmb;
            }
        } else {
            pointMoney = 0;
        }

        onLineMoney = onlineDiscount;
        mTxtPoint.setText("" + (int) userPoint + " 积分");
        mTxtRealPay.setText("¥" + productAmount + "");
        mTxtPostMoney.setText("+ ¥ " + freight);
        mTxtPayOnlineMoney.setText("- ¥ " + onlineDiscount);
        mTxtBoxMoney.setText("+ ¥ " + boxAmount);
        mTxtPointMoney.setText("- ¥ " + pointMoney);
        mTxtDiscountMoney.setText("- ¥ " + 0);
        mTxtYouHuiMoney.setText("- ¥ " + discountAmount);
        mTxtAfterFinalMony.setText("¥" + productAmount);


        initTotalPrice();
//        mTxtFinalMony.setText("¥" + (productAmount + boxAmount + freight - onlineDiscount - userPoint / 100) + "");

    }

    //列出送达时间
    @Override
    public void getPostTime(List<SelectPostTimeBean> s) {
        if (s == null || s.size() == 0) {
            return;
        }


        SelectPostTimeBean selectPostTimeBean = s.get(0);

        selectDay = selectPostTimeBean.getSelectDay();
        String s1 = selectPostTimeBean.getSelectTimes().get(0);
        mTxtArriveTime.setText(selectDay + " " + s1);

        goToPayParamsBean.setReceiverDate(selectDay + " " + s1);//设置收货时间


        selectPostTimeBeanList.clear();


        selectPostTimeBeanList.addAll(s);
        selectPostTimeBeanList.get(0).setSelected(true);
        timePop1Adapter.notifyDataSetChanged();

        itemBeanList.clear();
        List<String> selectTimes = selectPostTimeBeanList.get(0).getSelectTimes();

        List<SelectPostTimeBean.ItemBean> mItemList = new ArrayList<>();
        for (int i = 0; i < selectTimes.size(); i++) {
            SelectPostTimeBean.ItemBean itemBean = new SelectPostTimeBean.ItemBean();
            itemBean.setName(selectTimes.get(i));
            itemBean.setSelected(false);
            mItemList.add(itemBean);
        }

        itemBeanList.addAll(mItemList);
        itemBeanList.get(0).setSelected(true);
        timePop2Adapter.notifyDataSetChanged();

        selectDay = selectPostTimeBeanList.get(0).getSelectDay();
        selectTime = itemBeanList.get(0).getName();

        mTxtArriveTime.setText(selectDay + " " + selectTime);
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

        goToPayParamsBean.setPaymentMethodId(payMethodBean.getId());//设置支付参数


    }

    //获取优惠券信息
    @Override
    public void getDisCount(List<DisCountBean> s) {
        if (s == null || s.size() == 0) {
            mTxtCoupon.setText("无优惠券可用");
            return;
        }
        mTxtCoupon.setText("您有" + s.size() + "张优惠券可用");

        disCountBeanList.clear();
        disCountBeanList.addAll(s);
        discountPopAdapter.notifyDataSetChanged();

    }

    //生成订单信息
    @Override
    public void makeOrderId(MakeOrderBean s) {
        if (s != null) {
            String sn = s.getSn();
            if (sn == null || sn.equals("")) {

                String msg = s.getMsg();
                showErrorToast(msg);
                return;
            }
            mPresenter.payOrder(sn);
        }
    }

    //获取支付信息
    @Override
    public void payOrder(AliPayBean s) {
        String paymentMethonCode = s.getPaymentMethonCode();
        if (paymentMethonCode.equals("wxpay")) {//吊起微信支付
            wxPay(s);
        } else if (paymentMethonCode.equals("alipay")) {//支付宝支付
            aliPayres(s.getAliPayResult().getOrderInfo());
        } else {
            finish();
        }
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
        ImageView mImg_chaPay = (ImageView) discountPopView.findViewById(R.id.mImg_chaPay);

        mImg_chaPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discountPopWindow.dismiss();
            }
        });
        discountPopAdapter = new DiscountPopAdapter(mContext, disCountBeanList);
        discountManager = new LinearLayoutManager(mContext);
        mRv_discountPop.setAdapter(discountPopAdapter);
        mRv_discountPop.setLayoutManager(discountManager);
        discountPopAdapter.setOnItemClickListener(new DiscountPopAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int parentPos, int id) {
                discountPopAdapter.selectItem(parentPos);
                DisCountBean disCountBean = disCountBeanList.get(parentPos);
                List<DisCountBean> disCountBeans = new ArrayList<>();
                disCountBeans.add(disCountBean);

                dicCount = disCountBean.getAmount();
                double lowLimit = disCountBean.getLowLimit();

                if (lowLimit < productAmount) {//满足条件可以使用优惠券

                    List<String> mList = new ArrayList<>();
                    for (int i = 0; i < disCountBeans.size(); i++) {
                        mList.add(disCountBeans.get(i).getAnhao());
                    }
                    goToPayParamsBean.setAnHaos(mList);//设置优惠券
                    mTxtCoupon.setText("满" + disCountBean.getLowLimit() + "减" + disCountBean.getAmount());
                    mTxtDiscountMoney.setText("- ¥ " + dicCount);
                    initTotalPrice();

                } else {
                    showErrorToast("不满足使用条件");
                }

                discountPopWindow.dismiss();
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

    //初始化赠品弹框
    private void initGiftPop() {
        giftPopView = LayoutInflater.from(mActivity).inflate(R.layout.layout_gift_popview, null, false);
        View view_pop = giftPopView.findViewById(R.id.view_pop);
        giftCha = giftPopView.findViewById(R.id.mImg_chaPay);
        mRv_giftPop = giftPopView.findViewById(R.id.mRv_giftPop);

        giftCha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                giftPopWindow.dismiss();
            }
        });

        LinearLayoutManager giftManager = new LinearLayoutManager(mContext);
        giftAdapter = new GiftPopAdapter(mContext, giftList);
        mRv_giftPop.setAdapter(giftAdapter);
        mRv_giftPop.setLayoutManager(giftManager);
        giftAdapter.setOnItemClickListener(new GiftPopAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, int id) {
                giftList.get(pos).setSelect(true);
                giftAdapter.notifyDataSetChanged();

                String name = giftList.get(pos).getName();
                mTxtGive.setText(name);
                int giftId = giftList.get(pos).getId();
                goToPayParamsBean.setGiftId(giftId + "");//设置赠品信息

                giftPopWindow.dismiss();
            }
        });

        giftPopWindow = PopupWindowHelper.createPopupWindow(giftPopView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        giftPopWindow.setAnimationStyle(R.style.slide_up_in_down_out);

        //点击空白地方
        view_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                giftPopWindow.dismiss();
            }
        });
    }

    //计算总价格(用户积分抵扣费用不能超过总价格)
    private void initTotalPrice() {

        double totalPrice = afterDiscountAmount + boxAmount + freight - onLineMoney - dicCount;//除了积分以外的总价格

        if (totalPrice <= pointMoney) {
            pointMoney = totalPrice;
            mTxtPointMoney.setText("- ¥ " + MathUtils.round(pointMoney,2));
            goToPayParamsBean.setUsePoint(pointMoney * pointRmb);

//            mTxtPoint.setText("(" + (userPoint + "-" + pointMoney * 100) + "） 积分");
        } else {
            goToPayParamsBean.setUsePoint(pointMoney * pointRmb);
            mTxtPointMoney.setText("- ¥ " + MathUtils.round(pointMoney,2));
//            mTxtPoint.setText("(" + (userPoint  +"-" + pointMoney * 100) + "） 积分");
        }

//        if (userPoint == 0d) {
//            mTxtPoint.setText(userPoint + " 积分");
//        }
        double v = afterDiscountAmount + boxAmount + freight - onLineMoney - pointMoney - dicCount;
        if (v == productAmount) {
            mLLRealPrice.setVisibility(View.GONE);
            mTxtFinalMony.setText("¥ " + MathUtils.round(v, 2) + "");
        } else {
            mLLRealPrice.setVisibility(View.GONE);
            mTxtFinalMony.setText("¥ " + MathUtils.round(v, 2) + "");
        }
        mTxtFinalMony.setText("¥ " + MathUtils.round(v, 2) + "");
    }

    //初始化时间选择弹框
    private void initPostTimePop() {
        postTimePopView = LayoutInflater.from(mActivity).inflate(R.layout.layout_posttime_popview, null, false);
        View view_pop = postTimePopView.findViewById(R.id.view_pop);
        RecyclerView mRv_day = (RecyclerView) postTimePopView.findViewById(R.id.mRv_day);
        RecyclerView mRv_time = (RecyclerView) postTimePopView.findViewById(R.id.mRv_time);
        ImageView mImg_chaPay = (ImageView) postTimePopView.findViewById(R.id.mImg_chaPay);
        mTxt_arriveTimePop = (TextView) postTimePopView.findViewById(R.id.mTxt_arriveTimePop);

        mTxt_arriveTimePop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTxtArriveTime.setText(selectDay + " " + selectTime);
                goToPayParamsBean.setReceiverDate(selectDay + " " + selectTime);
                postTimeWindow.dismiss();
            }
        });

        mImg_chaPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postTimeWindow.dismiss();
            }
        });


        LinearLayoutManager datManager = new LinearLayoutManager(mContext);
        LinearLayoutManager timeManager = new LinearLayoutManager(mContext);

        timePop1Adapter = new TimePop1Adapter(mContext, selectPostTimeBeanList);
        timePop2Adapter = new TimePop2Adapter(mContext, itemBeanList);

        mRv_day.setLayoutManager(datManager);
        mRv_day.setAdapter(timePop1Adapter);

        mRv_time.setLayoutManager(timeManager);
        mRv_time.setAdapter(timePop2Adapter);

        //左侧时间被点击,显示
        timePop1Adapter.setOnItemClickListener(new TimePop1Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, String select) {
                timePop1Adapter.selectItem(pos);
                selectDay = selectPostTimeBeanList.get(pos).getSelectDay();
                List<String> itemBeansList = selectPostTimeBeanList.get(pos).getSelectTimes();

                List<SelectPostTimeBean.ItemBean> mItemList = new ArrayList<>();
                for (int i = 0; i < itemBeansList.size(); i++) {
                    SelectPostTimeBean.ItemBean itemBean = new SelectPostTimeBean.ItemBean();
                    itemBean.setName(itemBeansList.get(i));
                    itemBean.setSelected(false);
                    mItemList.add(itemBean);
                }
                itemBeanList.clear();
                itemBeanList.addAll(mItemList);
                itemBeanList.get(0).setSelected(true);
                timePop2Adapter.notifyDataSetChanged();
            }
        });

        //右侧时间被点击
        timePop2Adapter.setOnItemClickListener(new TimePop2Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, String select) {
                timePop2Adapter.selectItem(pos);
                selectTime = select;
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
        ImageView mImg_chaPay = (ImageView) payPopView.findViewById(R.id.mImg_chaPay);

        mImg_chaPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payWindow.dismiss();
            }
        });

        payManager = new LinearLayoutManager(mContext);
        payPopAdapter = new PayPopAdapter(mContext, payMethodBeanList);
        mRv_payPop.setAdapter(payPopAdapter);
        mRv_payPop.setLayoutManager(payManager);
        payPopAdapter.setOnItemClickListener(new PayPopAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int parentPos, long id) {
                payPopAdapter.selectItem(parentPos);


                mTxtPayMethed.setText(payMethodBeanList.get(parentPos).getName());

                goToPayParamsBean.setPaymentMethodId(payMethodBeanList.get(parentPos).getId());//设置支付方式id

                if (payMethodBeanList.get(parentPos).getId() == 3) {
                    onLineMoney = 0;
                    mTxtPayOnlineMoney.setText("- ¥ 0");
                } else {
                    onLineMoney = onlineDiscount;
                    mTxtPayOnlineMoney.setText("- ¥ " + onlineDiscount);
                }

                initTotalPrice();
                payWindow.dismiss();
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

    //接收页面跳转返回值
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 100) {
            switch (requestCode) {
                case 100:
                    MyAddressBean receiverInfo = (MyAddressBean) data.getSerializableExtra("address");

                    if (receiverInfo == null) {
                        return;
                    }
                    mLLShowAddress1.setVisibility(View.GONE);
                    mLLShowAddress2.setVisibility(View.VISIBLE);
                    try {
                        String address = receiverInfo.getAddress();
                        String consignee = receiverInfo.getConsignee();
                        String phone = receiverInfo.getPhone();
//                        MyAddressBean.AreaInfoBean areaInfo = receiverInfo.getAreaInfo();
                        mTxtGoToPayAddress.setText(receiverInfo.getProvince() + receiverInfo.getCity() + receiverInfo.getDistrict() + address);
                        mTxtGoToPayAddressName.setText(consignee);
                        mTxtGoToPayAddressPhone.setText(phone);

                        goToPayParamsBean.setReceiverId(receiverInfo.getId());//设置地址参数
                    } catch (Exception e) {
                        mLLShowAddress1.setVisibility(View.VISIBLE);
                        mLLShowAddress2.setVisibility(View.GONE);
                        goToPayParamsBean.setReceiverId(0L);//设置地址参数
                    }

                    break;
            }
        }
    }


    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /**
     * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "";
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(mActivity, "支付成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(mActivity, "支付失败", Toast.LENGTH_SHORT).show();
                    }

                    finish();
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(mActivity,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(mActivity,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    public void aliPayres(final String orderInfo) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(mActivity);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    public void wxPay(AliPayBean aliPayBean) {
        AliPayBean.WxPayResult wxPayResult = aliPayBean.getWxPayResult();
        String appId = wxPayResult.getAppId();
        final IWXAPI msgApi = WXAPIFactory.createWXAPI(mContext, Constant.APP_ID, false);
        msgApi.registerApp(appId);
        PayReq request = new PayReq();
        request.appId = appId;
        request.partnerId = wxPayResult.getPartnerId();
        request.prepayId = wxPayResult.getPrepayId();
        request.packageValue = wxPayResult.getPackageValue();
        request.nonceStr = wxPayResult.getNonceStr();
        request.timeStamp = wxPayResult.getTimeStamp();
        request.sign = wxPayResult.getSign();
        msgApi.sendReq(request);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}


