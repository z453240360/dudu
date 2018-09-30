package com.dodo.marcket.business.mine.presenter;



import com.dodo.marcket.base.BasePresenter;
import com.dodo.marcket.bean.OrderList;
import com.dodo.marcket.bean.params.OptionOrderParamsBean;
import com.dodo.marcket.business.mine.activity.CommentOrderActivity;
import com.dodo.marcket.business.mine.constrant.CommentOrderContract;
import com.dodo.marcket.http.utils.APIException;
import com.dodo.marcket.http.utils.ResponseSubscriber;
import com.dodo.marcket.utils.ParamsUtils;
import com.google.gson.Gson;

import java.util.List;


public class CommentOrderPresenter extends BasePresenter<CommentOrderActivity> implements CommentOrderContract.Presenter{

    //评价订单
    @Override
    public void discussOrder(int id) {
        OptionOrderParamsBean bean = new OptionOrderParamsBean();
        bean.setId(id);
        String name = "order.getComment";
        addSubscription(apiModel.getComment(ParamsUtils.getParams(new Gson().toJson(bean),name,mToken)), new ResponseSubscriber<List<OrderList>>(mContext) {
            @Override
            public void apiSuccess(List<OrderList> s) {
                mView.discussOrder(1);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }

}
