package com.dodo.marcket.business.mine.presenter;



import com.dodo.marcket.base.BasePresenter;
import com.dodo.marcket.bean.DisCountBean;
import com.dodo.marcket.bean.SalesMan;
import com.dodo.marcket.bean.basebean.PhoneBean;
import com.dodo.marcket.business.mine.activity.AddNewAddressActivity;
import com.dodo.marcket.business.mine.activity.SalesManActivity;
import com.dodo.marcket.business.mine.constrant.AddNewAddressContract;
import com.dodo.marcket.business.mine.constrant.SalesManContract;
import com.dodo.marcket.http.utils.APIException;
import com.dodo.marcket.http.utils.ResponseSubscriber;
import com.dodo.marcket.utils.ParamsUtils;

import java.util.List;


public class SalesManPresenter extends BasePresenter<SalesManActivity> implements SalesManContract.Presenter{


    //绑定业务员
    @Override
    public void bindSalesMan(String code) {
        PhoneBean phoneBean = new PhoneBean();
        phoneBean.setCode(code);
        String name = "member.bindSalesMan";
        addSubscription(apiModel.bindSalesMan(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<Boolean>(mContext) {

            @Override
            public void apiSuccess(Boolean s) {
                mView.bindSalesMan(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }

    //解除业务员
    @Override
    public void cancleSalesMan() {
        PhoneBean phoneBean = new PhoneBean();
        String name = "member.cancleSalesMan";
        addSubscription(apiModel.cancleSalesMan(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<Boolean>(mContext) {

            @Override
            public void apiSuccess(Boolean s) {
                mView.cancleSalesMan(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }

    //获取业务员信息
    @Override
    public void getSalesMan() {
        PhoneBean phoneBean = new PhoneBean();
        String name = "member.getSalesMan";
        addSubscription(apiModel.getSalesMan(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<SalesMan>(mContext,"asdasd") {

            @Override
            public void apiSuccess(SalesMan  s) {
                mView.getSalesMan(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }
}
