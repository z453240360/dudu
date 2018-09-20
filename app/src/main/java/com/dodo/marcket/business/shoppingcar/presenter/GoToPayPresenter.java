package com.dodo.marcket.business.shoppingcar.presenter;



import com.dodo.marcket.base.BasePresenter;
import com.dodo.marcket.bean.AliPayBean;
import com.dodo.marcket.bean.DisCountBean;
import com.dodo.marcket.bean.GoToPayBean;
import com.dodo.marcket.bean.MakeOrderBean;
import com.dodo.marcket.bean.MyAddressBean;
import com.dodo.marcket.bean.PayMethodBean;
import com.dodo.marcket.bean.SelectPostTimeBean;
import com.dodo.marcket.bean.basebean.PhoneBean;
import com.dodo.marcket.bean.params.GoToPayParamsBean;
import com.dodo.marcket.bean.params.PayBean;
import com.dodo.marcket.bean.params.PayOrderParamsBean;
import com.dodo.marcket.bean.params.PayParamsFatherBean;
import com.dodo.marcket.business.shoppingcar.activity.GoToPayActivity;
import com.dodo.marcket.business.shoppingcar.constrant.GoToPayContract;
import com.dodo.marcket.business.shoppingcar.constrant.ShoppingCartFragmentContract;
import com.dodo.marcket.business.shoppingcar.fragment.BuyListFragment;
import com.dodo.marcket.http.utils.APIException;
import com.dodo.marcket.http.utils.ResponseSubscriber;
import com.dodo.marcket.utils.ParamsUtils;
import com.google.gson.Gson;

import java.util.List;


public class GoToPayPresenter extends BasePresenter<GoToPayActivity> implements GoToPayContract.Presenter{

    /**
     * 结算商品
     */
    @Override
    public void payProducts(List<PayParamsFatherBean> payList) {
        PayBean phoneBean = new PayBean();
        String name = "cart.payProducts";

        phoneBean.setCartItemParamList(payList);
        addSubscription(apiModel.payProducts(ParamsUtils.getParams(new Gson().toJson(phoneBean),name,mToken)), new ResponseSubscriber<GoToPayBean>(mContext) {

            @Override
            public void apiSuccess(GoToPayBean s) {
                mView.getPayMsg(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }

    //获取地址信息
    @Override
    public void getAddress() {
        PhoneBean phoneBean = new PhoneBean();
        String name = "member.getReceiver";
        addSubscription(apiModel.getMyAddress(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<List<MyAddressBean>>(mContext) {

            @Override
            public void apiSuccess(List<MyAddressBean> s) {
                mView.getMyAddress(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }


    //获取送达时间
    @Override
    public void getPostTime() {
        PhoneBean phoneBean = new PhoneBean();
        String name = "cart.list.deliverytimes";
        addSubscription(apiModel.deliverytimes(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<List<SelectPostTimeBean>>(mContext,"asd") {

            @Override
            public void apiSuccess(List<SelectPostTimeBean> s) {
                mView.getPostTime(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }

    //获取支付方式列表
    @Override
    public void getPayMethod() {
        PhoneBean phoneBean = new PhoneBean();
        String name = "cart.list.payfunctions";
        addSubscription(apiModel.payfunctions(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<List<PayMethodBean>>(mContext,"asd") {

            @Override
            public void apiSuccess(List<PayMethodBean> s) {
                mView.getPayMethod(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }

    //获取优惠券信息
    @Override
    public void getDisCount(int status) {
        PhoneBean phoneBean = new PhoneBean();
        phoneBean.setStatus(status);
        String name = "member.getAnhao";
        addSubscription(apiModel.getVerCode(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<List<DisCountBean>>(mContext) {

            @Override
            public void apiSuccess(List<DisCountBean> s) {
                mView.getDisCount(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }

    //传递参数生成订单信息
    @Override
    public void makeOrderId(GoToPayParamsBean goToPayParamsBean) {
        String name = "order.save";
        addSubscription(apiModel.makeOrderId(ParamsUtils.getParams(new Gson().toJson(goToPayParamsBean),name,mToken)), new ResponseSubscriber<MakeOrderBean>(mContext) {

            @Override
            public void apiSuccess(MakeOrderBean s) {
                mView.makeOrderId(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }

    @Override
    public void payOrder(String sn) {
        PayOrderParamsBean payOrderParamsBean = new PayOrderParamsBean();
        payOrderParamsBean.setOrderSN(sn);
        String name = "order.pay";
        addSubscription(apiModel.payOrder(ParamsUtils.getParams(new Gson().toJson(payOrderParamsBean),name,mToken)), new ResponseSubscriber<AliPayBean>(mContext) {

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
}
