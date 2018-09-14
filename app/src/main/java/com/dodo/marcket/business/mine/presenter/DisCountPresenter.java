package com.dodo.marcket.business.mine.presenter;



import com.dodo.marcket.base.BasePresenter;
import com.dodo.marcket.bean.DisCountBean;
import com.dodo.marcket.bean.LoginBean;
import com.dodo.marcket.bean.basebean.PhoneBean;
import com.dodo.marcket.business.mine.activity.DisCountActivity;
import com.dodo.marcket.business.mine.activity.MyOrderActivity;
import com.dodo.marcket.business.mine.constrant.DisCountContract;
import com.dodo.marcket.business.mine.constrant.MyOrderContract;
import com.dodo.marcket.http.utils.APIException;
import com.dodo.marcket.http.utils.ResponseSubscriber;
import com.dodo.marcket.utils.ParamsUtils;

import java.util.List;


public class DisCountPresenter extends BasePresenter<DisCountActivity> implements DisCountContract.Presenter{

    @Override
    public void getDisCount(int status) {
        PhoneBean phoneBean = new PhoneBean();
        phoneBean.setStatus(0);
        String name = "member.getAnhao";
        addSubscription(apiModel.getVerCode(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<List<DisCountBean>>(mContext) {

            @Override
            public void apiSuccess(List<DisCountBean> s) {
                mView.getDisCount();
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }

}
