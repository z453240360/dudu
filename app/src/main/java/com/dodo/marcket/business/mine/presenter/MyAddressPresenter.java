package com.dodo.marcket.business.mine.presenter;



import com.dodo.marcket.base.BasePresenter;
import com.dodo.marcket.bean.AreaParamBean;
import com.dodo.marcket.bean.LoginBean;
import com.dodo.marcket.bean.MyAddressBean;
import com.dodo.marcket.bean.basebean.PhoneBean;
import com.dodo.marcket.business.mine.activity.LoginActivity;
import com.dodo.marcket.business.mine.activity.MyAddressActivity;
import com.dodo.marcket.business.mine.constrant.LoginContract;
import com.dodo.marcket.business.mine.constrant.MyAddressContract;
import com.dodo.marcket.http.utils.APIException;
import com.dodo.marcket.http.utils.ResponseSubscriber;
import com.dodo.marcket.utils.ParamsUtils;

import java.util.List;


public class MyAddressPresenter extends BasePresenter<MyAddressActivity> implements MyAddressContract.Presenter{

    //获取会员地址
    @Override
    public void getMyAddress() {
        PhoneBean phoneBean = new PhoneBean();
        String name = "member.getReceiver";
        addSubscription(apiModel.getMyAddress(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<List<MyAddressBean>>(mContext) {

            @Override
            public void apiSuccess(List<MyAddressBean> s) {
                mView.getMyAddress(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }

    //删除会员地址
    @Override
    public void deleteReceiver() {
        PhoneBean phoneBean = new PhoneBean();
        String name = "member.deleteReceiver";
        addSubscription(apiModel.deleteReceiver(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<List<MyAddressBean>>(mContext) {

            @Override
            public void apiSuccess(List<MyAddressBean> s) {
                mView.getMyAddress(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }

    //更新会员地址
    @Override
    public void updateReceiver(long id, String consignee, String phone, String address, boolean isDefault, String province, AreaParamBean areaParam) {
        PhoneBean phoneBean = new PhoneBean();
        String name = "member.updateReceiver";
        addSubscription(apiModel.updateReceiver(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<List<MyAddressBean>>(mContext) {

            @Override
            public void apiSuccess(List<MyAddressBean> s) {
                mView.getMyAddress(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }


}
