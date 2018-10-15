package com.dodo.marcket.business.clasify.presenter;


import com.dodo.marcket.base.BasePresenter;
import com.dodo.marcket.bean.ProductBean;
import com.dodo.marcket.bean.ProductParmsBean;
import com.dodo.marcket.bean.basebean.PhoneBean;
import com.dodo.marcket.bean.params.ProductDetailParamsBean;
import com.dodo.marcket.business.clasify.activity.SearchActivity;
import com.dodo.marcket.business.clasify.constrant.SearchContract;
import com.dodo.marcket.http.utils.APIException;
import com.dodo.marcket.http.utils.ResponseSubscriber;
import com.dodo.marcket.utils.ParamsUtils;
import com.google.gson.Gson;

import java.util.List;


public class SearchPresenter extends BasePresenter<SearchActivity> implements SearchContract.Presenter{


    @Override
    public void searchProduct(String goodsKeys,int page,int pageSize,String msg) {

        PhoneBean phoneBean = new PhoneBean();
        phoneBean.setKey(goodsKeys);
        phoneBean.setPageNumber(page);
        phoneBean.setPageSize(pageSize);
        String name = "product.search";
        addSubscription(apiModel.searchGoods(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<List<ProductBean>>(mContext,msg) {

            @Override
            public void apiSuccess(List<ProductBean> s) {

                mView.getSearchResult(s);
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
    public void addProduct(int quantity, ProductParmsBean productParmsBean) {
        PhoneBean phoneBean = new PhoneBean();
        phoneBean.setQuantity(quantity);
        phoneBean.setProductParmsBean(productParmsBean);
        String name = "cart.addProduct";
        addSubscription(apiModel.addProduct(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<Boolean>(mContext,"asdasd") {

            @Override
            public void apiSuccess(Boolean s) {
                mView.addProduct(s);
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
