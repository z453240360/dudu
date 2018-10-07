package com.dodo.marcket.business.clasify.presenter;


import com.dodo.marcket.base.BasePresenter;
import com.dodo.marcket.bean.FirstClassfyBean;
import com.dodo.marcket.bean.GoToPayBean;
import com.dodo.marcket.bean.ProductParmsBean;
import com.dodo.marcket.bean.ShoppingCarBean;
import com.dodo.marcket.bean.basebean.PhoneBean;
import com.dodo.marcket.bean.params.PayBean;
import com.dodo.marcket.bean.params.PayParamsFatherBean;
import com.dodo.marcket.bean.params.UpCarNumBean;
import com.dodo.marcket.business.clasify.constrant.ClassifyFragmentContract;
import com.dodo.marcket.business.clasify.fragment.ClassifyFragment;
import com.dodo.marcket.http.utils.APIException;
import com.dodo.marcket.http.utils.ResponseSubscriber;
import com.dodo.marcket.utils.ParamsUtils;
import com.google.gson.Gson;

import java.util.List;


public class ClassifyFragmentPresenter extends BasePresenter<ClassifyFragment> implements ClassifyFragmentContract.Presenter{


    /**
     * 一级列表
     */
    @Override
    public void getFirstKind() {
        PhoneBean phoneBean = new PhoneBean();
        String name = "product.getRootProductCategory";
        addSubscription(apiModel.getFirstClassfyList(ParamsUtils.getParams(phoneBean,name)), new ResponseSubscriber<List<FirstClassfyBean>>(mContext) {

            @Override
            public void apiSuccess(List<FirstClassfyBean> s) {
                mView.getFirstKind(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }

    /**
     * 获取购物车商品
     */
    @Override
    public void getProducts() {
        PhoneBean phoneBean = new PhoneBean();
        String name = "cart.getProducts";
        addSubscription(apiModel.getProducts(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<ShoppingCarBean>(mContext) {

            @Override
            public void apiSuccess(ShoppingCarBean s) {
                mView.getProducts(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }

    /**
     * 获取购物车数量
     */
    @Override
    public void getCarNum() {
        PhoneBean phoneBean = new PhoneBean();
        String name = "cart.cartCount";
        addSubscription(apiModel.getCarNum(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<Integer>(mContext) {
            @Override
            public void apiSuccess(Integer s) {
                mView.getCarNum(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }


    /**
     * 更新购物车数量
     * @param quantity
     * @param productParmsBean
     */
    @Override
    public void updateNum(final int quantity, ProductParmsBean productParmsBean, final int pos) {

        UpCarNumBean upCarNumBean = new UpCarNumBean();
        upCarNumBean.setQuantity(quantity);
        upCarNumBean.setProductParam(productParmsBean);
        String name = "cart.mergeQty";

        addSubscription(apiModel.mergeQty(ParamsUtils.getParams(new Gson().toJson(upCarNumBean),name,mToken)), new ResponseSubscriber<Boolean>(mContext,"asdasd") {

            @Override
            public void apiSuccess(Boolean s) {
                mView.updateNum(quantity,s,pos);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }

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


    /**
     * 清空购物车
     */
    @Override
    public void clearShoppingCar() {
        PhoneBean phoneBean = new PhoneBean();
        String name = "cart.clear";
        addSubscription(apiModel.clearShoppingCar(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<Boolean>(mContext) {

            @Override
            public void apiSuccess(Boolean s) {
                mView.clearShoppingCar(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }


    //底部购物车弹窗
    @Override
    public void getCarPop() {
//        PhoneBean phoneBean = new PhoneBean();
//        String name = "product.getRootProductCategory";
//        addSubscription(apiModel.getFirstClassfyList(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<List<FirstClassfyBean>>(mContext) {
//
//            @Override
//            public void apiSuccess(List<FirstClassfyBean> s) {
//                mView.getFirstKind(s);
//            }
//
//            @Override
//            public void apiError(APIException e) {
//                mView.showErrorMsg(e.getMessage(),e.code);
//            }
//        });
    }
}
