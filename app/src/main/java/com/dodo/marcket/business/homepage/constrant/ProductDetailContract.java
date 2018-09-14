package com.dodo.marcket.business.homepage.constrant;



import com.dodo.marcket.base.BaseView;
import com.dodo.marcket.bean.ProductBean;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class ProductDetailContract {

    public interface View extends BaseView {
        void getProductDetailById(ProductBean productBean);
        void getProductBySize(ProductBean productBean);
    }

    public interface Presenter {
        void getProductDetailById(long id);
        void getProductBySize(long productId,String specParam);
    }
}
