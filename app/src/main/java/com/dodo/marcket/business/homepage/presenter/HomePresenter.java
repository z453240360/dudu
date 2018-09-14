package com.dodo.marcket.business.homepage.presenter;



import com.dodo.marcket.base.BasePresenter;
import com.dodo.marcket.bean.BannerBean;
import com.dodo.marcket.bean.ProductBean;
import com.dodo.marcket.bean.basebean.PhoneBean;
import com.dodo.marcket.business.HomeActivity;
import com.dodo.marcket.business.homepage.constrant.HomeContract;
import com.dodo.marcket.business.homepage.constrant.HomePageContract;
import com.dodo.marcket.http.utils.APIException;
import com.dodo.marcket.http.utils.ResponseSubscriber;
import com.dodo.marcket.utils.ParamsUtils;

import java.util.List;


public class HomePresenter extends BasePresenter<HomeActivity> implements HomeContract.Presenter{

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

    //首页轮播图
    @Override
    public void getBanner(long adPositionId) {
//        PhoneBean phoneBean = new PhoneBean();
//        String name = "cart.cartCount";
//        addSubscription(apiModel.getCarNum(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<List<ProductBean>>(mContext) {
//            @Override
//            public void apiSuccess(List<ProductBean> s) {
//                mView.getCarNum();
//            }
//
//            @Override
//            public void apiError(APIException e) {
//                mView.showErrorMsg(e.getMessage(),e.code);
//            }
//        });

    }
}
