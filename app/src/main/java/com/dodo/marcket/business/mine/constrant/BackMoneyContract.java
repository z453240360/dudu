package com.dodo.marcket.business.mine.constrant;



import com.dodo.marcket.base.BaseView;
import com.dodo.marcket.bean.MyBackBoxBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class BackMoneyContract {

    public interface View extends BaseView {
        void myBackBoxList(MyBackBoxBean backBoxBeanList);
    }

    public interface Presenter {
        void getMyBackBoxList(int pageNumber,int pageSize);
    }
}
