package com.dodo.marcket.business.mine.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseActivity;
import com.dodo.marcket.bean.AliPayBean;
import com.dodo.marcket.bean.AuthResult;
import com.dodo.marcket.bean.OrderDetailBean;
import com.dodo.marcket.bean.PayResult;
import com.dodo.marcket.bean.params.PayBean2;
import com.dodo.marcket.bean.params.PayParamsBean;
import com.dodo.marcket.bean.params.PayParamsFatherBean;
import com.dodo.marcket.business.mine.adapter.OrderDetailAdapter;
import com.dodo.marcket.business.mine.constrant.OrderDetailContract;
import com.dodo.marcket.business.mine.presenter.OrderDetailPresenter;
import com.dodo.marcket.http.constant.Constant;
import com.dodo.marcket.utils.ToastUtils;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderDetailActivity extends BaseActivity<OrderDetailPresenter> implements OrderDetailContract.View {


    @BindView(R.id.mTxt_orderDetaiNum)
    TextView mTxtOrderDetaiNum;
    @BindView(R.id.mTxt_orderDetailTime)
    TextView mTxtOrderDetailTime;
    @BindView(R.id.mTxt_orderDetailStatus)
    TextView mTxtOrderDetailStatus;
    @BindView(R.id.mRv_orderDetailList)
    RecyclerView mRvOrderDetailList;
    @BindView(R.id.mLL_orderDrtailPay)
    LinearLayout mLLOrderDrtailPay;
    @BindView(R.id.mTxt_orderCancel)
    TextView mTxtOrderCancel;
    @BindView(R.id.mTxt_real1)
    TextView mTxtReal1;
    @BindView(R.id.mTxt_orderreal1)
    TextView mTxtOrderreal1;
    @BindView(R.id.mTxt_order11)
    TextView mTxtOrder11;
    @BindView(R.id.mTxt_orderCancel1)
    TextView mTxtOrderCancel1;
    @BindView(R.id.mLL_1)
    LinearLayout mLL1;
    @BindView(R.id.mTxt_real2)
    TextView mTxtReal2;
    @BindView(R.id.mTxt_cancel2)
    TextView mTxtCancel2;
    @BindView(R.id.mTxt_orderPay2)
    TextView mTxtOrderPay2;
    @BindView(R.id.mLL_2)
    LinearLayout mLL2;
    @BindView(R.id.mTxt_orderPrice3)
    TextView mTxtOrderPrice3;
    @BindView(R.id.mTxt_orderAgainOrder3)
    TextView mTxtOrderAgainOrder3;
    @BindView(R.id.mTxt_orderDiscuss3)
    TextView mTxtOrderDiscuss3;
    @BindView(R.id.mLL_3)
    LinearLayout mLL3;
    @BindView(R.id.mTxt_orderPrice4)
    TextView mTxtOrderPrice4;
    @BindView(R.id.mTxt_order4)
    TextView mTxtOrder4;
    @BindView(R.id.mTxt_orderPayReal4)
    TextView mTxtOrderPayReal4;
    @BindView(R.id.mLL_4)
    LinearLayout mLL4;
    private String sn;
    private int snId;
    private List<OrderDetailBean.OrderItemsBean> orderItems = new ArrayList<>();
    private OrderDetailAdapter adapter;
    private LinearLayoutManager manager;
    private int id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
    }

    @Override
    public void loadData() {
        Bundle extras = getIntent().getExtras();
        sn = extras.getString("sn");
        snId = extras.getInt("snId");


        initRv();
        mPresenter.getOrderDitail(snId);
    }

    @Override
    protected void onResume() {
        super.onResume();


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

    private void initRv() {
        adapter = new OrderDetailAdapter(mContext, orderItems);
        manager = new LinearLayoutManager(mContext);
        mRvOrderDetailList.setAdapter(adapter);
        mRvOrderDetailList.setLayoutManager(manager);
    }


    //获取订单详情
    @Override
    public void getOrderDitail(OrderDetailBean od) {
        double amount = od.getAmount();//调整金额:正数要向客户收钱,负数要退钱给客户
        String createDate = od.getCreateDate();//订单时间
        String msg = od.getMsg();//保存订单返信息
        String sn = od.getSn();
        String orderStatus = od.getOrderStatus();//订单状态
        double offsetAmount = od.getOffsetAmount();
        int payAmount = od.getPayAmount();

        //"¥ " + payAmount//实付
        //"¥ " + payAmount + offsetAmount) + "");//实收
        //"¥ " + amount);//应付

        mTxtOrderDetaiNum.setText(sn);
        mTxtOrderDetailTime.setText(createDate);


        List<OrderDetailBean.OrderItemsBean> items = od.getOrderItems();
        if (items != null) {
            orderItems.clear();
            orderItems.addAll(items);
            adapter.notifyDataSetChanged();
        } else {
            mRvOrderDetailList.setVisibility(View.GONE);
        }

        if (orderStatus.equals("待付款")) {//查询状态:0=全部,1=待付款,2=待发货,3=配送中,4=已完成,5=已取消
            initBottomView("1", od);
        } else if (orderStatus.equals("待发货")) {
            initBottomView("2", od);
        } else if (orderStatus.equals("配送中")) {
            initBottomView("3", od);
        } else if (orderStatus.equals("已完成")) {
            initBottomView("4", od);
        } else if (orderStatus.equals("已取消")) {
            initBottomView("5", od);
        }

    }

    //取消订单
    @Override
    public void cancelOrder(int id) {
        ToastUtils.show(mContext, "取消订单成功");
    }

    @Override
    public void payOrder(AliPayBean s) {
        String paymentMethonCode = s.getPaymentMethonCode();
        if (paymentMethonCode.equals("wxpay")) {//吊起微信支付
            wxPay(s);
        } else if (paymentMethonCode.equals("alipay")) {//支付宝支付
            aliPayres(s.getAliPayResult().getOrderInfo());
        }else {
            showErrorToast("货到付款");
            finish();
        }
    }

    @Override
    public void againOrder(int id) {

    }

    //更具订单状态初始化底部view
    public void initBottomView(String status, OrderDetailBean od) {
        double amount = od.getAmount();//调整金额:正数要向客户收钱,负数要退钱给客户
        String createDate = od.getCreateDate();//订单时间
        String msg = od.getMsg();//保存订单返信息
        String sn = od.getSn();
        String orderStatus = od.getOrderStatus();//订单状态
        id = od.getId();
        double offsetAmount = od.getOffsetAmount();
        int payAmount = od.getPayAmount();

        switch (status) {
            case "1"://1=待付款
                mLL2.setVisibility(View.VISIBLE);
                mTxtReal2.setText("¥ " + od.getAmount());//合计
                mTxtOrderDetailStatus.setText("待付款");
                break;

            case "2"://2=待发货
                mTxtOrderDetailStatus.setText("待发货");
                mLL1.setVisibility(View.VISIBLE);
                mTxtReal1.setText("¥ " + od.getAmount());
                mTxtOrderreal1.setText("¥ " + (payAmount + offsetAmount));
                mTxtOrder11.setText("¥ " + payAmount);
                break;
            case "3"://3=配送中
                mTxtOrderDetailStatus.setText("配送中");
                mLL1.setVisibility(View.VISIBLE);
                mTxtReal1.setText("¥ " + od.getAmount());
                mTxtOrderreal1.setText("¥ " + (payAmount + offsetAmount));
                mTxtOrder11.setText("¥ " + payAmount);
                break;
            case "4"://4=已完成
                mTxtOrderDetailStatus.setText("已完成");
                mLL3.setVisibility(View.VISIBLE);

                mTxtOrderPrice3.setText("¥ " + payAmount);
                break;
            case "5"://5=已取消
                mTxtOrderDetailStatus.setText("已取消");
                mLL4.setVisibility(View.VISIBLE);
                mTxtOrderPrice4.setText("¥ " + od.getAmount());
                mTxtOrder4.setText("¥ " + (payAmount + offsetAmount));
                mTxtOrderPayReal4.setText("¥ " + payAmount);
                break;
        }
    }


    @OnClick({R.id.mTxt_orderCancel1, R.id.mTxt_cancel2, R.id.mTxt_orderPay2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mTxt_orderCancel1://取消订单
            case R.id.mTxt_cancel2://取消订单
                mPresenter.cancelOrder(id);
                break;
            case R.id.mTxt_orderPay2://马上付款
                mPresenter.payOrder(sn);
                break;

            case R.id.mTxt_orderAgainOrder3://再来一单
            case R.id.mTxt_orderCancel://再来一单

                PayBean2 payBean = new PayBean2();
                List<PayParamsFatherBean> payParamsFatherBeans = new ArrayList<>();

                for (int i = 0; i < orderItems.size(); i++) {
                    int qty = orderItems.get(i).getQty();//数量
                    OrderDetailBean.OrderItemsBean.ProductInfoBean productInfo = orderItems.get(i).getProductInfo();
                    int proId = productInfo.getId();//商品ID
                    PayParamsBean payParamsBean = new PayParamsBean();
                    payParamsBean.setId(proId);
                    PayParamsFatherBean payParamsFatherBean = new PayParamsFatherBean();
                    payParamsFatherBean.setQuantity(qty);
                    payParamsFatherBean.setProductParam(payParamsBean);
                    payParamsFatherBeans.add(payParamsFatherBean);
                }

                payBean.setCartItemParamList(payParamsFatherBeans);
                mPresenter.againOrder(payBean);

                break;
            case R.id.mTxt_orderDiscuss3://评价

                break;


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
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(mActivity, "支付失败", Toast.LENGTH_SHORT).show();
                    }
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

    public void wxPay(AliPayBean aliPayBean){
        AliPayBean.WxPayResult wxPayResult = aliPayBean.getWxPayResult();
        String appId = wxPayResult.getAppId();
        final IWXAPI msgApi = WXAPIFactory.createWXAPI(mContext, Constant.APP_ID,false);
        msgApi.registerApp(appId);
        PayReq request = new PayReq();
        request.appId = appId;
        request.partnerId = wxPayResult.getPartnerId();
        request.prepayId= wxPayResult.getPrepayId();
        request.packageValue =  wxPayResult.getPackageValue();
        request.nonceStr= wxPayResult.getNonceStr();
        request.timeStamp=wxPayResult.getTimeStamp();
        request.sign= wxPayResult.getSign();
        msgApi.sendReq(request);
    }
}
