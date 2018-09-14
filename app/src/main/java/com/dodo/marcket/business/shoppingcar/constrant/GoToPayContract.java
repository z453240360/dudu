package com.dodo.marcket.business.shoppingcar.constrant;



import com.dodo.marcket.base.BaseView;
import com.dodo.marcket.bean.MyAddressBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class GoToPayContract {

    public interface View extends BaseView {
        void getMyAddress(List<MyAddressBean> s);
    }

    public interface Presenter {
        void payProducts();
        void getAddress();
    }
}
