package com.dodo.marcket.business.shoppingcar.constrant;


import com.dodo.marcket.base.BaseView;
import com.dodo.marcket.bean.DisCountBean;
import com.dodo.marcket.bean.GoToPayBean;
import com.dodo.marcket.bean.MyAddressBean;
import com.dodo.marcket.bean.PayMethodBean;
import com.dodo.marcket.bean.SelectPostTimeBean;
import com.dodo.marcket.bean.params.PayBean;
import com.dodo.marcket.bean.params.PayParamsFatherBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class GoToPayContract {

    public interface View extends BaseView {
        void getMyAddress(List<MyAddressBean> s);

        void getPayMsg(GoToPayBean payBean);

        void getPostTime(List<SelectPostTimeBean> s);

        void getPayMethod(List<PayMethodBean> s);

        void getDisCount(List<DisCountBean> s);
    }

    public interface Presenter {
        void payProducts(List<PayParamsFatherBean> payList);

        void getAddress();

        void getPostTime();

        void getPayMethod();

        void getDisCount(int status);
    }
}
