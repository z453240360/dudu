package com.dodo.marcket.business.mine.constrant;



import com.dodo.marcket.base.BaseView;
import com.dodo.marcket.bean.LoginBean;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class LoginContract {

    public interface View extends BaseView {
        void getVerificationCode();
        void getLogin(LoginBean loginBean);
    }

    public interface Presenter {
        void getVerificationCode(String mobile);
        void getLogin(String userName,String password);
    }
}
