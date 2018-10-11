package com.dodo.marcket.business.mine.constrant;



import com.dodo.marcket.base.BaseView;
import com.dodo.marcket.bean.SalesMan;
import com.dodo.marcket.bean.params.CommentParamsBean;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class CommentOrderContract {

    public interface View extends BaseView {
        void discussOrder(int id);
        void getDiscussOrder(CommentParamsBean id);
    }

    public interface Presenter {
        void discussOrder(CommentParamsBean id);
        void getDiscussOrder(long id);
    }
}
