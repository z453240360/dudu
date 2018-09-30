package com.dodo.marcket.business.homepage.presenter;



import com.dodo.marcket.base.BasePresenter;
import com.dodo.marcket.bean.ProductBean;
import com.dodo.marcket.bean.ProductParmsBean;
import com.dodo.marcket.bean.basebean.PhoneBean;
import com.dodo.marcket.bean.params.ProductDetailParamsBean;
import com.dodo.marcket.bean.params.UpCarNumBean;
import com.dodo.marcket.business.homepage.activity.ProductDetailActivity;
import com.dodo.marcket.business.homepage.constrant.ProductDetailContract;
import com.dodo.marcket.http.utils.APIException;
import com.dodo.marcket.http.utils.ResponseSubscriber;
import com.dodo.marcket.utils.ParamsUtils;
import com.google.gson.Gson;


public class ProductDetailPresenter extends BasePresenter<ProductDetailActivity> implements ProductDetailContract.Presenter{

    @Override
    public void getProductDetailById(long id) {
        PhoneBean phoneBean = new PhoneBean();
        phoneBean.setId(id);
        String name = "product.getProductDetailById";
        addSubscription(apiModel.getProductDetailById(ParamsUtils.getParams(phoneBean,name)), new ResponseSubscriber<ProductBean>(mContext) {

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
}
