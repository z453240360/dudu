package com.dodo.marcket.business.mine.constrant;



import com.dodo.marcket.base.BaseView;
import com.dodo.marcket.bean.AreaParamBean;
import com.dodo.marcket.bean.MyAddressBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class MyAddressContract {

    public interface View extends BaseView {
        void getMyAddress(List<MyAddressBean> myAddressBeanList);
        void deleteReceiver();
    }

    public interface Presenter {
        void getMyAddress();
        void deleteReceiver();
        void updateReceiver(long id, String consignee, String phone, String address, boolean isDefault, String province, AreaParamBean areaParam);
    }
}
