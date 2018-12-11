package com.dodo.marcket.business.mine.constrant;



import com.dodo.marcket.base.BaseView;
import com.dodo.marcket.bean.BackBoxBean;
import com.dodo.marcket.bean.MyPointBean;
import com.dodo.marcket.bean.params.BackBoxParams;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class MyPointContract {

    public interface View extends BaseView {
        void getMyPoint(List<MyPointBean> myPoints);
    }

    public interface Presenter {
        void getMyPoint(int page,int pageSize,String msg);
    }
}
