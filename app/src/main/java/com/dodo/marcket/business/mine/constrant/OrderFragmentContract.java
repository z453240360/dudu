package com.dodo.marcket.business.mine.constrant;



import com.dodo.marcket.base.BaseView;
import com.dodo.marcket.bean.OrderList;
import com.dodo.marcket.bean.params.PayBean;
import com.dodo.marcket.bean.params.PayBean2;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class OrderFragmentContract {

    public interface View extends BaseView {
        void getOrderList(List<OrderList> orderLists);
        void cancelOrder(int id);
        void payOrder(int id);
        void againOrder(int id);
    }

    public interface Presenter {

        void getOrder(int orderStatus,int page,int pageSize);
        void cancelOrder(int id);
        void payOrder(String sn);
        void againOrder(PayBean2 payParamsBean);
    }
}
