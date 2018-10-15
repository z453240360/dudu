package com.dodo.marcket.business.mine.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseFragment;
import com.dodo.marcket.bean.AliPayBean;
import com.dodo.marcket.bean.AuthResult;
import com.dodo.marcket.bean.CommentBean;
import com.dodo.marcket.bean.OrderDetailBean;
import com.dodo.marcket.bean.OrderItemCommentParamsBean;
import com.dodo.marcket.bean.OrderList;
import com.dodo.marcket.bean.PayResult;
import com.dodo.marcket.bean.params.PayBean2;
import com.dodo.marcket.bean.params.PayParamsBean;
import com.dodo.marcket.bean.params.PayParamsFatherBean;
import com.dodo.marcket.business.HomeActivity;
import com.dodo.marcket.business.mine.activity.CommentOrderActivity;
import com.dodo.marcket.business.mine.activity.OrderDetailActivity;
import com.dodo.marcket.business.mine.adapter.MultiAdapter;
import com.dodo.marcket.business.mine.constrant.OrderFragmentContract;
import com.dodo.marcket.business.mine.presenter.OrderFragmentPresenter;
import com.dodo.marcket.http.constant.Constant;
import com.dodo.marcket.utils.ToastUtils;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.Unbinder;


public class OrderFragment extends BaseFragment<OrderFragmentPresenter> implements OrderFragmentContract.View {


    public static OrderFragment orderFragment;
    @BindView(R.id.mRv_order)
    XRecyclerView mRvOrder;
    Unbinder unbinder;
    @BindView(R.id.mLL_noDate)
    LinearLayout mLLNoDate;
    Unbinder unbinder1;
    private int page = 1;
    private int pageSize = 10;
    private int postion;
    protected boolean isViewInitiated;
    protected boolean isVisibleToUser;
    protected boolean isDataInitiated = false;

    private LinearLayoutManager manager;
    private MultiAdapter adapter;
    private List<OrderList> mDates = new ArrayList<>();

    public static OrderFragment getInstance() {
        if (orderFragment == null)
            orderFragment = new OrderFragment();
        return orderFragment;
    }

    public OrderFragment() {
    }

    @SuppressLint("ValidFragment")
    public OrderFragment(int postion) {
        this.postion = postion;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    public void initPresenter() {
        mPresenter.init(this);
    }

    @Override
    public void loadData() {
        isDataInitiated = true;
        initRv();

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

    public void initFresh(){
        isDataInitiated= true;
    }

    //初始化列表
    private void initRv() {
        adapter = new MultiAdapter(mContext, mDates);
        manager = new LinearLayoutManager(mContext);
        mRvOrder.setAdapter(adapter);
        mRvOrder.setLayoutManager(manager);


        mRvOrder.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRvOrder.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);

        mRvOrder.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                mPresenter.getOrder(postion, page, pageSize,"");
            }

            @Override
            public void onLoadMore() {
                page++;
                mPresenter.getOrder(postion, page, pageSize,"");
            }
        });


        adapter.setOnItemClickListener(new MultiAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, int orderId, String sn) {//订单点击
                initFresh();
                Bundle bundle = new Bundle();
                bundle.putString("sn",sn);
                bundle.putInt("snId",orderId);
                startActivity(OrderDetailActivity.class,bundle);
            }

            @Override
            public void cancelOrder(int id, int postion) {//取消订单
                initFresh();
                mPresenter.cancelOrder(id);
            }

            @Override
            public void payOrder(String sn, int postion) {//支付订单
                initFresh();
                mPresenter.payOrder(sn);
            }

            @Override
            public void disOrder(int id, int postion) {//评价订单
                initFresh();
                Bundle b = new Bundle();
                List<OrderList.OrderItemsBean> orderItems = mDates.get(postion).getOrderItems();
                List<OrderItemCommentParamsBean> commentBeans = new ArrayList<>();
                for (int i = 0; i < orderItems.size(); i++) {
                    OrderList.OrderItemsBean.ProductInfoBean productInfo = orderItems.get(i).getProductInfo();

                    String name = productInfo.getName();
                    int id1 = productInfo.getId();

                    OrderItemCommentParamsBean commentBean = new OrderItemCommentParamsBean();
                    commentBean.setProductName(name);
                    commentBean.setSupport(true);
                    commentBean.setProductId((long) id1);

                    commentBeans.add(commentBean);
                }

                b.putLong("orderId",(long)mDates.get(postion).getId());
                b.putSerializable("list", (Serializable) commentBeans);


                startActivity(CommentOrderActivity.class,b);
            }

            @Override
            public void againOrder(int id, int postion) {//再次购买
                initFresh();
                PayBean2 payBean = new PayBean2();
                List<PayParamsFatherBean> payParamsFatherBeans = new ArrayList<>();

                OrderList orderList = mDates.get(postion);
                List<OrderList.OrderItemsBean> orderItems = orderList.getOrderItems();
                for (int i = 0; i < orderItems.size(); i++) {
                    int qty = orderItems.get(i).getQty();//数量
                    OrderList.OrderItemsBean.ProductInfoBean productInfo = orderItems.get(i).getProductInfo();
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
            }
        });

    }

    //当前页面是否可见
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        getDate();
    }

    //当前是否创建
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        getDate();
    }

    //获取网络数据
    public void getDate() {
        if (isViewInitiated && isVisibleToUser && isDataInitiated) {
            mPresenter.getOrder(postion, page, pageSize,"sd");
        }
    }

    //获取订单列表
    @Override
    public void getOrderList(List<OrderList> orderLists) {

        if (page==1) {
            mRvOrder.refreshComplete();
        }else {
            mRvOrder.loadMoreComplete();
        }

        if (orderLists == null || orderLists.size() == 0) {
            if (page==1){
                mLLNoDate.setVisibility(View.VISIBLE);
                mRvOrder.setVisibility(View.GONE);
            }
            return;
        }
        if (page==1){
            mDates.clear();
        }
        mLLNoDate.setVisibility(View.GONE);
        mRvOrder.setVisibility(View.VISIBLE);
        isDataInitiated = false;
        mDates.addAll(orderLists);
        adapter.notifyDataSetChanged();
    }

    //取消订单
    @Override
    public void cancelOrder(int id) {
        ToastUtils.show(mContext,"取消订单成功");
        page=1;
        mDates.clear();
        mPresenter.getOrder(postion, page, pageSize,"");
    }


    //支付订单
    @Override
    public void payOrder(AliPayBean s) {
        String paymentMethonCode = s.getPaymentMethonCode();
        if (paymentMethonCode.equals("wxpay")) {//吊起微信支付
            wxPay(s);
        } else if (paymentMethonCode.equals("alipay")) {//支付宝支付
            aliPayres(s.getAliPayResult().getOrderInfo());
        }else {
            showErrorToast("货到付款");
            startActivity(HomeActivity.class);
        }
    }

    //再次购买（批量添加购物车）
    @Override
    public void againOrder(boolean id) {
        if (id){
            ToastUtils.show(mContext,"已经为您添加到购物车");
        }else {
            ToastUtils.show(mContext,"下单失败");
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
        final IWXAPI msgApi = WXAPIFactory.createWXAPI(mContext, Constant.APP_ID,true);
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
