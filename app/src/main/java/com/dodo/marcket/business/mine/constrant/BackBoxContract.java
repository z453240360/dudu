package com.dodo.marcket.business.mine.constrant;



import com.dodo.marcket.base.BaseView;
import com.dodo.marcket.bean.BackBoxBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class BackBoxContract {

    public interface View extends BaseView {
        void getBackBoxList(List<BackBoxBean> s);
    }

    public interface Presenter {
        void getBackBoxList();
        void getBackOrder(List<BackBoxBean> backBoxList);
    }
}
