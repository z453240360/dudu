package com.dodo.marcket.business.mine.constrant;



import com.dodo.marcket.base.BaseView;
import com.dodo.marcket.bean.SalesMan;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class CommentOrderContract {

    public interface View extends BaseView {
        void discussOrder(int id);
    }

    public interface Presenter {
        void discussOrder(int id);
    }
}
