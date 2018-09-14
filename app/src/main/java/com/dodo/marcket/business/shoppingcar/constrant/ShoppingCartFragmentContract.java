package com.dodo.marcket.business.shoppingcar.constrant;



import com.dodo.marcket.base.BaseView;
import com.dodo.marcket.bean.ProducHeadBean;
import com.dodo.marcket.bean.ProductBean;
import com.dodo.marcket.bean.ProductParmsBean;
import com.dodo.marcket.bean.ShoppingCarBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class ShoppingCartFragmentContract {

    public interface View extends BaseView {
        void getProducts(ShoppingCarBean productBeans);
        void updateNum(boolean b);
    }

    public interface Presenter {
        void addProduct(int quantity, ProductParmsBean productParmsBean);
        void deleteProduct(int quantity, ProductParmsBean productParmsBean);
        void clearShoppingCar();
        void getProducts();
        void updateNum(int quantity, ProductParmsBean productParmsBean);
    }
}
