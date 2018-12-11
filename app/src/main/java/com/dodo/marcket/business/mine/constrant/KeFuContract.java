package com.dodo.marcket.business.mine.constrant;



import com.dodo.marcket.base.BaseView;
import com.dodo.marcket.bean.BackBoxBean;
import com.dodo.marcket.bean.KeFuBean;
import com.dodo.marcket.bean.params.BackBoxParams;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class KeFuContract {

    public interface View extends BaseView {
        void getKeFuList(KeFuBean list);
        void sendResult();
    }

    public interface Presenter {
        void getKeFuList(int page,int pageSize);
        void sendKeFuMsg(String strParam);
    }
}
