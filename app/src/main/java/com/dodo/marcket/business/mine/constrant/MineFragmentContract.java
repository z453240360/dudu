package com.dodo.marcket.business.mine.constrant;



import com.dodo.marcket.base.BaseView;
import com.dodo.marcket.bean.LoginBean;
import com.dodo.marcket.bean.basebean.UserInfoBean;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class MineFragmentContract {

    public interface View extends BaseView {
        void getUserMsg(UserInfoBean userInfoBean);
    }

    public interface Presenter {
        void getUserMsg();
    }
}
