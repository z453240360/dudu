package com.dodo.marcket.business.homepage.presenter;



import com.dodo.marcket.MainActivity;
import com.dodo.marcket.base.BasePresenter;
import com.dodo.marcket.bean.HotBean;
import com.dodo.marcket.bean.ProductBean;
import com.dodo.marcket.bean.ProductParmsBean;
import com.dodo.marcket.bean.basebean.PhoneBean;
import com.dodo.marcket.business.homepage.activity.HotActivity;
import com.dodo.marcket.business.homepage.constrant.HotContract;
import com.dodo.marcket.business.homepage.constrant.MainContract;
import com.dodo.marcket.http.utils.APIException;
import com.dodo.marcket.http.utils.ResponseSubscriber;
import com.dodo.marcket.utils.ParamsUtils;

import java.util.List;

public class MainPresenter extends BasePresenter<MainActivity> implements MainContract.Presenter{
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
}
