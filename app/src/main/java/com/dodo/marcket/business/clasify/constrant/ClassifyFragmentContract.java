package com.dodo.marcket.business.clasify.constrant;



import com.dodo.marcket.base.BaseView;
import com.dodo.marcket.bean.FirstClassfyBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class ClassifyFragmentContract {

    public interface View extends BaseView {
        void getFirstKind(List<FirstClassfyBean> firstClassfyBeanList);
    }

    public interface Presenter {
        void getFirstKind();
        void getCarPop();
    }
}