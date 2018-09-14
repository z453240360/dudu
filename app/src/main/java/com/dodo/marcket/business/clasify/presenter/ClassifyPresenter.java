package com.dodo.marcket.business.clasify.presenter;



import com.dodo.marcket.base.BasePresenter;
import com.dodo.marcket.bean.FirstClassfyBean;
import com.dodo.marcket.bean.basebean.PhoneBean;
import com.dodo.marcket.business.clasify.activity.ClasifyActivity;
import com.dodo.marcket.business.clasify.constrant.ClassifyContract;
import com.dodo.marcket.business.clasify.constrant.ClassifyFragmentContract;
import com.dodo.marcket.business.clasify.fragment.ClassifyFragment;
import com.dodo.marcket.http.utils.APIException;
import com.dodo.marcket.http.utils.ResponseSubscriber;
import com.dodo.marcket.utils.ParamsUtils;

import java.util.List;


public class ClassifyPresenter extends BasePresenter<ClasifyActivity> implements ClassifyContract.Presenter{


//    /**
//     * 一级列表
//     */
//    @Override
//    public void getFirstKind() {
//        PhoneBean phoneBean = new PhoneBean();
//        String name = "product.getRootProductCategory";
//        addSubscription(apiModel.getFirstClassfyList(ParamsUtils.getParams(phoneBean,name)), new ResponseSubscriber<List<FirstClassfyBean>>(mContext) {
//
//            @Override
//            public void apiSuccess(List<FirstClassfyBean> s) {
//                mView.getFirstKind(s);
//            }
//
//            @Override
//            public void apiError(APIException e) {
//                mView.showErrorMsg(e.getMessage(),e.code);
//            }
//        });
//    }
}
