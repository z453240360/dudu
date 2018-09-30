package com.dodo.marcket.business.mine.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dodo.marcket.R;
import com.dodo.marcket.base.BaseFragment;
import com.dodo.marcket.bean.OrderList;
import com.dodo.marcket.bean.params.PayBean;
import com.dodo.marcket.bean.params.PayBean2;
import com.dodo.marcket.bean.params.PayParamsBean;
import com.dodo.marcket.bean.params.PayParamsFatherBean;
import com.dodo.marcket.business.mine.activity.CommentOrderActivity;
import com.dodo.marcket.business.mine.activity.OrderDetailActivity;
import com.dodo.marcket.business.mine.adapter.MultiAdapter;
import com.dodo.marcket.business.mine.constrant.OrderFragmentContract;
import com.dodo.marcket.business.mine.presenter.OrderFragmentPresenter;
import com.dodo.marcket.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class OrderFragment extends BaseFragment<OrderFragmentPresenter> implements OrderFragmentContract.View {


    public static OrderFragment orderFragment;
    @BindView(R.id.mRv_order)
    RecyclerView mRvOrder;
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
        showErrorMsg(msg,type);
    }

    //初始化列表
    private void initRv() {
        adapter = new MultiAdapter(mContext, mDates);
        manager = new LinearLayoutManager(mContext);
        mRvOrder.setAdapter(adapter);
        mRvOrder.setLayoutManager(manager);

        adapter.setOnItemClickListener(new MultiAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, int orderId, String sn) {//订单点击
                startActivity(OrderDetailActivity.class);
            }

            @Override
            public void cancelOrder(int id, int postion) {//取消订单
                mPresenter.cancelOrder(id);
            }

            @Override
            public void payOrder(String sn, int postion) {//支付订单
                mPresenter.payOrder(sn);
            }

            @Override
            public void disOrder(int id, int postion) {//评价订单
                startActivity(CommentOrderActivity.class);
            }

            @Override
            public void againOrder(int id, int postion) {//再次购买
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
            mPresenter.getOrder(postion, page, pageSize);
        }
    }

    //获取订单列表
    @Override
    public void getOrderList(List<OrderList> orderLists) {
        if (orderLists == null || orderLists.size() == 0) {
            if (page==1){
                mLLNoDate.setVisibility(View.VISIBLE);
                mRvOrder.setVisibility(View.GONE);
            }
            return;
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
    }


    //支付订单
    @Override
    public void payOrder(int id) {

    }

    //再次购买（批量添加购物车）
    @Override
    public void againOrder(int id) {

    }

}
