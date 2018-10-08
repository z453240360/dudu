package com.dodo.marcket.business.mine.presenter;



import com.dodo.marcket.base.BasePresenter;
import com.dodo.marcket.bean.CommentBean;
import com.dodo.marcket.bean.OrderList;
import com.dodo.marcket.bean.params.CommentParamsBean;
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
        CommentParamsBean paramsBean = new CommentParamsBean();

        String name = "order.getComment";
        addSubscription(apiModel.getComment(ParamsUtils.getParams(new Gson().toJson(paramsBean),name,mToken)), new ResponseSubscriber<List<CommentBean>>(mContext) {
            @Override
            public void apiSuccess(List<CommentBean> s) {
                mView.discussOrder(1);
            }

            @Override
            public void apiError(APIException e) {
                mView.showErrorMsg(e.getMessage(),e.code);
            }
        });
    }

}
