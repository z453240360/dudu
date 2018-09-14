package com.dodo.marcket.business.homepage.constrant;



import com.dodo.marcket.base.BaseView;
import com.dodo.marcket.bean.BannerBean;
import com.youth.banner.Banner;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class HomeContract {

    public interface View extends BaseView {
        void getCarNum(int num);
        void getBanner(List<BannerBean> bannerBeans);
    }

    public interface Presenter {
        void getCarNum();
        void getBanner(long adPositionId);
    }
}
