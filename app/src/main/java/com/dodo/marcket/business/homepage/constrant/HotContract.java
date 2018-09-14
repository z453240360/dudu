package com.dodo.marcket.business.homepage.constrant;



import com.dodo.marcket.base.BaseView;
import com.dodo.marcket.bean.BannerBean;
import com.dodo.marcket.bean.HotBean;
import com.dodo.marcket.bean.ProductBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class HotContract {

    public interface View extends BaseView {
        void getProduct(HotBean list);
    }

    public interface Presenter {
        void getPromotionDetail(long id);
    }
}
