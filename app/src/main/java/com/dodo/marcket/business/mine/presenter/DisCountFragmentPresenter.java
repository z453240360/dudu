package com.dodo.marcket.business.mine.presenter;



import com.dodo.marcket.base.BasePresenter;
import com.dodo.marcket.bean.DisCountBean;
import com.dodo.marcket.bean.basebean.PhoneBean;
import com.dodo.marcket.business.mine.constrant.DisCountFragmentContract;
import com.dodo.marcket.business.mine.fragment.DisCountFragment;
import com.dodo.marcket.http.utils.APIException;
import com.dodo.marcket.http.utils.ResponseSubscriber;
import com.dodo.marcket.utils.ParamsUtils;

import java.util.List;


public class DisCountFragmentPresenter extends BasePresenter<DisCountFragment> implements DisCountFragmentContract.Presenter{

    @Override
    public void getDisCount(int status) {
        PhoneBean phoneBean = new PhoneBean();
        phoneBean.setStatus(status);
        String name = "member.getAnhao";
        addSubscription(apiModel.getDisCount(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<List<DisCountBean>>(mContext) {

            @Override
            public void apiSuccess(List<DisCountBean> s) {
                mView.getDisCount(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }

}
