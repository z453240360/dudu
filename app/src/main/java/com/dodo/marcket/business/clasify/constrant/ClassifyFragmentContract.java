package com.dodo.marcket.business.clasify.constrant;


import com.dodo.marcket.base.BaseView;
import com.dodo.marcket.bean.FirstClassfyBean;
import com.dodo.marcket.bean.GoToPayBean;
import com.dodo.marcket.bean.ProductParmsBean;
import com.dodo.marcket.bean.ShoppingCarBean;
import com.dodo.marcket.bean.params.PayParamsFatherBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class ClassifyFragmentContract {

    public interface View extends BaseView {
        void getFirstKind(List<FirstClassfyBean> firstClassfyBeanList);
        void getProducts(ShoppingCarBean productBeans);
        void updateNum(int qty,boolean b,int pos);
        void getPayMsg(GoToPayBean payBean);
        void clearShoppingCar(boolean b);
        void getCarNum(int num);
    }

    public interface Presenter {
        void getFirstKind();
        void getCarPop();
        void clearShoppingCar();
        void getProducts();
        void getCarNum();
        void payProducts(List<PayParamsFatherBean> payList);
        void updateNum(int quantity, ProductParmsBean productParmsBean, int pos);
    }
}
