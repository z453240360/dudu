package com.dodo.marcket.business.homepage.constrant;



import com.dodo.marcket.base.BaseView;
import com.dodo.marcket.bean.BannerBean;
import com.dodo.marcket.bean.HomePageActivityBean;
import com.dodo.marcket.bean.ProducHeadBean;
import com.dodo.marcket.bean.ProductBean;
import com.dodo.marcket.bean.ProductParmsBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class HomePageContract {

    public interface View extends BaseView {
        void getHotProducts(List<ProductBean> productList,List<ProducHeadBean> type);
        void getAdPosition(List<BannerBean> bannerBeanList);
        void getBanner(List<BannerBean> bannerBeanList);
        void getAllPromotion(List<HomePageActivityBean> activityBeans);
        void addProduct(boolean isAdd);
        void getProductDetailById(ProductBean productBean);
    }

    public interface Presenter {
        void getHotProduct();
        void getAdPosition(long id);
        void getBanner(long id);
        void getAllPromotion();
        void addProduct(int quantity, ProductParmsBean productParmsBean);
        void getProductDetailById(long id);
        void getProductBySize(long productId,String specParam);
    }
}
