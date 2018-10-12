package com.dodo.marcket.business.mine.constrant;


import com.dodo.marcket.base.BaseView;
import com.dodo.marcket.bean.AliPayBean;
import com.dodo.marcket.bean.OrderDetailBean;
import com.dodo.marcket.bean.params.PayBean2;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class OrderDetailContract {

    public interface View extends BaseView {
        void getOrderDitail(OrderDetailBean orderDetailBean);
        void cancelOrder(int id);
        void payOrder(AliPayBean id);
        void againOrder(boolean id);
    }

    public interface Presenter {
        void getOrderDitail(int sn);

        void cancelOrder(int id);
        void payOrder(String sn);
        void againOrder(PayBean2 payParamsBean);
    }
}
