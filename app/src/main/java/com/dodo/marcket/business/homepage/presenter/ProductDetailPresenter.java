package com.dodo.marcket.business.homepage.presenter;



import com.dodo.marcket.base.BasePresenter;
import com.dodo.marcket.bean.ProductBean;
import com.dodo.marcket.bean.basebean.PhoneBean;
import com.dodo.marcket.bean.params.ProductDetailParamsBean;
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
}
