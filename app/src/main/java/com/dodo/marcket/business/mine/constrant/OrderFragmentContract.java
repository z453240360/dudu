package com.dodo.marcket.business.mine.constrant;



import com.dodo.marcket.base.BaseView;
import com.dodo.marcket.bean.OrderList;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class OrderFragmentContract {

    public interface View extends BaseView {
        void getOrderList(List<OrderList> orderLists);
    }

    public interface Presenter {

        void getOrder(String orderStatus);
    }
}
