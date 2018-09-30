package com.dodo.marcket.business.homepage.constrant;



import com.dodo.marcket.base.BaseView;
import com.dodo.marcket.bean.BannerBean;
import com.dodo.marcket.bean.HotBean;
import com.dodo.marcket.bean.ProductBean;
import com.dodo.marcket.bean.ProductParmsBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class HotContract {

    public interface View extends BaseView {
        void getProduct(HotBean list);
        void addProduct(boolean isAdd);
        void getProductDetailById(ProductBean productBean);
    }

    public interface Presenter {
        void getPromotionDetail(long id);
        void addProduct(int quantity, ProductParmsBean productParmsBean);
        void getProductDetailById(long id);
    }
}
