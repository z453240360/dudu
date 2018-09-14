package com.dodo.marcket.business.shoppingcar.presenter;



import com.dodo.marcket.base.BasePresenter;
import com.dodo.marcket.bean.DisCountBean;
import com.dodo.marcket.bean.MyAddressBean;
import com.dodo.marcket.bean.basebean.PhoneBean;
import com.dodo.marcket.business.shoppingcar.activity.GoToPayActivity;
import com.dodo.marcket.business.shoppingcar.constrant.GoToPayContract;
import com.dodo.marcket.business.shoppingcar.constrant.ShoppingCartFragmentContract;
import com.dodo.marcket.business.shoppingcar.fragment.BuyListFragment;
import com.dodo.marcket.http.utils.APIException;
import com.dodo.marcket.http.utils.ResponseSubscriber;
import com.dodo.marcket.utils.ParamsUtils;

import java.util.List;


public class GoToPayPresenter extends BasePresenter<GoToPayActivity> implements GoToPayContract.Presenter{

    /**
     * 结算商品
     */
    @Override
    public void payProducts() {
        PhoneBean phoneBean = new PhoneBean();
        String name = "cart.payProducts";
        addSubscription(apiModel.payProducts(ParamsUtils.getParams(phoneBean,name,mToken)), new ResponseSubscriber<List<DisCountBean>>(mContext) {

            @Override
            public void apiSuccess(List<DisCountBean> s) {
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }

    @Override
    public void getAddress() {
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
}
