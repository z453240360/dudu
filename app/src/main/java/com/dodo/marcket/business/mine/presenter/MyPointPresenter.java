package com.dodo.marcket.business.mine.presenter;



import com.dodo.marcket.base.BasePresenter;
import com.dodo.marcket.bean.BackBoxBean;
import com.dodo.marcket.bean.MyBackBoxBean;
import com.dodo.marcket.bean.MyPointBean;
import com.dodo.marcket.bean.basebean.PhoneBean;
import com.dodo.marcket.bean.params.BackBoxParams;
import com.dodo.marcket.bean.params.BackBoxParamsBean;
import com.dodo.marcket.business.mine.activity.BackBoxActivity;
import com.dodo.marcket.business.mine.activity.MyPointActivity;
import com.dodo.marcket.business.mine.constrant.BackBoxContract;
import com.dodo.marcket.business.mine.constrant.MyPointContract;
import com.dodo.marcket.http.utils.APIException;
import com.dodo.marcket.http.utils.ResponseSubscriber;
import com.dodo.marcket.utils.ParamsUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class MyPointPresenter extends BasePresenter<MyPointActivity> implements MyPointContract.Presenter{

    /**
     * 获取积分列表
     */
    @Override
    public void getMyPoint(int page,int pageSize,String msg) {

        PhoneBean phoneBean = new PhoneBean();
        phoneBean.setPageNumber(page);
        phoneBean.setPageSize(pageSize);
        String name = "member.points";
        addSubscription(apiModel.getMyPointList(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<List<MyPointBean>>(mContext,msg) {

            @Override
            public void apiSuccess(List<MyPointBean> s) {
                mView.getMyPoint(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });





    }
}
