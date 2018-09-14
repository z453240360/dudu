package com.dodo.marcket.business.mine.presenter;



import com.dodo.marcket.base.BasePresenter;
import com.dodo.marcket.bean.basebean.PhoneBean;
import com.dodo.marcket.bean.basebean.UserInfoBean;
import com.dodo.marcket.business.mine.constrant.MineFragmentContract;
import com.dodo.marcket.business.mine.fragment.MineFragment;
import com.dodo.marcket.http.utils.APIException;
import com.dodo.marcket.http.utils.ResponseSubscriber;
import com.dodo.marcket.utils.ParamsUtils;

public class MineFragmentPresenter extends BasePresenter<MineFragment> implements MineFragmentContract.Presenter{

    //获取个人信息
    @Override
    public void getUserMsg() {
        PhoneBean phoneBean = new PhoneBean();
        String name = "member.get";
        addSubscription(apiModel.getUserMsg(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<UserInfoBean>(mContext) {

            @Override
            public void apiSuccess(UserInfoBean s) {
                mView.getUserMsg(s);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }
}
