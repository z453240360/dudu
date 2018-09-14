package com.dodo.marcket.business.mine.constrant;



import com.dodo.marcket.base.BaseView;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class DisCountContract {

    public interface View extends BaseView {
        void getDisCount();
    }

    public interface Presenter {

        void getDisCount(int status);
    }
}
