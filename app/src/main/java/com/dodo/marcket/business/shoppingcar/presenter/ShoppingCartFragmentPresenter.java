package com.dodo.marcket.business.shoppingcar.presenter;



import com.dodo.marcket.base.BasePresenter;
import com.dodo.marcket.bean.DisCountBean;
import com.dodo.marcket.bean.ProducHeadBean;
import com.dodo.marcket.bean.ProductBean;
import com.dodo.marcket.bean.ProductParmsBean;
import com.dodo.marcket.bean.ShoppingCarBean;
import com.dodo.marcket.bean.basebean.PhoneBean;
import com.dodo.marcket.business.homepage.constrant.HomePageContract;
import com.dodo.marcket.business.homepage.fragment.HomePageFragment;
import com.dodo.marcket.business.mine.constrant.MineFragmentContract;
import com.dodo.marcket.business.mine.fragment.MineFragment;
import com.dodo.marcket.business.shoppingcar.constrant.ShoppingCartFragmentContract;
import com.dodo.marcket.business.shoppingcar.fragment.ShoppingCartFragment;
import com.dodo.marcket.http.utils.APIException;
import com.dodo.marcket.http.utils.ResponseSubscriber;
import com.dodo.marcket.utils.ParamsUtils;

import java.util.List;


public class ShoppingCartFragmentPresenter extends BasePresenter<ShoppingCartFragment> implements ShoppingCartFragmentContract.Presenter{


    /**
     * 添加商品
     * @param quantity 数量
     * @param productParmsBean
     */
    @Override
    public void addProduct(int quantity, ProductParmsBean productParmsBean) {
        PhoneBean phoneBean = new PhoneBean();
        phoneBean.setQuantity(quantity);
        phoneBean.setProductParmsBean(productParmsBean);
        String name = "cart.addProduct";
        addSubscription(apiModel.addProduct(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<List<DisCountBean>>(mContext) {

            @Override
            public void apiSuccess(List<DisCountBean> s) {

            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }

    /**
     * 购物车删除商品
     * @param quantity
     * @param productParmsBean
     */
    @Override
    public void deleteProduct(int quantity, ProductParmsBean productParmsBean) {
        PhoneBean phoneBean = new PhoneBean();
        phoneBean.setQuantity(quantity);
        phoneBean.setProductParmsBean(productParmsBean);
        String name = "cart.delProduct";
        addSubscription(apiModel.delProduct(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<List<DisCountBean>>(mContext) {

            @Override
            public void apiSuccess(List<DisCountBean> s) {
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
        addSubscription(apiModel.clearShoppingCar(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<List<DisCountBean>>(mContext) {

            @Override
            public void apiSuccess(List<DisCountBean> s) {

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
     * 更新购物车数量
     * @param quantity
     * @param productParmsBean
     */
    @Override
    public void updateNum(int quantity, ProductParmsBean productParmsBean) {
        PhoneBean phoneBean = new PhoneBean();
        String name = "cart.mergeQty";
        addSubscription(apiModel.mergeQty(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<Boolean>(mContext) {

            @Override
            public void apiSuccess(Boolean s) {
                mView.updateNum(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }
}
