package com.dodo.marcket.business.mine.presenter;



import com.dodo.marcket.base.BasePresenter;
import com.dodo.marcket.bean.OrderList;
import com.dodo.marcket.business.mine.constrant.MineFragmentContract;
import com.dodo.marcket.business.mine.constrant.OrderFragmentContract;
import com.dodo.marcket.business.mine.fragment.MineFragment;
import com.dodo.marcket.business.mine.fragment.OrderFragment;

import java.util.ArrayList;
import java.util.List;


public class OrderFragmentPresenter extends BasePresenter<OrderFragment> implements OrderFragmentContract.Presenter{

    //获取订单列表
    @Override
    public void getOrder(String orderStatus) {
        List<OrderList> orderLists = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            OrderList orderList = new OrderList();

            orderList.setStatus(i%4+"");
            orderLists.add(orderList);
        }

        mView.getOrderList(orderLists);
    }
}
