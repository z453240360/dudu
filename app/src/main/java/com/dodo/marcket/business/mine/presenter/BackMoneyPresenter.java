package com.dodo.marcket.business.mine.presenter;



import com.dodo.marcket.base.BasePresenter;
import com.dodo.marcket.bean.BackBoxBean;
import com.dodo.marcket.bean.MyBackBoxBean;
import com.dodo.marcket.bean.basebean.PhoneBean;
import com.dodo.marcket.business.mine.activity.MyBackBoxActivity;
import com.dodo.marcket.business.mine.constrant.BackMoneyContract;
import com.dodo.marcket.http.utils.APIException;
import com.dodo.marcket.http.utils.ResponseSubscriber;
import com.dodo.marcket.utils.ParamsUtils;

import java.util.List;


public class BackMoneyPresenter extends BasePresenter<MyBackBoxActivity> implements BackMoneyContract.Presenter{

    //列出所有可退的筐，按订单分组
    @Override
    public void getMyBackBoxList(int pageNumber, int pageSize) {
            PhoneBean phoneBean = new PhoneBean();
            phoneBean.setPageNumber(pageNumber);
            phoneBean.setPageSize(pageSize);
            String name = "return.box.list";
            addSubscription(apiModel.getMyBackBoxList(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<MyBackBoxBean>(mContext) {

                @Override
                public void apiSuccess(MyBackBoxBean s) {
                    mView.myBackBoxList(s);
                }

                @Override
                public void apiError(APIException e) {
                    mView.showErrorMsg(e.getMessage(),e.code);
                }
            });

    }
}
