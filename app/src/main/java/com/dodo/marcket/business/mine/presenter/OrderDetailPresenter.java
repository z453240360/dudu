package com.dodo.marcket.business.mine.presenter;


import com.dodo.marcket.base.BasePresenter;
import com.dodo.marcket.bean.AliPayBean;
import com.dodo.marcket.bean.CancelOrderBean;
import com.dodo.marcket.bean.OrderDetailBean;
import com.dodo.marcket.bean.OrderList;
import com.dodo.marcket.bean.basebean.PhoneBean;
import com.dodo.marcket.bean.params.OptionOrderParamsBean;
import com.dodo.marcket.bean.params.PayBean2;
import com.dodo.marcket.business.mine.activity.OrderDetailActivity;
import com.dodo.marcket.business.mine.constrant.OrderDetailContract;
import com.dodo.marcket.http.utils.APIException;
import com.dodo.marcket.http.utils.ResponseSubscriber;
import com.dodo.marcket.utils.ParamsUtils;
import com.google.gson.Gson;

import java.util.List;


public class OrderDetailPresenter extends BasePresenter<OrderDetailActivity> implements OrderDetailContract.Presenter{

    @Override
    public void getOrderDitail(int sn) {
        PhoneBean phoneBean = new PhoneBean();
        phoneBean.setId(sn);
        String name = "order.view";
        addSubscription(apiModel.getOrderDitail(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<OrderDetailBean>(mContext) {

            @Override
            public void apiSuccess(OrderDetailBean s) {
                mView.getOrderDitail(s);
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
        addSubscription(apiModel.cancelOrder(ParamsUtils.getParams(new Gson().toJson(bean),name,mToken)), new ResponseSubscriber<CancelOrderBean>(mContext) {
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
        addSubscription(apiModel.payOrder(ParamsUtils.getParams(new Gson().toJson(bean),name,mToken)), new ResponseSubscriber<AliPayBean>(mContext) {
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
        addSubscription(apiModel.againOrder(ParamsUtils.getParams(new Gson().toJson(payBeans),name,mToken)), new ResponseSubscriber<Boolean>(mContext) {
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
