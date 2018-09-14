package com.dodo.marcket.business.mine.presenter;



import com.dodo.marcket.base.BasePresenter;
import com.dodo.marcket.bean.BackBoxBean;
import com.dodo.marcket.bean.basebean.PhoneBean;
import com.dodo.marcket.business.mine.activity.BackBoxActivity;
import com.dodo.marcket.business.mine.constrant.BackBoxContract;
import com.dodo.marcket.http.utils.APIException;
import com.dodo.marcket.http.utils.ResponseSubscriber;
import com.dodo.marcket.utils.ParamsUtils;

import java.util.List;


public class BackBoxPresenter extends BasePresenter<BackBoxActivity> implements BackBoxContract.Presenter{


    //列出所有可退的筐，按订单分组
    @Override
    public void getBackBoxList() {
        PhoneBean phoneBean = new PhoneBean();
        String name = "order.box.list";
        addSubscription(apiModel.getBackBox(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<List<BackBoxBean>>(mContext) {

            @Override
            public void apiSuccess(List<BackBoxBean> s) {
                mView.getBackBoxList(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }

    //生成退筐单
    @Override
    public void getBackOrder(List<BackBoxBean> backBoxList) {
        PhoneBean phoneBean = new PhoneBean();
        String name = "return.box.create";
        addSubscription(apiModel.getBackOrder(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<List<BackBoxBean>>(mContext) {

            @Override
            public void apiSuccess(List<BackBoxBean> s) {
                mView.getBackBoxList(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }
}
