package com.dodo.marcket.business.mine.constrant;



import com.dodo.marcket.base.BaseView;
import com.dodo.marcket.bean.SalesMan;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class SalesManContract {

    public interface View extends BaseView {
        void bindSalesMan(boolean s);
        void cancleSalesMan(boolean s);
        void getSalesMan(SalesMan salesMan);
    }

    public interface Presenter {
        void bindSalesMan(String code);
        void cancleSalesMan();
        void getSalesMan();


    }
}
