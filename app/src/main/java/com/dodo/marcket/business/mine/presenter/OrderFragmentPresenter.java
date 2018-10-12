package com.dodo.marcket.business.mine.presenter;



import com.dodo.marcket.base.BasePresenter;
import com.dodo.marcket.bean.AliPayBean;
import com.dodo.marcket.bean.CancelOrderBean;
import com.dodo.marcket.bean.OrderList;
import com.dodo.marcket.bean.params.OptionOrderParamsBean;
import com.dodo.marcket.bean.params.OrderParamsBean;
import com.dodo.marcket.bean.params.PayBean2;
import com.dodo.marcket.business.mine.constrant.OrderFragmentContract;
import com.dodo.marcket.business.mine.fragment.OrderFragment;
import com.dodo.marcket.http.utils.APIException;
import com.dodo.marcket.http.utils.ResponseSubscriber;
import com.dodo.marcket.utils.ParamsUtils;
import com.google.gson.Gson;

import java.util.List;


public class OrderFragmentPresenter extends BasePresenter<OrderFragment> implements OrderFragmentContract.Presenter{

    //获取订单列表
    @Override
    public void getOrder(int orderStatus,int page,int pageSize) {
        OrderParamsBean orderParamsBean = new OrderParamsBean();
        orderParamsBean.setPageNumber(page);
        orderParamsBean.setPageSize(pageSize);
        orderParamsBean.setStatus(orderStatus);
        String name = "order.list";
        addSubscription(apiModel.getOrderList(ParamsUtils.getParams(new Gson().toJson(orderParamsBean),name,mToken)), new ResponseSubscriber<List<OrderList>>(mContext,"asd") {
            @Override
            public void apiSuccess(List<OrderList> s) {
                mView.getOrderList(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }

    //取消订单
    @Override
    public void cancelOrder(int id) {
        OptionOrderParamsBean bean = new OptionOrderParamsBean();
        bean.setId(id);
        String name = "order.cancel";
        addSubscription(apiModel.cancelOrder(ParamsUtils.getParams(new Gson().toJson(bean),name,mToken)), new ResponseSubscriber<CancelOrderBean>(mContext,"sd") {
            @Override
            public void apiSuccess(CancelOrderBean s) {
                mView.cancelOrder(1);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }

    //支付订单
    @Override
    public void payOrder(String sn) {
        OptionOrderParamsBean bean = new OptionOrderParamsBean();
        bean.setSn(sn);
        String name = "order.pay";
        addSubscription(apiModel.payOrder(ParamsUtils.getParams(new Gson().toJson(bean),name,mToken)), new ResponseSubscriber<AliPayBean>(mContext,"sd") {
            @Override
            public void apiSuccess(AliPayBean s) {
                mView.payOrder(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }

    //再次购买订单（批量添加购物车）
    @Override
    public void againOrder(PayBean2 payBeans) {
        String name = "cart.addProducts";
        addSubscription(apiModel.againOrder(ParamsUtils.getParams(new Gson().toJson(payBeans),name,mToken)), new ResponseSubscriber<Boolean>(mContext,"asd") {
            @Override
            public void apiSuccess(Boolean s) {
                mView.againOrder(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }
}
