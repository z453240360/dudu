package com.dodo.marcket.business.mine.presenter;



import com.dodo.marcket.base.BasePresenter;
import com.dodo.marcket.bean.LoginBean;
import com.dodo.marcket.bean.basebean.PhoneBean;
import com.dodo.marcket.business.mine.activity.LoginActivity;
import com.dodo.marcket.business.mine.constrant.LoginContract;
import com.dodo.marcket.business.mine.constrant.MineFragmentContract;
import com.dodo.marcket.business.mine.fragment.MineFragment;
import com.dodo.marcket.http.utils.APIException;
import com.dodo.marcket.http.utils.ResponseSubscriber;
import com.dodo.marcket.utils.ParamsUtils;


public class LoginPresenter extends BasePresenter<LoginActivity> implements LoginContract.Presenter{


    @Override
    public void getVerificationCode(String mobile) {
        PhoneBean phoneBean = new PhoneBean();
        phoneBean.setStrParam(mobile);
        String name = "token.smssend";
        addSubscription(apiModel.getVerCode(ParamsUtils.getParams(phoneBean,name)), new ResponseSubscriber<LoginBean>(mContext) {

            @Override
            public void apiSuccess(LoginBean s) {
                mView.getVerificationCode();
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }

    @Override
    public void getLogin(String userName, String password) {
        PhoneBean phoneBean = new PhoneBean();
        phoneBean.setMobile(userName);
        phoneBean.setMessageCode(password);

        String name = "token.get";

        addSubscription(apiModel.getLogin(ParamsUtils.getParams(phoneBean,name)), new ResponseSubscriber<LoginBean>(mContext,"sss") {

            @Override
            public void apiSuccess(LoginBean s) {
                mView.getLogin(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }
}
