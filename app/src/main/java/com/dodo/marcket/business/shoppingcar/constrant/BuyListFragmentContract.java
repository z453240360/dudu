package com.dodo.marcket.business.shoppingcar.constrant;



import com.dodo.marcket.base.BaseView;
import com.dodo.marcket.bean.ProductBean;
import com.dodo.marcket.bean.ProductParmsBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class BuyListFragmentContract {

    public interface View extends BaseView {
        void getProductList(List<ProductBean> list);
        void addProduct(boolean isAdd,int pos);
        void getProductDetailById(ProductBean productBean);
        void updateNum(int qty,boolean b,int pos);
    }

    public interface Presenter {
        void getProductList(long id,int page,int pageSize,String msg);
        void addProduct(int quantity, ProductParmsBean productParmsBean,int pos);
        void getProductDetailById(long id);
        void getProductBySize(long productId,String specParam);
        void updateNum(int quantity, ProductParmsBean productParmsBean,int pos);
    }
}
