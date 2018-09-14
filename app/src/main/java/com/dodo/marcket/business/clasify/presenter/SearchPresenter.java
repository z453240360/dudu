package com.dodo.marcket.business.clasify.presenter;



import com.dodo.marcket.base.BasePresenter;
import com.dodo.marcket.bean.DisCountBean;
import com.dodo.marcket.bean.FirstClassfyBean;
import com.dodo.marcket.bean.ProductBean;
import com.dodo.marcket.bean.basebean.PhoneBean;
import com.dodo.marcket.business.clasify.activity.SearchActivity;
import com.dodo.marcket.business.clasify.constrant.ClassifyFragmentContract;
import com.dodo.marcket.business.clasify.constrant.SearchContract;
import com.dodo.marcket.business.clasify.fragment.ClassifyFragment;
import com.dodo.marcket.http.utils.APIException;
import com.dodo.marcket.http.utils.ResponseSubscriber;
import com.dodo.marcket.utils.ParamsUtils;

import java.util.ArrayList;
import java.util.List;


public class SearchPresenter extends BasePresenter<SearchActivity> implements SearchContract.Presenter{


    @Override
    public void searchProduct(String goodsKeys,int page,int pageSize) {

        PhoneBean phoneBean = new PhoneBean();
        phoneBean.setKey(goodsKeys);
        phoneBean.setPageNumber(page);
        phoneBean.setPageSize(pageSize);
        String name = "product.search";
        addSubscription(apiModel.searchGoods(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<List<ProductBean>>(mContext) {

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
}
