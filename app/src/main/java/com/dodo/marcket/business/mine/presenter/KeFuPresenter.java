package com.dodo.marcket.business.mine.presenter;



import com.dodo.marcket.base.BasePresenter;
import com.dodo.marcket.bean.BackBoxBean;
import com.dodo.marcket.bean.KeFuBean;
import com.dodo.marcket.bean.KeFuBeanSend;
import com.dodo.marcket.bean.ProducHeadBean;
import com.dodo.marcket.bean.ProductBean;
import com.dodo.marcket.bean.basebean.PhoneBean;
import com.dodo.marcket.bean.params.BackBoxParams;
import com.dodo.marcket.bean.params.BackBoxParamsBean;
import com.dodo.marcket.business.homepage.activity.KeFuActivity;
import com.dodo.marcket.business.mine.activity.BackBoxActivity;
import com.dodo.marcket.business.mine.constrant.BackBoxContract;
import com.dodo.marcket.business.mine.constrant.KeFuContract;
import com.dodo.marcket.http.utils.APIException;
import com.dodo.marcket.http.utils.ResponseSubscriber;
import com.dodo.marcket.utils.ParamsUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class KeFuPresenter extends BasePresenter<KeFuActivity> implements KeFuContract.Presenter{


    @Override
    public void getKeFuList(int page, int pageSize) {
        PhoneBean phoneBean = new PhoneBean();
        phoneBean.setPageNumber(page);
        phoneBean.setPageSize(pageSize);
        String name = "consult.list";
        addSubscription(apiModel.consult(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<KeFuBean>(mContext) {

            @Override
            public void apiSuccess(KeFuBean s) {
                mView.getKeFuList(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }

    @Override
    public void sendKeFuMsg(String strParam) {
        PhoneBean phoneBean = new PhoneBean();
        phoneBean.setStrParam(strParam);
        String name = "consult.send";
        addSubscription(apiModel.consultSend(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<Object>(mContext) {

            @Override
            public void apiSuccess(Object s) {
                mView.sendResult();
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }
}
