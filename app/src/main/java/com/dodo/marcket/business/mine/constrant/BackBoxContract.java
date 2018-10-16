package com.dodo.marcket.business.mine.constrant;



import com.dodo.marcket.base.BaseView;
import com.dodo.marcket.bean.BackBoxBean;
import com.dodo.marcket.bean.params.BackBoxParams;
import com.dodo.marcket.bean.params.BackBoxParamsBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class BackBoxContract {

    public interface View extends BaseView {
        void getBackBoxList(List<BackBoxBean> s);
        void getBackResult();
    }

    public interface Presenter {
        void getBackBoxList();
        void getBackOrder(List<BackBoxParams> backBoxList);
    }
}
