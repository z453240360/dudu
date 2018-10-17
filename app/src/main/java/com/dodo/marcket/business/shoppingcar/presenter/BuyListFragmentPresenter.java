package com.dodo.marcket.business.shoppingcar.presenter;


import com.dodo.marcket.base.BasePresenter;
import com.dodo.marcket.bean.ProductBean;
import com.dodo.marcket.bean.ProductParmsBean;
import com.dodo.marcket.bean.basebean.PhoneBean;
import com.dodo.marcket.bean.params.ProductDetailParamsBean;
import com.dodo.marcket.bean.params.UpCarNumBean;
import com.dodo.marcket.business.shoppingcar.constrant.BuyListFragmentContract;
import com.dodo.marcket.business.shoppingcar.fragment.BuyListFragment;
import com.dodo.marcket.http.utils.APIException;
import com.dodo.marcket.http.utils.ResponseSubscriber;
import com.dodo.marcket.utils.ParamsUtils;
import com.google.gson.Gson;

import java.util.List;


public class BuyListFragmentPresenter extends BasePresenter<BuyListFragment> implements BuyListFragmentContract.Presenter{


    /**
     * 获取商品列表
     * @param id
     * @param page
     * @param pageSize
     */
    @Override
    public void getProductList(long id, int page, int pageSize,String msg) {
        PhoneBean phoneBean = new PhoneBean();
        phoneBean.setId(id);
        phoneBean.setPageNumber(page);
        phoneBean.setPageSize(pageSize);
        String name = "product.getProductByCategoryId";
        addSubscription(apiModel.getProductByCategoryId(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<List<ProductBean>>(mContext,msg) {

            @Override
            public void apiSuccess(List<ProductBean> s) {
                mView.getProductList(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }

    /**
     * 添加商品
     * @param quantity 数量
     * @param productParmsBean
     */
    @Override
    public void addProduct(int quantity, ProductParmsBean productParmsBean, final int pos) {
        PhoneBean phoneBean = new PhoneBean();
        phoneBean.setQuantity(quantity);
        phoneBean.setProductParmsBean(productParmsBean);
        String name = "cart.addProduct";
        addSubscription(apiModel.addProduct(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<Boolean>(mContext,"asdasd") {

            @Override
            public void apiSuccess(Boolean s) {
                mView.addProduct(s,pos);
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

    //获取商品详情
    @Override
    public void getProductDetailById(long id) {
        PhoneBean phoneBean = new PhoneBean();
        phoneBean.setId(id);
        String name = "product.getProductDetailById";
        addSubscription(apiModel.getProductDetailById(ParamsUtils.getParams(phoneBean,name)), new ResponseSubscriber<ProductBean>(mContext,"获取商品详情") {

            @Override
            public void apiSuccess(ProductBean s) {
                mView.getProductDetailById(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }

    //根据规格获取商品详情
    @Override
    public void getProductBySize(long productId, String specParam) {
        ProductDetailParamsBean phoneBean = new ProductDetailParamsBean();
        phoneBean.setProductId(productId);
        phoneBean.setSpecParam(specParam);
        String name = "product.getProductDetailByProductSpec";
        addSubscription(apiModel.getProductDetailByProductSpec(ParamsUtils.getParams(new Gson().toJson(phoneBean),name)), new ResponseSubscriber<ProductBean>(mContext,"asd") {

            @Override
            public void apiSuccess(ProductBean s) {
                mView.getProductDetailById(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }
}
