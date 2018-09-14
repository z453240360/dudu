package com.dodo.marcket.business.shoppingcar.presenter;



import com.dodo.marcket.base.BasePresenter;
import com.dodo.marcket.bean.DisCountBean;
import com.dodo.marcket.bean.FirstClassfyBean;
import com.dodo.marcket.bean.ProductBean;
import com.dodo.marcket.bean.ProductParmsBean;
import com.dodo.marcket.bean.basebean.PhoneBean;
import com.dodo.marcket.business.shoppingcar.constrant.BuyListFragmentContract;
import com.dodo.marcket.business.shoppingcar.constrant.ShoppingCartFragmentContract;
import com.dodo.marcket.business.shoppingcar.fragment.BuyListFragment;
import com.dodo.marcket.business.shoppingcar.fragment.ShoppingCartFragment;
import com.dodo.marcket.http.utils.APIException;
import com.dodo.marcket.http.utils.ResponseSubscriber;
import com.dodo.marcket.utils.ParamsUtils;

import java.util.List;


public class BuyListFragmentPresenter extends BasePresenter<BuyListFragment> implements BuyListFragmentContract.Presenter{


    @Override
    public void getProductList(long id, int page, int pageSize) {
        PhoneBean phoneBean = new PhoneBean();
        phoneBean.setId(id);
        phoneBean.setPageNumber(page);
        phoneBean.setPageSize(pageSize);
        String name = "product.getProductByCategoryId";
        addSubscription(apiModel.getProductByCategoryId(ParamsUtils.getParams(phoneBean,name)), new ResponseSubscriber<List<ProductBean>>(mContext) {

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
    public void addProduct(int quantity, ProductParmsBean productParmsBean) {
        PhoneBean phoneBean = new PhoneBean();
        phoneBean.setQuantity(quantity);
        phoneBean.setProductParmsBean(productParmsBean);
        String name = "cart.addProduct";
        addSubscription(apiModel.addProduct(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<Boolean>(mContext) {

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
}
