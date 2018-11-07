package com.dodo.marcket.business.homepage.constrant;



import com.dodo.marcket.base.BaseView;
import com.dodo.marcket.bean.BannerBean;
import com.dodo.marcket.bean.ProductBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class MainContract {

    public interface View extends BaseView {
        void getProductList(List<ProductBean> list);
    }

    public interface Presenter {
        void getProductList(long id,int page,int pageSize,String msg);
    }
}
