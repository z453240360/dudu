package com.dodo.marcket.business.homepage.presenter;



import com.dodo.marcket.base.BasePresenter;
import com.dodo.marcket.bean.HotBean;
import com.dodo.marcket.bean.ProductBean;
import com.dodo.marcket.bean.basebean.PhoneBean;
import com.dodo.marcket.business.homepage.activity.HotActivity;
import com.dodo.marcket.business.homepage.constrant.HotContract;
import com.dodo.marcket.http.utils.APIException;
import com.dodo.marcket.http.utils.ResponseSubscriber;
import com.dodo.marcket.utils.ParamsUtils;

import java.util.List;

public class HotPresenter extends BasePresenter<HotActivity> implements HotContract.Presenter{

    @Override
    public void getPromotionDetail(long id) {
        PhoneBean phoneBean = new PhoneBean();
        phoneBean.setId(id);
        String name = "promotion.getPromotionDetail";
        addSubscription(apiModel.getPromotionDetail(ParamsUtils.getParams(phoneBean,name)), new ResponseSubscriber<HotBean>(mContext) {
            @Override
            public void apiSuccess(HotBean s) {
                mView.getProduct(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }
}
