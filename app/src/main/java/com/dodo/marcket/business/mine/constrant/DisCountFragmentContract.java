package com.dodo.marcket.business.mine.constrant;



import com.dodo.marcket.base.BaseView;
import com.dodo.marcket.bean.DisCountBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class DisCountFragmentContract {

    public interface View extends BaseView {
        void getDisCount(List<DisCountBean> s);
    }

    public interface Presenter {

        void getDisCount(int status);
    }
}
