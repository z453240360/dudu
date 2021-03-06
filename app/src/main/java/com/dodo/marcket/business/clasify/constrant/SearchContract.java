package com.dodo.marcket.business.clasify.constrant;


import com.dodo.marcket.base.BaseView;
import com.dodo.marcket.bean.ProductBean;
import com.dodo.marcket.bean.ProductParmsBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class SearchContract {

    public interface View extends BaseView {
        void addProduct(boolean isAdd);
        void getProductDetailById(ProductBean productBean);
        void getSearchResult(List<ProductBean> productList);
    }

    public interface Presenter {
        void searchProduct(String goodsKeys,int page,int pageSize,String msg);
        void addProduct(int quantity, ProductParmsBean productParmsBean);
        void getProductDetailById(long id);
        void getProductBySize(long productId,String specParam);
    }
}
